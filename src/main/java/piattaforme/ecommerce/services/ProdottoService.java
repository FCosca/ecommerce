package piattaforme.ecommerce.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import piattaforme.ecommerce.Exception.ProdottoException;
import piattaforme.ecommerce.Exception.ProdottoNonEsiste;
import piattaforme.ecommerce.entities.Prodotto;
import piattaforme.ecommerce.repositories.ProdottoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdottoService {

    @Autowired
    ProdottoRepository prodottoRepository;

    @Transactional(readOnly= true)
    public List<Prodotto> allProdotto() {return prodottoRepository.findAll();}

    @Transactional(readOnly = true)
    public Prodotto getByCodice(String codice){return prodottoRepository.findProdottoByCodice(codice);}

    @Transactional(readOnly = true)
    public List<Prodotto> getByNome(String nome) {return prodottoRepository.findByNome(nome);}

 /*   public Page<Prodotto> findAllProductsPageable(Pageable pageable){return prodottoRepository.findAll(pageable);} */



    @Transactional
    public Prodotto addProdotto(Prodotto prodotto) throws ProdottoException{
    if(prodottoRepository.existsByCodice(prodotto.getCodice())){
        throw new ProdottoException();
    }
    return prodottoRepository.save(prodotto);
    }

    @Transactional
    public void deleteProdotto(String codice) throws ProdottoNonEsiste {
        Prodotto p = prodottoRepository.findProdottoByCodice(codice);
        if (p == null) {
            throw new ProdottoNonEsiste();
        }
        prodottoRepository.delete(p);
    }

  /*  @Transactional
    public void updateProdotto(String codice, Integer quantita) throws ProdottoNonEsiste{
        Prodotto up= prodottoRepository.findProdottoByCodice(codice);
        if (up == null){
            throw new ProdottoNonEsiste();
        }
        prodottoRepository.save(quantita);
    } */

    @Transactional
    public void updateProdotto(Integer quantita, String codice) throws ProdottoNonEsiste{
        Prodotto pu = prodottoRepository.findProdottoByCodice(codice);
        if(pu == null){
            throw new ProdottoNonEsiste();
        }
        prodottoRepository.setQuantita(quantita, codice);
    }

  /*  @Transactional(readOnly = true)
    public List<Prodotto> allProdotto(int pageNumber, int pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Prodotto> pagedResult = prodottoRepository.findAll(paging);
        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    } */

    @Cacheable(cacheNames = "prodotti")
    public List<?> listAll(){
        List<Prodotto> prodotti = new ArrayList<>();
        prodottoRepository.findAll().forEach(prodotti::add);
        return prodotti;
    }

  /*  public Optional<Prodotto> findByCodice(String codice){ return prodottoRepository.findByCodice(codice);} */


}




