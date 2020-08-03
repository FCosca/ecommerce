package piattaforme.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import piattaforme.ecommerce.Exception.NoProdottiInStockException;
import piattaforme.ecommerce.Exception.ProdottoException;
import piattaforme.ecommerce.entities.Prodotto;
import piattaforme.ecommerce.repositories.ProdottoRepository;
import piattaforme.ecommerce.repositories.ShoppingCartRepository;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    private ProdottoRepository prodottoRepository;

    @Autowired
    public ShoppingCartService(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }




   private Map<Prodotto, Integer> prodotti = new HashMap<>();

    public Map<Prodotto, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(prodotti);
    }

    public void addProdotto(Prodotto prodotto) {
        if (prodotti.containsKey(prodotto)) {
            prodotti.replace(prodotto, prodotti.get(prodotto) + 1);
        } else {
            prodotti.put(prodotto, 1);
        }
    }

    public void removeProduct(Prodotto prodotto) {
        if (prodotti.containsKey(prodotto)) {
            if (prodotti.get(prodotto) > 1)
                prodotti.replace(prodotto, prodotti.get(prodotto) - 1);
            else if (prodotti.get(prodotto) == 1) {
                prodotti.remove(prodotto);
            }
        }
    }

 /*   public void checkout() throws NoProdottiInStockException {
        Prodotto prodotto;
        for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) {
            // Refresh quantity for every product before checking
            prodotto = prodottoRepository.findOne(entry.getKey().getCodice());
            if (prodotto.getQuantita() < entry.getValue())
                throw new NoProdottiInStockException(prodotto);
            entry.getKey().setQuantita(prodotto.getQuantita() - entry.getValue());
        }
        prodottoRepository.save(prodotti.keySet());
        prodottoRepository.flush();
        prodotti.clear();
    } */

    public BigDecimal getTotal() {
        return prodotti.entrySet().stream()
                .map(entry -> entry.getKey().getPrezzoP().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }


}
