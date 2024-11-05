package com.web.dpelos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.web.dpelos.dto.LoginRequest;
import com.web.dpelos.dto.LoginResponse;
import com.web.dpelos.entity.Role;
import com.web.dpelos.entity.UserEntity;
import com.web.dpelos.repository.UserRepository;
import com.web.dpelos.security.JWTGenerator;
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

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTGenerator jwtGenerator;

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody LoginRequest peticion) {
        try {
            UserEntity user = userRepository.findByUsername(peticion.getDocument()).orElseThrow(
                    () -> new UsernameNotFoundException("Usuario no encontrado"));

            String rol = user.getRoles().stream().map(Role::getName).findFirst().orElse("SIN_ROL");

            if ("DUENO".equals(rol)) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(peticion.getDocument(), "123"));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String token = jwtGenerator.generateToken(authentication);
                return ResponseEntity.ok(new LoginResponse(token, "DUENO"));
                //return new ResponseEntity<String>(token, HttpStatus.OK);
                // Dueno dueno = duenoService.buscarDuenoPorCedula(user.getUsername());
                // return ResponseEntity.ok(new LoginResponse(token, "DUENO"));
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(peticion.getDocument(), peticion.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication); // Generar el token

            if ("ADMINISTRADOR".equals(rol)) {
                //Administrador admin = adminService.buscarAdminPorCedula(user.getUsername());
                //return new ResponseEntity<String>(token, HttpStatus.OK);
                return ResponseEntity.ok(new LoginResponse(token, "ADMMINISTRADOR"));
            } else if ("VETERINARIO".equals(rol)) {
                //Veterinario vet = veterinarioService.buscarVetPorCedula(user.getUsername());
                //return new ResponseEntity<String>(token, HttpStatus.OK);
                return ResponseEntity.ok(new LoginResponse(token, "VETERINARIO"));
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Rol no reconocido");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o credenciales incorrectas");
        }
    }
}
