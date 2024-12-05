package it.giuseppe.main;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import it.giuseppe.main.MainScreen.Operation;

public class Editor {
	
	Person person;
	
	PeopleList peopleList;
	
	JFrame frame;
	
	Operation operation;
	
	public Editor(Person person, String purpose, PeopleList peopleList, Operation operation) {
		
		//Assign person and purpose, TO-DO if purpose is new contact person will be null
		
		this.person = person;
		this.peopleList = peopleList;
		this.operation = operation;
		//Create the new window
		buildWindow(purpose);
		
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	private void save(Person p) {
		
		peopleList.add(p);
		
		operation.updateModel(p);
		
	}
	
	private void cancel() {
		frame.setVisible(false);
	}
	
	private void buildWindow(String purpose) {
		
		//Create the new frame with a form layout and a tollbar
		frame = new JFrame(purpose);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel formPanel = new JPanel();
        
        formPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name Label and Text Field
        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(20);
        nameField.setText(person != null? person.getName():"");
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(nameField, gbc);

        // Surname Label and Text Field
        JLabel surnameLabel = new JLabel("Surname:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(surnameLabel, gbc);

        JTextField surnameField = new JTextField(20);
        surnameField.setText(person != null? person.getSurname():"");
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(surnameField, gbc);

        // Phone Label and Text Field
        JLabel phoneLabel = new JLabel("Phone:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(phoneLabel, gbc);

        JTextField phoneField = new JTextField(20);
        phoneField.setText(person != null? person.getPhone():"");
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(phoneField, gbc);
        
        // Address Label and Text Field
        JLabel addressLabel = new JLabel("Address:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(addressLabel, gbc);

        JTextField addressField = new JTextField(20);
        addressField.setText(person != null? person.getAddress():"");
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(addressField, gbc);
        
        // Age Label and Text Field
        JLabel ageLabel = new JLabel("Age:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(ageLabel, gbc);

        JTextField ageField = new JTextField(20);
        ageField.setText(person != null? ""+person.getAge():"");
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(ageField, gbc);
        
        
        //Toolbar
        JToolBar toolBar = new JToolBar();
        toolBar.setLayout(new GridLayout(1,3));
        
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");
        
        
        saveBtn.addActionListener(e->{
        	if (person == null) {
        		save(new Person(nameField.getText(),surnameField.getText(),addressField.getText(),phoneField.getText(),Integer.parseInt(ageField.getText())));
        	}
        });
        
        cancelBtn.addActionListener(e->{
        	cancel();
        });
        
        toolBar.add(saveBtn);
        toolBar.add(cancelBtn);
        
        // Add toolbar and form to the frame
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(toolBar, BorderLayout.PAGE_END);
        
        
        frame.pack();
		
	}
	
}
