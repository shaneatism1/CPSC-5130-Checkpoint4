import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class fourfive extends JFrame{

	public fourfive() {
		super("4.5");
		setLayout(new FlowLayout());
		
		Connection connection = null, connection2 = null;
		int all = 0;
		
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_database", "root", "password");
            connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_database", "root", "password");
            
            Statement stmt = connection.createStatement(), stmt2 = connection.createStatement();
            String query1 =  
            		 "SELECT COUNT(Treatment.Person_Who_Ordered + Treatment.Person_Involved) as Num_Involved, Employee.EID, Employee.Last_Name, Employee.First_Name "
            		+ "FROM Treatment "
            		+ "LEFT JOIN Employee "
            		+ "ON Treatment.Person_Who_Ordered = Employee.EID "
            		+ "GROUP BY Treatment.Person_Who_Ordered";
            String query2 = "SELECT COUNT(*) FROM Treatment";
            
            ResultSet response = stmt.executeQuery(query1);
            ResultSet response2 = stmt2.executeQuery(query2);
            
            if(!response.isBeforeFirst()) {
            	showMessageDialog(null, "Query returned no results");
            }
            else {
            	all = Integer.parseInt(response2.getString(1));
            }
            
            String outputString = "EmployeeID\tLast\tFirst";
            JTextField output = new JTextField(outputString);
            add(output);
            
            while (response.next() && all == Integer.parseInt(response.getString(1)))
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
