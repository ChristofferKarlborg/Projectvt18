package api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import api.filter.AuthorizationRequired;
import dataaccess.exceptions.UserDoesNotExistException;
import datatransfer.AccountCreationEntity;
import entities.Account;
import service.AccountService;
import service.exception.ValidationException;


@Path("account")
public class AccountApi {
	
	private static AccountService service = new AccountService();
	
	@GET	
	public Response get() {
		//Testing
		System.out.println("Contact");
		
		return Response.ok().build();
	}
	
	@GET
	@Path("email/{email}")
	@AuthorizationRequired
	public Response get(@PathParam("email") String email) {
		System.out.println("in api");
		
		try {
			Account foundAccount = service.getAccountByEmail(email);
			return Response.ok().entity(foundAccount).build();
		} catch (UserDoesNotExistException e) {
			return Response.status(400).build();
		}
		
		
	}
	

	// Create account
	
	@POST
	@Path("new")
	public Response create(AccountCreationEntity account) {
		
		try {
			service.createNewAccount(account.getEmail(), account.getPlaintextPassword(), account.getUsername());
		} catch (ValidationException e) {
			return Response.status(400).build();
		}
		
		return Response.status(200).build();
	
	}
}
