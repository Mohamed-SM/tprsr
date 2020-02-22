import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Client {
    static String serverName = "localhost";
    static int serverPort = 9999;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket(serverName, serverPort);
        System.out.println("Socket client : " + socket);

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());


        String choix = "";
        String msg;

        while (!choix.equals("E")) {
            System.out.println("A: ajouter\nM: modifier\nR: recherche\nE:quitter");
            choix = scanner.nextLine().trim().toUpperCase();
            switch (choix) {
                case "A":
                    out.writeObject(choix); // naba3to choix lel server
                    msg = (String) in.readObject();
                    System.out.println("msg de servuer : " + msg);


                    System.out.print("entre le nom : ");
                    String nom = scanner.nextLine().trim();
                    System.out.print("entre le prenom : ");
                    String prenom = scanner.nextLine().trim();
                    System.out.print("entre le tel");
                    String tel = scanner.nextLine().trim();
                    System.out.print("entre le nom : ");
                    String pass = scanner.nextLine().trim();


                    Person Person = new Person(nom, prenom, tel, pass); // person jdid


                    out.writeObject(Person); // naba3to person lel server

                    msg = (String) in.readObject(); // message de seccess
                    System.out.println("msg de servuer : " + msg);

                    break;
            }
        }


        in.close();
        out.close();
        socket.close();
    }
}
