package service;

import dataaccess.dao.FlightDao;
import dataaccess.daoimpl.FlightDaoImpl;

public class FlightService {
	private static FlightService instance = null;
	private FlightDao dao = new FlightDaoImpl();

	public static FlightService getInstance() {

		if (instance == null) {
			instance = new FlightService();
		}

		return instance;
	}
	
//	public void registerNewFlight(String aircraftRegistrationNumber, int arrivalTime, int departure, int companyID, boolean international, int gate, int delayed, List<Seat> seats) throws ValidationException{
//		//TODO: implement
//		System.out.println("fix this");
//	}
}
