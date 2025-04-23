package com.project.zumba.service;

import java.util.List;

import com.project.zumba.model.Enrollment;

public interface IEnrollmentService {
	public Enrollment getEnrollment(int id);

	public List<Enrollment> getAllEnrollment();

	public void saveEnrollment(Enrollment object);

	public void updateEnrollment(Enrollment object);

	public void deleteEnrollment(int id);
}
