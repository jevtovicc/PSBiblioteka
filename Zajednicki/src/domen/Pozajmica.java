/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.sql.ResultSet;

/**
 *
 * @author filipjevtovic
 */
public class Pozajmica implements ApstraktniDomenskiObjekat {
    private long pozajmicaID;
    private Clan clan;
    private Knjiga knjiga;
    private Date datumPozajmice;

    public Pozajmica() {
    }

    public Pozajmica(long pozajmicaID, Clan clan, Knjiga knjiga, Date datumPozajmice) {
        this.pozajmicaID = pozajmicaID;
        this.clan = clan;
        this.knjiga = knjiga;
        this.datumPozajmice = datumPozajmice;
    }

    public long getPozajmicaID() {
        return pozajmicaID;
    }

    public void setPozajmicaID(long pozajmicaID) {
        this.pozajmicaID = pozajmicaID;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public Date getDatumPozajmice() {
        return datumPozajmice;
    }

    public void setDatumPozajmice(Date datumPozajmice) {
        this.datumPozajmice = datumPozajmice;
    }

    @Override
    public String toString() {
        return "Pozajmica{" + "clan=" + clan + ", knjiga=" + knjiga + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.pozajmicaID ^ (this.pozajmicaID >>> 32));
        hash = 47 * hash + Objects.hashCode(this.clan);
        hash = 47 * hash + Objects.hashCode(this.knjiga);
        hash = 47 * hash + Objects.hashCode(this.datumPozajmice);
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
        final Pozajmica other = (Pozajmica) obj;
        if (this.pozajmicaID != other.pozajmicaID) {
            return false;
        }
        if (!Objects.equals(this.clan, other.clan)) {
            return false;
        }
        if (!Objects.equals(this.knjiga, other.knjiga)) {
            return false;
        }
        return Objects.equals(this.datumPozajmice, other.datumPozajmice);
    }

    @Override
    public String vratiNazivTabele() {
        return "Pozajmica";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "clanID, knjigaID, datumPozajmice";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return this.clan.getClanID() + "," + this.knjiga.getKnjigaID() + ",'" + this.datumPozajmice + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "pozajmicaID=" + pozajmicaID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "clanID=" + this.clan.getClanID() + ", knjigaID=" + this.knjiga.getKnjigaID() + ", datumPozajmice='" + this.datumPozajmice + "'";
    }

    @Override
    public void postaviGenerisaniID(long generisaniID) {
        this.pozajmicaID = generisaniID;
    }
    
    
}
