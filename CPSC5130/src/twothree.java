import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class twothree extends JFrame{

	public twothree(String min, String max) {
		super("2.3");
		setLayout(new FlowLayout());
		
		Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_database", "root", "password");
            
            Statement stmt = connection.createStatement();
            String query = "SELECT Admit.PID, Patient.Last_Name, Patient.First_Name "
            		+ "FROM Admit "
            		+ "LEFT JOIN Patient "
            		+ "ON Admit.PID = Patient.PID "
            		+ "WHERE Admit.Discharge_Date >= " + min + " AND Admit.Discharge_Date <= " + max;
            ResultSet response = stmt.executeQuery(query);
            
            if(!response.isBeforeFirst()) {
            	showMessageDialog(null, "Query returned no results");
            }
            
            String outputString = "ID\tlast\tfirst";
            JTextField output = new JTextField(outputString);
            add(output);
            
            while (response.next())
            {
            	outputString = response.getString(1) + "\t" + response.getString(2) + "\t" + response.getString(3);
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
