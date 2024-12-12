package Backend.Database;

import Backend.Database.pics.GroupsInterface;
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
    }

    synchronized public static GroupsDataBase getGroupsDataBase() {
        if(groupsDataBase == null)
        {
            groupsDataBase = new GroupsDataBase();
            innerLoad();

        }
        return groupsDataBase;
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
        groups = groupsDataBase.deserializeUsers();
        if(groups == null)
            groups = new ArrayList<Group>();
    }
    @Override
    public void load() {
    try {
        FileReader reader = new FileReader(groups_json);
        Type type = new TypeToken<ArrayList<Group>>(){}.getType();
        groups = gson.fromJson(reader,type);
        reader.close();
    }catch (IOException e)
    {e.printStackTrace();}

    }

    @Override
    public ArrayList<Group> getGroups() {
        return groups;
    }

    public synchronized Object getGroup(Group group)
    {
        for (Group group1 : groups)
            if(group.equals(group)) return group;
        return null;
    }
    public Boolean IsGroupFound(Object object) { return ( groups.contains(object) ) ? true : false; }
    public synchronized void addGroup(Group group) {
        groups.add(group);
        save();
    }
    public synchronized Group getGroupByName(String groupName)
    {
        for (Group group : groups)
           /* if(object.getUsername().matches(userName))*/ return group;
        return null;
    }
}
