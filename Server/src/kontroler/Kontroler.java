/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Autor;
import domen.Clan;
import domen.ClanskaKarta;
import domen.Knjiga;
import domen.Zaduzenje;
import domen.Zaposleni;
import java.util.List;
import operacije.autor.KreirajAutoraSO;
import operacije.autor.UcitajAutoreSO;
import operacije.prijava.PrijavaSO;
import operacije.clan.IzmeniClanaSO;
import operacije.clan.IzmeniClanskuKartuSO;
import operacije.clan.KreirajClanaSO;
import operacije.clan.KreirajClanskuKartuSO;
import operacije.clan.ObrisiClanaSO;
import operacije.clan.UcitajClanoveSO;
import operacije.knjiga.IzmeniKnjiguSO;
import operacije.knjiga.KreirajKnjiguSO;
import operacije.knjiga.ObrisiKnjiguSO;
import operacije.knjiga.UcitajKnjigeSO;
import operacije.zaduzenje.IzmeniZaduzenjeSO;
import operacije.zaduzenje.KreirajZaduzenjeSO;
import operacije.zaduzenje.ObrisiZaduzenjeSO;
import operacije.zaduzenje.UcitajZaduzenjaSO;

/**
 *
 * @author filipjevtovic
 */
public class Kontroler {
    private static Kontroler instanca;
    
    private Kontroler() {
        
    }
    
    public static Kontroler getInstanca() {
        if(instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public Zaposleni login(Zaposleni z) throws Exception {
        PrijavaSO operacija = new PrijavaSO();
        operacija.izvrsi(z, null);
        
        return operacija.getZaposleni();
    }

    public List<Clan> ucitajClanove() throws Exception {
        UcitajClanoveSO operacija = new UcitajClanoveSO();
        operacija.izvrsi(null, null);
        
        return operacija.getClanovi();
    }

    public void obrisiClana(Clan c) throws Exception {
        ObrisiClanaSO operacija = new ObrisiClanaSO();
        operacija.izvrsi(c, null);
    }

    public void dodajClana(Clan c) throws Exception {
        KreirajClanaSO operacija = new KreirajClanaSO();
        operacija.izvrsi(c, null);
    }

    public void izmeniClana(Clan c) throws Exception {
        IzmeniClanaSO operacija = new IzmeniClanaSO();
        operacija.izvrsi(c, null);
    }

    public List<Knjiga> ucitajKnjige() throws Exception {
        UcitajKnjigeSO operacija = new UcitajKnjigeSO();
        operacija.izvrsi(null, null);
        
        return operacija.getKnjige();
    }

    public void obrisiKnjigu(Knjiga k) throws Exception {
        ObrisiKnjiguSO operacija = new ObrisiKnjiguSO();
        operacija.izvrsi(k, null);
    }

    public List<Autor> ucitajAutore() throws Exception {
        UcitajAutoreSO operacija = new UcitajAutoreSO();
        operacija.izvrsi(null, null);
        
        return operacija.getAutori();
    }

    public void dodajKnjigu(Knjiga k) throws Exception {
        KreirajKnjiguSO operacija = new KreirajKnjiguSO();
        operacija.izvrsi(k, null);
    }

    public void izmeniKnjigu(Knjiga k) throws Exception {
        IzmeniKnjiguSO operacija = new IzmeniKnjiguSO();
        operacija.izvrsi(k, null);
    }

    public List<Zaduzenje> ucitajZaduzenja() throws Exception {
        UcitajZaduzenjaSO operacija = new UcitajZaduzenjaSO();
        operacija.izvrsi(null, null);
        
        return operacija.getPozajmice();
    }

    public void dodajZaduzenje(Zaduzenje p) throws Exception {
        KreirajZaduzenjeSO operacija = new KreirajZaduzenjeSO();
        operacija.izvrsi(p, null);
    }

    public void dodajAutora(Autor a) throws Exception {
        KreirajAutoraSO operacija = new KreirajAutoraSO();
        operacija.izvrsi(a, null);
    }

    public void obrisiZaduzenje(Zaduzenje z) throws Exception {
        ObrisiZaduzenjeSO operacija = new ObrisiZaduzenjeSO();
        operacija.izvrsi(z, null);
    }

    public void izmeniZaduzenje(Zaduzenje z) throws Exception {
        IzmeniZaduzenjeSO operacija = new IzmeniZaduzenjeSO();
        operacija.izvrsi(z, null);
    }

    public void dodajClanskuKartu(ClanskaKarta clanskaKarta) throws Exception {
        KreirajClanskuKartuSO operacija = new KreirajClanskuKartuSO();
        operacija.izvrsi(clanskaKarta, null);
    }

    public void izmeniClanskuKartu(ClanskaKarta clanskaKarta) throws Exception {
        IzmeniClanskuKartuSO operacija = new IzmeniClanskuKartuSO();
        operacija.izvrsi(clanskaKarta, null);
    }
}
