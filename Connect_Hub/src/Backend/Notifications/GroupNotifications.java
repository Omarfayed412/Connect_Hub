/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Notifications;

import Backend.ContentCreation.AbstractContent;
import Backend.Group;
import Backend.User.User;

/**
 *
 * @author Omar Fayed
 */
public class GroupNotifications  extends Notification{
    private Group group = null;
    private AbstractContent content = null;
    private User user = null;
    
    public GroupNotifications() {
        
    }

    public Group getGroup() {
        try {
            return this.group;
        } catch (NullPointerException e) {
            System.out.println("No group for this notification.");
            return null;
        }
    }

    public AbstractContent getContent() {
        try {
            return this.content;
        } catch (NullPointerException e) {
            System.out.println("No Content for this notification.");
            return null;
        }
    }

    public User getUser() {
        try {
            return this.user;
        } catch (NullPointerException e) {
            System.out.println("No Users for this notification.");
            return null;
        }
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setContent(AbstractContent content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String toStringAccepted() {
        try {
            return "You have been added to group " + this.group.getName() + ".\n";
        } catch (NullPointerException e) {
            System.out.println("No group added to the notification.");
            return null;
        }    
    }
    
    public String toStringNewPost() {
        try {
            return "A new post has been added to your group " + this.group.getName() + ".\n";
        } catch (NullPointerException e) {
            System.out.println("No group added to the notification.");
            return null;
        }
    }
    
    public String toStringStatus() {
        try {
            return "Your group " + this.group.getName() + " has changed its status.\n";
        } catch (NullPointerException e) {
            System.out.println("No group added to the notification.");
            return null;
        }
    }
    
    public String toStringRequest() {
        try {
            return this.user.getUsername() + " Requested to enter your group " + this.group.getName() + ".\n";
        } catch (NullPointerException e) {
            System.out.println("No group or user added to the notification.");
            return null;
        }
    }
}
