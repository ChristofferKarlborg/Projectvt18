package api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import dataaccess.dao.AccountDao;
import dataaccess.daoimpl.AccountDaoImpl;
import dataaccess.exceptions.UserDoesNotExistException;
import entities.Account;
import filter.AuthorizationRequired;


@AuthorizationRequired
@Path("")
public class AccountApi {
	
// TODO: since we now have a server, we also need a to migrate to a proper db outside of testing 
//	private static AccountDao dao = new AccountDaoImpl();
	
	@GET
	@AuthorizationRequired
	public Response get() {

		return Response.ok().build();
	}
	
	
	@GET
	@Path("{email}")
	@AuthorizationRequired
	public Response get(@PathParam("email") String email) {
		
		
		try {
			Account account = new Account("asdf3","asdf@asdf2.com");
			return Response.ok(account).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
		
	
		
	
	}
}
