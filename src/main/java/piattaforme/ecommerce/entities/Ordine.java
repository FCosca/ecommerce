package piattaforme.ecommerce.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @ManyToOne
    @JoinColumn(name = "prodotto")
    private Prodotto prodotto;






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

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }


/*    public List<Prodotto> getProdotto() {
        return prodotto;
    }

    public void setProdotto(List<Prodotto> prodotto) {
        this.prodotto = prodotto;
    } */
}
