package Backend.Search;

import Backend.Database.GroupsDataBase;
import Backend.Database.GroupsInterface;
import Backend.GroupManagement.Group;

import java.util.List;

public class SearchGroup {
    private GroupsInterface groupsDataBase;


    public SearchGroup(GroupsInterface groupsDataBase)
    {
        this.groupsDataBase = groupsDataBase;
    }


    /// return User object if found and not in the blocked list
    /// return null if object is blocked or not found
    public List<Group> getGroup(String groupName) {

        return this.groupsDataBase.getGroupByName(groupName);

    }


}
