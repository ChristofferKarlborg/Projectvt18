package tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;

import entities.Account;
import utilities.HibernateUtilities;

public class Test_HsqlDB {

	@Test
	public void test() {

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			System.out.println("Server is up!");
		} catch (Exception e) {
			System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
			e.printStackTrace();

		}

		Account tmpCustomer = new Account("asdf", "asdf@asdf.com", "asdf3");

		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();

		// CriteriaBuilder cb = session.getCriteriaBuilder();
		// CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);

		// Query q = session.createQuery(cq);

		session.save(tmpCustomer);

		//session.getTransaction().commit();
//
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Account> cq = cb.createQuery(Account.class);
		Root<Account> root = cq.from(Account.class); // SELECT * FROM

		Query q = session.createQuery(cq);

		cb.equal(root.get("name"), "asdf"); // WHERE name = "asdf";

		List<Account> people = q.getResultList();

		System.out.println(people.get(0).getName());

		System.out.println();
		session.getTransaction().commit();
		
		
		HibernateUtilities.closeDB();

		System.out.println("Closed!");
	}

}
