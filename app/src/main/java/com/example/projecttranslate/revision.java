package com.example.projecttranslate;

public class revision {
    String espanol;
    String otomi;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    String uid;

    public revision() {
    }

    public revision(String espanol, String otomi, String uid) {
        this.espanol = espanol;
        this.otomi = otomi;
        this.uid = uid;

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



}
