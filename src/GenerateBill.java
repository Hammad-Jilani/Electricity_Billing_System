import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class GenerateBill extends JFrame implements ActionListener {
    Choice chooseMonth;
    JTextArea area;
    String meter;
    JButton bill;
    public GenerateBill(String meter){
        this.meter = meter;
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        JLabel heading = new JLabel("Generate Report");
        JLabel meterNumber = new JLabel(meter);

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

        panel.add(chooseMonth);

        area = new JTextArea(50,15);
        area.setText("\n\n\t\t   --------Click on the--------\n\t\t Generate Bill Button to get\n\t\t the bill of the Selected Month");
        area.setFont(new Font("Roboto",Font.PLAIN,18));

        JScrollPane pane = new JScrollPane(area);
        panel.add(pane);

        bill = new JButton("Generate Bill");
        bill.addActionListener(this);

//        panel.add(area);
        panel.add(meterNumber);
        panel.add(heading);
        panel.add(chooseMonth);
        add(panel,"North");
        add(pane,"Center");
        add(bill,"South");

        setSize(800,600);
        setVisible(true);
        setLocation(200,0);
    }
    public static void main(String[] args) {
        new GenerateBill("");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            Connect c = new Connect();
            String month = chooseMonth.getSelectedItem();
            area.setText("\n\t\t\tK Electric \n\t\tElectric Bill Generated For month of "+month+" 2024\n\n\n");
            String query = "select * from customer where meter = '"+meter+"'";
            ResultSet set = c.s.executeQuery(query);
            if (set.next()){
                area.append("\n   Customer Name : '"+set.getString("name")+"'");
                area.append("\n   Meter Number : '"+set.getString("meter")+"'");
                area.append("\n   Address : '"+set.getString("address")+"'");
                area.append("\n   City : '"+set.getString("city")+"'");
                area.append("\n   Email : '"+set.getString("email")+"'");
                area.append("\n   Phone Number : '"+set.getString("phone")+"'");
                area.append("\n   State : '"+set.getString("state")+"'");

                area.append("\n ----------------------------\n");
            }
            set = c.s.executeQuery("select * from meter_info where meter_no = '"+meter+"'");
            if (set.next()){
                area.append("\n Meter Location : '"+set.getString("meter_location")+"'");
                area.append("\n Meter Type : '"+set.getString("meter_type")+"' ");
                area.append("\n Phase Code : '"+set.getString("phase_code")+"'");
                area.append("\n Bill Type : '"+set.getString("bill_type")+"'");
                area.append("\n Days : '"+set.getString("days")+"'");
                area.append("\n ----------------------------\n");
            }

            set = c.s.executeQuery("select * from tax");
            if (set.next()){
                area.append("\n Cost per Unit : '"+set.getString("cost_per_unit")+"'");
                area.append("\n Meter rent : '"+set.getString("meter_rent")+"' ");
                area.append("\n Service Charge : '"+set.getString("service_charge")+"'");
                area.append("\n Service Tax : '"+set.getString("service_tax")+"'");
                area.append("\n Levy : '"+set.getString("levy")+"'");
                area.append("\n Fixed Tax : '"+set.getString("fixed_tax")+"'");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
