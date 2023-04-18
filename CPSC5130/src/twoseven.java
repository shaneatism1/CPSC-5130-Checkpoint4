import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class twoseven extends JFrame{

	public twoseven() {
		super("2.7");
		setLayout(new FlowLayout());
		
		Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_database", "root", "password");
            
            Statement stmt = connection.createStatement();
            String query = "SELECT Admit.PID, Patient.Last_Name, Patient.First_Name, Admit.DID, Admit.Admit_Doctor "
            		+ "FROM Admit "
            		+ "LEFT JOIN Patient "
            		+ "ON Admit.PID = Patient.PID "
            		+ "WHERE DATEDIFF(day, Admit.Admit_Date, GETDATE()) >= 0 AND DATEDIFF(day, Admit.Admit_Date, GETDATE()) <= 30 "
            		+ "GROUP BY Patient.PID ";
            ResultSet response = stmt.executeQuery(query);
            
            if(!response.isBeforeFirst()) {
            	showMessageDialog(null, "Query returned no results");
            }
            
            String outputString = "ID\tLast\tFirst\tDID\tDoctor";
            JTextField output = new JTextField(outputString);
            add(output);
            
            while (response.next())
            {
            	outputString = response.getString(1) + "\t" + response.getString(2) + "\t" + response.getString(3) + "\t" + response.getString(4) + "\t" + response.getString(5);
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
