package Backend.GroupManagement;

import Backend.Database.GroupsDataBase;
import Backend.Database.UserDatabase;
import Backend.User.User;

import java.util.ArrayList;
import java.util.List;

public class GroupManager {
    private List<Group> groupJoined;
    private List<Group>groupAdmined;
    private List<Group>JoinRequests;
    private User user;
    protected UserDatabase userDatabase=UserDatabase.getUserDataBase();
    protected GroupsDataBase groupsDataBase=GroupsDataBase.getGroupsDataBase();
    public void refresh()
    {
        userDatabase.save();
        userDatabase.load();
        groupsDataBase.save();
        groupsDataBase.load();
    }
    public GroupManager(User user) {
        this.user=user;
        this.groupAdmined = new ArrayList<>();
        JoinRequests = new ArrayList<>();
        this.groupJoined = new ArrayList<>();
    }

    public void RequettojoinGroup(Group group)
    {
        this.JoinRequests.add(group);
    }
    public void joinGroup(Group group)
    {
        groupJoined.add(group);
    }
    public void leaveGroup(Group group)
    {
        groupJoined.remove(group);
    }
    public void removeRequest(Group group)
    {
        this.JoinRequests.remove(group);
    }
    public void deleteGroup(Group group)
    {
       if(this.groupAdmined.contains(group))
       {
           this.groupAdmined.remove(group);
       }
    }
    public void createGroup(String desc,String name,String photoPath) //edit it to have the attributse of the builder
    {
         Group group=new GroupBuilderConcerete().setName(name).setDescription(desc).setPhotoPath(photoPath).setPrimaryAdmin(this.user).setGroupID().build();
         group.addMember(user);
         PrimaryAdminRole.getInstance().promoteToAdmin(group,user);
         this.groupAdmined.add(group);
         this.groupJoined.add(group);
         groupsDataBase.addGroup(group);
         refresh();

    }

}
