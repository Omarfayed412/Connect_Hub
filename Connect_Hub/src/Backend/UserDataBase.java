
///           class UserDataBase .....
///           this class follows the Singleton design pattern
///           usage : use getUserDataBase() : DataBase method to get the DataBase
///           UserDataBase userDataBase = UserDataBase.getUserDataBase();
///           ArrayList<User> users = userDataBase.getUsers();
package Backend;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserDataBase implements UserDBA {
    private static List<User> users = null;
    private static UserDataBase userDataBase = null;
    private static final String users_json = "src/users.json";
    private static Gson gson = null;
    private static int numberOfUsers;


    private UserDataBase() {
        users = new ArrayList<User>();
        gson = new Gson();
        numberOfJSONOBJECTS();
    }

    public synchronized static UserDataBase getUserDataBase() {
        if (userDataBase == null) {
            System.out.println("UserDataBase Created");
            userDataBase = new UserDataBase();
            /// Avoid null Exceptions
            if (numberOfUsers > 0)
                innerLoad();

        }
        return userDataBase;
    }

    public void numberOfJSONOBJECTS() {
        List<User> userList = null;
        try {
            FileReader reader = new FileReader(users_json);
            Type type = new TypeToken<List<User>>() {
            }.getType();
            userList = gson.fromJson(reader, type);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (userList == null)
            numberOfUsers = 0;
        else
            numberOfUsers = userList.size();


    }


    /// Deserialization..... first instance

    private static void innerLoad() {
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

    @Override
    public void load() {
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


    /// Serialization.....
    public void save() {
        //serialization into users.json......
        try {
            FileWriter writer = new FileWriter(users_json);
            gson.toJson(users, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        return users;
    }

    /// getUser() method return user if found
    /// return null if user not found
    public User getUser(String userId)
    {
        for (User user : users)
            if(user.getUserID().matches(userId)) return user;
        return null;
    }
    /// returns true if user  already existed
    /// return false if email not  existed
    public Boolean IsUserFound(User user) { return ( users.contains(user) ) ? true : false; }

    /// return number of current users
    public int getNumberOfUsers() { return numberOfUsers; }

    /// returns true if email is already used
    /// return false if email is not used
    public Boolean isEmailExist(String email)
    {
        Boolean isFound = false;
        for(User user : users)
        {
            if(user.getEmail().matches(email)) { isFound = true; break;}

        }
        return isFound;
    }
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
    public void addUser(User user) {
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
