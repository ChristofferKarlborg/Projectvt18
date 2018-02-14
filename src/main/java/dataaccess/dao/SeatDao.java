package dataaccess.dao;

import java.util.List;

import dataaccess.exceptions.EntityDoesNotExistsException;
import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Seat;
import utilities.SeatClass;

public interface SeatDao {
	// CRUD
			public void createSeat(Seat newSeat);

			public Seat findSeatById(int seatId) throws IncorrectAmountOfQueryResultsException;

			public void updateSeat(Seat seatToUpdate) throws EntityDoesNotExistsException;

			public void removeSeat(Seat seatToRemove) throws EntityDoesNotExistsException;
			
	// Other
			
			public List<Seat> findSeatByFlightId(int flightId);
			
}
