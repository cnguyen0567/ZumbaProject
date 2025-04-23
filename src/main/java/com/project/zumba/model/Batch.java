package com.project.zumba.model;
import java.time.LocalDate;
import java.time.LocalTime;

public class Batch {
	/* Fields */
	private Integer batchId;
	private String batchName; //ex. beginnerZumba, inteZumba, advZumba
	private LocalDate batchStartDate;
	private LocalDate batchEndDate;
	private LocalTime batchStartTime;
	private Float batchDuration; //1hr, 1.5hrs
	private Integer batchSizeLimit = 20; //Set class size limit
	private LocalDate batchAddedOn;
	
	/* Constructor */
	public Batch(Integer batchId, String batchName, LocalDate batchStartDate, LocalDate batchEndDate, LocalTime batchStartTime,
			Float batchDuration, Integer batchSizeLimit, LocalDate batchAddedOn) {
		super();
		this.batchId = batchId;
		this.batchName = batchName;
		this.batchStartDate = batchStartDate;
		this.batchEndDate = batchEndDate;
		this.batchStartTime = batchStartTime;
		this.batchDuration = batchDuration;
		this.batchSizeLimit = batchSizeLimit;
		this.batchAddedOn = batchAddedOn;
	}

	public Batch() {
		super();
	}

	/* Setters and Getters */
	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public LocalDate getBatchStartDate() {
		return batchStartDate;
	}

	public void setBatchStartDate(LocalDate batchStartDate) {
		this.batchStartDate = batchStartDate;
	}

	public LocalDate getBatchEndDate() {
		return batchEndDate;
	}

	public void setBatchEndDate(LocalDate batchEndDate) {
		this.batchEndDate = batchEndDate;
	}

	public LocalTime getBatchStartTime() {
		return batchStartTime;
	}

	public void setBatchStartTime(LocalTime batchStartTime) {
		this.batchStartTime = batchStartTime;
	}

	public Float getBatchDuration() {
		return batchDuration;
	}

	public void setBatchDuration(Float batchDuration) {
		this.batchDuration = batchDuration;
	}

	public Integer getBatchSizeLimit() {
		return batchSizeLimit;
	}

	public void setBatchSizeLimit(Integer batchSizeLimit) {
		this.batchSizeLimit = batchSizeLimit;
	}

	public LocalDate getBatchAddedOn() {
		return batchAddedOn;
	}

	public void setBatchAddedOn(LocalDate addedOn) {
		this.batchAddedOn = addedOn;
	}
	/* To String */

	@Override
	public String toString() {
		return "Batch [batchId=" + batchId + ", batchName=" + batchName + ", batchStartDate=" + batchStartDate
				+ ", batchEndDate=" + batchEndDate + ", batchStartTime=" + batchStartTime + ", batchDuration="
				+ batchDuration + ", batchSizeLimit=" + batchSizeLimit + ", batchAddedOn=" + batchAddedOn + "]";
	}
	
	
	
	
}
