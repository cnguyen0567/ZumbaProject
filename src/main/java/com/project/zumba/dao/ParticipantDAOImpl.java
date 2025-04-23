package com.project.zumba.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import com.project.zumba.model.Participant;
import com.project.zumba.persist.dao.IDAO;
import com.project.zumba.persist.database.Database;

public class ParticipantDAOImpl implements IDAO<Participant> {
	private Database db = Database.getInstance();

	@Override
	public Participant get(int id) {
		System.out.println("Starting ParticipantDAOImpl - get(id):");

		// SQL statement to select record by id
		String selectSql = "SELECT * FROM participant WHERE participantId = ?";
		// Declare a var to later hold result
		Participant participantObj = null;
		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(selectSql)) {
			// Set parameter for the select
			ps.setInt(1, id);
			// Execute the query
			ResultSet participantResultSet = db.executeQuery(ps);

			// Extract the individual info from the resultSet\
			if (participantResultSet.next()) {
				System.out.println("Participant found: " + participantResultSet.getInt("participantId") + ", " + participantResultSet.getString("participantEmail"));
			participantObj.setParticipantId(participantResultSet.getInt("participantId"));
			participantObj.setParticipantName(participantResultSet.getString("participantName"));
			participantObj.setParticipantEmail(participantResultSet.getString("participantEmail"));
			participantObj.setParticipantPassword(participantResultSet.getString("participantPassword"));
			participantObj.setIsAdmin(participantResultSet.getBoolean("isAdmin"));
			participantObj.setAddedOn(participantResultSet.getDate("participantAddedOn").toLocalDate());
			} else {
				System.out.println("Participant not found:");
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("SQL Exception occurred: ");
			e.printStackTrace();
		}

		return participantObj;
	}
	
	public Participant get(String email, String pw) {
		System.out.println("Starting ParticipantDAOImpl - get(email, pass):");

		// SQL statement to select record by id
		String selectSql = "SELECT * FROM participant WHERE participantEmail = ? AND participantPassword = ?";
		// Declare a var to later hold result
		Participant participantObj = new Participant();
		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(selectSql)) {
			// Set parameter for the select
			ps.setString(1, email);
			ps.setString(2, pw);
			// Execute the query
			ResultSet participantResultSet = db.executeQuery(ps);
			
			// Extract the individual info from the resultSet
			if (participantResultSet.next()) {
				System.out.println("Participant found: " + participantResultSet.getInt("participantId") + ", " + participantResultSet.getString("participantEmail"));
			participantObj.setParticipantId(participantResultSet.getInt("participantId"));
			participantObj.setParticipantName(participantResultSet.getString("participantName"));
			participantObj.setParticipantEmail(participantResultSet.getString("participantEmail"));
			participantObj.setParticipantPassword(participantResultSet.getString("participantPassword"));
			participantObj.setIsAdmin(participantResultSet.getBoolean("isAdmin"));
			participantObj.setAddedOn(participantResultSet.getDate("addedOn").toLocalDate());
			} else {
				System.out.println("Participant not found:");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("SQL Exception occurred: ");
			e.printStackTrace();
		}
		System.out.println("End ParticipantDAOImpl - get(email, pass):");
		return participantObj;
	}

	@Override
	public List<Participant> getAll() {
		System.out.println("Starting ParticipantDAOImpl - getAll():");
		// Prep sql statement
		// String selectSql = "SELECT * FROM participant";
		return null;
	}

	@Override
	public void save(Participant object) {
		System.out.println("Start ParticipantDAOImpl - save/insert sql statement:");
		// SQL query placeholder to insert Categories object into the database
		String insertSql = "INSERT INTO participant (participantName, participantEmail, participantPassword, isAdmin) VALUES (?, ?, ?, ?)";

		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(insertSql)) {
			// Set values for prepared statement
			ps.setString(1, object.getParticipantName());
			ps.setString(2, object.getParticipantEmail());
			ps.setString(3, object.getParticipantPassword());
			ps.setBoolean(4, object.getIsAdmin());

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
	public void update(Participant object) {
		System.out.println("Start ParticipantDAOImpl - update sql statement:");
		// SQL query placeholder to insert Categories object into the database
		String updateSql = "UPDATE participant SET participantName=?, participantEmail=?, participantPassword=?, isAdmin=? WHERE participantId=?";

		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(updateSql)) {

			// Set values for prepared statement
			ps.setString(1, object.getParticipantName());
			ps.setString(2, object.getParticipantEmail());
			ps.setString(3, object.getParticipantPassword());
			ps.setBoolean(4, object.getIsAdmin());
			ps.setInt(5, object.getParticipantId());

			// Actual attempt to send to db
			int sucessOrFail = db.executeUpdate(ps);

			if (sucessOrFail == 1) {
				System.out.println("Successfully processed " + object + " to db"); // the "object" should call the
																					// toString method
			} else {
				System.out.println("Error update");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		System.out.println("Start ParticipantDAOImpl - delete sql statement:");
		// SQL query placeholder to insert Categories object into the database
		String deleteSql = "DELETE FROM participant WHERE participantId = ?";

		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(deleteSql)) {

			// Set parameters:
			ps.setInt(1, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateParticipantByEmail(Participant object) {
		System.out.println("Start ParticipantDAOImpl - update sql statement:");
		// SQL query placeholder to insert Categories object into the database
		String updateSql = "UPDATE participant SET participantName=?, participantPassword=?, isAdmin=? WHERE participantEmail=?";

		try (Connection connection = db.getConnection();
				PreparedStatement ps = connection.prepareStatement(updateSql)) {

			// Set values for prepared statement
			ps.setString(1, object.getParticipantName());
			ps.setString(2, object.getParticipantPassword());
			ps.setBoolean(3, object.getIsAdmin());
			ps.setString(4, object.getParticipantEmail());

			// Actual attempt to send to db
			int sucessOrFail = db.executeUpdate(ps);

			if (sucessOrFail == 1) {
				System.out.println("Successfully processed " + object + " to db"); // the "object" should call the
																					// toString method
			} else {
				System.out.println("Error update");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
