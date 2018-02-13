package dataaccess.dao;

import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Ticket;


public interface TicketDao {
	
	// CRUD
				public void createTicket(Ticket newTicket);

				public Ticket findTicketById(int ticketId) throws IncorrectAmountOfQueryResultsException;

				public void updateTicket(Ticket ticketToUpdate) throws UserDoesNotExistException;

				public void removeTicketById(int ticketId) throws UserDoesNotExistException;
				
				public void findTicketByCustomerId(int customerID);

}
