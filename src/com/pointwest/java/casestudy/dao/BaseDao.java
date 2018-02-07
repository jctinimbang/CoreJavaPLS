package com.pointwest.java.casestudy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.pointwest.java.casestudy.exception.DatabaseException;
import com.pointwest.java.casestudy.util.Constants;

public abstract class BaseDao {
	Logger myLogger = Logger.getLogger(this.getClass());
	private final String DB_URL = "jdbc:mysql://172.26.83.193:3306/plsdb";
	private final String DB_USERNAME = "newuser";
	private final String DB_PASSWORD = "password123";
	private final String DB_DRIVER_NAME = "com.mysql.jdbc.Driver";
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	Connection getConnection() throws DatabaseException{
		myLogger.info("Start");
		try {
			Class.forName(DB_DRIVER_NAME);
			connection = DriverManager.getConnection(DB_URL, 
					DB_USERNAME, DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			myLogger.error(e.getClass() + ": " + e.getMessage());
			throw new DatabaseException(Constants.ERROR_CONNECT_DATABASE);
		} catch (SQLException e) {
			myLogger.error(e.getClass() + ":" + e.getMessage());
			throw new DatabaseException(Constants.ERROR_CONNECT_DATABASE);
		}
		myLogger.info("End");
		return connection;
	}
	

	void closeConnection(Connection connection) throws DatabaseException{
		myLogger.info("Start");
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				myLogger.error(e.getClass() + ": " + e.getMessage());
				throw new DatabaseException(Constants.ERROR_CONNECT_DATABASE);
			}
		}
		myLogger.info("End");
	}
	
	void closePreparetdStatement(PreparedStatement preparedStatement) throws DatabaseException{
		myLogger.info("Start");
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				myLogger.error(e.getClass() + ": " + e.getMessage());
				throw new DatabaseException(Constants.ERROR_CONNECT_DATABASE);
			}
		}
		myLogger.info("End");
	}
	
	void closeResultSet(ResultSet resultSet) throws DatabaseException{
		myLogger.info("Start");
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				myLogger.error(e.getClass() + ": " + e.getMessage());
				throw new DatabaseException(Constants.ERROR_CONNECT_DATABASE);
			}
		}
		myLogger.info("End");
	}
}
