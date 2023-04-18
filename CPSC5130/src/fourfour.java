import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class fourfour extends JFrame{

	public fourfour(String id) {
		super("4.4");
		setLayout(new FlowLayout());
		
		Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_database", "root", "password");
            
            Statement stmt = connection.createStatement();
            String query = "SELECT COUNT(Treatment.TID) AS Num_Occurences_Treatment, Procedure_Medicine.Name, Employee.First_Name, Employee.Last_Name "
            		+ "FROM Admit "
            		+ "LEFT JOIN Employee "
            		+ "ON Admit.Admit_Doctor = Employee.EID "
            		+ "LEFT JOIN Treatment "
            		+ "ON Admit.DID = Treatment.TID "
            		+ "LEFT JOIN Procedure_Medicine "
            		+ "ON Procedure_Medicine.PMID = Treatment.PMID "
            		+ "WHERE Admit.Admit_Doctor = " + id
            		+ " GROUP BY Treatment.TID "
            		+ "ORDER BY Num_Occurences_Treatment DESC ";
            ResultSet response = stmt.executeQuery(query);
            
            if(!response.isBeforeFirst()) {
            	showMessageDialog(null, "Query returned no results");
            }
            
            String outputString = "TreatOccur\tTreatName\tFirst\tLast";
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
