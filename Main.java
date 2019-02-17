package main_package;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Main {
	
	public static void println(String something) {
		
		System.out.println(something);
		
	}
	
	public static void println() {
		
		System.out.println();
		
	}
	
	public static void main(String args[]) {
		

		
		 

		
		SwingUtilities.invokeLater(new Runnable() {
			
			
			
			
			@Override
			public void run() {
				
				

				
				
				new ManagePlaylists();				
				
				

			}
		});
		/**/
		
		
		
	}

}
