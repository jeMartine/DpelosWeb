package com.web.dpelos.service;

import com.resend.services.emails.model.CreateEmailResponse;
import com.web.dpelos.entity.Email;

public interface EmailService {
    public CreateEmailResponse enviarEmail(Email email);
}
