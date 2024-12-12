package Backend.GroupManagement;

import Backend.Database.GroupsDataBase;
import Backend.Database.GroupsInterface;
import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;
import Backend.User.User;

import java.util.ArrayList;
import java.util.List;

public class GroupManager {
    private List<String> groupJoined;
    private List<String>groupAdmined;
    private List<String>JoinRequests;
    private String userId;
    //protected UserDatabase userDatabase=UserDatabase.getUserDataBase();
    //protected GroupsDataBase groupsDataBase=GroupsDataBase.getGroupsDataBase();

    public GroupManager(User user) {
        this.userId=user.getUserID();
        this.groupAdmined = new ArrayList<>();
        JoinRequests = new ArrayList<>();
        this.groupJoined = new ArrayList<>();
    }

    public List<String> getGroupJoined() {
        return groupJoined;
    }

    public void RequettojoinGroup(Group group)
    {
        this.JoinRequests.add(group.getGroupID());
    }
    public void joinGroup(Group group)
    {
        groupJoined.add(group.getGroupID());
    }
    public void leaveGroup(Group group)
    {
        groupJoined.remove(group.getGroupID());
    }
    public void removeRequest(Group group)
    {
        this.JoinRequests.remove(group.getGroupID());
    }
    public void deleteGroup(String groupId)
    {
        this.groupAdmined.remove(groupId);
    }
    public void promoteToAdmin(Group group)
    {
        this.groupAdmined.add(group.getGroupID());
    }
    public void demoteToAdmin(Group group)
    {
        this.groupAdmined.remove(group.getGroupID());
    }
    public void createGroup(String desc,String name,String photoPath) //edit it to have the attributse of the builder
    {
        GroupsInterface groupsDataBase = GroupsDataBase.getGroupsDataBase();
        groupsDataBase.load();
        System.out.println(groupsDataBase.getGroups().size()+"    1===============================================");
        IUserDatabase userDatabase = UserDatabase.getUserDataBase();
        User user = userDatabase.getUser(this.userId);
        System.out.println(user+ "user");
         Group group=new GroupBuilderConcerete().setName(name).setDescription(desc).setPhotoPath(photoPath).setPrimaryAdmin(user).setGroupID().build();
         System.out.println(group + "group");
         group.addMember(user);
         PrimaryAdminRole.getInstance().promoteToAdmin(group,user);
         this.groupAdmined.add(group.getGroupID());
         this.groupJoined.add(group.getGroupID());
        System.out.println(groupsDataBase.getGroups().size()+"    1111===============================================");
        groupsDataBase.addGroup(group);
         System.out.println(groupsDataBase.getGroups().size()+"   2===============================================");
         groupsDataBase.save();

    }

}
