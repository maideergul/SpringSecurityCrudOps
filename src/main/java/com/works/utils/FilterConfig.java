package com.works.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig implements Filter {
	
	final Util util;
	public FilterConfig( Util util ) {
		this.util = util;
	}	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		if ( !url.contains("h2-console") ) {
			util.userInfo(url);
		}
		chain.doFilter(request, response);		
	}	

}