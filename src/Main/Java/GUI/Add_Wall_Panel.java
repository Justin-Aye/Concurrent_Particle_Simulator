package Main.Java.GUI;

import Main.Java.Classes.SharedResources;
import Main.Java.Classes.Wall;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Add_Wall_Panel extends JPanel{
    private JTextField x1, y1, x2, y2;
    private JButton addBtn, clearBtn;
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

        top.add(new JLabel("X1: ", JLabel.CENTER));
        top.add((x1 = new RoundJTextField(5)));

        top.add(new JLabel("Y1: ", JLabel.CENTER));
        top.add((y1 = new RoundJTextField(5)));

        top.add(new JLabel("X2: ", JLabel.CENTER));
        top.add((x2 = new RoundJTextField(5)));

        top.add(new JLabel("Y2: ", JLabel.CENTER));
        top.add((y2 = new RoundJTextField(5)));

        add(top, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new GridLayout(2, 1));
        btnPanel.add((addBtn = new JButton("Add")));
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sr.Add_Wall(new Wall(
                        Integer.parseInt(x1.getText()),
                        Integer.parseInt(x2.getText()),
                        Integer.parseInt(y1.getText()),
                        Integer.parseInt(y2.getText())
                ));
            }
        });

        btnPanel.add((clearBtn = new JButton("Clear")));
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sr.Clear_Walls();
            }
        });

        add(btnPanel, BorderLayout.SOUTH);
    }
}
