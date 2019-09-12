package classes.GUI;

import javax.swing.JOptionPane;

public class DeviceEditorPopupFactory
{ 
    public JOptionPane create(String objectName)
    {
        if(objectName == "Router")
        {
            return new RouterEditorPopup();
        }
        else
        {
            return new VmEditorPopup();
        }
    }
}