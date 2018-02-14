package service;

import dataaccess.dao.AccountDao;
import dataaccess.daoimpl.AccountDaoImpl;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Account;
import service.exception.InvalidPasswordException;
import service.exception.InvalidUserNameException;

public class AccountService {

	private static AccountService instance = null;
	private AccountDao dao = new AccountDaoImpl();

	public static AccountService getInstance() {

		if (instance == null) {
			instance = new AccountService();
		}

		return instance;
	}

	public Account checkCredentials(String email, String password)
			throws InvalidUserNameException, InvalidPasswordException {

		try {
			//Is there an account with that email?
			Account foundAccount = dao.findAccountByEmail(email);
			
			//Does that account have that password!
			if( foundAccount.comparePassword(email)) {
				return foundAccount;
				
			}else {
				//Wrong password
				throw new InvalidPasswordException();
			}
			
		} catch (UserDoesNotExistException e) {
				//No account registered to that email
				throw new InvalidUserNameException();
			
		}
	
	}
}
