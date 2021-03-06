package dataaccess.daoimpl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import dataaccess.dao.TicketDao;
import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Company;
import entities.Flight;
import entities.Ticket;
import utilities.SimpleGenericCrud;

//TODO: implement and check get by flight id

public class TicketDaoImpl implements TicketDao {

	private SimpleGenericCrud<Ticket> crudDao = new SimpleGenericCrud(Ticket.class);
	private Session session = crudDao.session;

	@Override
	public void createTicket(Ticket newTicket) {
		crudDao.createEntity(newTicket);
	}

	@Override
	public Ticket findTicketById(int ticketId) throws IncorrectAmountOfQueryResultsException {
		return crudDao.findEntityById(ticketId);
	}

	@Override
	public void updateTicket(Ticket ticketToUpdate) throws UserDoesNotExistException {
		crudDao.updateEntity(ticketToUpdate);
	}

	@Override
	public void removeTicketById(int ticketId) throws UserDoesNotExistException {
		try {
			crudDao.removeEntity(crudDao.findEntityById(ticketId));
		} catch (IncorrectAmountOfQueryResultsException e) {
			throw new UserDoesNotExistException("User does not exists");
		}
	}

	@Override
	public List<Ticket> findTicketByCustomerId(int customerId) throws IncorrectAmountOfQueryResultsException {

		return crudDao.findByFieldValue("customerId", Integer.toString(customerId));
	}

	@Override
	public Ticket findTicketBySeatId(int seatId) throws IncorrectAmountOfQueryResultsException {

		return crudDao.findByFieldValueSingleResult("seatId", Integer.toString(seatId));
	}

	@Override
	public List<Ticket> getTicketsByFlightId(int flightId) {
		
		//TODO: implement
		
		return null;

	}

}
