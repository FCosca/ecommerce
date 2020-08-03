package piattaforme.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import piattaforme.ecommerce.Exception.ProdottoException;
import piattaforme.ecommerce.Exception.ProdottoNonEsiste;
import piattaforme.ecommerce.entities.Prodotto;
import piattaforme.ecommerce.repositories.ProdottoRepository;
import piattaforme.ecommerce.services.ProdottoService;
import piattaforme.ecommerce.utils.Pager;



import java.util.List;
import java.util.Optional;

@RestController
public class ProdottoController {

 /*   private static final int INITIAL_PAGE = 0;

    @Autowired
    public ProdottoController(ProdottoService prodottoService){this.prodottoService= prodottoService;} */

    @Autowired
    private ProdottoService prodottoService;

    @Autowired
    private ProdottoRepository prodottoRepository;




    @GetMapping("/prodotti")
    public ResponseEntity getAll(){
        return new ResponseEntity<>(prodottoService.allProdotto(), HttpStatus.OK);

    }

    @GetMapping("prodotti/{codice}")
    public ResponseEntity getByCodice(@PathVariable ("codice") String codice) {
        Prodotto prodottoC = prodottoService.getByCodice(codice);
        if (prodottoC == null){
            return new ResponseEntity<>("Nessun Risultato", HttpStatus.OK);
        }
        return new ResponseEntity<>(prodottoC, HttpStatus.OK);
    }

    @GetMapping("prodotti/{nome}")
    public ResponseEntity getByNome(@PathVariable("nome") String nome){
        List<Prodotto> prodottoN = prodottoService.getByNome(nome);
        if(prodottoN.size() == 0){
            return new ResponseEntity<>("Nessun Risultato", HttpStatus.OK);
        }return new ResponseEntity<>(prodottoN, HttpStatus.OK);

    }

    @PostMapping("/prodotti/add")
    public ResponseEntity create(@RequestBody Prodotto prodotto) throws ProdottoException{
        Prodotto p=null;
        try{
            p=prodottoService.addProdotto(prodotto);
        }catch (ProdottoException e){
            return new ResponseEntity<>("Prodotto Già esistente", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @DeleteMapping("/prodotti/delete/{codice}")
    public ResponseEntity delete(@PathVariable("codice") String codice){
        try{
            prodottoService.deleteProdotto(codice);
        }catch (ProdottoNonEsiste ne){
            return new ResponseEntity<>("Impossibile eliminare", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("Eliminato", HttpStatus.OK);
    }

  /*  @PutMapping("prodotti/update/{codice}")
    public ResponseEntity update(@PathVariable String codice, Integer quantita){
        try{
            prodottoService.updateProdotto(quantita);
        }catch (ProdottoNonEsiste nn){
            return new ResponseEntity<>("Impossibile aggiornare", HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>("Aggioranto", HttpStatus.OK);
    } */

    @PutMapping("prodotti/modifica")
    public ResponseEntity setQuantita(@RequestParam Integer quantita, @RequestParam String codice){
        try{
            prodottoService.updateProdotto(quantita, codice);
        }catch (ProdottoNonEsiste e){
            return new ResponseEntity<>("Prodotto Non presente", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Quantità aggiornata!", HttpStatus.OK);
    }

  /*  @GetMapping("/listaProdotti")
    public ResponseEntity getAll(Model model, @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "sortBy", defaultValue = "id") String sortBy){
        List<Prodotto> result = prodottoService.allProdotto(pageNumber, pageSize, sortBy);
        if (result.size() <= 0){
            return new ResponseEntity<>("No risultati", HttpStatus.OK);
        }
        model.addAttribute("PaginaProdotti", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    } */

 /*  @GetMapping({"/listaProdotti"})
    public String listaprodotti(Model model, @RequestParam(value = "nome", defaultValue = "") String likeNome,
                                @RequestParam(value = "page", defaultValue = "1") int page){
        final int maxResult=5;
        final int maxNavigationPage= 10;

        PaginationResult<Prodotto> result = prodottoRepository.queryProducts(page,
                maxResult, maxNavigationPage, likeNome);
        model.addAttribute("Pagina Prodotti", result);
        return "listaProdotti";
    } */

 /*   @RequestMapping({"/buyProdotto"})
    public String listaProdotti(HttpServletRequest request, Model model,
                                @RequestParam(value = "codice", defaultValue = "") String codice){
        Prodotto prodotto= null;
        if(codice!= null && codice.length()> 0){
            prodotto = prodottoRepository.findProdottoByCodice(codice);
        }
        if (prodotto != null){
            CartInfo cartInfo = Utils.getCartInSession(request);

            ProductInfo productInfo = new ProductInfo(prodotto);

            cartInfo.addProduct(productInfo, 1);

        }
        return "redirect:/shoppingCart";
    } */

    /* @GetMapping("/list")
    public String listAll(Model model){
        model.addAttribute("prodotti", prodottoService.allProdotto());
        return "store";
    } */


 /*   @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listAll(Model model){
    model.addAttribute("prodotti", prodottoService.allProdotto());
    return "prodotti/list";
 } */

 /*   @ModelAttribute("list")
    public List<Prodotto> listAll(){
        return prodottoRepository.findAll();
    } */

 /*   @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView listAll(){
        ModelAndView mav = new ModelAndView("list/list");
        mav.addObject("prodotti", prodottoRepository.findAll()) ;
        return mav;
    } */

    @GetMapping("/list")
    public ModelAndView listAll(Model model){
        model.addAttribute("prodotti", prodottoService.allProdotto());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("store");
        mv.getModel().put(prodottoService.allProdotto().toString(), "");
        return mv;
    }






}
