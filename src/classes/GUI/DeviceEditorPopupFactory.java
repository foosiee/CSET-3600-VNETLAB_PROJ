package classes.GUI;

import javax.swing.JOptionPane;

import classes.Exceptions.PaneCancelledException;

public class DeviceEditorPopupFactory
{ 
    public GenericEditorPopup create(String objectName)
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

    public GenericEditorPopup create(String objectName, Object[] props)
    {
        if(objectName == "Router")
        {
            return new RouterEditorPopup(props);
        }
        else
        {
            return new VmEditorPopup(props);
        }
    }
}