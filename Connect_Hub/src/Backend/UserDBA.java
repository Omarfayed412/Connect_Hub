package Backend;
import Backend.Database.Database;

import java.util.List;

public interface UserDBA extends Database {
    void save();
    void addUser(User user);
    public void load();
    public User getUser(String userId);
    public List<User> getUsers();

}
