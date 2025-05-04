package main;
import java.io.*;
import java.net.*;

public class Server {
    private Socket socket = null;
    private DataInputStream clientInput = null;
    private DataInputStream terminalInput = null;
    private ServerSocket serverSocket = null;
    private DataOutputStream output = null;
    
    public Server(int port){
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = serverSocket.accept();
            System.out.println("Client accepted");
            
            // takes input from the client socket
            clientInput = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            output = new DataOutputStream(socket.getOutputStream());
            terminalInput = new DataInputStream(System.in);
            
            String clientLine = "";
            String serverLine = "";
            // reads message from client until "End" is sent
            while (!clientLine.equals("End")) {
                try {
                    serverLine = terminalInput.readLine();
                    output.writeUTF(serverLine);
                    clientLine = clientInput.readUTF();
                    System.out.println(clientLine);
                } catch (IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");
            // close connection
            socket.close();
            clientInput.close();
        }
        catch (IOException i) {
            System.out.println(i);
        }
    }
}
