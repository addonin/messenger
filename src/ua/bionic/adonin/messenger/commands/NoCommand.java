package ua.bionic.adonin.messenger.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.bionic.adonin.messenger.managers.ConfigurationManager;

public class NoCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.LOGIN_PAGE);	   
	}

}
