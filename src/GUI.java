import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GUI {
    // Define color attributes for light and dark modes
    private Color lightFrameColor = Color.WHITE;
    private Color darkFrameColor = new Color(33, 33, 33, 255);
    //ERU:colors changed slightly
    private Color label_forgotPassColor = new Color(51, 153, 255);

    JTextField textField_password;
    JTextField textField_user;

    public GUI() {
        MyFrame frame = new MyFrame();
        frame.getContentPane().setBackground(lightFrameColor);

        // Create the labels
        JLabel label_name = new JLabel("Welcome to Block Digital Service Manager");
        label_name.setFont(new Font("Serif", Font.BOLD, 24)); // Bold font for title
        label_name.setBorder(new EmptyBorder(new Insets(0,0,0,48)));
        label_name.setHorizontalAlignment(JLabel.CENTER);

        JLabel label_user = new JLabel("Username");
        label_user.setFont(new Font("Serif", Font.PLAIN, 16));
        JLabel label_password = new JLabel("Password");
        label_password.setFont(new Font("Serif", Font.PLAIN, 16));

        //ERU:changed formatting a little
        // Create the text fields
        textField_user = new JTextField(20); // Set to 20 columns
        textField_user.setBorder(new LineBorder(Color.BLACK, 1, true));
        textField_user.setMargin(new Insets(10,5,10,5));
        textField_user.setOpaque(false);
        textField_user.setPreferredSize(new Dimension(48,24));


        //ERU:changed a few things
        textField_password = new JTextField(20); // Set to 20 columns
        textField_password.setBorder(new LineBorder(Color.BLACK, 1, true));
        textField_password.setMargin(new Insets(10,5,10,5));
        textField_password.setOpaque(false);
        textField_password.setPreferredSize(new Dimension(48,24));



        // Create the login button
        //ERU:changed a few things
        JButton button_login = new JButton("Login");
        button_login.setBorder(BorderFactory.createCompoundBorder(
                button_login.getBorder(),
                BorderFactory.createLineBorder(Color.BLACK, 1, true)
        ));
        button_login.setMargin(new Insets(5, 25, 5, 25));
        button_login.setForeground(lightFrameColor);
        button_login.setBackground(darkFrameColor);
        button_login.setFont(new Font("Serif", Font.PLAIN, 14));
        button_login.setFocusable(false);
        button_login.setBorderPainted(false);
        button_login.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button_login.setBackground(new Color(37, 150, 190));
                button_login.setForeground(Color.BLACK);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button_login.setBackground(Color.BLACK);
                button_login.setForeground(Color.WHITE);
                authenticateUser();
            }
        });





        // Create links for forgot password and register
        JLabel label_forgotPass = new JLabel("Forgot your password? Recover");
        JLabel label_register = new JLabel("No account yet? Register");
        //ERU:had changed color a bit
        label_forgotPass.setForeground(label_forgotPassColor);
        label_register.setForeground(label_forgotPassColor);



        // Create a panel to hold the components using GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(lightFrameColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE; // Make the components fill horizontally
        gbc.anchor = GridBagConstraints.CENTER;



        // Add components to the panel
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.gridwidth = 2; // Span across 2 columns
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(label_name, gbc);


        gbc.gridy++;
        gbc.gridwidth = 1; // Reset to span only one column
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0,10,0,10);
        panel.add(label_user, gbc);


        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,10,5,10);
        panel.add(textField_user, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0,10,0,10);
        panel.add(label_password, gbc);


        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,10,15,10);
        panel.add(textField_password, gbc);


        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(button_login, gbc);


        gbc.gridy++;
        gbc.insets = new Insets(0,10,0,10);
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(label_forgotPass, gbc);


        gbc.gridy++;
        panel.add(label_register, gbc);

        // Center the panel in the frame
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true); // Make the frame visible
    }

    private void authenticateUser() {
        String username = textField_user.getText();
        String password = textField_password.getText();
        DbConnector dbConnector = new DbConnector();
        Connection connection = null;

        try {
            connection = dbConnector.connect("bdsm");
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);

                ResultSet set = statement.executeQuery();
                if (set.next()) {
                    JOptionPane.showMessageDialog(null, "Login is successful");
                    // SwingUtilities.getWindowAncestor(textField_user).dispose();
                    // new HomeScreen();
                } else {
                    SwingUtilities.getWindowAncestor(textField_user).dispose();
                    new HomeScreen();
                    // JOptionPane.showMessageDialog(null, "Either username or password is invalid");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error detected when trying to connect to the database" + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setLabelProperties(JLabel label, int fontSize, MyFrame frame) {
        label.setFont(new Font("Serif", Font.PLAIN, fontSize)); // Set the font style and size
        label.setForeground(frame.getContentPane().getBackground().equals(lightFrameColor) ? Color.BLACK : Color.WHITE); // Set font color based on frame background
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new); // Run the GUI in the Event Dispatch Thread
    }
}
