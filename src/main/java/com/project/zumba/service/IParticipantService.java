package com.project.zumba.service;

import java.util.List;

import com.project.zumba.model.Participant;

public interface IParticipantService {
	public Participant getParticipant(int id);

	public List<Participant> getAllParticipant();

	public void saveParticipant(Participant object);

	public void updateParticipant(Participant object);

	public void deleteParticipant(int id);
	public Participant getParticipantBySignIn(String email, String pw) ;
}
