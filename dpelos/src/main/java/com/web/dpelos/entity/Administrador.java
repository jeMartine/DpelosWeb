package com.web.dpelos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrador {
    @Id
    @GeneratedValue
    private Long adminId;
    private String adminCedula;
    @JsonIgnore
    private String password;

    public Administrador(String adminCedula, String password) {
        this.adminCedula = adminCedula;
        this.password = password;
    }

}
