package Main.Java.GUI;

import Main.Java.Classes.Particle;
import Main.Java.Classes.SharedResources;
import Main.Java.Classes.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class Animation_Panel extends JPanel implements ActionListener {

    Timer timer;
    private final int FRAME_RATE = 17;
    private int width, height;
    private ArrayList<Particle> particles;
    private SharedResources sharedResources;
    private final ArrayList<Wall> walls;
    private final ExecutorService executor;

    Animation_Panel(SharedResources sr, ExecutorService executor){
        setMinimumSize(new Dimension(500, 500));
        setBackground(Color.BLACK);
        particles = sr.getParticles();
        sharedResources = sr;
        walls = sr.getWalls();
        this.executor = executor;
        timer = new Timer(FRAME_RATE, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.height = getHeight();
        this.width = getWidth();
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D new_g = (Graphics2D) g;
        new_g.setColor(Color.WHITE);

        for (Wall w : walls){
            new_g.drawLine(w.getX1(), w.getY1(), w.getX2(), w.getY2());
        }

        for (Particle p : sharedResources.getParticles()){
            new_g.drawOval(p.getX(), p.getY(), p.getDiameter(), p.getDiameter());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        for (Particle particle : sharedResources.getParticles()) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    // If particle hits walls of the frame
                    if (particle.getX() >= width-particle.getDiameter()/2 || particle.getX() <= particle.getDiameter()/2)
                        particle.setX_speed(-1 * particle.getX_speed());

                    else if (particle.getY() >= height-particle.getDiameter()/2 || particle.getY() <= particle.getDiameter()/2)
                        particle.setY_speed(-1 * particle.getY_speed());

                    // If particle hits custom walls
                    else
                        for (Wall w : walls){
                            if(w.is_Colliding(particle.getFutureX((float)(FRAME_RATE / 1000.0)), particle.getFutureY((float)(FRAME_RATE / 1000.0)))){

                                float nx = (float) (Math.sin(w.getAngle()));
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
