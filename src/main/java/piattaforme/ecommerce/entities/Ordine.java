package piattaforme.ecommerce.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long codice;

    @Basic
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_ordine")
    private Date dataOrdine;

    @ManyToOne
    @JoinColumn(name = "buyer")
    private Utente buyer;

 /*   @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private List<Prodotto> prodotto; */

 /*   @ManyToOne
    @JoinColumn(name = "prodotto")
    private Prodotto prodotto;  */

   @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="ORDINE_PRODOTTO", joinColumns = {
            @JoinColumn(name="ORDINE_CODICE")}, inverseJoinColumns = {
            @JoinColumn(name = "PRODOTTO_CODICE")})
    private List<Prodotto> prodotto;

    public Ordine (Long codice,Date dataOrdine, Utente buyer){
        this.codice= codice;
        this.dataOrdine= dataOrdine;
        this.buyer= buyer;
    }

    public Ordine(Utente buyer){
       /* this.prodotto= prodotto; */
        this.buyer= buyer;
    }







    public Ordine(@NotNull Long codice) {
        this.codice = codice;
    }

    public Ordine(){}


    public Long getCodice() {
        return codice;
    }

    public void setCodice(Long codice) {
        this.codice = codice;
    }


    public Date getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public Utente getBuyer() {
        return buyer;
    }

    public void setBuyer(Utente buyer) {
        this.buyer = buyer;
    }

 /*   public List<Prodotto> getProdotto() {
        return prodotto;
    }

    public void setProdotto(List<Prodotto> prodotto) {
        this.prodotto = prodotto;
    } */


    public List<Prodotto> getProdotto() {
        return prodotto;
    }

    public void setProdotto(List<Prodotto> prodotto) {
        this.prodotto = prodotto;
    }


}
