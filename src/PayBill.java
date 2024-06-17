import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayBill extends JFrame implements ActionListener {
    JButton pay,back;
    Choice chooseMonth;
    String meter;
    public PayBill(String meter){
        this.meter = meter;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Electricity Bill");
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        heading.setBounds(300,10,400,30);
        add(heading);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30,50,100,20);
        add(meterNo);

        JLabel lblMeterNo = new JLabel();
        lblMeterNo.setBounds(180,50,100,20);
        add(lblMeterNo);

        JLabel customerName = new JLabel("Customer Name:");
        customerName.setBounds(30,80,100,20);
        add(customerName);

        JLabel lblCustomerName = new JLabel();
        lblCustomerName.setBounds(180,80,100,20);
        add(lblCustomerName);

        JLabel Months = new JLabel("Months:");
        Months.setBounds(30,110,100,20);
        add(Months);

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
        chooseMonth.setBounds(180,110,150,20);
        add(chooseMonth);

        JLabel units = new JLabel("Units:");
        units.setBounds(30,140,100,20);
        add(units);

        JLabel lblUnits = new JLabel();
        lblUnits.setBounds(180,140,100,20);
        add(lblUnits);

        JLabel totalBill = new JLabel("Total Bill:");
        totalBill.setBounds(30,170,100,20);
        add(totalBill);

        JLabel lblTotalBill = new JLabel();
        lblTotalBill.setBounds(180,170,100,20);
        add(lblTotalBill);

        JLabel status = new JLabel("Status:");
        status.setBounds(30,200,100,20);
        add(status);

        JLabel lblStatus = new JLabel();
        lblStatus.setBounds(180,200,100,20);
        lblStatus.setForeground(Color.RED);
        add(lblStatus);

        String query = "select * from customer where meter = '"+meter+"'";
        Connect c = new Connect();
        try {
            ResultSet set = c.s.executeQuery(query);
            if (set.next()){
                lblMeterNo.setText(set.getString("meter"));
                lblCustomerName.setText(set.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        chooseMonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String query = "select * from bill where meter_no = '"+meter+"' and month = '"+chooseMonth.getSelectedItem()+"'";
                try {
                    ResultSet set = c.s.executeQuery(query);
                    if (set.next()){
                        lblTotalBill.setText(set.getString("totalBill"));
                        lblStatus.setText(set.getString("billStatus"));
                        lblUnits.setText(set.getString("units"));
                        Months.setText(set.getString("month"));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        pay = new JButton("Pay");
        pay.setBounds(60,250,100,30);
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBounds(200,250,100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(400,400,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,100,400,400);
        add(image);

        setVisible(true);
        setLocation(100,50);
        setSize(800,600);

    }
    public static void main(String[] args) {
        new PayBill("");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == pay) {
            String query = "update bill set billStatus = '"+"Paid"+"' where meter_no = '"+meter+"' and month = '"+chooseMonth.getSelectedItem()+"'";
            Connect c = new Connect();
            try {
                c.s.executeUpdate(query);
                setVisible(false);
                new EasyPaisa(meter);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }else{
//            new Project(meter)
            setVisible(false);
        }
    }
}
