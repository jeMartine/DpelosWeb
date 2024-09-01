package com.web.dpelos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.service.VeterinarioService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/vet")
public class VeterinarioController {
    @Autowired
    VeterinarioService veterinarioService;

    //buscar un veterinario
    @GetMapping("/buscarVet")
    public String buscarVeterinario() {
        return "buscarVet";
    }
    
    @PostMapping("/buscarVet")
    public String buscarVeterinario(@RequestParam("cedulaVet") String cedulaVet, 
                                    @RequestParam("passwordVet") String passwordVet,
                                    HttpSession session, Model model) {
        Veterinario veterinario = veterinarioService.buscarVetLogin(cedulaVet, passwordVet);
        if (veterinario != null) {
            session.setAttribute("idVeterinario", veterinario.getIdVeterinario());
            model.addAttribute("veterinario", veterinario);
            return "redirect:/empleado";
        } else {
            Veterinario vetExiste = veterinarioService.buscarVetPorCedula(cedulaVet);
            if(vetExiste != null){
                return "redirect:/login?error=password&cedula=" + cedulaVet + "&userType=veterinario";
            }else{
                return "redirect:/login?error=user&cedula=" + cedulaVet + "&userType=veterinario";
            }
        }  
    }
    
}
