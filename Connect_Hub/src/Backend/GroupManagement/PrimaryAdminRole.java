package Backend.GroupManagement;

import Backend.User.User;
import Backend.Database.*;
public class PrimaryAdminRole extends AdminRole {

    private static volatile PrimaryAdminRole instance;


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
        if (group.isMember(user)) {
            group.removeMember(user);
            user.getGroupManager().leaveGroup(group);
            refresh();
        }

    }

    public void promoteToAdmin(Group group, User user) {
        group.promoteToAdmin(user);
        user.getGroupManager().promoteToAdmin(group);
        refresh();
    }

    public void demoteAdmin(Group group, User user) {
        if (group.isAdmin(user)) {
            group.demoteAdmin(user);
            user.getGroupManager().demoteToAdmin(group);
            refresh();
        }

    }

    public void deleteGroup(User user, Group group) {
        if (groupsDataBase.IsGroupFound(group)&&group.isPrimaryAdmin(user)) {
            user.getGroupManager().deleteGroup(group);
            groupsDataBase.removeGroup(group);
            refresh();
        }
    }
}
