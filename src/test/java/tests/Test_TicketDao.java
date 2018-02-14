package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;


import dataaccess.dao.TicketDao;
import dataaccess.daoimpl.TicketDaoImpl;
import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Ticket;


public class Test_TicketDao {

	private static TicketDao dao = new TicketDaoImpl();
	
	
	@Test
	public void test_TicketsCanBeUpdated() {
		//Arrange
		Ticket ticket7 = new Ticket(7);		
		dao.createTicket(ticket7);	
		int tmpId = ticket7.getId();	
		int resultingId = 0;
		
		//Act
		ticket7.setSeatId(8);
		
		try {
			dao.updateTicket(ticket7);			
			resultingId = dao.findTicketById(tmpId).getSeatId();
			
		} catch (UserDoesNotExistException e) {			
			e.printStackTrace();
			fail();
			
		} catch (IncorrectAmountOfQueryResultsException e) {			
			e.printStackTrace();
			fail();
		}
		
		//Assert		
		assertTrue( resultingId == 8);

	}
	
	@Test(expected = UserDoesNotExistException.class)
	public void test_TicketsCanBeDeleted() throws UserDoesNotExistException {
		//Arrange
		Ticket Ticket4 = new Ticket(4);
		int tmpId = Ticket4.getId();
		
		//Act-Assert
		dao.createTicket(Ticket4);		
		
		//This should cause an exception
		dao.removeTicketById(tmpId);
		
		fail();
	}
	
	@Test
	public void test_TicketsCanBeReadFromDbViaSeatId() {
		//Arrange
		dao.createTicket(new Ticket(0));
		int seatId = 1;
		
		//Act		
		try {
			seatId = dao.findTicketBySeatId(0).getSeatId();
		} catch (IncorrectAmountOfQueryResultsException e) {
			e.printStackTrace();
			fail();
		}
		
		//Assert
		assertTrue( seatId == 0);
	}
	
	@Test
	public void test_TicketsCanBeReadFromDbViaCustomerId() {
		//Arrange
		List<Ticket> ticketList = new ArrayList();
		
		Ticket ticket5 = new Ticket(5);
		ticket5.setCustomerId(5);		
		dao.createTicket(ticket5);
		
		Ticket ticket6 = new Ticket(6);
		ticket6.setCustomerId(5);		
		dao.createTicket(ticket6);
		
		//Act
		try {
			ticketList = dao.findTicketByCustomerId(5);
			
		} catch (IncorrectAmountOfQueryResultsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		//Assert
		assertTrue("Size should be 2, is " + ticketList.size(),ticketList.size() == 2);
	}

}
