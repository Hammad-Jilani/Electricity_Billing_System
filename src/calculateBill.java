import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class calculateBill extends JFrame implements ActionListener {
    JTextField tfUnits;
    JButton next,cancel;
    JLabel lblName,lblAddress;
    Choice meterNumber,chooseMonth;
    public calculateBill(){
        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(173,216,230));
        p1.setSize(700,600);
        p1.setLocation(300,0);
        setLayout(null);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(250,20,300,40);
        heading.setFont(new Font("Roboto",Font.BOLD,20));
        p1.add(heading);

        JLabel customerName = new JLabel("Meter Number:");
        customerName.setBounds(100,80,150,25);
        customerName.setFont(new Font("Roboto",Font.BOLD,13));
        p1.add(customerName);

        meterNumber = new Choice();
        meterNumber.setBounds(280,80,150,25);
        Connect c = new Connect();
        String query = "select * from customer";
        try {
            ResultSet set = c.s.executeQuery(query);
            while (set.next()){
                meterNumber.add(set.getString("meter"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        p1.add(meterNumber);

        JLabel meterNo = new JLabel("Name :");
        meterNo.setBounds(100,120,150,25);
        p1.add(meterNo);

        lblName = new JLabel();
        lblName.setBounds(280,120,150,25);
        p1.add(lblName);

        JLabel address = new JLabel("Address:");
        address.setBounds(100,160,150,25);
        p1.add(address);

        lblAddress = new JLabel();
        lblAddress.setBounds(280,160,150,25);
        p1.add(lblAddress);

        meterNumber.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String query = "select * from customer where meter ='"+meterNumber.getSelectedItem()+"'";
                try {
                    ResultSet set = c.s.executeQuery(query);
                    while (set.next()){
                        lblName.setText(set.getString("name"));
                        lblAddress.setText(set.getString("address"));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JLabel city = new JLabel("Units Consumed:");
        city.setBounds(100,200,150,25);
        p1.add(city);

        tfUnits = new JTextField();
        tfUnits.setBounds(280,200,150,25);
        p1.add(tfUnits);

        JLabel state = new JLabel("Month:");
        state.setBounds(100,240,150,25);
        p1.add(state);

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
        chooseMonth.setBounds(280,240,150,25);
        p1.add(chooseMonth);

        next = new JButton("Submit");
        next.setBounds(200,400,100,30);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p1.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(350,400,100,30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p1.add(cancel);

        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(40,40,200,300);
        add(image);

        setVisible(true);
        setLocation(100,50);
        setSize(1000,500);
    }

    public static void main(String[] args) {
        new calculateBill();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connect c = new Connect();
        if (e.getSource() == next){
            String meter = meterNumber.getSelectedItem();
            int units = Integer.parseInt(tfUnits.getText());
            String months = chooseMonth.getSelectedItem();

            int totalBill = 0;

            String query = "select * from tax";
            try {
                ResultSet set = c.s.executeQuery(query);
                while (set.next()){
                    totalBill+= units * Integer.parseInt(set.getString("cost_per_unit"));
                    totalBill+=Integer.parseInt(set.getString("meter_rent"));
                    totalBill+=Integer.parseInt(set.getString("service_charge"));
                    totalBill+=Integer.parseInt(set.getString("service_tax"));
                    totalBill+=Integer.parseInt(set.getString("levy"));
                    totalBill+=Integer.parseInt(set.getString("fixed_tax"));
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            query = "insert into bill values('"+meter+"','"+months+"','"+units+"','"+totalBill+"','Not Paid')";
            try {
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Bill updated Successfully");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }else {
            setVisible(false);
        }
    }
}
