/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author filipjevtovic
 */
public class Zaduzenje implements ApstraktniDomenskiObjekat {
    private long zaduzenjeID;
    private Clan clan;
    private Knjiga knjiga;
    private Date datumZaduzenja;
    private Date datumRazduzenja;

    public Zaduzenje() {
    }

    public Zaduzenje(long pozajmicaID, Clan clan, Knjiga knjiga, Date datumZaduzenja, Date datumRazduzenja) {
        this.zaduzenjeID = pozajmicaID;
        this.clan = clan;
        this.knjiga = knjiga;
        this.datumZaduzenja = datumZaduzenja;
        this.datumRazduzenja = datumRazduzenja;
    }

    public long getZaduzenjeID() {
        return zaduzenjeID;
    }

    public void setZaduzenjeID(long zaduzenjeID) {
        this.zaduzenjeID = zaduzenjeID;
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

    public Date getDatumZaduzenja() {
        return datumZaduzenja;
    }

    public void setDatumZaduzenja(Date datumZaduzenja) {
        this.datumZaduzenja = datumZaduzenja;
    }

    public Date getDatumRazduzenja() {
        return datumRazduzenja;
    }

    public void setDatumRazduzenja(Date datumRazduzenja) {
        this.datumRazduzenja = datumRazduzenja;
    }

    @Override
    public String toString() {
        return "Pozajmica{" + "clan=" + clan + ", knjiga=" + knjiga + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.zaduzenjeID ^ (this.zaduzenjeID >>> 32));
        hash = 47 * hash + Objects.hashCode(this.clan);
        hash = 47 * hash + Objects.hashCode(this.knjiga);
        hash = 47 * hash + Objects.hashCode(this.datumZaduzenja);
        hash = 47 * hash + Objects.hashCode(this.datumRazduzenja);
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
        final Zaduzenje other = (Zaduzenje) obj;
        if (this.zaduzenjeID != other.zaduzenjeID) {
            return false;
        }
        if (!Objects.equals(this.clan, other.clan)) {
            return false;
        }
        if (!Objects.equals(this.knjiga, other.knjiga)) {
            return false;
        }
        if (!Objects.equals(this.datumZaduzenja, other.datumZaduzenja)) {
            return false;
        }
        return Objects.equals(this.datumRazduzenja, other.datumRazduzenja);
    }

    @Override
    public String vratiNazivTabele() {
        return "zaduzenje";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()) {
            long pozajmicaID = rs.getLong("zaduzenje_id");
            
            Date datumZaduzenja = rs.getDate("datum_zaduzenja");
            Date datumRazduzenja = rs.getDate("datum_razduzenja");
            
            long autorID = rs.getLong("autor_id");
            String autorImePrezime = rs.getString(17);
            Autor a = new Autor(autorID, autorImePrezime);
            
            long knjigaID = rs.getLong("knjiga_id");
            String nazivKnjige = rs.getString("naziv_knjige");
            String ISBN = rs.getString("isbn");
            int kolicina = rs.getInt("kolicina");
            Knjiga k = new Knjiga(knjigaID, nazivKnjige, ISBN, kolicina, a);           
            
            long clanID = rs.getLong("clan_id");
            String clanImePrezime = rs.getString(7);
            String adresaStanovanja = rs.getString("adresa_stanovanja");
            Date datumUclanjenja = rs.getDate("datum_uclanjenja");
            Date datumIsteka = rs.getDate("datum_isteka");
            Clan c = new Clan(clanID, clanImePrezime, adresaStanovanja, datumUclanjenja, datumIsteka);
            
            Zaduzenje p = new Zaduzenje(pozajmicaID, c, k, datumZaduzenja, datumRazduzenja);
            lista.add(p);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "clan_id, knjiga_id, datum_zaduzenja, datum_razduzenja";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        String outputPattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(outputPattern);
        
        String datumZaduzenjaString = sdf.format(datumZaduzenja);
        String datumRazduzenjaString = datumRazduzenja == null ? null : sdf.format(datumRazduzenja);        
        
        if(datumRazduzenjaString != null) {
            return clan.getClanID() + "," + knjiga.getKnjigaID() + ",'" + datumZaduzenjaString + "','" + datumRazduzenjaString + "'";
        } else {
            return clan.getClanID() + "," + knjiga.getKnjigaID() + ",'" + datumZaduzenjaString + "', NULL";
        }
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "zaduzenje_id=" + zaduzenjeID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        String outputPattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(outputPattern);
        String datumZaduzenjaString = sdf.format(datumZaduzenja);
        String datumRazduzenjaString = datumRazduzenja == null ? null : sdf.format(datumRazduzenja);  
        return "clan_id=" + clan.getClanID() + ", knjiga_id=" + knjiga.getKnjigaID() + ", datum_zaduzenja='" + datumZaduzenjaString + "', datum_razduzenja=" + datumRazduzenjaString == null ? "NULL" : datumRazduzenjaString;
    }

    @Override
    public void postaviGenerisaniID(long generisaniID) {
        this.zaduzenjeID = generisaniID;
    }
    
    
}
