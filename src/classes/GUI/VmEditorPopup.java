package classes.GUI;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VmEditorPopup extends GenericEditorPopup
{
    protected JTextField ipAdd = new JTextField();
    protected JTextField os = new JTextField();

    public VmEditorPopup()
    {
        super();
        this.setProps();
    }

    protected void setProps()
    {
        this.props = new Object[]{
            "IP address:", this.ipAdd,
            "OS", this.os,
            "Name", this.name
        };
        this.setPopupValues();
    }

    protected void setDeviceType()
    {
        this.type = "VM";
    }
}