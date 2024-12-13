import Backend.Database.IUserDatabase;
import Backend.Database.NotfFDatabase;
import Backend.Database.UserDatabase;
import Backend.Notifications.FriendRequestNotification;
import Backend.Notifications.Notification;
import Backend.User.User;
import Backend.User.UserBuilderConcerete;

public class Test {

    public static void main(String[] args) {
        NotfFDatabase db = NotfFDatabase.getNotfFDataBase();
        IUserDatabase ud = UserDatabase.getUserDataBase();
        db.load();
        Notification n =(FriendRequestNotification) db.getNotification("ssssss");
        System.out.println(n);

    }


}
