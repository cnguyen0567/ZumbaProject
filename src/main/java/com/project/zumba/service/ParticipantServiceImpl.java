package com.project.zumba.service;

import java.util.List;

import com.project.zumba.dao.EnrollmentDAOImpl;
import com.project.zumba.dao.ParticipantDAOImpl;
import com.project.zumba.model.Participant;

public class ParticipantServiceImpl implements IParticipantService {
	//Declare an interface obj to complete service
	private ParticipantDAOImpl participantDao;
	private EnrollmentDAOImpl enrollmentDao;
	
	//Instantiate the obj with constructor
	public ParticipantServiceImpl() {
		participantDao = new ParticipantDAOImpl();
	}
	@Override
	public Participant getParticipant(int id) {
		System.out.println("Inside ParticipantServiceImpl.getParticipant()");
		return participantDao.get(id);
	}
	public Participant getParticipantBySignIn(String email, String pw) {
		System.out.println("Inside ParticipantServiceImpl.getParticipantBySignIn()");
		return participantDao.get(email, pw);
	}

	@Override
	public List<Participant> getAllParticipant() {
		System.out.println("Inside ParticipantServiceImpl.getAllParticipant()");
		//participantDao.getAll();	
		return null;
	}

	@Override
	public void saveParticipant(Participant object) {
		System.out.println("Inside ParticipantServiceImpl.saveParticipant()");
		participantDao.save(object);	
	}

	@Override
	public void updateParticipant(Participant object) {
		System.out.println("Inside ParticipantServiceImpl.updateParticipant()");
		participantDao.update(object);
		
	}

	@Override
	public void deleteParticipant(int id) {
		System.out.println("Inside ParticipantServiceImpl.deleteParticipant()");
		enrollmentDao = new EnrollmentDAOImpl();
		enrollmentDao.deleteByParticipant(id);  //need to delete all enrollment first
		participantDao.delete(id);
	}
	
	public void updateParticipantByEmail(Participant object) {
		System.out.println("Inside ParticipantServiceImpl.updateParticipantByEmail()");
		participantDao.updateParticipantByEmail(object);
		
	}


}
