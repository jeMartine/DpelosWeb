package com.web.dpelos.dto;

public class DrogaTratamientoCountDTO {
    private Long idDroga;
    private String nombreDroga;
    private Long tratamientoCount;

    public DrogaTratamientoCountDTO(Long idDroga, String nombreDroga, Long tratamientoCount) {
        this.idDroga = idDroga;
        this.nombreDroga = nombreDroga;
        this.tratamientoCount = tratamientoCount;
    }

    public Long getIdDroga() {
        return idDroga;
    }

    public void setIdDroga(Long idDroga) {
        this.idDroga = idDroga;
    }

    public String getNombreDroga() {
        return nombreDroga;
    }

    public void setNombreDroga(String nombreDroga) {
        this.nombreDroga = nombreDroga;
    }

    public Long getTratamientoCount() {
        return tratamientoCount;
    }

    public void setTratamientoCount(Long tratamientoCount) {
        this.tratamientoCount = tratamientoCount;
    }

}