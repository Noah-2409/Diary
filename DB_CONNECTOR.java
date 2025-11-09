package diary;
import java.sql.*;
public class DB_CONNECTOR{

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/DiaryTest";
    
   
    private static final String USER = "postgres";
    private static final String PASS = "postgres";
    
    public static Connection getConnection() throws SQLException {
    	 System.out.println("Versuche, eine Verbindung herzustellen...");
    	try{
    		Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
    		System.out.println("Verbindung zur Datenbank erfolgreich hergestellt!");
                return connection;
    	}catch(SQLException e) {
    		System.err.println("Fehler: "+e.getMessage());
    		throw e;
    	}
               
    }
}

