package classes.Devices;

import classes.Exceptions.PaneCancelledException;
import classes.GUI.VmEditorPopup;
import javax.swing.JDialog;

public class Vm extends Device
{
    private String ipAdd;
    private String os;

    public Vm() throws PaneCancelledException
    {
        super();
    }

    protected void setDeviceType()
    {
        this.type = "VM";
    }

    protected void setPanePropsToDevice()
    {
        for(String prop : this.props.keySet())
        {
            if(prop == "IP")
            {
                this.ipAdd = this.props.get(prop);
            }
            else if(prop == "OS")
            {
                this.os = this.props.get(prop);
            }
            else if(prop == "Name")
            {
                this.name = this.props.get(prop);
            }
        } 
    }
}