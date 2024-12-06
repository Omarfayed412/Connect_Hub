package Backend;
import java.util.List;

public interface FriendsInterface {
    public List<String> getFriends();
    public void addFriends(String  userId);
    public List<String> getBlocked();
    public void addBlocked(String  userId);
    public List<String> getPending();
    public void addPending(String  userId);
    public void removeFriends(String  userId);
    public void removeBlocked(String  userId);
    public void acceptFriends(String  userId);
    public void declineFriends(String  userId);
    public Boolean isBlocked(String userId);
    public Boolean isFriend(String userId);
}
