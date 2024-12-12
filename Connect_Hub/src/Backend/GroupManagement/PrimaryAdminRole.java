package Backend.GroupManagement;

import Backend.User.User;
import Backend.Database.*;
public class PrimaryAdminRole extends AdminRole {

    private static volatile PrimaryAdminRole instance;
    private static GroupsInterface groupDatabase = GroupsDataBase.getGroupsDataBase();
    private static IUserDatabase userDatabase = UserDatabase.getUserDataBase();

    private PrimaryAdminRole() {}


    // Public method to get the singleton instance
    public static PrimaryAdminRole getInstance() {
        if (instance == null) {
            synchronized (PrimaryAdminRole.class) {
                if (instance == null) {
                    instance = new PrimaryAdminRole();
                }
            }
        }
        return instance;
    }

    public void removeMember(Group group, User user) {
        groupDatabase.load();
        userDatabase.load();
        group = groupDatabase.getGroup(group.getGroupID());
        user = userDatabase.getUser(user.getUserID());
        if (group.isMember(user)) {
            group.removeMember(user);
            user.getGroupManager().leaveGroup(group);
        }

    }

    public void promoteToAdmin(Group group, User user) {
        System.out.println("innnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
        userDatabase.load();
        user = userDatabase.getUser(user.getUserID());

        group.promoteToAdmin(user);
        user.getGroupManager().promoteToAdmin(group);
        userDatabase.save();
    }

    public void demoteAdmin(Group group, User user) {
        groupDatabase.load();
        userDatabase.load();
        group = groupDatabase.getGroup(group.getGroupID());
        user = userDatabase.getUser(user.getUserID());
        if (group.isAdmin(user)) {
            group.demoteAdmin(user);
            user.getGroupManager().demoteToAdmin(group);
            userDatabase.save();
            groupDatabase.save();
        }

    }

    public void deleteGroup(User user, Group group) {
        groupDatabase.load();
        userDatabase.load();
        group = groupDatabase.getGroup(group.getGroupID());
        user = userDatabase.getUser(user.getUserID());
        if (groupsDataBase.IsGroupFound(group)&&group.isPrimaryAdmin(user)) {
            user.getGroupManager().deleteGroup(group.getGroupID());
            group.deleteGroup();
            groupsDataBase.removeGroup(group.getGroupID());
            groupsDataBase.save();
            userDatabase.save();
        }
    }
}
