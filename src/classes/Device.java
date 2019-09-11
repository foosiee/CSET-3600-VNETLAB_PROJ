
package classes;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.net.URI;
import java.net.URL;

public class Device extends JLabel
{
    private String name;
    private Map<String, String> fileNames = new HashMap<>()
    {
        {
            put("Virtual Machine", "vm");
            put("Router", "router");
        }
    };

    public Device(String name)
    {
        this.name = name;
        this.setFont(this.getFont().deriveFont(Font.ITALIC));
        this.setHorizontalAlignment(JLabel.CENTER);
        getLabel();
    }

    private void getLabel()
    {
        name = getFileName();
        ImageIcon icon = createImageIcon("/src/assets/" + name + ".png");
        this.setIcon(icon);
        if (icon != null)
        {
            System.out.println("Set icon");
            this.setText(null);
        } else 
        {
            this.setText("Image not found");
        }
    }

    private URL getFileUrl(String path)
    {
        try
        {
            String dirName = System.getProperty("user.dir");
            path = dirName + path;
            File f = new File(path);
            return f.toURI().toURL();
        }
        catch (Exception MalformedURLException)
        {
            return null;
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    private ImageIcon createImageIcon(String path)
    {
        URL imgURL = getFileUrl(path);
        if (imgURL != null) 
        {
            return new ImageIcon(imgURL);
        } else 
        {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private String getFileName()
    {
        return fileNames.get(this.name);
    }
}