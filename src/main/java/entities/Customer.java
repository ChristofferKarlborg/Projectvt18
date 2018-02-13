package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Customer implements EntityIdAccess{

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	private String email;
	
	//TODO: We'll deal with the tickets later, first, make sure it is saved correctly
	@OneToMany(mappedBy="customerId" , cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Ticket> purchasedTickets = new ArrayList<>();
	
	public Customer(String name, String email) {
		
		this.name = name;
		this.email = email;
	}
	
	public Customer() {
		
	}
	
	public void setCustomer( Customer otherCustomer) {
		this.name = otherCustomer.name;
		this.email = otherCustomer.email;
	}

	public void buyTicket() {
		//TODO not implemented
	}
	
	public void addTicket(Ticket newTicket) {
		this.purchasedTickets.add(newTicket);
	}
	
	public List<Ticket> getTickets(){
		return this.purchasedTickets;
	}
	
	public void removeTicket(Ticket ticketToRemove) {
		this.purchasedTickets.remove(ticketToRemove);
	}
	
	public List<Ticket> listBoughtTickets(){
		return this.purchasedTickets;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
