package Frontend.Profile;

import Backend.ContentCreation.IContent;
import Backend.ProfileAndFriends.FriendManager;
import Backend.ProfileAndFriends.NewsFeed;
import Backend.User.User;
import Frontend.NewFeedWindows.Post;
import Frontend.NewFeedWindows.Story;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserView extends  JFrame {
    private JPanel main;
    private JTextArea bio;
    private JButton blockButton;
    private JButton button2;
    private JLabel coverPhoto;
    private JLabel profilePhoto;
    private JLabel userName;
    private JLabel status;
    private JLabel blockConfirm;
    private JButton newsFeedButton;
    private JScrollPane postsScroll;
    private User user;
    private FriendManager friendManager;
    private User userIn;

    public UserView(User userIn, User user, FriendManager friendManager) {
        this.user = user;
        this.friendManager = friendManager;
        this.userIn = userIn;
        setContentPane(main);
        setTitle("Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);
        setVisible(true);
        bio.setWrapStyleWord(true);
        bio.setLineWrap(true);
        bio.setEditable(false);
        DefaultCaret caret = (DefaultCaret) bio.getCaret();
        loadProfile();
        newsFeedButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frontend.NewFeedWindows.NewsFeed(userIn);
            }
        });
        if (friendManager.isFriend(user.getUserID()))
            button2.setText("UnFriend");
        else
            button2.setText("Add Friend");
        blockButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                friendManager.blockFriend(user);
                blockButton.setVisible(false);
                button2.setVisible(false);
                blockConfirm.setVisible(true);
                blockConfirm.setText("Blocked");
            }
        });
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                blockButton.setVisible(false);
                button2.setVisible(false);
                status.setVisible(true);
                if(friendManager.isFriend(user.getUserID())) {
                    friendManager.removeFriend(user);
                    status.setText("Removed Friend");
                }
                else {
                    friendManager.sendRequest(user);
                    status.setText("Request sent");
                }
            }
        });
        caret.setVisible(false);
        status.setVisible(false);
        blockConfirm.setVisible(false);
        loadPosts();
    }
    private void loadProfile() {
        ImageIcon pPhoto = new ImageIcon(this.user.getProfile().getProfilePhoto());
        Image scaledImage = pPhoto.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        profilePhoto.setText("");
        profilePhoto.setIcon(scaledIcon);
        ImageIcon cPhoto = new ImageIcon(this.user.getProfile().getCoverPhoto());
        scaledImage = cPhoto.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        coverPhoto.setText("");
        coverPhoto.setIcon(scaledIcon);
        userName.setText(this.user.getUsername());
        bio.setText(this.user.getProfile().getBio());
    }

    private void loadPosts(){
        NewsFeed newsFeed = new NewsFeed(user);
        // main panel for news
        JPanel combinedContainer = new JPanel();
        combinedContainer.setLayout(new BoxLayout(combinedContainer, BoxLayout.Y_AXIS));
        combinedContainer.setBackground(Color.WHITE);
        // stories panel
        JPanel storiesPanel = new JPanel();
        storiesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        storiesPanel.setBackground(Color.LIGHT_GRAY);
        // get friends stories
        java.util.List<IContent> stories = newsFeed.getMyStories();

        for (IContent content : stories) {
            JPanel story = new Story(content);
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
        storiesScrollPane.setPreferredSize(new Dimension(400, 400));
        combinedContainer.add(storiesScrollPane);
        // panel for posts
        JPanel postsContainer = new JPanel();
        postsContainer.setLayout(new BoxLayout(postsContainer, BoxLayout.Y_AXIS));
        postsContainer.setBackground(Color.WHITE);
        List<IContent> posts = newsFeed.getMyPosts();

        for (IContent content : posts) {
            JPanel postPanel = new Post(content);
            postsContainer.add(postPanel);
        }
        combinedContainer.add(postsContainer);
        // main horizontal scroll
        postsScroll.setViewportView(combinedContainer);
        postsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        postsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        postsScroll.getVerticalScrollBar().setValue(0);
    }

}

