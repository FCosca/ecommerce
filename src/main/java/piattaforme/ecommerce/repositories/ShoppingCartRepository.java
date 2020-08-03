package piattaforme.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import piattaforme.ecommerce.Exception.NoProdottiInStockException;
import piattaforme.ecommerce.entities.Prodotto;

import java.util.Map;

public interface ShoppingCartRepository extends JpaRepository<Prodotto, String> {


/*    void addProduct(Prodotto prodotto);

    void removeProduct(Prodotto prodotto); */

  /*  Map<Prodotto, Integer> getProdottiInCart(); */

 /*   void checkout() throws NoProdottiInStockException; */



}
