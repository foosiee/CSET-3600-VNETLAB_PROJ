package classes.Devices;

public class Connection
{
    private Device one;
    private Device two;

    public Connection(Device a, Device b)
    {
        this.one = a;
        this.two = b;
    }

    public Device getDeviceOne()
    {
        return this.one;
    }

    public Device getDeviceTwo()
    {
        return this.two;
    }
}