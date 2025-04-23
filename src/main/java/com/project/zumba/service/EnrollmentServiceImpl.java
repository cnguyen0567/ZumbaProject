package com.project.zumba.service;

import java.util.List;

import com.project.zumba.dao.EnrollmentDAOImpl;
import com.project.zumba.model.Enrollment;

public class EnrollmentServiceImpl implements IEnrollmentService {
	//Declare an interface obj to complete service
	private EnrollmentDAOImpl enrollmentDao;
	
	//Instantiate the obj inside the constructor
	public EnrollmentServiceImpl() {    
		enrollmentDao = new EnrollmentDAOImpl();
	}
	@Override
	public Enrollment getEnrollment(int id) {
		System.out.println("Inside EnrollmentServiceImpl.getEnrollment()");
		return enrollmentDao.get(id);
	}

	@Override
	public List<Enrollment> getAllEnrollment() {
		System.out.println("Inside EnrollmentServiceImpl.getAllEnrollment()");
		//batchDao.getAll();	
		return null;
	}

	@Override
	public void saveEnrollment(Enrollment object) {
		System.out.println("Inside EnrollmentServiceImpl.saveEnrollment()");
		enrollmentDao.save(object);	
		
	}

	@Override
	public void updateEnrollment(Enrollment object) {
		System.out.println("Inside EnrollmentServiceImpl.updateEnrollment()");
		enrollmentDao.update(object);
		
	}

	@Override
	public void deleteEnrollment(int id) {
		System.out.println("Inside EnrollmentServiceImpl.deleteEnrollment()");
		enrollmentDao.delete(id);
		
	}
	public void deleteEnrollmentByParticipant(int id) {
		System.out.println("Inside EnrollmentServiceImpl.deleteEnrollment()");
		enrollmentDao.deleteByParticipant(id);
	}
	
	public void deleteEnrollmentByBatch(int id) {
		System.out.println("Inside EnrollmentServiceImpl.deleteEnrollment()");
		enrollmentDao.deleteByBatch(id);
	}
}