import java.net.MalformedURLException;
import java.net.URL;

public class PageAdding {

    public static String urlparser(String pageUrl) {
        try {
            URL url = new URL(pageUrl);
            // Construct the base URL by getting the protocol, host
            String baseUrl = url.getProtocol() + "://" + url.getHost();
            return baseUrl + "/";
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e.getMessage());
            return null; // or return a default value
        }
    }

    public static String nameExtracter(String pageUrl) {
        try {
            URL url = new URL(pageUrl);
            String host =  url.getHost();

            if (host.startsWith("www.")) {
                host = host.substring(4);
            }
            // Remove ".com"
            int dotIndex = host.indexOf(".");
            if (dotIndex != -1) {
                host = host.substring(0, dotIndex);
            }
            return host;
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e.getMessage());
            return null;
        }
    }
}
