import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
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
        ObjectInputStream I = new ObjectInputStream(soc.getInputStream());

        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("Hiba", "Moumne", "066666666", "pass1"));
        persons.add(new Person("Chimaa", "Moumne", "066666666", "pass1"));
        persons.add(new Person("Iman", "Moumne", "066666666", "pass1"));
        persons.add(new Person("Nor", "Moumne", "066666666", "pass1"));

        String choix = "";

        while (!choix.equals("Q")) {
            choix = (String) I.readObject(); //nesta9blo choix men 3and client
            System.out.println("choix de client : " + choix);
            switch (choix) {
                case "A":
                    O.writeObject("Serveur pri pour ajoute un person : ");

                    Person personRecu = (Person) I.readObject(); // nesta9blo person jdid
                    System.out.println("Serveur recoit : " + personRecu);

                    persons.add(personRecu);

                    O.writeObject("Serveur a resu un persone avec success : " + personRecu);
                    break;


                case "R":
                    O.writeObject("Serveur pri pour recher : ");
                    String nom = (String) I.readObject(); // nesta9blo nome de rechech
                    int i;

                    for (i = 0; i < persons.size(); i++) {
                        Person person = persons.get(i);
                        if (person.getNome().equals(nom)) break;

                    }

                    if (i < persons.size()) O.writeObject("Person trouver: index: " + i + "  " + persons.get(i));
                    else O.writeObject("Person non trouver");
                    break;

                case "M":
                    O.writeObject("Serveur pri pour modification : ");
                    O.writeObject(persons.size());

                    int index = (int) I.readObject(); // nesta9blo nome de rechech

                    O.writeObject("Person a modifer : " + persons.get(index));

                    Person person = (Person) I.readObject(); // nesta9blo person jdid
                    System.out.println("Serveur recoit : " + person);

                    persons.set(index, person);

                    O.writeObject("Serveur a modifee un persone avec success : index: " + index + "  " + person);

                    break;

                case "Q":
                    O.writeObject("Serveur fermer!!");
                    break;

            }
        }


        I.close();
        O.close();
        soc.close();

        System.out.println("serveur fermer!!");
    }


}
