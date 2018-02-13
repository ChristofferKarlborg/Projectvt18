package dataaccess.daoimpl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import dataaccess.dao.CompanyDao;
import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Company;


public class CompanyDaoImpl implements CompanyDao {

	private SimpleGenericCrud<Company> crudDao = new SimpleGenericCrud(Company.class);
	private Session session = crudDao.session;
	
	@Override
	public void createCompany(Company newCompany) {
		
		crudDao.createEntity(newCompany);
		
	}

	@Override
	public Company findCompanyById(int companyId) throws IncorrectAmountOfQueryResultsException {
		
		return crudDao.findEntityById(companyId);
	}

	@Override
	public void updateCompany(Company companyToUpdate) throws UserDoesNotExistException {

		crudDao.updateEntity(companyToUpdate);
		
	}

	@Override
	public void removeCompany(Company companyToRemove) throws UserDoesNotExistException {
		
		crudDao.removeEntity(companyToRemove);
		
	}

	@Override
	public Company findByName(String companyName) throws IncorrectAmountOfQueryResultsException {

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Company> criteria = builder.createQuery( Company.class );
		Root<Company> root = criteria.from( Company.class );
		criteria.select(root).where(builder.equal(root.get("companyName"), companyName));

		// Execute Query
		List<Company> CompanyList = crudDao.executeQueryThenCommit(crit -> {
			return session.createQuery(crit).setMaxResults(1).getResultList();
		}, criteria);

		// Check result size and return
		if (CompanyList.size() != 1) {
			throw new IncorrectAmountOfQueryResultsException("Amount of users returned from DB should be 1, is : " + CompanyList.size() );
		}
		return CompanyList.get(0);
		
	}
	
	public void closeConnection() {
		crudDao.closeConnection();
	}

}
