import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connect extends JFrame {
    Connection c;
    Statement s;
    public Connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///Electricity_Bill_Management","root","password");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
