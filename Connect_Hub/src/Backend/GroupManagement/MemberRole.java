package Backend.GroupManagement;
import Backend.Database.pics.GroupsInterface;
import Backend.ContentCreation.Post;
import Backend.Database.GroupsDataBase;
import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;
import Backend.User.User;
// using singeltone design pattern in each role
public class MemberRole {

    private static  MemberRole instance;
    protected IUserDatabase userDatabase;
    protected GroupsInterface groupsDataBase;
    // Private constructor to prevent instantiation
    public MemberRole() {
        userDatabase =UserDatabase.getUserDataBase();
        groupsDataBase=GroupsDataBase.getGroupsDataBase();
    }
    protected void refresh()
    {
        userDatabase.save();
        userDatabase.load();
        groupsDataBase.save();
        groupsDataBase.load();
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

    public void requestToJoinGroup(User user, Group group) {
        if (!group.isMember(user)) {
            group.addRequest(user);
            user.getGroupManager().RequettojoinGroup(group);
            refresh();
        }
    }

    public void leaveGroup(User user, Group group) {
        if (group.isMember(user)) {
            user.getGroupManager().leaveGroup(group);
            group.removeMember(user);
            refresh();
        }
    }

    public void post(Post post, Group group, User user) {
        if (group.isMember(user)) {
            group.addPost(post);
            refresh();
        }
    }
}
