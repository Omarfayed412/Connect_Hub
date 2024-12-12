import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;

public class Test {

    public static void main(String[] args) {
        IUserDatabase db = UserDatabase.getUserDataBase();
    }


}
