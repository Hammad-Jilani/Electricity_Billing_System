import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EasyPaisa extends JFrame implements ActionListener {
    String meter;
    JButton back;
    public EasyPaisa(String meter) {
        this.meter = meter;
        JEditorPane pane = new JEditorPane();
        pane.setEditable(false);
        try{
            pane.setPage("https://paytm.com/online-payments");
        }catch (Exception e){
            pane.setContentType("text/html");
            pane.setText("<html>Could not load</html>");
        }

        JScrollPane pane1 = new JScrollPane(pane);
        add(pane1);

        setSize(800,400);
        setLocation(50,50);
        setVisible(true);
        back = new JButton("Back");
        back.setBounds(600,30,100,30);
        back.addActionListener(this);
        pane.add(back);
    }

    public static void main(String[] args) {
        new EasyPaisa("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new PayBill(meter);
    }
}
