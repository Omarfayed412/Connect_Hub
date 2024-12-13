package Frontend.NewFeedWindows;

import Backend.*;
import Backend.ContentCreation.IContent;
import Backend.Database.*;
import Backend.GroupManagement.Group;
import Backend.GroupManagement.MemberRole;
import Backend.Notifications.FriendRequestNotification;
import Backend.Notifications.GroupNotifications;
import Backend.ProfileAndFriends.FriendManager;
import Backend.User.User;
import Frontend.GroupWindows.*;
import Frontend.MainWindow2;
import Frontend.Notifications.GroupNews;
import Frontend.Notifications.UserNews;
import Frontend.Profile.ProfileWindow;
import Frontend.Profile.UserView;
import Frontend.SearchPanels.GroupSearch;
import Frontend.SearchPanels.UserSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Objects;

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
    private JScrollPane sGroups;
    private JPanel search;
    private JTextField searchFeild;
    private JButton searchButton;
    private JScrollPane searchScroll;
    private JButton createGroupButton;
    private JScrollPane groupsIn;
    private JScrollPane notScroll;
    private JFrame secondryWindow = null;
    private Backend.ProfileAndFriends.NewsFeed newsFeed;
    private IContentDatabase contentDatabase = ContentDatabase.getInstance();
    private IUserDatabase userDatabase = UserDatabase.getUserDataBase();
    private GroupsInterface groupDatabase = GroupsDataBase.getGroupsDataBase();
    private FriendManager friendManager;
    private User user;

    public NewsFeed(User user) {
        System.out.println("user");
        contentDatabase.load();
        this.userDatabase.load();
        this.user = userDatabase.getUser(user.getUserID());
        this.newsFeed = new Backend.ProfileAndFriends.NewsFeed(this.user);
        this.friendManager = new FriendManager(this.user);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);
        setContentPane(main);
        setVisible(true);
        userName.setText(this.user.getUsername());
        ImageIcon pPhoto = new ImageIcon(this.user.getProfile().getProfilePhoto());

        Image scaledImage = pPhoto.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        profilePhoto.setText("");
        profilePhoto.setIcon(scaledIcon);
        profilePhoto.setText("");
        newsLoad();
        friendsLoad();
        suggestionsLoad();
        suggestionsGroupsLoad();
        groupIn();

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
                refresh();
            }
        });

        openProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileWindow(user);
                dispose();
            }
        });

        createGroupButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondryWindow != null) {
                    return;
                }
                secondryWindow = new JFrame("Create Group");
                secondryWindow.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        secondryWindow = null;
                    }
                });

                CreateGroup c = new CreateGroup(secondryWindow, user);
                c.getCreateButton().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        c.createGroup();
                        secondryWindow.dispose();
                        secondryWindow = null;
                    }
                });
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AccountManager ac = AccountManager.getInstance(userDatabase);
                ac.logout(user.getUserID());
                userDatabase.save();
                contentDatabase.save();
                System.out.println("Window closed");
            }
        });
        logout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AccountManager ac = AccountManager.getInstance(userDatabase);
                ac.logout(user.getUserID());
                userDatabase.save();
                contentDatabase.save();
                dispose();
                new MainWindow2();
            }
        });

        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                search(searchFeild.getText());
            }
        });
        notificationsLoad();
    }
    public void refresh() {
        userDatabase.load();
        contentDatabase.load();
        this.user = userDatabase.getUser(this.user.getUserID());
        newsLoad();
        friendsLoad();
        suggestionsLoad();
        suggestionsGroupsLoad();
        groupIn();
        notificationsLoad();
    }

    public void newsLoad() {
        // main panel for news
        JPanel combinedContainer = new JPanel();
        combinedContainer.setLayout(new BoxLayout(combinedContainer, BoxLayout.Y_AXIS));
        combinedContainer.setBackground(Color.WHITE);
        // stories panel
        JPanel storiesPanel = new JPanel();
        storiesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        storiesPanel.setBackground(Color.LIGHT_GRAY);
        // get friends stories
        List<IContent> stories = newsFeed.getStories().reversed();

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
        storiesScrollPane.setPreferredSize(new Dimension(550, 400));
        combinedContainer.add(storiesScrollPane);
        // panel for posts
        JPanel postsContainer = new JPanel();
        postsContainer.setLayout(new BoxLayout(postsContainer, BoxLayout.Y_AXIS));
        postsContainer.setBackground(Color.WHITE);
        List<IContent> posts = newsFeed.getPosts().reversed();

        for (IContent content : posts) {
            JPanel postPanel = new Post(content);
            postsContainer.add(postPanel);
        }
        List<JPanel> gp = newsFeed.getGroupPosts().reversed();

        for (JPanel panel : gp) {
            postsContainer.add(panel);
        }

        combinedContainer.add(postsContainer);
        // main horizontal scroll
        news.setViewportView(combinedContainer);
        news.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        news.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        news.getVerticalScrollBar().setValue(0);
    }

    public void friendsLoad() {
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
        friends.getVerticalScrollBar().setValue(0);
    }

    public void suggestionsLoad(){
        JPanel mainFriends = new JPanel();
        mainFriends.setLayout(new BoxLayout(mainFriends, BoxLayout.Y_AXIS));
        mainFriends.setBackground(Color.WHITE);
        List<User> Suggestions = newsFeed.getSuggestions();
        for(User ser : Suggestions) {

            FriendAdd f = new FriendAdd(ser);
            f.getAddButton().addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    friendManager.sendRequest(ser);
                    f.getAddButton().setVisible(false);
                    f.getStatusLabel().setVisible(true);
                    f.getStatusLabel().setText("Request sent");
                }
            });
            mainFriends.add(f);
        }
        suggestions.setViewportView(mainFriends);
        suggestions.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        suggestions.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        suggestions.getVerticalScrollBar().setValue(0);

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
                newsLoad();
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
                newsLoad();
            }
        });
        createStory.getCreateButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                newsFeed.createStory(createStory.getText(), createStory.getImage());
                secondryWindow.dispose();
                secondryWindow = null;
                newsLoad();
            }
        });

    }

    public void suggestionsGroupsLoad(){
        JPanel groups = new JPanel();
        groups.setLayout(new BoxLayout(groups, BoxLayout.Y_AXIS));
        groups.setBackground(Color.WHITE);
        List<Group> suggestions = newsFeed.getSuggestionsGroup();
        System.out.println(suggestions+ "Suggestions loaded------------------------------");
        for(Group group : suggestions) {

            GroupSG f = new GroupSG(group);
            f.getJoinButton().addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    groupDatabase.load();
                    userDatabase.load();
                    user = userDatabase.getUser(user.getUserID());
                    Group g=  groupDatabase.getGroup(group.getGroupID());
                    g.addRequest(user);
                    user.getGroupManager().RequettojoinGroup(g);
                    f.getJoinButton().setVisible(false);
                    f.getStatus().setText("Request sent");
                    f.getStatus().setVisible(true);
                    groupDatabase.save();
                    userDatabase.save();
                }
            });
            groups.add(f);
        }
        this.sGroups.setViewportView(groups);
        this.sGroups.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.sGroups.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sGroups.getVerticalScrollBar().setValue(0);

    }

    public void search(String text){

        List<User> users = newsFeed.searchUser(text);
        List<Group> groups = newsFeed.searchGroup(text);
        JPanel searchP = new JPanel();
        searchP.setLayout(new BoxLayout(searchP, BoxLayout.Y_AXIS));
        searchP.setBackground(Color.WHITE);
        // display it on a panel
        for(User userIN : users) {
            //System.out.println("User Found"userIN);
            UserSearch userSearch = new UserSearch(userIN);
            JButton button1 = userSearch.getButton1();
            JButton block = userSearch.getBlockButton();
            JButton view = userSearch.getViewProfileButton();
            JLabel status = userSearch.getStatus();
            if(Objects.equals(userIN.getUserID(), user.getUserID())) {
                button1.setVisible(false);
                block.setVisible(false);
                view.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new ProfileWindow(user);
                    }
                });

            }
            else {
                if (friendManager.isFriend(userIN.getUserID()))
                    button1.setText("UnFriend");
                else
                    button1.setText("Add Friend");
                button1.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (friendManager.isFriend(userIN.getUserID())) {
                            friendManager.removeFriend(userIN);
                            status.setText("Friend Removed");
                        }
                        else {
                            friendManager.sendRequest(userIN);
                            status.setText("Request sent");
                        }
                        button1.setVisible(false);
                        block.setVisible(false);
                        status.setVisible(true);

                    }
                });
                block.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        friendManager.blockFriend(userIN);
                        button1.setVisible(false);
                        view.setVisible(false);
                        block.setVisible(false);
                        status.setVisible(true);
                        status.setText("User blocked");
                    }
                });
                view.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new UserView(user, userIN, friendManager);
                    }
                });
            }
            searchP.add(userSearch);
        }
        for (Group group : groups) {
            GroupSearch groupSearch = new GroupSearch(group);
            JButton button1 = groupSearch.getButton1();
            JButton view = groupSearch.getViewButton();
            JLabel status = groupSearch.getStatus();
            if (group.isMember(user)) {
                if (!group.isPrimaryAdmin(user))
                    button1.setText("Leave Group");
                else
                    button1.setVisible(false);
            }
            else {
                if(group.isPending(user)) {
                    button1.setVisible(false);
                    status.setText("Request sent");
                    status.setVisible(true);
                }
                button1.setText("Join Group");

            }
            button1.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    MemberRole memberRole = MemberRole.getInstance();
                    if (group.isMember(user)) {
                        memberRole.leaveGroup(user, group);
                        status.setText("Leave Group");
                        status.setVisible(true);
                        button1.setVisible(false);
                        view.setVisible(false);
                    }
                    else {
                        button1.setText("Join Group");
                        groupDatabase.getGroup(group.getGroupID()).addRequest(user);
                        userDatabase.getUser(user.getUserID()).getGroupManager().RequettojoinGroup(group);
                        status.setText("Request sent");
                        status.setVisible(true);
                        button1.setVisible(false);
                        view.setVisible(false);
                        groupDatabase.save();
                    }
                }
            });
            view.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    if (group.isPrimaryAdmin(user))
                        new PAdminWindow(user, group);
                    else if (group.isAdmin(user))
                        new AdminWindow(user, group);
                    else
                        new MemberWindow(user, group);

                }
            });
            searchP.add(groupSearch);


        }
        searchScroll.setViewportView(searchP);
        searchScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        searchScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        searchScroll.getVerticalScrollBar().setValue(0);

    }

    public void notificationsLoad(){
        JPanel notifications = new JPanel();
        notifications.setLayout(new BoxLayout(notifications, BoxLayout.Y_AXIS));
        notifications.setBackground(Color.WHITE);
        // get user friends
        List<FriendRequestNotification> fr = user.getFRNotifications().reversed();
        List<GroupNotifications> gr = user.getGroupNotifications().reversed();
        // display it on a panel
        for(FriendRequestNotification requestNotification : fr) {
            UserNews userNews = new UserNews(requestNotification);
            notifications.add(userNews);
        }
        for(GroupNotifications requestNotification : gr) {
            GroupNews userNews = new GroupNews(requestNotification);
            notifications.add(userNews);
        }
        notScroll.setViewportView(notifications);
        notScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        notScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        notScroll.getVerticalScrollBar().setValue(0);
    }

    public void groupIn(){
        this.user = userDatabase.getUser(this.user.getUserID());
        List<String> groups = user.getGroupManager().getGroupJoined();
        System.out.println(groups.size() + "+===============================================");
        JPanel groupsJoined = new JPanel();
        groupsJoined.setLayout(new BoxLayout(groupsJoined, BoxLayout.Y_AXIS));
        groupsJoined.setBackground(Color.WHITE);
        for(String groupId : groups) {
            System.out.println(groupId+"groups in");
            Group group = groupDatabase.getGroup(groupId);
            GroupPanel f = new GroupPanel(group);
            JButton viewButton = f.getViewGroup();
            viewButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (group.isPrimaryAdmin(user))
                        new PAdminWindow(user, group);
                    else if (group.isAdmin(user))
                        new AdminWindow(user, group);
                    else
                        new MemberWindow(user, group);
                    dispose();
                }
            });

            groupsJoined.add(f);
        }
        groupsIn.setViewportView(groupsJoined);
        groupsIn.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        groupsIn.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }


}
