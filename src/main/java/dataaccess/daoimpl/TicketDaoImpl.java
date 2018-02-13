package dataaccess.daoimpl;

import org.hibernate.Session;

import dataaccess.dao.TicketDao;
import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Company;
import entities.Ticket;

public class TicketDaoImpl implements TicketDao{
	
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
			throw new UserDoesNotExistException();
		}
	}

	@Override
	public void findTicketByCustomerId(int customerID) {
		// TODO Auto-generated method stub
		
	}

}
