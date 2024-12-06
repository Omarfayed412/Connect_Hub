package Backend;

//this class follows singletone design pattern
// i used lazy initilization approach
public class AccountManager {
    private UserDBA userDataBase;
    private static AccountManager instance;
    private AccountManager(UserDBA userDataBase) {
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
        User user= new UserBuilderConcerete().setEmail(email).setUsername(username).setPassword(rawPassword).setDateOfBirth(dateOfBirth).setUserID().setStatus().build();
        userDataBase.addUser(user);
    }
    public void login(User user){
        user.setStatus(); // status eluser in list
        userDataBase.save();
        userDataBase.load();
    }
    public void logout(User user){
        user.resetStatus();
        userDataBase.save();
        userDataBase.load(); // loading data after logout
    }

}
