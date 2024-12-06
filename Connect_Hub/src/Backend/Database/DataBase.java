package Backend.Database;
import Backend.*;
import java.util.List;

// using this interface to apply Dependency inversion Principle
 public interface DataBase {
     void save();
     void addUser(User user);
     public void load();
    public User getUser(String userId);
    public List<User> getUsers();
}
/*
user    content
Dat=Base userDateba  = new
 */