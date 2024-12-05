package it.giuseppe.main;

public class Person {
	private String name;
	private String surname;
	private String address;
	private String phone;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Person(String name,String surname, String address, String phone, int age) {
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phone = phone;
		this.age = age;
	}
	
	public Person(String name,String surname, String address) {
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phone = phone;
		this.age = age;
	}
	
	@Override
	public boolean equals(Object person) {
		
		if(person == this) {
			return true;
		}
		
		if (!(person instanceof Person)) {
            return false;
        }
		
		Person tmp = (Person) person;
		
		
		return tmp.getName() == this.name && tmp.getSurname() == this.surname && tmp.getAddress() == this.address && tmp.getPhone() == this.phone && tmp.getAge() == this.age;
		
	}
	
}
