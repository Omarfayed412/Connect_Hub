package Backend.Database;

import Backend.User;
import java.util.List;

<<<<<<< Updated upstream:Connect_Hub/src/Backend/Database/UserDBA.java
public interface UserDBA extends Database {
=======
public interface IUserDatabase extends Database {
>>>>>>> Stashed changes:Connect_Hub/src/Backend/Database/IUserDatabase.java
    void addUser(User user);
    public User getUser(String userId);
    public List<User> getUsers();
}
