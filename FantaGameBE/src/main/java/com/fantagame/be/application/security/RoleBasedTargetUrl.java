package com.fantagame.be.application.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class RoleBasedTargetUrl extends  SimpleUrlAuthenticationSuccessHandler{

	
	private final String ADMIN_URL 	= "/app/secure/admin/home.htm";
	private final String USER_URL 	= "/app/secure/home.htm";
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        
		//super.onAuthenticationSuccess(request, response, authentication);
		
		String targetUrl = "/app/pub/home.htm";
		
		if(authentication.isAuthenticated()){
			if(hasRole(authentication.getAuthorities(),"ROLE_ADMIN")){
				targetUrl = ADMIN_URL;
			}
			else if(hasRole(authentication.getAuthorities(),"ROLE_USER")){
				targetUrl = USER_URL;
			}
			else if(hasRole(authentication.getAuthorities(),"ROLE_USER","ROLE_ADMIN")){
				targetUrl = ADMIN_URL;
			}
		}
        
		logger.debug("User "+authentication.getName()+", Redirecting to DefaultSavedRequest Url: " + targetUrl);
        
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
              
        getRedirectStrategy().sendRedirect(request, response, targetUrl );//+ ";jsessionid="+request.getSession().getId());
        
        clearAuthenticationAttributes(request);
    }



	private boolean hasRole(Collection<? extends GrantedAuthority> authorities,String ...role ){
		int hasRoleCount = 0;
		List<String> roles = Arrays.asList(role);
		
		if(role != null){
			
			for (String tmp:roles){
				@SuppressWarnings("unchecked")
				Iterator<GrantedAuthority> iterator = (Iterator<GrantedAuthority>) authorities.iterator();		
				while(iterator.hasNext())
				{
					if(iterator.next().getAuthority().equals(tmp)){
						hasRoleCount++;
						break;
					}
				}
			}		
		}
		return role.length == hasRoleCount;
	}
	
}
