package diary;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDo {
public static void insertTask(String story){
	
	String query = "INSERT INTO diary_entry(date,story) VALUES(?,?)";
	
	try(Connection connect = DB_CONNECTOR.getConnection();
			PreparedStatement stmt = connect.prepareStatement(query)){
				stmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
				stmt.setString(2,story);
				stmt.execute();
	}catch(SQLException e) {
		System.err.println("Fehler: "+e.getMessage());
		
	}
}
public static List<String> pastStory(int year,int month,int day) {
	List<String> list = new ArrayList<>();
	String query = "SELECT story FROM diary_entry WHERE date=?";
	try(Connection connect = DB_CONNECTOR.getConnection();
			PreparedStatement stmt = connect.prepareStatement(query)){
		stmt.setDate(1,java.sql.Date.valueOf(LocalDate.of(year,month,day)));
		try(ResultSet rs =stmt.executeQuery()){
			while(true) {
				if(rs.next()) {
					list.add(rs.getString("story"));
				}
				list.add("Keinen weiteren Werte !");
				break;
			}
			return list;
			
		}catch(SQLException e) {
			list.add("Fehler: "+e.getMessage());
			return list;
		}
	}catch(SQLException e) {
		list.add("Fehler:" +e.getMessage());
		return list;
	}
}

}
