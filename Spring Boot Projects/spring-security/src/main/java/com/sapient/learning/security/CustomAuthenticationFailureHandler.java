package com.sapient.learning.security;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

@Component("authenticationFailureHandler")
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private MessageSource messages;

	@Autowired
	private LocaleResolver localeResolver;

	@Override
	public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException exception) throws IOException, ServletException {	
		
		System.out.println("Setting message in request for authentication failure.");
		
		final Locale locale = localeResolver.resolveLocale(request);
		String errorMessage;
		if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
			errorMessage = messages.getMessage("auth.message.disabled", null, locale);
		} else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
			errorMessage = messages.getMessage("auth.message.expired", null, locale);
		} else if (exception.getMessage().equalsIgnoreCase("blocked")) {
			errorMessage = messages.getMessage("auth.message.blocked", null, locale);
		} else {
			errorMessage = messages.getMessage("message.badCredentials", null, locale);
		}
		
		request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
		
		redirectStrategy.sendRedirect(request, response, "/login?error");
	}
}
