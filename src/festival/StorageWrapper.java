package festival;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StorageWrapper {
	//get*
	int getInt(int key, String field, String table) throws SQLException, FileNotFoundException;
	String getString(int key, String field, String table) throws SQLException, FileNotFoundException;
	
	ArrayList<Integer> getInts(String field, String table, String keyField, int key) throws SQLException, FileNotFoundException;
	ArrayList<String> getStrings(String field, String table, String keyField, int key) throws SQLException, FileNotFoundException;
	
	ResultSet getRow(int key, String field, String table) throws SQLException, FileNotFoundException;
	ResultSet getRows(int key, String field, String table) throws SQLException, FileNotFoundException;
	
	//set*
	void setInt(String table, String field, int value, String keyField, int key) throws SQLException, FileNotFoundException;
	void setString(String table, String field, String value, String keyField, int key) throws SQLException, FileNotFoundException;
	void setRow(List<String> values, String table) throws SQLException, FileNotFoundException;
}