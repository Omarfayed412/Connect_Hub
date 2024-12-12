package Backend.GroupManagement;
import Backend.GroupManagement.GroupBuilderInterface;
import Backend.ContentCreation.Post;
import Backend.GroupManagement.GroupBuilderConcerete;
import Backend.User.User;
import java.util.ArrayList;
import java.util.List;
public class Group {
    private String name;
    private String description;
    private String photoPath;
    private String groupID;
    private User primaryAdmin;
    private List<User> members;
    private List<User> admins;
    private List<Post> posts;
    private List<User> pendingRequests;
    Group(GroupBuilderConcerete builder) {
        this.name = builder.getName();
        this.description = builder.getDescription();
        this.photoPath = builder.getPhotoPath();
        this.groupID = builder.getGroupID();
        this.primaryAdmin = builder.getPrimaryAdmin();
        this.members = builder.getMembers();
        this.admins = builder.getAdmins();
        this.posts = builder.getPosts();
        this.pendingRequests = builder.getPendingRequests();
    }
    public void addRequest(User user)
    {
        pendingRequests.add(user);
    }
    public void removePending(User user)
    {
        pendingRequests.remove(user);
    }
    public void addMember(User user) //validate if already exist in the frontend listeners
    {
        members.add(user);
    }
    public void removeMember(User user)
    {
        members.remove(user);
        if(admins.contains(user))
            admins.remove(user);
    }
    public void addPost(Post post)
    {
        posts.add(post);
    }
    public void removePost(Post post)  //validate in the frontend that it exist before removing it
    {
        posts.remove(post);
    }
    public void promoteToAdmin(User user)
    {
        if(!admins.contains(user)&&members.contains(user))
          admins.add(user);
    }
    public void demoteAdmin(User user)
    {
        admins.remove(user);
    }
     public boolean isMember(User user)
     {
         return members.contains(user);
     }
     public boolean isAdmin(User user)
     {
         return ((primaryAdmin.getUserID().equals(user.getUserID()))||admins.contains(user));
     }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public User getPrimaryAdmin() {
        return primaryAdmin;
    }

    public void setPrimaryAdmin(User primaryAdmin) {
        this.primaryAdmin = primaryAdmin;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    public boolean isPrimaryAdmin(User user)
    {
        return user.getUserID().equals(this.primaryAdmin.getUserID());
    }

}
