package entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import utilities.EntityIdAccess;
import utilities.SeatClass;

@Entity
public class Seat  implements EntityIdAccess{
	
	@Id
	@GeneratedValue
	private int id;
	private int flight;
	
	@Enumerated(EnumType.STRING)
	private SeatClass type = null;
	
	public Seat(SeatClass type){
		this.type = type;
	}
	
	private Seat() {
		
	}
	
	public int getId() {
		return id;
	}
	public int getFlight() {
		return flight;
	}

	public SeatClass getType() {
		return type;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFlight(int flight) {
		this.flight = flight;
	}

	public void setType(SeatClass type) {
		this.type = type;
	}
	
	
}
