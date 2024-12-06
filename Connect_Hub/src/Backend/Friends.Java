package Backend;

import java.util.ArrayList;
import java.util.List;

 class Friends implements FriendsInterface {
    private List<String> friends;
    private List<String> blocked;
    // Received friend requests
    private List<String> pending;
    public Friends() {
        friends = new ArrayList<String>();
        blocked = new ArrayList<>();
        pending = new ArrayList<>();
    }

    public List<String> getFriends() {
        return friends;
    }
    public void addFriends(String  userId) {
        this.friends.add(userId);
    }
    public List<String> getBlocked() {
        return blocked;
    }
    public void addBlocked(String  userId) {
        this.blocked.add(userId);
    }
    public List<String> getPending() {
        return pending;
    }
    public void addPending(String  userId) {
        this.pending.add(userId);
    }
    public void removeFriends(String  userId) {
        this.friends.remove(userId);
    }
    public void removeBlocked(String  userId) {
        this.blocked.remove(userId);
    }
    public void acceptFriends(String  userId) {
        this.friends.add(userId);
        this.pending.remove(userId);
    }

    public void declineFriends(String  userId) {
//        this.friends.remove(userId);
        this.pending.remove(userId);
    }





}