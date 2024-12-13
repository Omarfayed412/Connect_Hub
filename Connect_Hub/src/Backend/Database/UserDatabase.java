
///           class UserDataBase .....
///           this class follows the Singleton design pattern
///           usage : use getUserDataBase() : DataBase method to get the DataBase
///           UserDataBase userDataBase = UserDataBase.getUserDataBase();
///           ArrayList<User> users = userDataBase.getUsers();
package Backend.Database;

import Backend.User.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class UserDatabase implements  IUserDatabase{
    private static List<User> users = null;
    private static UserDatabase userDataBase = null;
    private static final String users_json = "users.json";
    private static Gson gson = null;



    private UserDatabase() {
        users = new ArrayList<User>();
        gson = new Gson();
    }

    public synchronized static UserDatabase getUserDataBase() {
        if (userDataBase == null) {
            System.out.println("UserDataBase Created");
            userDataBase = new UserDatabase();
            /// Avoid null Exceptions
                innerLoad();
        }
        return userDataBase;
    }


    /// Deserialization..... first instance

    private List<User> deserializeUsers() {
        try (FileReader reader = new FileReader(users_json)) {
            Type type = new TypeToken<List<User>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            System.err.println("Error reading users from file: " + e.getMessage());
            return new ArrayList<User>();
        }
    }

    private static void innerLoad() {
        users = userDataBase.deserializeUsers();
        if(users == null)
            users = new ArrayList<User>();
    }

    @Override
    public void load() {
        System.out.println("\\u001B[31m" + "Warninggggggggg Loading Users" + "\u001B[0m");
        try {
            FileReader reader = new FileReader(users_json);
            /// generic method to return the type of the object inside the List
            Type type = new TypeToken<List<User>>() {
            }.getType();
            users = gson.fromJson(reader, type);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public synchronized List<User> getUserByName(String userName)
    {
        List<User> users1 = new ArrayList<>();
        for (User user : users)
            if(user.getUsername().contains(userName)) users1.add(user);
        return users1;
    }

    /// Serialization.....
    public void save() {
        //serialization into users.json......
        for (User user : users)
        {
            System.out.println("Saving user: " + user.getGroupManager().getGroupJoined());
        }
        try {
            FileWriter writer = new FileWriter(users_json);
            gson.toJson(users, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized List<User> getUsers() {
        return users;
    }

    /// getUser() method return user if found
    /// return null if user not found
    public synchronized User getUser(String userId)
    {
        for (User user : users)
            if(user.getUserID().matches(userId)) return user;
        return null;
    }
    /// returns true if user  already existed
    /// return false if email not  existed
    public Boolean IsUserFound(User user) { return ( users.contains(user) ) ? true : false; }

    /// return number of current users
    public int getNumberOfUsers() { return users.size(); }

    /// returns true if email is already used
    /// return false if email is not used

    /// returns true if id is already used
    /// return false if id is not used
    public Boolean isIdExist(String id)
    {
        Boolean isFound = false;
        for(User user : users)
        {
            if(user.getUserID().matches(id)) { isFound = true; break;}

        }
        return isFound;
    }

    @Override
    public synchronized void addUser(User user) {
          users.add(user);
          save();
    }
//    public void printUsers()
//    {
//        for(User user : users)
//            System.out.println(user.getUsername());
//
//    }
}
