import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;

public class newCustomer extends JFrame implements ActionListener {
    JTextField tfCustomerName,tfAddress,tfCity,tfState,tfEmail,tfPhone;
    JButton next,cancel;
    JLabel lblMeterNo;
    public newCustomer(){
        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(173,216,230));
        p1.setSize(700,600);
        p1.setLocation(300,0);
        setLayout(null);

        JLabel heading = new JLabel("New Customer");
        heading.setBounds(300,20,150,40);
        heading.setFont(new Font("Roboto",Font.BOLD,20));
        p1.add(heading);

        JLabel customerName = new JLabel("Customer Name:");
        customerName.setBounds(100,80,150,25);
        customerName.setFont(new Font("Roboto",Font.BOLD,13));
        p1.add(customerName);

        tfCustomerName = new JTextField();
        tfCustomerName.setBounds(280,80,230,25);
        tfCustomerName.setFont(new Font("Roboto",Font.PLAIN,13));
        p1.add(tfCustomerName);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(100,120,150,25);
        p1.add(meterNo);

        Random rand = new Random();
        int mn = rand.nextInt(9919299)%11111 +99999;

        lblMeterNo = new JLabel(String.valueOf(mn));
        lblMeterNo.setBounds(280,120,150,25);
        p1.add(lblMeterNo);

        JLabel address = new JLabel("Address:");
        address.setBounds(100,160,150,25);
        p1.add(address);

        tfAddress = new JTextField();
        tfAddress.setBounds(280,160,230,25);
        tfAddress.setFont(new Font("Roboto",Font.PLAIN,13));
        p1.add(tfAddress);

        JLabel city = new JLabel("City:");
        city.setBounds(100,200,150,25);
        p1.add(city);

        tfCity = new JTextField();
        tfCity.setBounds(280,200,230,25);
        tfCity.setFont(new Font("Roboto",Font.PLAIN,13));
        p1.add(tfCity);

        JLabel state = new JLabel("State:");
        state.setBounds(100,240,150,25);
        p1.add(state);

        tfState = new JTextField();
        tfState.setBounds(280,240,230,25);
        tfState.setFont(new Font("Roboto",Font.PLAIN,13));
        p1.add(tfState);

        JLabel email = new JLabel("Email:");
        email.setBounds(100,280,150,25);
        p1.add(email);

        tfEmail = new JTextField();
        tfEmail.setBounds(280,280,230,25);
        tfEmail.setFont(new Font("Roboto",Font.PLAIN,13));
        p1.add(tfEmail);

        JLabel phoneNumber = new JLabel("Phone Number:");
        phoneNumber.setBounds(100,320,150,25);
        p1.add(phoneNumber);

        tfPhone = new JTextField();
        tfPhone.setBounds(280,320,230,25);
        tfPhone.setFont(new Font("Roboto",Font.PLAIN,13));
        p1.add(tfPhone);

        next = new JButton("Next");
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

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200,400,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(40,40,200,400);
        add(image);

        setVisible(true);
        setLocation(100,50);
        setSize(1000,600);
    }

    public static void main(String[] args) {
        new newCustomer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String phone = tfPhone.getText();
        String name = tfCustomerName.getText();
        String address= tfAddress.getText();
        String meter = lblMeterNo.getText();
        String city = tfCity.getText();
        String state = tfState.getText();
        String email = tfEmail.getText();
        Connect c = new Connect();
        if (e.getSource() == next){
            String query = "insert into customer values('"+name+"','"+meter+"','"+address+"','"+city+"','"+email+"','"+phone+"', '"+state+"')";
            String query2 = "insert into login values('"+meter+"','','"+name+"','','')";

            try {
                c.s.executeUpdate(query);
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null,"Customer details added successfully");
                setVisible(false);
                new meterInfo(meter);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
