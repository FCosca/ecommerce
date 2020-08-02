package piattaforme.ecommerce.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Ruolo {

    @Id
    private String nome;


    public Ruolo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Utente> getUtenti(){return utenti;}

    public void setUtenti(List<Utente> utenti){this.utenti= utenti;}

    public Ruolo(){}

   @ManyToMany(mappedBy = "ruolo")
    private List<Utente> utenti;


}
