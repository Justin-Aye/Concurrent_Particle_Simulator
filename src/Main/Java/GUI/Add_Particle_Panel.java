package Main.Java.GUI;

import Main.Java.Classes.SharedResources;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

class Add_Particle_Panel extends JPanel{
        public JTextField speed, angle, count, x1, y1, x2, y2, theta_1, theta_2, v1, v2;
        public final JComboBox<String> forms = new JComboBox<>(new String[]{"Form 1", "Form 2", "Form 3"});
        public final JButton addButton, clrButton;

        Add_Particle_Panel(SharedResources sr, int form) {
                setLayout(new GridBagLayout());
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Add Particle"),
                        new EmptyBorder(0, 0, 10, 0)
                ));

                GridBagConstraints gbc = new GridBagConstraints();    
                gbc.gridy = 0;
                gbc.gridx = 0;
                // gbc.gridwidth = 2;
                JPanel formPanel = new JPanel(new GridLayout(1, 1));
                formPanel.add(forms);
                add(formPanel, gbc);
                
                gbc.gridy++;
                gbc.gridx = 0;
                gbc.gridwidth = 1;
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.anchor = GridBagConstraints.WEST;

                add(new JLabel("Enter Speed: "), gbc);
                gbc.gridy++;
                add(new JLabel("Enter Angle: "), gbc);
                gbc.gridy++;
                add(new JLabel("Count: "), gbc);

                gbc.gridx++;
                gbc.gridy = 1;
                gbc.weightx = 0.5;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                add((speed = new RoundJTextField(10)), gbc);
                gbc.gridy++;
                add((angle = new RoundJTextField(10)), gbc);
                gbc.gridy++;
                add((count = new RoundJTextField(10)), gbc);

                // Determines which additional forms are to be displayed
                gbc.gridy++;
                gbc.gridx = 0;
                gbc.gridwidth = 2;

                GridLayout layout = new GridLayout(2, 4);
                layout.setVgap(5);
                JPanel panel = new JPanel();
                
                switch (form) {
                        // Form 2
                        case 2:
                                panel.setBorder(
                                                BorderFactory.createCompoundBorder(
                                                        BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Set Start and End Angle"),
                                                        new EmptyBorder(10, 0, 10, 0)
                                                )
                                        );
                                panel.add(new JLabel("Start θ: ", JLabel.CENTER));
                                panel.add(theta_1 = new RoundJTextField(5));
                                panel.add(new JLabel("End θ: ", JLabel.CENTER));
                                panel.add(theta_2 = new RoundJTextField(5));
                                break;

                        // Form 3
                        case 3:
                                panel.setBorder(
                                        BorderFactory.createCompoundBorder(
                                                BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Set Start and End Speed"),
                                                new EmptyBorder(10, 0, 10, 0)
                                        )
                                );
                                panel.add(new JLabel("Start Velocity: ", JLabel.CENTER));
                                panel.add(v1 = new RoundJTextField(5));
                                panel.add(new JLabel("End Velocity: ", JLabel.CENTER));
                                panel.add(v2 = new RoundJTextField(5));
                                break;

                        // Form 1 or Default Mode:
                        default:
                                panel.setLayout(layout);
                                panel.setBorder(
                                        BorderFactory.createCompoundBorder(
                                                BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Set Start and End Points"),
                                                new EmptyBorder(10, 0, 10, 0)
                                        )
                                );
                                panel.add(new JLabel("<html>X<sub>1</sub></html>", JLabel.CENTER));
                                panel.add(x1 = new RoundJTextField(5));
                                panel.add(new JLabel("<html>Y<sub>1</sub></html>", JLabel.CENTER));
                                panel.add(y1 = new RoundJTextField(5));
                                panel.add(new JLabel("<html>X<sub>2</sub></html>", JLabel.CENTER));
                                panel.add(x2 = new RoundJTextField(5));
                                panel.add(new JLabel("<html>Y<sub>2</sub></html>", JLabel.CENTER));
                                panel.add(y2 = new RoundJTextField(5));
                }

                panel.setLayout(layout);
                add(panel, gbc);

                // Add and Clear Buttons
                gbc.gridy++;
                gbc.gridx = 0;
                gbc.gridwidth = 2;
                JPanel btnPanel = new JPanel(new GridLayout(2, 1));
                btnPanel.add((addButton = new JButton("Add")));
                btnPanel.add((clrButton = new JButton("Clear")));
                add(btnPanel, gbc);
        }
}