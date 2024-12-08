package Backend;

import Backend.Database.IUserDatabase;
import Backend.User.User;
import Backend.User.UserBuilderConcerete;

//this class follows singletone design pattern
// i used lazy initilization approach
public class AccountManager {
    private IUserDatabase userDataBase;
    private static AccountManager instance;
    private AccountManager(IUserDatabase userDataBase) {
        this.userDataBase = userDataBase;
    }
    public static AccountManager getInstance(IUserDatabase userDataBase) {
        if (instance == null) {
            synchronized (AccountManager.class) {    // i transported the (synchronized in the if block) to prevent blocking the object every call of a thread
                if (instance == null) {
                    instance = new AccountManager(userDataBase);
                }
            }
        }
        return instance;
    }
    // use signup after validate(password,username,email)
    public void signup(String email, String username, String rawPassword, String dateOfBirth){   //validation of the sign up handeled in frontend using validators classess
        User user= new UserBuilderConcerete().setEmail(email).setUsername(username).setPassword(rawPassword).setDateOfBirth(dateOfBirth).setUserID().setStatus().setProfile().build();
        userDataBase.addUser(user);
    }
    public void login(User user){
        user.setStatus(); // status eluser in list
        userDataBase.save();
        userDataBase.load();
    }
    public void logout(String user){
        userDataBase.getUser(user).resetStatus();
        userDataBase.save();
        userDataBase.load(); // loading data after logout
    }

}
