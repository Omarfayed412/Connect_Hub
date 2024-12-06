package Backend;

import java.util.List;

public class FriendManager
{

    private User client;
    private FriendsInterface friends;

    public FriendManager(User client) {
        this.client = client;
        this.friends = this.client.getProfile().getFriends();
    }

    public Boolean sendRequest(User friend)
    {
        friend.getProfile().getFriends().addPending(this.client.getUserID());
        return true;
    }
    public List<String> getFriends() {
    return friends.getFriends();
}
    public List<String> getPending() {
        return friends.getPending();
    }
    public Boolean isFriend(String userId)
    {
        Boolean flag  = false;
        for(String temp : friends.getFriends())
        {
            if(temp.matches(userId)) {flag = true;break;}

        }
        if(isBlocked(userId)) flag = false;
        return flag;

    }
    public Boolean isBlocked(String userId)
    {
        Boolean flag  = false;
        for(String temp : friends.getBlocked())
        {
            if(temp.matches(userId)) {flag = true;break;}

        }
        return flag;

    }
    public Boolean acceptRequest(User friend)
    {
        this.friends.acceptFriends(friend.getUserID());
        friend.getProfile().getFriends().addFriends(this.client.getUserID());
        return true;
    }
    public  Boolean declineRequest(User friend)
    {
        friend.getProfile().getFriends().declineFriends(this.client.getUserID());
        return true;
    }
    public Boolean blockFriend(User friend)
    {
        this.client.getProfile().getFriends().addBlocked(this.client.getUserID());
//        friend.getProfile().getFriends().addBlocked(this.client.getUserID());
        return true;
    }
    public Boolean removeFriend(User friend)
    {
        this.client.getProfile().getFriends().removeFriends(this.client.getUserID());
        friend.getProfile().getFriends().removeFriends(this.client.getUserID());
        return true;
    }
    public  Boolean unblockFriend(User friend)
    {
//        friend.getProfile().getFriends().removeBlocked(this.client.getUserID());
        this.client.getProfile().getFriends().removeBlocked(this.client.getUserID());
        return true;
    }






}
