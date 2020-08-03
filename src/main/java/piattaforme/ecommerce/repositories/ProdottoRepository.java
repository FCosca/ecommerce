package piattaforme.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import piattaforme.ecommerce.entities.Prodotto;

import javax.persistence.LockModeType;
import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

public interface ProdottoRepository extends JpaRepository<Prodotto, String> {


    List<Prodotto> findAll();

    Optional<Prodotto> findByCodice(String codice);

    Prodotto findProdottoByCodice(String codice);

    List<Prodotto> findByNome(String nome);


    @Modifying
    @Query("update Prodotto p set p.quantita = ?1 where p.codice = ?2")
    void setQuantita(Integer quantita, String codice);

    boolean existsByCodice(String codice);

    boolean existsByNome(String nome);

}
