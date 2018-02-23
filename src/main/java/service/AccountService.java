package service;

import dataaccess.dao.AccountDao;
import dataaccess.daoimpl.AccountDaoImpl;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Account;
import service.exception.InvalidPasswordException;
import service.exception.InvalidUserNameException;
import service.exception.ValidationException;

public class AccountService {

	private static AccountService instance = null;
	private AccountDao dao = new AccountDaoImpl();

	public static AccountService getInstance() {

		if (instance == null) {
			instance = new AccountService();
		}

		return instance;
	}
	
	public void createNewAccount(String email, String rawTextPassword, String username) throws ValidationException{
		if( ! validateNewAccount(email, rawTextPassword, username))
			throw new ValidationException();
		
		//TODO: Implement some form of actual password hashing apart from messagedigest;
		
		
		dao.createAccount(new Account( username, email, rawTextPassword));
		
	}
	
	private boolean validateNewAccount(String email, String rawTextPassword, String username){

		return ( ! emailIsInUse(email) || ! usernameIsInUse(username) || ! rawTextPasswordIsInvalid(rawTextPassword) );
	}
	
	private boolean rawTextPasswordIsInvalid(String password) {
		return password.length()<10 ? true:false; 
	}
	
	private boolean emailIsInUse(String Email){
		try {
			dao.findAccountByEmail(Email);
			return true;
		}catch(UserDoesNotExistException e){
			return false;
		}
	}
	
	private boolean usernameIsInUse(String username) {
		try {
			dao.findAccountByUserName(username);
			return true;
		}catch(UserDoesNotExistException e){
			return false;
		}
	}

//	public Account checkCredentials(String email, String password)
//			throws InvalidUserNameException, InvalidPasswordException {
//
//		try {
//			//Is there an account with that email?
//			Account foundAccount = dao.findAccountByEmail(email);
//			
//			//Does that account have that password!
//			if( foundAccount.comparePassword(email)) {
//				return foundAccount;
//				
//			}else {
//				//Wrong password
//				throw new InvalidPasswordException();
//			}
//			
//		} catch (UserDoesNotExistException e) {
//				//No account registered to that email
//				throw new InvalidUserNameException();
//			
//		}
//	
//	}
}
