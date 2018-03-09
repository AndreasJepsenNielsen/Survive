package com.Survive.main;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    public Window(int width, int height, String title, Game game)
    {
        JFrame frame = new JFrame(title); // frame of our window, built in library in java JRE

        frame.setSize(new Dimension(width, height)); // sets the size to be the height and width of Window method
        frame.setMaximumSize(new Dimension(width, height)); // sets the maximum and minimum size so the size will always be the same
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X button closes the game and stop the process
        frame.setResizable(false); // can't resize window
        frame.setLocationRelativeTo(null); // not needed, if set to null window will start in the middle of the screen
        frame.add(game); // adding the game to the frame
        frame.setVisible(true);
        game.start(); // running start method
    }

}
