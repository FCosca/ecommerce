package piattaforme.ecommerce.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import piattaforme.ecommerce.entities.Ordine;
import piattaforme.ecommerce.entities.Prodotto;
import piattaforme.ecommerce.entities.Utente;
import piattaforme.ecommerce.repositories.ProdottoRepository;
import piattaforme.ecommerce.repositories.UtenteRepository;
import piattaforme.ecommerce.services.ProdottoService;
import piattaforme.ecommerce.utils.Pager;

import java.security.Principal;
import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    UtenteRepository utenteRepository;


    private static final int INITIAL_PAGE = 0;

    @Autowired
    public IndexController(ProdottoService prodottoService){this.prodottoService= prodottoService;}

    @Autowired
    private ProdottoService prodottoService;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @GetMapping("/")
    public String showIndex(Model model, Principal principal, Utente utente, Ordine ordine, Prodotto prodotto){

        return "index";

    }

    @GetMapping("/prodotti")
    public ModelAndView prodotti(@RequestParam("page") Optional<Integer> page){
        int evalPage = (page.orElse(0)<1) ? INITIAL_PAGE : page.get() -1;
        Page<Prodotto> prodotti = prodottoService.findAllProductsPageable(PageRequest.of(evalPage, 5));
        Pager pager =new Pager(prodotti);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prodotti", prodotti);
        modelAndView.addObject("pager", pager);
        modelAndView.setViewName("/prodotti");
        return modelAndView;
    }
}
