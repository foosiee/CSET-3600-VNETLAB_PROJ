package classes.Devices;

import java.util.HashMap;
import javax.swing.JDialog;

import classes.Exceptions.PaneCancelledException;
import classes.GUI.RouterEditorPopup;

public class Router extends Device
{
    private String ipAdd;
    private String subnet;

    public Router() throws PaneCancelledException
    {
        super();
    }

    protected void setDeviceType()
    {
        this.type = "Router";
    }

    protected void setPanePropsToDevice()
    {
        for(String prop : this.props.keySet())
        {
            if(prop == "IP")
            {
                this.ipAdd = this.props.get(prop);
            }
            else if(prop == "Subnet")
            {
                this.subnet = this.props.get(prop);
            }
            else if(prop == "Name")
            {
                this.name = this.props.get(prop);
            }
        } 
    }
}