package Frontend;
import Backend.*;
import Backend.Database.DataBase;

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
    private FriendsInterface friends;
    private DefaultListModel<String> friendRequestsModel;
    private DefaultListModel<String> friendsListModel;
    private DefaultListModel<String> friendsSuggestionsModel;
    private JList requestsList;
    private JList friendsList;
    private UserInterfaceID user;
    private DataBase userDataBase;

    public  FriendManagerGUI (DataBase userDataBase,UserInterfaceID user)
    {
        this.userDataBase = userDataBase;
        this.user = user;
        this.friends = user.getProfile().getFriends();
        setVisible(true);   /// set visibility of this page if not the page won't appear
        setSize(new Dimension(400,400));  ///  set the dimns of the panel append to us  and it is a necessity
        setContentPane(Container);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoadRequests();
        LoadFriends();
     //   LoadSuggestions();


        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void LoadRequests()
    {
        friendRequestsModel = new DefaultListModel<>();
        requestsList.setModel(friendRequestsModel);
        //       friendRequestsModel.addElement("Yaseen Islam");
        for(String id : friends.getPending())
        {
            friendRequestsModel.addElement(userDataBase.getUser(id).getUsername());
        }


    }
    public void LoadFriends()
    {
        friendsListModel = new DefaultListModel<>();
        friendsList.setModel(friendsListModel);
        //     friendsListModel.addElement("Waseem Islam");
        for(String id : friends.getFriends())
        {
            friendRequestsModel.addElement(userDataBase.getUser(id).getUsername());
        }


    }
    public void LoadSuggestions()
    {
        friendsSuggestionsModel = new DefaultListModel<>();
        suggestionsList.setModel(friendsSuggestionsModel);
        //         friendsListModel.addElement("Waseem Islam");
        for(UserInterface user : userDataBase.getUsers())
        {
            if((!friends.isFriend(user.getUserID()))&&(!friends.isBlocked(user.getUserID())))
                friendsSuggestionsModel.addElement(user.getUsername());
        }


    }
    public static void main(String[] args) {
        UserInterfaceID userInterfaceID = null;
        DataBase userDataBase = null;
        new FriendManagerGUI(userDataBase,userInterfaceID);
    }

}
