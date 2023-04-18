import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class fourone extends JFrame{

	public fourone() {
		super("4.1");
		setLayout(new FlowLayout());
		
		Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_database", "root", "password");
            
            Statement stmt = connection.createStatement();
            String query = "SELECT Last_Name, First_Name, Job_Category "
            		+ "FROM Employee "
            		+ "ORDER BY Last_Name ASC, First_Name ASC";
            ResultSet response = stmt.executeQuery(query);
            
            if(!response.isBeforeFirst()) {
            	showMessageDialog(null, "Query returned no results");
            }
            
            String outputString = "Last\tFirst\tCategory";
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
