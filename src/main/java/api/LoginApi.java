package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import datatransfer.LoginEntity;



public class LoginApi {
	
	//TODO: Class stub
	//TODO: We need both to generate rsa for the token exchange and then later, a token;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate( LoginEntity login) {
		
		//TODO: method stub
		
	return Response.status(404).build();
	}
}
