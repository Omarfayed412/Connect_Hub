package Backend.Database;

// using this interface to apply Dependency inversion Principle

import Backend.User;

interface Database {
     void save();
     void addUser(User user);
     public void load();
}
