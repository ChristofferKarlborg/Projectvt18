package dataaccess.dao;

import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Company;

//TODO: not done

public interface CompanyDao {

	public void createCompany(Company newCompany);
	public Company findCompanyById(int companyId) throws IncorrectAmountOfQueryResultsException;
	public Company findByName(String companyName) throws IncorrectAmountOfQueryResultsException;
	public void updateCompany(Company companyToUpdate) throws UserDoesNotExistException;
	public void removeCompany(Company companyToRemove) throws UserDoesNotExistException;
	
}
