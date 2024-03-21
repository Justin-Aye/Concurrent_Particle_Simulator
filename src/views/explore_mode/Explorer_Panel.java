package views.explore_mode;

import javax.swing.JPanel;
import javax.swing.Timer;

import models.Explorer;
import models.Particle;
import models.Resources;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

public class Explorer_Panel extends JPanel implements ActionListener, KeyListener{

    private Resources resources;
    private ExecutorService executor;
    private CopyOnWriteArrayList<Particle> particles;
    private Explorer explorer;
    private Sprite sprite;
    private Image image;
    private boolean keyPressed;
    private Timer timer;
    private int FRAME_RATE = 15;
    private int width_ratio, height_ratio;
    private int center_x, center_y;

    public Explorer_Panel(ExecutorService e, Resources r){
        this.resources = r;
        this.executor = e;
        this.particles = resources.getParticles();

        this.width_ratio = (int) Math.ceil(Explorer_Window.WIDTH / 33.0);
        this.height_ratio = (int) Math.ceil(Explorer_Window.HEIGHT / 19);
        this.center_x = Explorer_Window.WIDTH / 2;
        this.center_y = Explorer_Window.HEIGHT / 2;

        this.explorer = new Explorer(1200, 650, Explorer_Window.WIDTH, Explorer_Window.HEIGHT);
        this.sprite = new Sprite("front");
        this.image = sprite.pauseImage();

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(Explorer_Window.WIDTH, Explorer_Window.HEIGHT));
        setMinimumSize(new Dimension(Explorer_Window.WIDTH, Explorer_Window.HEIGHT));

        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(FRAME_RATE, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (g instanceof Graphics2D) {
            Graphics2D g2d = (Graphics2D) g;
            // g2d.setColor(Color.RED);
            // g2d.fillRect(
            //     this.center_x - width_ratio, 
            //     this.center_y - height_ratio, 
            //     width_ratio * 2 + 1, 
            //     height_ratio * 2 + 1
            // );
            if (keyPressed)
                image = sprite.getImage();
            if(image != null){
                g.drawImage(image, (this.center_x - width_ratio) - 60,  //hardcoded 
                (this.center_y - height_ratio)-80, this); //hardcoded
            }
            
            g2d.setColor(Color.BLACK);
            synchronized(particles) {
                for (Particle p : particles) {
                    if(explorer.isDetected(p.getX(), p.getY()))
                        g2d.fillOval(
                            (p.getX() - explorer.getV1_x()) * width_ratio, 
                            (p.getY() - explorer.getV1_y()) * height_ratio, 
                            Particle.DIAMETER * height_ratio, 
                            Particle.DIAMETER * height_ratio
                        );
                }
            }

            if(this.explorer.getCenter_x() - 16 < 0){
                g2d.fillRect(0, 0, Math.abs(this.explorer.getCenter_x() - 16)*width_ratio, Explorer_Window.HEIGHT);
               
            }

            if(this.explorer.getCenter_x() + 16 > Explorer_Window.WIDTH){
                int overflow = this.explorer.getCenter_x() + 16;
                int width = (overflow - Explorer_Window.WIDTH) * width_ratio;
                g2d.fillRect(Explorer_Window.WIDTH - (width), 0, width, Explorer_Window.HEIGHT);
                
            }

            if(this.explorer.getCenter_y() - 9 < 0){
                g2d.fillRect(0, 0, Explorer_Window.WIDTH, Math.abs(this.explorer.getCenter_y() - 9)*height_ratio);
            
            }

            if(this.explorer.getCenter_x() + 9 > Explorer_Window.HEIGHT){
                int overflow = this.explorer.getCenter_y() + 9;
                int height = (overflow - Explorer_Window.HEIGHT) * height_ratio;
                g2d.fillRect(0, Explorer_Window.HEIGHT - height, Explorer_Window.WIDTH, height);
              
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        keyPressed = false;
        image = sprite.pauseImage(); 
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        int keyCode = e.getKeyCode();
        if(keyChar == 'w' || keyCode == KeyEvent.VK_UP ){
                if(!keyPressed)
                    sprite.walkToFront();
                keyPressed = true;
                this.explorer.move_up();
                System.out.println(explorer.getCenter_x() + " " + explorer.getCenter_y());
        }else if (keyChar == 'a' || keyCode == KeyEvent.VK_LEFT){
                if(!keyPressed)
                    sprite.walkToLeft();
                keyPressed = true;
                this.explorer.move_left();
                System.out.println(explorer.getCenter_x() + " " + explorer.getCenter_y());
        }else if (keyChar == 's' || keyCode == KeyEvent.VK_DOWN){
                if(!keyPressed)
                    sprite.walkToBack();
                keyPressed = true;
                this.explorer.move_down();
                System.out.println(explorer.getCenter_x() + " " + explorer.getCenter_y());
        }else if (keyChar == 'd' || keyCode == KeyEvent.VK_RIGHT){
                if(!keyPressed)
                    sprite.walkToRight();
                keyPressed = true;
                this.explorer.move_right();
                
                System.out.println(explorer.getCenter_x() + " " + explorer.getCenter_y());
        }
         
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Particle particle : particles) {
            this.executor.submit(() -> {
                synchronized(particle) {
                    int dia = Particle.DIAMETER;

                    // // Check if particle hits walls of the SimPanel
                    if (particle.getX() - (dia / 2) <= 0 || particle.getX() + (dia / 2) >= (Explorer_Window.WIDTH)) {
                        particle.setVelocityX(-particle.getVelocityX());
                    }
                    
                    if (particle.getY() - (dia / 2) <= 0 || particle.getY() + (dia / 2) >= (Explorer_Window.HEIGHT)) {
                        particle.setVelocityY(-particle.getVelocityY());
                    }

                    // Update the position of the particle
                    particle.refreshXY();
                }
            });
        }

        // Repaint the SimPanel
        repaint();
    }
}
