package entities;

import javax.persistence.Entity;

@Entity
public class Passenger  implements EntityIdAccess{

	
	private int id;
	private boolean checkedIn;
	private int ticket;
	
	public int getId() {
		return id;
	}
	public boolean isCheckedIn() {
		return checkedIn;
	}
	public int getTicket() {
		return ticket;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	
	
	
}
