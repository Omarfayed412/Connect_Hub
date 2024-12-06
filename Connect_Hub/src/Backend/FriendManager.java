package Backend;

public class FriendManager
{

    private UserInterfaceID client;
    private FriendsInterface friends;

    public FriendManager(UserInterfaceID client) {
        this.client = client;
        this.friends = this.client.getProfile().getFriends();
    }

    public Boolean sendRequest(User friend)
    {
        friend.getProfile().getFriends().addPending(this.client.getUserID());
        return true;
    }
    public Boolean acceptRequest(User friend)
    {
        friends.acceptFriends(friend.getUserID());
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
