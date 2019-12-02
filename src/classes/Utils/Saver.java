package classes.Utils;

import java.util.ArrayList;

import classes.Devices.Device;
import classes.Devices.DeviceManager;

public class Saver
{
    public void save(ArrayList<Device> devices)
    {
        for(Device d : devices)
        {
            System.out.println(d.getDeviceType());
        }
    }

    private String handle(Device d)
    {
        String deviceType = d.getDeviceType();
        if (deviceType == "VM")
        {
            return handleVm(d);
        }
        else
        {
            return handleRouter(d);
        }
    }

    private String handleVm(Device d)
    {
        String output = "VM ";
        return output;
    }

    private String handleRouter(Device d)
    {
        String output = "VM ";
        return output;
    }
}
