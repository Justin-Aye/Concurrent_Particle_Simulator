package views.explore_mode;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

public class Sprite {

    public static BufferedImage[] currentImages;
    private int currentImageIndex = 0;
    private static String[] frontPaths = {
            "images/f1.png",
            "images/f2.png",
            "images/f3.png",
            "images/f4.png",
            "images/f5.png",
            "images/f6.png",
            "images/f7.png",
            "images/f8.png",
            "images/f9.png"
    };
    private static String[] backPaths = {
        "images/b1.png",
        "images/b2.png",
        "images/b3.png",
        "images/b4.png",
        "images/b5.png",
        "images/b6.png",
        "images/b7.png",
        "images/b8.png",
        "images/b9.png"
    };
    private static String[] leftPaths = {
        "images/l1.png",
        "images/l2.png",
        "images/l3.png",
        "images/l4.png",
        "images/l5.png",
        "images/l6.png",
        "images/l7.png",
        "images/l8.png",
        "images/l9.png"
    };
    private static String[] rightPaths = {
        "images/r1.png",
        "images/r2.png",
        "images/r3.png",
        "images/r4.png",
        "images/r5.png",
        "images/r6.png",
        "images/r7.png",
        "images/r8.png",
        "images/r9.png"
    };
    

    private static String[] currentPaths;
    private Timer timer;

    public Sprite(String direction) {
        switch(direction){
            case "front":
                currentPaths = frontPaths;
                break;
            case "back":
                currentPaths = backPaths;
                break;
            case "left":
                currentPaths = leftPaths;
                break;
            case "right":
                currentPaths = rightPaths;
                break;
            default:
                System.out.println("Null string");
                currentPaths = frontPaths;
                break;
        }
        loadImages();
        // Create Timer to change image every 1 second
        timer = new Timer(170, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextImage();
            }
        });
        timer.start();
    }

    private void nextImage() {
            currentImageIndex = (currentImageIndex + 1) % currentPaths.length;
    }

    private void loadImages() {
         // Load front images
         currentImages = new BufferedImage[currentPaths.length];
         for (int i = 0; i < currentPaths.length; i++) {
             try {
                 currentImages[i] = ImageIO.read(getClass().getResource(currentPaths[i]));
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
    }

     public Image getImage(){
        return currentImages[currentImageIndex].getScaledInstance(200, 200, Image.SCALE_SMOOTH);
     }


     public Image pauseImage(){
        return currentImages[0].getScaledInstance(200, 200, Image.SCALE_SMOOTH);
     }
     

     public void walkToFront(){
        currentPaths = frontPaths;
        loadImages();
     }

     public void walkToBack(){
        currentPaths = backPaths;
        loadImages();
     }

     public void walkToLeft(){
        currentPaths = leftPaths;
        loadImages();
     }

     public void walkToRight(){
        currentPaths = rightPaths;
        loadImages();
     }

}
