package piattaforme.ecommerce.utils;

import org.springframework.data.domain.Page;
import piattaforme.ecommerce.entities.Prodotto;


public class Pager {

    private final Page<Prodotto> prodotti;
    public Pager(Page<Prodotto> prodotti){this.prodotti= prodotti;}
    public int getPageIndex(){return prodotti.getNumber()+1;}
    public int getPageSize(){return prodotti.getSize();}
    public boolean hasNext(){return prodotti.hasNext();}
    public boolean hasPrevious(){return prodotti.hasPrevious();}
    public int getTotalPages(){return prodotti.getTotalPages();}
    public long getTotalElements(){return prodotti.getTotalElements();}
    public boolean indexOutOfBounds(){
        return this.getPageIndex()<0 || this.getPageIndex()>this.getTotalElements();
    }

}
