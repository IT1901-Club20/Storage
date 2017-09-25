package festival;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import festival.user.Technician;

public class Festival {
	private StorageWrapper storage;
	
	public Festival(StorageWrapper storage) {
		this.storage = storage;
	}
	
	public StorageWrapper getStorage() {
		return this.storage;
	}
	
	//TODO Implementere ein form for caching av get-metodene for klassene.
	public Concert getConcert(int id) {
		return new Concert(id, this);
	}

	public Technician getTechnician(int id) {
		return new Technician(id, this);
	}

	public static void main(String[] args) throws FileNotFoundException, SQLException, ClassNotFoundException {
		long s = System.nanoTime();
		MySQLWrapper con = new MySQLWrapper("localhost", "festival", "root", "fish");
		
		Festival fest = new Festival(con);
		
		Technician tech = fest.getTechnician(1);
		System.out.println(tech.getConcerts().get(0));
			
		
		System.out.println("Ran for " + (System.nanoTime() - s)/1000000 + " milliseconds.");
	}
}
