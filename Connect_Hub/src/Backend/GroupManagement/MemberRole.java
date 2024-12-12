package Backend.GroupManagement;
import Backend.Database.GroupsInterface;
import Backend.ContentCreation.Post;
import Backend.Database.GroupsDataBase;
import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;
import Backend.User.User;
// using singeltone design pattern in each role
public class MemberRole {

    private static  MemberRole instance;
    protected static IUserDatabase userDatabase;
    protected static GroupsInterface groupsDataBase;
    // Private constructor to prevent instantiation
    public MemberRole() {
        userDatabase =UserDatabase.getUserDataBase();
        groupsDataBase=GroupsDataBase.getGroupsDataBase();
    }

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

    public static void requestToJoinGroup(User user, Group group) {
        groupsDataBase.load();
        userDatabase.load();
        group = groupsDataBase.getGroup(group.getGroupID());
        user = userDatabase.getUser(user.getUserID());
        if (!group.isMember(user)) {
            group.addRequest(user);
            user.getGroupManager().RequettojoinGroup(group);
            groupsDataBase.save();
            userDatabase.save();
        }
    }

    public void leaveGroup(User user, Group group) {
        groupsDataBase.load();
        userDatabase.load();
        group = groupsDataBase.getGroup(group.getGroupID());
        user = userDatabase.getUser(user.getUserID());
        if (group.isMember(user)) {
            user.getGroupManager().leaveGroup(group);
            group.removeMember(user);
            groupsDataBase.save();
        }
    }

    public void post(Post post, Group group, User user) {
        groupsDataBase.load();
        userDatabase.load();
        group = groupsDataBase.getGroup(group.getGroupID());
        user = userDatabase.getUser(user.getUserID());
        if (group.isMember(user)) {
            group.addPost(post);
            groupsDataBase.save();
            userDatabase.save();
        }
    }
}
