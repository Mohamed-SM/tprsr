import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Serveur {
    static int port = 9999;
    public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(port);
        System.out.println("Socket serveur : " + s);
        Socket soc = s.accept();
        System.out.println("Serveur accept connextion : " + soc);

        ObjectOutputStream O = new ObjectOutputStream(soc.getOutputStream());
        ObjectInputStream I  = new ObjectInputStream(soc.getInputStream());

        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("Hiba", "Moumne", "066666666", "pass1"));
        persons.add(new Person("Chimaa", "Moumne", "066666666", "pass1"));
        persons.add(new Person("Iman", "Moumne", "066666666", "pass1"));
        persons.add(new Person("Nor", "Moumne", "066666666", "pass1"));

        String choix = "";

        while (!choix.equals("E")) {
            choix = (String) I.readObject(); //nesta9blo choix men 3and client
            System.out.println("choix de client : " + choix);
            switch (choix) {
                case "A":
                    O.writeObject("Serveur pri pour ajoute un person : ");

                    Person personRecu = (Person) I.readObject(); // nesta9blo person jdid
                    System.out.println("Serveur recoit : " + personRecu);

                    persons.add(personRecu);

                    O.writeObject("Serveur a resux un persone avex success : " + personRecu);
                    break;
                case "O":
                    O.writeObject("Serveur pri pour recher : ");
            }
        }


        I.close();
        O.close();
        soc.close();
    }


}
