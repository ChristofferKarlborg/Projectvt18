package dataaccess.dao;


import java.util.GregorianCalendar;
import java.util.List;

import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Flight;

public interface FlightDao {

	// Create
	public void createFlight(Flight newFlight);

	// Read
	public Flight findFlightById(int flightId);
	public Flight findFlightByAircraftRegistrationNumber(String aircraftRegistrationNumber) throws IncorrectAmountOfQueryResultsException;
	public List<Flight> findFlightByArrivalTime(GregorianCalendar arrivalTime);
	public List<Flight> findFlightByDeparture(GregorianCalendar departure) ;
	public List<Flight> findFlightByCompanyID(int companyID) ;
	public List<Flight> findFlightByInternational(boolean international) ;
	public List<Flight> findFlightByGate(int gate);
	public List<Flight> findFlightByDelayed();
	
	// Update
	public void updateFlight(Flight flightToUpdate) throws UserDoesNotExistException;

	// Delete
	public void removeFlightById(int flightId) throws IncorrectAmountOfQueryResultsException;

}
