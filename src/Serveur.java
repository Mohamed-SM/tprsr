import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Serveur {
    static int port = 9999;
    public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(port);
        System.out.println("\u001B[31mServer: \u001B[0mSocket serveur : " + s);
        Socket soc = s.accept();
        System.out.println("\u001B[31mServer: \u001B[0mServeur accept connextion : " + soc);

        ObjectOutputStream O = new ObjectOutputStream(soc.getOutputStream());
        ObjectInputStream I  = new ObjectInputStream(soc.getInputStream());


        //O.writeObject(personRecu);

        //Fermeture des flux et du socket de service


        String choix = "";

        while (!choix.equals("E")) {
            choix = (String) I.readObject();
            System.out.println("choix : " + choix);
            switch (choix) {
                case "A":
                    O.writeObject(new String("Serveur pri pour ajoute un person : "));
                    Person personRecu = (Person) I.readObject();
                    System.out.println("Serveur recoit : " + personRecu);
                    O.writeObject(new String("Serveur a resux un persone avex success : " + personRecu));
                    break;
            }
        }


        I.close();
        O.close();
        soc.close();
    }


}
