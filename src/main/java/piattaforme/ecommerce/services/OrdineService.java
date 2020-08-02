package piattaforme.ecommerce.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import piattaforme.ecommerce.Exception.OrdineNonEsiste;
import piattaforme.ecommerce.Exception.UtenteNotFoundException;
import piattaforme.ecommerce.entities.Ordine;
import piattaforme.ecommerce.entities.Utente;
import piattaforme.ecommerce.repositories.OrdineRepository;
import piattaforme.ecommerce.repositories.ProdottoAcquistoRepository;
import piattaforme.ecommerce.repositories.UtenteRepository;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrdineService {

    @Autowired
    OrdineRepository ordineRepository;

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    ProdottoService prodottoService;

    @Autowired
    ProdottoAcquistoRepository prodottoAcquistoRepository;

   /* @Autowired
    private SessionFactory sessionFactory;
 */

    @Transactional(readOnly = true)
    public List<Ordine> allOrdine(){return ordineRepository.findAll();}

    @Transactional
    public Ordine addOrdine(Ordine ordine){
        return ordineRepository.save(ordine);
    }

    @Transactional
    public void deleteOrdine(Long codice) throws OrdineNonEsiste{
        Ordine o = ordineRepository.findOrdineByCodice(codice);
        if (o == null){
            throw new OrdineNonEsiste();
        }ordineRepository.delete(o);
    }

    @Transactional(readOnly = true)
    public List<Ordine> getOrdineByUtente(Utente utente) throws UtenteNotFoundException {
        if(!utenteRepository.existsByEmail(utente.getEmail())){
            throw new UtenteNotFoundException();
        }
        return ordineRepository.findByBuyer(utente);
    }


}