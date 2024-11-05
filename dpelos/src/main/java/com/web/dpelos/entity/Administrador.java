package com.web.dpelos.entity;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrador {

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;
    
    @Id
    @GeneratedValue
    private Long adminId;
    private String adminCedula;
    @Transient
    private String password;

    public Administrador(String adminCedula, String password) {
        this.adminCedula = adminCedula;
        this.password = password;
    }

}
