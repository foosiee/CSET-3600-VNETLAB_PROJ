package classes.GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
public class Window
{
    public static void createAndShow()
    {
        JFrame frame = new JFrame("VNETLAB");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SplitPanel window = new SplitPanel();
        frame.getContentPane().add(window.getSplitPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}