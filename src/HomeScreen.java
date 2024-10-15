import java.awt.*;
import javax.swing.*;

// Class representing a Service with a limit and emergency status
class Service {
    String name;
    int limit; // Limit for the service
    boolean emergencyStatus; // Emergency status

    public Service(String name, int limit, boolean emergencyStatus) {
        this.name = name;
        this.limit = limit;
        this.emergencyStatus = emergencyStatus;
    }

    public String getName() {
        return name;
    }

    public int getLimit() {
        return limit;
    }

    public boolean isEmergencyStatus() {
        return emergencyStatus;
    }
}

public class HomeScreen extends JFrame {
    private DefaultListModel<Service> serviceListModel; // Model for containing services

    public HomeScreen() {
        setTitle("Home");
        setMaximumSize(new Dimension(1600, 1200));
        setMinimumSize(new Dimension(400, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Initialize the service list model
        serviceListModel = new DefaultListModel<>();

        // Sample data
        String[] serviceNames = {"facebook.com", "youtube.com", "pinterest.com", "twitter.com", "instagram.com", "tumblr.com"};
        // Limits
        int[] limits = {30, 60, 45, 90, 120, 75};
        // Initial emergency statuses
        boolean[] emergencyStatuses = {false, true, false, true, false, true};

        // Populate the service list model
        for (int i = 0; i < serviceNames.length; i++) {
            serviceListModel.addElement(new Service(serviceNames[i], limits[i], emergencyStatuses[i]));
        }

        // JLabel for the services
        JLabel service_label = new JLabel("Service Management");
        service_label.setFont(new Font("Serif", Font.BOLD, 20));
        service_label.setHorizontalAlignment(SwingConstants.CENTER);
        service_label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add the title label to the GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.insets = new Insets(20, 20, 10, 20); // Top, left, bottom, right
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(service_label, gbc); // Add title label

        // Panels to hold each column of data
        JPanel namePanel = new JPanel();
        JPanel limitPanel = new JPanel();
        JPanel emergencyPanel = new JPanel();

        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        limitPanel.setLayout(new BoxLayout(limitPanel, BoxLayout.Y_AXIS));
        emergencyPanel.setLayout(new BoxLayout(emergencyPanel, BoxLayout.Y_AXIS));

        // Add data to each panel
        for (int i = 0; i < serviceListModel.size(); i++) {
            Service service = serviceListModel.get(i);
            // Create labels with the specified font size
            JLabel nameLabel = new JLabel(service.getName());
            JLabel limitLabel = new JLabel(service.getLimit() + " min");
            JLabel emergencyLabel = new JLabel(service.isEmergencyStatus() ? "Allowed" : "Not Allowed");

            nameLabel.setFont(new Font("Serif", Font.PLAIN, 16));
            limitLabel.setFont(new Font("Serif", Font.PLAIN, 16));
            emergencyLabel.setFont(new Font("Serif", Font.PLAIN, 16));

            // Add labels to the respective panels
            namePanel.add(nameLabel);
            limitPanel.add(limitLabel);
            emergencyPanel.add(emergencyLabel);
        }

        // Panel to hold the three columns together
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for mainPanel

        // Add columns to the mainPanel
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10); // Spacing between columns
        gbc.fill = GridBagConstraints.VERTICAL;

        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0 for namePanel
        mainPanel.add(namePanel, gbc);

        gbc.gridx = 1; // Column 1 for limitPanel
        mainPanel.add(limitPanel, gbc);

        gbc.gridx = 2; // Column 2 for emergencyPanel
        mainPanel.add(emergencyPanel, gbc);

        // Add the main panel below the title in the GridBagLayout
        gbc = new GridBagConstraints();
        gbc.gridx = 0; // Column 0
        gbc.gridy = 1; // Row 1, below the title
        gbc.insets = new Insets(10, 20, 20, 20); // Adjusted spacing
        gbc.fill = GridBagConstraints.BOTH; // Allow the panel to fill space
        add(mainPanel, gbc);

        // Final setup for the main frame
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomeScreen());
    }
}
