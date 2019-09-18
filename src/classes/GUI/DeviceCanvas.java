package classes.GUI;

import classes.Devices.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

public class DeviceCanvas extends JScrollPane
{
    private ArrayList<Connection> connections = new ArrayList<>();

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(Connection c: connections)
        {
            Graphics2D g2 = (Graphics2D) g.create();
            Device one = c.getDeviceOne();
            Device two = c.getDeviceTwo();

            Point2D p1 = new Point2D.Double(one.getBounds().getCenterX(), one.getBounds().getCenterY());
            Point2D p2 = new Point2D.Double(two.getBounds().getCenterX(), two.getBounds().getCenterY());

            g2.draw(new Line2D.Double(p1,p2));
            g2.dispose();
        }
    }

    public void addConnection(Connection c)
    {
        this.connections.add(c);
        this.repaint();
    }
}