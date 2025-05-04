package main;
/*TODO: when address cannot be resolved the program crashes because serverInput is null*/

// A Java program for a ClientSide
import java.io.*;
import java.net.*;

public class Client {

    // initialize socket and input output streams
    private Socket socket = null;
    private DataInputStream terminalInput = null;
    private DataOutputStream output = null;
    private DataInputStream serverInput = null;

    // constructor to put ip address and port
    public Client(String address, int port) {
        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            terminalInput = new DataInputStream(System.in);
            serverInput = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            output = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }

        // string to read message from input
        String serverLine = "";
        String line = "";
        // keep reading until "End" is input
        while (!line.equals("End")) {
            try {
                serverLine = serverInput.readUTF();
                System.out.println(serverLine);
                line = terminalInput.readLine();
                output.writeUTF(line);
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        // close the connection
        try {
            terminalInput.close();
            output.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }
}
