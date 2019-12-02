package classes.GUI;

import javax.swing.JOptionPane;

public class Notification
{
    public static void showNotification(String message)
    {
        JOptionPane.showMessageDialog(null,message,"Notification",1);
    }
}