package Backend;

import Backend.ContentCreation.IContent;
import Backend.ContentCreation.Post;
import Backend.ContentCreation.Story;
import Backend.Database.IContentDatabase;
import Backend.Database.IUserDatabase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsFeed {
    private User userID;
    private IContentDatabase database;
    private IUserDatabase userDatabase;
    private List<IContent> friendsContents;
    public NewsFeed( User userID, IContentDatabase database, IUserDatabase userDatabase ) {
        this.userID = userID;
        this.database = database;
        this.userDatabase = userDatabase;
    }
    // allow user to create post
    public void createPost(String text, String image){
        Post post = new Post();
        post.setAuthorId(userID.getUserID());
        LocalDateTime now = LocalDateTime.now();
        post.setTimeStamp(now);
        post.setImgContent(image);
        String id = userID.getUserID()+"p" + String.valueOf(userID.getProfile().getContent().size()+1);
        post.setContentId(id);
        post.setTxtContent(text);
        database.addContent(post);
        userID.getProfile().addContent(post);
    }
    // allow user to create story
    public void createStory(String text, String image){
        Story story = new Story();
        story.setAuthorId(userID.getUserID());
        LocalDateTime now = LocalDateTime.now();
        story.setTimeStamp(now);
        story.setImgContent(image);
        String id = userID.getUserID()+"p" + (userID.getProfile().getContent().size() + 1);
        story.setContentId(id);
        story.setTxtContent(text);
        database.addContent(story);
        userID.getProfile().addContent(story);
    }
    // load friends contents
    public void loadFriendsContents(){
        List<IContent> friendsContents = new ArrayList<>();
        List<String> friend = userID.getProfile().getFriends().getFriends();
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

        for (IContent content : friendsContents) {
            if (content instanceof Story)
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
        for (IContent content : friendsContents) {
            if (content instanceof Post)
                continue;
            if (content instanceof Story) {
                // if day pass delete
                if (currentTime.isAfter(content.getTimeStamp().plusDays(1))) {
                    database.removeContent(content.getContentId());
                    userDatabase.getUser(content.getAuthorId()).getProfile().removeContent(content);
                }
                continue;
            }
            stories.add(content);
        }
        return stories;
    }

    // get suggestion friends for user
    public List<User> getSuggestions(){
        List<User> suggestions = new ArrayList<>();
        List<User> allUsers = userDatabase.getUsers();
        Friends friends = userID.getProfile().getFriends();
        for (User user : allUsers) {
            if (friends.isFriend(user.getUserID())||friends.isBlocked(user.getUserID()) ) {
                continue;
            }
            suggestions.add(user);
        }
        return suggestions;
    }





}
