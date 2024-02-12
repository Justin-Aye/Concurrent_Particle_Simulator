package Main.Java.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import Main.Java.Classes.Particle;
import Main.Java.Classes.SharedResources;
import Main.Java.Classes.Wall;

public class Control_Panel extends JPanel {

    private Add_Particle_Panel topPanel;
    private Add_Wall_Panel bottomPanel;
    FPS_Panel fps_counter;

    Control_Panel(SharedResources sr, ExecutorService executor, JPanel p){

        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        fps_counter = new FPS_Panel();
        add(fps_counter);

        topPanel = new Add_Particle_Panel(sr);

        // Add Particles Button
        topPanel.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float s = Float.parseFloat(topPanel.speed.getText());
                float a = Integer.parseInt(topPanel.angle.getText());
                int c = Integer.parseInt(topPanel.count.getText());
                int theta_1 = Integer.parseInt(topPanel.theta_1.getText());
                int theta_2 = Integer.parseInt(topPanel.theta_1.getText());

                if(theta_1 != 0 && theta_2 != 0){}

                if(c > 0){
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            for(int i = 0; i < c; i++) {
                                try {
                                    Thread.sleep(100);
                                    sr.Add_Particle(new Particle(s, a + (i*10), 5));
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                        }
                    });
                }
                    
            }
        });

        // Clear Particles Button
        topPanel.clrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sr.Clear_Particles();
            }
        });

        add(topPanel);

        bottomPanel = new Add_Wall_Panel(sr);
        bottomPanel.addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sr.Add_Wall(new Wall(
                        Integer.parseInt(bottomPanel.x1.getText()),
                        Integer.parseInt(bottomPanel.x2.getText()),
                        Integer.parseInt(bottomPanel.y1.getText()),
                        Integer.parseInt(bottomPanel.y2.getText())
                ));
            }
        });

        bottomPanel.clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sr.Clear_Walls();
            }
        });

        add(bottomPanel);
    }
}
