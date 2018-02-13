package dataaccess.dao;

import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Seat;

public interface SeatDao {
	// CRUD
			public void createSeat(Seat newSeat);

			public Seat findSeatById(int seatId) throws IncorrectAmountOfQueryResultsException;

			public void updateSeat(Seat seatToUpdate) throws UserDoesNotExistException;

			public void removeSeatById(int seatId) throws UserDoesNotExistException;
}
