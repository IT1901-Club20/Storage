package festival;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface StorageWrapper {
	int getInt(int key, String field, String table) throws SQLException, FileNotFoundException;
	String getString(int key, String field, String table) throws SQLException, FileNotFoundException;
	
	List<Integer> getInts(int key, String field, String table) throws SQLException, FileNotFoundException;
	List<String> getStrings(int key, String field, String table) throws SQLException, FileNotFoundException;
	
	ResultSet getRow(int key, String keyField, String table) throws SQLException, FileNotFoundException;
	ResultSet getRows(int key, String keyField, String table) throws SQLException, FileNotFoundException;
}
