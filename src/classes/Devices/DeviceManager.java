package classes.Devices;

import classes.GUI.*;
import java.util.ArrayList;

import javax.swing.JToggleButton;

public class DeviceManager
{
    private Boolean active = false;
    private ArrayList<Device> selectedDevices;
    private ArrayList<Device> allDevices = new ArrayList<>();
    private JToggleButton btn;
    private ArrayList<Connection> connections = new ArrayList<>();
    private DeviceCanvas canvas;

    public DeviceManager(JToggleButton btn, DeviceCanvas canvas)
    {
        this.btn = btn;
        this.canvas = canvas;
    }

    public void activateConnection()
    {
        this.active = true;
        this.selectedDevices = new ArrayList<>();
    }

    public void deactivateConnection()
    {
        this.active = false;
        this.selectedDevices = null;
    }

    public void addDeviceForConnection(Device d)
    {
        if(this.active && !selectedDevices.contains(d) && selectedDevices.size() < 2)
        {
            this.selectedDevices.add(d);
            if(this.selectedDevices.size() == 2)
            {
                this.createConnection();
            }
        }
    }

    public Boolean getState()
    {
        return this.active;
    }

    public void repaintCanvas()
    {
        this.canvas.repaint();
    }

    public void add(Device d)
    {
        this.allDevices.add(d);
        d.setManager(this);
    }

    public ArrayList<Device> getDevices()
    {
        return allDevices;
    }

    private void createConnection()
    {
        Device a = this.selectedDevices.get(0);
        Device b = this.selectedDevices.get(1);
        this.connectionRules(a, b);

        a.addConnectedDevice(b);
        b.addConnectedDevice(a);

        Connection c = new Connection(a,b);
        this.connections.add(c);
        this.canvas.addConnection(c);
        this.btn.doClick();
    }

    private void connectionRules(Device a, Device b)
    {
        if(a.getDeviceType() == "VM" && b.getDeviceType() == "VM")
        {
            System.out.println("VMs can't connect to each other");
        }
    }
}