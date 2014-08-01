package ua.bionic.adonin.messenger.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.bionic.adonin.messenger.managers.ConfigurationManager;

public class SessionFilter implements Filter {

	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String page;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		
		
		if (session != null && !session.isNew()) {
			chain.doFilter(request, response);
		} else {			
			page = ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.LOGIN_PAGE);
			RequestDispatcher dispatcher = req.getRequestDispatcher(page);
	        dispatcher.forward(request, response);
		}
	}
	
	@Override
	public void destroy() {
		this.filterConfig = null;
	}

}
