package Frontend.NewFeedWindows;

import Backend.*;
import Backend.ContentCreation.IContent;
import Backend.Database.ContentDatabase;
import Backend.Database.IContentDatabase;
import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;
import Frontend.ProfileWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class NewsFeed extends JFrame{
    private JTabbedPane Feed;
    private JPanel main;
    private JPanel topPanal;
    private JLabel profilePhoto;
    private JLabel userName;
    private JScrollPane news;
    private JButton refresh;
    private JButton openProfile;
    private JButton logout;
    private JScrollPane friends;
    private JScrollPane suggestions;
    private JButton createStoryButton;
    private JButton createPostButton;
    private JFrame secondryWindow = null;
    private Backend.NewsFeed newsFeed;
    private IContentDatabase contentDatabase = (IContentDatabase) ContentDatabase.getInstance();
    private IUserDatabase userDatabase;
    private FriendManager friendManager;

    public NewsFeed(IUserDatabase userDatabase, User user) {
        this.userDatabase = userDatabase;
        this.newsFeed = new Backend.NewsFeed(user, contentDatabase, userDatabase);
        this.friendManager = new FriendManager(user);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);
        setContentPane(main);
        setVisible(true);
        userName.setText(user.getUsername());
        ImageIcon pPhoto = new ImageIcon(user.getProfile().getProfilePhoto());

        Image scaledImage = pPhoto.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        profilePhoto.setText("");
        profilePhoto.setIcon(scaledIcon);
        profilePhoto.setText("");
//        newsLoad(user);
//        friendsLoad(user);
//        suggestionsLoad();

        createPostButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createPost();
            }
        });
        createStoryButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createStory();
            }
        });
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                userDatabase.save();
                userDatabase.load();
                contentDatabase.save();
                contentDatabase.load();
                newsLoad(user);
                friendsLoad(user);
                suggestionsLoad();
            }
        });

        openProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileWindow(user);
                dispose();
            }
        });
        logout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AccountManager ac = AccountManager.getInstance(userDatabase);
                ac.logout(user);
            }
        });

    }

    public void newsLoad(User user) {
        // main panel for news
        JPanel combinedContainer = new JPanel();
        combinedContainer.setLayout(new BoxLayout(combinedContainer, BoxLayout.Y_AXIS));
        combinedContainer.setBackground(Color.WHITE);
        // stories panel
        JPanel storiesPanel = new JPanel();
        storiesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        storiesPanel.setBackground(Color.LIGHT_GRAY);
        // get friends stories
        List<IContent> stories = newsFeed.getStories();

        for (IContent content : stories) {
            JPanel story = new Story(user.getUsername(), user.getProfile().getProfilePhoto(), content);
            story.setOpaque(true);
            storiesPanel.add(story);
        }
        // Horizontal scroll pane for stories
        JPanel storiesWrapper = new JPanel(new GridBagLayout());
        storiesWrapper.add(storiesPanel);
        JScrollPane storiesScrollPane = new JScrollPane(
                storiesWrapper,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        storiesScrollPane.setPreferredSize(new Dimension(550, 400));
        combinedContainer.add(storiesScrollPane);
        // panel for posts
        JPanel postsContainer = new JPanel();
        postsContainer.setLayout(new BoxLayout(postsContainer, BoxLayout.Y_AXIS));
        postsContainer.setBackground(Color.WHITE);
        List<IContent> posts = newsFeed.getPosts();

        for (IContent content : posts) {
            JPanel postPanel = new Post(user.getUsername(), user.getProfile().getProfilePhoto(), content);
            postsContainer.add(postPanel);
        }
        combinedContainer.add(postsContainer);
        // main horizontal scroll
        news.setViewportView(combinedContainer);
        news.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        news.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public void friendsLoad(User user) {
        JPanel mainFriends = new JPanel();
        mainFriends.setLayout(new BoxLayout(mainFriends, BoxLayout.Y_AXIS));
        mainFriends.setBackground(Color.WHITE);
        // get user friends
        List<String> fr = user.getProfile().getFriends().getFriends();
        // display it on a panel
        for(String friend : fr) {
            mainFriends.add(new FriendView(userDatabase.getUser(friend)));
        }
        friends.setViewportView(mainFriends);
        friends.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        friends.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public void suggestionsLoad(){
        JPanel mainFriends = new JPanel();
        mainFriends.setLayout(new BoxLayout(mainFriends, BoxLayout.Y_AXIS));
        mainFriends.setBackground(Color.WHITE);
        List<User> Suggestions = newsFeed.getSuggestions();
        for(User user : Suggestions) {

            FriendAdd f = new FriendAdd(user);
            f.getAddButton().addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    friendManager.sendRequest(user);
                }
            });
            mainFriends.add(f);
            suggestionsLoad();
        }
        suggestions.setViewportView(mainFriends);
        suggestions.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        suggestions.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    }

    public void createPost(){
        secondryWindow = new JFrame();
        CreatePost createPost = new CreatePost(secondryWindow);
        secondryWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                secondryWindow = null;
            }
        });
        createPost.getCreateButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                newsFeed.createPost(createPost.getText(), createPost.getImage());
                secondryWindow.dispose();
                secondryWindow = null;
            }
        });

    }
    public void createStory(){
        System.out.println("in function createStory");
        if (secondryWindow != null) {
            return;
        }
        secondryWindow = new JFrame();
        CreateStory createStory = new CreateStory(secondryWindow);
        System.out.println("in created createStory");
        secondryWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                secondryWindow = null;
            }
        });
        createStory.getCreateButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                newsFeed.createPost(createStory.getText(), createStory.getImage());
                secondryWindow.dispose();
                secondryWindow = null;
            }
        });

    }



}
