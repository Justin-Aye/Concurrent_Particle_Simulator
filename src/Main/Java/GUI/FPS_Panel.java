package Main.Java.GUI;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

class FPS_Panel extends JPanel {
    JLabel counter;
    public FPS_Panel(){
        setLayout(new GridLayout(1, 2));
        setBorder(new EmptyBorder(10, 0, 10, 0));
        counter = new JLabel("0", JLabel.LEFT);
        add(new JLabel("FPS: ", JLabel.RIGHT));
        add(counter);
    }
}
