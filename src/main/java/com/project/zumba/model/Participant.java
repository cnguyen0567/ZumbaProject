package com.project.zumba.model;
import java.time.LocalDate;
import java.util.Date;

public class Participant {
	/* Fields */
	private Integer participantId;
	private String participantName;
	private String participantEmail;
	private String participantPassword;
	//private String avatarURL;
	private Boolean isAdmin = false;
	private LocalDate addedOn;
	
	/* Constructor */
	public Participant(Integer participantId, String participantName, String participantEmail,
			String participantPassword, Boolean isAdmin, LocalDate addedOn) {
		super();
		this.participantId = participantId;
		this.participantName = participantName;
		this.participantEmail = participantEmail;
		this.participantPassword = participantPassword;
		this.isAdmin = isAdmin;
		this.addedOn = addedOn;
	}
	
	public Participant() {
		super();
	}

	/* Getters and Setters */
	public Integer getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public String getParticipantEmail() {
		return participantEmail;
	}

	public void setParticipantEmail(String participantEmail) {
		this.participantEmail = participantEmail;
	}

	public String getParticipantPassword() {
		return participantPassword;
	}

	public void setParticipantPassword(String participantPassword) {
		this.participantPassword = participantPassword;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public LocalDate getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(LocalDate addedOn) {
		this.addedOn = addedOn;
	}
	/*To String */
	@Override
	public String toString() {
		return "Participant [participantId=" + participantId + ", participantName=" + participantName
				+ ", participantEmail=" + participantEmail + ", participantPassword=" + participantPassword
				+ ", isAdmin=" + isAdmin + ", addedOn=" + addedOn + "]";
	}
	
	
	
}