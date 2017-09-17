package com.fantagame.be.business.service.oauth2.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Ryan Heaton
 * @author Dave Syer
 */
public class TestImplicitProvider {

	@Rule
	public ServerRunning serverRunning = ServerRunning.isRunning();
	
	private String implicitUrl(String clientId) {
		URI uri = serverRunning.buildUri("/FantaGameBE/app/oauth/authorize").queryParam("response_type", "token")
				.queryParam("state", "mystateid").queryParam("client_id", clientId)
				.queryParam("redirect_uri", "http://anywhere").queryParam("scope", "read").build();
		return uri.toString();
	}

	/**
	 * tests the basic implicit provider
	 */
	@Test
	public void testBasicImplicitProvider() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));

		ResponseEntity<Void> result = serverRunning.getForResponse(implicitUrl("my-less-trusted-autoapprove-client"), headers);
		assertEquals(HttpStatus.FOUND, result.getStatusCode());
		String location = result.getHeaders().getLocation().toString();
		String cookie = result.getHeaders().getFirst("Set-Cookie");

		assertNotNull("Expected cookie in " + result.getHeaders(), cookie);
		headers.set("Cookie", cookie);

		ResponseEntity<String> response = serverRunning.getForString(location, headers);
		// should be directed to the login screen...
		assertTrue(response.getBody().contains("/FantaGameBE/app/pub/login/login_security_check.do"));
		assertTrue(response.getBody().contains("username"));
		assertTrue(response.getBody().contains("password"));

		location = "/FantaGameBE/app/FantaGameBE/app/pub/login/login_security_check.do";

		MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
		formData.add("j_username", "cybertop");
		formData.add("j_password", "scarabeo");

		result = serverRunning.postForRedirect(location, headers, formData);

		System.out.println(result.getStatusCode());
		System.out.println(result.getHeaders());

		assertNotNull(result.getHeaders().getLocation());
		assertTrue(result.getHeaders().getLocation().toString().matches("http://anywhere#access_token=.+"));
	}
	
	/**
	 * tests the basic implicit provider
	 */
	@Test
	public void testPostForToken() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<Void> result;

		MultiValueMap<String, String> formData;
		formData = new LinkedMultiValueMap<String, String>();
		formData.add("j_username", "cybertop");
		formData.add("j_password", "scarabeo");

		String location = "/FantaGameBE/app/pub/login/login_security_check.do";
		result = serverRunning.postForStatus(location, headers, formData);
		assertEquals(HttpStatus.FOUND, result.getStatusCode());
		String cookie = result.getHeaders().getFirst("Set-Cookie");

		assertNotNull("Expected cookie in " + result.getHeaders(), cookie);
		headers.set("Cookie", cookie);

		location = "/FantaGameBE/app/oauth/authorize";
		formData = new LinkedMultiValueMap<String, String>();
		formData.add("response_type", "token");
		formData.add("state", "mystateid");
		formData.add("client_id", "my-less-trusted-client");
		formData.add("redirect_uri", "http://anywhere");
		formData.add("scope", "read");
		
		result = serverRunning.postForStatus(location, headers, formData);
		// The approval page
		assertEquals(HttpStatus.OK, result.getStatusCode());

		formData = new LinkedMultiValueMap<String, String>();
		formData.add(AuthorizationEndpoint.USER_OAUTH_APPROVAL, "true");
		result = serverRunning.postForStatus(location, headers, formData);
		assertEquals(HttpStatus.FOUND, result.getStatusCode());

		location = result.getHeaders().getLocation().toString();
		System.err.println(location);
		URI redirection = serverRunning.buildUri(location).build();
		assertEquals("anywhere", redirection.getHost());
		assertEquals("http", redirection.getScheme());

		// we've got the access token.
		String fragment = redirection.getFragment();
		assertNotNull("No fragment in redirect: "+redirection, fragment);
		
	}

	/**
	 * tests the basic implicit provider
	 */
	@Test
	public void testPostForAutomaticApprovalToken() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<Void> result;

		MultiValueMap<String, String> formData;
		formData = new LinkedMultiValueMap<String, String>();
		formData.add("j_username", "cybertop");
		formData.add("j_password", "scarabeo");

		String location = "/FantaGameBE/app/pub/login/login_security_check.do";
		result = serverRunning.postForStatus(location, headers, formData);
		assertEquals(HttpStatus.FOUND, result.getStatusCode());
		String cookie = result.getHeaders().getFirst("Set-Cookie");

		assertNotNull("Expected cookie in " + result.getHeaders(), cookie);
		headers.set("Cookie", cookie);

		location = "/FantaGameBE/app/oauth/authorize";
		formData = new LinkedMultiValueMap<String, String>();
		formData.add("response_type", "token");
		formData.add("state", "mystateid");
		formData.add("client_id", "my-less-trusted-autoapprove-client");
		formData.add("redirect_uri", "http://anywhere");
		formData.add("scope", "read");
		
		result = serverRunning.postForStatus(location, headers, formData);
		assertEquals(HttpStatus.FOUND, result.getStatusCode());

		location = result.getHeaders().getLocation().toString();
		System.err.println(location);
		URI redirection = serverRunning.buildUri(location).build();
		assertEquals("anywhere", redirection.getHost());
		assertEquals("http", redirection.getScheme());

		// we've got the access token.
		String fragment = redirection.getFragment();
		assertNotNull("No fragment in redirect: "+redirection, fragment);
		
	}

}
