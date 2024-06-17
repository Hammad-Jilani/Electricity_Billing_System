import javax.swing.*;
import java.awt.*;

public class splash extends JFrame implements Runnable {
    Thread t;
    public splash(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/karachi.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,800,600);
        add(image);

        t = new Thread(this);
        t.start();


        setVisible(true);
        setLocation(300,50);
        setSize(800,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args) {
        new splash();
    }

    @Override
    public void run() {
        try{
            Thread.sleep(5000);
            new Login();
            setVisible(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
