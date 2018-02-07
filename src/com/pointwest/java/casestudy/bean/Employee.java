package com.pointwest.java.casestudy.bean;

public class Employee {
	private int empID;
	private String firstName;
	private String lastName;
	private String role;
	private String shift;
	private Seat seat;
	private Project project;
	
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public Seat getSeat() {
		if (seat == null) {
			seat = new Seat();
		}
		return seat;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	public Project getProject() {
		if (project == null) {
			project = new Project();
		}
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}

	public String getFullName() {
		String fullName = firstName + " " + lastName;
		return fullName;
	}

}
