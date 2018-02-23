package utilities;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import dataaccess.exceptions.IncorrectAmountOfQueryResultsException;
import dataaccess.exceptions.UserDoesNotExistException;

public class SimpleGenericCrud<T extends EntityIdAccess> {

	
	//Get session from utilities and open it
	public Session session = HibernateUtilities.getSessionFactory().openSession();
	
	//Class literal, to be used of instanciating the criteria
	private Class<T> genericClass;
		
	//Constructor
	public SimpleGenericCrud( Class<T> genericClass) {
		super();
		this.genericClass = genericClass;
		
	}

	//Create
	public void createEntity(T newObj) {
		executeQueryThenCommit((T s) -> {
			session.save(s);
		}, newObj);
	}

	
	//Search
	public T findEntityById(int entityId) throws IncorrectAmountOfQueryResultsException {

		// Build Criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery( genericClass );
		Root<T> root = criteria.from( genericClass );
		criteria.select(root).where(builder.equal(root.get("id"), entityId));

		// Execute Query
		List<T> entityList = executeQueryThenCommit(crit -> {
			return session.createQuery(crit).setMaxResults(1).getResultList();
		}, criteria);

		// Check result size and return
		if (entityList.size() != 1) {
			throw new IncorrectAmountOfQueryResultsException("Amount of users returned from DB should be 1, is : " + entityList.size() );
		}
		return entityList.get(0);

	}
	
	public List<T> findByFieldValue(String nameOfField, String fieldValue) {
		
				// Build Criteria
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<T> criteria = builder.createQuery( genericClass );
				Root<T> root = criteria.from( genericClass );
				criteria.select(root).where(builder.equal(root.get(nameOfField), fieldValue));

				// Execute Query
				List<T> entityList = executeQueryThenCommit(crit -> {
					return session.createQuery(crit).getResultList();
				}, criteria);

				return entityList;		
	}
	
	public T findByFieldValueSingleResult(String nameOfField, String fieldValue) throws IncorrectAmountOfQueryResultsException{
		//Get the result from the ordinary method
		List<T> result = findByFieldValue(nameOfField, fieldValue);
		
		//Three cases, too many results, one, or none at all;
		if(result.size() > 1) {
			throw new IncorrectAmountOfQueryResultsException(" Too many results " + result.size() + "  returned, 1 expected");
		}else if(result.size() == 0) {
			throw new IncorrectAmountOfQueryResultsException(" Could not find entity");
		}
		else{
			return result.get(0);
		}
	}
	

	//Update
	public void updateEntity(T entityToUpdate) throws UserDoesNotExistException {

		try {

			// Find the existing Entity
			T persistentEntity = findEntityById(entityToUpdate.getId());

			// Update
			executeQueryThenCommit(Entity -> {
				session.update(Entity);
			}, persistentEntity);

		} catch (IncorrectAmountOfQueryResultsException e) {
			// TODO: handle exception
		}

	}

	//Delete
	public void removeEntity(T entityToRemove) {

		try {

			// Find the existing Entity
			T persistentEntity = findEntityById(entityToRemove.getId());

			// Update
			executeQueryThenCommit(Entity -> {
				session.delete(Entity);
			}, persistentEntity);

		} catch (IncorrectAmountOfQueryResultsException e) {
			// TODO: handle exception
		}
	}

	// By using these methods, we avoid surrounding each function with opening and closing the transaction

	public <T> void executeQueryThenCommit(Consumer<T> statement, T parameter) {
		session.beginTransaction();
		statement.accept(parameter);
		session.getTransaction().commit();
	}

	public <T, R> R executeQueryThenCommit(Function<T, R> statement, T parameter) {
		session.beginTransaction();
		R result = statement.apply(parameter);
		session.getTransaction().commit();
		return result;
	}
	
	public void closeConnection() {
		this.session.close();
	}

}
