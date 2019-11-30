package com.example.projecttranslate;

public class administrators {
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    String  uid;

    String contrasena;
    public administrators(String id_admin, String contrasena) {
        this.uid = id_admin;
        this.contrasena = contrasena;
    }

    public administrators() {
    }
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


}
