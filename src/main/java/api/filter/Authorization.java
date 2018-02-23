package api.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;



@Provider
@AuthorizationRequired
public class Authorization implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext context) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Filter unimplemented");
		
		//context.abortWith(Response.status(404).build());
		//return;
		
	}


}
