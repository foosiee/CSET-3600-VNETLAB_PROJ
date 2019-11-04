package classes.GUI;

import classes.Devices.*;
import classes.Exceptions.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.net.URI;
import java.net.URL;

import java.util.Random;

public class SplitPanel extends JPanel implements ActionListener//, ChangeListener
{
    //private Device device;
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private JSplitPane pane;
    private String[] deviceNames = {"Router", "Virtual Machine"};
    private DeviceCanvas deviceScrollPane = new DeviceCanvas();
    private JToggleButton toggleButton;
    private DeviceManager manager;
    
    public SplitPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        createButtons();
        addButtons(panel);

        JScrollPane listScrollPane = new JScrollPane(panel);
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
        AbstractButton sourceBtn = (AbstractButton)e.getSource();
        String name = sourceBtn.getText();

        if(name == "Connection")
        {
            if(toggleButton.isSelected())
            {
                manager.activateConnection();
            }
            else
            {
                manager.deactivateConnection();
            }
        }
        else
        {
            DeviceFactory factory = new DeviceFactory();
            Device device = null;
            try
            {
                device = factory.create(name);
                this.manager.add(device);
                deviceScrollPane.add(device);
                deviceScrollPane.repaint();
            }
            catch(PaneCancelledException ex)
            {
                System.out.print("ex");
            }
        }
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

        toggleButton = new JToggleButton("Connection");
        toggleButton.addActionListener(this);
        manager = new DeviceManager(toggleButton, deviceScrollPane);

        var saveButton = new JButton("Save");
        var loadButton = new JButton("Load");

        panel.add(toggleButton);
        panel.add(saveButton);
        panel.add(loadButton);
    }

    public JSplitPane getSplitPane()
    {
        return pane;
    }
}