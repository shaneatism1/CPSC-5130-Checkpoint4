import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class threetwo extends JFrame{

	public threetwo() {
		super("3.2");
		setLayout(new FlowLayout());
		
		Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_database", "root", "password");
            
            Statement stmt = connection.createStatement();
            String query = "Select COUNT(Diagnosis.Name) as Num_Occurences_Diagnosis, Diagnosis.Name, Diagnosis.DID "
            		+ "FROM Room "
            		+ "LEFT JOIN Admit "
            		+ "ON Room.RID = Admit.RID "
            		+ "LEFT JOIN Diagnosis "
            		+ "ON Admit.DID = Diagnosis.DID "
            		+ "WHERE Room.AID != null "
            		+ "GROUP BY Diagnosis.DID "
            		+ "ORDER BY Num_Occurences_Diagnosis DESC";
            ResultSet response = stmt.executeQuery(query);
            
            if(!response.isBeforeFirst()) {
            	showMessageDialog(null, "Query returned no results");
            }
            
            String outputString = "NumOccur\tName\tID";
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
