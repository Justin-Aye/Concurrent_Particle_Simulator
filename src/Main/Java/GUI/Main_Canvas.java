package Main.Java.GUI;

import Main.Java.Classes.SharedResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Main_Canvas extends JFrame {

    private Animation_Panel particlePanel;
    private Control_Panel controlPanel;
    private JSplitPane splitPane;

    public Main_Canvas(String name, SharedResources sr, ExecutorService executor){
        super(name);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1480, 720));
        setResizable(false);
        setLayout(new BorderLayout());

        URL url = getClass().getResource("/Logo.png");
        assert url != null;

        setIconImage(new ImageIcon(url).getImage());

        particlePanel = new Animation_Panel(sr, executor);
        controlPanel = new Control_Panel(sr, executor, particlePanel);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerSize(0);
        splitPane.setDividerLocation(1280);
        splitPane.add(particlePanel);
        splitPane.add(controlPanel);

        add(splitPane, BorderLayout.CENTER);

        pack();
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                executor.shutdownNow();
                try {
                    if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                        executor.shutdownNow();
                        if (!executor.awaitTermination(60, TimeUnit.SECONDS))
                            System.err.println("Executor did not terminate");
                    }
                } catch (InterruptedException ie) {
                    executor.shutdownNow();
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

}
