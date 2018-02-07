package com.pointwest.java.casestudy.ui;

import com.pointwest.java.casestudy.util.Constants;

public class WelcomePageUI extends UIHelper {
	private final String MESSAGE_WELCOME_PLS = "Welcome to People Locator System!";
	private final String STRING_WELCOME_PAGE = "Welcome";
	private final String[] ARRAY_STRING_WELCOME_PAGE_OPTIONS = { Constants.STRING_LOGIN, Constants.STRING_EXIT };
	
	@Override
	public void displayHeaderUI() {
		displayPageName(STRING_WELCOME_PAGE);
		displayHeader(MESSAGE_WELCOME_PLS);
	}
	
	public String displayWelcomePage() {
		myLogger.info("Start");
		displayOptionMenuTemplate(ARRAY_STRING_WELCOME_PAGE_OPTIONS);
		String option = getInputOption(ARRAY_STRING_WELCOME_PAGE_OPTIONS);
		myLogger.info("End");
		return option;

	}
	
	public void diplayExitPage() {
		myLogger.info("Start");
		System.out.println("Terminating the system. Thank you!");
		myLogger.info("End");
	}

	
}
