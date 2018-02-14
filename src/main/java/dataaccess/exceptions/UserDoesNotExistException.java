package dataaccess.exceptions;

public class UserDoesNotExistException extends EntityDoesNotExistsException {

	public UserDoesNotExistException(String message) {
		super(message);
	}
}
