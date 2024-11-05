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
}
