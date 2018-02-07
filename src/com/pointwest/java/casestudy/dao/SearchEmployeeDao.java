package com.pointwest.java.casestudy.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pointwest.java.casestudy.bean.Employee;
import com.pointwest.java.casestudy.exception.DatabaseException;
import com.pointwest.java.casestudy.manager.SearchEmployeeManager;
import com.pointwest.java.casestudy.util.Constants;

public class SearchEmployeeDao extends BaseDao {

	public List<Employee> getEmployeeResult(String query, String input) throws DatabaseException {
		List<Employee> employeeList = null;
		Employee employee = null;
		SearchEmployeeManager searchEmployeeManager = new SearchEmployeeManager();
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + input + "%");
			resultSet = preparedStatement.executeQuery();
			employeeList = new ArrayList<Employee>();
			while (resultSet.next()) {
				employee = new Employee();
				employee.setFirstName(resultSet.getString("first_name"));
				employee.setLastName(resultSet.getString("last_name"));
				employee.setEmpID(resultSet.getInt("emp_id"));
				employee.setShift(resultSet.getString("shift"));
				employee.getSeat().setBuildingID(resultSet.getString("bldg_id"));
				employee.getSeat().setFloor(resultSet.getInt("floor_number"));
				employee.getSeat().setQuadrant(resultSet.getString("quadrant"));
				employee.getSeat().setRow(resultSet.getInt("row_number"));
				employee.getSeat().setColumn(resultSet.getInt("column_number"));
				employee.getSeat().setLocalNumber(resultSet.getInt("local_number"));
				employee.getProject().setProjectNames(
						searchEmployeeManager.processEmployeeProjectNaming(resultSet.getString("projects")));
				;
				employeeList.add(employee);
			}

		} catch (SQLException e) {
			myLogger.error(e.getClass() + ": " + e.getMessage());
			throw new DatabaseException(Constants.ERROR_RETRIEVE_DATABASE);
		} finally {
			closeResultSet(resultSet);
			closePreparetdStatement(preparedStatement);
			closeConnection(connection);

		}

		return employeeList;
	}
}
