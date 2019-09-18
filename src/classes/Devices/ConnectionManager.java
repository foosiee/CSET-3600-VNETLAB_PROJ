package classes.Devices;

import java.util.ArrayList;

import javax.swing.JToggleButton;

public class ConnectionManager
{
    private Boolean active = false;
    private ArrayList<Device> selectedDevices;
    private JToggleButton btn;

    public ConnectionManager(JToggleButton btn)
    {
        this.btn = btn;
    }

    public void activate()
    {
        this.active = true;
        selectedDevices = new ArrayList<>();
    }

    public void deactivate()
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
        this.btn.doClick();
    }
}