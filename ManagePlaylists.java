package main_package;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class ManagePlaylists extends JFrame {

	JMenuBar menuBar;
	JMenu menu, menu2,submenu;
	JMenuItem menuItem,menuItem2;
	
	JTextField playlistName = null;
	JTextField startTime = null;
	
	JSpinner minDurationSec = null;
	JSpinner minDurationMin = null;
	JSpinner minDurationHrs = null;
	JSpinner maxDurationSec = null;
	JSpinner maxDurationMin = null;
	JSpinner maxDurationHrs = null;
	
	SpinnerNumberModel snm = null;
	
	JTable adsTable=new JTable();
	
	JButton previous;
	JButton add;
	JButton delete;
	JButton edit;
	JButton editAdsList;
	JButton viewAds;
	JButton next;

	List<Playlist> playlists = new ArrayList<>();
	private int index = 0;

	private void fillForm(int index) {
		if (playlists.size() == 0) {
			playlistName.setText("");
			startTime.setText("");
			minDurationSec.setValue(30);
			minDurationMin.setValue(30);
		
			maxDurationSec.setValue(30);
			maxDurationMin.setValue(30);
		
		} else {

			Playlist myPlaylist = playlists.get(index);
			playlistName.setText(myPlaylist.playlistName);
			startTime.setText(myPlaylist.startTime);
			minDurationSec.setValue(myPlaylist.getMinDuration() % 60);
			minDurationMin.setValue(myPlaylist.getMinDuration() / 60);
			
			maxDurationSec.setValue(myPlaylist.getMaxDuration() % 60);
			maxDurationMin.setValue(myPlaylist.getMaxDuration() / 60);
			
			
			if(myPlaylist.getAdverts().size()>0) {
			
			List<Advertisement> adverts=myPlaylist.getAdverts();
			
			
			List<String[]> temp = new ArrayList<>();    	

	    	
	    	for(Advertisement ad:adverts) {
	    		
	    		String[] temp2= {ad.getFirm(),ad.getDescription(),ad.getPrice(),String.valueOf(ad.getDuration()/60)+":"+String.valueOf(ad.getDuration()%60) };
	    		
	    		temp.add( temp2);
	    		
	    	}
	    	
	    	
	    	String[][] data = temp.toArray(new String[0][]);
	    	
	    	
	    	
			String[] columns= {"Firm","Description","Price","Duration"};
	    	
	    	DefaultTableModel myModel = new DefaultTableModel(data, columns);
			
	    	
	    	adsTable.setModel(myModel);
			
			}
			
			else {
				
				DefaultTableModel myModel = new DefaultTableModel();
				
				myModel.setRowCount(0);
				
				adsTable.setModel(myModel);
				
			}
			
			
		}
	}
	

	public void externalFillForm() {
		
		fillForm(index);
		
	}
	
	
	/**/

	public ManagePlaylists() {
		super("ManagePlaylists");

		buildGUI();
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void buildGUI() {

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		
		//MENU
		
		JPanel menuPane=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		menuBar = new JMenuBar();
		menuBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		menu = new JMenu("File");
		
		menuItem =new JMenuItem("Exit");
		menuItem.addActionListener(
				e ->{
					
					
					System.exit(0);
				}
				
				
				);
		
		
		menu2 = new JMenu("Help");
		
		
		
		
		menuItem2 =new JMenuItem("About");
		menuItem2.addActionListener(
				e ->{
					
					
					JOptionPane.showMessageDialog(null, "Version 1.0\nAuthor: Ripa Codrin-Andrei\nIf the ad table for the playlist is not displaying after editing, cycle through the list", 
							"About", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
				);
		menu.add(menuItem);
		menu2.add(menuItem2);
		menuBar.add(menu);
		menuBar.add(menu2);
		
		menuPane.add(menuBar);
		this.add(menuPane);
		

		// draw form
		JPanel form = new JPanel();
		form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

		
		
		//Add name input
		JPanel nameInput=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		nameInput.add(new JLabel("Name:"));
		playlistName = new JTextField(30);
		playlistName.setEnabled(true);
		nameInput.add(playlistName);
		
		form.add(nameInput);

		
		
		// place Start Time input
		JPanel startInput=new JPanel(new FlowLayout(FlowLayout.LEFT));

				
		
		startInput.add(new JLabel("Start Time:"));
		startTime = new JTextField(30);
		startTime.setEnabled(true);
		startInput.add(startTime);
		
		form.add(startInput);

		//add Minimum duration input
		JPanel minInput=new JPanel(new FlowLayout(FlowLayout.LEFT));

		
		
		//add second spinner
		minInput.add(new JLabel("Minimum Duration (sec):"));
		snm = new SpinnerNumberModel(30, 0, 60, 5);
		minDurationSec = new JSpinner(snm);
		minDurationSec.setEnabled(true);
		minInput.add(minDurationSec);

		// add minute spinner
		minInput.add(new JLabel("Minimum Duration (min):"));
		snm = new SpinnerNumberModel(30, 0, 1440, 5);
		minDurationMin = new JSpinner(snm);
		minDurationMin.setEnabled(true);
		minInput.add(minDurationMin);
		

		
		form.add(minInput);

		
		//add Maximum duration input
		
		JPanel maxInput=new JPanel(new FlowLayout(FlowLayout.LEFT));

		
		// add second spinner

		maxInput.add(new JLabel("Maximum Duration (sec):"));

		snm = new SpinnerNumberModel(30, 0, 60, 5);
		maxDurationSec = new JSpinner(snm);

		maxDurationSec.setEnabled(true);
		maxInput.add(maxDurationSec);

		// add minute spinner

		maxInput.add(new JLabel("Maximum Duration (min):"));
		snm = new SpinnerNumberModel(30, 0, 1440, 5);
		maxDurationMin = new JSpinner(snm);
		maxDurationMin.setEnabled(true);
		maxInput.add(maxDurationMin);
				
		
		
		
		form.add(maxInput);
		
		// place Scroll Pane for TABLE 

		adsTable.setAutoCreateRowSorter(true);
		form.add(new JScrollPane(adsTable));
		/**/
		
		// add form
		this.add(form);


	
		
		// draw buttons
		JPanel buttonsNavigation = new JPanel(new GridLayout(1, 4));

		previous = new JButton("Previous");
		buttonsNavigation.add(previous);
		previous.addActionListener(e -> {
			
			
			if(playlists.size()>0) {
				if (index >= 0) {
				}
				if (index == 0)
					index = playlists.size() - 1;
				else
					index--;
				fillForm(index);
				System.out.println(index);
			}
			else {
				
				JOptionPane.showMessageDialog(null, "This functionality does not work without a playlist.\nPlease add a  playlist", 
						"Warning", JOptionPane.ERROR_MESSAGE);
				
			}
			
			

		});

		add = new JButton("Add");
		buttonsNavigation.add(add);
		add.addActionListener(e -> {
			
			
				String tempPlaylistName = playlistName.getText();
	
				String tempStartTime = startTime.getText();
				Integer tempMinDuration = (Integer) minDurationSec.getValue() + 60 * (Integer) minDurationMin.getValue();
				Integer tempMaxDuration = (Integer) maxDurationSec.getValue() + 60 * (Integer) maxDurationSec.getValue();
	
				System.out.println("WORKS UNTIL HERE");
	
				if(tempPlaylistName.matches(".+")&&tempStartTime.matches("[0-1][0-9]:[0-5][0-9]|[2][0-3]:[0-5][0-9]")) {
					playlists.add(new Playlist(tempPlaylistName, tempStartTime, tempMinDuration, tempMaxDuration));
					System.out.println(playlists.size());
					index = playlists.size() - 1;
					fillForm(index);
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Input not valid\nName must be non-empty\nTime must be hh:mm", 
							"Warning", JOptionPane.ERROR_MESSAGE);
					
				}

		});

		delete = new JButton("Delete");
		buttonsNavigation.add(delete);
		delete.addActionListener(e -> {

			if(playlists.size()>0) {
			
				playlists.remove(index);
				if (playlists.size() != 0) {
					index--;
	
				} else {
	
					index = 0;
	
				}
				fillForm(index);
			}
			else {
				JOptionPane.showMessageDialog(null, "This functionality does not work without a playlist.\nPlease add a  playlist", 
						"Warning", JOptionPane.ERROR_MESSAGE);
				
				
			}
			

		});

		edit = new JButton("Edit");
		buttonsNavigation.add(edit);
		edit.addActionListener(e -> {
			
			if(playlists.size()>0) {

				String tempPlaylistName = playlistName.getText();
				String tempStartTime = startTime.getText();
				Integer tempMinDuration = (Integer) minDurationSec.getValue() + 60 * (Integer) minDurationMin.getValue();
				Integer tempMaxDuration = (Integer) maxDurationSec.getValue() + 60 * (Integer) maxDurationSec.getValue();
				
				
				if(tempPlaylistName.matches(".+")&&tempStartTime.matches("[0-1][0-9]:[0-5][0-9]|[2][0-3]:[0-5][0-9]")) {
					
					List<Advertisement> temp = new ArrayList<>();
					temp=playlists.get(index).getAdverts();
					playlists.set(index, new Playlist(tempPlaylistName, tempStartTime, tempMinDuration, tempMaxDuration));
					playlists.get(index).setAdverts(temp);
					
					fillForm(index);
				}
				else {
					JOptionPane.showMessageDialog(null, "Input not valid\nName must be non-empty\nTime must be hh:mm", 
							"Warning", JOptionPane.ERROR_MESSAGE);
					
				}
				
				
			}
			else {
				
				JOptionPane.showMessageDialog(null, "This functionality does not work without a playlist.\nPlease add a  playlist", 
						"Warning", JOptionPane.ERROR_MESSAGE);
				
			}
		});
		
		
		editAdsList = new JButton("Edit Ad List");
		buttonsNavigation.add(editAdsList);
		editAdsList.addActionListener(e -> {
			
						
			
			if(playlists.size()>0) {
				new ManageAdverts(playlists.get(index).getAdverts(),this);
				
				
			}
			else {
				
				JOptionPane.showMessageDialog(null, "This functionality does not work without a playlist.\nPlease add a  playlist", 
						"Warning", JOptionPane.ERROR_MESSAGE);
				
			}
			
			
		});
		
		
		viewAds = new JButton("View Ad Price Diagram");
		buttonsNavigation.add(viewAds);
		viewAds.addActionListener(e -> {
			
			if(playlists.size()>0 && playlists.get(index).getAdverts().size()>0) {
			
				
				TreeMap<String,Double> priceAndOccurence=new TreeMap<String,Double>();
				
				List<String> chartPrices=new ArrayList<>();
				List<Double> priceOccurence=new ArrayList<>();
				
				for(Advertisement ad:playlists.get(index).getAdverts()) {
					
					if(priceAndOccurence.get(ad.getPrice())==null) {
						priceAndOccurence.put(ad.getPrice(),(double) 1);						
						
					}
					else {
						
						priceAndOccurence.put(ad.getPrice(),priceAndOccurence.get(ad.getPrice())+1);
						
					}
					
					
				}
				
				for (Entry<String, Double> entry : priceAndOccurence.entrySet())
				{
					chartPrices.add(entry.getKey());
					priceOccurence.add(entry.getValue());
					
				}

				
				
				
				Double values[]= priceOccurence.toArray(new Double[0]);
				String labels[] = chartPrices.toArray(new String[0]);
				new ChartFrame(values,labels,"Prices by Count");
			
			}
			else {
				
				JOptionPane.showMessageDialog(null, "This functionality does not work without a playlist and ads to that playlist.\nPlease add accordingly", 
						"Warning", JOptionPane.ERROR_MESSAGE);
				
			}
			
			
		});
		
		
		
		
		

		next = new JButton("Next");
		buttonsNavigation.add(next);
		next.addActionListener(e -> {
			if(playlists.size()>0) {
				if (index >= 0) {
					if (index == playlists.size() - 1)
						index = 0;
					else
						index++;
					fillForm(index);
				}
				System.out.println(index);
			}
			else {
				
				JOptionPane.showMessageDialog(null, "This functionality does not work without a playlist.\nPlease add a  playlist", 
						"Warning", JOptionPane.ERROR_MESSAGE);
				
			}

		});

		this.add(buttonsNavigation);

	}

}
