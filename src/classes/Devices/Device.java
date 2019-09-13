
package classes.Devices;
import classes.GUI.DeviceEditorPopupFactory;
import classes.GUI.GenericEditorPopup;
import classes.GUI.RouterEditorPopup;
import classes.GUI.VmEditorPopup;
import classes.Exceptions.PaneCancelledException;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.net.URI;
import java.net.URL;

public abstract class Device extends JLabel implements MouseListener
{
    protected String name;
    protected String type;
    protected GenericEditorPopup pane;
    protected HashMap<String, String> props;
    private Map<String, String> fileNames = new HashMap<>()
    {
        {
            put("Virtual Machine", "vm");
            put("Router", "router");
        }
    };

    public Device() throws PaneCancelledException
    {
        this.setDeviceType();
        pane = this.getOptionPane();
        this.showPane();
        try
        {
            this.handlePaneClose();
        }
        catch(PaneCancelledException ex)
        {
            throw new PaneCancelledException("Pane was cancelled");
        }
        this.getPaneProps();
        this.setPanePropsToDevice();
        this.setFont(this.getFont().deriveFont(Font.ITALIC));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.addMouseListener(this);
        getLabel();
    }

    public void mouseClicked(MouseEvent e)
    {
        Object[] p = this.pane.getPropsObject();
        GenericEditorPopup edit =  this.getOptionPane(p);
        this.showPane(edit);

        String input = (String)edit.getValue();
        if(input == "Edit")
        {
            this.pane = edit;
            this.getPaneProps();
            this.setPanePropsToDevice();
        }
    }


    protected abstract void setDeviceType();
    protected abstract void setPanePropsToDevice();
    protected void showEditPane()
    {
        Object[] p = this.pane.getPropsObject();
        GenericEditorPopup editPop = getOptionPane(p);
        JDialog d = editPop.createDialog("Edit Props");
        d.setVisible(true);
    }

    protected GenericEditorPopup getOptionPane()
    {
        DeviceEditorPopupFactory factory = new DeviceEditorPopupFactory();
        return factory.create(this.type);
    }

    protected GenericEditorPopup getOptionPane(Object[] props)
    {
        DeviceEditorPopupFactory factory = new DeviceEditorPopupFactory();
        return factory.create(this.type, props);
    }

    protected void getPaneProps()
    {
        this.props = this.pane.getProps();
    }

    private void handlePaneClose() throws PaneCancelledException
    {
        String input = (String)this.pane.getValue();
        if(input == "Cancel")
        {
            throw new PaneCancelledException("Pane was cancelled.");
        }
    }

    private void showPane()
    {
        JDialog d = this.pane.createDialog("Set Props");
        d.setVisible(true);
    }

    private void showPane(GenericEditorPopup popup)
    {
        JDialog d = popup.createDialog("Set Props");
        d.setVisible(true);
    }

    private void getLabel()
    {
        ImageIcon icon = createImageIcon("/src/assets/" + this.type + ".png");
        this.setIcon(icon);
        if (icon != null)
        {
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

    // interface forces to implement these
    public void mouseEntered(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e)
    {

    }

    public void mouseReleased(MouseEvent e)
    {

    }

    public void mousePressed(MouseEvent e)
    {
        
    }
}