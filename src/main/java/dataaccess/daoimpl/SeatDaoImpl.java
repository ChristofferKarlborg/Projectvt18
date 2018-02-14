package dataaccess.daoimpl;

import java.util.List;

import dataaccess.dao.SeatDao;
import dataaccess.exceptions.EntityDoesNotExistsException;
import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import entities.Seat;
import utilities.SimpleGenericCrud;

public class SeatDaoImpl implements SeatDao {

	private SimpleGenericCrud<Seat> crudDao = new SimpleGenericCrud(Seat.class);
	
	@Override
	public void createSeat(Seat newSeat) {
		crudDao.createEntity(newSeat);
	}

	@Override
	public Seat findSeatById(int seatId) throws IncorrectAmountOfQueryResultsException {
		return crudDao.findEntityById(seatId);
	}

	@Override
	public void updateSeat(Seat seatToUpdate) throws EntityDoesNotExistsException {
		crudDao.updateEntity(seatToUpdate);
	}

	@Override
	public void removeSeat(Seat seatToRemove) throws EntityDoesNotExistsException {
		crudDao.removeEntity(seatToRemove);
	}

	@Override
	public List<Seat> findSeatByFlightId(int flightId) {
		return crudDao.findByFieldValue("flight", Integer.toString(flightId));
	}

}
