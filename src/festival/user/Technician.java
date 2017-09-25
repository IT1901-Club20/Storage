package festival.user;

import festival.StorageWrapper;
import festival.MySQLWrapper;

import java.awt.List;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import festival.Concert;
import festival.Festival;


public class Technician extends User {
	public Technician(int id, Festival festival) {
		super(id, festival);
	}
	
	public ArrayList<Concert> getConcerts() {
		try {
			ArrayList<Integer> concert_id = this.storage.getInts("concert", "employment", "technician", this.id);
			ArrayList<Concert> concerts = new ArrayList<Concert>();
			
			for (int i = 0; i < concert_id.size(); i++) {
				concerts.add(this.festival.getConcert(concert_id.get(i)));
			}
			
			return concerts;
		} catch (FileNotFoundException | SQLException e) {
			e.printStackTrace();
			return new ArrayList<Concert>();
		}
	}

}
