package views;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutorService;
import models.Resources;

/**
 * The Window class is a JFrame that is used to display the simulation and control panels.
 */
public class Window extends JFrame {
    // Window Title
    public final static String TITLE = "Particle Simulator";

    // Window Size
    public final static int WIDTH = 1530;

    public final static int HEIGHT = 720;

    /**
     * The Window constructor is used to create a new Window.
     * @param executor - The executor to be used.
     */
    public Window(ExecutorService executor, Resources resources) {
        // Set the title of the window
        super(TITLE);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the size of the window
        setSize(Window.WIDTH, Window.HEIGHT);

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

        // Create the Control and Simulation Panel
        ControlPanel controlPanel = new ControlPanel(executor, resources);
        SimPanel simPanel = new SimPanel(executor, resources, controlPanel);

        // Create the a Split Pane for the Sim Panel and Control Panel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, simPanel, controlPanel);
        splitPane.setDividerLocation(SimPanel.WIDTH);
        splitPane.setDividerSize(0);

        // Add the split pane to the window
        add(splitPane, BorderLayout.CENTER);

        // Set the window to be visible
        pack();
        setVisible(true);
    }
}
