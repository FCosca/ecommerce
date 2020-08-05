package piattaforme.ecommerce.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.mail.imap.protocol.INTERNALDATE;
import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
public class Prodotto {

    @Id
    @NotEmpty
    private String codice;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String descrizione;

    @NotNull
    private Integer quantita;

    @NotNull

    private BigDecimal prezzoP;

    private String image;





    public Prodotto(@NotEmpty String codice, @NotEmpty String nome, @NotEmpty String descrizione, @NotNull Integer quantita,  BigDecimal  prezzoP, String image) {
        this.codice = codice;
        this.nome = nome;
        this.descrizione = descrizione;
        this.quantita = quantita;
        this.prezzoP = prezzoP;
        this.image = image;
    }



    public  Prodotto(){}

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public   BigDecimal  getPrezzoP() {
        return prezzoP;
    }

    public void setPrezzoP( BigDecimal  prezzoP) {
        this.prezzoP = prezzoP;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto that = (Prodotto) o;
        return codice.equalsIgnoreCase(that.codice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codice);
    }

    @Override
    public String toString() {
        return  "codice = " + codice;
    }

 /*   @ManyToOne
    @JoinColumn(name = "ordine")
    @JsonIgnore
    private Ordine ordine;

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    } */

 /*   @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private List<Prodotto> prodotto; */


 /*   @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ordine> ordine ;

    public List<Ordine> getOrdine() {
        return ordine;
    }

    public void setOrdine(List<Ordine> ordine) {
        this.ordine = ordine;
    }  */
 @ManyToMany(mappedBy = "prodotto")
 private List<Ordine> ordine;

    public List<Ordine> getOrdine() {
        return ordine;
    }

    public void setOrdine(List<Ordine> ordine) {
        this.ordine = ordine;
    }
}
