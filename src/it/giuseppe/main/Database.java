package it.giuseppe.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Database {
	
	private final String path = "db.txt";
	
	public List<Person> getPeopleList(){
		
		File file = new File(path);
		FileReader fileReader;
		
				 
		List<Person> result = new LinkedList<>();
		
		try {
			//Create new file if not exist
			file.createNewFile();
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader); 
			
			String line;
			//read lines and split by ';', then create a Person and add to the list
			while((line = bufferedReader.readLine()) != null) {
				String[] lineSplit = line.split(";");
				result.add(new Person(lineSplit[0],lineSplit[1],lineSplit[2],lineSplit[3],Integer.parseInt(lineSplit[4])));
			}
			
			bufferedReader.close();
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		
		
		
		return result;
		
	}
	
	public void write(List<Person> list) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            // Write each line to the file
            for (Person person : list) {
            	String line = ""+person.getName()+";"+person.getSurname()+";"+person.getAddress()+";"+person.getPhone()+";"+person.getAge();
                writer.write(line);
                writer.newLine(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
