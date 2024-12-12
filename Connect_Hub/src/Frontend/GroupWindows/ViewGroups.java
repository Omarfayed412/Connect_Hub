package Frontend.GroupWindows;

import Backend.Database.GroupsDataBase;
import Backend.Database.GroupsInterface;
import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;
import Backend.GroupManagement.Group;
import Backend.User.User;
import Frontend.Profile.FriendRBWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewGroups {
    private JPanel panel1;
    private JScrollPane mainScroll;
    private User user;
    private IUserDatabase userDatabase = UserDatabase.getUserDataBase();
    private GroupsInterface groupDatabase = GroupsDataBase.getGroupsDataBase();
    private JFrame mainFrame;
    private JFrame profileFrame;

    public ViewGroups(JFrame frame,JFrame profileWindow,  User user) {
        this.mainFrame = frame;
        this.profileFrame = profileWindow;
        this.user = user;
        frame.setContentPane(panel1);
        frame.setVisible(true);
        frame.setSize(400, 600);
        loadGroups();
    }

    private void loadGroups() {
        System.out.println("Loading Groups");
        userDatabase.load();
        groupDatabase.load();
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
                       new PAdminWindow();
                   else if (group.isMember(user))
                       new MemberWindow();
                   else
                       new AdminWindow();
                   mainFrame.dispose();
                   profileFrame.dispose();
                }
            });

            groupsJoined.add(f);
        }
        mainScroll.setViewportView(groupsJoined);
        mainScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

}
