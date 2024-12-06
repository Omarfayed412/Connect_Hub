package Frontend;
import Backend.*;
import Backend.Database.UserDBA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FriendManagerGUI extends JFrame {

    private JTabbedPane tabbedPane1;
    private JButton acceptButton;
    private JButton declineButton;
    private JButton sendRequestButton;
    private JButton removeButton;
    private JButton blockButton;
    private JPanel Container;
    private JPanel friendRequestPanel;
    private JPanel friendListPanel;
    private JPanel friendSuggestionsPanel;
    private JList suggestionsList;
    private DefaultListModel<String> friendRequestsModel;
    private DefaultListModel<String> friendsListModel;
    private DefaultListModel<String> friendsSuggestionsModel;
    private JList requestsList;
    private JList friendsList;
    private UserInterfaceID user;
    private UserDBA userDataBase;
    private FriendManager friendManager;

    public FriendManagerGUI(UserDBA userDataBase, UserInterfaceID user, FriendManager friendManager) {
        this.userDataBase = userDataBase;
        this.friendManager = friendManager;
        this.user = user;
        setVisible(true);   /// set visibility of this page if not the page won't appear
        setSize(new Dimension(400, 400));  ///  set the dimns of the panel append to us  and it is a necessity
        setContentPane(Container);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoadRequests();
        LoadFriends();
        //   LoadSuggestions();


        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRequest = (String) requestsList.getSelectedValue();
                if (selectedRequest != null) {
                    // Accept the request and move to friends list
                    String userId = userDataBase.getUserIdByName(selectedRequest);
                    friendManager.acceptRequest(userDataBase.getUser(userId));
                   displayMessage("Friend Request is accepted !!");
                }
                LoadFriends();
                LoadRequests();
                // Reload the friend list

            }
        });
        declineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRequest = (String) requestsList.getSelectedValue();
                if (selectedRequest != null) {
                    String userId = userDataBase.getUserIdByName(selectedRequest);
                    friendManager.declineRequest(userDataBase.getUser(userId));
                    LoadRequests();  // Reload the pending requests
                    displayMessage("Friend Request is Declined !!");
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRequest = (String) requestsList.getSelectedValue();
                if (selectedRequest != null) {
                    String userId = userDataBase.getUserIdByName(selectedRequest);
                    String name = userDataBase.getUser(userId).getUsername();
                    friendManager.removeFriend(userDataBase.getUser(userId));
                    LoadFriends();
                    LoadSuggestions();
                    displayMessage(name +" is removed !!");
                }
            }
        });
        blockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRequest = (String) requestsList.getSelectedValue();
                if (selectedRequest != null) {
                    String userId = userDataBase.getUserIdByName(selectedRequest);
                    friendManager.blockFriend(userDataBase.getUser(userId));
                    String name = userDataBase.getUser(userId).getUsername();
                    displayMessage(name +" is blocked !!");
                    LoadFriends();
                }
            }
        });
        sendRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRequest = (String) requestsList.getSelectedValue();
                if (selectedRequest != null) {
                    String userId = userDataBase.getUserIdByName(selectedRequest);
                    friendManager.sendRequest(userDataBase.getUser(userId));
                    String name = userDataBase.getUser(userId).getUsername();
                    displayMessage(" Request is sent !!");
                    LoadFriends();
                }
            }
        });
    }

    public void LoadRequests() {
        friendRequestsModel = new DefaultListModel<>();
        requestsList.setModel(friendRequestsModel);
        //       friendRequestsModel.addElement("Yaseen Islam");
        for (String id : this.friendManager.getPending()) {
            friendRequestsModel.addElement(userDataBase.getUser(id).getUsername());
        }


    }

    public void LoadFriends() {
        friendsListModel = new DefaultListModel<>();
        friendsList.setModel(friendsListModel);
        //     friendsListModel.addElement("Waseem Islam");
        for (String id : this.friendManager.getFriends()) {
            friendRequestsModel.addElement(userDataBase.getUser(id).getUsername());
        }


    }

    public void LoadSuggestions() {
        friendsSuggestionsModel = new DefaultListModel<>();
        suggestionsList.setModel(friendsSuggestionsModel);
        //         friendsListModel.addElement("Waseem Islam");
        for (UserInterface user : userDataBase.getUsers()) {
            if ((!friendManager.isFriend(user.getUserID())) && (!friendManager.isBlocked(user.getUserID())))
                friendsSuggestionsModel.addElement(user.getUsername());
        }


    }
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(null, message);  // No need for 'this'
    }


    public static void main(String[] args) {

        UserDBA userDataBase = null;
        FriendManager friendManager1 = null;
        UserInterfaceID userInterfaceID  = null;
        new FriendManagerGUI(userDataBase, userInterfaceID, friendManager1);
    }


}
