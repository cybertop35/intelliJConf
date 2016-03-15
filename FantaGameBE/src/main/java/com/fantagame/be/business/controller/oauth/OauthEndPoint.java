package com.fantagame.be.business.controller.oauth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.filter.OAuth2AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class OauthEndPoint extends AbstractAuthenticationProcessingFilter{

	protected OauthEndPoint(){
		super("/app/oauth/token");
		setAuthenticationFailureHandler(new OAuth2AuthenticationFailureHandler());
	}
	
	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				// no-op - just allow filter chain to continue to token endpoint
			}
		});
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		String clientId = request.getParameter("client_id");
		String clientSecret = request.getParameter("client_secret");

		if (clientId == null) {
			return null;
		}

		if (clientSecret == null) {
			clientSecret = "";
		}

		clientId = clientId.trim();
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(clientId,
				clientSecret);

		return this.getAuthenticationManager().authenticate(authRequest);

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}

	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		int pathParamIndex = uri.indexOf(';');

		if (pathParamIndex > 0) {
			// strip everything after the first semi-colon
			uri = uri.substring(0, pathParamIndex);
		}

		String clientId = request.getParameter("client_id");

		if (clientId == null) {
			// Give basic auth a chance to work instead (it's preferred anyway)
			return false;
		}

		return super.requiresAuthentication(request, response);
	}
}
