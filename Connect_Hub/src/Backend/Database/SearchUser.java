package Backend.Database;

import Backend.User.User;

public class SearchUser {
    private UserDatabase userDatabase;
    private User user;


    public SearchUser(UserDatabase userDatabase, User user)
    {
        this.userDatabase = userDatabase;
        this.user = user;
    }


    /// return User object if found and not in the blocked list
    /// return null if object is blocked or not found
    public User getUser(String userName) {

       User user1 = this.userDatabase.getUserByName(userName);

       if(user1 == null ) return null;

       else if ((user.getProfile().getFriends().isBlocked(user1.getUserID()))) {
           return null;
       }
       return user1;

    }


}
