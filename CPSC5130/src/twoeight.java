import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class twoeight extends JFrame{

	public twoeight() {
		super("2.8");
		setLayout(new FlowLayout());
		
		Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_database", "root", "password");
            
            Statement stmt = connection.createStatement();
            String query = "SELECT Patient.Last_Name, Patient.First_Name, COUNT(Patient.PID) AS Num_Admissions, AVG(Admit.Discharge_Date - Admit.Admit_Date) AS Average_Duration, MAX(DATEDIFF(day,lag(Admit.Admit_Date,1))) AS Longest_Span, MIN(DATEDIFF(day,lag(Admit.Admit_Date,1))) AS Shortest_Span, AVG(DATEDIFF(day,lag(Admit.Admit_Date,1))) AS Average_Span "
            		+ "FROM Admit "
            		+ "LEFT JOIN Patient "
            		+ "ON Admit.PID = Patient.PID "
            		+ "GROUP BY Patient.PID";
            ResultSet response = stmt.executeQuery(query);
            
            if(!response.isBeforeFirst()) {
            	showMessageDialog(null, "Query returned no results");
            }
            
            String outputString = "NumAdmit\tAverageStay\tLongestS\tShortestS\tAVG Span";
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
