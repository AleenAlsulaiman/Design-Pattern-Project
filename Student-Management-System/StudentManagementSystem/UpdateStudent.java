//  معدل: تم تطبيق Strategy Pattern لتقليل التكرار في ActionListener
package StudentManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

public class UpdateStudent extends JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs;

    private JPanel contentPane;
    private JTextField updateEntry;
    private JTextField nameU;
    private JTextField entryU;
    private JTextField emailU;
    private JTextField contactU;
    private JTextField homeU;

    //   
    interface ButtonActionStrategy {
        void execute();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateStudent frame = new UpdateStudent();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public UpdateStudent() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 676, 656);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.GRAY);

        nameU = new JTextField();
        nameU.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Student Name");
        lblNewLabel_1.setForeground(Color.BLACK);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel lblNewLabel_1_1 = new JLabel("Entry Number");
        lblNewLabel_1_1.setForeground(Color.BLACK);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel lblNewLabel_1_2 = new JLabel("Email Address");
        lblNewLabel_1_2.setForeground(Color.BLACK);
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel lblNewLabel_1_3 = new JLabel("Contact Number");
        lblNewLabel_1_3.setForeground(Color.BLACK);
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel lblNewLabel_1_4 = new JLabel("Home City");
        lblNewLabel_1_4.setForeground(Color.BLACK);
        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 16));

        entryU = new JTextField();
        entryU.setColumns(10);

        emailU = new JTextField();
        emailU.setColumns(10);

        contactU = new JTextField();
        contactU.setColumns(10);

        homeU = new JTextField();
        homeU.setColumns(10);

        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String query = "UPDATE `student` SET name=?, entrynumber=?, email=?, contactnumber=?, homecity=? WHERE entrynumber=?";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagementsystem", "root", "");
                    pst = con.prepareStatement(query);

                    String pid = updateEntry.getText();
                    pst.setString(1, nameU.getText());
                    pst.setString(2, entryU.getText());
                    pst.setString(3, emailU.getText());
                    pst.setString(4, contactU.getText());
                    pst.setString(5, homeU.getText());
                    pst.setString(6, pid);
                    if (nameU.getText().equals("") || entryU.getText().equals("") || emailU.getText().equals("") || contactU.getText().equals("") || homeU.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Fill all the details :(");
                    } else {
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Updated Successfully :)");
                        dispose();
                        Menu menu = new Menu();
                        menu.show();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        updateBtn.setForeground(Color.BLACK);
        updateBtn.setFont(new Font("Tahoma", Font.BOLD, 14));

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 647, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(127)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNewLabel_1)
                        .addComponent(lblNewLabel_1_1)
                        .addComponent(lblNewLabel_1_2)
                        .addComponent(lblNewLabel_1_3)
                        .addComponent(lblNewLabel_1_4))
                    .addGap(18)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(entryU, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameU, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailU, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                        .addComponent(contactU, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                        .addComponent(homeU, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(114, Short.MAX_VALUE))
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(261)
                    .addComponent(updateBtn, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(261, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
                    .addGap(27)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addComponent(lblNewLabel_1)
                        .addComponent(nameU, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                    .addGap(27)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addComponent(lblNewLabel_1_1)
                        .addComponent(entryU, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                    .addGap(26)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addComponent(lblNewLabel_1_2)
                        .addComponent(emailU, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                    .addGap(29)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addComponent(lblNewLabel_1_3)
                        .addComponent(contactU, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                    .addGap(27)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addComponent(homeU, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel_1_4))
                    .addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                    .addComponent(updateBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );

        updateEntry = new JTextField();
        updateEntry.setBounds(190, 100, 237, 33);
        desktopPane.add(updateEntry);
        updateEntry.setColumns(10);

        JButton btnNewButton = new JButton("Search");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.setBounds(334, 164, 149, 33);
        desktopPane.add(btnNewButton);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setForeground(Color.BLACK);
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCancel.setBounds(143, 164, 149, 33);
        desktopPane.add(btnCancel);

        JLabel lblNewLabel = new JLabel("Search the \"Entry Number\"");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(180, 56, 283, 33);
        desktopPane.add(lblNewLabel);

        //    
        ButtonActionStrategy cancelAction = () -> {
            Menu menu = new Menu();
            menu.show();
            dispose();
        };
        btnCancel.addActionListener(e -> cancelAction.execute());

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = updateEntry.getText();
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagementsystem", "root", "");
                    pst = con.prepareStatement("SELECT * FROM student where entrynumber = ?");
                    pst.setString(1, str);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        nameU.setText(rs.getString(1));
                        entryU.setText(rs.getString(2));
                        emailU.setText(rs.getString(3));
                        contactU.setText(rs.getString(4));
                        homeU.setText(rs.getString(5));
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        contentPane.setLayout(gl_contentPane);
    }
}
