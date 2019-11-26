package com.example.projecttranslate;

public class translate {
    private String espanol;
    private String otomi;
    private Long id_traduccion;

    public translate() {
    }

    public translate(String espanol, String otomi, Long id_traduccion) {
        this.espanol = espanol;
        this.otomi = otomi;
        this.id_traduccion = id_traduccion;
    }

    public String getEspanol() {
        return espanol;
    }

    public void setEspanol(String espanol) {
        this.espanol = espanol;
    }

    public String getOtomi() {
        return otomi;
    }

    public void setOtomi(String otomi) {
        this.otomi = otomi;
    }

    public Long getId_traduccion() {
        return id_traduccion;
    }

    public void setId_traduccion(Long id_traduccion) {
        this.id_traduccion = id_traduccion;
    }



}
