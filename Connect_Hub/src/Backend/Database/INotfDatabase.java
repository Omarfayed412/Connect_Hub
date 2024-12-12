/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Backend.Database;

import Backend.Notifications.Notification;
import java.util.ArrayList;

/**
 *
 * @author Omar Fayed
 */
public interface INotfDatabase extends Database{
    public boolean addNotification(Notification notification);
    public boolean removeNotification(Notification notification);
    public Notification getNotification(String Id);
    public ArrayList<Notification> getNotifications();
}
