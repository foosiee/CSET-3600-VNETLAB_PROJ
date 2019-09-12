package classes.GUI;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RouterEditorPopup extends GenericEditorPopup
{
    protected JTextField ipAdd = new JTextField();
    protected JTextField subnet = new JTextField();

    public RouterEditorPopup()
    {
        super();
        this.setProps();
    }
    protected void setProps()
    {
        this.props = new Object[]{
            "IP address:", this.ipAdd,
            "Subnet", this.subnet,
            "Name", this.name
        };
        this.setPopupValues();
    }

    protected void setDeviceType()
    {
        this.type = "Router";
    }
}