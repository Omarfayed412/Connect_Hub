package Backend;

import Backend.Database.UserDBA;

public class CheckEmailExistance {
    public static Boolean isEmailExist(UserDBA usersDatabase, String email)
    {
        Boolean isFound = false;
        for(User user : usersDatabase.getUsers())
        {
            if(user.getEmail().matches(email)) { isFound = true; break;}

        }
        return isFound;
    }
}
