package Backend;

import Backend.Database.IUserDatabase;

public class CheckEmailExistance {
    public static Boolean isEmailExist(IUserDatabase usersDatabase, String email)
    {
        Boolean isFound = false;
        for(User user : usersDatabase.getUsers())
        {
            if(user.getEmail().matches(email)) { isFound = true; break;}

        }
        return isFound;
    }
}
