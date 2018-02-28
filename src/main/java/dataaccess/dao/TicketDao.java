package dataaccess.dao;

import java.util.List;

import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Ticket;


public interface TicketDao {
	
	// CRUD
				public void createTicket(Ticket newTicket);

				public Ticket findTicketById(int ticketId) throws IncorrectAmountOfQueryResultsException;

				public void updateTicket(Ticket ticketToUpdate) throws UserDoesNotExistException;

				public void removeTicketById(int ticketId) throws UserDoesNotExistException;
				
				public List<Ticket>findTicketByCustomerId(int customerID) throws IncorrectAmountOfQueryResultsException;
				
				public Ticket findTicketBySeatId(int seatId) throws IncorrectAmountOfQueryResultsException;
				
	// Other
				
				public List<Ticket> getTicketsByFlightId(int flightId);

}
