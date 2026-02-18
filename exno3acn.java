server code
import java.net.*;
import java.io.*;

public class Server {

    public static final int PORT = 4000;

    public static void main(String[] args) {

        System.out.println("Starting Server...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Server Started: " + serverSocket);

            Socket socket = serverSocket.accept();
            System.out.println("Client Connected: " + socket);

            // Input from client
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            String messageFromClient = reader.readLine();
            System.out.println("Client says: " + messageFromClient);

            // Output to client
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("Hello from server");

            socket.close();

        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}



client program
import java.net.*;
import java.io.*;

public class Client {

    public static void main(String[] args) {

        System.out.println("Trying to connect to server...");

        try (Socket socket = new Socket(InetAddress.getLocalHost(), 4000)) {

            // Send message to server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("Hi from client");

            // Receive message from server
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            String response = reader.readLine();
            System.out.println("Server says: " + response);

        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
