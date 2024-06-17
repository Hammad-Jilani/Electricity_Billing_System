import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class UpdateInformation extends JFrame implements ActionListener {
    JTextField tfAddress,tfCity,tfState,tfEmail,tfPhone;
    String meter;
    JButton update,back;
    JLabel lblCustomerName,meterNo;
    UpdateInformation(String meter){
        this.meter = meter;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Update Customer Details");
        heading.setBounds(200,20,250,40);
        heading.setFont(new Font("Roboto",Font.BOLD,20));
        add(heading);

        JLabel customerName = new JLabel("Customer Name:");
        customerName.setBounds(100,80,150,25);
        customerName.setFont(new Font("Roboto",Font.BOLD,13));
        add(customerName);

        lblCustomerName = new JLabel();
        lblCustomerName.setBounds(280,80,230,25);
        lblCustomerName.setFont(new Font("Roboto",Font.PLAIN,13));
        add(lblCustomerName);

        meterNo = new JLabel("Meter Number");
        meterNo.setBounds(100,120,150,25);
        add(meterNo);

        JLabel lblMeterNo = new JLabel();
        lblMeterNo.setBounds(280,120,150,25);
        add(lblMeterNo);

        JLabel address = new JLabel("Address:");
        address.setBounds(100,160,150,25);
        add(address);

        tfAddress = new JTextField();
        tfAddress.setBounds(280,160,230,25);
        tfAddress.setFont(new Font("Roboto",Font.PLAIN,13));
        add(tfAddress);

        JLabel city = new JLabel("City:");
        city.setBounds(100,200,150,25);
        add(city);

        tfCity = new JTextField();
        tfCity.setBounds(280,200,230,25);
        tfCity.setFont(new Font("Roboto",Font.PLAIN,13));
        add(tfCity);

        JLabel state = new JLabel("State:");
        state.setBounds(100,240,150,25);
        add(state);

        tfState = new JTextField();
        tfState.setBounds(280,240,230,25);
        tfState.setFont(new Font("Roboto",Font.PLAIN,13));
        add(tfState);

        JLabel email = new JLabel("Email:");
        email.setBounds(100,280,150,25);
        add(email);

        tfEmail = new JTextField();
        tfEmail.setBounds(280,280,230,25);
        tfEmail.setFont(new Font("Roboto",Font.PLAIN,13));
        add(tfEmail);

        JLabel phoneNumber = new JLabel("Phone Number:");
        phoneNumber.setBounds(100,320,150,25);
        add(phoneNumber);

        tfPhone = new JTextField();
        tfPhone.setBounds(280,320,230,25);
        tfPhone.setFont(new Font("Roboto",Font.PLAIN,13));
        add(tfPhone);

        update = new JButton("Update");
        update.setBounds(200,400,100,30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(350,400,100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        String query = "select * from customer where meter = '"+meter+"' ";
        Connect c = new Connect();
        try {
            ResultSet set = c.s.executeQuery(query);
            if (set.next()){
                lblMeterNo.setText(meter);
                lblCustomerName.setText(set.getString("name"));
                tfAddress.setText(set.getString("address"));
                tfCity.setText(set.getString("city"));
                tfState.setText(set.getString("state"));
                tfEmail.setText(set.getString("email"));
                tfPhone.setText(set.getString("phone"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(450,50,400,400);
        add(image);

        setVisible(true);
        setLocation(100,50);
        setSize(800,600);
        setTitle("Update Customer Information");
    }
    public static void main(String[] args) {
        new UpdateInformation("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String phone = tfPhone.getText();
        String address= tfAddress.getText();
        String city = tfCity.getText();
        String state = tfState.getText();
        String email = tfEmail.getText();
        if (e.getSource() == update){
            String query = "update customer set address='"+address+"',city='"+city+"',email='"+email+"',phone='"+phone+"',state='"+state+"'";
            Connect c = new Connect();
            try {
                c.s.executeUpdate(query);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(null,"Update Successfully");
        }else{
            setVisible(false);
        }
    }
}
