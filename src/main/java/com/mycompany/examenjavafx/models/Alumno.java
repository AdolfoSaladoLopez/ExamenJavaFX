
package com.mycompany.examenjavafx.models;

import java.io.Serializable;
import java.util.Objects;

public class Alumno implements Serializable {
    
    private int id;
    private String nombre;
    private String apellidos;
    private int ad;
    private int sge;
    private int di;
    private int pmdm; 
    private int psp;
    private int eie;
    private int hlc;

    public Alumno() {
        
    }

    public Alumno(int id, String nombre, String apellidos, int ad, int sge, int di, int pmdm, int psp, int eie, int hlc) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ad = ad;
        this.sge = sge;
        this.di = di;
        this.pmdm = pmdm;
        this.psp = psp;
        this.eie = eie;
        this.hlc = hlc;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getAd() {
        return ad;
    }

    public void setAd(int ad) {
        this.ad = ad;
    }

    public int getSge() {
        return sge;
    }

    public void setSge(int sge) {
        this.sge = sge;
    }

    public int getDi() {
        return di;
    }

    public void setDi(int di) {
        this.di = di;
    }

    public int getPmdm() {
        return pmdm;
    }

    public void setPmdm(int pmdm) {
        this.pmdm = pmdm;
    }

    public int getPsp() {
        return psp;
    }

    public void setPsp(int psp) {
        this.psp = psp;
    }

    public int getEie() {
        return eie;
    }

    public void setEie(int eie) {
        this.eie = eie;
    }

    public int getHlc() {
        return hlc;
    }

    public void setHlc(int hlc) {
        this.hlc = hlc;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alumno other = (Alumno) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.ad != other.ad) {
            return false;
        }
        if (this.sge != other.sge) {
            return false;
        }
        if (this.di != other.di) {
            return false;
        }
        if (this.pmdm != other.pmdm) {
            return false;
        }
        if (this.psp != other.psp) {
            return false;
        }
        if (this.eie != other.eie) {
            return false;
        }
        if (this.hlc != other.hlc) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.apellidos, other.apellidos);
    }
    
    
    
    
}
