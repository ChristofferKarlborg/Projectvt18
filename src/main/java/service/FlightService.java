package service;



import java.util.GregorianCalendar;
import java.util.List;

import dataaccess.dao.FlightDao;
import dataaccess.dao.SeatDao;
import dataaccess.daoimpl.FlightDaoImpl;
import dataaccess.daoimpl.SeatDaoImpl;
import entities.Flight;
import entities.Seat;
import service.exception.ValidationException;


public class FlightService {
	private static FlightService instance = null;
	private FlightDao flightDao = new FlightDaoImpl();
	private SeatDao seatDao = new SeatDaoImpl();

	public static FlightService getInstance() {

		if (instance == null) {
			instance = new FlightService();
		}

		return instance;
	}
	
	//TODO: move transactions over to services, to prevent what is basically autocommit
	
	//Get the flight, the seats and add them to the DB
	public void registerNewFlight(Flight newFlight, List<Seat> seats ) throws ValidationException{
		//TODO: Complete validation, decide on a method of approach (How, where and why)
		
		//Just throw an exception, fine turning the error message and validation can be done on the front end
		
		if(   newFlight.getDeparture().after(newFlight.getArrivalTime())
			||newFlight.getDeparture().before(new GregorianCalendar()) 
			||newFlight.getDestination().equals(newFlight.getStartLocation())
			||newFlight.getAircraftRegistrationNumber().equals(null)
			){
				throw new ValidationException();
			}
		
	}
	
	public List<Flight> getAllFlights() {
		return flightDao.getAllFlights();
	}
	
	
	
}
