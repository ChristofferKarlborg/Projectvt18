package utilities;

public enum SeatClass {
	FIRST_CLASS ("First Class"),
	BUSINESS_CLASS ("Business Class"),
	ECONOMY_CLASS ("Economy Class");
	
	private final String classOfSeat;
	
	private SeatClass(String classOfSeat) {
		this.classOfSeat = classOfSeat;
	}
	
	public String asString() {
		return this.classOfSeat;
	}
}
