package Frontend;
import Backend.*;
import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;
import Frontend.NewFeedWindows.FriendAdd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FriendManagerGUI  {

    private JTabbedPane mainScroll;
    private JButton sendRequestButton;
    private JPanel Container;
    private JScrollPane friendListScroll;
    private JScrollPane blockedScroll;
    private JScrollPane friendRequestScroll;
    private DefaultListModel<String> friendRequestsModel;
    private DefaultListModel<String> friendsListModel;
    private DefaultListModel<String> friendsSuggestionsModel;
    private User user;
    private IUserDatabase userDataBase;
    private FriendManager friendManager;


    public FriendManagerGUI(JFrame jFrame, User user) {
        this.userDataBase = UserDatabase.getUserDataBase();
        this.friendManager = new FriendManager(user);
        this.user = user;
       jFrame.setVisible(true);   /// set visibility of this page if not the page won't appear
       jFrame.setSize(400, 400);  ///  set the dimns of the panel append to us  and it is a necessity
        jFrame.setContentPane(Container);
        loadRequests();
        loadFriends();
        loadBlocked();


    }
//
    public void loadRequests() {
        List<String> requests = friendManager.getPending();
        JPanel mainFriends = new JPanel();
        mainFriends.setLayout(new BoxLayout(mainFriends, BoxLayout.Y_AXIS));
        mainFriends.setBackground(Color.WHITE);
        for(String userId : requests) {
            User user = userDataBase.getUser(userId);
            FriendRBWindow f = new FriendRBWindow(user);
            JButton acceptButton = f.getButton1();
            acceptButton.setText("Accept");
            JButton declineButton = f.getButton2();
            declineButton.setText("decline");
            acceptButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    friendManager.acceptRequest(user);
                    acceptButton.setVisible(false);
                    declineButton.setVisible(false);
                    f.getStatusLabel().setVisible(true);
                    f.getStatusLabel().setText("Request accepted");
                    loadFriends();
                }
            });
            declineButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    friendManager.declineRequest(user);
                    acceptButton.setVisible(false);
                    declineButton.setVisible(false);
                    f.getStatusLabel().setVisible(true);
                    f.getStatusLabel().setText("Request declined");
                }
            });
            mainFriends.add(f);
        }
        friendRequestScroll.setViewportView(mainFriends);
        friendRequestScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        friendRequestScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    }



    public void loadBlocked() {
        List<String> blocked = friendManager.getBlocked();
        JPanel mainFriends = new JPanel();
        mainFriends.setLayout(new BoxLayout(mainFriends, BoxLayout.Y_AXIS));
        mainFriends.setBackground(Color.WHITE);

        for(String userId : blocked) {
            User user = userDataBase.getUser(userId);
            FriendAdd f = new FriendAdd(user);
            JButton unBlock = f.getAddButton();
            unBlock.setText("Unblock");
            unBlock.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    friendManager.unblockFriend(user);
                    unBlock.setVisible(false);
                    f.getStatusLabel().setVisible(true);
                    f.getStatusLabel().setText("Unblocked");
                }
            });
            mainFriends.add(f);
        }
        blockedScroll.setViewportView(mainFriends);
        blockedScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        blockedScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    }
    public void loadFriends() {
        List<String> requests = friendManager.getFriends();
        JPanel mainFriends = new JPanel();
        mainFriends.setLayout(new BoxLayout(mainFriends, BoxLayout.Y_AXIS));
        mainFriends.setBackground(Color.WHITE);
        for(String userId : requests) {
            User user = userDataBase.getUser(userId);
            FriendRBWindow f = new FriendRBWindow(user);
            JButton remove = f.getButton1();
            remove.setText("Remove");
            JButton block = f.getButton2();
            block.setText("Block");
            remove.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int c = JOptionPane.showConfirmDialog(null, "Are you sure you want to unfriend " + user.getUsername() + "?");
                    if (c == JOptionPane.YES_OPTION) {
                        friendManager.removeFriend(user);
                        remove.setVisible(false);
                        block.setVisible(false);
                        f.getStatusLabel().setVisible(true);
                        f.getStatusLabel().setText("unfriended");
                    }

                }
            });
            block.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int c = JOptionPane.showConfirmDialog(null, "Are you sure you want to block " + user.getUsername() + "?");
                    if (c == JOptionPane.YES_OPTION) {
                        friendManager.blockFriend(user);
                        remove.setVisible(false);
                        block.setVisible(false);
                        f.getStatusLabel().setVisible(true);
                        f.getStatusLabel().setText("blocked");
                    }
                }
            });
            mainFriends.add(f);
        }
        friendListScroll.setViewportView(mainFriends);
        friendListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        friendListScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        }
//
//
//    }
//
//
//
//    }


//    public static void main(String[] args) {
//
//        UserDBA userDataBase = null;
//        FriendManager friendManager1 = null;
//        UserInterfaceID userInterfaceID  = null;
//        new FriendManagerGUI(userDataBase, userInterfaceID, friendManager1);
//    }


}
