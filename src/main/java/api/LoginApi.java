package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dataaccess.exceptions.UserDoesNotExistException;
import datatransfer.LoginEntity;
import entities.Account;
import service.AccountService;


@Path("login")
public class LoginApi {
	
	AccountService service = AccountService.getInstance();
	
	//TODO: Class stub
	//TODO: We need both to generate rsa for the token exchange and then later, a token;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate( LoginEntity login) {
		
		//TODO: remove, for testing only
		
		
		if(login.getPlaintextPassword().equals("asdf2" ) && login.getEmail().equals("asdf@asdf") ) {
			return Response.status(200).build();
		}
		
		try {
			Account accountTocheck = service.getAccountByEmail(login.getEmail());
			
			if( accountTocheck.getPassword() == Account.encryptPassword(login.getPlaintextPassword()) ) {
				
				return Response.status(200).build();
				
			}
			
			
		} catch (UserDoesNotExistException e) {
			
		}
		
		return Response.status(400).entity("Invalid credentials").build();
	}
}
