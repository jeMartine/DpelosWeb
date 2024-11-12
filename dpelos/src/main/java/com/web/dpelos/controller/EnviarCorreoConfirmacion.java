package com.web.dpelos.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resend.services.emails.model.CreateEmailResponse;
import com.web.dpelos.entity.Email;
import com.web.dpelos.entity.Tratamiento;
import com.web.dpelos.service.EmailService;

@RestController // Esta anotacion indica que esta clase es un controlador REST
@RequestMapping("/enviarCorreo") // Esta anotacion indica la URL base para todos los metodos de esta clase
@CrossOrigin(origins = "http://localhost:4200") // Esta anotacion indica que se permite el acceso a esta URL desde un
                                                // origen diferente (Angular)
public class EnviarCorreoConfirmacion {
    
    @Autowired
    EmailService emailService;

@PostMapping()
public ResponseEntity<Map<String, String>> enviarCorreo(@RequestBody Email email) {
    CreateEmailResponse response = emailService.enviarEmail(email);
    Map<String, String> result = new HashMap<>();
    
    if (response != null) {
        result.put("message", "Correo enviado exitosamente");
        return ResponseEntity.ok(result);
    } else {
        result.put("message", "Error al enviar el correo");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }
}
    

}
