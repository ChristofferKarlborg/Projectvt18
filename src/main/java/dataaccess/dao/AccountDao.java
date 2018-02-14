package dataaccess.dao;

import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Account;

public interface AccountDao {
	
	//CRUD
	public void createAccount(Account newAccount);
	public Account findAccountById(int accountId) throws IncorrectAmountOfQueryResultsException;
	public void updateAccount(Account accountToUpdate) throws UserDoesNotExistException;
	public void removeAccountById(int accountId) throws UserDoesNotExistException;
	public Account findAccountByEmail(String accountEmail) throws UserDoesNotExistException;
	public void closeConnection();
	
	//TODO: extend to include search by username
}
