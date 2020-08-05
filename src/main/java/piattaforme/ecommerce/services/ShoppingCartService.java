package piattaforme.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import piattaforme.ecommerce.Exception.NoProdottiInStockException;
import piattaforme.ecommerce.Exception.ProdottoException;
import piattaforme.ecommerce.entities.Ordine;
import piattaforme.ecommerce.entities.Prodotto;

import piattaforme.ecommerce.entities.Utente;
import piattaforme.ecommerce.repositories.OrdineRepository;
import piattaforme.ecommerce.repositories.ProdottoRepository;
import piattaforme.ecommerce.repositories.ShoppingCartRepository;
import piattaforme.ecommerce.repositories.UtenteRepository;


import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.*;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    OrdineRepository ordineRepository;

    @Autowired
    UtenteRepository utenteRepository;

    private ProdottoRepository prodottoRepository;

    OrdineService ordineService;



    @Autowired
    public ShoppingCartService(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }







   private Map<Prodotto, Integer> prodotti = new HashMap<>();

    public Map<Prodotto, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(prodotti);
    }

    @Transactional
    public void addProdotto(Prodotto prodotto) {
        if (prodotti.containsKey(prodotto)) {
            prodotti.replace(prodotto, prodotti.get(prodotto) + 1);
        } else {
            prodotti.put(prodotto, 1);
        }
    }

/* public void addProdotto(Prodotto prodotto) {
        if (prodotti.containsKey(prodotto))
            prodotti.put(prodotto.getCodice(), prodotti.get(prodotto) + 1);
        else {
            prodotti.put(prodotto.getCodice(), 1);
        } */


    @Transactional
    public void removeProduct(Prodotto prodotto) {
            if (prodotti.containsKey(prodotto)) {
                if (prodotti.get(prodotto) > 1)
                    prodotti.replace(prodotto, prodotti.get(prodotto) - 1);
                else if (prodotti.get(prodotto) == 1) {
                    prodotti.remove(prodotto);
                }
            }



        }


   public void checkout() throws NoProdottiInStockException {
        Prodotto prodotto;
        for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) {
            // Controllo quantità per tutti i prodotti prima di fare il checkout
            prodotto = prodottoRepository.findProdottoByCodice(entry.getKey().getCodice());
            if (prodotto.getQuantita() < entry.getValue())
                throw new NoProdottiInStockException(prodotto);
            entry.getKey().setQuantita(prodotto.getQuantita() - entry.getValue());
            prodottoRepository.save(entry.getKey());


        }
        for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) {
            prodottoRepository.save(entry.getKey());

        }
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String currentPrincipalName = authentication.getName();

       Ordine o=new Ordine(utenteRepository.findByEmail(currentPrincipalName));

       ordineRepository.save(o);

        prodottoRepository.flush();
        prodotti.clear();
    }

    public BigDecimal getTotal() {
        return prodotti.entrySet().stream()
                .map(entry -> entry.getKey().getPrezzoP().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
/* public BigDecimal getTotal() {
     BigDecimal d=new BigDecimal(0);
     for(Map.Entry<Prodotto,Integer> e:prodotti.entrySet()) {
         BigDecimal b=new BigDecimal(e.getKey().getQuantita());
         d.add(e.getKey().getPrezzoP().multiply(b));
     }
     return d;} */

 /*   public List<Prodotto> getAll() {

        return (List<Prodotto>) prodottoRepository.findAll();

    }
    public Prodotto findById(String codice) {

        Prodotto p = prodottoRepository.findById(codice).orElse(null);

        return p;

    } */


    }
