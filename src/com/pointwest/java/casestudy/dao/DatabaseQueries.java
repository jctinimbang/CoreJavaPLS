package com.pointwest.java.casestudy.dao;

public interface DatabaseQueries {

	static final String QUERY_LOGIN_USERNAME_PASSWORD = "SELECT emp.emp_id, emp.first_name, emp.last_name, emp.role"
			+ " FROM employee emp" + " WHERE BINARY emp.username = (?) AND BINARY emp.password = (?)";

	final String COMMON_QUERY_SEARCH = "SELECT emp.emp_id, emp.first_name, emp.last_name, emp.role, emp.shift,"
			+ " seat.bldg_id, seat.floor_number, seat.quadrant, seat.row_number, seat.column_number, seat.local_number,"
			+ " group_concat(project.proj_name) as projects" 
			+ " FROM employee emp"
			+ " LEFT JOIN employee_seat emp_seat ON emp.emp_id = emp_seat.emp_id"
			+ " LEFT JOIN seat seat ON emp_seat.seat_id = seat.seat_id"
			+ " LEFT JOIN employee_project emp_project ON emp.emp_id = emp_project.employee_id"
			+ " LEFT JOIN project project ON emp_project.proj_alias = project.proj_alias";

	final String COMMON_QUERY_GROUP_BY = " GROUP BY emp.emp_id, seat.bldg_id, seat.floor_number, seat.quadrant,"
			+ " seat.row_number, seat.column_number";
	
	final String COMMON_QUERY_ORDER_BY = " ORDER BY emp.first_name";

	static final String QUERY_SEARCH_BY_ID = COMMON_QUERY_SEARCH + " WHERE emp.emp_id LIKE (?)"
			+ COMMON_QUERY_GROUP_BY
			+ COMMON_QUERY_ORDER_BY;

	public static final String QUERY_SEARCH_BY_NAME = COMMON_QUERY_SEARCH
			+ " WHERE CONCAT (emp.first_name, ' ', emp.last_name,  ' ', emp.first_name) LIKE ?" + COMMON_QUERY_GROUP_BY
			+ COMMON_QUERY_ORDER_BY;

	static final String QUERY_SEARCH_BY_PROJECT = COMMON_QUERY_SEARCH + " WHERE project.proj_name LIKE ?"
			+ COMMON_QUERY_GROUP_BY
			+ COMMON_QUERY_ORDER_BY;

	static final String QUERY_VIEW_AVALABLE_SEATPLAN = "SELECT location.bldg_name,"
			+ " seat.bldg_id, seat.floor_number, group_concat(distinct seat.quadrant separator ' , ') as quadrants"
			+ " FROM seat seat" + " JOIN location location ON seat.bldg_id = location.bldg_id"
			+ " group by seat.bldg_id, seat.floor_number";

	static final String QUERY_VIEW_SEAT_PLAN = "SELECT emp.emp_id, emp.first_name, emp.last_name,"
			+ " seat.bldg_id, location.bldg_address, seat.floor_number, seat.quadrant, seat.row_number, seat.column_number, seat.local_number"
			+ " FROM employee emp" + " LEFT JOIN employee_seat emp_seat ON emp.emp_id = emp_seat.emp_id"
			+ " LEFT JOIN seat seat ON emp_seat.seat_id = seat.seat_id"
			+ " LEFT JOIN location location ON seat.bldg_id = location.bldg_id"
			+ " WHERE seat.bldg_id = (?) AND seat.floor_number = (?) AND seat.quadrant LIKE (?)"
			+ " ORDER BY seat.quadrant, seat.row_number, seat.column_number";
}
