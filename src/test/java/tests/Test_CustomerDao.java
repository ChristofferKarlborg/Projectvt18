package tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dataaccess.dao.CustomerDao;
import dataaccess.daoimpl.CustomerDaoImpl;
import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Customer;
import entities.Ticket;
import utilities.HibernateUtilities;

public class Test_CustomerDao {

	//TODO: Note, hardcoded to the mock implementation, to be changed later.
	
	private static CustomerDao dao = new CustomerDaoImpl();
	
	@BeforeClass
	public static void setUp() {

		dao.createCustomer(new Customer("asdf3","asdf@asdf2.com"));
		dao.createCustomer(new Customer("asdf4","asdf@asdf4.com"));
		dao.createCustomer(new Customer("asdf5","asdf@asdf5.com"));
		dao.createCustomer(new Customer("asdf6","asdf@asdf6.com"));
		
//		Customer customer7 = new Customer("asdf7","asdf@asdf7.com");
//		customer7.addTicket(new Ticket(customer7.getId()));
//		
//		dao.createCustomer(customer7);
		
		
		
	}
	
	@Test
	public void test_ReadUserFromDB() {
		 //TODO: provide a copy of the DB script so this can run even if saveUser fails
		
		try {
			Customer foundCustomer = dao.findCustomerById(1);
			assertTrue(foundCustomer.getId() == 1);
			
		} catch (IncorrectAmountOfQueryResultsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test(expected = IncorrectAmountOfQueryResultsException.class)
	public void test_DeleteCustomerFromDB() throws IncorrectAmountOfQueryResultsException{
	
		try {
			dao.removeCustomerById(2);
			
			dao.findCustomerById(2);
			System.out.println("We shouldn't get here");
			fail();
		} catch (UserDoesNotExistException e) {
			System.out.println("The user does not exists at test time");
		}		
	}
	
	@Test
	public void test_UpdateCustomerInDB() {
		
		//TODO: redo 
		
		try {
			Customer updatedCustomer = new Customer("asdf55", "asdf@asdf555.com");
			updatedCustomer.setId(3);
	
			dao.updateCustomer(updatedCustomer);
			
			assertTrue(dao.findCustomerById(3).getName().equals("asdf55"));
		} catch (Exception e) {
				fail();	
		}	
	}
	
	@Test
	public void test_CustomerCanBeFoundViaEmail() {

		try {
			Customer foundCustomer = dao.findCustomerByEmail("asdf@asdf6.com");
			assertTrue(foundCustomer.getEmail().equals("asdf@asdf6.com"));
			
		} catch (UserDoesNotExistException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test(expected = UserDoesNotExistException.class)
	public void test_SearchingForInvalidEmailCausesException() throws UserDoesNotExistException {

		Customer foundCustomer = dao.findCustomerByEmail("asdf@asdf6.asdfasdfasdfasdfasdf");
		assertTrue(foundCustomer.getName().equals("asdf@asdf6.asdfasdfasdfasdfasdf"));
	}
	
	//TODO: Add back after implementing ticket  and seat classes
	
	/*
	@Test
	public void addTicketToCustomer()  {
		
		
		
		try {
			Customer customer = dao.findCustomerByEmail("asdf@asdf7.com");
			int currentTickets = customer.getTickets().size();
			
			customer.addTicket(new Ticket(customer.getId()));
			
			
			dao.updateCustomer(customer);
			customer = dao.findCustomerByEmail("asdf@asdf7.com");
			
			customer.addTicket(new Ticket(customer.getId()));
			
			for(Ticket ticket : customer.getTickets()) {
				System.out.println(ticket.getCustomerId() + " " + ticket.getId() + " " + ticket.getSeatId());
			}
	
			
			assertTrue(customer.getTickets().size() == (currentTickets + 2));
		} catch (UserDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		
	}
	
	@Test
	public void removeTicketFromCustomer() throws UserDoesNotExistException {
		Customer customer = dao.findCustomerByEmail("asdf@asdf7.com");
		int currentTickets = customer.getTickets().size();
		
		customer.removeTicket(customer.getTickets().get(0));
		dao.updateCustomer(customer);
		
		customer = dao.findCustomerByEmail("asdf@asdf7.com");
		
		assertTrue(customer.getTickets().size() == (currentTickets - 1));
	}
	*/
}
