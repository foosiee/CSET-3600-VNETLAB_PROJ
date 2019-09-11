package classes.GUI;

import classes.Devices.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.net.URI;
import java.net.URL;

import java.util.Random;

public class SplitPanel extends JPanel implements ActionListener
{
    //private Device device;
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private JSplitPane pane;
    private String[] deviceNames = {"Router", "Virtual Machine"};
    private JScrollPane deviceScrollPane;

    private Random rand = new Random();

    public SplitPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        createButtons();
        addButtons(panel);

        JScrollPane listScrollPane = new JScrollPane(panel);

        deviceScrollPane = new JScrollPane();
        deviceScrollPane.setLayout(null);

        pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                  listScrollPane, deviceScrollPane);
        pane.setOneTouchExpandable(true);
        pane.setDividerLocation(150);

        Dimension minimumSize = new Dimension(100, 50);
        listScrollPane.setMinimumSize(minimumSize);
        deviceScrollPane.setMinimumSize(minimumSize);

        pane.setPreferredSize(new Dimension(800, 400));
    }

    public void actionPerformed(ActionEvent e)
    {
        // display/center the jdialog when the button is pressed
        // JDialog d = new JDialog(deviceScrollPane, "Hello", true);
        // d.setLocationRelativeTo(deviceScrollPane);
        // d.setVisible(true);

        JButton sourceBtn = (JButton)e.getSource();
        String name = sourceBtn.getName();
        Device device = new Device(name);
        device.setBounds(rand.nextInt(300), rand.nextInt(300), 50, 50);
        deviceScrollPane.add(device);
        deviceScrollPane.repaint();
    }

    private void createButtons()
    {
        JButton btn;
        for(String b : deviceNames)
        {
            btn = new JButton(b);
            btn.setName(b);
            btn.addActionListener(this);
            buttons.add(btn);
        }
    }

    private void addButtons(JPanel panel)
    {
        for(JButton btn : buttons)
        {
            panel.add(btn);
        }
    }

    public JSplitPane getSplitPane()
    {
        return pane;
    }
}