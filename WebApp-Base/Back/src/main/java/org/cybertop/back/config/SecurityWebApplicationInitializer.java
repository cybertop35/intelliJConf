package org.cybertop.back.config;

import org.cybertop.back.config.root.AppSecurityConfig;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Sets up the Spring Security filter chain
 *
 */
//@Order(2)
public class SecurityWebApplicationInitializer {//extends AbstractSecurityWebApplicationInitializer {
	public SecurityWebApplicationInitializer() {
		//super(AppSecurityConfig.class);
	}
}

