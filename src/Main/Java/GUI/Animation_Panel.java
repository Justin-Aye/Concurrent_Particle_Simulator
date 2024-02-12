package Main.Java.GUI;

import Main.Java.Classes.Particle;
import Main.Java.Classes.SharedResources;
import Main.Java.Classes.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

public class Animation_Panel extends JPanel implements ActionListener {

    private Timer timer, fpsTimer;
    private final int width = 1280, height = 720;
    private final int FRAME_RATE = 15;
    private final ExecutorService executor;
    private CopyOnWriteArrayList<Wall> walls;
    private CopyOnWriteArrayList<Particle> particles;

    private int frameCount =  0;
    private JLabel fps_counter;

    Animation_Panel(SharedResources sr, ExecutorService e, JLabel fps){
        setPreferredSize(new Dimension(1280, 720));
        setMinimumSize(new Dimension(1280, 720));
        setBackground(Color.BLACK);

        walls = sr.getWalls();
        particles = sr.getParticles();
        
        executor = e;
        fps_counter = fps;

        timer = new Timer(FRAME_RATE, this);
        timer.start();

        fpsTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fps_counter.setText(String.valueOf(frameCount * 2));
                frameCount =  0;
            }
        });
        fpsTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D new_g = (Graphics2D) g;
        new_g.setColor(Color.WHITE);

        synchronized (particles){
            for (Particle p : particles){
                new_g.drawOval(p.getX(), p.getY(), p.getDiameter(), p.getDiameter());
            }
        }

        synchronized(walls){
            for (Wall w : walls){
                new_g.drawLine(w.getX1(), w.getY1(), w.getX2(), w.getY2());
            }
        }

        frameCount++;
    }

    @Override
    public void actionPerformed(ActionEvent e){

        for (Particle particle : particles) {
            executor.submit(() -> {
                synchronized(particle) {
                    // Check if particle hits walls of the panel
                    if (particle.getX() >= width-particle.getDiameter()/2 || particle.getX() <= particle.getDiameter()/2)
                        particle.setX_speed(-1 * particle.getX_speed());

                    else if (particle.getY() >= height-particle.getDiameter()/2 || particle.getY() <= particle.getDiameter()/2)
                        particle.setY_speed(-1 * particle.getY_speed());

                    // If particle hits custom walls
                    else
                        for (Wall w : walls){
                            if(w.is_Colliding(particle.getFutureX((float)(FRAME_RATE / 1000.0)), particle.getFutureY((float)(FRAME_RATE / 1000.0)))){

                                float nx = (float) Math.sin(w.getAngle());
                                float ny = (float) Math.cos(w.getAngle());

                                float dot = particle.getX_speed() * nx + particle.getY_speed() * ny;
                                float newX_speed = particle.getX_speed() - 2 * dot * nx;
                                float newY_speed = particle.getY_speed() - 2 * dot * ny;

                                particle.setX_speed(newX_speed);
                                particle.setY_speed(newY_speed);
                                particle.updateXY();
                                break;
                            }
                        }

                    particle.updateXY();
                } 
            });
        };

        repaint();
    }
}
