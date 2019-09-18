package classes.Devices;

import classes.GUI.*;
import java.util.ArrayList;

import javax.swing.JToggleButton;

public class DeviceManager
{
    private Boolean active = false;
    private ArrayList<Device> selectedDevices;
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
        selectedDevices = new ArrayList<>();
    }

    public void deactivateConnection()
    {
        this.active = false;
        selectedDevices = null;
    }

    public void add(Device d)
    {
        if(this.active && !selectedDevices.contains(d) && selectedDevices.size() < 2)
        {
            selectedDevices.add(d);
            if(selectedDevices.size() == 2)
            {
                this.createConnection();
            }
        }
    }

    public Boolean getState()
    {
        return this.active;
    }

    private void createConnection()
    {
        Connection c = new Connection(this.selectedDevices.get(0), this.selectedDevices.get(1));
        this.connections.add(c);
        this.canvas.addConnection(c);
        this.btn.doClick();
    }

    public void repaintCanvas()
    {
        this.canvas.repaint();
    }
}