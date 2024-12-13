package Backend.GroupManagement;
import Backend.ContentCreation.IContent;
import Backend.Database.ContentDatabase;
import Backend.Database.IContentDatabase;
import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;
import Backend.GroupManagement.GroupBuilderInterface;
import Backend.ContentCreation.Post;
import Backend.GroupManagement.GroupBuilderConcerete;
import Backend.Notifications.GroupNotifications;
import Backend.User.User;
import java.util.ArrayList;
import java.util.List;
public class Group {
    private String name;
    private String description;
    private String photoPath;
    private String groupID;
    private String primaryAdmin;
    private List<String> members;
    private List<String> admins;
    private List<String> posts;
    private List<String> pendingRequests;
    public String getGroupID() {
        return groupID;
    }

    Group(GroupBuilderConcerete builder) {
        this.name = builder.getName();
        this.description = builder.getDescription();
        this.photoPath = builder.getPhotoPath();
        this.groupID = builder.getGroupID();
        this.primaryAdmin = builder.getPrimaryAdmin().getUserID();
        this.members = builder.getMembers();
        this.admins = builder.getAdmins();
        this.posts = builder.getPosts();
        this.pendingRequests = builder.getPendingRequests();
    }
    public void addRequest(User user)
    {
        pendingRequests.add(user.getUserID());
        GroupNotifications groupNotifications = new GroupNotifications();
        groupNotifications.setGroup(this);
        groupNotifications.setUser(user);
        groupNotifications.toStringRequest();
        IUserDatabase db = UserDatabase.getUserDataBase();
        db.load();
        for(String admin : admins) {
            User u = db.getUser(admin);
            u.addGroupNotifications(groupNotifications);
        }
        db.save();
    }
    public void removePending(User user)
    {
        pendingRequests.remove(user.getUserID());
    }
    public void addMember(String userID) //validate if already exist in the frontend listeners
    {
        members.add(userID);
    }
    public void removeMember(User user)
    {
        members.remove(user.getUserID());
        admins.remove(user.getUserID());
    }
    public void addPost(Post post)
    {
        posts.add(post.getContentId());

    }
    public boolean isPending(User user){
        return pendingRequests.contains(user.getUserID());
    }

    public void removePost(String post)  //validate in the frontend that it exist before removing it
    {
        IContentDatabase contentDB = ContentDatabase.getInstance();
        contentDB.load();
        posts.remove(post);
        contentDB.removeContent(post);
        contentDB.save();


    }
    public void promoteToAdmin(User user)
    {
        if(!admins.contains(user.getUserID())&&members.contains(user.getUserID()))
          admins.add(user.getUserID());
    }
    public void demoteAdmin(User user)
    {
        admins.remove(user.getUserID());
    }
     public boolean isMember(User user)
     {
         return members.contains(user.getUserID());
     }
     public boolean isAdmin(User user)
     {
         return ((primaryAdmin.equals(user.getUserID()))||admins.contains(user.getUserID()));
     }

    public List<String> getAdmins() {
        return admins;
    }

    public void deleteGroup(){
        IUserDatabase userDatabase = UserDatabase.getUserDataBase();
        for(String userID : members){
            userDatabase.getUser(userID).getGroupManager().leaveGroup(this);
        }
         for(String userID : admins){
             userDatabase.getUser(userID).getGroupManager().leaveGroup(this);
         }
         for(String postID : posts){
             removePost(postID);
         }
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

    public String getPrimaryAdmin() {
        return primaryAdmin;
    }

    public void setPrimaryAdmin(User primaryAdmin) {
        this.primaryAdmin = primaryAdmin.getUserID();
    }

    public List<User> getMembers() {
        IUserDatabase userDatabase = UserDatabase.getUserDataBase();
        List<User> members = new ArrayList<>();
        userDatabase.load();
        for (String member : this.members) {
            members.add(userDatabase.getUser(member));
        }
        return members;
    }
    public List<String > getMemberIDs() {
        return members;
    }
    public List<String> getPendingRequests() {
        return pendingRequests;
    }

//    public void setMembers(List<User> members) {
//        this.members = members;
//    }

//    public List<User> getAdmins() {
//        return admins;
//    }

//    public void setAdmins(List<User> admins) {
//        this.admins = admins;
//    }

    public List<IContent> getPosts() {
        IContentDatabase contentDatabase = ContentDatabase.getInstance();
        List<IContent> posts = new ArrayList<>();
        contentDatabase.load();
        for (String post : this.posts) {
            IContent p =  contentDatabase.getContent(post);
            System.out.println("post Status"+ p);
            posts.add(p);
        }
        return posts;
    }

//    public void setPosts(List<Post> posts) {
//        this.posts = posts;
//    }
    public boolean isPrimaryAdmin(User user)
    {
        return user.getUserID().equals(this.primaryAdmin);
    }

}
