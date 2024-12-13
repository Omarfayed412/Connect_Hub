package Backend.GroupManagement;

import Backend.Database.GroupsDataBase;
import Backend.Database.GroupsInterface;
import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;
import Backend.User.*;
import Backend.GroupManagement.*;
public class AdminRole extends MemberRole {
    // Singleton instance
    private static volatile AdminRole instance;
    private static GroupsInterface groupDatabase = GroupsDataBase.getGroupsDataBase();
    private static IUserDatabase userDatabase = UserDatabase.getUserDataBase();

    // Private constructor to prevent instantiation
     AdminRole() {}

    // Public method to get the singleton instance
    public static AdminRole getInstance() {
        if (instance == null) {
            synchronized (AdminRole.class) {
                if (instance == null) {
                    instance = new AdminRole();
                }
            }
        }
        return instance;
    }

    public void approveRequest(Group group, User user) {
         groupDatabase.load();
         userDatabase.load();
         group = groupDatabase.getGroup(group.getGroupID());
         user = userDatabase.getUser(user.getUserID());
        group.removePending(user);
        user.getGroupManager().removeRequest(group);
        group.addMember(user.getUserID());
        user.getGroupManager().joinGroup(group);
        groupDatabase.save();
        userDatabase.save();

    }

    public void declineRequest(Group group, User user) {
        groupDatabase.load();
        userDatabase.load();
        group = groupDatabase.getGroup(group.getGroupID());
        user = userDatabase.getUser(user.getUserID());
        group.removePending(user);
        user.getGroupManager().removeRequest(group);
        groupDatabase.save();
        userDatabase.save();
    }

    public void removeNormalMember(Group group, User user) {
        groupDatabase.load();
        userDatabase.load();
        group = groupDatabase.getGroup(group.getGroupID());
        user = userDatabase.getUser(user.getUserID());
        if (!group.isAdmin(user)) {
            group.removeMember(user);
            user.getGroupManager().leaveGroup(group);
            groupDatabase.save();
            userDatabase.save();
        }
    }
}
