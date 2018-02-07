package com.pointwest.java.casestudy.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.pointwest.java.casestudy.bean.Employee;
import com.pointwest.java.casestudy.bean.Seat;
import com.pointwest.java.casestudy.dao.ViewSeatPlanDao;
import com.pointwest.java.casestudy.exception.DatabaseException;
import com.pointwest.java.casestudy.util.Constants;

public class ViewSeatPlanManager {
	Logger myLogger = Logger.getLogger(this.getClass());

	public List<Seat> manageRetrieveAvailableSeatPlan() throws DatabaseException {
		myLogger.info("Start");
		List<Seat> availableSeatPlans;
		ViewSeatPlanDao viewSeatPlanDao = new ViewSeatPlanDao();
		myLogger.info("Fetch Data from Database");
		availableSeatPlans = viewSeatPlanDao.retrieveAvailableSeatPlan();
		myLogger.info("Total no of Result: " + availableSeatPlans.size());
		myLogger.info("End");
		return availableSeatPlans;
	}

	public boolean manageCheckInputsMatchSeatPlan(List<Seat> availableSeatPlans, Map<String, String> inputs) {
		myLogger.info("Start");
		boolean isMatch = false;
		for (Seat SeatPlan : availableSeatPlans) {
			isMatch = true;
			for (String key : inputs.keySet()) {
				switch (key) {
				case Constants.STRING_BLDG_ID:
					if (!(SeatPlan.getBuildingID().equalsIgnoreCase(inputs.get(key)))) {
						isMatch = false;
					}
					break;

				case Constants.STRING_FLOOR_NUMBER:
					if (!(inputs.get(key).matches("[0-9]+"))
							|| !(SeatPlan.getFloor() == Integer.parseInt(inputs.get(key)))) {
						isMatch = false;
					}
					break;
				case Constants.STRING_QUADRANT:
					if (!(SeatPlan.getQuadrant().contains(inputs.get(key).toUpperCase()))) {
						isMatch = false;
					}
					break;
				}
				if (isMatch == false) {
					break;
				}

			}
			if (isMatch == true) {
				break;
			}
		}

		myLogger.info("End");
		return isMatch;
	}

	public List<Employee> manageRetrieveEmployeeSeats(Map<String, String> inputs, boolean doContinueDao)
			throws DatabaseException {
		myLogger.info("Start");
		List<Employee> employees = null;
		if (doContinueDao == true) {
			ViewSeatPlanDao viewSeatPlanDao = new ViewSeatPlanDao();
			if (inputs.get(Constants.STRING_QUADRANT) == null) {
				inputs.put(Constants.STRING_QUADRANT, "");
			}
			myLogger.info("Fetch Data from Database");
			employees = viewSeatPlanDao.getSeatPlanMap(inputs.get(Constants.STRING_BLDG_ID),
					inputs.get(Constants.STRING_FLOOR_NUMBER), inputs.get(Constants.STRING_QUADRANT));
			myLogger.info("Total no of Result: " + employees.size());

		}
		myLogger.info("End");
		return employees;
	}

	public Map<String, Employee> manageEmployeesSeatPlan(List<Employee> employees) throws DatabaseException {
		myLogger.info("Start");
		Map<String, Employee> seatPlan = new HashMap<String, Employee>();
		String seatLocation = "";
		for (Employee employee : employees) {
			seatLocation = String.valueOf(employee.getSeat().getSeatLocation());
			seatPlan.put(seatLocation, employee);

		}
		myLogger.info("End");
		return seatPlan;
	}

	public Map<String, Employee> manageEmployeesSeatPlan(List<Employee> employees, Employee markEmployee)
			throws DatabaseException {
		myLogger.info("Start");
		Map<String, Employee> seatPlan = new HashMap<String, Employee>();
		String seatLocation = "";
		for (Employee employee : employees) {
			seatLocation = String.valueOf(employee.getSeat().getSeatLocation());
			if ((markEmployee.getEmpID() == employee.getEmpID())
					&& markEmployee.getSeat().getSeatLocation().equals(employee.getSeat().getSeatLocation())) {
				employee.setFirstName("**" + employee.getFirstName());
				employee.setLastName(employee.getLastName() + "**");
			}
			seatPlan.put(seatLocation, employee);

		}
		myLogger.info("End");
		return seatPlan;
	}

	public List<Employee> manageRetrieveEmployeeSeatsByReferenceEmployee(Employee employee) throws DatabaseException {
		myLogger.info("Start");
		List<Employee> employees = null;

		ViewSeatPlanDao viewSeatPlanDao = new ViewSeatPlanDao();
		myLogger.info("Fetch Data from Database");
		employees = viewSeatPlanDao.getSeatPlanMap(employee.getSeat().getBuildingID(),
				String.valueOf(employee.getSeat().getFloor()), String.valueOf(employee.getSeat().getQuadrant()));
		myLogger.info("Total no of Result: " + employees.size());
		myLogger.info("End");
		return employees;
	}
}
