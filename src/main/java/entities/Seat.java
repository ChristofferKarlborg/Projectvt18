package entities;

import javax.persistence.Entity;

@Entity
public class Seat  implements EntityIdAccess{
	private int id;
	private int flight;
	private boolean occupied;
	private String type;
	
	public int getId() {
		return id;
	}
	public int getFlight() {
		return flight;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public String getType() {
		return type;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFlight(int flight) {
		this.flight = flight;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
