package Backend.Database;

import Backend.GroupManagement.Group;

import java.util.ArrayList;
import java.util.List;

public interface GroupsInterface extends Database {
    void save();
    void load();
    ArrayList<Group> getGroups();
    public Group getGroup(String id);
    public void removeGroup(String group);
    public void addGroup(Group group);
    public Boolean IsGroupFound(Group group);
    public List<Group> getGroupByName(String groupName);


}
