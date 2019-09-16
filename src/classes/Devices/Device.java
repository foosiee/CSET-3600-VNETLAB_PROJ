
package classes.Devices;
import classes.GUI.DeviceEditorPopupFactory;
import classes.GUI.GenericEditorPopup;
import classes.GUI.RouterEditorPopup;
import classes.GUI.VmEditorPopup;
import classes.Exceptions.PaneCancelledException;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.net.URI;
import java.net.URL;

import java.util.Random;
public abstract class Device extends JLabel implements MouseListener, MouseMotionListener
{
    protected String name;
    protected String type;
    protected GenericEditorPopup pane;
    protected HashMap<String, String> props;

    private int size = 50;

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
        this.addMouseMotionListener(this);
        this.getLabel();

        Random rand = new Random();
        this.setBounds(rand.nextInt(300), rand.nextInt(300), size, size);
    }

    public void mouseClicked(MouseEvent e)
    {
        ArrayList<String> stringPropList = this.getStringPropList();

        Object[] p = this.pane.getPropsObject();
        GenericEditorPopup edit = this.getOptionPane(p);
        this.showPane(edit);

        String input = (String)edit.getValue();
        if(input == "Edit")
        {
            this.pane = edit;
            this.getPaneProps();
            this.setPanePropsToDevice();
        }
        else
        {
            Object[] objList = this.propListToObject(stringPropList);
            this.pane.setPropsObject(objList);
        }
        this.setPanePropsToDevice();
    }

    public void mouseDragged(MouseEvent e)
    {
        JComponent jc = (JComponent)e.getSource();
        jc.setLocation(jc.getX()-size/2+e.getX(), jc.getY()-size/2+e.getY());
    }

    protected abstract void showDeviceProps();

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

    protected abstract void setDeviceType();
    protected abstract void setPanePropsToDevice();

    private Object[] propListToObject(ArrayList<String> ls)
    {
        ArrayList<Object> objLs = new ArrayList<>();
        for(int i = 0; i < ls.size(); i++)
        {
            if(i % 2 == 0)
            {
                objLs.add(ls.get(i));
            }
            else
            {
                JTextField field = new JTextField(ls.get(i));
                objLs.add(field);
            }
        }
        return objLs.toArray();
    }
    private ArrayList<String> getStringPropList()
    {
        ArrayList<String> temp = new ArrayList<>();
        Object[] objList = this.pane.getPropsObject();
        for(int i = 0; i < objList.length; i++)
        {
            if(i % 2 == 0)
            {
                temp.add((String)objList[i]);
            }
            else
            {
                JTextField field = (JTextField)objList[i];
                temp.add(field.getText());
            }
        }
        return temp;
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