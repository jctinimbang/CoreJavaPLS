package com.pointwest.java.casestudy.main;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.pointwest.java.casestudy.bean.Employee;
import com.pointwest.java.casestudy.ui.LoginPageUI;
import com.pointwest.java.casestudy.ui.MainMenuUI;
import com.pointwest.java.casestudy.ui.SearchPageUI;
import com.pointwest.java.casestudy.ui.ViewSeatPlanPageUI;
import com.pointwest.java.casestudy.ui.WelcomePageUI;
import com.pointwest.java.casestudy.util.Constants;

public class PLSMain {
	static Logger myLogger = Logger.getLogger(PLSMain.class);
	public static void main(String[] args) {
		String option;
		Employee employee;
		myLogger.info("Start");
		do {
			option = doWelcome();

			if (option != Constants.STRING_EXIT) {

				employee = doLogin();

				if (employee == null) {
					option = Constants.STRING_EXIT;
					break;
				}
				doMainMenu(employee);
			}
		} while (option != Constants.STRING_EXIT);
		doExit();
		myLogger.info("End");
		myLogger.info("System Terminated");
	}

	//Initialize Welcome Page
	private static String doWelcome() {
		myLogger.info("Start");
		WelcomePageUI welcomePageUI = new WelcomePageUI();
		welcomePageUI.displayHeaderUI();
		String option = welcomePageUI.displayWelcomePage();
		myLogger.info("Choice: " + option);
		myLogger.info("End");
		return option;
	}
	
	//Initialize Login Page
	private static Employee doLogin() {
		myLogger.info("Start");
		LoginPageUI loginPageUI = new LoginPageUI();
		Employee employee;

		loginPageUI.displayHeaderUI();
		employee = loginPageUI.displayLoginPage();
		myLogger.info("End");
		return employee;
	}

	//Initialize Main Menu Page
	private static void doMainMenu(Employee employee) {
		myLogger.info("Start");
		String selectedMainMenuOption;
		MainMenuUI mainMenuUI = new MainMenuUI();
		do {
			selectedMainMenuOption = "";
			mainMenuUI.displayHeaderUI();
			selectedMainMenuOption = mainMenuUI.displayMainMenu(employee);
			myLogger.info("Choice: " + selectedMainMenuOption);
			switch (selectedMainMenuOption) {
			case Constants.STRING_SEARCH:
				doViewSearchEmployeePage();
				break;

			case Constants.STRING_VIEW_SEATPLAN:
				doViewSeatPlanPage();
				break;

			case Constants.STRING_LOGOUT:
				mainMenuUI.displayLogout();
				break;
			}
		} while (selectedMainMenuOption != Constants.STRING_LOGOUT);
		myLogger.info("End");
	}

	//Go To Search Employee Main Page
	private static void doViewSearchEmployeePage() {
		myLogger.info("Start");
		SearchPageUI searchPageUI = new SearchPageUI();
		String option;
		do {
			option = "";
			searchPageUI.displayHeaderUI();
			option = searchPageUI.displaySearchPage();
			myLogger.info("Choice: " + option);
			if (option != Constants.STRING_HOME) {
				doViewSearchEmployee(option);
				option = searchPageUI.displayActionOption();

			}
		} while (option != Constants.STRING_HOME);
		myLogger.info("End");
	}

	//Search Employee by selected choice
	private static List<Employee> doViewSearchEmployee(String option) {
		myLogger.info("Start");
		List<Employee> employeeList = null;
		SearchPageUI searchPageUI = new SearchPageUI();
		switch (option) {
		case Constants.STRING_SEARCH_BY_EMPLOYEE_ID:
			employeeList = searchPageUI.searchByEmployeeID();
	
			break;

		case Constants.STRING_SEARCH_BY_EMPLOYEE_NAME:
			employeeList = searchPageUI.searchByEmployeeName();
			
			break;

		case Constants.STRING_SEARCH_BY_EMPLOYEE_PROJECT:
			employeeList = searchPageUI.searchByEmployeeProject();
			
			break;
		}
		searchPageUI.displaySearchResult(employeeList);
		myLogger.info("End");
		return employeeList;
	}

	//Go To View Seat Plan Main Page
	private static void doViewSeatPlanPage() {
		myLogger.info("Start");
		String option;
		ViewSeatPlanPageUI viewSeatPlanPageUI = new ViewSeatPlanPageUI();
		do {
			option = "";
			viewSeatPlanPageUI.displayHeaderUI();
			option = viewSeatPlanPageUI.displayViewSeatPlanPage();
			myLogger.info("Choice: " + option);
			if (option != Constants.STRING_HOME) {
				doDisplaySeatPlan(option);
				option = viewSeatPlanPageUI.displayActionOption();
			}
		} while (option != Constants.STRING_HOME);
		myLogger.info("End");
	}

	//Display Seat Plan
	private static void doDisplaySeatPlan(String option) {
		myLogger.info("Start");
		Map<String, Employee> seatPlan = null;
		List<Employee> employeeList = null;
		ViewSeatPlanPageUI viewSeatPlanPageUI = new ViewSeatPlanPageUI();

		switch (option) {
		case Constants.STRING_VIEW_BY_LOCATION_FLOOR_LEVEL:
			seatPlan = viewSeatPlanPageUI.viewByLocationFloorLevel();
			viewSeatPlanPageUI.displayFloorSeatPlan(seatPlan);
			break;
		case Constants.STRING_VIEW_BY_QUADRANT:
			seatPlan = viewSeatPlanPageUI.viewByQuadrant();
			viewSeatPlanPageUI.displayQuadrantSeatPlan(seatPlan);
			break;
		case Constants.STRING_VIEW_BY_EMPLOYEE:
			SearchPageUI searchPageUI = new SearchPageUI();
			viewSeatPlanPageUI.displayViewByEmployeeHeader();
			option = searchPageUI.displaySearchPage();
			employeeList = doViewSearchEmployee(option);
			seatPlan = viewSeatPlanPageUI.viewSeatPlanByEmployee(employeeList);
			viewSeatPlanPageUI.displayFloorSeatPlan(seatPlan);
			break;
		}
		myLogger.info("End");
	}
	//Exit System
	private static void doExit() {
		WelcomePageUI welcomePageUI = new WelcomePageUI();
		welcomePageUI.diplayExitPage();
	}
}
