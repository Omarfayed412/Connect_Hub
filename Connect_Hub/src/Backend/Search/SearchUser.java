package Backend.Search;

import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;
import Backend.User.User;

import java.util.List;

public class SearchUser {
    private IUserDatabase userDatabase;
    private User user;


    public SearchUser(IUserDatabase userDatabase, User user)
    {
        this.userDatabase = userDatabase;
        this.user = userDatabase.getUser(user.getUserID());
    }


    /// return User object if found and not in the blocked list
    /// return null if object is blocked or not found
    public List<User> getUsers(String userName) {

       List<User> user1 = this.userDatabase.getUserByName(userName);
       user1.removeIf(u -> (user.getProfile().getFriends().isBlocked(u.getUserID())));
       return user1;

    }


}
