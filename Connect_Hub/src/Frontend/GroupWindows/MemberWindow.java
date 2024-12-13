package Frontend.GroupWindows;

import Backend.ContentCreation.IContent;
import Backend.ContentCreation.Post;
import Backend.Database.*;
import Backend.GroupManagement.Group;
import Backend.GroupManagement.MemberRole;
import Backend.User.User;
import Frontend.NewFeedWindows.CreatePost;
import Frontend.NewFeedWindows.NewsFeed;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.util.List;

public class MemberWindow extends JFrame {
    private JPanel main;
    private JTextArea discription;
    private JLabel photo;
    private JLabel groupName;
    private JButton leaveGroupButton;
    private JButton createPostButton;
    private JButton newsFeedButton;
    private JScrollPane feed;
    private JButton refreshButton;
    private JFrame secondryWindow;
    private Group group;
    private User user;
    private MemberRole memberRole = MemberRole.getInstance();
    private IContentDatabase contentDatabase = ContentDatabase.getInstance();
    private IUserDatabase userDatabase = UserDatabase.getUserDataBase();
    private GroupsInterface groupDatabase = GroupsDataBase.getGroupsDataBase();

    public MemberWindow(User user, Group group) {
        this.user = user;
        this.group = group;
        setContentPane(main);
        setTitle("Group");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);
        setVisible(true);
        discription.setWrapStyleWord(true);
        discription.setLineWrap(true);
        discription.setEditable(false);
        DefaultCaret caret = (DefaultCaret) discription.getCaret();
        caret.setVisible(false);
        contentDatabase.load();
        groupDatabase.load();
        loadProfile();
        createPostButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                groupDatabase.load();
                Group g = groupDatabase.getGroup(group.getGroupID());
                if (!g.isMember(user)) {
                    JOptionPane.showMessageDialog(null, "You are no longer a member");
                    dispose();
                    new NewsFeed(user);
                    return;
                }
                if (secondryWindow != null) {
                    return;
                }
                secondryWindow = new JFrame();
                secondryWindow.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        secondryWindow = null;
                    }
                });
                CreatePost c = new CreatePost(secondryWindow);
                c.getCreateButton().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        contentDatabase.load();
                        String image = c.getImage();
                        String text = c.getText();
                        Post post = new Post();
                        post.setAuthorId(user.getUserID());
                        LocalDateTime now = LocalDateTime.now();
                        post.setTimeStamp(now.toString());
                        if (image != null)
                            post.setImgPath(image);
                        String id = "G" + (group.getPosts().size()+1) + user.getUserID();
                        post.setContentId(id);
                        post.setTxtContent(text);
                        contentDatabase.addContent(post);
                        memberRole.post(post, group, user);
                        contentDatabase.save();
                        secondryWindow.dispose();
                        secondryWindow = null;
                        loadPosts();
                    }
                });
            }
        });
        newsFeedButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new NewsFeed(user);
            }
        });
        refreshButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                groupDatabase.load();
                Group g = groupDatabase.getGroup(group.getGroupID());
                if (!g.isMember(user)) {
                    JOptionPane.showMessageDialog(null, "You are no longer a member");
                    dispose();
                    new NewsFeed(user);
                    return;
                }
                contentDatabase.load();
                groupDatabase.load();
                Group gr = groupDatabase.getGroup(group.getGroupID());
                loadProfile();
                if (gr.isAdmin(user)){
                    dispose();
                    new AdminWindow(user, group);
                }
                else{
                    System.out.println("Not Admin: "+ gr.getAdmins());
                }

                loadPosts();
            }
        });
        leaveGroupButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Leave Group------------------------------------------>");
                memberRole.leaveGroup(user, group);
                dispose();
                new NewsFeed(user);
            }
        });
        loadPosts();


    }
    public void loadProfile(){
        this.group = groupDatabase.getGroup(group.getGroupID());
        groupName.setText(group.getName());
        discription.setText(group.getDescription());
        ImageIcon pPhoto = new ImageIcon(group.getPhotoPath());
        Image scaledImage = pPhoto.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        photo.setText("");
        photo.setIcon(scaledIcon);
        photo.setText("");
    }

    public void loadPosts() {
        this.group = groupDatabase.getGroup(group.getGroupID());
        JPanel postsContainer = new JPanel();
        postsContainer.setLayout(new BoxLayout(postsContainer, BoxLayout.Y_AXIS));
        postsContainer.setBackground(Color.WHITE);
        List<IContent> posts = group.getPosts().reversed();
        System.out.println(posts.size()+"Size----------------------------->");
        for (IContent content : posts) {
            JPanel postPanel = new Frontend.NewFeedWindows.Post(content);
            postsContainer.add(postPanel);
        }
        // main horizontal scroll
        feed.setViewportView(postsContainer);
        feed.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        feed.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        feed.getVerticalScrollBar().setValue(0);
    }




}
