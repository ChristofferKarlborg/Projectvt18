package dataaccess.dao;

import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Luggage;

public interface LuggageDao {

	// CRUD
		public void createLuggage(Luggage newLuggage);

		public Luggage findLuggageById(int luggageId) throws IncorrectAmountOfQueryResultsException;

		public void updateLuggage(Luggage luggageToUpdate) throws UserDoesNotExistException;

		public void removeLuggageById(int luggageId) throws UserDoesNotExistException;
}
