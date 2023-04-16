import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class ChoiceGui extends JFrame {

	private JCheckBox oneone, onetwo, onethree;
	private JCheckBox twoone, twotwo, twothree, twofour, twofive, twosix, twoseven, twoeight;
	private JCheckBox threeone, threetwo, threethree, threefour, threefive;
	private JCheckBox fourone, fourtwo, fourthree, fourfour, fourfive;
	
	private JTextField parameter_last_name = new JTextField("Enter the last name here");
	private JTextField parameter_first_name = new JTextField("Enter the first name here");
	private JTextField parameter_min_date = new JTextField("Enter the min date here");
	private JTextField parameter_max_date = new JTextField("Enter the max date here");
	private JTextField parameter_id = new JTextField("Enter the id here");
	
	private ButtonGroup group;
	
	public ChoiceGui() {
		
		super("SQL Queries Main Menu");
		setLayout(new FlowLayout());
		
		//declare each radio button to be used if selected brings up the proper query fields
		oneone = new JCheckBox("1.1", false);
		onetwo = new JCheckBox("1.2", false);
		onethree = new JCheckBox("1.3", false);
		
		twoone = new JCheckBox("2.1", false);
		twotwo = new JCheckBox("2.2", false);
		twothree = new JCheckBox("2.3", false);
		twofour = new JCheckBox("2.4", false);
		twofive = new JCheckBox("2.5", false);
		twosix = new JCheckBox("2.6", false);
		twoseven = new JCheckBox("2.7", false);
		twoeight = new JCheckBox("2.8", false);

		threeone = new JCheckBox("3.1", false);
		threetwo = new JCheckBox("3.2", false);
		threethree = new JCheckBox("3.3", false);
		threefour = new JCheckBox("3.4", false);
		threefive = new JCheckBox("3.5", false);
		
		fourone = new JCheckBox("4.1", false);
		fourtwo = new JCheckBox("4.2", false);
		fourthree = new JCheckBox("4.3", false);
		fourfour = new JCheckBox("4.4", false);
		fourfive = new JCheckBox("4.5", false);
		
		//add query buttons to view
		add(oneone);
		add(onetwo);
		add(onethree);
		
		add(twoone);
		add(twotwo);
		add(twothree);
		add(twofour);
		add(twofive);
		add(twosix);
		add(twoseven);
		add(twoeight);
		
		add(threeone);
		add(threetwo);
		add(threethree);
		add(threefour);
		add(threefive);
		
		add(fourone);
		add(fourtwo);
		add(fourthree);
		add(fourfour);
		add(fourfive);
		
		//grouping them together like this lets them know when one or other is being used
		group = new ButtonGroup();
		
		//add the button to the button group just to make ui look cleaner
		group.add(oneone);
		group.add(onetwo);
		group.add(onethree);
		
		group.add(twoone);
		group.add(twotwo);
		group.add(twothree);
		group.add(twofour);
		group.add(twofive);
		group.add(twosix);
		group.add(twoseven);
		group.add(twoeight);
		
		group.add(threeone);
		group.add(threetwo);
		group.add(threethree);
		group.add(threefour);
		group.add(threefive);
		
		group.add(fourone);
		group.add(fourtwo);
		group.add(fourthree);
		group.add(fourfour);
		group.add(fourfive);
		
		//wait for event to happen, pass in font object to constructor
		HandlerClass handlerobj = new HandlerClass();
		
		oneone.addItemListener(handlerobj);
		onetwo.addItemListener(handlerobj);
		onethree.addItemListener(handlerobj);
		
		twoone.addItemListener(handlerobj);
		twotwo.addItemListener(handlerobj);
		twothree.addItemListener(handlerobj);
		twofour.addItemListener(handlerobj);
		twofive.addItemListener(handlerobj);
		twosix.addItemListener(handlerobj);
		twoseven.addItemListener(handlerobj);
		twoeight.addItemListener(handlerobj);
		
		threeone.addItemListener(handlerobj);
		threetwo.addItemListener(handlerobj);
		threethree.addItemListener(handlerobj);
		threefour.addItemListener(handlerobj);
		threefive.addItemListener(handlerobj);
		
		fourone.addItemListener(handlerobj);
		fourtwo.addItemListener(handlerobj);
		fourthree.addItemListener(handlerobj);
		fourfour.addItemListener(handlerobj);
		fourfive.addItemListener(handlerobj);
		
		//add the parameter boxes so that when we press based on choice we will filter results
		add(parameter_last_name);
		add(parameter_first_name);
		add(parameter_min_date);
		add(parameter_max_date);
		add(parameter_id);
	}
	
	//implements allows you to use methods in class but you have to overwrite them
		//in this case ItemListener only has one method
		private class HandlerClass implements ItemListener{
			
			//go to query screen based on choice
			public void itemStateChanged(ItemEvent event) {
				if(oneone.isSelected()) {
					oneone oneone = new oneone();
					oneone.setSize(400,1000);
					oneone.setVisible(true);
				}
				else if(onetwo.isSelected()) {
					onetwo onetwo = new onetwo();
					onetwo.setSize(5,500);
					onetwo.setVisible(true);
				}
				else if(onethree.isSelected()) {
					onethree onethree = new onethree();
					onethree.setSize(400,1000);
					onethree.setVisible(true);
				}
				else if(twoone.isSelected()) {
					twoone twoone = new twoone();
					twoone.setSize(600,1000);
					twoone.setVisible(true);
				}
				else if(twotwo.isSelected()) {
					twotwo twotwo = new twotwo();
					twotwo.setSize(400,1000);
					twotwo.setVisible(true);
				}
				else if(twothree.isSelected()) {
					twothree twothree = new twothree(parameter_min_date.getText(), parameter_max_date.getText());
					twothree.setSize(300,1000);
					twothree.setVisible(true);
				}
				else if(twofour.isSelected()) {
					twofour twofour = new twofour(parameter_min_date.getText(), parameter_max_date.getText());
					twofour.setSize(300,1000);
					twofour.setVisible(true);
				}
				else if(twofive.isSelected()) {
					twofive twofive = new twofive(parameter_id.getText());
					twofive.setSize(220,1000);
					twofive.setVisible(true);
				}
				else if(twosix.isSelected()) {
					twosix twosix = new twosix(parameter_id.getText());
					twosix.setSize(100,1000);
					twosix.setVisible(true);
				}
				else if(twoseven.isSelected()) {
					twoseven twoseven = new twoseven();
					twoseven.setSize(600,1000);
					twoseven.setVisible(true);
				}
				else if(twoeight.isSelected()) {
					twoeight twoeight = new twoeight();
					twoeight.setSize(600,1000);
					twoeight.setVisible(true);
				}
				else if(threeone.isSelected()) {
					threeone threeone = new threeone();
					threeone.setSize(300,1000);
					threeone.setVisible(true);
				}
				else if(threetwo.isSelected()) {
					threetwo threetwo = new threetwo();
					threetwo.setSize(300,1000);
					threetwo.setVisible(true);
				}
				else if(threethree.isSelected()) {
					threethree threethree = new threethree();
					threethree.setSize(300,1000);
					threethree.setVisible(true);
				}
				else if(threefour.isSelected()) {
					threefour threefour = new threefour();
					threefour.setSize(300,1000);
					threefour.setVisible(true);
				}
				else if(threefive.isSelected()) {
					threefive threefive = new threefive(parameter_id.getText());
					threefive.setSize(400,1000);
					threefive.setVisible(true);
				}
				else if(fourone.isSelected()) {
					fourone fourone = new fourone();
					fourone.setSize(1010,1000);
					fourone.setVisible(true);
				}
				else if(fourtwo.isSelected()) {
					fourtwo fourtwo = new fourtwo();
					fourtwo.setSize(1010,1000);
					fourtwo.setVisible(true);
				}
				else if(fourthree.isSelected()) {
					fourthree fourthree = new fourthree();
					fourthree.setSize(1010,1000);
					fourthree.setVisible(true);
				}
				else if(fourfour.isSelected()) {
					fourfour fourfour = new fourfour();
					fourfour.setSize(1010,1000);
					fourfour.setVisible(true);
				}
				else if(fourfive.isSelected()) {
					fourfive fourfive = new fourfive();
					fourfive.setSize(1010,1000);
					fourfive.setVisible(true);
				}
				
				group.clearSelection();
			}
		}
}
