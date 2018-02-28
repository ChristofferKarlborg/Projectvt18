package datatransfer;

/**
 * 
 * Since the account details sent for creation differs a lot from the actual server-side account, an entity is nessesary to actually contain the request
 * 
 */

public class AccountCreationEntity extends LoginEntity {
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
