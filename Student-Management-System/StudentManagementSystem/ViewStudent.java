package StudentManagement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewStudent extends JFrame {

    // 1. Define the Abstract Factory Interface
    public interface StudentViewFactory {
        JPanel createContentPane();
        JDesktopPane createDesktopPane();
        JLabel createTitleLabel();
        JButton createBackButton();
    }

    // 2. Default Factory Implementation (matches original behavior)
    private static class DefaultStudentViewFactory implements StudentViewFactory {
        @Override
        public JPanel createContentPane() {
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(5, 5, 5, 5));
            return panel;
        }

        @Override
        public JDesktopPane createDesktopPane() {
            JDesktopPane pane = new JDesktopPane();
            pane.setBackground(Color.GRAY);
            return pane;
        }

        @Override
        public JLabel createTitleLabel() {
            JLabel label = new JLabel("Student Details");
            label.setForeground(Color.BLACK);
            label.setFont(new Font("Tahoma", Font.BOLD, 28));
            return label;
        }

        @Override
        public JButton createBackButton() {
            JButton button = new JButton("Go Back");
            button.setForeground(Color.BLACK);
            button.setFont(new Font("Tahoma", Font.BOLD, 14));
            button.addActionListener(e -> {
                new Menu().setVisible(true);
                dispose();
            });
            return button;
        }
    }

    // 3. Refactored ViewStudent Class
    private final StudentViewFactory factory;

    public ViewStudent() {
        this(new DefaultStudentViewFactory()); // Use default factory
    }

    public ViewStudent(StudentViewFactory factory) {
        this.factory = factory;
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 782, 611);

        // Create components using factory
        JPanel contentPane = factory.createContentPane();
        JDesktopPane desktopPane = factory.createDesktopPane();
        JLabel titleLabel = factory.createTitleLabel();
        JButton backButton = factory.createBackButton();

        // Position components (matches original layout)
        titleLabel.setBounds(255, 27, 225, 52);
        desktopPane.add(titleLabel);
        backButton.setBounds(10, 96, 113, 32);
        desktopPane.add(backButton);

        // Set layout (matches original GroupLayout behavior)
        contentPane.setLayout(new BorderLayout());
        contentPane.add(desktopPane, BorderLayout.NORTH);
        setContentPane(contentPane);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new ViewStudent().setVisible(true); // Still works the same way
        });
    }
}
