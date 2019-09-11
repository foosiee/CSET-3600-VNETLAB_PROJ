/*
* Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions
* are met:
*
*   - Redistributions of source code must retain the above copyright
*     notice, this list of conditions and the following disclaimer.
*
*   - Redistributions in binary form must reproduce the above copyright
*     notice, this list of conditions and the following disclaimer in the
*     documentation and/or other materials provided with the distribution.
*
*   - Neither the name of Oracle or the names of its
*     contributors may be used to endorse or promote products derived
*     from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
* IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
* THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
* PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
* EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
* PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
* LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
* NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/ 

package classes;

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
    private JList list;
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

    public void valueChanged(ListSelectionEvent e) 
    {
        JList list = (JList)e.getSource();
        String selection = deviceNames[list.getSelectedIndex()];
        System.out.println(selection);
        Device device = new Device(selection);
        device.setBounds(rand.nextInt(300), rand.nextInt(300), 50, 50);
        deviceScrollPane.add(device);
        deviceScrollPane.repaint();
        //updateLabel(deviceNames[list.getSelectedIndex()]);
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

    protected void updateLabel (String name)
    {
        if(name == "Router")
        {
            device.setBounds(0, 50, 100, 100);
        }
        else
        {
            device.setBounds(150,100, 100, 100);
        }
        device.updateLabel(name);
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