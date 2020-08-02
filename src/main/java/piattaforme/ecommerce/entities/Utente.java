package piattaforme.ecommerce.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Utente {


    @Id
    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String cognome;

    @NotEmpty
    private String indirizzo;

    @NotNull
    private Integer cap;

    @NotEmpty
    private String comune;

    @NotEmpty
    private String provincia;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ordine> ordine;


    public Utente( String email,  String password,  String nome, String cognome , String indirizzo,  Integer cap,  String comune,  String provincia ) {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.cap = cap;
        this.comune = comune;
        this.provincia = provincia;
    }

    public Utente(){}


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="UTENTE_RUOLO", joinColumns = {
            @JoinColumn(name="UTENTE_EMAIL", referencedColumnName = "email")}, inverseJoinColumns = {
            @JoinColumn(name = "RUOLO_NOME", referencedColumnName = "nome")})
    private List<Ruolo> ruolo;

    public List<Ruolo> getRuolo(){return ruolo;}

    public void setRuolo(List<Ruolo> ruolo){this.ruolo= ruolo;}

    public List<Ordine> getOrdine() {
        return ordine;
    }

    public void setOrdine(List<Ordine> ordine) {
        this.ordine = ordine;
    }
}
