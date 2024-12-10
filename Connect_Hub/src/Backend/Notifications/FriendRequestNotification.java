/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Notifications;

import Backend.User.User;

/**
 *
 * @author Omar Fayed
 */

public class FriendRequestNotification extends Notification{
    //profile of sender/acceptor
    private User user = null;

    public FriendRequestNotification(User user) {
        this.user = user;
    }
    
    //returns profile of sender/acceptor
    public User getContent() {
        return this.user;
    }
    
    //pass the profile of sender
    public void setContent(User user) {
        this.user = user;
    }
    
    //returns a string containing the info of the notification
    public String toStringAccepted() {
        return this.user.getUsername() + "has accepted your friend request.\n";
    }
    
    public String toStringRecieved() {
        return this.user.getUsername() + "has sent you a friend request.\n";
    }
}
