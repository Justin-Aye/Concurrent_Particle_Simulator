package Main.Java.GUI;

import Main.Java.Classes.SharedResources;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

class Add_Particle_Panel extends JPanel{
    final JTextField speed, angle, count, x1, y1, x2, y2, theta_1, theta_2, v1, v2;
    final JButton addButton, clrButton;

    Add_Particle_Panel(SharedResources sr, int form) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Add Particle"),
                new EmptyBorder(10, 0, 10, 0)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        add(new JLabel("Enter Speed: "), gbc);
        gbc.gridy++;
        add(new JLabel("Enter Angle: "), gbc);
        gbc.gridy++;
        add(new JLabel("Count: "), gbc);

        gbc.gridx++;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add((speed = new RoundJTextField(10)), gbc);
        gbc.gridy++;
        add((angle = new RoundJTextField(10)), gbc);
        gbc.gridy++;
        add((count = new RoundJTextField(10)), gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        GridLayout gap_layout = new GridLayout(2, 4);
        gap_layout.setVgap(5);
        JPanel gap_Panel = new JPanel();
        gap_Panel.setLayout(gap_layout);
        gap_Panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Set Start and End Points"),
                        new EmptyBorder(10, 0, 10, 0)
                )
        );
        gap_Panel.add(new JLabel("X1: ", JLabel.CENTER));
        gap_Panel.add(x1 = new RoundJTextField(5));
        gap_Panel.add(new JLabel("Y1: ", JLabel.CENTER));
        gap_Panel.add(y1 = new RoundJTextField(5));
        gap_Panel.add(new JLabel("X2: ", JLabel.CENTER));
        gap_Panel.add(x2 = new RoundJTextField(5));
        gap_Panel.add(new JLabel("Y2: ", JLabel.CENTER));
        gap_Panel.add(y2 = new RoundJTextField(5));

        add(gap_Panel, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        GridLayout angle_layout = new GridLayout(2, 4);
        angle_layout.setVgap(5);
        JPanel angle_Panel = new JPanel();
        angle_Panel.setLayout(angle_layout);
        angle_Panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Set Start and End Angle"),
                        new EmptyBorder(10, 0, 10, 0)
                )
        );
        angle_Panel.add(new JLabel("Start θ: ", JLabel.CENTER));
        angle_Panel.add(theta_1 = new RoundJTextField(5));
        angle_Panel.add(new JLabel("End θ: ", JLabel.CENTER));
        angle_Panel.add(theta_2 = new RoundJTextField(5));
        add(angle_Panel, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        GridLayout velocity_layout = new GridLayout(2, 4);
        velocity_layout.setVgap(5);
        JPanel Velocity_Panel = new JPanel();
        Velocity_Panel.setLayout(velocity_layout);
        Velocity_Panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Set Start and End Speed"),
                        new EmptyBorder(10, 0, 10, 0)
                )
        );
        Velocity_Panel.add(new JLabel("Start V: ", JLabel.CENTER));
        Velocity_Panel.add(v1 = new RoundJTextField(5));
        Velocity_Panel.add(new JLabel("End V: ", JLabel.CENTER));
        Velocity_Panel.add(v2 = new RoundJTextField(5));
        add(Velocity_Panel, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        addButton = new JButton("Add");
        add(addButton, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        clrButton = new JButton("Clear");
        add(clrButton, gbc);
    }
}