package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import utilities.EntityIdAccess;

@Entity
public class Ticket  implements EntityIdAccess{
	
	@Id
	@GeneratedValue
	private int id;
	
	private int seatId;
	
	private Integer customerId = null;
	
	public Ticket(int seatId) {
		this.seatId = seatId;
	}

	public int getId() {
		return id;
	}

	public int getSeatId() {
		return seatId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerId;
		result = prime * result + id;
		result = prime * result + seatId;
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
		Ticket other = (Ticket) obj;
		if (customerId != other.customerId)
			return false;
		if (id != other.id)
			return false;
		if (seatId != other.seatId)
			return false;
		return true;
	}
	

	
	
	
}
