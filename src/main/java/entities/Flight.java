package entities;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import utilities.EntityIdAccess;

@Entity
public class Flight implements EntityIdAccess {
 
	
	
	@Id
	@GeneratedValue
	private int id;
	
	private String aircraftRegistrationNumber;
	private String startLocation;
	private String destination;
	
	
	//TODO: replace with the new java date/time
	private GregorianCalendar departure;
	private GregorianCalendar arrivalTime;
	
	private boolean international;
	
	private int companyID;	
	private int gate;
	private int delayed;
		
	public Flight(String aircraftRegistrationNumber,
			String startLocation,
			String destination,
			GregorianCalendar departure,
			GregorianCalendar arrivalTime,
			int companyID,
			boolean international,
			int gate,
			int delayed){
		
		super();
		this.aircraftRegistrationNumber = aircraftRegistrationNumber;
		this.startLocation = startLocation;
		this.destination = destination;
	
		this.arrivalTime = arrivalTime;
		this.departure = departure;
		this.companyID = companyID;
		this.international = international;
		this.gate = gate;
		this.delayed = delayed;
	}
	
	private Flight() {
		
	}
	
	public int getId() {
		return id;
	}
	public String getAircraftRegistrationNumber() {
		return aircraftRegistrationNumber;
	}

	public String getStartLocation() {
		return startLocation;
	}

	public String getDestination() {
		return destination;
	}

	public GregorianCalendar getArrivalTime() {
		return arrivalTime;
	}

	public GregorianCalendar getDeparture() {
		return departure;
	}
	public int getCompanyID() {
		return companyID;
	}
	public boolean isInternational() {
		return international;
	}
	public int getGate() {
		return gate;
	}
	public int getDelayed() {
		return delayed;
	}

	public void setDeparture(GregorianCalendar departure) {
		this.departure = departure;
	}
	public void setInternational(boolean international) {
		this.international = international;
	}
	public void setGate(int gate) {
		this.gate = gate;
	}
	public void setDelayed(int delayed) {
		this.delayed = delayed;
	}
	
	public boolean noFieldIsNull() {
		if (      this.destination == null
				||this.startLocation == null
				||this.arrivalTime == null
				||this.departure == null
				||this.aircraftRegistrationNumber == null
				) {
			return false;
		}else {
			return true;
		}
				
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aircraftRegistrationNumber == null) ? 0 : aircraftRegistrationNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (aircraftRegistrationNumber == null) {
			if (other.aircraftRegistrationNumber != null)
				return false;
		} else if (!aircraftRegistrationNumber.equals(other.aircraftRegistrationNumber))
			return false;
		return true;
	}
	
	
	
	
	
	
	
}
