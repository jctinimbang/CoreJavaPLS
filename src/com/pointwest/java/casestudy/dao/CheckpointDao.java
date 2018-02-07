package com.pointwest.java.casestudy.dao;

import java.sql.SQLException;

import com.pointwest.java.casestudy.bean.Employee;
import com.pointwest.java.casestudy.exception.DatabaseException;
import com.pointwest.java.casestudy.util.Constants;

public class CheckpointDao extends BaseDao {
	public Employee loginPLS(String username, String password) throws DatabaseException {
		myLogger.info("Start");
		Employee employee = null;

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(DatabaseQueries.QUERY_LOGIN_USERNAME_PASSWORD);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employee = new Employee();
				employee.setFirstName(resultSet.getString("first_name"));
				employee.setLastName(resultSet.getString("last_name"));
				employee.setEmpID(resultSet.getInt("emp_id"));
				employee.setRole(resultSet.getString("role"));
			}

		} catch (SQLException e) {
			myLogger.error(e.getClass() + ": " + e.getMessage());
			throw new DatabaseException(Constants.ERROR_RETRIEVE_DATABASE);
		} finally {
			closeResultSet(resultSet);
			closePreparetdStatement(preparedStatement);
			closeConnection(connection);

		}
		
		myLogger.info("End");
		return employee;
	}
}
