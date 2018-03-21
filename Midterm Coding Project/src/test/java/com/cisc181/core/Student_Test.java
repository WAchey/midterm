package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eMajor;

public class Student_Test {
	static ArrayList<Course> courseList;
	static ArrayList<Semester> semesterList;
	static ArrayList<Section> sectionList;
	static ArrayList<Student> studentList;
	static ArrayList<Enrollment> enrolled;
	
	@BeforeClass
	public static void setup() {
		courseList = new ArrayList<Course>(3);
		courseList.add(new Course(UUID.randomUUID(), "Learning!", 3, eMajor.BUSINESS));
		courseList.add(new Course(UUID.randomUUID(), "Fun!", 3, eMajor.BUSINESS));
		courseList.add(new Course(UUID.randomUUID(), "School!", 3, eMajor.BUSINESS));
		
		semesterList = new ArrayList<Semester>(2);
		semesterList.add(new Semester(UUID.randomUUID(), new Date(), new Date()));
		semesterList.add(new Semester(UUID.randomUUID(), new Date(), new Date()));
		
		sectionList = new ArrayList<Section>(6);
		for(Semester semester : semesterList) {
			for(Course course : courseList) {
				sectionList.add(new Section(course.getCourseID(), semester.getSemester(), UUID.randomUUID(), 404));
			}
		}
		
		studentList = new ArrayList<Student>(10);
		studentList.add(new Student("", "", "", new Date(), eMajor.BUSINESS, "", "", ""));
		studentList.add(new Student("", "", "", new Date(), eMajor.BUSINESS, "", "", ""));
		studentList.add(new Student("", "", "", new Date(), eMajor.BUSINESS, "", "", ""));
		studentList.add(new Student("", "", "", new Date(), eMajor.BUSINESS, "", "", ""));
		studentList.add(new Student("", "", "", new Date(), eMajor.BUSINESS, "", "", ""));
		studentList.add(new Student("", "", "", new Date(), eMajor.BUSINESS, "", "", ""));
		studentList.add(new Student("", "", "", new Date(), eMajor.BUSINESS, "", "", ""));
		studentList.add(new Student("", "", "", new Date(), eMajor.BUSINESS, "", "", ""));
		studentList.add(new Student("", "", "", new Date(), eMajor.BUSINESS, "", "", ""));
		studentList.add(new Student("", "", "", new Date(), eMajor.BUSINESS, "", "", ""));
	}

	@Test
	public void test() {
		final int MAX_GRADE = 100;
		final int CREDIT_HOURS_PER_CLASS = 3;
		
		enrolled = new ArrayList<Enrollment>(studentList.size());
		ArrayList<Double> studentGpa = new ArrayList<Double>();
		
		for(int i = 0; i < studentList.size(); i++) {
			studentGpa.add(0.0); 
			
			for(int j = 0; j < sectionList.size(); j++) { 
				enrolled.add(new Enrollment(sectionList.get(j).getSectionID(), studentList.get(i).getStudentID()));
				double grade = Math.random() * MAX_GRADE;
				enrolled.get(j).SetGrade(grade);
				studentGpa.set(i, studentGpa.get(i) + gradePoints(grade)); //Adds the grade to the current student's gpa 
			}
			studentGpa.set(i, studentGpa.get(i) / ((double) sectionList.size() * CREDIT_HOURS_PER_CLASS));
		}
		
		
		int i = 0;
		for(Student student : studentList) {
			ArrayList<Enrollment> stEnroll = new ArrayList<Enrollment>();
			for(Enrollment enroll : enrolled) {
				if(student.getStudentID().equals(enroll.getStudentID()))
					stEnroll.add(enroll);
			}
			assertEquals(studentGpa.get(i), calcGPA(stEnroll), .01);
			i++;
		}
		//TODO get value for testAvg
		double testAvg = 0;
		
		
		for(Course course : courseList) {
			double avg = 0;
			int gCount = 0;
			for(Section section : sectionList) {
				if(section.getCourseID().equals(course.getCourseID())) {
					for(Enrollment enroll : enrolled) {
						if(section.getSectionID().equals(enroll.getSectionID())) {
							gCount++;
							avg += enroll.getGrade();
						}
					}
				}
			}
			avg /= (double)gCount;
			assertEquals(testAvg, avg, .001);
		}
		
		
	}
	
	public static int creditHours(UUID sectionID) {
		UUID courseID = null;
		for(Section section : sectionList) {
			if(sectionID.equals(section.getSectionID()))
				courseID = section.getCourseID();
		}
		for(Course course : courseList) {
			if(courseID.equals(course.getCourseID()))
				return course.getGradePoints();
		}
		return 0;
	}
	
	public static double gradePoints(double grade) {
		int iGrade = (int) grade;
		@SuppressWarnings("unused")
		double pGrade = 0;
		if(iGrade > 60)
			pGrade = .7; //D-
		if(iGrade > 63)
			pGrade = 1; //D
		if(iGrade > 67)
			pGrade = 1.3;
		if(iGrade > 70)
			pGrade = 1.7;
		if(iGrade > 73)
			pGrade = 2;
		if(iGrade > 77)
			pGrade = 2.3;
		if(iGrade > 80)
			pGrade = 2.7;
		if(iGrade > 83)
			pGrade = 3;
		if(iGrade > 87)
			pGrade = 3.33;
		if(iGrade > 90)
			pGrade = 3.7;
		if(iGrade > 95)
			pGrade = 4;
		
		
		return pGrade;
	}

	public static double calcGPA(ArrayList<Enrollment> classes) {
		double totalPts = 0;
		double totalHourse = 0;
		for(Enrollment enroll : classes) {
			totalPts += gradePoints(enroll.getGrade()); 
			totalHourse += creditHours(enroll.getSectionID());
		}
		return totalPts/(double)totalHourse;
	}
}