import java.io.*;
import java.net.Socket;

class ServerThread1 extends Thread{
    private Socket soc;
    private int number;

    ServerThread1(Socket soc, int number) {
        this.soc = soc;
        this.number = number;
    }

    @Override
    public void run() {
        try {

            ObjectOutputStream O = new ObjectOutputStream(soc.getOutputStream());
            ObjectInputStream I = new ObjectInputStream(soc.getInputStream());

            String choix = "";

            while (!choix.equals("Q")) {
                choix = (String) I.readObject(); //nesta9blo choix men 3and client
                System.out.println("choix de client " + number + "  : " + choix);
                switch (choix) {
                    case "A":
                        O.writeObject("Serveur pri pour ajoute un person : ");

                        Person personRecu = (Person) I.readObject(); // nesta9blo person jdid
                        System.out.println("Serveur recoit  " + number + "  : " + personRecu);

                        Serveur.persons.add(personRecu);

                        O.writeObject("Serveur a resu un persone avec success : " + personRecu);
                        break;


                    case "R":
                        O.writeObject("Serveur pri pour recher : ");
                        String nom = (String) I.readObject(); // nesta9blo nome de rechech
                        int i;

                        for (i = 0; i < Serveur.persons.size(); i++) {
                            Person person = Serveur.persons.get(i);
                            if (person.getNome().equals(nom)) break;

                        }

                        if (i < Serveur.persons.size()) O.writeObject("Person trouver: index: " + i + "  " + Serveur.persons.get(i));
                        else O.writeObject("Person non trouver");
                        break;

                    case "M":
                        O.writeObject("Serveur pri pour modification : ");
                        O.writeObject(Serveur.persons.size());

                        nom = (String) I.readObject();
                        String pass = (String) I.readObject();

                        System.out.println("client  " + number + " modifier :  " + nom + " " + pass);

                        Person person = null;
                        int j;
                        for (j = 0; j < Serveur.persons.size(); j++) {
                            Person p = Serveur.persons.get(j);
                            if (p.getNome().equals(nom) && p.getPass().equals(pass)) {
                                person = p;
                                break;
                            }
                        }

                        if (person != null) {
                            O.writeObject("Person a modifer : " + person);
                        } else {
                            O.writeObject("Aucun personn a modifier");
                            break;
                        }

                        person = (Person) I.readObject(); // nesta9blo person jdid
                        System.out.println( "client  " + number + " Serveur recoit : " + person);

                        Serveur.persons.set(j, person);

                        O.writeObject("Serveur a modifee un persone avec success : index: " + j + "  " + person);

                        break;

                    case "S":
                        O.writeObject("Serveur pri pour supprission : ");
                        O.writeObject(Serveur.persons.size());

                        nom = (String) I.readObject();
                        pass = (String) I.readObject();

                        System.out.println("client  " + number + " supprimer " + nom + " " + pass);

                        person = null;
                        int k;
                        for (k = 0; k < Serveur.persons.size(); k++) {
                            Person p = Serveur.persons.get(k);
                            if (p.getNome().equals(nom) && p.getPass().equals(pass)) {
                                person = p;
                                break;
                            }
                        }

                        if (person != null) {
                            O.writeObject("Person a modifer : " + person);
                        } else {
                            O.writeObject("Aucun personn a supprmier");
                            break;
                        }

                        String c = (String) I.readObject(); // nesta9blo person jdid

                        if (c.equals("YES") || c.equals("Y")) {
                            System.out.println("client  " + number + " Serveur recoit : " + c);

                            Serveur.persons.remove(k);

                            System.out.println(Serveur.persons);

                            O.writeObject("supprmier");
                        }

                        else {
                            O.writeObject("Non supprmier");
                        }


                        break;

                    case "P":
                        O.writeObject("Serveur pri pour envoiyes la list des personnes : ");
                        O.writeObject(Serveur.persons.size());
                        StringBuilder people = new StringBuilder();
                        for (Person person1 : Serveur.persons) {
                            people.append(person1).append("\n");
                        }

                        O.writeObject(people.toString());
                        break;

                    case "Q":
                        O.writeObject("Serveur fermer!!");
                        break;

                }
            }


            I.close();
            O.close();
            soc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
