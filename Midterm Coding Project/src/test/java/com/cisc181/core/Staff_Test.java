package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.BeforeClass;
import org.junit.Test;

public class Staff_Test {
	static ArrayList<Staff> staffList;
	static double salleries[] = {40000, 45000, 60000, 50000, 70000};
	@BeforeClass
	public static void setup() {
		staffList = new ArrayList<Staff>(5);
		for(double sallery : salleries) {
			staffList.add(new Staff(null, null, null, null, null, null, null, null, 0, sallery, null, null));
		}
	}
	
	@Test
	public void test() {
		double avg1 = 0;
		double avg2 = 0;
		for(double sallery : salleries) {
			avg1 += sallery;
		}
		avg1 /= salleries.length;
		
		for(Staff staff: staffList) {
			avg2 += staff.getSalary();
		}
		avg2 /= staffList.size();
		
		assertEquals(avg1, avg2, .001);
	}	

}
