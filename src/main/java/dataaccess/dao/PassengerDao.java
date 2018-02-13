package dataaccess.dao;

import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Passenger;

public interface PassengerDao {
	// CRUD
		public void createPassenger(Passenger newPassenger);

		public Passenger findPassengerById(int passengerId) throws IncorrectAmountOfQueryResultsException;

		public void updatePassenger(Passenger passengerToUpdate) throws UserDoesNotExistException;

		public void removePassengerById(int passengerId) throws UserDoesNotExistException;
}
