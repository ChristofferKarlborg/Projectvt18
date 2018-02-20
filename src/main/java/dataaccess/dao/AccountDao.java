package dataaccess.dao;

import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Account;

public interface AccountDao {
	
	//CRUD
	public void createAccount(Account newAccount);
	public Account findAccountById(int accountId) throws IncorrectAmountOfQueryResultsException;
	public void updateAccount(Account accountToUpdate) throws UserDoesNotExistException;
	public void removeAccount(Account accountToRemove) throws UserDoesNotExistException;
	public Account findAccountByEmail(String accountEmail) throws UserDoesNotExistException;
	public Account findAccountByUserName(String userName) throws UserDoesNotExistException;
	
}
