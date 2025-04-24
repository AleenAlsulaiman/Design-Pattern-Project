package StudentManagement;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

// === Decorator Pattern Interfaces and Classes ===
interface TextField {
    String getText();
}

class BasicTextField implements TextField {
    private JTextField textField;

    public BasicTextField(JTextField textField) {
        this.textField = textField;
    }

    public String getText() {
        return textField.getText();
    }
}

class ValidationDecorator implements TextField {
    private TextField decorated;

    public ValidationDecorator(TextField decorated) {
        this.decorated = decorated;
    }

    public String getText() {
        String text = decorated.getText();
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Field cannot be empty");
        }
        return text;
    }
}

// === Student JFrame ===
public class Student extends JFrame {

    private JPanel contentPane;
    private JTextField sname;
    private JTextField sentry;
    private JTextField semail;
    private JTextField scontact;
    private JTextField shome;

    Connection con = null;
    PreparedStatement pst = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Student frame = new Student();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Student() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 588, 620);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel studentDetails = new JLabel("Student Details");
        studentDetails.setFont(new Font("Perpetua Titling MT", Font.BOLD, 22));

        JLabel studentName = new JLabel("Student Name");
        JLabel entryNumber = new JLabel("Entry Number");
        JLabel emailAddress = new JLabel("Email Address");
        JLabel contactNumber = new JLabel("Contact Number");
        JLabel homeCity = new JLabel("Home City");

        sname = new JTextField();
        sentry = new JTextField();
        semail = new JTextField();
        scontact = new JTextField();
        shome = new JTextField();

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Decorate with validation
                    TextField nameField = new ValidationDecorator(new BasicTextField(sname));
                    TextField entryField = new ValidationDecorator(new BasicTextField(sentry));
                    TextField emailField = new ValidationDecorator(new BasicTextField(semail));
                    TextField contactField = new ValidationDecorator(new BasicTextField(scontact));
                    TextField homeField = new ValidationDecorator(new BasicTextField(shome));

                    String query = "INSERT INTO `student`(`name`, `entrynumber`, `email`, `contactnumber`, `homecity`) VALUES (?, ?, ?, ?, ?)";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagementsystem", "root", "");
                    pst = con.prepareStatement(query);
                    pst.setString(1, nameField.getText());
                    pst.setString(2, entryField.getText());
                    pst.setString(3, emailField.getText());
                    pst.setString(4, contactField.getText());
                    pst.setString(5, homeField.getText());

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Student added Successfully :)");
                    dispose();
                    Menu menu = new Menu();
                    menu.show();

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> {
            Menu menu = new Menu();
            menu.show();
            dispose();
        });

        GroupLayout gl = new GroupLayout(contentPane);
        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createParallelGroup()
            .addComponent(studentDetails)
            .addGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup()
                    .addComponent(studentName)
                    .addComponent(entryNumber)
                    .addComponent(emailAddress)
                    .addComponent(contactNumber)
                    .addComponent(homeCity))
                .addGroup(gl.createParallelGroup()
                    .addComponent(sname)
                    .addComponent(sentry)
                    .addComponent(semail)
                    .addComponent(scontact)
                    .addComponent(shome)))
            .addComponent(submit)
            .addComponent(cancel)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
            .addComponent(studentDetails)
            .addGroup(gl.createParallelGroup()
                .addComponent(studentName)
                .addComponent(sname))
            .addGroup(gl.createParallelGroup()
                .addComponent(entryNumber)
                .addComponent(sentry))
            .addGroup(gl.createParallelGroup()
                .addComponent(emailAddress)
                .addComponent(semail))
            .addGroup(gl.createParallelGroup()
                .addComponent(contactNumber)
                .addComponent(scontact))
            .addGroup(gl.createParallelGroup()
                .addComponent(homeCity)
                .addComponent(shome))
            .addComponent(submit)
            .addComponent(cancel)
        );

        contentPane.setLayout(gl);
    }
}
