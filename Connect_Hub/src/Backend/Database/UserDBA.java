package Backend.Database;

import Backend.User;
import java.util.List;

public interface UserDBA extends Database {
    void addUser(User user);
    public User getUser(String userId);
    public List<User> getUsers();
    public String getUserIdByName(String userName);
}
