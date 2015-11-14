package org.cybertop.back.config.root;




import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * The Spring Security configuration for the application - its a form login config with authentication via session cookie (once logged in),
 * with fallback to HTTP Basic for non-browser clients.
 *
 * The CSRF token is put on the reply as a header via a filter, as there is no server-side rendering on this app.
 *
 */
//@Configuration
//@EnableWebSecurity
public class AppSecurityConfig {//extends WebSecurityConfigurerAdapter {

//    private static final Logger LOGGER = Logger.getLogger(AppSecurityConfig.class);
//
//    @Autowired
//    private SecurityUserDetailsService userDetailsService;
//
//    @Autowired
//    DataSource dataSource;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        CsrfTokenResponseHeaderBindingFilter csrfTokenFilter = new CsrfTokenResponseHeaderBindingFilter();
//        http.addFilterAfter(csrfTokenFilter, CsrfFilter.class);
//
//        http
//            .authorizeRequests()
//            .antMatchers("/resources/public/**").permitAll()
//            .antMatchers("/resources/img/**").permitAll()
//            .antMatchers("/resources/bower_components/**").permitAll()
//            .antMatchers(HttpMethod.POST, "/user").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin()
//            .defaultSuccessUrl("/resources/calories-tracker.html")
//            .loginProcessingUrl("/authenticate")
//            .usernameParameter("username")
//            .passwordParameter("password")
//            .successHandler(new AjaxAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler()))
//            .loginPage("/resources/public/login.html")
//            .and()
//            .httpBasic()
//            .and()
//            .logout()
//            .logoutUrl("/logout")
//            .logoutSuccessUrl("/resources/public/login.html")
//            .permitAll();
//
//        if ("true".equals(System.getProperty("httpsOnly"))) {
//            LOGGER.info("launching the application in HTTPS-only mode");
//            http.requiresChannel().anyRequest().requiresSecure();
//        }
//    }
}
