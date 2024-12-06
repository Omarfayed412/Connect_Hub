package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

    public NewsFeed(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);
        setContentPane(main);
        setVisible(true);
        userName.setText("Mohamed Khamis");
        ImageIcon imageIcon = new ImageIcon("Connect_Hub/test/img.png");

        Image scaledImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        profilePhoto.setText("");
        profilePhoto.setIcon(scaledIcon);
        profilePhoto.setText("");
        newsLoad();
        friendsLoad();
        suggestionsLoad();

        createPostButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondryWindow != null) {
                    return;
                }
                secondryWindow = new JFrame();
                CreatePost createPost = new CreatePost(secondryWindow);
                secondryWindow.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        secondryWindow = null;
                    }
                });
            }
        });
        createStoryButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondryWindow != null) {
                    return;
                }
                secondryWindow = new JFrame();
                CreateStory createStory = new CreateStory(secondryWindow);
                secondryWindow.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        secondryWindow = null;
                    }
                });
            }
        });


    }

    public void newsLoad(){
        JPanel combinedContainer = new JPanel();
        combinedContainer.setLayout(new BoxLayout(combinedContainer, BoxLayout.Y_AXIS));
        combinedContainer.setBackground(Color.WHITE);
        JPanel storiesPanel = new JPanel();
        storiesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        storiesPanel.setBackground(Color.LIGHT_GRAY);
        for (int i = 1; i <= 10; i++) {
            JPanel story = new Story();
            story.setOpaque(true);
            storiesPanel.add(story);
        }

        JPanel storiesWrapper = new JPanel(new GridBagLayout());
        storiesWrapper.add(storiesPanel);
        JScrollPane storiesScrollPane = new JScrollPane(
                storiesWrapper,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        storiesScrollPane.setPreferredSize(new Dimension(550, 400));
        combinedContainer.add(storiesScrollPane);

        JPanel postsContainer = new JPanel();
        postsContainer.setLayout(new BoxLayout(postsContainer, BoxLayout.Y_AXIS));
        postsContainer.setBackground(Color.WHITE);


        for (int i = 1; i <= 20; i++) {
            JPanel postPanel = new Post();
            postsContainer.add(postPanel);
        }
        combinedContainer.add(postsContainer);
        news.setViewportView(combinedContainer);
        news.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        news.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public void friendsLoad(){
        JPanel mainFriends = new JPanel();
        mainFriends.setLayout(new BoxLayout(mainFriends, BoxLayout.Y_AXIS));
        mainFriends.setBackground(Color.WHITE);
        for(int i = 0; i<10; i++){
            mainFriends.add(new FriendView());
        }

        friends.setViewportView(mainFriends);
        friends.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        friends.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public void suggestionsLoad(){
        JPanel mainFriends = new JPanel();
        mainFriends.setLayout(new BoxLayout(mainFriends, BoxLayout.Y_AXIS));
        mainFriends.setBackground(Color.WHITE);
        for(int i = 0; i<10; i++){
            mainFriends.add(new FriendAdd());
        }

        suggestions.setViewportView(mainFriends);
        suggestions.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        suggestions.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    }


    public static void main(String[] args) {
        new NewsFeed();
    }

}
