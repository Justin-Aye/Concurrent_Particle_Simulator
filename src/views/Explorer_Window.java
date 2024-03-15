package views;

import java.util.concurrent.ExecutorService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import models.Resources;


/**
 * The Explorer Window class is a JFrame that is used to display the explorer's POV.
 */
public class Explorer_Window extends JFrame implements KeyListener{
    
    // Title
    public static final String TITLE = "Explorer";

    // Window Size
    public final static int WIDTH = 1280;
    public final static int HEIGHT = 720;

    @Override
    public void keyTyped(KeyEvent e) {
        // System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {

        char key = e.getKeyChar();
        switch (key) {
            case 'w':
                // TODO
                break;

            case 'a':
                // TODO
                break;
        
            case 's':
                // TODO
                break;

            case 'd':
                // TODO
                break;

            default:
                System.out.println(key);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // System.out.println(e.getKeyCode());
    }

    public Explorer_Window(ExecutorService executor, Resources resources){
        // Set the title of the window
        super(TITLE);
        System.out.println("CREATED");

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the size of the window
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // Set the window to be not resizable
        setResizable(false);
        
        // Set the layout of the window
        setLayout(new BorderLayout());

        // Close everything when X is clicked
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                executor.shutdownNow();
                System.gc();
            }
        });
        addKeyListener(this);

        // Add the split pane to the window
        getContentPane().add(new JPanel(), BorderLayout.CENTER);

        // Set the window to be visible
        pack();
        setVisible(true);
    }
}
