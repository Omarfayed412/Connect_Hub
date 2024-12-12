package Backend.GroupManagement;

import Backend.User.*;
import Backend.GroupManagement.*;
public class AdminRole extends MemberRole {
    // Singleton instance
    private static volatile AdminRole instance;

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
        group.removePending(user);
        user.getGroupManager().removeRequest(group);
        group.addMember(user);
        user.getGroupManager().joinGroup(group);
        refresh();
    }

    public void declineRequest(Group group, User user) {
        group.removePending(user);
        user.getGroupManager().removeRequest(group);
        refresh();
    }

    public void removeNormalMember(Group group, User user) {
        if (!group.isAdmin(user)) {
            group.removeMember(user);
            user.getGroupManager().leaveGroup(group);
            refresh();
        }
    }
}
