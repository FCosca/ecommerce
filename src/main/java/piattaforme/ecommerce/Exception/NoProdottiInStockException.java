package piattaforme.ecommerce.Exception;

import piattaforme.ecommerce.entities.Prodotto;

public class NoProdottiInStockException extends Exception {

    private static final String DEFAULT_MESSAGE = "0 disponibilità";

    public NoProdottiInStockException(){super(DEFAULT_MESSAGE);}

    public NoProdottiInStockException(Prodotto prodotto){
        super(String.format("No disponibilità %s in stock, Solo %d rimasta", prodotto.getNome(), prodotto.getQuantita()));
    }
}
