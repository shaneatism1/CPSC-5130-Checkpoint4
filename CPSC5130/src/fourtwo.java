import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class fourtwo extends JFrame{

	public fourtwo() {
		super("4.2");
		setLayout(new FlowLayout());
		
		Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_database", "root", "password");
            
            Statement stmt = connection.createStatement();
            String query = "SELECT Employee.Last_Name, Employee.First_Name "
            		+ "FROM Admit "
            		+ "LEFT JOIN Employee "
            		+ "ON Admit.Admit_Doctor = Employee.EID "
            		+ "WHERE COUNT(Admit.PID) >= 4 AND DATEDIFF(day,Admit.Admit_Date,GETDATE()) between 0 and 365 "
            		+ "GROUP BY Employee.EID";
            ResultSet response = stmt.executeQuery(query);
            
            if(!response.isBeforeFirst()) {
            	showMessageDialog(null, "Query returned no results");
            }
            
            String outputString = "Last\tFirst";
            JTextField output = new JTextField(outputString);
            add(output);
            
            while (response.next())
            {
            	outputString = response.getString(1) + "\t" + response.getString(2);
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
