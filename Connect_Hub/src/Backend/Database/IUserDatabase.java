package Backend.Database;

import Backend.User.User;
import java.util.List;


public interface IUserDatabase extends Database {
    void addUser(User user);
    public User getUser(String userId);
    public List<User> getUsers();
    public List<User> getUserByName(String userName);
}
