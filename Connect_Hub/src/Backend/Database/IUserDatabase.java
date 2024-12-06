package Backend.Database;

import Backend.User;
import java.util.List;

public interface IUserDatabase extends Database{
    void save();
    void addUser(User user);
    public User getUser(String userId);
    public List<User> getUsers();    
    public void load();
}
