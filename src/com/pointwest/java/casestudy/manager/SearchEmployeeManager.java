package com.pointwest.java.casestudy.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.pointwest.java.casestudy.bean.Employee;
import com.pointwest.java.casestudy.dao.DatabaseQueries;
import com.pointwest.java.casestudy.dao.SearchEmployeeDao;
import com.pointwest.java.casestudy.exception.DatabaseException;

public class SearchEmployeeManager {
	Logger myLogger = Logger.getLogger(this.getClass());
	public List<Employee> manageSearchEmployeeByID(String empID) throws DatabaseException {
		myLogger.info("Start");
		List<Employee> employeeList;
		SearchEmployeeDao searchEmployeeDao = new SearchEmployeeDao();
		myLogger.info("Fetch Data from Database");
		employeeList = searchEmployeeDao.getEmployeeResult(DatabaseQueries.QUERY_SEARCH_BY_ID, empID);
		myLogger.info("Total no of Result: " + employeeList.size());
		myLogger.info("End");
		return employeeList;
	}
	
	public List<Employee> manageSearchEmployeeByName(String empName) throws DatabaseException {
		myLogger.info("Start");
		List<Employee> employeeList;
		SearchEmployeeDao searchEmployeeDao = new SearchEmployeeDao();
		myLogger.info("Fetch Data from Database");
		employeeList = searchEmployeeDao.getEmployeeResult(DatabaseQueries.QUERY_SEARCH_BY_NAME, empName);
		myLogger.info("Total no of Result: " + employeeList.size());
		myLogger.info("End");
		return employeeList;
	}
	
	public List<Employee> manageSearchEmployeeByProject(String empProj) throws DatabaseException {
		myLogger.info("Start");
		List<Employee> employeeList;
		SearchEmployeeDao searchEmployeeDao = new SearchEmployeeDao();
		myLogger.info("Fetch Data from Database");
		employeeList = searchEmployeeDao.getEmployeeResult(DatabaseQueries.QUERY_SEARCH_BY_PROJECT, empProj);
		myLogger.info("Total no of Result: " + employeeList.size());
		
		myLogger.info("End");
		return employeeList;
	}
	
	public String processEmployeeProjectNaming(String project ) {
		String newProjectNames;
		if (project != null) {
			newProjectNames = project.replaceAll(",Project Never Exist", "");
			newProjectNames = newProjectNames.replaceAll("Project Never Exist,", "");
			newProjectNames = newProjectNames.replaceAll("Project Never Exist", "");
		} else {
			newProjectNames = "";
		}
		return newProjectNames;
	}
}
