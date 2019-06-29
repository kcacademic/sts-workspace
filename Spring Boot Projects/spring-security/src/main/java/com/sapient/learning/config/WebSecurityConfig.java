package com.sapient.learning.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import com.sapient.learning.security.CustomAuthenticationProvider;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/home", "/login", "/h2-console/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.successHandler(authenticationSuccesshandler)
				.failureHandler(authenticationFailureHandler)
				.permitAll()
				.and()
			.logout()
				.logoutSuccessHandler(logoutSuccessHandler)
				.permitAll()
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.and()
			.rememberMe()
				.rememberMeServices(rememberMeServices())
				.tokenValiditySeconds(86400);
		
		http
			.csrf()
				.disable();
		
		http
			.headers()
				.frameOptions()
				.disable();	
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
    
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PersistentTokenBasedRememberMeServices rememberMeServices() {
		PersistentTokenBasedRememberMeServices rememberMeAuthenticationProvider = 
				new PersistentTokenBasedRememberMeServices(
						"myAppKey", userDetailsService, jdbcTokenRepository());
		return rememberMeAuthenticationProvider;
	}
	
	@Bean
	public JdbcTokenRepositoryImpl jdbcTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setCreateTableOnStartup(false);
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}
	
    @SuppressWarnings("deprecation")
	@Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
         User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();

    return new InMemoryUserDetailsManager(user);
    }
	
	@Autowired
	DataSource dataSource;

	@Autowired
	AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	AuthenticationSuccessHandler authenticationSuccesshandler;

	@Autowired
	LogoutSuccessHandler logoutSuccessHandler;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	CustomAuthenticationProvider authenticationProvider;
	
}