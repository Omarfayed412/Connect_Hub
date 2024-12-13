package Backend.Database;

import Backend.ContentCreation.AbstractContent;
import Backend.GroupManagement.Group;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GroupsDataBase implements GroupsInterface {
    private static GroupsDataBase groupsDataBase = null;
    private static ArrayList<Group> groups = null;
    private static final String groups_json = "groups.json";
    private static Gson gson = null;
    private static int numberOfGroups;

    private GroupsDataBase() {
        groups = new ArrayList<>();
        System.out.println("inside constructor groupsDataBase");
        gson = new Gson();
        numberOfJSONOBJECTS();

        System.out.println("constructed groups dataBase finished");
    }

    synchronized public static GroupsDataBase getGroupsDataBase() {
        System.out.println("Groups DataBase called");
        System.out.println("inside getGroupsDataBase    -----------------   "+ groups);
        if (groupsDataBase == null) {
            System.out.println("Content Database created with size ++++++++++++++");
            groupsDataBase = new GroupsDataBase();
            /// Avoid null Exceptions
            System.out.println("Groups DataBase created");
            if (numberOfGroups > 0) {
                System.out.println("Number of groups created: " + numberOfGroups);
                innerLoad();
            }
        }
        return groupsDataBase;
    }
    public void numberOfJSONOBJECTS() {
        System.out.println("inside numberOfJSONOBJECTS");
        List<Group> group = null;
        try {
            FileReader reader = new FileReader(groups_json);
            Type type = new TypeToken<List<Group>>() {
            }.getType();
            System.out.println(" level 1");
            group = gson.fromJson(reader, type);
            reader.close();
            System.out.println("file readed successfully ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (group == null)
            numberOfGroups = 0;
        else
            numberOfGroups = group.size();
    }

    @Override
    public void save() {
        //serialization into users.json......
        try {
            FileWriter writer = new FileWriter(groups_json);
            gson.toJson(groups, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Group> deserializeUsers() {
        try (FileReader reader = new FileReader(groups_json)) {
            Type type = new TypeToken<List<Group>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            System.err.println("Error reading users from file: " + e.getMessage());
            return new ArrayList<Group>();
        }
    }

    private static void innerLoad() {
        System.out.println("inside inner load groups");
        groups = groupsDataBase.deserializeUsers();

        System.out.println("inside inner load");
        if(groups == null)
            groups = new ArrayList<Group>();
        System.out.println("groups loaded"+ groups);
    }
    @Override
    public void load() {
        System.out.println("Groups DataBase loaded");
    try {
        FileReader reader = new FileReader(groups_json);
        Type type = new TypeToken<ArrayList<Group>>(){}.getType();
        groups = gson.fromJson(reader,type);
        if (groups == null)
            groups = new ArrayList<>();
        reader.close();
    }catch (IOException e)
    {e.printStackTrace();}

    }

    @Override
    public ArrayList<Group> getGroups() {
        return groups;
    }

    public synchronized Group getGroup(String groupId)
    {
        for (Group group1 : groups)
            if(group1.getGroupID().equals(groupId)) return group1;
        return null;
    }
    public Boolean IsGroupFound(Group group) { return ( groups.contains(group) ) ? true : false; }
    public synchronized void addGroup(Group group) {
        //System.out.println(groups.size() + "beforeeeeeeeeeeeeeeeee");
        groups.add(group);
        //System.out.println(groups.size() + "Afterrrrrrrrrrrrrrrrrrrrr");
    }
    public synchronized void removeGroup(String groupId) {
        groups.remove(getGroup(groupId));
        save();
    }
    public synchronized List<Group> getGroupByName(String groupName)
    {
        List<Group> groupsLs = new ArrayList<>();
        for (Group group : groups)
            if(group.getName().contains(groupName)) groupsLs.add(group);
        if (groupsLs.isEmpty())
            return null;
        return groupsLs;
    }
}
