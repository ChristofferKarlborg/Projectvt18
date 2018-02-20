package utilities;

public enum FlightField {
	REGISTRATION_NUMBER("aircraftRegistrationNumber"),
	DEPARTURE_TIME("departure"),
	ARRIVAL_TIME("arrivalTime"),
	COMPANY("aircraftRegistrationNumber"),
	STARTLOCATION("startLocation"),
	DESTINATION("destination");
	
private final String fieldName;
	
	private FlightField(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String asFieldName() {
		return this.fieldName;
	}
}
