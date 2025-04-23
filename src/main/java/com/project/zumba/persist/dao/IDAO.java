package com.project.zumba.persist.dao;
import java.util.List;

/*
 * This serve as the highest generic DAO interface for other DAO-related interface and/or class to do CRUD op
 * */

public interface IDAO<T> {   //The <T> is generic. it doesn't what data type put in there. THe data type is enforced by the return data type of the method
							 //allow to do generic CRUD operation
							 //will figure out the datatype at run time
	
	T get (int id);         // take in an ID and return whatever object determined at implementation
	List<T> getAll();        //retrieve method
	void save (T object);    //create method
	void update (T object);  //tip on this. call the get first to see if the record exist before updating or deletinh
	void delete(int id);

}
