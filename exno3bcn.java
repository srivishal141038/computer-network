TCPCHAT SERVER

import java.io.*;
import java.net.*;

public class TcpChatServer {

    public static void main(String args[]) {

        PrintWriter toClient;
        BufferedReader fromUser, fromClient;

        try {
            ServerSocket srv = new ServerSocket(4000);
            System.out.println("\nServer started");

            Socket clt = srv.accept();
            System.out.println("Client connected");

            toClient = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(clt.getOutputStream())), true);

            fromClient = new BufferedReader(
                    new InputStreamReader(clt.getInputStream()));

            fromUser = new BufferedReader(
                    new InputStreamReader(System.in));

            String cltMsg, srvMsg;

            while (true) {
                cltMsg = fromClient.readLine();

                if (cltMsg == null || cltMsg.equals("end"))
                    break;

                System.out.println("Client: " + cltMsg);

                System.out.print("Message to Client: ");
                srvMsg = fromUser.readLine();
                toClient.println(srvMsg);
            }

            System.out.println("\nClient Disconnected");

            fromClient.close();
            toClient.close();
            fromUser.close();
            clt.close();
            srv.close();

        } catch (Exception e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}


TCPCHAT CLIENT


import java.io.*;
import java.net.*;

public class TcpChatClient {

    public static void main(String args[]) {

        Socket clt;
        PrintWriter toServer;
        BufferedReader fromUser, fromServer;

        try {
            clt = new Socket(InetAddress.getLocalHost(), 4000);

            toServer = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(clt.getOutputStream())), true);

            fromServer = new BufferedReader(
                    new InputStreamReader(clt.getInputStream()));

            fromUser = new BufferedReader(
                    new InputStreamReader(System.in));

            String cltMsg, srvMsg;

            System.out.println("Type \"end\" to Quit");

            while (true) {
                System.out.print("Message to Server: ");
                cltMsg = fromUser.readLine();

                toServer.println(cltMsg);

                if (cltMsg.equals("end"))
                    break;

                srvMsg = fromServer.readLine();
                System.out.println("Server: " + srvMsg);
            }

            fromServer.close();
            toServer.close();
            fromUser.close();
            clt.close();

        } catch (Exception e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
