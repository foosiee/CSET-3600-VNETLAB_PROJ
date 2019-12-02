package classes.Utils;

import java.util.ArrayList;

import classes.Devices.Device;
import classes.Devices.DeviceManager;

public class Saver
{
    public void save(ArrayList<Device> devices)
    {
        String fileText = "";
        for(Device d : devices)
        {
            fileText += handle(d);
        }
        // Write file text to a file
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
