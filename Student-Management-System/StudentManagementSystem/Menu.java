package StudentManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;

public class Menu extends JFrame {

    private JPanel contentPane;

    // === Bridge Pattern Interfaces & Classes ===
    abstract class MenuAction {
        protected ActionImplementor implementor;

        public MenuAction(ActionImplementor implementor) {
            this.implementor = implementor;
        }

        public abstract void perform();
    }

    interface ActionImplementor {
        void execute();
    }

    class AddStudentImpl implements ActionImplementor {
        public void execute() {
            new Student().show();
        }
    }

    class RemoveStudentImpl implements ActionImplementor {
        public void execute() {
            new RemoveStudent().show();
        }
    }

    class ViewStudentImpl implements ActionImplementor {
        public void execute() {
            new ViewStudent().show();
        }
    }

    class UpdateStudentImpl implements ActionImplementor {
        public void execute() {
            new UpdateStudent().show();
        }
    }

    class LogoutImpl implements ActionImplementor {
        public void execute() {
            new Login().show();
            JOptionPane.showMessageDialog(null, "Successfully logged out :)");
        }
    }

    class StandardAction extends MenuAction {
        public StandardAction(ActionImplementor implementor) {
            super(implementor);
        }

        public void perform() {
            implementor.execute();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Menu frame = new Menu();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Menu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 623);
        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.LIGHT_GRAY);

        JDesktopPane desktopPane_1 = new JDesktopPane();
        desktopPane_1.setBackground(Color.LIGHT_GRAY);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(desktopPane_1, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addGap(36)
                            .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(desktopPane_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                    .addGap(26)
                    .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(37, Short.MAX_VALUE))
        );

        // === New Bridge-based commands for each button ===
        MenuAction addAction = new StandardAction(new AddStudentImpl());
        MenuAction removeAction = new StandardAction(new RemoveStudentImpl());
        MenuAction viewAction = new StandardAction(new ViewStudentImpl());
        MenuAction updateAction = new StandardAction(new UpdateStudentImpl());
        MenuAction logoutAction = new StandardAction(new LogoutImpl());

        JButton btnNewButton = new JButton("Add Student");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton.addActionListener(e -> {
            addAction.perform();
            dispose();
        });
        btnNewButton.setBounds(32, 37, 287, 47);
        desktopPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Remove Existing Student");
        btnNewButton_1.setForeground(Color.BLACK);
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_1.addActionListener(e -> {
            removeAction.perform();
            dispose();
        });
        btnNewButton_1.setBounds(32, 113, 287, 52);
        desktopPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("View Students");
        btnNewButton_2.setForeground(Color.BLACK);
        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_2.addActionListener(e -> {
            viewAction.perform();
            dispose();
        });
        btnNewButton_2.setBounds(32, 195, 287, 52);
        desktopPane.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("Update Existing Student");
        btnNewButton_3.setForeground(Color.BLACK);
        btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_3.addActionListener(e -> {
            updateAction.perform();
            dispose();
        });
        btnNewButton_3.setBounds(32, 272, 287, 52);
        desktopPane.add(btnNewButton_3);

        JButton btnNewButton_4 = new JButton("Logout");
        btnNewButton_4.setForeground(Color.BLACK);
        btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_4.addActionListener(e -> {
            logoutAction.perform();
            dispose();
        });
        btnNewButton_4.setBounds(32, 348, 287, 47);
        desktopPane.add(btnNewButton_4);

        JLabel lblNewLabel = new JLabel("What do you want ?");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblNewLabel.setBounds(93, 17, 220, 27);
        desktopPane_1.add(lblNewLabel);

        contentPane.setLayout(gl_contentPane);
    }
}

