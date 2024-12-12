package Backend.GroupManagement;
import Backend.User.*;
import Backend.ContentCreation.*;

import java.lang.reflect.Member;
import  java.util.*;
public class GroupBuilderConcerete implements GroupBuilderInterface{
    private String name;
    private String description;
    private String photoPath = "test/img";
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

    public List<String> getMembers() {
        List<String> members = new ArrayList<>();
        for (User member : this.members) {
            members.add(member.getUserID());
        }
        return members;
    }

    public List<String> getAdmins() {
        List<String> admins = new ArrayList<>();
        for (User admin : this.admins) {
            admins.add(admin.getUserID());
        }
        return admins;
    }

    public List<String> getPosts() {
        List<String> posts = new ArrayList<>();
        for (Post post : this.posts) {
            posts.add(post.getContentId());
        }
        return posts;
    }

    public List<String> getPendingRequests() {
        List<String> requests = new ArrayList<>();
        for (User request : this.pendingRequests) {
            requests.add(request.getUserID());
        }
        return requests;
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
        if (photoPath == null) {
            return this;
        }
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
