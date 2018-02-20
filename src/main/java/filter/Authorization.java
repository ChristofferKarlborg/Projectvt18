package filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;



@Provider
@Priority(Priorities.AUTHENTICATION)
@AuthorizationRequired
public class Authorization implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext context) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Filter unimplemented");
		
		context.abortWith(Response.status(401).build());
		return;
		
	}

}
