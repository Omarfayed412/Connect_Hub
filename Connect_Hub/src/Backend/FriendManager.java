package Backend;

import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;

import java.util.List;

public class FriendManager
{

    private UserInterface client;
    private FriendsInterface friends;
    private IUserDatabase database = UserDatabase.getUserDataBase();

    public FriendManager(UserInterface client) {
        this.client = client;
        this.friends = this.client.getProfile().getFriends();
    }
    private void refresh(){
        this.client = database.getUser(client.getUserID());
        this.friends = client.getProfile().getFriends();
    }
    public Boolean sendRequest(UserInterface friend)
    {
        refresh();
        friend.getProfile().getFriends().addPending(this.client.getUserID());
        return true;
    }
    public List<String> getFriends() {
        refresh();
    return friends.getFriends();
}
    public List<String> getPending() {
        refresh();
        return friends.getPending();
    }

    public List<String> getBlocked() {
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
        refresh();
        this.friends.acceptFriends(friend.getUserID());
        User user = database.getUser(friend.getUserID());
        user.getProfile().getFriends().addFriends(this.client.getUserID());
        return true;
    }
    public  Boolean declineRequest(User friend)
    {
        refresh();
        friend.getProfile().getFriends().declineFriends(this.client.getUserID());
        return true;
    }
    public Boolean blockFriend(User friend)
    {
        refresh();
        this.client.getProfile().getFriends().removeFriends(friend.getUserID());
        this.client.getProfile().getFriends().addBlocked(this.client.getUserID());
        User user = database.getUser(friend.getUserID());
        user.getProfile().getFriends().removeFriends(this.client.getUserID());
        return true;
    }
    public Boolean removeFriend(User friend)
    {
        refresh();
        this.client.getProfile().getFriends().removeFriends(this.client.getUserID());
        User user = database.getUser(friend.getUserID());
        friend.getProfile().getFriends().removeFriends(this.client.getUserID());
        return true;
    }
    public  Boolean unblockFriend(User friend)
    {
        refresh();
//        friend.getProfile().getFriends().removeBlocked(this.client.getUserID());
        this.client.getProfile().getFriends().removeBlocked(friend.getUserID());
        return true;
    }






}
