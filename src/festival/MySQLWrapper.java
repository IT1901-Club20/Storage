package festival;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLWrapper implements StorageWrapper{
	private String driver;
	private String server;
	private String database;
	private String db_url;
	
	private Connection connection;
	
	MySQLWrapper(String server, String database, String username, String password) throws SQLException, ClassNotFoundException {
		this.server = server;
		this.database = database;
				
		this.db_url = "jdbc:mysql://" + server + "/" + database;
		
		Class.forName("org.gjt.mm.mysql.Driver");
		this.connection = DriverManager.getConnection(this.db_url, username, password);
	}
	
	//Gamal hjelpeklasse. Ikkje kast den, den kan bli nyttig ein vakker dag!
	private ResultSet getResultSet(String query) throws SQLException {
		ResultSet rs = null;
		Statement stm = null;
		
		try {
			stm = this.connection.createStatement();
			rs = stm.executeQuery(query);
		} catch (Exception SQLException) {
			System.out.println("Um...");
		} finally {
			if (stm != null) {
				stm.close();
			}
		}
		
		return rs;
	}
	
	public String getString(int id, String field, String table) throws SQLException {
		String query = "SELECT " + field + " FROM user WHERE id = " + id + ";"; //`" + table + "` WHERE `id` = " + id + ";";
		Statement stm = this.connection.createStatement();
		ResultSet rs = stm.executeQuery(query);
		
		rs.next();
		String ret = rs.getString(field);
		stm.close();
		
		return ret;
	}
	
	public int getInt(int id, String field, String table) throws SQLException {
		String query = "SELECT " + field + " FROM user WHERE id = " + id + ";"; //`" + table + "` WHERE `id` = " + id + ";";
		Statement stm = this.connection.createStatement();
		ResultSet rs = stm.executeQuery(query);
		rs.next();
		int ret = rs.getInt(field);
		stm.close();
		
		return ret;
	}
	
	public List<Integer> getInts(int id, String field, String table) throws SQLException {
		String query = "SELECT " + field + " FROM user WHERE id = " + id + ";"; //`" + table + "` WHERE `id` = " + id + ";";
		List<Integer> ret = new ArrayList<Integer>();
		Statement stm = this.connection.createStatement();
		ResultSet rs = stm.executeQuery(query);
		
		while (rs.next()) {
			ret.add(rs.getInt(field));
		}
		
		stm.close();

		return ret;
	}	

	public List<String> getStrings(int id, String field, String table) throws SQLException {
		String query = "SELECT " + field + " FROM user WHERE id = " + id + ";"; //`" + table + "` WHERE `id` = " + id + ";";
		List<String> ret = new ArrayList<String>();
		Statement stm = this.connection.createStatement();
		ResultSet rs = stm.executeQuery(query);
		
		while (rs.next()) {
			ret.add(rs.getString(field));
		}
		
		stm.close();

		return ret;
	}	
	
	// Metoden funkar ikkje, men skal sjå cirka sånn ut i framtida. IKKJE BRUK!!!@@1!
	public ResultSet getRow(int id, String field, String table) throws SQLException {
		String query = "SELECT " + field + " FROM user WHERE id = " + id + ";"; //`" + table + "` WHERE `id` = " + id + ";";
		return this.getResultSet(query);
	}

	// Metoden funkar ikkje, men skal sjå cirka sånn ut i framtida. IKKJE BRUK!!!@@1!
	public ResultSet getRows(int id, String field, String table) throws SQLException {
		String query = "SELECT " + field + " FROM user WHERE id = " + id + ";"; //`" + table + "` WHERE `id` = " + id + ";";
		return this.getResultSet(query);
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		long s = System.nanoTime();
		MySQLWrapper con = new MySQLWrapper("localhost", "festival", "root", "fish");
	    
		for (int i = 0; i < 1000; i++) {
			System.out.println(con.getString(1, "email", "user"));
		}
			
		System.out.println("Ran for " + (System.nanoTime() - s)/1000000 + " milliseconds.");	
	}
}
