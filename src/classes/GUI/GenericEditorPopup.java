package classes.GUI;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.net.URI;
import java.net.URL;
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

    protected void setPopupValues()
    {
        this.setMessage(this.props);
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