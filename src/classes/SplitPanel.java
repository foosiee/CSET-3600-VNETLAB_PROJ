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

public class SplitPanel extends JPanel implements ListSelectionListener, MouseMotionListener
{
    private Device device;
    private JList list;
    private JSplitPane pane;
    private String[] deviceNames = {"Router", "Virtual Machine"};

    public SplitPanel()
    {
        list = new JList(deviceNames);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        
        JScrollPane listScrollPane = new JScrollPane(list);
        device = new Device();
        device.setFont(device.getFont().deriveFont(Font.ITALIC));
        device.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane deviceScrollPane = new JScrollPane();
        deviceScrollPane.setLayout(null);
        deviceScrollPane.add(device);

        pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                  listScrollPane, deviceScrollPane);
        pane.setOneTouchExpandable(true);
        pane.setDividerLocation(150);

        Dimension minimumSize = new Dimension(100, 50);
        listScrollPane.setMinimumSize(minimumSize);
        deviceScrollPane.setMinimumSize(minimumSize);

        pane.setPreferredSize(new Dimension(800, 400));
        updateLabel(deviceNames[list.getSelectedIndex()]);
    }

    public void valueChanged(ListSelectionEvent e) 
    {
        JList list = (JList)e.getSource();
        updateLabel(deviceNames[list.getSelectedIndex()]);
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

    public JSplitPane getSplitPane()
    {
        return pane;
    }
}