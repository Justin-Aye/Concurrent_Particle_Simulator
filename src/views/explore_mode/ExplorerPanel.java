package views.explore_mode;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import models.Constants;
import models.Resources;
import views.Panel;
import views.Window;

public class ExplorerPanel extends Panel {
    // Panel Title
    private static final String PANEL_TITLE = "Welcome Explorer!";

    private final String INSTRUCTIONS = "<html>This is where you can explore the map you have created in the Developer Mode.<br><br>You can use <B>W A S D</B> on your keyboard to move.</html>";

    public ExplorerPanel(ExecutorService executorService, Resources resources) {
        // Call the Panel constructor
        super(PANEL_TITLE, new GridLayout(4, 1));

        // Add Instructions
        add(new JLabel(INSTRUCTIONS, SwingConstants.CENTER));

        // Add the Exit Button
        addButton("Exit Explorer Mode", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose current Window
                SwingUtilities.windowForComponent((JButton) e.getSource()).dispose();

                new Window(executorService, resources, Constants.DEVELOPER);
            }
        });
    }
}
