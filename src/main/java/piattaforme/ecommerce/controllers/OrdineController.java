package piattaforme.ecommerce.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import piattaforme.ecommerce.Exception.OrdineNonEsiste;
import piattaforme.ecommerce.Exception.UtenteNotFoundException;
import piattaforme.ecommerce.entities.Ordine;
import piattaforme.ecommerce.entities.Utente;
import piattaforme.ecommerce.services.OrdineService;

import java.util.List;

@RestController
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    @GetMapping("/ordine")
    public ResponseEntity getAll(){
        return new ResponseEntity<>(ordineService.allOrdine(), HttpStatus.OK);

    }

    @PostMapping("/ordine/add")
    public ResponseEntity create(@RequestBody Ordine ordine){
        Ordine o= null;
        o= ordineService.addOrdine(ordine);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    @DeleteMapping("/ordine/delete/{codice}")
    public ResponseEntity delete (@PathVariable ("codice") Long codice){
        try{
            ordineService.deleteOrdine(codice);
        }catch (OrdineNonEsiste one){
            return new ResponseEntity<>("Ordine non trovato e quindi impossibile eliminarlo", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Ordine Eliminato", HttpStatus.OK);
    }

 /*   @GetMapping("/{utente}")
    public List<Ordine> getOrdini(@RequestBody Utente utente){
        try{
            return ordineService.getOrdineByUtente(utente);
        }catch (UtenteNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente non trovato", e);
        }
    } */

}
