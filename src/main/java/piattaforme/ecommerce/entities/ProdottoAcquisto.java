package piattaforme.ecommerce.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class ProdottoAcquisto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODACQ_ORD")
    @JsonIgnore
    private Ordine ordine;




    public ProdottoAcquisto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }




    public ProdottoAcquisto(){}




    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if( obj == null || getClass() != obj.getClass()) return false;
        ProdottoAcquisto that = (ProdottoAcquisto) obj;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
