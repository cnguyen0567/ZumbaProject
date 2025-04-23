package com.project.zumba.service;

import java.util.List;

import com.project.zumba.model.Batch;

public interface IBatchService {
	public Batch getBatch(int id);

	public List<Batch> getAllBatches();

	public void saveBatch(Batch object);

	public void updateBatch(Batch object);

	public void deleteBatch(int id);
}
