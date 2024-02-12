package Main.Java.GUI;

import Main.Java.Classes.SharedResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.concurrent.ExecutorService;

public class Main_Canvas extends JFrame {

    private Animation_Panel particlePanel;
    private Control_Panel controlPanel;
    private JSplitPane splitPane;

    public Main_Canvas(String name, SharedResources sr, ExecutorService executor){
        super(name);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1480, 720));
        setResizable(false);
        setLayout(new FlowLayout());

        URL url = getClass().getResource("/Logo.png");
        assert url != null;

        setIconImage(new ImageIcon(url).getImage());
        
        controlPanel = new Control_Panel(sr, executor, particlePanel);
        particlePanel = new Animation_Panel(sr, executor, controlPanel.fps_counter.counter);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerSize(0);
        splitPane.setDividerLocation(1280);
        splitPane.add(particlePanel);
        splitPane.add(controlPanel);

        setContentPane(splitPane);

        
        pack();
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                executor.shutdownNow();
            }
        });
    }

}
