/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Notifications;

import Backend.ContentCreation.AbstractContent;
import Backend.ContentCreation.IContent;
import Backend.Database.*;
import Backend.GroupManagement.Group;
import Backend.User.User;

/**
 *
 * @author Omar Fayed
 */
public class GroupNotifications  extends Notification{
    private String groupID;
    private String contentID ;
    private String userID;
    private IUserDatabase userDatabase = UserDatabase.getUserDataBase();
    private IContentDatabase contentDatabase = ContentDatabase.getInstance();
    private GroupsInterface groupsDatabase = GroupsDataBase.getGroupsDataBase();
    private INotfDatabase notfDatabase = NotfFDatabase.getNotfFDataBase();
    
    public GroupNotifications() {
        
    }

    public Group getGroup() {
        Group group = groupsDatabase.getGroup(groupID);
        try {
            return group;
        } catch (NullPointerException e) {
            System.out.println("No group for this notification.");
            return null;
        }
    }

    public IContent getContent() {
        IContent content = contentDatabase.getContent(contentID);
        try {
            return content;
        } catch (NullPointerException e) {
            System.out.println("No Content for this notification.");
            return null;
        }
    }

    public User getUser() {
        User user = userDatabase.getUser(userID);
        try {
            return user;
        } catch (NullPointerException e) {
            System.out.println("No Users for this notification.");
            return null;
        }
    }

    public void setGroup(Group group) {
        this.groupID = group.getGroupID();
    }

    public void setContent(AbstractContent content) {
        this.contentID = content.getContentId();
    }

    public void setUser(User user) {
        this.userID = user.getUserID();
    }
    
    public void toStringAccepted() {
        try {
            super.setSContent("You have been added to group " + getGroup().getName() + ".\n");
        } catch (NullPointerException e) {
            System.out.println("No group added to the notification.");
        }    
    }
    
    public void toStringNewPost() {
        try {
            super.setSContent("A new post has been added to your group " + getGroup().getName() + ".\n");
        } catch (NullPointerException e) {
            System.out.println("No group added to the notification.");
        }
    }
    
    public void toStringStatus() {
        try {
            super.setSContent("Your group " + getGroup().getName() + " has changed its status.\n");
        } catch (NullPointerException e) {
            System.out.println("No group added to the notification.");
        }
    }
    
    public void toStringRequest() {
        try {
            super.setSContent(getUser().getUsername() + " Requested to enter your group " + getGroup().getName() + ".\n");
        } catch (NullPointerException e) {
            System.out.println("No group or user added to the notification.");
        }
    }
}
