package com.cisc181.core;

import java.util.Date;
import java.util.UUID;

public class Semester {
	private UUID SemesterID;
	private Date StartDate;
	private Date EndDate;
	public Semester(UUID semester, Date startDate, Date endDate) {
		super();
		SemesterID = semester;
		StartDate = startDate;
		EndDate = endDate;
	}
	public UUID getSemester() {
		return SemesterID;
	}
	public void setSemesterID(UUID semester) {
		SemesterID = semester;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	
	
}
