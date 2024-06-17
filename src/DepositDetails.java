import com.mysql.cj.protocol.Resultset;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepositDetails extends JFrame implements ActionListener {
    Choice chooseMeterNumber,chooseMonth;
    JTable table;
    JButton search,print;
    public DepositDetails(){
        super("Deposit Details");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblMeterNumber  = new JLabel("Search by Meter Number");
        lblMeterNumber.setBounds(20,20,150,20);
        add(lblMeterNumber);

        chooseMeterNumber = new Choice();
        chooseMeterNumber.setBounds(180,20,150,20);
        add(chooseMeterNumber);

        Connect c = new Connect();
        String query = "select * from customer";
        try {
            ResultSet set = c.s.executeQuery(query);
            while (set.next()){
                chooseMeterNumber.add(set.getString("meter"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        JLabel lblMonths  = new JLabel("Search by Month");
        lblMonths.setBounds(350,20,100,20);
        add(lblMonths);

        chooseMonth = new Choice();
        chooseMonth.add("January");
        chooseMonth.add("February");
        chooseMonth.add("March");
        chooseMonth.add("April");
        chooseMonth.add("May");
        chooseMonth.add("June");
        chooseMonth.add("July");
        chooseMonth.add("August");
        chooseMonth.add("September");
        chooseMonth.add("October");
        chooseMonth.add("November");
        chooseMonth.add("December");
        chooseMonth.setBounds(470,20,150,20);
        add(chooseMonth);

        table = new JTable();
        String query1 = "select * from bill where meter_no = '"+chooseMeterNumber.getSelectedItem()+"' and month = '"+chooseMonth.getSelectedItem()+"' ";
        Connect c1 = new Connect();
        try {
            ResultSet set = c1.s.executeQuery(query1);
            table.setModel(DbUtils.resultSetToTableModel(set));
            JScrollPane pane = new JScrollPane(table);
            pane.setBounds(50,80,600,500);
            add(pane);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        search = new JButton("Search");
        search.setBounds(50,50,100,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("print");
        print.setBounds(160,50,100,20);
        print.addActionListener(this);
        add(print);

        setVisible(true);
        setSize(700,700);

        setLocation(400,100);
    }

    public static void main(String[] args) {
        new DepositDetails();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search){
            String query1 = "select * from bill where meter_no = '"+chooseMeterNumber.getSelectedItem()+"' and month = '"+chooseMonth.getSelectedItem()+"' ";
            Connect c1 = new Connect();
            try {
                ResultSet set = c1.s.executeQuery(query1);
                table.setModel(DbUtils.resultSetToTableModel(set));
                JScrollPane pane = new JScrollPane(table);
                pane.setBounds(50,80,600,500);
                add(pane);
            } catch (SQLException exception) {
                throw new RuntimeException(exception);
            }
        }else{
            try {
                table.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
