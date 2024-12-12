package Backend.GroupManagement;
import Backend.User.*;
import Backend.ContentCreation.*;
import  java.util.*;
public class GroupBuilderConcerete implements GroupBuilderInterface{
    private String name;
    private String description;
    private String photoPath;
    private String groupID;
    private User primaryAdmin;
    private List<User> members = new ArrayList<>();
    private List<User> admins = new ArrayList<>();
    private List<Post> posts = new ArrayList<>();
    private List<User> pendingRequests = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getGroupID() {
        return groupID;
    }

    public User getPrimaryAdmin() {
        return primaryAdmin;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<User> getPendingRequests() {
        return pendingRequests;
    }

    @Override
    public GroupBuilderConcerete setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public GroupBuilderConcerete setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public GroupBuilderConcerete setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
        return this;
    }
    @Override
    public GroupBuilderConcerete setGroupID() {
        this.groupID = UUID.randomUUID().toString();
        return this;
    }

    @Override
    public GroupBuilderConcerete setPrimaryAdmin(User primaryAdmin) {
        this.primaryAdmin = primaryAdmin;
        return this;
    }

    @Override
    public Group build() {
        return new Group(this);
    }

}
