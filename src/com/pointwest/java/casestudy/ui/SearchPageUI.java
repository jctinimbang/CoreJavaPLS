package com.pointwest.java.casestudy.ui;

import java.util.List;

import com.pointwest.java.casestudy.bean.Employee;
import com.pointwest.java.casestudy.exception.DatabaseException;
import com.pointwest.java.casestudy.manager.SearchEmployeeManager;
import com.pointwest.java.casestudy.util.Constants;

public class SearchPageUI extends UIHelper {
	private final String STRING_SEARCH_AGAIN = Constants.STRING_SEARCH + " " + Constants.STRING_AGAIN;
	private final String[] ARRAY_SEARCH_ACTION_OPTION = { STRING_SEARCH_AGAIN, Constants.STRING_HOME };
	private final String STRING_SEARCH_EMPLOYEE = "Search Employee/s";
	private final String STRING_SEARCH_RESULT = "Search Result";
	private final String FIELD_ENTER_EMPLOYEE_ID = "Employee ID";
	private final String FIELD_ENTER_EMPLOYEE_NAME = "Employee Name";
	private final String FIELD_ENTER_EMPLOYEE_PROJECT = "Employee Project";
	private final String STRING_FIRST_NAME = "Firstname";
	private final String STRING_LAST_NAME = "Lastname";
	private final String STRING_SEAT = "Seat";
	private final String STRING_PROJECTS = "Project(s)";

	
	private final String[] ARRAY_STRING_SEARCH_MENU_OPTIONS = { Constants.STRING_SEARCH_BY_EMPLOYEE_ID,
			Constants.STRING_SEARCH_BY_EMPLOYEE_NAME, Constants.STRING_SEARCH_BY_EMPLOYEE_PROJECT };
	
	private final String[] ARRAY_STRING_SEARCH_RESULT_COLUMN_HEADER = { STRING_NUMBER_SIGN,
			Constants.STRING_EMPLOYEE_ID, STRING_FIRST_NAME, STRING_LAST_NAME,
			STRING_SEAT, Constants.STRING_LOCAL, Constants.STRING_SHIFT, STRING_PROJECTS };
	
	@Override
	public void displayHeaderUI() {
		myLogger.info("Start");
		displayPageName(Constants.STRING_SEARCH.toUpperCase());
		displayHeader(STRING_SEARCH_EMPLOYEE);
		myLogger.info("End");
	}

	public String displaySearchPage() {
		myLogger.info("Start");
		String option = "";

		System.out.println(Constants.STRING_MENU + ":");
		displayOptionMenuTemplate(ARRAY_STRING_SEARCH_MENU_OPTIONS);
		option = getInputOption(ARRAY_STRING_SEARCH_MENU_OPTIONS);
		myLogger.info("End");
		return option;
	}

	public List<Employee> searchByEmployeeID() {
		myLogger.info("Start");
		List<Employee> employeeList = null;
		String input = "";
		SearchEmployeeManager searchEmployeeManager = new SearchEmployeeManager();

		displayPageName(Constants.STRING_SEARCH + " - " + Constants.STRING_SEARCH_BY_EMPLOYEE_ID);
		input = getInputNumberField(FIELD_ENTER_EMPLOYEE_ID);
		try {
			employeeList = searchEmployeeManager.manageSearchEmployeeByID(input);
		} catch (DatabaseException e) {
			System.out.println(e.getErrorMessage());
		}
		myLogger.info("End");
		return employeeList;
	}

	public List<Employee> searchByEmployeeName() {
		myLogger.info("Start");
		List<Employee> employeeList = null;
		String input = "";
		SearchEmployeeManager searchEmployeeManager = new SearchEmployeeManager();

		displayPageName(Constants.STRING_SEARCH + " - " + Constants.STRING_SEARCH_BY_EMPLOYEE_NAME);
		input = getInputAlphabetField(FIELD_ENTER_EMPLOYEE_NAME);
		try {
			employeeList = searchEmployeeManager.manageSearchEmployeeByName(input);
		} catch (DatabaseException e) {
			System.out.println(e.getErrorMessage());
		}
		myLogger.info("End");
		return employeeList;
	}

	public List<Employee> searchByEmployeeProject() {
		myLogger.info("Start");
		List<Employee> employeeList = null;
		String input = "";
		SearchEmployeeManager searchEmployeeManager = new SearchEmployeeManager();

		displayPageName(Constants.STRING_SEARCH + " - " + Constants.STRING_SEARCH_BY_EMPLOYEE_PROJECT);
		input = getInputAlphabetField(FIELD_ENTER_EMPLOYEE_PROJECT);
		try {
			employeeList = searchEmployeeManager.manageSearchEmployeeByProject(input);
		} catch (DatabaseException e) {
			System.out.println(e.getErrorMessage());
		}
		myLogger.info("End");
		return employeeList;
	}

	public void displaySearchResult(List<Employee> employeeList) {
		if (employeeList != null) {
			myLogger.info("Start");
			displayPageName(STRING_SEARCH_RESULT.toUpperCase() + " - (" + employeeList.size() + ")");
			displayColumnHeaderString(ARRAY_STRING_SEARCH_RESULT_COLUMN_HEADER);

			if (employeeList.size() == 0) {
				System.out.println(buildRepeatedString(" ", 25) + "No Result Found");
			} else {
				displayEmployeeInfoList(employeeList);
			}

			System.out.println(buildRepeatedString("-", 35) + "end of result" + buildRepeatedString("-", 35));
			myLogger.info("End");
		}
	}

	private void displayColumnHeaderString(String[] arrayColumnNames) {
		myLogger.info("Start");
		String tempString = "";
		for (String columnName : arrayColumnNames) {
			int padding = columnName.length() + 4;
			tempString += String.format("%-" + padding + "s", columnName.toUpperCase()) + "|";
		}
		System.out.println(buildRepeatedString("-", tempString.length()));
		System.out.println(tempString);
		System.out.println(buildRepeatedString("-", tempString.length()));
		myLogger.info("End");
	}
	
	private void displayEmployeeInfoList(List<Employee> employeeList) {
		myLogger.info("Start");
		String rowOutput = "";
		String tempLocal = "";
		for (int i = 0; i < employeeList.size(); i++) {
			if (employeeList.get(i).getSeat().getLocalNumber() == 0) {
				tempLocal = "NONE";
			} else {
				tempLocal = String.valueOf(employeeList.get(i).getSeat().getLocalNumber());
			}

			rowOutput = String.format("%-5s", "[" + (i + 1) + "]")
					+ String.format("%-7s", employeeList.get(i).getEmpID()) + "|"
					+ String.format("%-18s", employeeList.get(i).getFirstName()) + "|"
					+ String.format("%-18s", employeeList.get(i).getLastName()) + "|"
					+ String.format("%-10s", employeeList.get(i).getSeat().getSeatLocation()) + "|"
					+ String.format("%-5s", tempLocal) + "|" + String.format("%-4s", employeeList.get(i).getShift())
					+ "|" + String.format("%-25s", employeeList.get(i).getProject().getProjectNames()) + "|";
			System.out.println(rowOutput);

		}
		myLogger.info("End");
	}

	public String displayActionOption() {
		myLogger.info("Start");
		String action = "";
		displayActionOption(ARRAY_SEARCH_ACTION_OPTION);
		action = getInputOption(ARRAY_SEARCH_ACTION_OPTION);
		myLogger.info("End");
		return action;
	}
}
