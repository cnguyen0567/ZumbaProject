package com.project.zumba.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.project.zumba.model.Batch;
import com.project.zumba.persist.dao.IDAO;
import com.project.zumba.persist.database.Database;

public class BatchDAOImpl implements IDAO<Batch> {
	private Database db = Database.getInstance();

	@Override
	public Batch get(int id) {
		System.out.println("Starting BatchDAOImpl - get(id):");

		// SQL statement to select batch by id
		String selectSql = "SELECT * FROM batch WHERE batchId = ?";

		Batch batchObj = null; // declare a var to later hold result
		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(selectSql)) {
			// Set parameter for the select
			ps.setInt(1, (int) id);
			// Execute the query
			ResultSet batchResultSet = db.executeQuery(ps);

			// Extract the individual info from the resultSet
			batchObj.setBatchId(batchResultSet.getInt("batchId"));
			batchObj.setBatchName(batchResultSet.getString("batchName"));
			batchObj.setBatchStartDate(batchResultSet.getDate("batchStartDate").toLocalDate());
			batchObj.setBatchEndDate(batchResultSet.getDate("batchEndDate").toLocalDate());
			batchObj.setBatchStartTime(batchResultSet.getTime("batchStartTime").toLocalTime());
			batchObj.setBatchDuration(batchResultSet.getFloat("batchDuration"));
			batchObj.setBatchSizeLimit(batchResultSet.getInt("batchSizeLimit"));
			batchObj.setBatchAddedOn(batchResultSet.getDate("batchAddedOn").toLocalDate());

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("SQL Exception occurred: ");
			e.printStackTrace();
		}

		return batchObj;
	}

	@Override
	public List<Batch> getAll() {
		System.out.println("Starting BatchDAOImpl - getAll():");
		List<Batch> batchList = new ArrayList<>();
		
		//Prep sql statement
		String selectSql = "SELECT * FROM batch";
		 
		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(selectSql)) {
			// Execute the query
			ResultSet batchResultSet = db.executeQuery(ps);
			
	        while (batchResultSet.next()) {
	            Batch batchObj = new Batch();
	            batchObj.setBatchId(batchResultSet.getInt("batchId"));
				batchObj.setBatchName(batchResultSet.getString("batchName"));
				batchObj.setBatchStartDate(batchResultSet.getDate("batchStartDate").toLocalDate());
				batchObj.setBatchEndDate(batchResultSet.getDate("batchEndDate").toLocalDate());
				batchObj.setBatchStartTime(batchResultSet.getTime("batchStartTime").toLocalTime());
				batchObj.setBatchDuration(batchResultSet.getFloat("batchDuration"));
				batchObj.setBatchSizeLimit(batchResultSet.getInt("batchSizeLimit"));
				batchObj.setBatchAddedOn(batchResultSet.getDate("batchAddedOn").toLocalDate());

	            batchList.add(batchObj);
	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void save(Batch object) {
		System.out.println("Start BatchDAOImpl - save/insert sql statement:");
		// SQL query placeholder to insert Categories object into the database
		String insertSql = "INSERT INTO batch (batchName, batchStartDate, batchEndDate, batchStartTime,"
							+ "batchDuration, batchSizeLimit) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(insertSql)) {
			// Set values for prepared statement
			ps.setString(1, object.getBatchName());
			java.sql.Date sqlStartDate = Date.valueOf(object.getBatchStartDate()); // Convert java.util.LocalDate to sql date
			ps.setDate(2, sqlStartDate);
			java.sql.Date sqlEndDate = Date.valueOf(object.getBatchEndDate()); // Convert java.util.LocalDate to sql date
			ps.setDate(3, sqlEndDate);
			java.sql.Time sqlStartTime = Time.valueOf(object.getBatchStartTime());
			ps.setTime(4, sqlStartTime);
			ps.setFloat(5, object.getBatchDuration());
			ps.setInt(6, object.getBatchSizeLimit());

			// Actual attempt to send to db
			int sucessOrFail = db.executeUpdate(ps);
			if (sucessOrFail == 1) {
				System.out.println("Successfully processed " + object + " to db");
			} else {
				System.out.println("Error saving batch");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Batch object) {
		System.out.println("Start BatchDAOImpl - update sql statement:");
		// SQL query placeholder to insert Categories object into the database
		String updateSql = "UPDATE batch SET batchName = ?, batchStartDate = ?, batchEndDate = ?, batchStartTime = ?, batchDuration = ?, batchSizeLimit = ? WHERE batchId = ?";

		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(updateSql)) {

			// Set values for prepared statement
			ps.setString(1, object.getBatchName());

			java.sql.Date sqlStartDate = Date.valueOf(object.getBatchStartDate()); // Convert java.util.LocalDate to
																					// sql.date
			ps.setDate(2, sqlStartDate);

			java.sql.Date sqlEndDate = Date.valueOf(object.getBatchEndDate()); // Convert java.util.LocalDate to
																				// sql.date
			ps.setDate(3, sqlEndDate);

			java.sql.Time sqlStartTime = Time.valueOf(object.getBatchStartTime());
			ps.setTime(4, sqlStartTime);
			ps.setFloat(5, object.getBatchDuration());
			ps.setInt(6, object.getBatchSizeLimit());
			ps.setInt(7, object.getBatchId());

			// Actual attempt to send to db
			int sucessOrFail = db.executeUpdate(ps);

			if (sucessOrFail == 1) {
				System.out.println("Successfully processed " + object + " to db"); // the "object" should call the
																					// toString method
			} else {
				System.out.println("Error saving batch");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		System.out.println("Start BatchDAOImpl - delete sql statement:");
		// SQL query placeholder to insert Categories object into the database
		String deleteSql = "DELETE FROM batch WHERE batchId = ?";

		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(deleteSql)) {
			// Set parameters:
			ps.setInt(1, id);

			// Actual attempt to send to db
			int sucessOrFail = db.executeUpdate(ps);
			if (sucessOrFail == 1) {
				System.out.println("Successfully deleted batch " + id + " from db");
			} else {
				System.out.println("Error deleting batch");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
