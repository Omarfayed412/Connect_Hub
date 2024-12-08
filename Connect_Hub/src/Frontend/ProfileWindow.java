package Frontend;

import Backend.AccountManager;
import Backend.ContentCreation.IContent;
import Backend.Database.UserDatabase;
import Backend.User;
import Frontend.NewFeedWindows.NewsFeed;
import Frontend.NewFeedWindows.Post;
import Frontend.NewFeedWindows.Story;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ProfileWindow extends JFrame {

    private JPanel panel1;
    private JTextArea bio;
    private JButton friendsManageButton;
    private JButton editPorfileButton;
    private JButton newsFeedButton;
    private JLabel coverPhoto;
    private JLabel profilePhoto;
    private JLabel userName;
    private JScrollPane postsScroll;
    private UserDatabase userDatabase = UserDatabase.getUserDataBase();
    private User user;
    private JFrame secondryWindow = null;
    private Backend.NewsFeed newsFeed;
    

    public ProfileWindow(User user) {
        userDatabase.load();
        this.user = userDatabase.getUser(user.getUserID());
        this.newsFeed = new Backend.NewsFeed(user);
        setContentPane(panel1);
        setTitle("Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);
        setVisible(true);
        loadProfile();
        bio.setWrapStyleWord(true);
        bio.setLineWrap(true);
        bio.setEditable(false);
        DefaultCaret caret = (DefaultCaret) bio.getCaret();
        caret.setVisible(false);
        loadPosts();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AccountManager ac = AccountManager.getInstance(userDatabase);
                ac.logout(user.getUserID());
                userDatabase.save();
                System.out.println("Window closed");
            }
        });
        newsFeedButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new NewsFeed(user);
                dispose();
            }
        });
        editPorfileButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondryWindow != null) {
                    return;
                }
                secondryWindow = new JFrame("Edit Profile");
                EditAccount w = new EditAccount(secondryWindow, user);
                secondryWindow.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        secondryWindow = null;
                    }
                });
                w.getSaveButton().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        w.saveProfile();
                        secondryWindow.dispose();
                        secondryWindow = null;
                        loadProfile();
                    }
                });

            }
        });
        friendsManageButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondryWindow != null) {
                    return;
                }
                secondryWindow = new JFrame("Friends");
                FriendManagerGUI win = new FriendManagerGUI(secondryWindow, user);
                secondryWindow.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        secondryWindow = null;

                    }
                });
                userDatabase.save();
                userDatabase.load();
                ProfileWindow.this.user = userDatabase.getUser(user.getUserID());

            }
        });


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
    }

}
