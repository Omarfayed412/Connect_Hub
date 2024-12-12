package Backend.Database.pics;

import Backend.Database.Database;
import Backend.GroupManagement.Group;

import java.util.ArrayList;

public interface GroupsInterface extends Database {
    void save();
    void load();
    ArrayList<Group> getGroups();
    public void removeGroup(Group group);
    public Boolean IsGroupFound(Group group);

}
