package com.example.timetabletest;

import java.io.Serializable;

/* Changes 
    Removed weeksRunning
*/

public class Session implements Serializable {
	private String startTime;
	private String endTime;
	//private int day;
	private String module;
	private String sessionType;
	private String groupID;
	private String roomCode;
	//private boolean weeksRunning[];
	

	
	public Session(String startTime, String endTime, String module,
			String sessionType, String groupID, String roomCode) {
		super();
		//this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.module = module;
		this.sessionType = sessionType;
		this.groupID = groupID;
		this.roomCode = roomCode;
		//this.weeksRunning = runsInWeek;
	}



	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}



	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}



	/**
	 * @return the day
	 */
        /*
	public int getDay() {
		return day;
	}
        */



	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}



	/**
	 * @return the sessionType
	 */
	public String getSessionType() {
		return sessionType;
	}



	/**
	 * @return the groupID
	 */
	public String getGroupID() {
		return groupID;
	}



	/**
	 * @return the roomCode
	 */
	public String getRoomCode() {
		return roomCode;
	}



	/**
	 * @return the weeksRunning
	 */
	/*public boolean[] getWeeksRunning() {
		return weeksRunning;
	}
        */



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String output =  "Start Time: " + startTime + " End Time: " + endTime
				+ " Weekday: " + " Module: " + module + " Type: "
				+ sessionType + " groupID: " + groupID + " Room: " + roomCode;
		return output;
	}
	
	

}
