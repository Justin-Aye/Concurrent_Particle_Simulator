package views.explore_mode;

import java.util.concurrent.ExecutorService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import models.Resources;


/**
 * The Explorer Window class is a JFrame that is used to display the explorer's POV.
 * @deprecated - since Window.java now exists
 */
public class Explorer_Window extends JFrame {
    
    // Title
    public static final String TITLE = "Explorer";

    // Window Size
    public final static int WIDTH = 1280;
    public final static int HEIGHT = 720;

    public Explorer_Window(ExecutorService executor, Resources resources){
        // Set the title of the window
        super(TITLE);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the size of the window
        setSize(new Dimension(WIDTH, HEIGHT));

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

        // Add the Main Panel to the window
        Explorer_Panel panel = new Explorer_Panel(executor, resources);
        getContentPane().add(panel, BorderLayout.CENTER);

        // Set the window to be visible
        pack();
        setVisible(true);
    }
}
