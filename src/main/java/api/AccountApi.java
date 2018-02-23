package api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import api.filter.AuthorizationRequired;
import datatransfer.AccountCreationEntity;
import entities.Account;
import service.AccountService;
import service.exception.ValidationException;


@Path("account")
public class AccountApi {
	
	private static AccountService service = new AccountService();
	
	@GET
	@AuthorizationRequired
	public Response get() {

		return Response.ok().build();
	}
	
	
	@GET
	@Path("{email}")
	@AuthorizationRequired
	public Response get(@PathParam("email") String email) {
		System.out.println("in api");
		
		try {
			Account account = new Account("username", "email", "password");
			return Response.ok(account).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}

	}
	
	
	@POST
	@Path("new")
	public Response create(AccountCreationEntity account) {
		
		try {
			service.createNewAccount(account.getEmail(), account.getPassword(), account.getEmail());
			
			return Response.status(200).build();
		}catch (ValidationException e) {
			return Response.status(400).build();
		}
	
	}
}
