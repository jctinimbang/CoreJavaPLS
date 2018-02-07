package com.pointwest.java.casestudy.ui;

import java.util.Map;

import com.pointwest.java.casestudy.bean.Employee;
import com.pointwest.java.casestudy.exception.DatabaseException;
import com.pointwest.java.casestudy.exception.LoginException;
import com.pointwest.java.casestudy.manager.LoginManager;
import com.pointwest.java.casestudy.util.Constants;

public class LoginPageUI extends UIHelper {
	private final String STRING_USERNAME = "Username";
	private final String STRING_PASSWORD = "Password";
	private final String MEESAGE_MAX_ATTEMPTS = "You have exceeded maximum attempt to login!";
	private final String [] ARRAY_STRING_LOGIN_FIELDS = { STRING_USERNAME, STRING_PASSWORD };
	
	@Override
	public void displayHeaderUI() {
		displayPageName(Constants.STRING_LOGIN);
		displayHeader(Constants.STRING_LOGIN);
	}

	public Employee displayLoginPage() {
		myLogger.info("Start");
		Map<String, String> inputs = null;
		LoginManager loginManager = new LoginManager();
		Employee employee = null;
		boolean isLoginSuccess = false;
		boolean isDataBaseError = false;
		for (int i = 0; i < 3; i++) {
			try {
				inputs = getInputFields(ARRAY_STRING_LOGIN_FIELDS);
				employee = loginManager.manageLogin(inputs.get(STRING_USERNAME),
						inputs.get(STRING_PASSWORD));
				isLoginSuccess = true;
				break;
			} catch (LoginException e) {
				System.out.println(e.getErrorMessage());
			} catch (DatabaseException e) {
				System.out.println(e.getErrorMessage());
				isDataBaseError = true;
				break;
			}
		}

		if (isLoginSuccess == false && isDataBaseError == false) {
			System.out.println(MEESAGE_MAX_ATTEMPTS);
		}
		myLogger.info("End");
		return employee;
	}

}