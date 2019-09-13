package classes.GUI;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JDialog;
import javax.swing.JLabel;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.io.File;

public abstract class GenericEditorPopup extends JOptionPane 
{
    protected String type;
    protected JTextField name = new JTextField();
    protected Object[] props;
    protected String[] options = new String[2];

    public GenericEditorPopup() 
    {
        this.setDeviceType();
        this.setDeviceIcon();

        options[0] = "Create";
        options[1] = "Cancel";
        this.setOptions(this.options);

        this.setMessageType(JOptionPane.INFORMATION_MESSAGE);
    }

    public GenericEditorPopup(String type, Object[] props)
    {
        this.type = type;
        this.props = props;
        this.setDeviceIcon();
        options[0] = "Edit";
        options[1] = "Cancel";
        this.setOptions(this.options);
        this.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        this.setPopupValues();
    }

    public HashMap<String,String> getProps()
    {
        int i;
        HashMap<String,String> map = new HashMap<>();
        for(i = 0; i < this.props.length; i+=2)
        {
            JTextField field = (JTextField)this.props[i+1];
            String fieldText = field.getText();
            map.put((String)this.props[i], fieldText);
        }
        return map;
    }

    public void setPropsObject(Object[] props)
    {
        this.props = props;
    }

    public Object[] getPropsObject()
    {
        return this.props;
    }

    protected void setPopupValues()
    {
        this.setMessage(this.props);
    }

    protected void showPopup()
    {
        JDialog d = this.createDialog("Set Props");
        d.setVisible(true);
    }

    private void setDeviceIcon()
    {
        String path;
        URL imgURL;

        path = "/src/assets/" + this.type + ".png";
        imgURL = getFileUrl(path);
        if(imgURL != null)
        {
            this.setIcon(new ImageIcon(imgURL));
        }
    }

    private URL getFileUrl(String path)
    {
        String dirName;
        File f;

        try
        {
            dirName = System.getProperty("user.dir");
            path = dirName + path;
            f = new File(path);
            return f.toURI().toURL();
        }
        catch (Exception MalformedURLException)
        {
            return null;
        }
    }
    protected abstract void setProps();
    protected abstract void setDeviceType();
}