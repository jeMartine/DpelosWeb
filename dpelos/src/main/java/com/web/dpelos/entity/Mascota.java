package com.web.dpelos.entity;

/*Plain Old Java Object */
public class Mascota {
    /* Atributos de la Clase por el momento */
    private Integer idMascota;
    private String nombreMascota;
    private Integer edadMascota;
    private String urlFotoMascota;
    private String razaMascota;
    private Integer idDueno;

    public Mascota(Integer idMascota, String nombreMascota, Integer edadMascota, String urlFotoMascota,
            String razaMascota, Integer idDueno) {
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.edadMascota = edadMascota;
        this.urlFotoMascota = urlFotoMascota;
        this.razaMascota = razaMascota;
        this.idDueno = idDueno;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public void setEdadMascota(Integer edadMascota) {
        this.edadMascota = edadMascota;
    }

    public void setRazaMascota(String razaMascota) {
        this.razaMascota = razaMascota;
    }

    public void setUrlFotoMascota(String urlFotoMascota) {
        this.urlFotoMascota = urlFotoMascota;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public Integer getEdadMascota() {
        return edadMascota;
    }

    public String getFotoMascota() {
        return urlFotoMascota;
    }

    public String getRazaMascota() {
        return razaMascota;
    }

    public String getUrlFotoMascota() {
        return urlFotoMascota;
    }

    public Integer getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(Integer idDueno) {
        this.idDueno = idDueno;
    }

}
