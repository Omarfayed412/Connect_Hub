package Backend.Database;

import Backend.Database.pics.GroupsInterface;
import Backend.GroupManagement.Group;
import Backend.User.User;

public class SearchGroup {
    private GroupsDataBase groupsDataBase;


    public SearchGroup(GroupsDataBase groupsDataBase)
    {
        this.groupsDataBase = groupsDataBase;
    }


    /// return User object if found and not in the blocked list
    /// return null if object is blocked or not found
    public Group getGroup(String groupName) {

        Group group = this.groupsDataBase.getGroupByName(groupName);

        if(group == null ) return null;

        return group;

    }


}
