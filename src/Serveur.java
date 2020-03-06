import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Serveur {
    static int port = 9999;
    static ArrayList<Person> persons = new ArrayList<>();
    static int number = 0;


    public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(port);
        System.out.println("Socket serveur : " + s);

        persons.add(new Person("Hiba", "Moumne", "066666666", "pass1"));
        persons.add(new Person("Chimaa", "Moumne", "066666666", "pass1"));
        persons.add(new Person("Iman", "Moumne", "066666666", "pass1"));
        persons.add(new Person("Nor", "Moumne", "066666666", "pass1"));


        while (true) {
            Socket soc = s.accept();
            number++;
            System.out.println("Serveur accept connextion : " + soc);

            ServerThread1 serverThread = new ServerThread1(soc, number);
            serverThread.start();
        }

        //System.out.println("serveur fermer!!");
    }


}
