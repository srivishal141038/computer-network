DNS CLIENT

import java.io.*;
import java.net.*;

public class DnsClient {

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress ipAddress;

        if (args.length == 0)
            ipAddress = InetAddress.getLocalHost();
        else
            ipAddress = InetAddress.getByName(args[0]);

        byte[] sendData;
        byte[] receiveData = new byte[1024];

        int portAddr = 8080;

        System.out.print("Enter the hostname: ");
        String sentence = br.readLine();

        sendData = sentence.trim().getBytes();

        DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length, ipAddress, portAddr);

        clientSocket.send(sendPacket);

        DatagramPacket receivePacket =
                new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);

        String modified =
                new String(receivePacket.getData(), 0, receivePacket.getLength());

        System.out.println("IP Address: " + modified);

        clientSocket.close();
    }
}

DNS SERVER

import java.io.*;
import java.net.*;

public class DnsServer {

    private static int indexOf(String[] array, String str) {
        str = str.trim();
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(str))
                return i;
        }
        return -1;
    }

    public static void main(String arg[]) throws IOException {

        String[] hosts = {
                "yahoo.com",
                "gmail.com",
                "cricinfo.com",
                "facebook.com"
        };

        String[] ip = {
                "68.180.206.184",
                "209.85.148.19",
                "80.168.92.140",
                "69.63.189.16"
        };

        System.out.println("DNS Server Started (Press Ctrl + C to Quit)");

        DatagramSocket serverSocket = new DatagramSocket(8080);

        while (true) {

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);

            serverSocket.receive(receivePacket);

            String sen =
                    new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();

            InetAddress ipAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            System.out.println("Request for host: " + sen);

            String capsent;

            int index = indexOf(hosts, sen);

            if (index != -1)
                capsent = ip[index];
            else
                capsent = "Host Not Found";

            sendData = capsent.getBytes();

            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, ipAddress, port);

            serverSocket.send(sendPacket);
        }
    }
}
