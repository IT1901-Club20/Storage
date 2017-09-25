package festival;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.awt.List;
import java.util.ArrayList;

import festival.user.Technician;
import festival.StorageWrapper;
import festival.MySQLWrapper;

public class Concert {
	protected int id;
	protected Festival festival;
	protected StorageWrapper storage;
	
	public Concert(int id, Festival festival) {
		this.id = id;
		this.festival = festival;
	}
	
	public String toString() {
		return "Hei, eg er konsert #" + this.id;
	}
	
	public ArrayList<Technician> getTechnicians() {
		ArrayList<Integer> technician_id;
		try {
			technician_id = this.storage.getInts("concert", "employment", "technician", this.id);
			ArrayList<Technician> technicians = new ArrayList<Technician>();
			
			for (int i = 0; i < technician_id.size(); i++) {
				technicians.add(this.festival.getTechnician(technician_id.get(i)));
			}
			
			return technicians;
		} catch (FileNotFoundException | SQLException e) {
			e.printStackTrace();
			return new ArrayList<Technician>();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, SQLException {
		long s = System.nanoTime();
		MySQLWrapper con = new MySQLWrapper("localhost", "festival", "root", "fish");
		
		Festival fest = new Festival(con);
		
		Concert cert = fest.getConcert(1);
		
		System.out.println(cert.getTechnicians());
		System.out.println("Ran for " + (System.nanoTime() - s)/1000000 + " milliseconds.");
	}
}
