/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Database;

import Backend.Notifications.Notification;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Omar Fayed
 */
public class NotfFDatabase implements INotfDatabase{
    private static NotfFDatabase notfDataBase = null;
    private static ArrayList<Notification> notificationList = null;
    private static final String notifications_json = "notification.json";
    private static Gson gson = null;
    private static int numberOfnotification;

    private NotfFDatabase() {
    }

    synchronized public static NotfFDatabase getGroupsDataBase() {
        if(notfDataBase == null)
        {
            notfDataBase = new NotfFDatabase();
            innerLoad();
        }
        return notfDataBase;
    }

    @Override
    public void save() {
        //serialization into users.json......
        try {
            FileWriter writer = new FileWriter(notifications_json);
            gson.toJson(notificationList, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Notification> deserializeUsers() {
        try (FileReader reader = new FileReader(notifications_json)) {
            Type type = new TypeToken<List<Notification>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            System.err.println("Error reading Notifications from file: " + e.getMessage());
            return new ArrayList<Notification>();
        }
    }

    private static void innerLoad() {
        notificationList = notfDataBase.deserializeUsers();
        if(notificationList == null)
            notificationList = new ArrayList<Notification>();
    }
    
    @Override
    public void load() {
        try {
            FileReader reader = new FileReader(notifications_json);
            Type type = new TypeToken<ArrayList<Notification>>(){}.getType();
            notificationList = gson.fromJson(reader,type);
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Notification> getNotifications() {
        return notificationList;
    }
    
    @Override
    public synchronized boolean addNotification(Notification notification) {
        notificationList.add(notification);
        save();
        return true;
    }
    
    @Override
    public synchronized boolean removeNotification(Notification notification) {
        notificationList.remove(notification);
        save();
        return true;
    }
    
    @Override
    public synchronized Notification getNotification(String Id)
    {
        for (Notification i : notificationList)
           if(i.getID().equalsIgnoreCase(Id))
               return i;
        return null;
    }
}
