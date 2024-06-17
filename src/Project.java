import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Project extends JFrame implements ActionListener {
    String atype,meter;
    public Project(String atype,String meter){
        this.atype = atype;
        this.meter = meter;

        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);

        JMenu master = new JMenu("Master");
        master.setForeground(Color.BLUE);

        JMenuItem customer = new JMenuItem("New Customer");
        customer.setFont(new Font("serif",Font.PLAIN,14));
        customer.setBackground(Color.WHITE);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon1.png"));
        Image i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        customer.setIcon(new ImageIcon(i2));
        customer.setMnemonic('D');
        customer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
        customer.addActionListener(this);

        JMenuItem customerDetails = new JMenuItem("Customer Details");
        customerDetails.setFont(new Font("serif",Font.PLAIN,14));
        customerDetails.setBackground(Color.WHITE);
        i1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon2.png"));
        i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        customerDetails.setIcon(new ImageIcon(i2));
        customerDetails.setMnemonic('M');
        customerDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.CTRL_MASK));
        master.add(customerDetails);
        customerDetails.addActionListener(this);

        JMenuItem depositDetails = new JMenuItem("Deposit Details");
        depositDetails.setFont(new Font("serif",Font.PLAIN,14));
        depositDetails.setBackground(Color.WHITE);
        i1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon3.png"));
        i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        depositDetails.setIcon(new ImageIcon(i2));
        depositDetails.addActionListener(this);
        depositDetails.setMnemonic('N');
        depositDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        master.add(depositDetails);

        JMenuItem calculateBill = new JMenuItem("Calculate Bill");
        calculateBill.setFont(new Font("serif",Font.PLAIN,14));
        calculateBill.setBackground(Color.WHITE);
        calculateBill.addActionListener(this);
        i1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon5.png"));
        i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        calculateBill.setIcon(new ImageIcon(i2));
        calculateBill.setMnemonic('B');
        calculateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
        master.add(calculateBill);

        JMenu information = new JMenu("Information");
        information.setForeground(Color.RED);

        JMenuItem updateInfo = new JMenuItem("Update Information");
        updateInfo.setFont(new Font("serif",Font.PLAIN,14));
        updateInfo.setBackground(Color.WHITE);
        i1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        updateInfo.setIcon(new ImageIcon(i2));
        updateInfo.addActionListener(this);
        updateInfo.setMnemonic('P');
        updateInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        information.add(updateInfo);

        JMenuItem viewInfo = new JMenuItem("View Information");
        viewInfo.setFont(new Font("serif",Font.PLAIN,14));
        viewInfo.setBackground(Color.WHITE);
        i1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        viewInfo.setIcon(new ImageIcon(i2));
        viewInfo.addActionListener(this);
        viewInfo.setMnemonic('B');
        viewInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
        information.add(viewInfo);

        JMenu user = new JMenu("User");
        user.setForeground(Color.BLUE);

        JMenuItem payBill = new JMenuItem("Pay Bill");
        payBill.addActionListener(this);
        payBill.setFont(new Font("serif",Font.PLAIN,14));
        payBill.setBackground(Color.WHITE);
        i1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        payBill.setIcon(new ImageIcon(i2));
        payBill.setMnemonic('R');
        payBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
        user.add(payBill);

        JMenuItem billDetails = new JMenuItem("Bill Details");
        billDetails.setFont(new Font("serif",Font.PLAIN,14));
        billDetails.setBackground(Color.WHITE);
        i1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        billDetails.setIcon(new ImageIcon(i2));
        billDetails.addActionListener(this);
        billDetails.setMnemonic('B');
        billDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
        user.add(billDetails);

        JMenu report = new JMenu("Report");
        report.setForeground(Color.RED);

        JMenuItem generateBill = new JMenuItem("Generate Report");
        generateBill.setFont(new Font("serif",Font.PLAIN,14));
        generateBill.addActionListener(this);
        generateBill.setBackground(Color.WHITE);
        i1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));
        i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        generateBill.setIcon(new ImageIcon(i2));
        generateBill.setMnemonic('A');
        generateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        report.add(generateBill);

        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.BLUE);

        JMenuItem notepad = new JMenuItem("Nodepad");
        notepad.setFont(new Font("serif",Font.PLAIN,14));
        notepad.addActionListener(this);
        notepad.setBackground(Color.WHITE);
        i1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
        i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(i2));
        notepad.setMnemonic('C');
        notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        master.add(customer);
        utility.add(notepad);

        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.addActionListener(this);
        calculator.setFont(new Font("serif",Font.PLAIN,14));
        calculator.setBackground(Color.WHITE);
        i1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
        i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(i2));
        calculator.setMnemonic('D');
        calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
        utility.add(calculator);

        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.red);

        JMenuItem Exit = new JMenuItem("Exit");
        Exit.setFont(new Font("serif",Font.PLAIN,14));
        Exit.addActionListener(this);
        Exit.setBackground(Color.WHITE);
        i1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon11.png"));
        i2 = i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        Exit.setIcon(new ImageIcon(i2));
        Exit.setMnemonic('W');
        Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,ActionEvent.CTRL_MASK));
        exit.add(Exit);

        i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect1.jpg"));
        i2 = i1.getImage().getScaledInstance(1200,800,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1200,800);
        add(image);

        if (atype.equals("Admin")) {
            menu.add(master);
        }else {
            menu.add(information);
            menu.add(user);
            menu.add(report);
        }

        menu.add(utility);
        menu.add(exit);
//        add(menu);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(50,0);
        setSize(1200,800);
    }
    public static void main(String[] args) {
        new Project("","");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();
        if (text.equals("Calculate Bill")){
            new calculateBill();
        } else if (text.equals("New Customer")) {
            new newCustomer();
        } else if (text.equals("Deposit Details")) {
            
        } else if (text.equals("Customer Details")) {
//            new CustomerDetails();
        } else if (text.equals("View Information")) {
            new ViewInformation(meter);
        }else if (text.equals("Update Information")){
            new UpdateInformation(meter);
        } else if (text.equals("Nodepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (text.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (text.equals("Bill Details")) {
            new BillDetails(meter);
        } else if (text.equals("Exit")) {
            setVisible(false);
            new Login();
        } else if (text.equals("Pay Bill")) {
            new PayBill(meter);
        } else if (text.equals("Generate Report")) {
            new GenerateBill(meter);
        }
    }
}
