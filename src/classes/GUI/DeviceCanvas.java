package classes.GUI;

import classes.Devices.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DeviceCanvas extends JScrollPane
{
    private ArrayList<Connection> connections = new ArrayList<>();

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }
}