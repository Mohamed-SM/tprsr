import java.io.Serializable;

public class Person implements Serializable {
    private String nome;
    private String prenom;
    private String tel;
    private String pass;

    public Person(String nome, String prenom, String tel, String pass) {
        this.nome = nome;
        this.prenom = prenom;
        this.tel = tel;
        this.pass = pass;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Person [" +
                "name=" + nome + ", " +
                "prenom=" + prenom + ", " +
                "tel=" + tel + ", " +
                "pass=" + pass + ", " +
                "]";
    }
}
