package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import utilities.EntityIdAccess;

@Entity
public class Company implements EntityIdAccess {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String companyName;
	
	//NO need for flights and employees, we query them for the company id

	public Company( String companyName) {
		super();

		this.companyName = companyName;
	}

	//Getters and setters
	public int getId() {
		return id;
	}

	public String getCompanyName() {
		return companyName;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	

}
