package Backend.ProfileAndFriends;

import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;
import Backend.Notifications.FriendRequestNotification;
import Backend.User.User;
import Backend.User.UserInterface;

import java.util.List;

public class FriendManager
{

    private User client;
    private FriendsInterface friends;
    private IUserDatabase database = UserDatabase.getUserDataBase();

    public FriendManager(User client) {
        this.client = client;
        this.friends = this.client.getProfile().getFriends();
    }
    private void refresh(){
        this.client = database.getUser(client.getUserID());
        this.friends = client.getProfile().getFriends();
    }
    public Boolean sendRequest(User friend)
    {
        database.load();
        refresh();
        friend = database.getUser(friend.getUserID());
        friend = database.getUser(friend.getUserID());
        friend.getProfile().getFriends().addPending(this.client.getUserID());
        FriendRequestNotification frn = new FriendRequestNotification(this.client);
        frn.toStringRecieved();
        friend.addFriendRequestNotification(frn);
        database.save();
        return true;
    }
    public List<String> getFriends() {
        database.load();
        refresh();
    return friends.getFriends();
}
    public List<String> getPending() {
        database.load();
        refresh();
        return friends.getPending();
    }

    public List<String> getBlocked() {
        database.load();
        refresh();
        return friends.getBlocked();
    }

    public Boolean isFriend(String userId)
    {
        refresh();
        Boolean flag  = false;
        for(String temp : friends.getFriends())
        {
            if(temp.matches(userId)) {flag = true;break;}

        }
        if(isBlocked(userId)) flag = false;
        return flag;

    }
    public boolean isPending(String userId){
        refresh();
        return friends.getPending().contains(userId);
    }
    public Boolean isBlocked(String userId)
    {
        refresh();
        Boolean flag  = false;
        for(String temp : friends.getBlocked())
        {
            if(temp.matches(userId)) {flag = true;break;}

        }
        return flag;

    }
    public Boolean acceptRequest(User friend)
    {
        database.load();
        refresh();
        friend = database.getUser(friend.getUserID());
        this.friends.acceptFriends(friend.getUserID());
        User user = database.getUser(friend.getUserID());
        user.getProfile().getFriends().addFriends(this.client.getUserID());
        FriendRequestNotification frn = new FriendRequestNotification(client);
        frn.toStringAccepted();
        friend.addFriendRequestNotification(frn);
        database.save();
        return true;
    }
    public  Boolean declineRequest(User friend)
    {
        database.load();
        refresh();
        this.friends.declineFriends(friend.getUserID());
        database.save();
        return true;
    }
    public Boolean blockFriend(User friend)
    {
        database.load();
        refresh();
        friend = database.getUser(friend.getUserID());
        System.out.println("Blocking ====================== " + friend.getUsername());
        this.client.getProfile().getFriends().removeFriends(friend.getUserID());
        this.client.getProfile().getFriends().addBlocked(friend.getUserID());
        User user = database.getUser(friend.getUserID());
        user.getProfile().getFriends().removeFriends(this.client.getUserID());
        database.save();
        return true;
    }
    public Boolean removeFriend(User friend)
    {
        database.load();
        refresh();
        friend = database.getUser(friend.getUserID());
        this.client.getProfile().getFriends().removeFriends(friend.getUserID());
        User user = database.getUser(friend.getUserID());
        user.getProfile().getFriends().removeFriends(this.client.getUserID());
        database.save();
        return true;
    }
    public  Boolean unblockFriend(User friend)
    {
        database.load();
        refresh();
//        friend.getProfile().getFriends().removeBlocked(this.client.getUserID());
        this.client.getProfile().getFriends().removeBlocked(friend.getUserID());
        database.save();
        return true;
    }






}
