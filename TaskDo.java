package diary;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


import javax.swing.JTextField;
/*-----------------------------Probleme----------------------------------
 * eintragen von datum im format DD-MM-YYYY geht nicht nach implementierung von IsValidDate
 * vllt methode nochmal nach fehlern checken
 * 
 * 
 */

public class TaskDo {
	public static void insertTask(String story, String title) {

		String query = "INSERT INTO diary_entry(date,story,title) VALUES(?,?,?)";

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
		String query = "SELECT title FROM diary_entry WHERE date=?";

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
	String query = "SELECT story FROM diary_entry WHERE date=? AND title=?";

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

}
