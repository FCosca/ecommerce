package piattaforme.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import piattaforme.ecommerce.Exception.NoProdottiInStockException;
import piattaforme.ecommerce.entities.Prodotto;
import piattaforme.ecommerce.repositories.ProdottoRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartService {


    private ProdottoRepository prodottoRepository;


    private Map<Prodotto, Integer> prodotti = new HashMap<>();

    @Autowired
    public ShoppingCartService(ProdottoRepository prodottoRepository){this.prodottoRepository= prodottoRepository;}


    public void addProduct(Prodotto prodotto){
        if(prodotti.containsKey(prodotto)){
            prodotti.replace(prodotto, prodotti.get(prodotto) +1);
        }else {
            prodotti.put(prodotto, 1);
        }
    }

    public void removeProduct(Prodotto prodotto){
        if(prodotti.containsKey(prodotto)){
            if (prodotti.get(prodotti)>1)
                prodotti.replace(prodotto, prodotti.get(prodotto)-1);
            else if (prodotti.get(prodotto)== 1){
                prodotti.remove(prodotto);
            }

        }
    }
    public Map<Prodotto, Integer> getProdottiInCart(){return Collections.unmodifiableMap(prodotti);}

    public void checkout() throws NoProdottiInStockException {
        Prodotto prodotto;
        for(Map.Entry<Prodotto, Integer> entry: prodotti.entrySet()){
            prodotto = prodottoRepository.findOne(entry.getKey().getCodice());
            if (prodotto.getQuantita()< entry.getValue())
                throw new NoProdottiInStockException(prodotto);
            entry.getKey().setQuantita(prodotto.getQuantita() - entry.getValue());
        }
        prodottoRepository.save(prodotti.keySet());
        prodottoRepository.flush();
        prodotti.clear();
    }

    public BigDecimal getTotal(){
        return prodotti.entrySet().stream()
                .map(entry -> entry.getKey().getPrezzoP().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
