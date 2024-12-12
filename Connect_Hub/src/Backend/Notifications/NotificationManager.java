/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Notifications;

import java.util.ArrayList;

/**
 *
 * @author Omar Fayed
 */
public class NotificationManager {
    private ArrayList<Notification> notificationList = null;

    public NotificationManager() {
        this.notificationList = new ArrayList<>();
    }

    public ArrayList<Notification> getNotificationList() {
        return notificationList;
    }
    
    public void addNotification(Notification notification) {
        this.notificationList.add(notification);
    }
    
    public boolean removeNotification(String Id) {
        try {
        Notification notification = getNotification(Id);
        this.notificationList.remove(notification);
        return true;
        } catch (NullPointerException e) {
            System.out.println("Notification not found or empty Notification list.");
            return false;
        }
    }
    
    public Notification getNotification(String Id) {
        for (Notification i : this.notificationList) {
            if (i.getID().equalsIgnoreCase(Id)) 
                return i;
        }
        System.out.println("Notification not found.");
        return null;
    }
}
