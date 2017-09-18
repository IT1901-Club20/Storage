package festival;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
	private String driver;
	private String server;
	private String database;
	private String db_url;
	
	private Connection connection;
	
	Connector(String server, String database, String username, String password) throws SQLException, ClassNotFoundException {
		this.server = server;
		this.database = database;
				
		this.db_url = "jdbc:mysql://" + server + "/" + database;
		
		Class.forName("org.gjt.mm.mysql.Driver");
		this.connection = DriverManager.getConnection(this.db_url, username, password);
	}
	
	public Object getItem(int id, String field, String table) throws SQLException {
		String query = "SELECT " + field + " FROM user WHERE id = " + id + ";"; //`" + table + "` WHERE `id` = " + id + ";";
		
		Object ret = null;
		Statement stm = null;
		
		try {
			stm = this.connection.createStatement();
			ResultSet rs = stm.executeQuery(query);
			
			rs.next();
			ret = rs.getObject(field);
			System.out.println(rs.getObject("email"));
		} catch (Exception SQLException) {
			System.out.println("Um...");
		} finally {
			if (stm != null) {
				stm.close();
			}
		}
		
		return ret;
	}
	
	/*
	public String getInt(int id, String field, String table) {
		String query = "SELECT `" + field + "` FROM `" + table + "` WHERE `id` = " + id + ";";
		
	}*/

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
			Connector con = new Connector("localhost", "festival", "root", "fish");
	    	
			System.out.println(con.getItem(1, "email", "user"));
	  }
}
