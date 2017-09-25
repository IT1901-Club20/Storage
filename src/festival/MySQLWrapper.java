package festival;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import festival.user.*;

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
	
	private String evaluateInputType(String input) {
		if (input == null) {
			return "null";
		}
		
		return "'" + input + "'";
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
	
	public void setInt(String table, String field, int value, String keyField, int key) throws SQLException {
		Statement stm = this.connection.createStatement();
		stm.executeUpdate("UPDATE `" + table + "` SET `" + field + "` = " + value + 
						  " WHERE `" + keyField + "` = " + key + ";");
		stm.close();
	}

	public void setString(String table, String field, String value, String keyField, int key) throws SQLException {
		Statement stm = this.connection.createStatement();
		stm.executeUpdate("UPDATE `" + table + "` SET `" + field + "` = '" + value +
						  "' WHERE `" + keyField + "` = " + key + ";");
		stm.close();
	}
	
	public void setRow(List<String> values, String table) throws SQLException {
		//st.executeUpdate("INSERT INTO Customers " + 
		//"VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)");
		Statement stm = this.connection.createStatement();
		
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(table);
		query.append(" VALUES (");
		
		for (int i = 0; i < values.size(); i++) {
			query.append(evaluateInputType(values.get(i)));
						
			if (i < values.size()-1) {
				query.append(", ");
			}
		}
		
		query.append(");");
		
		stm.executeUpdate(query.toString());
		stm.close();
		
		//String query = "SELECT " + field + " FROM user WHERE id = " + id + ";"; //`" + table + "` WHERE `id` = " + id + ";";
		//List<String> ret = new ArrayList<String>();
		//Statement stm = this.connection.createStatement();
		//ResultSet rs = stm.executeQuery(query);
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		long s = System.nanoTime();
		MySQLWrapper con = new MySQLWrapper("localhost", "festival", "root", "fish");
	    
		//con.setInt("user", "type", 99, "id", 770);
		/*
		for (int i = 0; i < 1000; i++) {
			System.out.println(con.getString(1, "email", "user"));
		}*/		
		
		List<String> values = new ArrayList<>();
		
		values.add(null);
		values.add("5");
		values.add("lite@kapabel.net");
		values.add("hunter3");
		values.add("2021-09-17 17:44:20");
		values.add("2023-09-17 17:44:20");
		
		con.setRow(values, "user");
		System.out.println("Ran for " + (System.nanoTime() - s)/1000000 + " milliseconds.");	
	}
}
