package Main.Java.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;

import Main.Java.Classes.SharedResources;

public class Control_Panel extends JPanel {

    private SharedResources sharedResources;

    Control_Panel(SharedResources sr, ExecutorService executor, JPanel p){

        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        sharedResources = sr;

        add(new Add_Particle_Panel(sr, executor));
        add(new Add_Wall_Panel(sr));
    }
}
