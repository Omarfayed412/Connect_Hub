package Backend;
import Backend.Database.DataBase;

import java.util.List;

public interface UserDBA extends DataBase {
    void save();
    void addUser(User user);
    public void load();
    public User getUser(String userId);
    public List<User> getUsers();

}
