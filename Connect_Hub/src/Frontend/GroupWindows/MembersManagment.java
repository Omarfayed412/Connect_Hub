package Frontend.GroupWindows;

import Backend.Database.GroupsDataBase;
import Backend.Database.GroupsInterface;
import Backend.Database.IUserDatabase;
import Backend.Database.UserDatabase;
import Backend.GroupManagement.Group;
import Backend.GroupManagement.PrimaryAdminRole;
import Backend.User.User;
import Frontend.Profile.FriendRBWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MembersManagment {
    private JTabbedPane maintabs;
    private JPanel panel1;
    private JScrollPane members;
    private JScrollPane requests;
    private User user;
    private Group group;
    private GroupsInterface groupDatabase = GroupsDataBase.getGroupsDataBase();
    private IUserDatabase userDatabase = UserDatabase.getUserDataBase();
    private PrimaryAdminRole primaryAdminRole = PrimaryAdminRole.getInstance();

    public MembersManagment(JFrame frame, User user, Group group) {
        this.user = user;
        this.group = group;
        frame.setContentPane(panel1);
        frame.setVisible(true);
        frame.setSize(400, 600);
        loadMembers();
        loadRequests();
    }

    public void loadMembers() {
        groupDatabase.load();
        userDatabase.load();
        this.group =  groupDatabase.getGroup(group.getGroupID());
        this.user =  userDatabase.getUser(user.getUserID());
        JPanel mainMembers = new JPanel();
        mainMembers.setLayout(new BoxLayout(mainMembers, BoxLayout.Y_AXIS));
        mainMembers.setBackground(Color.WHITE);
        // get user friends
        List<String> fr = group.getMemberIDs();
        // display it on a panel
        for(String member : fr) {
            System.out.println("here a member");
            FriendRBWindow frd = new FriendRBWindow(userDatabase.getUser(member));
            JButton button1 = frd.getButton1();
            JButton button2 = frd.getButton2();
            JLabel statusLabel = frd.getStatusLabel();
            if (member.equals(user.getUserID())) {
                button1.setVisible(false);
                button2.setVisible(false);
                statusLabel.setVisible(true);
                statusLabel.setText("Me");
            }
            else if(group.isPrimaryAdmin(userDatabase.getUser(member))) {
                button1.setVisible(false);
                button2.setVisible(false);
                statusLabel.setVisible(true);
                statusLabel.setText("Primary Admin");
            }
            else if (group.isAdmin(userDatabase.getUser(member))) {
                if (group.isPrimaryAdmin(user)){
                    button1.setText("Remove");
                    button2.setText("Demote Admin");
                    button1.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            primaryAdminRole.removeMember(group, userDatabase.getUser(member));
                            primaryAdminRole.demoteAdmin(group, userDatabase.getUser(member));
                            button2.setVisible(false);
                            button1.setVisible(false);
                            statusLabel.setVisible(true);
                            statusLabel.setText("Removed");
                        }
                    });
                    button2.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            primaryAdminRole.demoteAdmin(group, userDatabase.getUser(member));
                            button2.setVisible(false);
                            button1.setVisible(false);
                            statusLabel.setVisible(true);
                            statusLabel.setText("demoted");
                        }
                    });
                    mainMembers.add(frd);
                    continue;
                }
                button1.setVisible(false);
                button2.setVisible(true);
                statusLabel.setVisible(true);
                statusLabel.setText("Admin");
            }
            else {
                if (group.isPrimaryAdmin(user)){
                    button1.setText("Remove");
                    button2.setText("Promote Admin");
                    button1.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            primaryAdminRole.removeMember(group, userDatabase.getUser(member));
                            button2.setVisible(false);
                            button1.setVisible(false);
                            statusLabel.setVisible(true);
                            statusLabel.setText("Removed");
                            userDatabase.save();
                            groupDatabase.save();
                        }
                    });
                    button2.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            userDatabase.load();
                            groupDatabase.load();
                            Group g = groupDatabase.getGroup(group.getGroupID());
                            primaryAdminRole.promoteToAdmin(g, member);
                            button2.setVisible(false);
                            button1.setVisible(false);
                            statusLabel.setVisible(true);
                            statusLabel.setText("Promoted");
                            groupDatabase.save();
                            userDatabase.save();
                        }
                    });
                    mainMembers.add(frd);
                    continue;
                }
                button2.setVisible(false);
                button1.setText("Remove");
                button1.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        primaryAdminRole.removeMember(group, userDatabase.getUser(member));
                        button2.setVisible(false);
                        button1.setVisible(false);
                        statusLabel.setVisible(true);
                        statusLabel.setText("Removed");
                    }
                });
            }


            mainMembers.add(frd);
        }
        members.setViewportView(mainMembers);
        members.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        members.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public void loadRequests() {
        groupDatabase.load();
        userDatabase.load();
        this.group =  groupDatabase.getGroup(group.getGroupID());
        this.user =  userDatabase.getUser(user.getUserID());
        JPanel mainMembers = new JPanel();
        mainMembers.setLayout(new BoxLayout(mainMembers, BoxLayout.Y_AXIS));
        mainMembers.setBackground(Color.WHITE);
        // get user friends
        List<String> fr = group.getPendingRequests();
        // display it on a panel
        for(String member : fr) {
            FriendRBWindow frd = new FriendRBWindow(userDatabase.getUser(member));
            mainMembers.add(frd);
            JButton accept = frd.getButton1();
            JButton decline = frd.getButton2();
            accept.setText("Accept");
            decline.setText("Decline");
            accept.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    primaryAdminRole.approveRequest(group, userDatabase.getUser(member));
                    accept.setVisible(false);
                    decline.setVisible(false);
                    frd.getStatusLabel().setVisible(true);
                    frd.getStatusLabel().setText("Accepted");
                }
            });
            decline.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    primaryAdminRole.declineRequest(group, userDatabase.getUser(member));
                    accept.setVisible(false);
                    decline.setVisible(false);
                    frd.getStatusLabel().setVisible(true);
                    frd.getStatusLabel().setText("declined");
                }
            });
        requests.setViewportView(mainMembers);
        requests.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        requests.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        }

    }
}
