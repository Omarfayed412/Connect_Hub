package Backend.ProfileAndFriends;

import java.util.ArrayList;
import java.util.List;

 public class Friends implements FriendsInterface {
    private List<String> friends;
    private List<String> blocked;
    // Received friend requests
    private List<String> pending;
    public Friends() {
        friends = new ArrayList<String>();
        blocked = new ArrayList<>();
        pending = new ArrayList<>();
    }

    @Override
    public List<String> getFriends() {
        return friends;
    }
    @Override
    public void addFriends(String  userId) {
        this.friends.add(userId);
    }
    @Override
    public List<String> getBlocked() {
        return blocked;
    }
    @Override
    public void addBlocked(String  userId) {
        this.blocked.add(userId);
    }
    @Override
    public List<String> getPending() {
        return pending;
    }
    @Override
    public void addPending(String  userId) {
        this.pending.add(userId);
    }
    @Override
    public void removeFriends(String  userId) {
        this.friends.remove(userId);
    }
    @Override
    public void removeBlocked(String  userId) {
        this.blocked.remove(userId);
    }
    @Override
    public void acceptFriends(String  userId) {
        this.friends.add(userId);
        this.pending.remove(userId);
    }

    public Boolean isFriend(String userId)
    {
        Boolean flag  = false;
        for(String temp : friends)
        {
            if(temp.matches(userId)) {flag = true;break;}

        }
       if(isBlocked(userId)) flag = false;
       return flag;


    }
     public boolean isPending(String userId){
         return pending.contains(userId);
     }
    /// return true if blocked
     public Boolean isBlocked(String userId)
    {
        Boolean flag  = false;
        for(String temp : blocked)
        {
            if(temp.matches(userId)) {flag = true;break;}

        }
        return flag;

    }
    @Override
    public void declineFriends(String  userId) {
//        this.friends.remove(userId);
        this.pending.remove(userId);
    }
}