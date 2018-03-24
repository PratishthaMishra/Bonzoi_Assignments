package com.bonzoi.assignment2;

/**
 * @author Pratishtha Mishra
 * Bean Activity to store start and end time of an activity. 
 *
 */

public class Activity{
 
	private int startTime;
    private int endTime;
    
    @Override
	public String toString() {
		return "Activity [startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
}
