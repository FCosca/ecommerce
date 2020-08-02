package piattaforme.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import piattaforme.ecommerce.entities.ProdottoAcquisto;

public interface ProdottoAcquistoRepository extends JpaRepository<ProdottoAcquisto, Integer> {
}
