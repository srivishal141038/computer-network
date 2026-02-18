import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Download {

    public static void main(String[] args) {

        String fileName = "digital_image_processing.jpg";
        String website = "https://tutorialspoint.com/java_dip/images/" + fileName;

        System.out.println("Downloading File From: " + website);

        try {
            URL url = new URL(website);

            // Try-with-resources (auto closes streams)
            try (InputStream inputStream = url.openStream();
                 OutputStream outputStream = new FileOutputStream(fileName)) {

                byte[] buffer = new byte[2048];
                int length;

                while ((length = inputStream.read(buffer)) != -1) {
                    System.out.println("Buffer Read of length: " + length);
                    outputStream.write(buffer, 0, length);
                }

                System.out.println("Download completed successfully!");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

