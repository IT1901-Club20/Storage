package festival.user;

import festival.Festival;
import festival.StorageWrapper;

public class User {
	protected int id;
	protected Festival festival;
	protected String table;
	protected StorageWrapper storage;
	
	User(int id, Festival festival) {
		this.id = id;
		this.festival = festival;
		this.storage = festival.getStorage();
	}
	
	public int getId() {
		return this.id;
	}
	
	public String toString() {
		return this.getClass() + " " + this.id;
	}
}
