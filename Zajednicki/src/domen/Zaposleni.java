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
public class Zaposleni implements ApstraktniDomenskiObjekat {
    private long zaposleniID;
    private String imePrezime;
    private String korisnickoIme;
    private String lozinka;

    public Zaposleni() {
        
    }
    
    public Zaposleni(long zaposleniID, String imePrezime, String korisnickoIme, String lozinka) {
        this.zaposleniID = zaposleniID;
        this.imePrezime = imePrezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public long getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(long zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public String toString() {
        return imePrezime;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (int) (this.zaposleniID ^ (this.zaposleniID >>> 32));
        hash = 89 * hash + Objects.hashCode(this.imePrezime);
        hash = 89 * hash + Objects.hashCode(this.korisnickoIme);
        hash = 89 * hash + Objects.hashCode(this.lozinka);
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
        final Zaposleni other = (Zaposleni) obj;
        if (this.zaposleniID != other.zaposleniID) {
            return false;
        }
        if (!Objects.equals(this.imePrezime, other.imePrezime)) {
            return false;
        }
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.lozinka, other.lozinka);
    }

    @Override
    public String vratiNazivTabele() {
        return "Zaposleni";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()) {
            long zaposleniID = rs.getLong("zaposleniID");
            String imePrezime = rs.getString("imePrezime");
            String korisnickoIme = rs.getString("korisnickoIme");
            String lozinka = rs.getString("lozinka");
            
            Zaposleni z = new Zaposleni(zaposleniID, imePrezime, korisnickoIme, lozinka);
            lista.add(z);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezime, korisnickoIme, lozinka";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + this.imePrezime + "','" + this.korisnickoIme + "','" + this.lozinka + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "zaposleniID=" + zaposleniID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "imePrezime='" + this.imePrezime + "', korisnickoIme='" + this.korisnickoIme + "', lozinka='" + this.lozinka + "'";
    }

    @Override
    public void postaviGenerisaniID(long generisaniID) {
        this.zaposleniID = generisaniID;
    }
    
    
}
