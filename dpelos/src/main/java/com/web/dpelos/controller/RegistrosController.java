package com.web.dpelos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dpelos.service.VeterinarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/registros")
public class RegistrosController {

    @Autowired
    VeterinarioService veterinarioService;

    @GetMapping
    public String mostrarFormularioRegistros(@RequestParam(value = "tipoRegistro", required = false) String tipoRegistro, Model model, HttpSession session) {
        Long idVet = (Long) session.getAttribute("idVeterinario");
        if (idVet != null) {
            model.addAttribute("veterinario", veterinarioService.buscarVetPorId(idVet));
        }
        model.addAttribute("tipoRegistro", tipoRegistro != null ? tipoRegistro : ""); // Valor por defecto si no hay parámetro
        return "registros";
    }

    @PostMapping("/seleccionarTipo")
    public String seleccionarTipoRegistro(@RequestParam("tipoRegistro") String tipoRegistro) {
        return "redirect:/registros?tipoRegistro=" + tipoRegistro;
    }
}
