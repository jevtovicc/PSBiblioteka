/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author filipjevtovic
 */
public class Clan implements ApstraktniDomenskiObjekat {
    private long clanID;
    private String imePrezime;
    private String adresaStanovanja;
    private ClanskaKarta clanskaKarta;

    public Clan() {
    }

    public Clan(long clanID, String imePrezime, String adresaStanovanja, ClanskaKarta clanskaKarta) {
        this.clanID = clanID;
        this.imePrezime = imePrezime;
        this.adresaStanovanja = adresaStanovanja;
        this.clanskaKarta = clanskaKarta;
    }

    

    public long getClanID() {
        return clanID;
    }

    public void setClanID(long clanID) {
        this.clanID = clanID;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getAdresaStanovanja() {
        return adresaStanovanja;
    }

    public void setAdresaStanovanja(String adresaStanovanja) {
        this.adresaStanovanja = adresaStanovanja;
    }    

    public ClanskaKarta getClanskaKarta() {
        return clanskaKarta;
    }

    public void setClanskaKarta(ClanskaKarta clanskaKarta) {
        this.clanskaKarta = clanskaKarta;
    }
    
    @Override
    public String toString() {
        return imePrezime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Clan other = (Clan) obj;
        if (this.clanID != other.clanID) {
            return false;
        }
        if (!Objects.equals(this.imePrezime, other.imePrezime)) {
            return false;
        }
        return Objects.equals(this.adresaStanovanja, other.adresaStanovanja);
    }

    @Override
    public String vratiNazivTabele() {
        return "clan";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()) {
            long clanID = rs.getLong("clan_id");
            String imePrezime = rs.getString("ime_prezime");
            String adresaStanovanja = rs.getString("adresa_stanovanja");
            
            Date datumUclanjenja = rs.getDate("datum_uclanjenja");
            Date datumIsteka = rs.getDate("datum_isteka");
            long kartaID = rs.getLong("ck_id");
            
            ClanskaKarta ck = new ClanskaKarta(kartaID, datumUclanjenja, datumIsteka, null);
            
            Clan c = new Clan(clanID, imePrezime, adresaStanovanja, ck);
            ck.setClan(c);
            lista.add(c);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime_prezime, adresa_stanovanja";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + imePrezime + "'," + "'" + adresaStanovanja + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "clan_id=" + clanID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "ime_prezime='" + imePrezime + "', adresa_stanovanja='" + adresaStanovanja + "'";
    }

    @Override
    public void postaviGenerisaniID(long generisaniID) {
        this.clanID = generisaniID;
    }
    
}
