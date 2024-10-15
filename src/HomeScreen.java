import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.*;

public class HomeScreen extends JFrame {

    JList<String> listServices;

    public HomeScreen() {
        setTitle("Home");
        setSize(1600, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());

        JPanel boxContainer = new JPanel();
        boxContainer.setLayout(new GridLayout(1, 3, 20, 20)); // Adjust spacing between boxes
        boxContainer.setOpaque(false);

        RoundedPanel boxServices = new RoundedPanel();
        RoundedPanel boxTime = new RoundedPanel();
        RoundedPanel boxEmergency = new RoundedPanel();

        // Set preferred size for the rounded panels to reduce their size
        boxServices.setMaximumSize(new Dimension(400, 600));
        boxTime.setMaximumSize(new Dimension(400, 600));
        boxEmergency.setMaximumSize(new Dimension(400, 600));

        // List of services to be displayed in the JList
        String[] services = {"facebook.com", "youtube.com", "pinterest.com", "twitter.com", "instagram.com", "tumblr.com"};
        listServices = new JList<>(services);
        listServices.setVisibleRowCount(6);  // Limit the visible rows for the list
        listServices.setFixedCellHeight(20); // Set fixed height for each list item

        // JLabel for services
        JLabel service_label = new JLabel("Service name");
        service_label.setFont(new Font("Serif", Font.BOLD, 16));
        service_label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JLabel timer_label = new JLabel("Timer (Numbers Only)");
        timer_label.setFont(new Font("Serif", Font.BOLD, 16));
        timer_label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JLabel emergency_label = new JLabel("Emergency access");
        emergency_label.setFont(new Font("Serif", Font.BOLD, 16));
        emergency_label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Add the label and the list to boxServices
        boxServices.add(service_label);
        boxServices.add(listServices);  // Add the list directly, without scroll pane

        // Add the labels to boxTime and boxEmergency
        boxTime.add(timer_label);
        boxEmergency.add(emergency_label);

        // Dynamically add a text field and button for each service to the boxTime and boxEmergency panels
        for (String service : services) {
            // Create a text field for the timer with a smaller size
            JTextField textFieldTime = createNumericTextField();
            textFieldTime.setPreferredSize(new Dimension(80, 20)); // Set width to 80 and height to 20
            boxTime.add(textFieldTime);  // Text field for the associated service in the list

            // Create a toggle button for each service in the emergency panel
            JToggleButton emergencyToggleButton = new JToggleButton("o");  // Label the button accordingly
            emergencyToggleButton.setPreferredSize(new Dimension(120, 30)); // Set a preferred size

            // Create a label to display Allowed or Not Allowed
            JLabel statusLabel = new JLabel("Not Allowed"); // Initial status
            statusLabel.setFont(new Font("Serif", Font.BOLD, 14));
            statusLabel.setForeground(Color.RED); // Set color to red for Not Allowed

            // Add action listener to update status when button is clicked
            emergencyToggleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Perform action when toggle button is clicked
                    if (emergencyToggleButton.isSelected()) {
                        System.out.println(service + " emergency activated!");
                        statusLabel.setText("Allowed");
                        statusLabel.setForeground(Color.GREEN); // Change color to green for Allowed
                    } else {
                        System.out.println(service + " emergency deactivated!");
                        statusLabel.setText("Not Allowed");
                        statusLabel.setForeground(Color.RED); // Change color back to red for Not Allowed
                    }
                }
            });

            // Create a panel for the toggle button and the label
            JPanel emergencyPanel = new JPanel();
            emergencyPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align components to the left
            emergencyPanel.add(emergencyToggleButton);
            emergencyPanel.add(statusLabel);
            boxEmergency.add(emergencyPanel);  // Add the emergency panel to the emergency box
        }

        // Set vertical layout for emergency buttons
        boxEmergency.setLayout(new BoxLayout(boxEmergency, BoxLayout.Y_AXIS));

        // Add an emergency toggle button (if needed)
        JToggleButton emergencySwitch = new JToggleButton("Toggle All Emergency");
        emergencySwitch.setPreferredSize(new Dimension(120, 30)); // Set a preferred size
        boxEmergency.add(emergencySwitch);

        // Add the three panels (Services, Time, Emergency) to the boxContainer
        boxContainer.add(boxServices);
        boxContainer.add(boxTime);
        boxContainer.add(boxEmergency);

        // GridBagConstraints for layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 20, 20, 20);

        // Add the boxContainer to the main panel
        panel.add(boxContainer, gbc);
        setContentPane(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JTextField createTextField(RoundedPanel boxServices, RoundedPanel boxTime, RoundedPanel boxEmergency) {
        JTextField textField = new JTextField(24);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check for existing empty text fields
                boolean hasEmptyField = false;

                for (Component component : boxServices.getComponents()) {
                    if (component instanceof JTextField && component.isVisible() && ((JTextField) component).getText().isEmpty()) {
                        hasEmptyField = true;
                        break;
                    }
                }

                // Create new fields only if no empty fields exist
                if (!hasEmptyField) {
                    // Create a new text field for Services
                    JTextField newTextFieldServices = createTextField(boxServices, boxTime, boxEmergency);
                    boxServices.add(newTextFieldServices);
                    boxServices.revalidate();
                    boxServices.repaint();

                    // Create a new editable text field for the timer
                    JTextField newTextFieldTime = createNumericTextField();
                    newTextFieldTime.setPreferredSize(new Dimension(80, 20)); // Set width to 80 and height to 20
                    boxTime.add(newTextFieldTime);
                    boxTime.revalidate();
                    boxTime.repaint();

                    // Create a new emergency toggle button
                    JToggleButton newEmergencyToggleButton = new JToggleButton("Emergency: New Service");
                    newEmergencyToggleButton.setPreferredSize(new Dimension(120, 30)); // Set a preferred size
                    boxEmergency.add(newEmergencyToggleButton);
                    boxEmergency.revalidate();
                    boxEmergency.repaint();

                    // Focus on the newly created service text field
                    newTextFieldServices.requestFocusInWindow();
                } else {
                    // If there's an empty field, focus on it
                    for (Component component : boxServices.getComponents()) {
                        if (component instanceof JTextField && ((JTextField) component).getText().isEmpty()) {
                            component.requestFocusInWindow();
                            break;
                        }
                    }
                }
            }
        });
        return textField;
    }

    private JTextField createNumericTextField() {
        JTextField numericTextField = new JTextField(24);

        ((AbstractDocument) numericTextField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string == null) return;
                if (string.matches("\\d*")) { // Only digits
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text == null) return;
                if (text.matches("\\d*")) { // Only digits
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                super.remove(fb, offset, length);
            }
        });

        return numericTextField;
    }

    class RoundedPanel extends JPanel {
        private int cornerRound = 18;

        public RoundedPanel() {
            setOpaque(false);
            setBackground(Color.LIGHT_GRAY);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2D = (Graphics2D) g.create();

            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setColor(getBackground());
            g2D.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRound, cornerRound);
            g2D.dispose();
        }
    }

    class PrettyButton extends JButton {
        // Empty class for custom button styles if needed
    }

    public static void main(String[] args) {
        new HomeScreen();
    }
}
