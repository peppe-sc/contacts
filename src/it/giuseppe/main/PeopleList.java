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
		db.write(peopleList);
	}
	
	public void add(Person person) {
		peopleList.add(person);
		db.write(peopleList);
	}
	
	public void edit(Person person, String name, String surname, String address, int age, String phone) {
		person.setName(name);
		person.setSurname(surname);
		person.setAddress(address);
		person.setPhone(phone);
		person.setAge(age);
		
		db.write(peopleList);
		
	}
	
	
	
}
