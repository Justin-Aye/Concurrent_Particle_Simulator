package Main.Java.GUI;

import Main.Java.Classes.SharedResources;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Add_Wall_Panel extends JPanel{
    JTextField x1, y1, x2, y2;
    JButton addBtn, clearBtn;

    Add_Wall_Panel(SharedResources sr){

        BorderLayout b_layout = new BorderLayout();
        b_layout.setVgap(10);
        setLayout(b_layout);

        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Add Wall"),
                        new EmptyBorder(10, 0, 10, 0)
                )
        );

        GridLayout grid_layout = new GridLayout(2, 2);
        grid_layout.setVgap(5);
        JPanel top = new JPanel(grid_layout);

        top.add(new JLabel("<html>X<sub>1</sub></html>", JLabel.CENTER));
        top.add((x1 = new RoundJTextField(5)));

        top.add(new JLabel("<html>Y<sub>1</sub></html>", JLabel.CENTER));
        top.add((y1 = new RoundJTextField(5)));

        top.add(new JLabel("<html>X<sub>2</sub></html>", JLabel.CENTER));
        top.add((x2 = new RoundJTextField(5)));

        top.add(new JLabel("<html>Y<sub>2</sub></html>", JLabel.CENTER));
        top.add((y2 = new RoundJTextField(5)));

        add(top, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new GridLayout(2, 1));
        btnPanel.add((addBtn = new JButton("Add")));
        btnPanel.add((clearBtn = new JButton("Clear")));

        add(btnPanel, BorderLayout.SOUTH);
    }
}
