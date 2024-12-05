package it.giuseppe.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	
	PeopleList peopleListWrapper;
	List<Person> list;
	JTable contactsTable;
	DefaultTableModel tableModel;
	
	public MainScreen() {
		peopleListWrapper = new PeopleList();
		
		list = peopleListWrapper.getPeopleList();
		

        //Frame creation
        JFrame frame = new JFrame("Contacts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //Table model creation
        tableModel = new DefaultTableModel() {
        	@Override
            public boolean isCellEditable(int row, int column) {
                // Return false to make the table non-editable
                return false;
            }
        };
        tableModel.addColumn("Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("Phone");

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
        	
        	
        	Editor newPane = new Editor(null , "New Contact", peopleListWrapper, person->{
        		tableModel.addRow(new Object[]{person.getName(), person.getSurname(),  person.getPhone()});
        		contactsTable.setModel(tableModel);
        		
        	});
        	newPane.show();
        	
        	
        	
        });
        editContactBtn.addActionListener(e -> {
        	if(contactsTable.getSelectedRow()!=-1) {
        		Editor newPane = new Editor(list.get(contactsTable.getSelectedRow()) , "Edit Contact", peopleListWrapper, person ->{
        			refreshTable();
        		});
        		newPane.show();
        	}else {
        		JOptionPane.showMessageDialog(frame, "Select a contact to edit", "Error",JOptionPane.ERROR_MESSAGE);
        	}
        });
        deleteContactBtn.addActionListener(e -> {
        	if(contactsTable.getSelectedRow()!=-1) {
        		
        		Person tmp = list.get(contactsTable.getSelectedRow());
        		
        		int choice = JOptionPane.showConfirmDialog(frame, "Do you want to Delete " + tmp.getName() + " " + tmp.getSurname() +"?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        		
        		if(choice == JOptionPane.YES_OPTION) {
        			peopleListWrapper.delete(list.get(contactsTable.getSelectedRow()));
            		refreshTable();
        		}
        		
        		
        	}else {
        		JOptionPane.showMessageDialog(frame, "Select a contact to delete", "Error",JOptionPane.ERROR_MESSAGE);
        	}
        	
        });

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
	
	private void refreshTable() {
		
		tableModel = new DefaultTableModel() {
			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
		};
        tableModel.addColumn("Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("Phone");
        

        //Add people to the table
        for (Person p : list) {
            tableModel.addRow(new Object[]{p.getName(), p.getSurname(),  p.getPhone()});
        }
        contactsTable.setModel(tableModel);
	}
	
}
