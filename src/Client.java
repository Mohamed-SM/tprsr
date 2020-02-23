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

        String nom;
        String prenom;
        String tel;
        String pass;

        Person Person;

        while (!choix.equals("Q")) {
            System.out.print("A: ajouter\nM: modifier\nR: recherche\nQ: quitter\nvoitre choix : ");
            choix = scanner.nextLine().trim().toUpperCase();
            out.writeObject(choix); // naba3to choix lel server

            msg = (String) in.readObject();
            System.out.println("msg de servuer : " + msg);

            switch (choix) {
                case "A":

                    System.out.print("entre le nom : ");
                    nom = scanner.nextLine().trim();
                    System.out.print("entre le prenom : ");
                    prenom = scanner.nextLine().trim();
                    System.out.print("entre le tel : ");
                    tel = scanner.nextLine().trim();
                    System.out.print("entre le pass : ");
                    pass = scanner.nextLine().trim();


                    // person jdid
                    Person = new Person(nom, prenom, tel, pass);


                    out.writeObject(Person); // naba3to person lel server

                    msg = (String) in.readObject(); // message de seccess
                    System.out.println("msg de servuer : " + msg);

                    break;

                case "R":
                    System.out.print("taper le nom que vous cherch : ");
                    String name = scanner.nextLine().trim();

                    out.writeObject(name);

                    msg = (String) in.readObject();
                    System.out.println("msg de servuer : " + msg);

                    break;

                case "M":
                    int max = (int) in.readObject();
                    if (max != 0) {
                        int index = -1;

                        while (index < 0 || index >= max) {
                            System.out.print("Entre le index (entre 0 et " + (max - 1) + " inclus) : ");
                            index = scanner.nextInt();
                        }

                        out.writeObject(index);

                        msg = (String) in.readObject();
                        System.out.println("msg de servuer : " + msg);
                        scanner.nextLine();


                        System.out.print("entre le nom : ");
                        nom = scanner.nextLine().trim();
                        System.out.print("entre le prenom : ");
                        prenom = scanner.nextLine().trim();
                        System.out.print("entre le tel : ");
                        tel = scanner.nextLine().trim();
                        System.out.print("entre le pass : ");
                        pass = scanner.nextLine().trim();

                        //modification de person
                        Person = new Person(nom, prenom, tel, pass);


                        out.writeObject(Person); // naba3to person lel server

                        msg = (String) in.readObject(); // message de seccess
                        System.out.println("msg de servuer : " + msg);

                    }
                    break;
            }
        }


        in.close();
        out.close();
        socket.close();

        System.out.println("client fermer!!");
    }
}
