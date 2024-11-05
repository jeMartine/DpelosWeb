package com.web.dpelos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeterinarioDTO {
    private Long idVeterinario;
    private String nombreVeterinario;
    private String apellidoVeterinario;
    private String cedulaVeterinario;
    private boolean estadoVeterinario;
    private String fotoUrl;
    private int numeroAtenciones;
    private String especialidad;

    public VeterinarioDTO() {
        this.idVeterinario = null;
        this.nombreVeterinario = "";
        this.apellidoVeterinario = "";
        this.cedulaVeterinario = "";
        this.estadoVeterinario = false;
        this.fotoUrl = "";
        this.numeroAtenciones = 0;
        this.especialidad = "";
    }
}
