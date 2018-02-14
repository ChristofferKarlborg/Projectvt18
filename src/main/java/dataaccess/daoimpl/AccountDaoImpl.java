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
	Session session = HibernateUtilities.getSessionFactory().openSession();

	
	@Override
	public void createAccount(Account newAccount) {
		executeQueryThenCommit((Account s) -> {
			session.save(s);
		}, newAccount);
	}

	@Override
	public Account findAccountById(int accountId) throws IncorrectAmountOfQueryResultsException {

		// Build Criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Account> criteria = builder.createQuery(Account.class);
		Root<Account> root = criteria.from(Account.class);
		criteria.select(root).where(builder.equal(root.get("id"), accountId));

		// Execute Query
		List<Account> account = executeQueryThenCommit(crit -> {
			return session.createQuery(crit).setMaxResults(1).getResultList();
		}, criteria);

		// Check result size and return
		if (account.size() != 1) {
			throw new IncorrectAmountOfQueryResultsException("Amount of users returned from DB should be 1, is : " + account.size() );
		}
		return account.get(0);

	}

	@Override
	public void updateAccount(Account accountToUpdate) throws UserDoesNotExistException {

		try {

			// Find the existing account
			Account persistentUser = findAccountById(accountToUpdate.getId());
			persistentUser.setCustomer(accountToUpdate);

			// Update
			executeQueryThenCommit(account -> {
				session.update(account);
			}, persistentUser);

		} catch (IncorrectAmountOfQueryResultsException e) {
			// TODO: handle exception
		}

	}

	@Override
	public void removeAccountById(int accountId) {

		try {

			// Find the existing account
			Account persistentUser = findAccountById(accountId);

			// Update
			executeQueryThenCommit(account -> {
				session.delete(account);
			}, persistentUser);

		} catch (IncorrectAmountOfQueryResultsException e) {
			// TODO: handle exception
		}

	}

	// TODO: Bara för att

	private <T> void executeQueryThenCommit(Consumer<T> statement, T parameter) {
		session.beginTransaction();
		statement.accept(parameter);
		session.getTransaction().commit();
	}

	private <T, R> R executeQueryThenCommit(Function<T, R> statement, T parameter) {
		session.beginTransaction();
		R result = statement.apply(parameter);
		session.getTransaction().commit();
		return result;
	}
	
	public void closeConnection() {
		this.session.close();
	}

	@Override
	public Account findAccountByEmail(String accountEmail) throws UserDoesNotExistException  {

		List<Account> accountToReturnList = crudDao.findByFieldValue("email", accountEmail);
		if(accountToReturnList.size() == 0) {
			throw new UserDoesNotExistException("List of returned users is empty");
			
		//TODO: set up case for this;	
		//If we get more than one result, we have an issue where multiple accounts use the same email, this is not allowed and then we've missed some validation somewhere;
		}else if(accountToReturnList.size() > 1) {
			System.out.println("[WARNING] Multiple results from email query, returning only first one");
		}
		
		return accountToReturnList.get(0);
		
	}

}
