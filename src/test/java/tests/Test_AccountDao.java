package tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dataaccess.dao.AccountDao;
import dataaccess.daoimpl.AccountDaoImpl;
import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Account;
import entities.Ticket;
import utilities.HibernateUtilities;

public class Test_AccountDao {

	//TODO: Note, hardcoded to the mock implementation, to be changed later.
	
	private static AccountDao dao = new AccountDaoImpl();
	
	@BeforeClass
	public static void setUp() {

		dao.createAccount(new Account("asdf3","asdf@asdf2.com"));
		dao.createAccount(new Account("asdf4","asdf@asdf4.com"));
		dao.createAccount(new Account("asdf5","asdf@asdf5.com"));
		dao.createAccount(new Account("asdf6","asdf@asdf6.com"));
		
//		Account account7 = new Account("asdf7","asdf@asdf7.com");
//		account7.addTicket(new Ticket(account7.getId()));
//		
//		dao.createAccount(account7);
		
		
		
	}
	
	@Test
	public void test_ReadUserFromDB() {
		 //TODO: provide a copy of the DB script so this can run even if saveUser fails
		
		try {
			Account foundAccount = dao.findAccountById(1);
			assertTrue(foundAccount.getId() == 1);
			
		} catch (IncorrectAmountOfQueryResultsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test(expected = IncorrectAmountOfQueryResultsException.class)
	public void test_DeleteAccountFromDB() throws IncorrectAmountOfQueryResultsException{
	
		try {
			dao.removeAccountById(2);
			
			dao.findAccountById(2);
			System.out.println("We shouldn't get here");
			fail();
		} catch (UserDoesNotExistException e) {
			System.out.println("The user does not exists at test time");
		}		
	}
	
	@Test
	public void test_UpdateAccountInDB() {
		
		//TODO: redo 
		
		try {
			Account updatedAccount = new Account("asdf55", "asdf@asdf555.com");
			updatedAccount.setId(3);
	
			dao.updateAccount(updatedAccount);
			
			assertTrue(dao.findAccountById(3).getName().equals("asdf55"));
		} catch (Exception e) {
				fail();	
		}	
	}
	
	@Test
	public void test_AccountCanBeFoundViaEmail() {

		try {
			Account foundAccount = dao.findAccountByEmail("asdf@asdf6.com");
			assertTrue(foundAccount.getEmail().equals("asdf@asdf6.com"));
			
		} catch (UserDoesNotExistException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test(expected = UserDoesNotExistException.class)
	public void test_SearchingForInvalidEmailCausesException() throws UserDoesNotExistException {

		Account foundAccount = dao.findAccountByEmail("asdf@asdf6.asdfasdfasdfasdfasdf");
		assertTrue(foundAccount.getName().equals("asdf@asdf6.asdfasdfasdfasdfasdf"));
	}
	
	//TODO: Add back after implementing ticket  and seat classes
	
	/*
	@Test
	public void addTicketToAccount()  {
		
		
		
		try {
			Account account = dao.findAccountByEmail("asdf@asdf7.com");
			int currentTickets = account.getTickets().size();
			
			account.addTicket(new Ticket(account.getId()));
			
			
			dao.updateAccount(account);
			account = dao.findAccountByEmail("asdf@asdf7.com");
			
			account.addTicket(new Ticket(account.getId()));
			
			for(Ticket ticket : account.getTickets()) {
				System.out.println(ticket.getAccountId() + " " + ticket.getId() + " " + ticket.getSeatId());
			}
	
			
			assertTrue(account.getTickets().size() == (currentTickets + 2));
		} catch (UserDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		
	}
	
	@Test
	public void removeTicketFromAccount() throws UserDoesNotExistException {
		Account account = dao.findAccountByEmail("asdf@asdf7.com");
		int currentTickets = account.getTickets().size();
		
		account.removeTicket(account.getTickets().get(0));
		dao.updateAccount(account);
		
		account = dao.findAccountByEmail("asdf@asdf7.com");
		
		assertTrue(account.getTickets().size() == (currentTickets - 1));
	}
	*/
}
