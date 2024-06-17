import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDetails extends JFrame  {
    String meter;
    BillDetails(String meter){
        this.meter = meter;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JTable table = new JTable();
        Connect c = new Connect();
        String query = "select * from bill where meter_no = '"+meter+"' ";
        try {
            ResultSet set = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(set));
            JScrollPane pane = new JScrollPane(table);
            pane.setBounds(0,0,700,650);
            add(pane);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        setVisible(true);
        setLocation(100,50);
        setSize(700,650);
    }
    public static void main(String[] args) {

    }
}
