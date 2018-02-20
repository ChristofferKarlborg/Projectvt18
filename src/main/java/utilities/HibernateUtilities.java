package utilities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtilities {

	// Creating a session factory in hibernate is EXTREMELY expensive, it is highly
	// recommended to create a singleton

	
	private static SessionFactory sessionFactory = null;

	public static synchronized SessionFactory getSessionFactory( String hibernateConfigurationLocation) {
		
		if (sessionFactory == null) {
			try {
				Configuration cfg;
				if(hibernateConfigurationLocation == null) {
					cfg = new Configuration().configure();
				}else {
					cfg = new Configuration().addResource(hibernateConfigurationLocation).configure();
				}
				
				sessionFactory = cfg.buildSessionFactory();

			} catch (Exception e) {
				System.out.println("Session factory creation failed");
				e.printStackTrace();
			}
		}

		return sessionFactory;
	}
	
	
	public static synchronized SessionFactory getSessionFactory() {
		return getSessionFactory(null);
	}
	
	public static void closeDB() {
		if(sessionFactory != null) {
			sessionFactory.close();
		}
	}
	
}
