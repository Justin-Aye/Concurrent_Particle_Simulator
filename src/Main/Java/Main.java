package Main.Java;

import Main.Java.Classes.Particle;
import Main.Java.Classes.SharedResources;
import Main.Java.GUI.Main_Canvas;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static Main_Canvas canvas;
    private static ArrayList<Particle> particles;
    private static SharedResources sharedResources;

    public static void main(String[] args) {

        final int min_threads = 1;
        final int max_threads = 32;
        final int live_time = 60;
        final TimeUnit timeUnit = TimeUnit.SECONDS;

        ExecutorService executor = Executors.newFixedThreadPool(max_threads);

        SwingUtilities.invokeLater(() -> {
            sharedResources = new SharedResources();
            particles = new ArrayList<>();
            canvas = new Main_Canvas("Particle Simulator", sharedResources, executor);
        });
    }
}