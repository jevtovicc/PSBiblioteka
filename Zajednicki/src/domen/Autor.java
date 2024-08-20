/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.ResultSet;

/**
 *
 * @author filipjevtovic
 */
public class Autor implements ApstraktniDomenskiObjekat {
    private long autorID;
    private String imePrezime;

    public Autor() {
    }

    public Autor(long autorID, String imePrezime) {
        this.autorID = autorID;
        this.imePrezime = imePrezime;
    }

    public long getAutorID() {
        return autorID;
    }

    public void setAutorID(long autorID) {
        this.autorID = autorID;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    @Override
    public String toString() {
        return imePrezime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (this.autorID ^ (this.autorID >>> 32));
        hash = 37 * hash + Objects.hashCode(this.imePrezime);
        return hash;
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
        final Autor other = (Autor) obj;
        if (this.autorID != other.autorID) {
            return false;
        }
        return Objects.equals(this.imePrezime, other.imePrezime);
    }

    @Override
    public String vratiNazivTabele() {
        return "Autor";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()) {
            long autorID = rs.getLong("autorID");
            String imePrezime = rs.getString("imePrezime");
            
            Autor a = new Autor(autorID, imePrezime);
            lista.add(a);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezime";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + this.imePrezime + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "autorID=" + autorID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "imePrezime='" + imePrezime + "'";
    }

    @Override
    public void postaviGenerisaniID(long generisaniID) {
        this.autorID = generisaniID;
    }
    
}
