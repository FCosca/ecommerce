package piattaforme.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import piattaforme.ecommerce.entities.Utente;

import javax.persistence.LockModeType;

public interface UtenteRepository extends JpaRepository<Utente, String> {

    Utente findByEmail(String email);

    boolean existsByEmail(String email);
}
