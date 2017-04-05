package heroes.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class AuthFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        final String authHeader = request.getHeader("Authorization");
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) 
        	token = authHeader.substring(7); // The part after "Bearer "
        
        String URI = request.getRequestURI();
        if(URI.startsWith("/api")){
        	if(!URI.startsWith("/api/auth")){
            	if(token == null){
    	        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    	        	return;
            	}
            	else{        		
            		try{
            			final String userName = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
            			//
            		}
            		catch(SignatureException e){
            			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            			return;
            		}
            	}
        	}
        }
        
        chain.doFilter(req, res);
	}
	
}
