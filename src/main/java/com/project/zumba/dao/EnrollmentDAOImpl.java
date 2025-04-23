package com.project.zumba.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.project.zumba.model.Enrollment;
import com.project.zumba.persist.dao.IDAO;
import com.project.zumba.persist.database.Database;

public class EnrollmentDAOImpl implements IDAO<Enrollment>{
	private Database db = Database.getInstance();

	@Override
	public Enrollment get(int id) {
		System.out.println("Starting EnrollmentDAOImpl - get(id):");

		// SQL statement to select batch by id
		String selectSql = "SELECT * FROM enrollment WHERE enrollmentId = ?";

		Enrollment enrollmentObj = null; // declare a var to later hold result
		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(selectSql)) {
			// Set parameter for the select
			ps.setInt(1, id);
			// Execute the query
			ResultSet enrollmentResultSet = db.executeQuery(ps);

			// Extract the individual info from the resultSet
			enrollmentObj.setEnrollmentId(enrollmentResultSet.getInt("enrollmentId"));
			enrollmentObj.setBatchId(enrollmentResultSet.getInt("batchId"));
			enrollmentObj.setParticipantId(enrollmentResultSet.getInt("participantId"));
			enrollmentObj.setAddedOn(enrollmentResultSet.getDate("addedOn").toLocalDate());

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("SQL Exception occurred: ");
			e.printStackTrace();
		}

		return enrollmentObj;
	}

	@Override
	public List<Enrollment> getAll() {
		System.out.println("Starting EnrollmentDAOImpl - getAll():");
		//Prep sql statement
		//String selectSql = "SELECT * FROM enrollment";
		
		return null;
	}

	@Override
	public void save(Enrollment object) {
		System.out.println("Start EnrollmentDAOImpl - save/insert sql statement:");
		// SQL query placeholder to insert Categories object into the database
		String insertSql = "INSERT INTO enrollment (enrollmentId, participantId, batchId) VALUES (?, ?, ?)";

		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(insertSql)) {
			// Set values for prepared statement
			ps.setInt(1, object.getEnrollmentId());
			ps.setInt(2, object.getBatchId());
			ps.setInt(3, object.getParticipantId());

			// Actual attempt to send to db
			int sucessOrFail = db.executeUpdate(ps);

			if (sucessOrFail == 1) {
				System.out.println("Successfully processed " + object + " to db"); // the "object" should call the
																					// toString method
			} else {
				System.out.println("Error saving");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Enrollment object) {
		System.out.println("Start EnrollmentDAOImpl - update sql statement:");
		// SQL query placeholder to insert Categories object into the database
		String updateSql = "UPDATE enrollment SET participantId = ?, batchId = ? WHERE enrollmentId = ?";

		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(updateSql)) {
			
			// Set values for prepared statement
			ps.setInt(1, object.getParticipantId());
			ps.setInt(2, object.getBatchId());
			ps.setInt(3, object.getEnrollmentId());
			
			// Actual attempt to send to db
			int sucessOrFail = db.executeUpdate(ps);

			if (sucessOrFail == 1) {
				System.out.println("Successfully processed " + object + " to db"); // the "object" should call the
																					// toString method
			} else {
				System.out.println("Error saving");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		System.out.println("Start EnrollmentDAOImpl - delete sql statement:");
		// SQL query placeholder to insert Categories object into the database
		String deleteSql = "DELETE FROM enrollment WHERE enrollmentId = ?";

		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(deleteSql)) {
			//Set parameters:
			ps.setInt(1, id);
			// Actual attempt to send to db
			int sucessOrFail = db.executeUpdate(ps);
			if (sucessOrFail == 1) {
				System.out.println("Successfully deleted enrollment " + id + " from db");
			} else {
				System.out.println("Error deleting enrollment");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteByParticipant(int participantId) {
		System.out.println("Start EnrollmentDAOImpl - delete by participant sql statement:");
		// SQL query placeholder to insert Categories object into the database
		String deleteSql = "DELETE FROM enrollment WHERE participantId = ?";

		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(deleteSql)) {

			//Set parameters:
			ps.setInt(1, participantId);
			// Actual attempt to send to db
			int sucessOrFail = db.executeUpdate(ps);
			if (sucessOrFail == 1) {
				System.out.println("Successfully deleted all enrollment for participant " + participantId + " from db");
			} else {
				System.out.println("Error deleting enrollment for participant " + participantId);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteByBatch(int batchId) {
		System.out.println("Start EnrollmentDAOImpl - delete by batch sql statement:");
		// SQL query placeholder to insert Categories object into the database
		String deleteSql = "DELETE FROM enrollment WHERE batchId = ?";

		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(deleteSql)) {

			//Set parameters:
			ps.setInt(1, batchId);
			// Actual attempt to send to db
			int sucessOrFail = db.executeUpdate(ps);
			if (sucessOrFail == 1) {
				System.out.println("Successfully deleted all enrollment for batch " + batchId + " from db");
			} else {
				System.out.println("Error deleting enrollment for batch " + batchId);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
