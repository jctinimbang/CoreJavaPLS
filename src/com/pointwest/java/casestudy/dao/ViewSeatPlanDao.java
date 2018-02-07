package com.pointwest.java.casestudy.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pointwest.java.casestudy.bean.Employee;
import com.pointwest.java.casestudy.bean.Seat;
import com.pointwest.java.casestudy.exception.DatabaseException;
import com.pointwest.java.casestudy.util.Constants;

public class ViewSeatPlanDao extends BaseDao {
	public List<Seat> retrieveAvailableSeatPlan() throws DatabaseException {
		List<Seat> availableSeatPlans;
		Seat seat;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(DatabaseQueries.QUERY_VIEW_AVALABLE_SEATPLAN);
			resultSet = preparedStatement.executeQuery();
			availableSeatPlans = new ArrayList<Seat>();
			while (resultSet.next()) {
				seat = new Seat();
				seat.setBuildingName(resultSet.getString("bldg_name"));
				seat.setBuildingID(resultSet.getString("bldg_id"));
				seat.setFloor(resultSet.getInt("floor_number"));
				seat.setQuadrant(resultSet.getString("quadrants"));
				availableSeatPlans.add(seat);
			}

		} catch (SQLException e) {
			myLogger.error("ERROR - " + e.getClass() + ": " + e.getMessage());
			throw new DatabaseException(Constants.ERROR_RETRIEVE_DATABASE);
		} finally {
			closeResultSet(resultSet);
			closePreparetdStatement(preparedStatement);
			closeConnection(connection);

		}

		return availableSeatPlans;
	}

	public List<Employee> getSeatPlanMap(String bldgName, String floorNumber, String quadrant)
			throws DatabaseException {
		List<Employee> seatPlan;
		Employee employee;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(DatabaseQueries.QUERY_VIEW_SEAT_PLAN);
			preparedStatement.setString(1, bldgName);
			preparedStatement.setString(2, floorNumber);
			preparedStatement.setString(3, "%" + quadrant + "%");
			resultSet = preparedStatement.executeQuery();
			seatPlan = new ArrayList<Employee>();
			while (resultSet.next()) {
				employee = new Employee();
				employee.setEmpID(resultSet.getInt("emp_id"));
				employee.setFirstName(resultSet.getString("first_name"));
				employee.setLastName(resultSet.getString("last_name"));
				employee.getSeat().setBuildingID(resultSet.getString("bldg_id"));
				employee.getSeat().setBuildingAddress(resultSet.getString("bldg_address"));
				employee.getSeat().setFloor(resultSet.getInt("floor_number"));
				employee.getSeat().setQuadrant(resultSet.getString("quadrant"));
				employee.getSeat().setRow(resultSet.getInt("row_number"));
				employee.getSeat().setColumn(resultSet.getInt("column_number"));
				employee.getSeat().setLocalNumber(resultSet.getInt("local_number"));
				seatPlan.add(employee);
			}

		} catch (SQLException e) {
			myLogger.error(e.getClass() + ": " + e.getMessage());
			throw new DatabaseException(Constants.ERROR_RETRIEVE_DATABASE);
		} finally {
			closeResultSet(resultSet);
			closePreparetdStatement(preparedStatement);
			closeConnection(connection);
		}

		return seatPlan;
	}
}
