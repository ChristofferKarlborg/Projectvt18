package dataaccess.dao;

import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Customer;

public interface CustomerDao {
	
	//CRUD
	public void createCustomer(Customer newCustomer);
	public Customer findCustomerById(int customerId) throws IncorrectAmountOfQueryResultsException;
	public void updateCustomer(Customer customerToUpdate) throws UserDoesNotExistException;
	public void removeCustomerById(int customerId) throws UserDoesNotExistException;
	public Customer findCustomerByEmail(String customerEmail) throws UserDoesNotExistException;
	public void closeConnection();
}
