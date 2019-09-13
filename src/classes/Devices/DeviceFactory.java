package classes.Devices;

import classes.Exceptions.PaneCancelledException;

public class DeviceFactory
{
    public Device create(String type) throws PaneCancelledException
    {
        try
        {
            if(type == "Router")
            {
                return new Router();
            }
            else
            {
                return new Vm();
            }
        }
        catch(PaneCancelledException ex)
        {
            throw ex;
        }
    }
}