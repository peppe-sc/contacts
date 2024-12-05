package it.giuseppe.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class MainScreen {
	
	@FunctionalInterface
	public interface Operation {
	    void updateModel( Person p);
	}
	
	PeopleList p;
	List<Person> list;
	JTable contactsTable;
	DefaultTableModel tableModel;
	
	public MainScreen() {
		p = new PeopleList();
		
		list = p.getPeopleList();
		

        //Frame creation
        JFrame frame = new JFrame("Contacts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //Table model creation
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Surname");
        //tableModel.addColumn("Address");
        tableModel.addColumn("Phone");
        //tableModel.addColumn("Age");

        //Add people to the table
        for (Person person : list) {
            tableModel.addRow(new Object[]{person.getName(), person.getSurname(),  person.getPhone()});
        }
        
        //Create table and set the dynamic size
        contactsTable = new JTable(tableModel);
        contactsTable.setFillsViewportHeight(true); 
        contactsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
        
        contactsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //Create a scroll pane and add the table to the pane
        JScrollPane scrollPane = new JScrollPane(contactsTable);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        //Create toolbar with buttons
        JToolBar toolBar = new JToolBar();
        toolBar.setLayout(new GridLayout(1,3));
        
        JButton addContactBtn = new JButton("New Contact");
        JButton editContactBtn = new JButton("Edit Contact");
        JButton deleteContactBtn = new JButton("Delete Contact");

        // Add action listeners to the buttons
        addContactBtn.addActionListener(e -> {
        	
        	if(contactsTable.getSelectedRow()==-1) {
        		Editor newPane = new Editor(null , "New Contact", p, person->{
        			tableModel.addRow(new Object[]{person.getName(), person.getSurname(),  person.getPhone()});
        			contactsTable.setModel(tableModel);
        			//list.add(person);
        		});
        		newPane.show();
        	}else {
        		//TO-DO throw the error
        	}
        	
        	//Editor newPane = new Editor(contactsTable.getSelectedRow()!=-1? list.get(contactsTable.getSelectedRow()):null , "New Contact");
        	
        });
        editContactBtn.addActionListener(e -> {
        	if(contactsTable.getSelectedRow()!=-1) {
        		Editor newPane = new Editor(list.get(contactsTable.getSelectedRow()) , "Edit Contact", p, person ->{});
        		newPane.show();
        	}else {
        		//TO-DO throw the error
        	}
        });
        //deleteContactBtn.addActionListener(e -> openNewWindow("Window 3"));

        // Add buttons to the toolbar
        toolBar.add(addContactBtn);
        toolBar.add(editContactBtn);
        toolBar.add(deleteContactBtn);
        
        // Add toolbar to the frame
        frame.add(toolBar, BorderLayout.PAGE_END);
        
        
        
        //Adapt the frame to the subcomponents
        frame.pack();

        //Show the frame
        frame.setVisible(true);
	}
	
}
