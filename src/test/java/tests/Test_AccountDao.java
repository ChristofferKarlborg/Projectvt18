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
	
	private static AccountDao dao = new AccountDaoImpl();
	
	@BeforeClass
	public static void setUp() {

		dao.createAccount(new Account("asdf3", "asdf@asdf2.com", "asdf3"));
		dao.createAccount(new Account("asdf4", "asdf@asdf4.com", "asdf3"));
		dao.createAccount(new Account("asdf5", "asdf@asdf5.com", "asdf3"));
		dao.createAccount(new Account("asdf6", "asdf@asdf6.com", "asdf3"));
		
	}
	
	@Test
	public void test_ReadUserFromDB() {
		 
		
		try {
			Account foundAccount = dao.findAccountById(1);
			assertTrue(foundAccount.getId() == 1);
			
		} catch (IncorrectAmountOfQueryResultsException e) {
		
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test(expected = IncorrectAmountOfQueryResultsException.class)
	public void test_DeleteAccountFromDB() throws IncorrectAmountOfQueryResultsException{
	
		try {
			Account tmpAccount = dao.findAccountById(2);
			
			dao.removeAccount(tmpAccount);
			
			dao.findAccountById(2);
			System.out.println("We shouldn't get here");
			fail();
		} catch (UserDoesNotExistException e) {
			System.out.println("The user does not exists at test time");
		}		
	}
	
	@Test
	public void test_UpdateAccountInDB() {
		
		try {
			Account updatedAccount = dao.findAccountById(3);
			updatedAccount.setName("asdf55");
	
			dao.updateAccount(updatedAccount);
			
			Account tmpAccount = dao.findAccountById(3);
			assertTrue("id: " + tmpAccount.getId() + "name :" +  tmpAccount.getName(),tmpAccount.getName().equals("asdf55"));
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
	
	
}
