package piattaforme.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import piattaforme.ecommerce.entities.Ruolo;
import piattaforme.ecommerce.entities.Utente;
import piattaforme.ecommerce.repositories.UtenteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Utente createUtente(Utente utente){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        utente.setPassword(encoder.encode(utente.getPassword()));
        Ruolo utenteRuolo = new Ruolo("Utente");
        List<Ruolo> ruolo = new ArrayList<>();
        ruolo.add(utenteRuolo);
        utente.setRuolo(ruolo);
        utenteRepository.save(utente);
        return utente;
    }

    @Transactional(readOnly = true)
    public Utente findByEmail(String email){return utenteRepository.findByEmail(email);}
}
