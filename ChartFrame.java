package main_package;

import javax.swing.JFrame;

public class ChartFrame extends JFrame{
	
	
	
	ChartFrame(Double[] values, String[] names, String title){

//	    this.setSize(400, 300);

	    this.getContentPane().add(new ChartPanel(values, names, title));
	
	    this.setVisible(true);
	}
	
	
	
	
	

}
