package piattaforme.ecommerce.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

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

    private byte[] image;

    @OneToMany(targetEntity = ProdottoAcquisto.class, mappedBy = "prodotto", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProdottoAcquisto> prodottoAcquisto;




    public Prodotto(@NotEmpty String codice, @NotEmpty String nome, @NotEmpty String descrizione, @NotNull Integer quantita, BigDecimal prezzoP, byte[] image) {
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

    public BigDecimal getPrezzoP() {
        return prezzoP;
    }

    public void setPrezzoP(BigDecimal prezzoP) {
        this.prezzoP = prezzoP;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public List<ProdottoAcquisto> getProdottoAcquisto() {
        return prodottoAcquisto;
    }

    public void setProdottoAcquisto(List<ProdottoAcquisto> prodottoAcquisto) {
        this.prodottoAcquisto = prodottoAcquisto;
    }
}
