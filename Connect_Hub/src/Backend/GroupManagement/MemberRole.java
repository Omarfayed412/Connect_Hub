package Backend.GroupManagement;

import Backend.ContentCreation.Post;
import Backend.User.User;
// using singeltone design pattern in each role
public class MemberRole {

    private static  MemberRole instance;

    // Private constructor to prevent instantiation
    public MemberRole() {}


    public static MemberRole getInstance() {
        if (instance == null) {
            synchronized (MemberRole.class) {
                if (instance == null) {
                    instance = new MemberRole();
                }
            }
        }
        return instance;
    }

    public void requestToJoinGroup(User user, Group group) {
        if (!group.isMember(user)) {
            group.addRequest(user);
            user.getGroupManager().RequettojoinGroup(group);
        }
    }

    public void leaveGroup(User user, Group group) {
        if (group.isMember(user)) {
            user.getGroupManager().leaveGroup(group);
            group.removeMember(user);
        }
    }

    public void post(Post post, Group group, User user) {
        if (group.isMember(user)) {
            group.addPost(post);
        }
    }
}
