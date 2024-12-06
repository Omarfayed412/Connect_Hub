package Backend.Database;

import Backend.User;

public interface IUserDatabase extends Database{
    void save();
    void addUser(User user);
    public void load();
}
