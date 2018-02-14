package dataaccess.daoimpl;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import dataaccess.dao.FlightDao;
import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Flight;
import utilities.SimpleGenericCrud;

public class FlightDaoImpl implements FlightDao {

	// TODO: turn to singleton;
	private SimpleGenericCrud<Flight> crudDao = new SimpleGenericCrud(Flight.class);
	private Session session = crudDao.session;

	public FlightDaoImpl() {

	}

	@Override
	public void createFlight(Flight newFlight) {
		crudDao.createEntity(newFlight);

	}

	@Override
	public Flight findFlightById(int flightId) {

		// TODO :Find a better way to handle sql Exceptions
		try {
			return crudDao.findEntityById(flightId);
		} catch (IncorrectAmountOfQueryResultsException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateFlight(Flight flightToUpdate) throws UserDoesNotExistException {
		crudDao.updateEntity(flightToUpdate);
	}

	@Override
	public void removeFlightById(int flightId) throws IncorrectAmountOfQueryResultsException {
		Flight flightToRemove = null;
		flightToRemove = findFlightById(flightId);
		crudDao.removeEntity(flightToRemove);

	}

	@Override
	public Flight findFlightByAircraftRegistrationNumber(String aircraftRegistrationNumber)
			throws IncorrectAmountOfQueryResultsException {
		return crudDao.findByFieldValueSingleResult("aircraftRegistrationNumber", aircraftRegistrationNumber);
	}

	@Override
	public List<Flight> findFlightByArrivalTime(GregorianCalendar arrivalTime) {

		// Build Criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Flight> criteria = builder.createQuery(Flight.class);
		Root<Flight> root = criteria.from(Flight.class);
		criteria.select(root).where(builder.equal(root.get("arrivalTime"), arrivalTime));

		// Execute Query
		List<Flight> entityList = crudDao.executeQueryThenCommit(crit -> {
			return session.createQuery(crit).getResultList();
		}, criteria);

		return entityList;
	}

	@Override
	public List<Flight> findFlightByDeparture(GregorianCalendar departure) {
		
		// Build Criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Flight> criteria = builder.createQuery(Flight.class);
		Root<Flight> root = criteria.from(Flight.class);
		criteria.select(root).where(builder.equal(root.get("departure"), departure));

		// Execute Query
		List<Flight> entityList = crudDao.executeQueryThenCommit(crit -> {
			return session.createQuery(crit).getResultList();
		}, criteria);

		return entityList;
	}

	@Override
	public List<Flight> findFlightByCompanyID(int companyID) {
		return crudDao.findByFieldValue("companyID", Integer.toString(companyID));
	}

	@Override
	public List<Flight> findFlightByInternational(boolean international) {
		return crudDao.findByFieldValue("companyID", Boolean.toString(international));
	}

	@Override
	public List<Flight> findFlightByGate(int gate) {
		return crudDao.findByFieldValue("gate", Integer.toString(gate));
	}

	@Override
	public List<Flight> findFlightByDelayed() {

		// Build Criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Flight> criteria = builder.createQuery(Flight.class);
		Root<Flight> root = criteria.from(Flight.class);
		criteria.select(root).where(builder.gt(root.get("delayed"), 0));

		// Execute Query
		List<Flight> entityList = crudDao.executeQueryThenCommit(crit -> {
			return session.createQuery(crit).getResultList();
		}, criteria);

		return entityList;

	}

}
