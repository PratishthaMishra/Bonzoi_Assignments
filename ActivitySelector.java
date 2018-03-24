package com.bonzoi.assignment2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 
 * @author Pratishtha Mishra
 * Enter the input through command line arguments, Sample input for 2 test cases : 
 * 2
 * 6
 * 5 1 5 3 0 8 
 * 9 2 7 4 6 9 
 * 3
 * 12 10 20
 * 25 20 30 
 *
 */

public class ActivitySelector {

	public static void main(String[] args) {

		if (args.length < 4) {
			System.out.println(
					"Invalid input, Enter the input in following format.\n T(Number of Testcases)\n [T Descriptions] : {N(Number of activities)"
							+ "\n [N start times]\n [N finish times]}\n   Exiting...");
			System.exit(0);
		}
		int cmdArgIndex = 0;
		int testCases = Integer.parseInt(args[cmdArgIndex]);
		if (testCases < 1 || testCases > 50) {
			System.out.println("Constraint voilation, Testcases T should follow this condition :  1<=T<=50   ");
			System.exit(0);
		}
		
		//Reading the inputs from command line arguments and creating a list of activities for every test case. 
		for (int i = 0; i < testCases; i++) {
            List<Activity> activityList = new ArrayList<Activity>();
			
            cmdArgIndex = cmdArgIndex + 1;
			int activities = Integer.parseInt(args[cmdArgIndex]);
			
			if (activities < 1 || activities > 1000) {
				System.out.println("Constraint voilation, Activities N should follow this condition :  1<=N<=1000   ");
				System.exit(0);
			}
			
			for (int j = 0; j < activities; j++) {
				Activity activity = new Activity();
				cmdArgIndex = cmdArgIndex + 1;
				activity.setStartTime(Integer.parseInt(args[cmdArgIndex]));
				activity.setEndTime(Integer.parseInt(args[cmdArgIndex + activities]));
				activityList.add(activity);
			}
			cmdArgIndex = cmdArgIndex + activities;
			
			int optimalCount = findMaxActivities(activityList);
			
			// Printing the result. 
			System.out.println("Output for Testcase" + (i + 1) + " : " + optimalCount);

		}

	}

	/**
	 * This method works on the principle of Greedy algorithm to find the maximum number of 
	 * activities that can be performed by one individual. Activities are first sorted on the 
	 * basis of end time, first activity is considered as current by default and then for next activity 
	 * in the list, its start time is checked, if it is greater than or equals to the end time of
	 * current activity, it is picked as the current activity and optimal count is increased by 1. 
	 * This process is continued through out the list and we get the optimal count. 
	 * @param activityList
	 * @return optimalCount
	 */
	public static int findMaxActivities(List<Activity> activityList) {

		int optimalCount = 1;
		Collections.sort(activityList, new EndTimeComparator());

		int i = 0;
		for (int j = 1; j < activityList.size(); j++) {

			if (activityList.get(j).getStartTime() >= activityList.get(i).getEndTime()) {
				optimalCount++;
				i = j;
			}
		}
		return optimalCount;
	}

}
