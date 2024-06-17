import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewInformation extends JFrame implements ActionListener {
    JLabel lblname,lblAddress,lblMeterNumber,lblCity,lblState,lblEmail,lblPhone;
    JButton Cancel;
    String meter;
    ViewInformation(String meter){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        this.meter = meter;
        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(300,0,500,40);
        heading.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(heading);

        JLabel Name = new JLabel("Name:");
        Name.setBounds(70,80,100,20);
        Name.setFont(new Font("Tahoma",Font.PLAIN,14));
        add(Name);

        lblname = new JLabel();
        lblname.setBounds(180,80,100,20);
        add(lblname);

        JLabel meterNumber = new JLabel("Meter Number:");
        meterNumber.setBounds(70,110,100,20);
        meterNumber.setFont(new Font("Tahoma",Font.PLAIN,14));
        add(meterNumber);

        lblMeterNumber = new JLabel();
        lblMeterNumber.setBounds(180,110,100,20);
        add(lblMeterNumber);

        JLabel Address = new JLabel("Address:");
        Address.setBounds(70,140,100,20);
        Address.setFont(new Font("Tahoma",Font.PLAIN,14));
        add(Address);

        lblAddress = new JLabel();
        lblAddress.setBounds(180,140,100,20);
        add(lblAddress);

        JLabel City = new JLabel("City:");
        City.setBounds(70,170,100,20);
        City.setFont(new Font("Tahoma",Font.PLAIN,14));
        add(City);

        lblCity = new JLabel();
        lblCity.setBounds(180,170,100,20);
        add(lblCity);

        JLabel State = new JLabel("State:");
        State.setBounds(70+350,80,100,20);
        State.setFont(new Font("Tahoma",Font.PLAIN,14));
        add(State);

        lblState = new JLabel();
        lblState.setBounds(180+350,80,100,20);
        add(lblState);

        JLabel Email = new JLabel("Email:");
        Email.setBounds(70+350,80+30,100,20);
        Email.setFont(new Font("Tahoma",Font.PLAIN,14));
        add(Email);

        lblEmail = new JLabel();
        lblEmail.setBounds(180+350,80+30,100,20);
        add(lblEmail);

        JLabel Phone = new JLabel("Phone:");
        Phone.setBounds(70+350,80+60,100,20);
        Phone.setFont(new Font("Tahoma",Font.PLAIN,14));
        add(Phone);

        lblPhone = new JLabel();
        lblPhone.setBounds(180+350,80+60,100,20);
        add(lblPhone);

        String query = "select * from customer where meter ='"+meter+"'";
        Connect c = new Connect();
        try {
            ResultSet set = c.s.executeQuery(query);
            if (set.next()) {
                lblname.setText(set.getString("name"));
                lblPhone.setText(set.getString("phone"));
                lblAddress.setText(set.getString("address"));
                lblCity.setText(set.getString("city"));
                lblEmail.setText(set.getString("email"));
                lblState.setText(set.getString("state"));
                lblMeterNumber.setText("meter");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Cancel  = new JButton("Cancel");
        Cancel.setBounds(250,240,100,25);
        Cancel.setBackground(Color.BLACK);
        Cancel.setForeground(Color.WHITE);
        Cancel.addActionListener(this);
        add(Cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,300,600,300);
        add(image);
        setBounds(350,50,850,650);
        setVisible(true);
    }
    public static void main(String[] args) {
        new ViewInformation("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }
}
