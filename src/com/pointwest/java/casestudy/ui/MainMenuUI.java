package com.pointwest.java.casestudy.ui;

import com.pointwest.java.casestudy.bean.Employee;
import com.pointwest.java.casestudy.util.Constants;

public class MainMenuUI extends UIHelper {
	private final String[] ARRAY_STRING_MAIN_MENU_OPTIONS = { Constants.STRING_SEARCH, Constants.STRING_VIEW_SEATPLAN,
			Constants.STRING_LOGOUT };
	@Override
	public void displayHeaderUI() {
		myLogger.info("Start");
		displayPageName(Constants.STRING_HOME);
		myLogger.info("End");
	}
	public String displayMainMenu(Employee employee) {
		myLogger.info("Start");
		String option = "";
		displayHeader("Welcome " + employee.getFullName() + "[" + employee.getRole() + "]!");
		System.out.println(Constants.STRING_MENU + ":");
		displayOptionMenuTemplate(ARRAY_STRING_MAIN_MENU_OPTIONS);
		option = getInputOption(ARRAY_STRING_MAIN_MENU_OPTIONS);
		myLogger.info("End");
		return option;
	}
	public void displayLogout() {
		System.out.println(buildRepeatedString("-", 25) + "L O G O U T" + buildRepeatedString("-", 25));
	}


}
