package com.pointwest.java.casestudy.manager;

import org.apache.log4j.Logger;

import com.pointwest.java.casestudy.bean.Employee;
import com.pointwest.java.casestudy.dao.CheckpointDao;
import com.pointwest.java.casestudy.exception.DatabaseException;
import com.pointwest.java.casestudy.exception.LoginException;

public class LoginManager {
	Logger myLogger = Logger.getLogger(this.getClass());
	public Employee manageLogin(String username, String password) throws DatabaseException, LoginException {
		myLogger.info("Start");
		CheckpointDao checkpointDao = new CheckpointDao();
		Employee employee = null;
		
		if(username.contains(".")) {
			employee = checkpointDao.loginPLS(username, password);
		} else {
			myLogger.info("Username '" + username + "' does not contain a period(.)character");
		}
		
		if (employee == null) {
			myLogger.info("invalid Username & Password");
			throw new LoginException();
		} else {
			myLogger.info("Login Success");
		}
		myLogger.info("End");
		return employee;
	}
}
