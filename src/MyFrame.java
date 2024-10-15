import java.awt.Dimension;
import javax.swing.*;

public class MyFrame extends JFrame {
    MyFrame(){
        //ERU:trying to set an icon. doesn't work properly yet. takes the default windows image instead
        // ImageIcon appIcon = new ImageIcon("../images/Time_atack.png");
        // this.setIconImage(appIcon.getImage());


        this.setTitle("BDSM"); //ERU:sorry i needed to
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ERU:sets our window to a certain size when started
        this.setMinimumSize(new Dimension(640,480));
        // this.setSize(500, 500);
        this.setResizable(true);
        this.setVisible(true);
    }
}
