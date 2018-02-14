package entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import utilities.EntityIdAccess;

@Entity
public class Account implements EntityIdAccess {

	@Id
	@GeneratedValue
	private int id;

	private String name;
	private String userName;
	private String password;
	private String email;
	
	private int companyId = -1;

	// TODO: We'll deal with the tickets later, first, make sure it is saved
	// correctly

	public Account(String name, String email) {

		this.name = name;
		this.email = email;
	}

	public Account() {

	}

	public void setCustomer(Account otherCustomer) {
		this.name = otherCustomer.name;
		this.email = otherCustomer.email;
		this.password = otherCustomer.password;
		this.companyId = otherCustomer.companyId;
	}

	public boolean comparePassword(String otherPasswordPlainText) {
		if (this.password.equals(encryptPassword(otherPasswordPlainText))) {
			return true;
		}else {
			return false;
		}
	}

	public void setPassword(String plainText) {
		this.password = encryptPassword(plainText);
	}

	private static String encryptPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			// We use a byte array because we can be absolutely certain that it will be the
			// same on any system.
			byte[] bytes = md.digest(password.getBytes());

			StringBuilder sb = new StringBuilder();

			for (byte b : bytes) {
				sb.append(String.format("02X", b));

			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return password;
	}

	// Getters and setters

	public String getUserName() {
		return userName;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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
