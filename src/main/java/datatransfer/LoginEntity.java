package datatransfer;

public class LoginEntity {
	
	private String plaintextPassword;
	private String email;
	
	public String getPlaintextPassword() {
		return plaintextPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setPlaintextPassword(String password) {
		this.plaintextPassword = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
