package com.bonzoi.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Pratishtha Mishra
 * Enter the server logs through console, one log in each line and then press enter and ctrl+z. 
 * Sample input : 
 * 185.12.15.89 – – [23/Nov/2014:03:33:03 -0500] “GET /ip HTTP/1.1” 503 323 “-” “curl/7.30.0”
 * 185.12.15.93 – – [23/Nov/2014:03:33:07 -0500] “GET /ip HTTP/1.1” 200 323 “-” “curl/7.30.0”
 * 54.243.188.61 – – [23/Nov/2014:03:33:08 -0500] “GET /ip HTTP/1.1” 503 323 “-” “check_http/v1.4.16 (nagios-plugins 1.4.16)”
 * 87.255.53.44 – – [23/Nov/2014:03:33:52 -0500] “GET /ip HTTP/1.1” 200 323 “-” “curl/7.19.7 (universal-apple-darwin10.0) libcurl/7.19.7 OpenSSL/0.9.8r zlib/1.2.3”
 * 87.255.53.45 – – [23/Nov/2014:03:33:54 -0500] “GET /ip HTTP/1.1” 503 323 “-” “curl/7.19.7 (universal-apple-darwin10.0) libcurl/7.19.7 OpenSSL/0.9.8r zlib/1.2.3”
 */

public class HttpLogParser {

	public static final String HTTP = "HTTP";
	public static final String INVALID_LOG_MSG = "Invalid Logs.\\nSample server log : \\n 185.12.15.89 – – [23/Nov/2014:03:33:03 -0500] “GET /ip HTTP/1.1” 503 323 “-” “curl/7.30.0”";

	public static void main(String[] args) {

		HashMap<String, Integer> httpCodeMap = new HashMap<String, Integer>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter Server logs: ");
		String serverLog = null;

		/**
		 *  Reading each line through console and parsing it to get the HTTP code, Maintaining a map 
		 *  of HTTP codes with HTTP code as key and number of occurrences as value. 
		 */
		try {
			while ((serverLog = br.readLine()) != null) {
				 if(!serverLog.isEmpty()) {
					String httpCode = serverLog.substring(serverLog.indexOf(HTTP) + 10, serverLog.indexOf(HTTP) + 13);
					if (httpCodeMap.containsKey(httpCode)) {
						int count = httpCodeMap.get(httpCode);
						httpCodeMap.put(httpCode, count + 1);
					} else {
						httpCodeMap.put(httpCode, 1);
					}
			 }
			}

			if(httpCodeMap.size()!=0) {
				// Sorting the Map on decreasing order of values. 
				 
				Set<Entry<String, Integer>> set = httpCodeMap.entrySet();
				List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
				Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

					public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
						return (o2.getValue()).compareTo(o1.getValue());
					}
				});
				
				// Printing the result. 
				 
				System.out.println();
				System.out.println("HTTP Code  :  Count");
				for (Map.Entry<String, Integer> entry : list) {
					System.out.println(entry.getKey() + "        :    " + entry.getValue());
				}
			} else {
				System.out.println(INVALID_LOG_MSG);
			}
			

		} catch (IndexOutOfBoundsException e) {
			System.out.println(INVALID_LOG_MSG);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
