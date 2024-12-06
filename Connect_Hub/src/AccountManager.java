import java.time.LocalDate;

//this class follows singletone design pattern
// i used lazy initilization approach
public class AccountManager {
    private UserRepository userDataBase;
    private static AccountManager instance;
    private AccountManager(UserRepository userDataBase) {
        this.userDataBase = userDataBase;
    }
    public static AccountManager getInstance(UserDataBase userDataBase) {
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
        UserDataBase.load();
    }
    public void logout(User user){
        user.resetStatus();
        userDataBase.save();
        UserDataBase.load(); // loading data after logout
    }

}