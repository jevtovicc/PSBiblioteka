/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Autor;
import domen.Clan;
import domen.Knjiga;
import domen.Zaduzenje;
import domen.Zaposleni;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
        konekcija();
        startHeartbeat();
    }
    
    public static Komunikacija getInstanca() {
        if(instanca == null) {
            instanca = new Komunikacija();
        }
        return instanca;
    }
    
    private void konekcija() {
        try {    
            soket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Neuspesno povezivanje sa serverom. Proverite da li je server pokrenut.", "Greska", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    public void startHeartbeat() {
        new Thread(() -> {
            while (true) {
                try {
                    Zahtev z = new Zahtev(Operacija.HEARTBEAT, null);
                    posiljalac.posalji(z);
                    primalac.primi();
                    Thread.sleep(5000); // Sleep for 5 seconds before sending the next heartbeat
                } catch (IOException ioex) {
                    JOptionPane.showMessageDialog(null, "Server je pao. Bicete diskonektovani", "Greska", JOptionPane.ERROR_MESSAGE);
                    zatvoriResurse();
                    System.exit(0);
                } catch (InterruptedException e) {
                    System.err.println("Heartbeat thread interrupted: " + e.getMessage());
                    Thread.currentThread().interrupt(); // Restore the interrupted status
                    break; // Exit the loop if the thread is interrupted
                }
            }
        }).start();
    }
    
    public void zatvoriResurse() {
        try {
            soket.close();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Zaposleni login(String korIme, String lozinka) throws IOException {
        Zaposleni z = new Zaposleni();
        z.setKorisnickoIme(korIme);
        z.setLozinka(lozinka);
        
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, z);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        z = (Zaposleni) odgovor.getOdgovor();
        
        return z;
    }

    public List<Clan> ucitajClanove() throws IOException {
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

    public List<Knjiga> ucitajKnjige() throws IOException {
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

    public List<Autor> ucitajAutore() throws IOException {
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

    public List<Zaduzenje> ucitajPozajmice() throws IOException {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_POZAJMICE, null);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Zaduzenje> pozajmice = (List<Zaduzenje>) odgovor.getOdgovor();
        
        return pozajmice;
    }

    public void dodajPozajmicu(Zaduzenje p) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_POZAJMICU, p);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Exception e) {
            throw new Exception(e);
        }
    }

    public void posaljiKrajRada() throws IOException {
        Zahtev zahtev = new Zahtev(Operacija.KRAJ_RADA, null);
        posiljalac.posalji(zahtev);
        zatvoriResurse();
    }

    public void dodajAutora(Autor a) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_AUTORA, a);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Exception e) {
            throw new Exception(e);
        }
    }
}
