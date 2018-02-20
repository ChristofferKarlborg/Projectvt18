package dataaccess.daoimpl;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import dataaccess.dao.AccountDao;
import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Account;
import utilities.HibernateUtilities;
import utilities.SimpleGenericCrud;

public class AccountDaoImpl implements AccountDao {

	private SimpleGenericCrud<Account> crudDao = new SimpleGenericCrud(Account.class);
	Session session = crudDao.session;

	
	@Override
	public void createAccount(Account newAccount) {
		crudDao.createEntity(newAccount);
	}

	@Override
	public Account findAccountById(int accountId) throws IncorrectAmountOfQueryResultsException {
		return crudDao.findEntityById(accountId);		
	}

	@Override
	public void updateAccount(Account accountToUpdate) throws UserDoesNotExistException {
		crudDao.updateEntity(accountToUpdate);
	}

	@Override
	public void removeAccount(Account accountToRemove) {
		crudDao.removeEntity(accountToRemove);

	}

	@Override
	public Account findAccountByEmail(String accountEmail) throws UserDoesNotExistException  {

		try {
			return crudDao.findByFieldValueSingleResult("email", accountEmail);
		} catch (IncorrectAmountOfQueryResultsException e) {
			// Since we validate email before adding accounts, we should only have an issue with 0 results, not >1
			throw new UserDoesNotExistException();
		}
		
	}

	@Override
	public Account findAccountByUserName(String userName) throws UserDoesNotExistException {
		try {
			return crudDao.findByFieldValueSingleResult("userName", userName);
		} catch (IncorrectAmountOfQueryResultsException e) {		
			throw new UserDoesNotExistException();
		}
	}

}
