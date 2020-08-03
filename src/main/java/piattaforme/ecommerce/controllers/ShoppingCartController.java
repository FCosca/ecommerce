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

    private final ShoppingCartService shoppingCartService;
    private final ProdottoService prodottoService;



    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProdottoService prodottoService) {
        this.shoppingCartService = shoppingCartService;
        this.prodottoService = prodottoService;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
        modelAndView.addObject("prodotti", shoppingCartService.getProductsInCart());
        modelAndView.addObject("totale", shoppingCartService.getTotal().toString());
        return modelAndView;
    }

    @GetMapping("/shoppingCart/addProduct/{codice}")
    public ModelAndView addProductToCart(@PathVariable("codice") String codice) {
        prodottoService.findById(codice).ifPresent(shoppingCartService::addProdotto);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/removeProduct/{codice}")
    public ModelAndView removeProductFromCart(@PathVariable("codice") String codice) {
        prodottoService.findById(codice).ifPresent(shoppingCartService::removeProduct);
        return shoppingCart();
    }

 /*   @GetMapping("/shoppingCart/checkout")
    public ModelAndView checkout() {
        try {
            shoppingCartService.checkout();
        } catch (NoProdottiInStockException e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }
        return shoppingCart();
    } */
}