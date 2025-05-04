package main;
import main.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(Integer.parseInt(args[0]));
    }
}
