package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dataaccess.dao.SeatDao;
import dataaccess.daoimpl.SeatDaoImpl;
import dataaccess.exceptions.EntityDoesNotExistsException;
import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import entities.Seat;
import utilities.SeatClass;

public class Test_SeatDao {

	private static SeatDao dao = new SeatDaoImpl();

	@Test(expected = IncorrectAmountOfQueryResultsException.class)
	public void test_SeatCanBeDeleted() throws IncorrectAmountOfQueryResultsException {
		// Arrange
		Seat seat1 = new Seat(SeatClass.FIRST_CLASS);
		dao.createSeat(seat1);

		// Act
		try {
			dao.removeSeat(seat1);

			// Assert
			dao.findSeatById(seat1.getId());

		} catch (EntityDoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fail();
	}

	@Test
	public void test_SeatCanBeUpdated() {
		// Arrange
		Seat seat2 = new Seat(SeatClass.BUSINESS_CLASS);
		Seat tmpSeat = null;
		dao.createSeat(seat2);

		// Act
		try {
			seat2.setType(SeatClass.ECONOMY_CLASS);
			dao.updateSeat(seat2);
			tmpSeat = dao.findSeatById(seat2.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Assert
		assertTrue(tmpSeat.getType() == SeatClass.ECONOMY_CLASS);
	}

	@Test
	public void test_SeatCanBeFoundByFlightId() {
		// Arrange
		Seat seat3 = new Seat(SeatClass.FIRST_CLASS);
		seat3.setFlight(1234);

		dao.createSeat(seat3);
		dao.createSeat(seat3);

		// Act
		List<Seat> seatList = dao.findSeatByFlightId(1234);

		// Assert
		for (Seat seat : seatList) {
			assertTrue(seat.getFlight() == 1234);
		}

	}

	@Test
	public void test_SeatCanBeFoundBySeatId() {
		// Arrange
		Seat seat4 = new Seat(SeatClass.FIRST_CLASS);
		int id = -1;
		dao.createSeat(seat4);
		
		//Act
		try {
			id = dao.findSeatById(seat4.getId()).getId();
		} catch (IncorrectAmountOfQueryResultsException e) {
			fail();
		}
		
		//Assert
		assertTrue(id == seat4.getId());
	}
}














