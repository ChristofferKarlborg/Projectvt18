package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import utilities.HibernateUtilities;

public class InMemoryDbUtility extends RunListener{

	 @Override
	  public void testRunFinished(Result result) throws Exception {
		 System.out.println("Closing down session entity manager");
		 HibernateUtilities.closeDB();
	  }

}
