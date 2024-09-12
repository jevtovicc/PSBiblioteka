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
import static komunikacija.Operacija.DODAJ_CLANA;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import kontroler.Kontroler;
import observer.PrijavljeniZaposleniMenadzer;

/**
 *
 * @author filipjevtovic
 */
public class ObradaKlijentskihZahteva extends Thread {
    Socket soket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj=false;
    private Zaposleni prijavljeniZaposleni;
    
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

                switch (zahtev.getOperacija()) {
                    case HEARTBEAT -> obradiHeartbeat();
                    case KRAJ_RADA -> prekiniNit();
                    case LOGIN -> obradiLogin(zahtev);
                    case DODAJ_CLANA -> obradiDodajClana(zahtev);
                    case UCITAJ_CLANOVE -> obradiUcitajClanove();
                    case IZMENI_CLANA -> obradiIzmeniClana(zahtev);
                    case OBRISI_CLANA -> obradiObrisiClana(zahtev);
                    case DODAJ_KNJIGU -> obradiDodajKnjigu(zahtev);
                    case UCITAJ_KNJIGE -> obradiUcitajKnjige();
                    case IZMENI_KNJIGU -> obradiIzmeniKnjigu(zahtev);
                    case OBRISI_KNJIGU -> obradiObrisiKnjigu(zahtev);
                    case UCITAJ_AUTORE -> obradiUcitajAutore();
                    case DODAJ_AUTORA -> obradiDodajAutora(zahtev);
                    case UCITAJ_ZADUZENJA -> obradiUcitajZaduzenja();
                    case DODAJ_ZADUZENJE -> obradiDodajZaduzenje(zahtev);
                    case OBRISI_ZADUZENJE -> obradiObrisiZaduzenje(zahtev);
                    case IZMENI_ZADUZENJE -> obradiIzmeniZaduzenje(zahtev);
                    default -> throw new IllegalStateException("Greska. Operacija ne postoji.");
                }
            } catch(Exception e) {
                System.out.println("Neuspesno slanje odgovora ka klijentu!");
                e.printStackTrace();
            }
            
        }
    }
      
    public void prekiniNit() {
        kraj = true;
        try { 
            soket.close(); 
            PrijavljeniZaposleniMenadzer.ukloniZaposlenog(prijavljeniZaposleni);
            System.out.println("Klijent diskonektovan");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        interrupt();
    }
    
    private void obradiLogin(Zahtev zahtev) throws Exception {
        Odgovor odgovor = new Odgovor();
        Zaposleni z = (Zaposleni) zahtev.getParametar();
        z = Kontroler.getInstanca().login(z);
        if (z != null) {
            this.prijavljeniZaposleni = z;
            PrijavljeniZaposleniMenadzer.dodajZaposlenog(z);
        }
        
        
        odgovor.setOdgovor(z);
        posiljalac.posalji(odgovor);
    }
    
    private void obradiDodajClana(Zahtev zahtev) throws IOException {
        Odgovor odgovor = new Odgovor();
        try {
            Clan c = (Clan) zahtev.getParametar();
            Kontroler.getInstanca().dodajClana(c);
            Kontroler.getInstanca().dodajClanskuKartu(c.getClanskaKarta());
            odgovor.setOdgovor(null);
        } catch(Exception e) {
            odgovor.setOdgovor(e);
        }
        posiljalac.posalji(odgovor);
    }
    
    private void obradiUcitajClanove() throws Exception {
        Odgovor odgovor = new Odgovor();
        List<Clan> clanovi = Kontroler.getInstanca().ucitajClanove();
        odgovor.setOdgovor(clanovi);
        posiljalac.posalji(odgovor);
    }
    
    private void obradiIzmeniClana(Zahtev zahtev) throws IOException {
        Odgovor odgovor = new Odgovor();
        try {
            Clan c = (Clan) zahtev.getParametar();
            Kontroler.getInstanca().izmeniClana(c);
            Kontroler.getInstanca().izmeniClanskuKartu(c.getClanskaKarta());
            odgovor.setOdgovor(c);
        } catch(Exception e) {
            odgovor.setOdgovor(e);
        }
        posiljalac.posalji(odgovor);
    }
    
    private void obradiObrisiClana(Zahtev zahtev) throws IOException {
        Odgovor odgovor = new Odgovor();
        try {
            Clan c = (Clan) zahtev.getParametar();
            Kontroler.getInstanca().obrisiClana(c);
            odgovor.setOdgovor(c);
        } catch(Exception e) {
            odgovor.setOdgovor(e);
        }
        posiljalac.posalji(odgovor);
    }

    private void obradiDodajKnjigu(Zahtev zahtev) throws IOException {
        Odgovor odgovor = new Odgovor();
        try {
            Knjiga k = (Knjiga) zahtev.getParametar();
            Kontroler.getInstanca().dodajKnjigu(k);                            
            odgovor.setOdgovor(null);
        } catch(Exception e) {
            odgovor.setOdgovor(e);
        }
        posiljalac.posalji(odgovor);
    }

    private void obradiUcitajKnjige() throws Exception {
        Odgovor odgovor = new Odgovor();
        List<Knjiga> knjige = Kontroler.getInstanca().ucitajKnjige();
        odgovor.setOdgovor(knjige);
        posiljalac.posalji(odgovor);
    }

    private void obradiIzmeniKnjigu(Zahtev zahtev) throws IOException {
        Odgovor odgovor = new Odgovor();
        try {
            Knjiga k = (Knjiga) zahtev.getParametar();
            Kontroler.getInstanca().izmeniKnjigu(k);                            
            odgovor.setOdgovor(k);
        } catch(Exception e) {
            odgovor.setOdgovor(e);
        }
        posiljalac.posalji(odgovor);
    }

    private void obradiObrisiKnjigu(Zahtev zahtev) throws IOException {
        Odgovor odgovor = new Odgovor();
        try {
            Knjiga k = (Knjiga) zahtev.getParametar();
            Kontroler.getInstanca().obrisiKnjigu(k);
            odgovor.setOdgovor(k);
        } catch(Exception e) {
            odgovor.setOdgovor(e);
        }
        posiljalac.posalji(odgovor);
    }

    private void obradiUcitajAutore() throws Exception {
        Odgovor odgovor = new Odgovor();
        List<Autor> autori = Kontroler.getInstanca().ucitajAutore();
        odgovor.setOdgovor(autori);
        posiljalac.posalji(odgovor);
    }

    private void obradiUcitajZaduzenja() throws Exception {
        Odgovor odgovor = new Odgovor();
        List<Zaduzenje> pozajmice = Kontroler.getInstanca().ucitajZaduzenja();
        odgovor.setOdgovor(pozajmice);
        posiljalac.posalji(odgovor);
    }

    private void obradiDodajZaduzenje(Zahtev zahtev) throws IOException {
        Odgovor odgovor = new Odgovor();
        try {
            Zaduzenje p = (Zaduzenje) zahtev.getParametar();
            Kontroler.getInstanca().dodajZaduzenje(p);                            
            odgovor.setOdgovor(null);
        } catch(Exception e) {
            odgovor.setOdgovor(e);
        }
        posiljalac.posalji(odgovor);
    }

    private void obradiHeartbeat() throws IOException {
        posiljalac.posalji(new Odgovor());
    }

    private void obradiDodajAutora(Zahtev zahtev) throws IOException {
        Odgovor odgovor = new Odgovor();
        try {
            Autor a = (Autor) zahtev.getParametar();
            Kontroler.getInstanca().dodajAutora(a);                            
            odgovor.setOdgovor(null);
        } catch(Exception e) {
            odgovor.setOdgovor(e);
        }
        posiljalac.posalji(odgovor);
    }

    private void obradiObrisiZaduzenje(Zahtev zahtev) throws IOException {
        Odgovor odgovor = new Odgovor();
        try {
            Zaduzenje z = (Zaduzenje) zahtev.getParametar();
            Kontroler.getInstanca().obrisiZaduzenje(z);
            odgovor.setOdgovor(z);
        } catch(Exception e) {
            odgovor.setOdgovor(e);
        }
        posiljalac.posalji(odgovor);
    }

    private void obradiIzmeniZaduzenje(Zahtev zahtev) throws IOException {
        Odgovor odgovor = new Odgovor();
        try {
            Zaduzenje z = (Zaduzenje) zahtev.getParametar();
            Kontroler.getInstanca().izmeniZaduzenje(z);
            odgovor.setOdgovor(z);
        } catch(Exception e) {
            odgovor.setOdgovor(e);
        }
        posiljalac.posalji(odgovor);
    }

    public Zaposleni getPrijavljeniZaposleni() {
        return prijavljeniZaposleni;
    }
}
