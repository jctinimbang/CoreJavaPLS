package com.pointwest.java.casestudy.bean;

public class Seat {
	private String buildingID;
	private int floor;
	private String quadrant;
	private int row;
	private int column;
	private int localNumber;
	private String buildingName;
	private String buildingAddress;

	public String getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(String buildingID) {
		this.buildingID = buildingID;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getQuadrant() {
		return quadrant;
	}

	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getLocalNumber() {
		return localNumber;
	}

	public String getSeatLocation() {
		return buildingID
				+ floor
				+ "F"
				+ quadrant
				+ row
				+ "-"
				+ column;
	}
	
	public void setLocalNumber(int localNumber) {
		this.localNumber = localNumber;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingAddress() {
		return buildingAddress;
	}

	public void setBuildingAddress(String buildingAddress) {
		this.buildingAddress = buildingAddress;
	}

}
