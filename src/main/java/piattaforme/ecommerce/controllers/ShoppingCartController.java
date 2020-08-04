package piattaforme.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import piattaforme.ecommerce.Exception.NoProdottiInStockException;
import piattaforme.ecommerce.entities.Prodotto;

import piattaforme.ecommerce.services.ProdottoService;
import piattaforme.ecommerce.services.ShoppingCartService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

 /*  @RequestMapping("/shoppingCart/addProduct/{codice}")
    public String addProduct(@PathVariable String codice, Model model, HttpSession session){

        Prodotto p= prodottoService.getByCodice(codice);
        if(session.getAttribute("prodsess")== null){
            Map<String, Integer> cart = new HashMap<>();
            cart.put(p.getNome(),p.getPrezzoP());
            session.setAttribute("prodsess", cart);
            model.addAttribute("cart", cart);
            Integer sum = 0;
            for(Integer val: cart.values()){
                sum+= val;
            }
            model.addAttribute("sum", sum);


        }else {
            Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("prodsess");
            cart.put(p.getNome(), p.getPrezzoP());
            session.setAttribute("prodsess", cart);
            model.addAttribute("cart", cart);
            Integer sum=0;
            for(Integer val: cart.values()){
                sum+=val;
            }
            model.addAttribute("sum,sum");
        }
        return "cart";

    }
    @RequestMapping("/cart")
    public String cart(HttpSession session, Model model) {

        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("prodsess");
        model.addAttribute("cart", cart);
        Integer sum = 0;
        for (Integer val : cart.values()) {
            sum += val;
        }
        model.addAttribute("sum", sum);
        return "show-cart";
    }

    @RequestMapping("/delete")
    public String deleteFromCart(@RequestParam("key") String key, HttpSession session, Model model) {

        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("prodsess");
        cart.remove(key);
        Integer sum = 0;
        for (Integer val : cart.values()) {
            sum += val;
        }
        session.setAttribute("prodsess", cart);
        model.addAttribute("cart", cart);
        model.addAttribute("sum", sum);
        return "redirect:/cart";
    } */
