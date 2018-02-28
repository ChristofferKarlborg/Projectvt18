package api.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/api/*")
public class CORS implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {


		HttpServletRequest req =  ( HttpServletRequest ) request;
		HttpServletResponse res = ( HttpServletResponse) response;
		
		if(req.getHeader("Access-Control-Allow-Origin") == null) res.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		if(req.getHeader("Access-Control-Allow-Headers") == null) res.addHeader("Access-Control-Allow-Headers", "content-type");

		System.out.println("In filter");
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
