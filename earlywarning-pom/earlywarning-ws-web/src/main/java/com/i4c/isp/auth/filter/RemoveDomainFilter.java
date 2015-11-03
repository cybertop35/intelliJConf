package com.i4c.isp.auth.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//@WebFilter("/*")
public class RemoveDomainFilter implements Filter {

	//private static final String PROPERTY_NAME = "userIdNoDomain";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		System.out.println("Header - Filter");
		HttpServletRequest req = (HttpServletRequest) request;
		Enumeration<String> e = req.getHeaderNames();
		
		while (e.hasMoreElements()) {
			String key = e.nextElement();
			System.out.println("Key: " + key + " Value: "
					+ req.getHeader(key));
		}

		chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	private static String removeDomain(String str) {
		if (str.contains("\\")) {
			return str.substring(str.indexOf("\\") + 1, str.length());
		} else
			return str;
	}
}
