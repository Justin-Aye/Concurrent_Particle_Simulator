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
    private final static int WIDTH = 1280;
    private final static int HEIGHT = 720;
    private int FRAME_RATE = 15;
    private int width_ratio, height_ratio;
    private int center_x, center_y;

    public Explorer_Panel(ExecutorService e, Resources r){
        this.resources = r;
        this.executor = e;
        this.particles = resources.getParticles();

        this.width_ratio = (int) Math.ceil(WIDTH / 33.0);
        this.height_ratio = (int) Math.ceil(HEIGHT/ 19);
        this.center_x = WIDTH / 2;
        this.center_y = HEIGHT / 2;

        this.explorer = new Explorer(r.getSpriteX(), r.getSpriteY(), WIDTH, HEIGHT);
        this.sprite = new Sprite("front");
        this.image = sprite.pauseImage();


        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

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
            if (keyPressed)
                image = sprite.getImage();
            if(image != null){
                g.drawImage(image, (this.center_x - width_ratio) - 60, 
                (this.center_y - height_ratio)-80, this); 
            }
            
            g2d.setColor(Color.BLACK);
            synchronized(particles) {
                for (Particle p : particles) {
                    if(explorer.isDetected(p.getX(), HEIGHT - p.getY())){
                        int x_val = (p.getX() - explorer.getV1_x()) * width_ratio;
                        int y_val = ((HEIGHT - p.getY()) - explorer.getV1_y()) * height_ratio;

                        if (x_val < 0)
                            x_val = p.DIAMETER * width_ratio;

                        if (y_val < 0)
                            y_val = p.DIAMETER * height_ratio;

                        g2d.fillOval(
                            x_val, 
                            y_val, 
                            Particle.DIAMETER * height_ratio, 
                            Particle.DIAMETER * height_ratio
                        );
                    }
                }
            }

            if(this.explorer.getCenter_x() - 16 < 0){
                g2d.fillRect(0, 0, Math.abs(this.explorer.getCenter_x() - 16)*width_ratio, HEIGHT);
               
            }

            if(this.explorer.getCenter_x() + 16 > WIDTH){
                int overflow = this.explorer.getCenter_x() + 16;
                int width = (overflow -WIDTH) * width_ratio;
                g2d.fillRect(WIDTH- (width), 0, width, HEIGHT);
                
            }

            if(this.explorer.getCenter_y() - 9 < 0){
                g2d.fillRect(0, 0, WIDTH, Math.abs(this.explorer.getCenter_y() - 9)*height_ratio);
            
            }

            if(this.explorer.getCenter_y() + 9 >  HEIGHT){
                int overflow = this.explorer.getCenter_y() + 9;
                int height = (overflow - HEIGHT) * height_ratio;
                g2d.fillRect(0, HEIGHT - height, WIDTH, height);
              
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
                    if (particle.getX() - (dia / 2) <= 0 || particle.getX() + (dia / 2) >= (WIDTH)) {
                        particle.setVelocityX(-particle.getVelocityX());
                    }
                    
                    if (particle.getY() - (dia / 2) <= 0 || particle.getY() + (dia / 2) >= (HEIGHT)) {
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
