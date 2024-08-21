/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author filipjevtovic
 */
public class Pozajmica implements ApstraktniDomenskiObjekat {
    private long pozajmicaID;
    private Clan clan;
    private Knjiga knjiga;
    private Date datumPozajmice;
    private Date datumVracanja;

    public Pozajmica() {
    }

    public Pozajmica(long pozajmicaID, Clan clan, Knjiga knjiga, Date datumPozajmice, Date datumVracanja) {
        this.pozajmicaID = pozajmicaID;
        this.clan = clan;
        this.knjiga = knjiga;
        this.datumPozajmice = datumPozajmice;
        this.datumVracanja = datumVracanja;
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

    public Date getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(Date datumVracanja) {
        this.datumVracanja = datumVracanja;
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
        hash = 47 * hash + Objects.hashCode(this.datumVracanja);
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
        if (!Objects.equals(this.datumPozajmice, other.datumPozajmice)) {
            return false;
        }
        return Objects.equals(this.datumVracanja, other.datumVracanja);
    }

    @Override
    public String vratiNazivTabele() {
        return "Pozajmica";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()) {
            long pozajmicaID = rs.getLong("pozajmicaID");
            Date datumPozajmice = rs.getDate("datumPozajmice");
            Date datumVracanja = rs.getDate("datumVracanja");
            
            long autorID = rs.getLong("autorID");
            String autorImePrezime = rs.getString(17);
            Autor a = new Autor(autorID, autorImePrezime);
            
            long knjigaID = rs.getLong("knjigaID");
            String nazivKnjige = rs.getString("nazivKnjige");
            String ISBN = rs.getString("ISBN");
            int kolicina = rs.getInt("kolicina");
            Knjiga k = new Knjiga(knjigaID, nazivKnjige, ISBN, kolicina, a);           
            
            long clanID = rs.getLong("clanID");
            String clanImePrezime = rs.getString(7);
            String adresaStanovanja = rs.getString("adresaStanovanja");
            Date datumUclanjenja = rs.getDate("datumUclanjenja");
            Date datumIsteka = rs.getDate("datumIsteka");
            Clan c = new Clan(clanID, clanImePrezime, adresaStanovanja, datumUclanjenja, datumIsteka);
            
            Pozajmica p = new Pozajmica(pozajmicaID, c, k, datumPozajmice, datumVracanja);
            lista.add(p);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "clanID, knjigaID, datumPozajmice, datumVracanja";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return clan.getClanID() + "," + knjiga.getKnjigaID() + ",'" + datumPozajmice + ",'" + datumPozajmice + "'";
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
        return "clanID=" + clan.getClanID() + ", knjigaID=" + knjiga.getKnjigaID() + ", datumPozajmice='" + datumPozajmice + "', datumVracanja=" + datumVracanja;
    }

    @Override
    public void postaviGenerisaniID(long generisaniID) {
        this.pozajmicaID = generisaniID;
    }
    
    
}
