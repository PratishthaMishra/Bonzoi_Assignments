package com.bonzoi.assignment2;

import java.util.Comparator;
/**
 * @author Pratishtha Mishra
 * Comparator utility class to sort the activities based on their end time. 
 *
 */
public class EndTimeComparator implements Comparator<Activity> {

	@Override
	public int compare(Activity a1, Activity a2) {
		
		if(a1.getEndTime() == a2.getEndTime())  
			return 0;  
		else if(a1.getEndTime() > a2.getEndTime())  
			return 1;  
		else  
			return -1;  
	}

}
