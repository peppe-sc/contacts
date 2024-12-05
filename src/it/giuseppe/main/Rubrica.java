package it.giuseppe.main;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

public class Rubrica {

	public static void main(String[] args) {
		
		MainScreen m = new MainScreen();
		
		
		
	}
	@FunctionalInterface
	public interface Operation {
	    int apply(int a, int b);
	}

	private static void openNewWindow(String title) {
        JFrame newWindow = new JFrame(title);
        newWindow.setSize(300, 200);
        newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newWindow.add(new JLabel("Welcome to " + title, SwingConstants.CENTER));
        newWindow.setVisible(true);
    }

}
