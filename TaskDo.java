package diary;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/*-----------------------------Probleme----------------------------------
 * 1.) getEntryStoryOrTitle bei nicht zahlen und ungültiges datum eingabe fehler
 * 
 * 
 */

public class TaskDo {
public static void insertTask(String story,String title){
	
	String query = "INSERT INTO diary_entry(date,story,title) VALUES(?,?,?)";
	
	try(Connection connect = DB_CONNECTOR.getConnection();
			PreparedStatement stmt = connect.prepareStatement(query)){
				stmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
				stmt.setString(2,story);
				stmt.setString(3,title);
				stmt.execute();
	}catch(SQLException e) {
		System.err.println("Fehler: "+e.getMessage());
		
	}
}
public static String getEntryStoryOrTitle(int year,int month,int day,String attribut,int index) throws SQLException {
	String ergebnis= "Keine weitere Ergebnisse";
	LocalDate date = LocalDate.of(year,month,day);
	String query="SELECT "+attribut+" FROM diary_entry WHERE date=?";
	
	try(Connection connect = DB_CONNECTOR.getConnection();
			PreparedStatement stmt = connect.prepareStatement(query)){
		stmt.setDate(1,java.sql.Date.valueOf(date));
		try(ResultSet rs = stmt.executeQuery()){
			int i=0;
			while(rs.next()){
			if(i==index) {
				ergebnis = rs.getString(attribut);
				break;
			}
			i++;
		}
		return ergebnis;
		
		}catch(SQLException e) {
			return "Fehler: "+e.getMessage();
		}
		
	}catch(SQLException e){
		return "Fehler: "+e.getMessage();
	}
}

}
