package Main.Java.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
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

        topPanel = new Add_Particle_Panel(sr, 1);

        // Add Particles Button
        topPanel.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float s = Float.parseFloat(topPanel.speed.getText());
                float a = Integer.parseInt(topPanel.angle.getText());
                int c = Integer.parseInt(topPanel.count.getText());

                // For Form 1
                int x1 = Integer.parseInt(topPanel.x1.getText());
                int y1 = Integer.parseInt(topPanel.y1.getText());
                int x2 = Integer.parseInt(topPanel.x2.getText());
                int y2 = Integer.parseInt(topPanel.y2.getText());
                boolean isForm1 = x1 != 0 && y1 != 0 && x2 != 0 && y2 != 0;

                // For Form 2
                int theta_1 = Integer.parseInt(topPanel.theta_1.getText());
                int theta_2 = Integer.parseInt(topPanel.theta_2.getText());
                boolean isForm2 = (theta_1 != 0 || theta_2 != 0);

                // For Form 3
                int v1 = Integer.parseInt(topPanel.v1.getText());
                int v2 = Integer.parseInt(topPanel.v2.getText());
                boolean isForm3 = v1 != 0 && v2 != 0;

                // Execute when Count is more than 0
                if (c > 0) {
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            // First Form
                            if (isForm1) {
                                int [][] particles = getDistance(x1,y1,x2,y2,c);
                                for (int i = 0; i< c; i++) {
                                    for (int j = 0; j< 2; j++) {
                                        System.out.print(particles[i][j] + " ");
                                    }
                                    System.out.println();
                                }
                                for(int i = 0; i < c; i++) {
                                    try {
                                        Thread.sleep(100);
                                        sr.Add_Particle(new Particle(particles[i][0], particles[i][1], s, a + (i*10), 5));
                                    } catch (Exception exception) {
                                        exception.printStackTrace();
                                    }
                                }
                            }

                            // Second Form
                            else if (isForm2) {
                                float interval = (float) Math.abs(theta_2 - theta_1) / c;
                                for(int i = 0; i < c; i++) {
                                    try {
                                        Thread.sleep(100);
                                        System.out.println(theta_1 + interval*i);
                                        sr.Add_Particle(new Particle(s, (theta_1 + interval*(i+1)), 5));
                                    } catch (Exception exception) {
                                        exception.printStackTrace();
                                    }
                                }
                                
                            }

                            // Third Form
                            else if (isForm3) {
                                for(int i = 0; i < c; i++) {
                                    try {
                                        Thread.sleep(100);
                                        sr.Add_Particle(new Particle(s, a + (i*10), 5));
                                    } catch (Exception exception) {
                                        exception.printStackTrace();
                                    }
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

    public int[][] getDistance(double x1, double y1, double x2, double y2, int n_Particles){
         //Calculate distance
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        //Calculate fixed distance between particles
        double hiwalay = distance/(n_Particles-1);

        //Get start and end points of each particle
        int[][] particles = new int[n_Particles][2];
      
        double dx = (double) (x2 - x1) / (n_Particles - 1);
        double dy = (double) (y2 - y1) / (n_Particles - 1);

        for (int i = 0; i < n_Particles; i++) {
            particles[i][0] = (int) (x1 + i * dx);
            particles[i][1] = (int) (y1 + i * dy);
        }

        return particles;
    }

    
}
