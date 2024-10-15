import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class LoginPage {
    private JTextField textField_user;
    private JTextField textField_password;
    private JLabel label_errorMessage;

    private LoginManager loginManager;

    public LoginPage(LoginManager loginManager) {
        this.loginManager = loginManager;

        // Create the frame and set its layout
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.WHITE);

        // Set minimum window size (600x400) and prevent resizing smaller than this
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setResizable(true); // Allow resizing but enforce minimum size

        // Create components
        JLabel label_user = new JLabel("Username:");
        JLabel label_password = new JLabel("Password:");
        textField_user = new JTextField(20);
        textField_password = new JPasswordField(20);

        JButton button_login = new JButton("Login");
        button_login.setBorder(BorderFactory.createCompoundBorder(
                button_login.getBorder(),
                BorderFactory.createLineBorder(Color.BLACK, 1, true)
        ));
        button_login.setMargin(new Insets(5, 25, 5, 25));
        button_login.setForeground(Color.WHITE);
        button_login.setBackground(Color.BLACK);

        // Add a label to display errors, initially invisible
        label_errorMessage = new JLabel();
        label_errorMessage.setForeground(Color.RED); // Set text color to red for errors
        label_errorMessage.setVisible(false); // Start as invisible

        // Add action for the login button
        button_login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                String username = textField_user.getText();
                String password = textField_password.getText();
                loginManager.authenticate(username, password);
            }
        });

        // Add components to the frame
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add the user label and text field
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(label_user, gbc);
        gbc.gridx = 1;
        frame.add(textField_user, gbc);

        // Add the password label and text field
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(label_password, gbc);
        gbc.gridx = 1;
        frame.add(textField_password, gbc);

        // Add the login button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        frame.add(button_login, gbc);

        // Add the error message label
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        frame.add(label_errorMessage, gbc); // Error message will appear here

        // Display the frame
        frame.pack();
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }

    // Method to show success or error messages
    public void showMessage(String message, boolean isError) {
        label_errorMessage.setText(message);
        label_errorMessage.setVisible(true);
        label_errorMessage.setForeground(isError ? Color.RED : Color.GREEN); // Set color based on success or error
    }
}
