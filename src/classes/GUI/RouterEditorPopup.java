package classes.GUI;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RouterEditorPopup extends GenericEditorPopup
{
    public JTextField ipAdd = new JTextField();
    public JTextField subnet = new JTextField();

    public RouterEditorPopup()
    {
        super();
        this.setProps();
        //his.showPopup();
        //System.out.println(ipAdd.getText());
    }

    public RouterEditorPopup(Object[] props)
    {
        super("Router", props);
    }

    protected void setProps()
    {
        this.props = new Object[]{
            "IP", this.ipAdd,
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