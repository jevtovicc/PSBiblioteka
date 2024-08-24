/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Autor;
import domen.Clan;
import domen.Knjiga;
import domen.Zaduzenje;
import domen.Zaposleni;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import kontroler.Kontroler;

/**
 *
 * @author filipjevtovic
 */
public class ObradaKlijentskihZahteva extends Thread {
    Socket soket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj=false;
    
    public ObradaKlijentskihZahteva(Socket soket) {
        this.soket = soket;
        posiljalac = new Posiljalac(soket);
        primalac = new Primalac(soket);
    }
    
    @Override
    public void run() {
        while(!kraj) {
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                Odgovor odgovor = new Odgovor();

                switch (zahtev.getOperacija()) {
                    case KRAJ_RADA:
                        prekiniNit();
                        return;
                    case LOGIN:
                        Zaposleni z = (Zaposleni) zahtev.getParametar();
                        z = Kontroler.getInstanca().login(z);
                        odgovor.setOdgovor(z);
                        break;
                    case UCITAJ_CLANOVE:
                        List<Clan> clanovi = Kontroler.getInstanca().ucitajClanove();
                        odgovor.setOdgovor(clanovi);
                        break;
                    case OBRISI_CLANA:
                        try {
                            Clan c = (Clan) zahtev.getParametar();
                            Kontroler.getInstanca().obrisiClana(c);
                            odgovor.setOdgovor(c);
                        } catch(Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case DODAJ_CLANA:
                        try {
                            Clan c = (Clan) zahtev.getParametar();
                            Kontroler.getInstanca().dodajClana(c);                            
                            odgovor.setOdgovor(null);
                        } catch(Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case IZMENI_CLANA:
                        try {
                            Clan c = (Clan) zahtev.getParametar();
                            Kontroler.getInstanca().izmeniClana(c);
                            odgovor.setOdgovor(c);
                        } catch(Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_KNJIGE:
                        List<Knjiga> knjige = Kontroler.getInstanca().ucitajKnjige();
                        odgovor.setOdgovor(knjige);
                        break;
                    case DODAJ_KNJIGU:
                        try {
                            Knjiga k = (Knjiga) zahtev.getParametar();
                            Kontroler.getInstanca().dodajKnjigu(k);                            
                            odgovor.setOdgovor(null);
                        } catch(Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case IZMENI_KNJIGU:
                        try {
                            Knjiga k = (Knjiga) zahtev.getParametar();
                            Kontroler.getInstanca().izmeniKnjigu(k);                            
                            odgovor.setOdgovor(k);
                        } catch(Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case OBRISI_KNJIGU:
                        try {
                            Knjiga k = (Knjiga) zahtev.getParametar();
                            Kontroler.getInstanca().obrisiKnjigu(k);
                            odgovor.setOdgovor(k);
                        } catch(Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_AUTORE:
                        List<Autor> autori = Kontroler.getInstanca().ucitajAutore();
                        odgovor.setOdgovor(autori);
                        break;
                    case UCITAJ_POZAJMICE:
                        List<Zaduzenje> pozajmice = Kontroler.getInstanca().ucitajPozajmice();
                        odgovor.setOdgovor(pozajmice);
                        break;
                    case DODAJ_POZAJMICU:
                        try {
                            Zaduzenje p = (Zaduzenje) zahtev.getParametar();
                            Kontroler.getInstanca().dodajPozajmicu(p);                            
                            odgovor.setOdgovor(null);
                        } catch(Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    default: System.out.println("Greska. Operacija ne postoji.");
                }

                posiljalac.posalji(odgovor);
            } catch(Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    
    public void prekiniNit() {
        kraj = true;
        try { 
            soket.close(); 
            System.out.println("Klijent diskonektovan");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        interrupt();
    }
}
