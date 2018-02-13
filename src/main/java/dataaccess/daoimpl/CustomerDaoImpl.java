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

import dataaccess.dao.CustomerDao;
import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Customer;
import utilities.HibernateUtilities;

public class CustomerDaoImpl implements CustomerDao {

	private SimpleGenericCrud<Customer> crudDao = new SimpleGenericCrud(Customer.class);
	Session session = HibernateUtilities.getSessionFactory().openSession();

	
	@Override
	public void createCustomer(Customer newCustomer) {
		executeQueryThenCommit((Customer s) -> {
			session.save(s);
		}, newCustomer);
	}

	@Override
	public Customer findCustomerById(int customerId) throws IncorrectAmountOfQueryResultsException {

		// Build Criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
		Root<Customer> root = criteria.from(Customer.class);
		criteria.select(root).where(builder.equal(root.get("id"), customerId));

		// Execute Query
		List<Customer> customer = executeQueryThenCommit(crit -> {
			return session.createQuery(crit).setMaxResults(1).getResultList();
		}, criteria);

		// Check result size and return
		if (customer.size() != 1) {
			throw new IncorrectAmountOfQueryResultsException("Amount of users returned from DB should be 1, is : " + customer.size() );
		}
		return customer.get(0);

	}

	@Override
	public void updateCustomer(Customer customerToUpdate) throws UserDoesNotExistException {

		try {

			// Find the existing customer
			Customer persistentUser = findCustomerById(customerToUpdate.getId());
			persistentUser.setCustomer(customerToUpdate);

			// Update
			executeQueryThenCommit(customer -> {
				session.update(customer);
			}, persistentUser);

		} catch (IncorrectAmountOfQueryResultsException e) {
			// TODO: handle exception
		}

	}

	@Override
	public void removeCustomerById(int customerId) {

		try {

			// Find the existing customer
			Customer persistentUser = findCustomerById(customerId);

			// Update
			executeQueryThenCommit(customer -> {
				session.delete(customer);
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
	public Customer findCustomerByEmail(String customerEmail) throws UserDoesNotExistException  {

		List<Customer> customerToReturnList = crudDao.findByFieldValue("email", customerEmail);
		if(customerToReturnList.size() == 0) {
			throw new UserDoesNotExistException();
			
		//TODO: set up case for this;	
		//If we get more than one result, we have an issue where multiple accounts use the same email, this is not allowed and then we've missed some validation somewhere;
		}else if(customerToReturnList.size() > 1) {
			System.out.println("[WARNING] Multiple results from email query, returning only first one");
		}
		
		return customerToReturnList.get(0);
		
	}

}
