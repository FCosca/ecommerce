package piattaforme.ecommerce.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piattaforme.ecommerce.entities.Utente;
import piattaforme.ecommerce.repositories.UtenteRepository;
import piattaforme.ecommerce.services.UtenteService;

import javax.validation.Valid;

@Controller
public class UtenteController {

    @Autowired
    UtenteService utenteService;

    @Autowired
    UtenteRepository utenteRepository;
    @GetMapping("/login")
    public String showLoginForm(){
        return "views/loginForm";
    }


    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("Utente", new Utente());
        return "views/registerForm";
    }
    @PostMapping("/register")
    public String registerUtente(@Valid Utente utente, Model model){
        String email = utente.getEmail();
        if (utenteRepository.findByEmail(email) != null){
            model.addAttribute("exist",true);
            return "views/registerForm";
        }
        utenteService.createUtente(utente);
        model.addAttribute("success", true);
        return "views/loginForm";
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("User has authorities: " + userDetails.getAuthorities());
        return authentication.getName();
    }
}
