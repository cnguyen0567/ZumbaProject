package com.project.zumba.model;

import java.time.LocalDate;

public class Enrollment {
	private Integer enrollmentId;
	private Integer participantId;
	private Integer batchId;
	private LocalDate addedOn;
	
	/*Constructor*/
	public Enrollment(Integer enrollmentId, Integer participantId, Integer batchId, LocalDate addedOn) {
		super();
		this.enrollmentId = enrollmentId;
		this.participantId = participantId;
		this.batchId = batchId;
		this.addedOn = addedOn;
	}
	
	public Enrollment() {
		super();
	}

	/*Getters and Setters*/
	public Integer getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(Integer enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public Integer getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public LocalDate getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(LocalDate addedOn) {
		this.addedOn = addedOn;
	}
	
	/*To strng */
	@Override
	public String toString() {
		return "Enrollment [enrollmentId=" + enrollmentId + ", participantId=" + participantId + ", batchId=" + batchId
				+ ", addedOn=" + addedOn + "]";
	}
	
	
	
}
