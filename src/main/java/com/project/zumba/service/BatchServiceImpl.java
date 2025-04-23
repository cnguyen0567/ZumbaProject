package com.project.zumba.service;
import java.util.List;
import com.project.zumba.dao.BatchDAOImpl;
import com.project.zumba.dao.EnrollmentDAOImpl;
import com.project.zumba.model.Batch;

public class BatchServiceImpl implements IBatchService {
	//Declare an interface obj to complete service
	private BatchDAOImpl batchDao;
	private EnrollmentDAOImpl enrollmentDao;
	
	public BatchServiceImpl() {    //instantiate the field inside the constructor ensures that
		batchDao = new BatchDAOImpl();  // when this class is called/used, an obj is instantiate
	}
	
	@Override
	public Batch getBatch(int id) {
		System.out.println("Inside BatchServiceImpl.getBatch()");
		return batchDao.get(id);
	}
	@Override
	public List<Batch> getAllBatches() {
		System.out.println("Inside BatchServiceImpl.getAllBatches()");
		//batchDao.getAll();	
		return null;
	}
	@Override
	public void saveBatch(Batch object) {
		System.out.println("Inside BatchServiceImpl.saveBatch()");
		batchDao.save(object);	
	}
	@Override
	public void updateBatch(Batch object) {
		System.out.println("Inside BatchServiceImpl.updateBatch()");
		batchDao.update(object);
	}
	@Override
	public void deleteBatch(int id) {
		System.out.println("Inside BatchServiceImpl.deleteBatch()");
		enrollmentDao = new EnrollmentDAOImpl();
		enrollmentDao.deleteByBatch(id);  //need to delete all enrollment first
		batchDao.delete(id);              //then can delete batch
		
	}
}
