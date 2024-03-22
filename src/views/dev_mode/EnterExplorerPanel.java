package views.dev_mode;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import models.Constants;
import models.Resources;
import views.InputField;
import views.Panel;
import views.Window;

public class EnterExplorerPanel extends Panel {

    private static final String PANEL_TITLE = "Explorer Mode";

    private InputField x, y;

    private Resources resources;

    private ExecutorService executorService;

    public EnterExplorerPanel(ExecutorService executorService, Resources resources) {
        // Call the Panel constructor
        super(PANEL_TITLE, new BorderLayout());

        // Set the Resources
        this.resources = resources;

        this.executorService = executorService;

        // Add the Input Panel
        add(new InputPanel(), BorderLayout.NORTH);

        // Add the Button Panel
        add(new ButtonPanel(), BorderLayout.SOUTH);
    }

    /**
     * Below are the InputField Configurations
     */
    private class InputPanel extends Panel {
        public InputPanel() {
            super(new GridLayout(2, 4, 0, 5));

            x = addInputBar("X", 5);

            y = addInputBar("Y", 5);
        }
    }

    /**
    * Below are the Button Configurations
    */
    private class ButtonPanel extends Panel {
        public ButtonPanel() {
            super(new GridLayout(1, 1));

            addButton("Enter Explorer Mode", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Dispose current Window
                    SwingUtilities.windowForComponent((JButton) e.getSource()).dispose();

                    resources.setSpriteSpawn(x.getText(), y.getText());

                    // Create a new Explorer Window
                    new Window(executorService, resources, Constants.EXPLORER, "Explorer Mode");
                }
            });
        }
    }
}
