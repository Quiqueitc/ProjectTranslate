package com.example.projecttranslate;

public class revision {
    String espanol;
    String otomi;
    Long id_revision;
    Long estatus;

    public revision() {
    }

    public revision(String espanol, String otomi, Long id_revision, Long estatus) {
        this.espanol = espanol;
        this.otomi = otomi;
        this.id_revision = id_revision;
        this.estatus = estatus;
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

    public Long getId_revision() {
        return id_revision;
    }

    public void setId_revision(Long id_revision) {
        this.id_revision = id_revision;
    }

    public Long getEstatus() {
        return estatus;
    }

    public void setEstatus(Long estatus) {
        this.estatus = estatus;
    }


}
