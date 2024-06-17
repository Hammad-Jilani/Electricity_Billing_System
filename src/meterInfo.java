import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;

public class meterInfo extends JFrame implements ActionListener {
    JTextField tfCustomerName,tfAddress,tfCity,tfState,tfEmail,tfPhone;
    JButton next,cancel;
    JLabel lblMeterNo;
    Choice meterLocation,meterType,phaseCode,billType;
    String meter;
    public meterInfo(String meter){
        this.meter = meter;
        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(173,216,230));
        p1.setSize(700,600);
        p1.setLocation(300,0);
        setLayout(null);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(300,20,150,40);
        heading.setFont(new Font("Roboto",Font.BOLD,20));
        p1.add(heading);

        JLabel customerName = new JLabel("Meter Number:");
        customerName.setBounds(100,80,150,25);
        customerName.setFont(new Font("Roboto",Font.BOLD,13));
        p1.add(customerName);

        JLabel lblMeter = new JLabel(meter);
        lblMeter.setBounds(280,80,230,25);
        lblMeter.setFont(new Font("Roboto",Font.PLAIN,13));
        p1.add(lblMeter);

//        tfCustomerName = new JTextField();
//        tfCustomerName.setBounds(280,80,230,25);
//        tfCustomerName.setFont(new Font("Roboto",Font.PLAIN,13));
//        p1.add(tfCustomerName);

        JLabel meterNo = new JLabel("Meter Location");
        meterNo.setBounds(100,120,150,25);
        p1.add(meterNo);

        meterLocation = new Choice();
        meterLocation.add("Outside");
        meterLocation.add("Inside");
        meterLocation.setBounds(280,120,150,25);
        p1.add(meterLocation);

//        lblMeterNo = new JLabel(String.valueOf(mn));
//        lblMeterNo.setBounds(280,120,150,25);
//        p1.add(lblMeterNo);

        JLabel address = new JLabel("Meter Type:");
        address.setBounds(100,160,150,25);
        p1.add(address);

        meterType = new Choice();
        meterType.add("Electric Meter");
        meterType.add("Solar Meter");
        meterType.add("Smart Meter");
        meterType.setBounds(280,160,150,25);
        p1.add(meterType);

        JLabel city = new JLabel("Phase Code:");
        city.setBounds(100,200,150,25);
        p1.add(city);

        phaseCode = new Choice();
        phaseCode.add("011");
        phaseCode.add("022");
        phaseCode.add("033");
        phaseCode.add("044");
        phaseCode.add("055");
        phaseCode.add("066");
        phaseCode.add("077");
        phaseCode.add("088");
        phaseCode.add("099");
        phaseCode.setBounds(280,200,230,25);
        p1.add(phaseCode);

        JLabel state = new JLabel("Bill Type:");
        state.setBounds(100,240,150,25);
        p1.add(state);

        billType = new Choice();
        billType.add("Normal");
        billType.add("Industrial");
        billType.setBounds(280,240,150,25);
        p1.add(billType);


        JLabel days = new JLabel("Days:");
        days.setBounds(100,280,150,25);
        p1.add(days);

        JLabel email = new JLabel("30 Days:");
        email.setBounds(280,280,150,25);
        p1.add(email);

        JLabel notes = new JLabel("Notes");
        notes.setBounds(100,320,150,25);
        p1.add(notes);

        JLabel description = new JLabel("By default bill is calculated for 30 days only");
        description.setBounds(280,320,350,25);
        p1.add(description);

        next = new JButton("Submit");
        next.setBounds(200,400,100,30);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p1.add(next);

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
        new meterInfo("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String mater = meter;
        String Location = meterLocation.getSelectedItem();
        String type = meterType.getSelectedItem();
        String code = phaseCode.getSelectedItem();
        String typeBill = meterType.getSelectedItem();
        String days ="30";
        Connect c = new Connect();
        if (e.getSource() == next){
            String query = "insert into meter_info values('"+mater+"','"+Location+"','"+type+"','"+code+"','"+typeBill+"','"+days+"')";

            try {
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Meter information add successfully");
                setVisible(false);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

