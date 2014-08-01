package ua.bionic.adonin.messenger.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.bionic.adonin.messenger.managers.ConfigurationManager;

public class HomeCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page;
		page = ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.MAIN_PAGE);
		return page;
	}

}
