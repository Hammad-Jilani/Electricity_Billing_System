import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class SignUp extends JFrame implements ActionListener {
    JButton back,create;
    JPasswordField password;
    Choice accountType;
    JTextField meter,username,name;

    public SignUp(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(30,30,650,300);
        panel.setBackground(Color.WHITE);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230),2),"Create Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(173,216,230)));
        panel.setLayout(null);
        panel.setForeground(new Color(34,139,34));
        add(panel);

        JLabel heading = new JLabel("Create Account As");
        heading.setBounds(100,50,140,20);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma",Font.PLAIN,16));
        panel.add(heading);

        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(260,50,150,20);
        panel.add(accountType);
//        accountType.addFocusListener();

        JLabel lblMeterNumber = new JLabel("Meter Number");
        lblMeterNumber.setBounds(100,90,140,20);
        lblMeterNumber.setForeground(Color.GRAY);
        lblMeterNumber.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblMeterNumber.setVisible(false);
        panel.add(lblMeterNumber);

        meter = new JTextField();
        meter.setVisible(false);
        meter.setBounds(260,90,150,20);
        panel.add(meter);

        JLabel lblUsername = new JLabel("Username :");
        lblUsername.setBounds(100,130,140,20);
        lblUsername.setForeground(Color.GRAY);
        lblUsername.setFont(new Font("Tahoma",Font.PLAIN,16));
        panel.add(lblUsername);

        username = new JTextField();
        username.setBounds(260,130,150,20);
        panel.add(username);

        JLabel lblName = new JLabel("Name :");
        lblName.setBounds(100,170,140,20);
        lblName.setForeground(Color.GRAY);
        lblName.setFont(new Font("Tahoma",Font.PLAIN,16));
        panel.add(lblName);

        name = new JTextField();
        name.setBounds(260,170,150,20);
        panel.add(name);

        meter.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    Connect c = new Connect();
                    ResultSet set = c.s.executeQuery("select * from login where meter_no = '"+meter.getText()+"'");
                    while (set.next()){
                        name.setText(set.getString("name"));
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });


        JLabel lblPassword = new JLabel("Password :");
        lblPassword.setBounds(100,210,140,20);
        lblPassword.setForeground(Color.GRAY);
        lblPassword.setFont(new Font("Tahoma",Font.PLAIN,16));
        panel.add(lblPassword);

        password = new JPasswordField();
        password.setBounds(260,210,150,20);
        panel.add(password);

        accountType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = accountType.getSelectedItem();
                if (user.equals("Customer")){
                    lblMeterNumber.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                }else{
                    lblMeterNumber.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }
            }
        });

        create  = new JButton("Create");
        create.setBounds(140,260,100,25);
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.addActionListener(this);
        panel.add(create);

        back  = new JButton("Back");
        back.setBounds(300,260,100,25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        panel.add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2 = i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(410,30,250,250);
        panel.add(image);

        setSize(800,500);
        setLocation(150,50);
        setVisible(true);

    }
    public static void main(String[] args) {
        new SignUp();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connect c = new Connect();
        String Name  = name.getText();
        String UserName = username.getText();
        String Password = String.valueOf(password.getPassword());
        String Choice = accountType.getSelectedItem();
        String eMeter = meter.getText();

        if (e.getSource() == create){
            String query = null;
            if (Choice.equals("Admin")) {
                query="insert into login values('" + eMeter + "','" + UserName + "','" + Name + "','" + Password + "','" + Choice + "')";
            }else{
                query = "update login set username = '"+UserName+"', password ='"+Password+"',user ='"+Choice+"' where meter_no ='"+eMeter+"'";
            }
            try {
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Account created Successfully");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }else if(e.getSource() == back){
            setVisible(false);
            new Login();
        }
    }
}
