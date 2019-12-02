package classes.GUI;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VmEditorPopup extends GenericEditorPopup
{
    public JTextField ipAdd = new JTextField();
    public JTextField os = new JTextField();

    public VmEditorPopup()
    {
        super();
        this.setProps();
        //this.showPopup();
    }

    public VmEditorPopup(Object[] props)
    {
        super("vm", props);
    }

    protected void setProps()
    {
        this.props = new Object[]{
            "IP", this.ipAdd,
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