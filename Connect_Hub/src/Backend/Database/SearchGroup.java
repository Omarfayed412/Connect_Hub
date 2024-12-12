package Backend.Database;

import Backend.Database.pics.GroupsInterface;
import Backend.User.User;

public class SearchGroup {
    private GroupsDataBase groupsDataBase;


    public SearchGroup(GroupsDataBase groupsDataBase)
    {
        this.groupsDataBase = groupsDataBase;
    }


    /// return User object if found and not in the blocked list
    /// return null if object is blocked or not found
    public Object getGroup(String groupName) {

        Object object = this.groupsDataBase.getGroupByName(groupName);

        if(object == null ) return null;

        return object;

    }


}
