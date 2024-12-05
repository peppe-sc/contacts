package it.giuseppe.main;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public void save() {
		
	}
	
	public void delete(Person p) {
		peopleList.remove(peopleList.indexOf(p));
		
	}
	
	public void add(Person person) {
		peopleList.add(person);
		db.write(peopleList);
	}
	
	public void edit(Person person, String name, String surname, String address, String age, String phone) {
		
	}
	
	
	
}
