package team15.GUI;

/**
 *
 * @author team15
 */

//Imports
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import team15.UserOjects.Location;
import team15.UserOjects.User;

public class LocationsDialog extends JDialog{
    private final JList locations;
    private final DefaultListModel<Location> model;
    private final JLabel error, cur;
    private final User user;
    private JComboBox<Location> cmbLocation;
    private JComboBox<String> country;

    public LocationsDialog(User u, final TreeMap<String, ArrayList<Location>> loc){
        super(new JFrame(), "Location List", true);
        
        this.user = u;
        
        if(user.getLocations().isEmpty()){
            
        }
        
        //Set the list parameters
        locations = new JList();
        locations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        locations.setVisibleRowCount(-1);
        model = new DefaultListModel();
        locations.setModel(model);
        
        //Add all the locations to the listbox
        for(Location l: user.getLocations()){
            model.addElement(l);
        }
        
        //Set the size of the window
        JPanel panel = new JPanel();
        this.setResizable(false);
        this.getContentPane().add(panel);
        this.setSize(500, 450);
        this.setLocation(200, 200);
        
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        
        //Set the list scrollers parameters
        JScrollPane scroller = new JScrollPane(locations);
        scroller.setPreferredSize(new Dimension(460, 200));
        layout.putConstraint(SpringLayout.WEST, scroller, 20, 
                                                SpringLayout.WEST, panel);
        panel.add(scroller);
        
        //Current location
        cur = new JLabel("Current location: " + user.getCurrentLocation());
        layout.putConstraint(SpringLayout.WEST, cur, 20, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cur, 20, 
                                                SpringLayout.SOUTH, scroller);
        panel.add(cur);
        
        //Add buttons
        JButton set = new JButton("Change Current");
        JButton add = new JButton("Add Location");
        JButton remove = new JButton("Remove Location");
        
        layout.putConstraint(SpringLayout.WEST, add, 20, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, add, 20, 
                                                SpringLayout.SOUTH, cur);
        panel.add(add);
        
        layout.putConstraint(SpringLayout.WEST, remove, 20, 
                                                SpringLayout.EAST, add);
        layout.putConstraint(SpringLayout.NORTH, remove, 20, 
                                                SpringLayout.SOUTH, cur);
        panel.add(set);
        
        layout.putConstraint(SpringLayout.WEST, set, 20, 
                                                SpringLayout.EAST, remove);
        layout.putConstraint(SpringLayout.NORTH, set, 20, 
                                                SpringLayout.SOUTH, cur);
        panel.add(remove);
        
        //Combo box labels
        JLabel lblCountry = new JLabel("Country: ");
        layout.putConstraint(SpringLayout.WEST, lblCountry, 20, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, lblCountry, 30, 
                                                SpringLayout.SOUTH, add);
        panel.add(lblCountry);
        
        JLabel lblLoc = new JLabel("Location: ");
        layout.putConstraint(SpringLayout.WEST, lblLoc, 20, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, lblLoc, 20, 
                                                SpringLayout.SOUTH, lblCountry);
        panel.add(lblLoc);
        
        //combo boxes
        country = new JComboBox(loc.keySet().toArray());
        cmbLocation = new JComboBox();
        
        layout.putConstraint(SpringLayout.WEST, country, 90, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, country, 24, 
                                                SpringLayout.SOUTH, add);
        
        layout.putConstraint(SpringLayout.WEST, cmbLocation, 90, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cmbLocation, 20, 
                                                SpringLayout.SOUTH, lblCountry);
        panel.add(country);
        panel.add(cmbLocation);
        
        country.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                DefaultComboBoxModel<Location> locModel = new 
                                        DefaultComboBoxModel(loc.get((String)country.getSelectedItem()).toArray());
                cmbLocation.setModel(locModel);
            }    
        });
        
        country.setSelectedItem("CA");
        
        //Button action listeners
        remove.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                remove();
            }    
        });
        
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                add();
            }    
        });
        
        set.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                set();
            }    
        });
              
        error = new JLabel();
        layout.putConstraint(SpringLayout.WEST, error, 20, 
                                                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, error, 20, 
                                                SpringLayout.SOUTH, lblLoc);
        panel.add(error);
        
        this.setVisible(true);
    }

    private void set(){
        int i = locations.getSelectedIndex();
        
        if(i == -1){
            error.setText("Please select a location from the list.");
            return;
        }
        
        Location loc = model.get(i);
        
        if(loc.equals(user.getCurrentLocation())){
            error.setText("Error: This is already your current location.");
            return;
        }
        
        Location oldLocation = user.getCurrentLocation();
        if(!user.setCurrentLoc(loc)){
            error.setText("Error: Could not find location in user file.");
            return;
        }
        
        try{
            user.saveUser();
        } catch(Exception ex){
            user.setCurrentLoc(oldLocation);
            error.setText("Error saving user to the local drive");
            return;
        }
        
        cur.setText("Current location: " + user.getCurrentLocation());
        error.setText("");
    }
    
    private void add(){
        Location l = cmbLocation.getItemAt(cmbLocation.getSelectedIndex());
        
        if(l == null){
            error.setText("Error: Please select a location to add.");
        }
        
        for(Location loc: user.getLocations()){
            if(loc.equals(l)){
                error.setText("Error: that location already exists.");
                return;
            }
        }

        user.addLocation(l);

        try{
            user.saveUser();
        } catch(Exception ex){
            user.removeLocation(l);
            error.setText("Error saving user to the local drive");
        }
        
        model.addElement(l);
        error.setText("");
        if(user.getLocations().size() == 1){
            user.setCurrentLoc(l);
            cur.setText("Current location: " + user.getCurrentLocation());
        }
    }
    
    private void remove(){
        int i = locations.getSelectedIndex();
        
        if(i == -1){
            error.setText("Please select a location from the list.");
            return;
        }
        
        Location loc = model.get(i);
        if(loc == user.getCurrentLocation()){
            error.setText("You can not delete your current location.");
            return;
        }
        
        user.removeLocation(loc);
        
        try{
            user.saveUser();
        } catch(Exception ex){
            user.addLocation(i, loc);
            error.setText("Error saving user to local drive.");
            return;
        }
        
        model.remove(i);
        error.setText("");
    }
}
