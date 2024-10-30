package com.web.dpelos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.dpelos.dto.LoginRequest;
import com.web.dpelos.dto.LoginResponse;
import com.web.dpelos.service.AdminService;
import com.web.dpelos.service.DuenoService;
import com.web.dpelos.service.VeterinarioService;


@Controller
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    DuenoService duenoService;

    @Autowired
    VeterinarioService veterinarioService;

    @Autowired
    AdminService adminService;

    //Envía un objeto para iniciar sesión
    @PostMapping()
    public ResponseEntity<?> login(@RequestBody LoginRequest peticion) {
        Object user = null;
        Object admin= null;
        String rol = null;

        //Petición para empleado
        if (peticion.getType() == 1) {
            //Obtener los dos objetos
            user = veterinarioService.buscarVetLogin(peticion.getDocument(), peticion.getPassword());
            admin = adminService.buscarAdminLogin(peticion.getDocument(), peticion.getPassword());

            //Si no es nulo, envía administrador o veterinario
            if(admin!=null){
                rol = "ADMIN";
                return ResponseEntity.ok(new LoginResponse(admin, rol));
            }else if(user!=null){
                rol="VET";
                return ResponseEntity.ok(new LoginResponse(user, rol));
            }

        //petición para cliente
        } else if (peticion.getType() == 2) {
            user = duenoService.buscarDuenoPorCedula(peticion.getDocument());
            if(user!=null){
                return ResponseEntity.ok(new LoginResponse(user, "DUENO"));
            }
        }

        //Mensaje de error si no se encuentra cliente o empleado 
        String errorMessage = peticion.getType() == 1 ? "Usuario o credenciales incorrectas" : "No se ha encontrado el dueño";
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
        
    }

}
