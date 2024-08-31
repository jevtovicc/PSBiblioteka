/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author filipjevtovic
 */
public class ClanskaKarta implements ApstraktniDomenskiObjekat {
    
    private long kartaID;
    private Date datumUclanjenja;
    private Date datumIsteka;
    private Clan clan;
    
    public ClanskaKarta() {
    }

    public ClanskaKarta(long kartaID, Date datumUclanjenja, Date datumIsteka, Clan clan) {
        this.kartaID = kartaID;
        this.datumUclanjenja = datumUclanjenja;
        this.datumIsteka = datumIsteka;
        this.clan = clan;
    }

    public long getKartaID() {
        return kartaID;
    }

    public Date getDatumUclanjenja() {
        return datumUclanjenja;
    }

    public Date getDatumIsteka() {
        return datumIsteka;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public void setKartaID(long kartaID) {
        this.kartaID = kartaID;
    }

    public void setDatumUclanjenja(Date datumUclanjenja) {
        this.datumUclanjenja = datumUclanjenja;
    }

    public void setDatumIsteka(Date datumIsteka) {
        this.datumIsteka = datumIsteka;
    }
    
    

    @Override
    public String vratiNazivTabele() {
        return "clanska_karta";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()) {
            long kartaId = rs.getLong("ck_id");
            Date datumUclanjenja = rs.getDate("datum_uclanjenja");
            Date datumIsteka = rs.getDate("datum_isteka");
            
            long clanID = rs.getLong("clan_id");
            String imePrezime = rs.getString("ime_prezime");
            String adresaStanovanja = rs.getString("adresa_stanovanja");
            
            Clan c = new Clan(clanID, imePrezime, adresaStanovanja, null);
            
            ClanskaKarta ck = new ClanskaKarta(kartaId, datumUclanjenja, datumIsteka, c);
            c.setClanskaKarta(ck);
            
            lista.add(ck);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datum_uclanjenja, datum_isteka, clan_id";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        String outputPattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(outputPattern);
        String datumUclanjenjaString = sdf.format(datumUclanjenja);
        String datumIstekaString = sdf.format(datumIsteka);
        return "'" + datumUclanjenjaString + "','" + datumIstekaString + "', " + clan.getClanID();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "ck_id=" + kartaID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        String outputPattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(outputPattern);
        String datumUclanjenjaString = sdf.format(datumUclanjenja);
        String datumIstekaString = sdf.format(datumIsteka);
        return "datum_uclanjenja='" + datumUclanjenjaString + "', datum_isteka='" + datumIstekaString + "', clan_id=" + clan.getClanID();
    }

    @Override
    public void postaviGenerisaniID(long generisaniID) {
        this.kartaID = generisaniID;
    }
}
