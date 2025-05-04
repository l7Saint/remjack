package main;
import main.Client;

public class Main {
    public static void main(String[] args) { /* args[0] == <ip>, args[1] = <port> */
        Client client = new Client(args[0], Integer.parseInt(args[1]));
    }  
}
