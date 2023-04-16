import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class threefive extends JFrame{

	public threefive(String id) {
		super("3.5");
		setLayout(new FlowLayout());
		
		Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_database", "root", "password");
            
            Statement stmt = connection.createStatement();
            String query = "SELECT Employee.Last_Name, Employee.First_Name, Patient.Last_Name, Patient.First_Name "
            		+ "FROM Treatment "
            		+ "LEFT JOIN Employee "
            		+ "ON Treatment.Person_Who_Ordered = Employee.EID "
            		+ "LEFT JOIN Patient "
            		+ "ON Treatment.PID = Patient.PID "
            		+ "WHERE Treatment.TID = " + id;
            ResultSet response = stmt.executeQuery(query);
            
            if(!response.isBeforeFirst()) {
            	showMessageDialog(null, "Query returned no results");
            }
            
            String outputString = "E Last\tE First\tP Last\tP First";
            JTextField output = new JTextField(outputString);
            add(output);
            
            while (response.next())
            {
            	outputString = response.getString(1) + "\t" + response.getString(2) + "\t" + response.getString(3) + "\t" + response.getString(4);
            	output = new JTextField(outputString);
                add(output);
            }        
            
            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
	}
}
