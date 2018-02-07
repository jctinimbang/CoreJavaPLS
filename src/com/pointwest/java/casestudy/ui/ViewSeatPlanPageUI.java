package com.pointwest.java.casestudy.ui;

import java.util.List;
import java.util.Map;

import com.pointwest.java.casestudy.bean.Employee;
import com.pointwest.java.casestudy.bean.Seat;
import com.pointwest.java.casestudy.exception.DatabaseException;
import com.pointwest.java.casestudy.manager.ViewSeatPlanManager;
import com.pointwest.java.casestudy.util.Constants;

public class ViewSeatPlanPageUI extends UIHelper {
	private final int MAX_ROW_SEATPLAN = 3;
	private static final int MAX_COLUMN_SEATPLAN = 3;
	private final String STRING_VIEW_SEATPLAN_AGAIN = Constants.STRING_VIEW_SEATPLAN + " Again";
	private final String STRING_SEAT_LOCATION = "Seat Location";
	private final String STRING_FULL_NAME = "FullName";
	
	private final String[] ARRAY_VIEW_ACTION_OPTION = { STRING_VIEW_SEATPLAN_AGAIN, Constants.STRING_HOME };
	private final String[] ARRAY_STRING_VIEW_SEATPLAN_BY_LOCATION_FIELDS = { Constants.STRING_BLDG_ID,
			Constants.STRING_FLOOR_NUMBER };
	private final String[] ARRAY_STRING_VIEW_SEATPLAN_BY_LOCATION_FORMAT_FIELDS = { Constants.STRING_ALPAHBHET_ONLY,
			Constants.STRING_NUMBER_ONLY };
	private final String[] ARRAY_STRING_VIEW_SEATPLAN_BY_QUADRANTS_FIELDS = { Constants.STRING_BLDG_ID,
			Constants.STRING_FLOOR_NUMBER, Constants.STRING_QUADRANT };
	private final String[] ARRAY_STRING_VIEW_SEATPLAN_BY_QUADRANTS_FORMAT_FIELDS = { Constants.STRING_ALPAHBHET_ONLY,
			Constants.STRING_NUMBER_ONLY, Constants.STRING_ALPAHBHET_ONLY};
	private final String[] ARRAY_STRING_VIEW_SEATPLAN_MENU_OPTION = { Constants.STRING_VIEW_BY_LOCATION_FLOOR_LEVEL,
			Constants.STRING_VIEW_BY_QUADRANT, Constants.STRING_VIEW_BY_EMPLOYEE };
	private final String[] ARRAY_STRING_AVAILABLE_SEATPLAN_COLUMN_HEADER = { Constants.STRING_BLDG_ID,
			Constants.STRING_BLDG_NAME, Constants.STRING_FLOOR_NUMBER, Constants.STRING_QUADRANT };
	private final String[] ARRAY_STRING_SEATPLAN_FORMAT = { STRING_SEAT_LOCATION, STRING_FULL_NAME,
			Constants.STRING_LOCAL };

	@Override
	public void displayHeaderUI() {
		myLogger.info("Start");
		displayPageName(Constants.STRING_VIEW_SEATPLAN.toUpperCase());
		displayHeader(Constants.STRING_VIEW_SEATPLAN.toUpperCase());
		myLogger.info("End");
	}

	public String displayViewSeatPlanPage() {
		myLogger.info("Start");
		String seatPlanMenuoption = "";
		System.out.println(Constants.STRING_MENU + ":");
		displayOptionMenuTemplate(ARRAY_STRING_VIEW_SEATPLAN_MENU_OPTION);
		seatPlanMenuoption = getInputOption(ARRAY_STRING_VIEW_SEATPLAN_MENU_OPTION);
		myLogger.info("End");
		return seatPlanMenuoption;
	}

	public String displayActionOption() {
		myLogger.info("Start");
		String action = "";
		displayActionOption(ARRAY_VIEW_ACTION_OPTION);
		action = getInputOption(ARRAY_VIEW_ACTION_OPTION);
		myLogger.info("End");
		return action;
	}

	public Map<String, Employee> viewByLocationFloorLevel() {
		myLogger.info("Start");
		ViewSeatPlanManager viewSeatPlanManager = new ViewSeatPlanManager();
		Map<String, Employee> seatPlan = null;
		List<Employee> employeeList;
		List<Seat> availableSeatPlans;
		Map<String, String> inputs;
		boolean IsMatch = false;

		availableSeatPlans = displayAvailableSeatPlan();
		if (availableSeatPlans != null) {
			displayPageName(Constants.STRING_VIEW_SEATPLAN + " - " + Constants.STRING_VIEW_BY_LOCATION_FLOOR_LEVEL);
			inputs = getInputFields(ARRAY_STRING_VIEW_SEATPLAN_BY_LOCATION_FIELDS,
					ARRAY_STRING_VIEW_SEATPLAN_BY_LOCATION_FORMAT_FIELDS);
			IsMatch = viewSeatPlanManager.manageCheckInputsMatchSeatPlan(availableSeatPlans, inputs);
			try {
				employeeList = viewSeatPlanManager.manageRetrieveEmployeeSeats(inputs, IsMatch);
				if (!(employeeList == null)) {
					seatPlan = viewSeatPlanManager.manageEmployeesSeatPlan(employeeList);
				}

			} catch (DatabaseException e) {
				System.out.println(e.getErrorMessage());
			}
		}
		myLogger.info("End");
		return seatPlan;
	}

	public Map<String, Employee> viewByQuadrant() {
		myLogger.info("Start");
		ViewSeatPlanManager viewSeatPlanManager = new ViewSeatPlanManager();
		Map<String, Employee> seatPlan = null;
		List<Employee> employeeList;
		List<Seat> availableSeatPlans;
		Map<String, String> inputs;
		boolean IsMatch = false;

		availableSeatPlans = displayAvailableSeatPlan();
		if (availableSeatPlans != null) {
			displayPageName(Constants.STRING_VIEW_SEATPLAN + " - " + Constants.STRING_VIEW_BY_QUADRANT);
			inputs = getInputFields(ARRAY_STRING_VIEW_SEATPLAN_BY_QUADRANTS_FIELDS,
					ARRAY_STRING_VIEW_SEATPLAN_BY_QUADRANTS_FORMAT_FIELDS);
			IsMatch = viewSeatPlanManager.manageCheckInputsMatchSeatPlan(availableSeatPlans, inputs);
			try {
				employeeList = viewSeatPlanManager.manageRetrieveEmployeeSeats(inputs, IsMatch);
				if (!(employeeList == null)) {
					seatPlan = viewSeatPlanManager.manageEmployeesSeatPlan(employeeList);
				}

			} catch (DatabaseException e) {
				System.out.println(e.getErrorMessage());
			}
		}
		myLogger.info("End");
		return seatPlan;
	}

	private List<Seat> displayAvailableSeatPlan() {
		myLogger.info("Start");
		ViewSeatPlanManager viewSeatPlanManager = new ViewSeatPlanManager();
		List<Seat> availableSeatPlans = null;
		String tempRowValue = "";
		try {
			availableSeatPlans = viewSeatPlanManager.manageRetrieveAvailableSeatPlan();
			displayPageName("Available Seat Plans");
			displayColumnHeaderString(ARRAY_STRING_AVAILABLE_SEATPLAN_COLUMN_HEADER);
			for (Seat seat : availableSeatPlans) {
				tempRowValue = "";
				for (String column : ARRAY_STRING_AVAILABLE_SEATPLAN_COLUMN_HEADER) {
					switch (column) {
					case Constants.STRING_BLDG_ID:
						tempRowValue += String.format("%-7s", seat.getBuildingID());
						break;
					case Constants.STRING_BLDG_NAME:
						tempRowValue += String.format("%-30s", seat.getBuildingName());
						break;
					case Constants.STRING_FLOOR_NUMBER:
						tempRowValue += String.format("%-20s", seat.getFloor());
						break;
					case Constants.STRING_QUADRANT:
						tempRowValue += String.format("%-10s", seat.getQuadrant());
						break;

					}
					tempRowValue += "|";
				}
				System.out.println(tempRowValue);
			}

			System.out.println(buildRepeatedString("-", 22) + "end of result" + buildRepeatedString("-", 22));
		} catch (DatabaseException e) {
			System.out.println(e.getErrorMessage());
		}
		myLogger.info("End");
		return availableSeatPlans;
	}

	private void displayColumnHeaderString(String[] arrayColumnNames) {
		myLogger.info("Start");
		String tempString = "";
		for (String columnName : arrayColumnNames) {
			int padding = columnName.length() + 3;
			tempString += columnName.toUpperCase() + buildRepeatedString(" ", padding);
		}
		System.out.println(buildRepeatedString("-", tempString.length()));
		System.out.println(tempString);
		System.out.println(buildRepeatedString("-", tempString.length()));
		myLogger.info("End");
	}

	public Map<String, Employee> viewSeatPlanByEmployee(List<Employee> refEmployeeList) {
		myLogger.info("Start");
		Employee markEmployee;
		Map<String, Employee> seatPlan = null;
		List<Employee> employeeList;
		ViewSeatPlanManager viewSeatPlanManager = new ViewSeatPlanManager();

		if (refEmployeeList != null) {
			markEmployee = selectEmployeeFromList(refEmployeeList);
			if (!(markEmployee == null)) {
				displayPageName(Constants.STRING_VIEW_SEATPLAN + " - " + Constants.STRING_VIEW_BY_EMPLOYEE);
				try {
					employeeList = viewSeatPlanManager.manageRetrieveEmployeeSeatsByReferenceEmployee(markEmployee);
					seatPlan = viewSeatPlanManager.manageEmployeesSeatPlan(employeeList, markEmployee);
				} catch (DatabaseException e) {
					System.out.println(e.getErrorMessage());
				}
			}
		}
		myLogger.info("End");
		return seatPlan;
	}

	private Employee selectEmployeeFromList(List<Employee> employeeList) {
		myLogger.info("Start");
		Employee markEmployee;
		String optionNumber = "";
		boolean doLoop = false;

		if (!(employeeList.size() <= 0)) {
			do {
				System.out.print("Enter result no. of the employee you want to view: ");
				optionNumber = sc.nextLine();
				doLoop = getProcessInput(optionNumber, employeeList.size());
				if (doLoop == false) {
					System.out.println(
							"-> Invalid Input.Please input number ranging from 1 - " + employeeList.size() + " only.");
				}
			} while (!doLoop);
			markEmployee = employeeList.get(Integer.parseInt(optionNumber) - 1);
		} else {
			markEmployee = null;
		}
		myLogger.info("End");
		return markEmployee;
	}

	public void displayViewByEmployeeHeader() {
		displayPageName(Constants.STRING_VIEW_SEATPLAN + " - " + Constants.STRING_VIEW_BY_EMPLOYEE);
		System.out.println("--You need to search for an employee first--");
	}

	private void displayNoAvailableSeatPlan() {
		System.out.println(buildRepeatedString("-", 25) + "No Available Seat Plan" + buildRepeatedString("-", 25));
	}

	public void displayFloorSeatPlan(Map<String, Employee> seatPlan) {
		myLogger.info("Start");
		if (seatPlan == null) {
			displayNoAvailableSeatPlan();
			return;
		}
		String[] upperRow = { "A", "B" };
		String[] lowerRow = { "C", "D" };
		Map.Entry<String, Employee> entry = seatPlan.entrySet().iterator().next();
		Employee employee = entry.getValue();
		String bldgID = employee.getSeat().getBuildingID();
		int floorNumber = employee.getSeat().getFloor();
		String buildingAddress = employee.getSeat().getBuildingAddress();
		System.out.println("LOCATION: " + bldgID + "[" + buildingAddress + "],"
				+ Constants.STRING_FLOOR_NUMBER.toUpperCase() + ": " + floorNumber);
		System.out.println(buildRepeatedString("-", 75) + "|ENTRANCE|" + buildRepeatedString("-", 75));
		System.out.println(
				buildRepeatedString(" ", 25) + "**QUADRANT A**" + buildRepeatedString(" ", 65) + "**QUADRANT B**\n");

		displayQuadrantSeatPlan(seatPlan, upperRow, bldgID, floorNumber);

		System.out.println(buildRepeatedString("-", 75) + "|       |" + buildRepeatedString("-", 75));
		System.out.println(
				buildRepeatedString(" ", 25) + "**QUADRANT C**" + buildRepeatedString(" ", 65) + "**QUADRANT D**\n");

		displayQuadrantSeatPlan(seatPlan, lowerRow, bldgID, floorNumber);

		System.out.println(buildRepeatedString("-", 73) + "End of SeatPlan" + buildRepeatedString("-", 73));
		myLogger.info("End");
	}

	public void displayQuadrantSeatPlan(Map<String, Employee> seatPlan) {
		myLogger.info("Start");
		if (seatPlan == null) {
			displayNoAvailableSeatPlan();
			return;
		}
		Map.Entry<String, Employee> entry = seatPlan.entrySet().iterator().next();
		Employee employee = entry.getValue();
		String bldgID = employee.getSeat().getBuildingID();
		int floorNumber = employee.getSeat().getFloor();
		String buildingAddress = employee.getSeat().getBuildingAddress();
		String[] quadrant = { String.valueOf(employee.getSeat().getQuadrant()) };
		System.out.println(
				"LOCATION: " + bldgID + "[" + buildingAddress + "]," + Constants.STRING_FLOOR_NUMBER.toUpperCase()
						+ ": " + floorNumber + ", " + Constants.STRING_QUADRANT + ": " + quadrant[0]);
		System.out.println(buildRepeatedString("-", 85));
		System.out.println(buildRepeatedString(" ", 25) + "**QUADRANT " + quadrant[0].toUpperCase() + "**");

		displayQuadrantSeatPlan(seatPlan, quadrant, bldgID, floorNumber);

		System.out.println(buildRepeatedString("-", 35) + "End of SeatPlan" + buildRepeatedString("-", 35));
		myLogger.info("End");
	}

	public void displayQuadrantSeatPlan(Map<String, Employee> seatPlan, String[] quadrants, String bldgID,
			int floorNumber) {
		myLogger.info("Start");
		String tempString = "";
		String tempSeatLocation = "";
		for (int row = 1; row <= MAX_ROW_SEATPLAN; row++) {
			for (String indicator : ARRAY_STRING_SEATPLAN_FORMAT) {
				for (int i = 0; i < quadrants.length; i++) {
					for (int column = 1; column <= MAX_COLUMN_SEATPLAN; column++) {
						tempSeatLocation = bldgID.toUpperCase() + floorNumber + "F" + quadrants[i].toUpperCase() + row
								+ "-" + column;

						switch (indicator) {
						case STRING_SEAT_LOCATION:
							tempString = tempSeatLocation;
							break;

						case STRING_FULL_NAME:
							if (seatPlan.containsKey(tempSeatLocation)) {
								tempString = seatPlan.get(tempSeatLocation).getFullName();
							} else {
								tempString = "Not Available";
							}
							break;

						case Constants.STRING_LOCAL:
							if (seatPlan.containsKey(tempSeatLocation)) {
								String tempLocalNumber = String
										.valueOf(seatPlan.get(tempSeatLocation).getSeat().getLocalNumber());
								if (!("0".equals(tempLocalNumber))) {
									tempString = "loc." + tempLocalNumber;
									break;
								}

							}
							tempString = "";
							break;
						}

						System.out.print(String.format("%-25s", tempString));
					}
					System.out.print("\t | ");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
		}
		myLogger.info("End");
	}

}
