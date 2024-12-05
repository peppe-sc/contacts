package it.giuseppe.main;

import java.util.List;

public class PeopleList {
	
	private List<Person> peopleList;
	Database db;
	
	public PeopleList() {
		db = new Database();
		peopleList = db.getPeopleList();
	}

	public List<Person> getPeopleList() {
		return peopleList;
	}
	
	
	
}
