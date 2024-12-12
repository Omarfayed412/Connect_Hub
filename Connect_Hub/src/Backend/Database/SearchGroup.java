package Backend.Database;

import Backend.GroupManagement.Group;

import java.util.ArrayList;
import java.util.List;

public class SearchGroup {
    private GroupsDataBase groupsDataBase;


    public SearchGroup(GroupsDataBase groupsDataBase)
    {
        this.groupsDataBase = groupsDataBase;
    }


    /// return User object if found and not in the blocked list
    /// return null if object is blocked or not found
    public List<Group> getGroup(String groupName) {
        List<Group> groups = this.groupsDataBase.getGroupByName(groupName);

        return groups;

    }


}
