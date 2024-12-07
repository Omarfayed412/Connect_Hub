package Backend;

import Backend.ContentCreation.IContent;
import Backend.ContentCreation.Post;
import Backend.ContentCreation.Story;
import Backend.Database.ContentDatabase;
import Backend.Database.IContentDatabase;
import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class NewsFeed {
    private User user;
    private IContentDatabase database;
    private IUserDatabase userDatabase;
    private List<String> friendsContents;
    public NewsFeed(User user) {
        this.database = ContentDatabase.getInstance();
        this.userDatabase = UserDatabase.getUserDataBase();
        this.user = user;
    }
    // allow user to create post
    public void createPost(String text, String image){
        this.user = userDatabase.getUser(user.getUserID());
        Post post = new Post();
        post.setAuthorId(user.getUserID());
        LocalDateTime now = LocalDateTime.now();
        post.setTimeStamp(now.toString());
        post.setImgPath(image);
        String id = "p" + (user.getProfile().getContent().size()+1) + user.getUserID();
        post.setContentId(id);
        post.setTxtContent(text);
        database.addContent(post);
        user.getProfile().addContent(post);
        System.out.println(user.getProfile().getContent());
        System.out.println("2" + user.getProfile().getContent());
        System.out.println("1" + userDatabase.getUser(user.getUserID()).getProfile().getContent());
        userDatabase.save();
    }
    // allow user to create story
    public void createStory(String text, String image){
        this.user = userDatabase.getUser(user.getUserID());
        Story story = new Story();
        story.setAuthorId(user.getUserID());
        LocalDateTime now = LocalDateTime.now();
        story.setTimeStamp(now.toString());
        story.setImgPath(image);
        String id = "s" + (user.getProfile().getContent().size() + 1) +user.getUserID();
        story.setContentId(id);
        story.setTxtContent(text);
        database.addContent(story);
        user.getProfile().addContent(story);
        userDatabase.save();
    }
    // load friends contents
    public void loadFriendsContents(){
        this.user = userDatabase.getUser(user.getUserID());
        List<String> friend = user.getProfile().getFriends().getFriends();
        List<String> friendsContents = new ArrayList<>(user.getProfile().getContent());
        for(String friendID : friend){
            User user = userDatabase.getUser(friendID);
            friendsContents.addAll(user.getProfile().getContent());
        }
        this.friendsContents = friendsContents;
    }
    // get posts for user
    public List<IContent> getPosts(){
        loadFriendsContents();
        List<IContent> posts = new ArrayList<>();

        for (String contentId : friendsContents) {
            IContent content = database.getContent(contentId);
            if (contentId.charAt(0) == 's')
                continue;
            posts.add(content);
        }
        return posts;
    }
    // get stories for specific user
    public List<IContent> getStories(){
        loadFriendsContents();
        List<IContent> stories = new ArrayList<>();
        LocalDateTime currentTime = LocalDateTime.now();
        for (String contentId : friendsContents) {
            IContent content = database.getContent(contentId);
            if (contentId.charAt(0) == 'p')
                continue;
            if (contentId.charAt(0) == 's') {
                // if day pass delete

                if (currentTime.isAfter(LocalDateTime.parse(content.getTimeStamp()).plusDays(1))) {
                    System.out.println("out dated");
                    database.removeContent(content.getContentId());
                    userDatabase.getUser(content.getAuthorId()).getProfile().removeContent(content);
                    continue;
                }
            }
            stories.add(content);
            System.out.println(content);
        }
        return stories;
    }

    // get suggestion friends for user
    public List<User> getSuggestions(){
        this.user = userDatabase.getUser(user.getUserID());
        List<User> suggestions = new ArrayList<>();
        List<User> allUsers = userDatabase.getUsers();
        Friends friends = user.getProfile().getFriends();
        for (User user : allUsers) {
            if (user.getProfile().getFriends().isPending(this.user.getUserID())|| friends.isPending(user.getUserID())||
                    friends.isFriend(user.getUserID())||
                    friends.isBlocked(user.getUserID()) || Objects.equals(user.getUserID(), this.user.getUserID())) {
                continue;
            }
            suggestions.add(user);
        }
        return suggestions;
    }





}
