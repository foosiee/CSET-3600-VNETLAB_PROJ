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
        // JTextField username = new JTextField();
        // JTextField password = new JPasswordField();
        // JTextField objName = new JTextField();
        // Object[] message = {
        //     "IP address:", username,
        //     "Subnet", password,
        //     "Name", objName
        // };

        // String[] options = new String[2];
        // options[1] = "Cancel";
        // options[0] = "Create";
        
        // JOptionPane op = new JOptionPane();
        // op.setMessage(message);
        // op.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        // op.setOptions(options);
        // JDialog d = op.createDialog("Set Props");
        // d.setVisible(true);
        // Object input = op.getValue();
        // // int option = JOptionPane.showOptionDialog(null, message, "Set Props", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
        // if(input != null)
        // {
        //     String x = input.toString();
        //     if(x == options[0])
        //     {
        //         System.out.println("Object created");
    
        //     }
        //     else
        //     {
        //         System.out.println("Cancelled");
        //     }
        // }
        // else
        // {
        //     System.out.println("Xed out");
        // }

        JButton sourceBtn = (JButton)e.getSource();
        String name = sourceBtn.getName();
        // DeviceEditorPopupFactory factory = new DeviceEditorPopupFactory();
        // JOptionPane p = factory.create(name);

        // Device device = new Device(name);
        DeviceFactory factory = new DeviceFactory();
        Device device = null;
        try
        {
            device = factory.create(name);
            device.setBounds(rand.nextInt(300), rand.nextInt(300), 50, 50);
            deviceScrollPane.add(device);
            deviceScrollPane.repaint();
        }
        catch(PaneCancelledException ex)
        {
            System.out.print("ex");
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
    }

    public JSplitPane getSplitPane()
    {
        return pane;
    }
}