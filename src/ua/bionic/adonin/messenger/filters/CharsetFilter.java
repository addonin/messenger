package ua.bionic.adonin.messenger.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharsetFilter implements Filter {

	private FilterConfig filterConfig;
	private String requestEncoding;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		requestEncoding = filterConfig.getInitParameter("requestEncoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(requestEncoding);
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		this.filterConfig = null;
	}

}