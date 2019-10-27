package classes.Driver;

import classes.GUI.*;

public class VNETLAB
{
    public static void main(String[] args) 
    {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() {
                Window.createAndShow(); //
            }
        });
    }
}