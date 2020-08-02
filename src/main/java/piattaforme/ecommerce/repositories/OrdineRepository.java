package piattaforme.ecommerce.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import piattaforme.ecommerce.entities.Ordine;
import piattaforme.ecommerce.entities.Utente;


import java.util.List;

public interface OrdineRepository extends JpaRepository<Ordine, String > {



    @Override
    List<Ordine> findAll();

    List<Ordine> findByBuyer(Utente utente);

    Ordine findOrdineByCodice(Long codice);






}
