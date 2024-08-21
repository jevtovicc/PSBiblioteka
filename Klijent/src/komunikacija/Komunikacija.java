/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Autor;
import domen.Clan;
import domen.Knjiga;
import domen.Pozajmica;
import domen.Zaposleni;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import koordinator.Koordinator;

/**
 *
 * @author filipjevtovic
 */
public class Komunikacija {
    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instanca;
    
    private Komunikacija() {
        
    }
    
    public static Komunikacija getInstanca() {
        if(instanca == null) {
            instanca = new Komunikacija();
        }
        return instanca;
    }
    
    public void konekcija() {
        try {
            soket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
        } catch (IOException ex) {
            System.out.println("NEUSPESNO POVEZIVANJE SA SERVEROM");
            ex.printStackTrace();
        }
        
    }

    public Zaposleni login(String korIme, String lozinka) {
        Zaposleni z = new Zaposleni();
        z.setKorisnickoIme(korIme);
        z.setLozinka(lozinka);
        
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, z);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        z = (Zaposleni) odgovor.getOdgovor();
        
        return z;
    }

    public List<Clan> ucitajClanove() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_CLANOVE, null);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Clan> clanovi = (List<Clan>) odgovor.getOdgovor();
        
        return clanovi;
    }

    public void obrisiClana(Clan c) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_CLANA, c);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Exception e) {
            throw new Exception(e);
        }
    }

    public void dodajClana(Clan c) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_CLANA, c);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Exception e) {
            throw new Exception(e);
        }
    }

    public void izmeniClana(Clan azuriraniClan) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_CLANA, azuriraniClan);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Exception e) {
            throw new Exception(e);
        } else {
            Koordinator.getInstanca().osveziPregledClanovaFormu();
        }
    }

    public List<Knjiga> ucitajKnjige() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KNJIGE, null);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Knjiga> knjige = (List<Knjiga>) odgovor.getOdgovor();
        
        return knjige;
    }

    public void obrisiKnjigu(Knjiga k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KNJIGU, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Exception e) {
            throw new Exception(e);
        }
    }

    public List<Autor> ucitajAutore() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_AUTORE, null);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Autor> autori = (List<Autor>) odgovor.getOdgovor();
        
        return autori;
    }

    public void dodajKnjigu(Knjiga k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KNJIGU, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Exception e) {
            throw new Exception(e);
        }
    }

    public void izmeniKnjigu(Knjiga azuriranaKnjiga) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_KNJIGU, azuriranaKnjiga);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Exception e) {
            throw new Exception(e);
        } else {
            Koordinator.getInstanca().osveziPregledKnjigaFormu();
        }
    }

    public List<Pozajmica> ucitajPozajmice() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_POZAJMICE, null);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Pozajmica> pozajmice = (List<Pozajmica>) odgovor.getOdgovor();
        
        return pozajmice;
    }

    public void dodajPozajmicu(Pozajmica p) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_POZAJMICU, p);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Exception e) {
            throw new Exception(e);
        }
    }
}
