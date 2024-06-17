import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Login extends JFrame implements ActionListener {
    JTextField tfUsername;
    JPasswordField tfPassword;
    Choice Cas;

    JButton login,signUp,cancel;
    public Login(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel username = new JLabel("Username :");
        username.setBounds(350,50,100,30);
        add(username);

        tfUsername = new JTextField();
        tfUsername.setBounds(480,50,220,30);
        tfUsername.setFont(new Font("serif",Font.PLAIN,16));
        add(tfUsername);

        JLabel password = new JLabel("Password :");
        password.setBounds(350,100,100,30);
        add(password);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(480,100,220,30);
        add(tfPassword);

        JLabel as = new JLabel("Login as :");
        as.setBounds(350,150,100,30);
        add(as);

        Cas = new Choice();
        Cas.add("Admin");
        Cas.add("Customer");
        Cas.setBounds(480,150,220,30);
        add(Cas);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50,20,220,200);
        add(image);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image log = i4.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        login = new JButton("Login",new ImageIcon(log));
        login.setBounds(350,200,100,25);
        login.addActionListener(this);
        add(login);

        i4 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image Cancel = i4.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        cancel = new JButton("Cancel",new ImageIcon(Cancel));
        cancel.setBounds(480,200,100,25);
        cancel.addActionListener(this);
        add(cancel);

        i4 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image SignUp = i4.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        signUp = new JButton("SignUp",new ImageIcon(SignUp));
        signUp.setBounds(610,200,100,25);
        signUp.addActionListener(this);
        add(signUp);

        setVisible(true);
        setTitle("Electricity Bill System");
        setLocation(200,120);
        setSize(800,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new Login();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login){
            Connect c = new Connect();
            String uname = tfUsername.getText();
            String password = String.valueOf(tfPassword.getPassword());
            String choice = Cas.getSelectedItem();
            String query = "select * from login where username = '"+uname+"' and password = '"+password+"' and user = '"+choice+"'";
            try {
                ResultSet set = c.s.executeQuery(query);
                if (set.next()){
                    JOptionPane.showMessageDialog(null,"Login Successful");
                    setVisible(false);
                    String meter = set.getString("meter_no");
                    new Project(choice,meter);
                }else{
                    JOptionPane.showMessageDialog(null,"Login failed");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource() == cancel) {
            setVisible(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }else{
            setVisible(false);
            new SignUp();
        }
    }
}
