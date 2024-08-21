package com.web.dpelos.entity;
/*Clase Dueno */
public class Dueno {
    private Integer cedulaDueno;
    private String nombreDueno;
    private String apellidoDueno;
    private String correoDueno;
    private String celularDueno;

    public Dueno(Integer cedulaDueno, String nombreDueno, String apellidoDueno, String correoDueno, String celularDueno) {
        this.cedulaDueno = cedulaDueno;
        this.nombreDueno = nombreDueno;
        this.apellidoDueno = apellidoDueno;
        this.correoDueno = correoDueno;
        this.celularDueno = celularDueno;
    }

    public Integer getCedulaDueno() {
        return cedulaDueno;
    }

    public void setCedulaDueno(Integer cedulaDueno) {
        this.cedulaDueno = cedulaDueno;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public String getApellidoDueno() {
        return apellidoDueno;
    }

    public void setApellidoDueno(String apellidoDueno) {
        this.apellidoDueno = apellidoDueno;
    }

    public String getCorreoDueno() {
        return correoDueno;
    }

    public void setCorreoDueno(String correoDueno) {
        this.correoDueno = correoDueno;
    }

    public String getCelularDueno() {
        return celularDueno;
    }

    public void setCelularDueno(String celularDueno) {
        this.celularDueno = celularDueno;
    }
    
}
