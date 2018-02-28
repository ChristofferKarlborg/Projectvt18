package tests;

import static org.junit.Assert.*;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import dataaccess.dao.FlightDao;
import dataaccess.daoimpl.FlightDaoImpl;
import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Flight;



public class Test_FlightDao {

	private static FlightDao dao = new FlightDaoImpl();
	private static ZonedDateTime nextDate = ZonedDateTime.now().plusDays(1);
	private static ZonedDateTime twoDaysFromNow = ZonedDateTime.now().plusDays(2);

	@BeforeClass
	public static void setUp() {

		dao.createFlight(new Flight("asdf-asdf1","here","there" , null,  nextDate     , 1,     false, 21, 0));
		dao.createFlight(new Flight("asdf-asdf2","here","there" ,  null, nextDate      , 20,    true,  21, 0 ));
		dao.createFlight(new Flight("asdf-asdf3", "here","there" , null, nextDate      , 300,   false, 21, 5 ));
		dao.createFlight(new Flight("asdf-asdf4", "there","here" , null,twoDaysFromNow, 4000,  true,  21, 0 ));
		dao.createFlight(new Flight("asdf-asdf5", "there","here" , null,twoDaysFromNow, 50000, false, 22, 0 ));
		
		dao.createFlight(new Flight("asdf-asdf10",null,null ,  nextDate,twoDaysFromNow, 50000, false, 22, 0));
		dao.createFlight(new Flight("asdf-asdf11", null,null ,nextDate, nextDate, 50000, false, 22, 0));
	}
	
	@Test
	public void test_FlightCanBeReadFromDB() {
		Flight flight = new Flight("asdf-asdf12",null,null, null,nextDate, 50000, false, 00022, 0);
		dao.createFlight(flight);
		assertTrue(dao.findFlightById(flight.getId()).getId() == flight.getId());
	}
	
	@Test(expected = IncorrectAmountOfQueryResultsException.class)
	public void test_FlightCanBeDeleted() throws IncorrectAmountOfQueryResultsException{
		Flight flightToBeDeleted = dao.findFlightByAircraftRegistrationNumber("asdf-asdf11");
		dao.removeFlightById(flightToBeDeleted.getId());
		
		//Cause exception
		dao.findFlightByAircraftRegistrationNumber("asdf-asdf11");
		
		
	}
	
	@Test
	public void test_FlightCanBeUpdated() throws IncorrectAmountOfQueryResultsException {
		Flight flightToBeUpdated = dao.findFlightByAircraftRegistrationNumber("asdf-asdf10");
		flightToBeUpdated.setInternational(true);
		try {
			dao.updateFlight(flightToBeUpdated);
		} catch (UserDoesNotExistException e) {
			fail();
		}
		
		assertTrue(dao.findFlightById(flightToBeUpdated.getId()).isInternational() == true );
		
	}
	
	@Test
	public void test_FlightCanBeFoundByAircraftRegistrationNumber() throws IncorrectAmountOfQueryResultsException {
		
		Flight result = dao.findFlightByAircraftRegistrationNumber("asdf-asdf1");
		assertTrue(result.getAircraftRegistrationNumber().equals("asdf-asdf1"));
	}
	
	@Test
	public void test_FlightCanBeFoundByDeparture() {
		
		List<Flight> result = dao.findFlightByDeparture(nextDate);
		assertTrue(result.size() + " should be 2",result.size() == 2);
		
	}
	
	@Test
	public void test_FlightCanBeFoundByArrivalTime() {		
		List<Flight> result = dao.findFlightByArrivalTime(nextDate);
		
		assertTrue(result.size() + " should be 5",result.size() == 5);
	}
	
	@Test
	public void test_FlightCanBeFoundByCompanyID() {
		
		List<Flight> result = dao.findFlightByCompanyID(4000);
		
		if(result.size() > 0) {
			for(Flight flight : result ) {
				assertTrue(flight.getCompanyID() == 4000);
			}
		}else {
			fail();
		}
		
	}
	
	@Test
	public void test_FlightCanBeFoundByInternational() {
		
		List<Flight> result = dao.findFlightByCompanyID(4000);
		
		if(result.size() > 0) {
			for(Flight flight : result ) {
				assertTrue(flight.getCompanyID() == 4000);
			}
		}else {
			fail();
		}
	}
	
	@Test
	public void test_FlightCanBeFoundByGate() {
		
		
		List<Flight> result = dao.findFlightByGate(21);
		
		if(result.size() > 0) {
			for(Flight flight : result ) {
				assertTrue(flight.getGate() == 21);
				
			}
		}else {
			fail();
		}
	}

	@Test
	public void test_FlightCanBeFoundByDelayedStatus() {
		
		List<Flight> result = dao.findFlightByDelayed();
		
		if(result.size() > 0) {
			for(Flight flight : result ) {
				assertTrue(flight.getDelayed() > 0);
				
			}
		}else {
			fail();
		}
	}
	
	@Test
	public void test_getAllFlights() {
		assertTrue(dao.getAllFlights().size() > 0);
	}
	
	@Test
	public void test_GetFlightsByLocation() {
		assertTrue(dao.findFlightByDestination("there").size() == 3);
		assertTrue( dao.findFlightByDestination("here").size() == 2);
	}
	
	@Test
	public void test_GetFlightsByDestination() {
		assertTrue(dao.findFlightByStartingPosition("here").size() == 3);
		assertTrue(dao.findFlightByStartingPosition("there").size() == 2);
	}
	
	@Test
	public void test_GetFlightsByStartAndDestination() {
		assertTrue(dao.findFlightByStartAndDestination("here", "there").size() == 3);
		assertTrue(dao.findFlightByStartAndDestination("there", "here").size() == 2);
	}
	
	
	
	
}
