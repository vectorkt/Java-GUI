package main_package;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class ManageAdverts extends JFrame {

	JTextField firm = null;
	JTextField description = null;
	JSpinner durationSec = null;
	JSpinner durationMin = null;
	SpinnerNumberModel snm = null;
	JTextField price = null;

	JButton previous;
	JButton add;
	JButton delete;
	JButton edit;
	JButton next;

	List<Advertisement> ads;
	private int index = 0;

	private void fillForm(int index) {
		if (ads.size() == 0) {
			firm.setText("");
			description.setText("");
			price.setText("");
			durationSec.setValue(30);
			durationMin.setValue(30);
		} else {
			Advertisement myAd = ads.get(index);
			firm.setText(myAd.getFirm());
			description.setText(myAd.getDescription());
			price.setText(myAd.getPrice());
			durationSec.setValue(myAd.getDuration() % 60);
			durationMin.setValue(myAd.getDuration() / 60);

		}
	}

	
	
	
	
	
	public ManageAdverts(List<Advertisement> ads,ManagePlaylists ref) {
		
		super("ManageAdverts");
		

		this.addWindowListener((WindowListener) new WindowListener() {			

			@Override
			public void windowClosing(WindowEvent e) {
				//TO BE CALLED WHEN ManageAdverts IS CLOSING
				ref.externalFillForm();

			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			
		});
		
		
		this.ads=ads;
		
		
		buildGUI();
		this.setVisible(true);
		this.pack();
		
		

	}
	
	public ManageAdverts() {
		super("ManageAdverts");
			
		
		
		buildGUI();
		this.setVisible(true);
		this.pack();
		
		

	}
	

	private void buildGUI() {

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		// draw form
		JPanel form = new JPanel();
		form.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// place firm test field
		gbc.insets = new Insets(5, 5, 10, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		form.add(new JLabel("Firm:"), gbc);

		firm = new JTextField(30);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		firm.setEnabled(true);
		form.add(firm, gbc);

		// place description text field

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.EAST;
		form.add(new JLabel("Description:"), gbc);

		description = new JTextField(30);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		description.setEnabled(true);
		form.add(description, gbc);

		// place price textfield
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.EAST;
		form.add(new JLabel("Price:"), gbc);

		price = new JTextField(30);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		price.setEnabled(true);
		form.add(price, gbc);

		// add second spinner
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		// gbc.anchor = GridBagConstraints.EAST;
		form.add(new JLabel("Duration (sec):"), gbc);

		snm = new SpinnerNumberModel(30, 0, 60, 5);
		durationSec = new JSpinner(snm);
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		// gbc.anchor = GridBagConstraints.WEST;
		durationSec.setEnabled(true);
		form.add(durationSec, gbc);

		// add minute spinner
		gbc.gridx = 1;
		gbc.gridy = 3;
		// gbc.gridwidth=1;
		gbc.anchor = GridBagConstraints.EAST;
		form.add(new JLabel("Duration (min):"), gbc);

		snm = new SpinnerNumberModel(30, 0, 60, 5);
		durationMin = new JSpinner(snm);
		gbc.gridx = 2;
		gbc.anchor = GridBagConstraints.WEST;
		durationMin.setEnabled(true);
		form.add(durationMin, gbc);

		// add form
		this.add(form);

		// draw buttons
		JPanel buttonsNavigation = new JPanel(new GridLayout(1, 4));

		previous = new JButton("Previous");
		buttonsNavigation.add(previous);
		previous.addActionListener(e -> {

			
			if(ads.size()>0) {
				if (index >= 0) {
				}
				if (index == 0)
					index = ads.size() - 1;
				else
					index--;
				fillForm(index);
				System.out.println(index);
			}
			else {
				
				JOptionPane.showMessageDialog(null, "This functionality does not work without any ads.\nPlease add an ad", 
						"Warning", JOptionPane.ERROR_MESSAGE);
				
			}

		});

		add = new JButton("Add");
		buttonsNavigation.add(add);
		add.addActionListener(e ->

		{
			
			String tempFirm = firm.getText();
			String tempDescription = description.getText();
			String tempPrice = price.getText();
			Integer tempDuration = (Integer) durationSec.getValue() + 60 * (Integer) durationMin.getValue();
			if(tempFirm.matches(".+") && tempDescription.matches(".+")&&tempPrice.matches("[0-9]+.[0-9]+\\$|[0-9]+\\$")) {
				ads.add(new Advertisement(tempFirm, tempDescription, tempPrice, tempDuration));
				System.out.println(ads.size());
				index=ads.size()-1;
			}
			else {
				
				JOptionPane.showMessageDialog(null, "Input not valid\nFirm and Description must be non-empty\nPrice must be 4$ or 35.54/$", 
						"Warning", JOptionPane.ERROR_MESSAGE);
				
			}

		}

		);

		delete = new JButton("Delete");
		buttonsNavigation.add(delete);
		delete.addActionListener(e -> {
			
			
			if(ads.size()>0) {
				ads.remove(index);
				if(ads.size()!=0) {
					index--;
					
					
					
				}
				else {
					
					index=0;
					
				}
				fillForm(index);
			}
			else {
				
				JOptionPane.showMessageDialog(null, "This functionality does not work without any ads.\nPlease add an ad", 
						"Warning", JOptionPane.ERROR_MESSAGE);
				
			}
			
		});

		edit = new JButton("Edit");
		buttonsNavigation.add(edit);
		edit.addActionListener(e -> {
			if(ads.size()>0) {

				String tempFirm = firm.getText();
				String tempDescription = description.getText();
				String tempPrice = price.getText();
				Integer tempDuration = (Integer) durationSec.getValue() + 60 * (Integer) durationMin.getValue();

				if(tempFirm.matches(".+") && tempDescription.matches(".+")&&tempPrice.matches("[0-9]+.[0-9]+\\$|[0-9]+\\$")) {
					System.out.println(ads.set(index, new Advertisement(tempFirm, tempDescription, tempPrice, tempDuration)));
				}
				else {
					
					JOptionPane.showMessageDialog(null, "This functionality does not work without any ads.\nPlease add an ad", 
							"Warning", JOptionPane.ERROR_MESSAGE);
					
					
					
				}
				
				
			}
			else {
				
				JOptionPane.showMessageDialog(null, "This functionality does not work without any ads.\nPlease add an ad", 
						"Warning", JOptionPane.ERROR_MESSAGE);
				
			}
		});

		next = new JButton("Next");
		buttonsNavigation.add(next);
		next.addActionListener(e -> {

			if(ads.size()>0) {
				if (index >= 0) {
					if (index == ads.size() - 1)
						index = 0;
					else
						index++;
					fillForm(index);
				}
				System.out.println(index);
			}
			else {
				
				JOptionPane.showMessageDialog(null, "This functionality does not work without any ads.\nPlease add an ad", 
						"Warning", JOptionPane.ERROR_MESSAGE);
				
			}
		});

		this.add(buttonsNavigation);
		
		JPanel closing = new JPanel(new FlowLayout());
		JButton close =new JButton("Finish Editing");
		close.addActionListener(e->{this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));});
		closing.add(close);
		
		this.add(closing);
		
		

	}

}
