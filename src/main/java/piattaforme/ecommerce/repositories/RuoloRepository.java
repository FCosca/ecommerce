package piattaforme.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import piattaforme.ecommerce.entities.Ruolo;

public interface RuoloRepository extends JpaRepository<Ruolo, String> {
}
