package classes.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import classes.Devices.*;
import classes.GUI.Notification;

public class Saver
{
    public void save(ArrayList<Device> devices)
    {
        String fileText = "";
        HashMap<String, String> ipMap = new HashMap<>();
        for(Device d : devices)
        {
            if(d.getDeviceType() == "VM")
            {
                fileText += handle(d, ipMap);
            }
        }

        for(Device d : devices)
        {
            if(d.getDeviceType() != "VM")
            {
                fileText += handle(d, ipMap);
            }
        }

        try
        {
            write(fileText);
            Notification.showNotification("Saved file!");
        }
        catch(Exception IOException)
        {
            Notification.showNotification("Error saving file!");
        }
    }

    private String handle(Device d, HashMap<String, String> ipMap)
    {
        String deviceType = d.getDeviceType();
        if (deviceType == "VM")
        {
            return handleVm(d, ipMap);
        }
        else
        {
            return handleRouter(d, ipMap);
        }
    }

    private String handleVm(Device d, HashMap<String, String> ipMap)
    {
        HashMap<String, String>  props = d.getProps();
        ArrayList<Device>  devices = d.getConnectedDevices();
        String ports = "";
        for (int i = 0; i < devices.size(); i++)
        {
            HashMap<String, String> tempProps = devices.get(i).getProps();
            String ip = tempProps.get("IP");
            String temp = String.format("\tetho%d : %s", i, ip);
            String mapOut = d.getDeviceName() + ".etho" + i; 
            ipMap.put(props.get("IP"), mapOut);
            ports += temp + "\n";
        } 
        String output = String.format("VM %s {\n\tos : %s\n%s}\n", d.getDeviceName(), props.get("OS"), ports);
        return output;
    }

    private String handleRouter(Device d, HashMap<String, String> ipMap)
    {
        HashMap<String, String>  props = d.getProps();
        ArrayList<Device> devices = d.getConnectedDevices();
        String interfaces = "\tinf : ";
        for (int i = 0; i < devices.size(); i++)
        {
            HashMap<String, String> tempProps = devices.get(i).getProps();
            String ip = tempProps.get("IP");
            if(ipMap.containsKey(ip))
            {
                interfaces += ipMap.get(ip) + ", ";
            }
        } 
        interfaces += "\n";
        String output = String.format("Router %s {\n\tSubnet : %s\n%s}\n", d.getDeviceName(), props.get("Subnet"), interfaces);
        return output;
    }

    private void write(String content) throws IOException
    {
        String dirName = System.getProperty("user.dir");
        String path = dirName + "/save.cfg";
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(content);
        writer.close();
    }
}
