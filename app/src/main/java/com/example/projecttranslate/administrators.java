package com.example.projecttranslate;

public class administrators {
    Long id_admin;

    String contrasena;
    public administrators(Long id_admin, String contrasena) {
        this.id_admin = id_admin;
        this.contrasena = contrasena;
    }

    public administrators() {
    }

    public Long getId_admin() {
        return id_admin;
    }

    public void setId_admin(Long id_admin) {
        this.id_admin = id_admin;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


}
