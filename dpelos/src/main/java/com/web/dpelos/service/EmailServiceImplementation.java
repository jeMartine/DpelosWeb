package com.web.dpelos.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import com.web.dpelos.entity.Email;

@Service
public class EmailServiceImplementation implements EmailService {
    @Override
    public CreateEmailResponse enviarEmail(Email email) {
        Resend resend = new Resend("re_5hPoNKzR_NJkbLP8xGytsyEvmDrgTbk63");
        System.out.println(email.getCorreoPropietario());
        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("D'Pelos <onboarding@resend.dev>")
                .to(email.getCorreoPropietario())
                .subject("Cita agendada con D'Pelos para " + email.getNombreMascota() + "!")
                .html("<p>Estimado(a) " + email.getNombrePropietario() + ",</p>" +
                        "<p>Para D'Pelos es un gusto atender a tu peludito!</p>" +
                        "<p>Confirmamos la cita de <strong>" + email.getNombreMascota()
                        + "</strong> en la especialidad de <strong>" + email.getServicio()
                        + "</strong>, el día <strong>" + email.getFechaConsulta() + "</strong>.</p>" +
                        "<p>Esperamos con ansias encontrarnos.</p>" +
                        "<p>Atentamente,</p>" +
                        "<p>D'Pelos 🐾</p>")
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
            System.out.println("error"+data.getId());
            return data;
        } catch (ResendException e) {
            e.printStackTrace();
        }
        return null;
    }
}
