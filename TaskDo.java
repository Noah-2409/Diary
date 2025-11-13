package diary;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
/*-----------------------------Probleme----------------------------------
 * 
 * 
 */

public class TaskDo {
	public static void insertTask(String story, String title) {
		//_________________-ERFOLG________________________

		String query = "INSERT INTO diary_entry(date,story,title) VALUES(?,?,?);";

		try (Connection connect = DB_CONNECTOR.getConnection();
				PreparedStatement stmt = connect.prepareStatement(query)) {
			stmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			stmt.setString(2, story);
			stmt.setString(3, title);
			stmt.execute();
		} catch (SQLException e) {
			System.err.println("Fehler: " + e.getMessage());

		}
	}

	public static String getTitleFromDate(int year, int month, int day, int index) throws SQLException {
		String ergebnis = "Keine weitere Ergebnisse";
		LocalDate date = LocalDate.of(year, month, day);
		String query = "SELECT title FROM diary_entry WHERE date=?;";

		try (Connection connect = DB_CONNECTOR.getConnection();
				PreparedStatement stmt = connect.prepareStatement(query)) {
			stmt.setDate(1, java.sql.Date.valueOf(date));
			try (ResultSet rs = stmt.executeQuery()) {
				int i = 0;
				while (rs.next()) {
					if (i == index) {
						ergebnis = rs.getString("title");
						break;
					}
					i++;
				}
				return ergebnis;

			} catch (SQLException e) {
				return "Fehler: " + e.getMessage();
			}

		} catch (SQLException e) {
			return "Fehler: " + e.getMessage();
		}
	}
public static String getStoryFromDate(int year, int month, int day,String title) throws SQLException{

	String ergebnis;
	LocalDate date = LocalDate.of(year, month, day);
	String query = "SELECT story FROM diary_entry WHERE date=? AND title=?;";

	try (Connection connect = DB_CONNECTOR.getConnection();
			PreparedStatement stmt = connect.prepareStatement(query)) {
		stmt.setDate(1, java.sql.Date.valueOf(date));
		stmt.setString(2,title);
		try (ResultSet rs = stmt.executeQuery()) {
			rs.next();
			ergebnis = rs.getString("story");
			return ergebnis;

		} catch (SQLException e) {
			return "Fehler: " + e.getMessage();
		}
		
		
		
	} catch (SQLException e) {
		return "Fehler: " + e.getMessage();
	}

	}

	public static boolean isValidDate(JTextField fieldDay, JTextField fieldMonth, JTextField fieldYear) {
		if(!fieldDay.getText().isEmpty()&&
				!fieldMonth.getText().isEmpty()&&
				!fieldYear.getText().isEmpty()) {
			try {
			if (fieldDay.getText().length() < 2) {
				fieldDay.setText("0" + fieldDay.getText());
			}
			if (fieldMonth.getText().length() < 2) {
				fieldMonth.setText("0" + fieldMonth.getText());
			}
			LocalDate.parse(fieldYear.getText() + "-" + fieldMonth.getText() + "-" + fieldDay.getText());
			return true;

		} catch (DateTimeParseException e) {
			return false;
		}
		}
		return false;
		
	}
	public static List<String> allEntries()  throws SQLException{
		List<String> list =new  ArrayList<>();
		String query = "SELECT date,title FROM diary_entry";
		
		try(Connection connect =DB_CONNECTOR.getConnection();
				PreparedStatement stmt = connect.prepareStatement(query)){
			try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					String date = rs.getDate("date").toString();
					String title = rs.getString("title");
					
					list.add(date+" | "+title);
				}
			}catch(SQLException e) {
				throw e;
			}
			
			return list;
			
		}catch(SQLException e) {
			throw e;
			
		}
	}
	public static void updateEntry(LocalDate date,String title,String story)throws SQLException{
		String query = "UPDATE diary_entry set story = ? WHERE date = ? AND title = ?";
		try(Connection connect = DB_CONNECTOR.getConnection();
				PreparedStatement stmt = connect.prepareStatement(query)){
			stmt.setString(1,story);
			stmt.setDate(2,java.sql.Date.valueOf(date));
			stmt.setString(3, title);
			stmt.execute();
			
		}catch(SQLException e) {
			System.err.println("Fehler: "+e.getMessage());
			throw e;
		}
	}
	public static void deleteAll() throws SQLException {
		String query = "TRUNCATE TABLE diary_entry restart IDENTITY";
		
		try(Connection connect = DB_CONNECTOR.getConnection();
				PreparedStatement stmt = connect.prepareStatement(query)){
			stmt.execute();
		}catch(SQLException e) {
			throw e;
		}
	}
	

}
