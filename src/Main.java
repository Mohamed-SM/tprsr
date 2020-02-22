public class Main {

    public static void main(String[] args) {
        Person person = new Person("Mohamed", "Slimani", "06666666666", "modpass kbir w sucre beazf");
        System.out.println(person);

        System.out.println("name: " + person.getNome());
        System.out.println("tel: " + person.getTel());

        person.setPrenom("no one");

        System.out.println(person);

    }
}
