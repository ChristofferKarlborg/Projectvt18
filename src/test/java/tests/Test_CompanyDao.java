package tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dataaccess.dao.CompanyDao;
import dataaccess.dao.CustomerDao;
import dataaccess.daoimpl.CompanyDaoImpl;
import dataaccess.daoimpl.CustomerDaoImpl;
import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Company;
import entities.Customer;
import utilities.HibernateUtilities;

public class Test_CompanyDao {

	private static CompanyDao dao = new CompanyDaoImpl();
	
	@BeforeClass
	public static void setUp() {
		dao.createCompany(new Company( "Acme1" ));
		dao.createCompany(new Company( "Acme2" ));
		dao.createCompany(new Company( "Acme3" ));
		dao.createCompany(new Company( "Acme4" ));
	}
	
	@Test
	public void companyCanBeFoundByName() {

		Company companyToBefound = null;
		
		try {
			companyToBefound = dao.findByName("Acme1");
		} catch (IncorrectAmountOfQueryResultsException e) {
			System.out.println(e.getMessage());
			fail();
		}
		
		assertTrue("Name is " + companyToBefound.getCompanyName() + ", Should be Acme1",companyToBefound.getCompanyName().equals("Acme1"));

	}
	
	@Test
	public void gettingNonExistantCompanyCausesException() {

		Company companyToBefound;
		
		try {
			companyToBefound = dao.findByName("Acme111");
			fail();
		} catch (IncorrectAmountOfQueryResultsException e) {
			
		}

	}
	
	@Test
	public void companyCanBeUpdated() {
		Company companyToUpdate = null;
		try {
			//Get object
			companyToUpdate = dao.findByName("Acme3");
			companyToUpdate.setCompanyName("Acme33");
			
			//Perform update
			dao.updateCompany(companyToUpdate);
			
			//Get the updated version From DB
			Company updatedCompany = dao.findCompanyById(companyToUpdate.getId());
			
			//Assert that the update has been done
			assertTrue(updatedCompany.getCompanyName().equals("Acme33"));
		
			//The type of exception doesn't matter really, it's a fail anyhow
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			fail();
		}
	}

	@Test
	public void companyCanBeDeleted() {

		Company companyToRemove = null;
		try {
			//Get object
			companyToRemove = dao.findByName("Acme2");
			
			//Perform delete
			dao.removeCompany(companyToRemove);
			
			//Attempt to get the company, this should cause an exception
			Company updatedCompany = dao.findCompanyById(companyToRemove.getId());
			fail();
			
		} catch (IncorrectAmountOfQueryResultsException e) {
			//Success
			
		} catch (UserDoesNotExistException e) {
			
			//The user did not exist when attempting removal
			e.printStackTrace();
			fail();
		}
	}
}
