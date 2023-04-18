import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class fourthree extends JFrame{

	public fourthree(String id) {
		super("4.3");
		setLayout(new FlowLayout());
		
		Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_database", "root", "password");
            
            Statement stmt = connection.createStatement();
            String query = 
            		  "SELECT COUNT(Admit.DID) AS Num_Occurences_Diagnosis, Diagnosis.Name, Employee.First_Name, Employee.Last_Name "
            		+ "FROM Admit "
            		+ "LEFT JOIN Employee "
            		+ "ON Admit.Admit_Doctor = Employee.EID "
            		+ "LEFT JOIN Diagnosis "
            		+ "ON Admit.DID = Diagnosis.DID "
            		+ "WHERE Admit.Admit_Doctor = " + id
            		+ " GROUP BY Admit.DID "
            		+ "ORDER BY Num_Occurences_Diagnosis DESC";
            ResultSet response = stmt.executeQuery(query);
            
            if(!response.isBeforeFirst()) {
            	showMessageDialog(null, "Query returned no results");
            }
            
            String outputString = "DiagOccur\tDiagName\tFirst\tLast";
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
