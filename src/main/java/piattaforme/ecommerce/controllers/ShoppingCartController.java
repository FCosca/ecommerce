package piattaforme.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import piattaforme.ecommerce.Exception.NoProdottiInStockException;
import piattaforme.ecommerce.services.ProdottoService;
import piattaforme.ecommerce.services.ShoppingCartService;

@RestController
public class ShoppingCartController {

    @Autowired
    private ProdottoService prodottoService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService,ProdottoService prodottoService){
        this.shoppingCartService = shoppingCartService;
        this.prodottoService= prodottoService;
    }

    @GetMapping("/cart")
    public ModelAndView ShoppingCart(){
        ModelAndView modelAndView= new ModelAndView("/cart");
        modelAndView.addObject("Prodotti", shoppingCartService.getProdottiInCart());
        modelAndView.addObject("Totale", shoppingCartService.getTotal().toString());
        return modelAndView;
    }

    @GetMapping("/cart/removeProduct/{codice}")
    public ModelAndView removeProductFromCart(@PathVariable("codice") String codice){
        prodottoService.findByCodice(codice).ifPresent(shoppingCartService::removeProduct);
        return ShoppingCart();
    }

    @GetMapping("/cart/addProduct/{codice}")
    public ModelAndView addProductToCart(@PathVariable("codice") String codice){
        prodottoService.findByCodice(codice).isPresent(shoppingCartService::addProduct);
        return ShoppingCart();
    }

    @GetMapping("/cart/checkout")
    public ModelAndView checkout(){
        try {
            shoppingCartService.checkout();

        }catch (NoProdottiInStockException e){
            return ShoppingCart().addObject("No Disponibilità", e.getMessage());
        }
        return ShoppingCart();
    }


}
