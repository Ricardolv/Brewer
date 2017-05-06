package com.richard.brewer.config.init;

import java.util.EnumSet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.SessionTrackingMode;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

public class SecurityInitalizer extends AbstractSecurityWebApplicationInitializer {
	
	
	
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		//configuracao que determina o tempo de acesso do usuario ao sistema 
//		servletContext.getSessionCookieConfig().setMaxAge(20);
		
		//configuracao para que  JSessionId nao passe na url e sim pelo cookier 
		servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
		
		FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("encodingFilter",
				new CharacterEncodingFilter());
		characterEncodingFilter.setInitParameter("encoding", "UTF-8");
		characterEncodingFilter.setInitParameter("forceEncoding", "true");
		characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
	}

}
