package entities;

public class Luggage implements EntityIdAccess {

	private int id;
	private int weight;
	private int flight;
	
	public int getId() {
		return id;
	}
	public int getWeight() {
		return weight;
	}
	public int getFlight() {
		return flight;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public void setFlight(int flight) {
		this.flight = flight;
	}
	
	
}
