package utilities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtilities {

	// Creating a session factory in hibernate is EXTREMELY expensive, it is highly
	// recommended to create a singleton
	//TODO: WE could shift this over to its own file and implement this via an interface
	
	private static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			try {

				Configuration cfg = new Configuration().configure();
				sessionFactory = cfg.buildSessionFactory();

				// It is recommended to catch a more specific statement, this is of
				// demonstration purposes
			} catch (Exception e) {
				System.out.println("Session factory creation failed");
				e.printStackTrace();
			}
		}

		return sessionFactory;
	}
	
	public static void closeDB() {
		if(sessionFactory != null) {
			sessionFactory.close();
		}
	}
}
