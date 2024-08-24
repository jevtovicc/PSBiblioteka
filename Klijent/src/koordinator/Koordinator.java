/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koordinator;

import domen.Clan;
import domen.Knjiga;
import domen.Zaposleni;
import forme.clan.DodajClanaForma;
import forme.GlavnaForma;
import forme.clan.IzmeniClanaForma;
import forme.prijava.LoginForma;
import forme.clan.PregledClanovaForma;
import forme.knjiga.DodajKnjiguForma;
import forme.knjiga.IzmeniKnjiguForma;
import forme.knjiga.PregledKnjigaForma;
import forme.zaduzenje.DodajZaduzenjeForma;
import forme.zaduzenje.PregledZaduzenjaForma;
import java.io.IOException;
import komunikacija.Komunikacija;
import kontroler.DodajClanaKontroler;
import kontroler.DodajKnjiguKontroler;
import kontroler.DodajZaduzenjeKontroler;
import kontroler.GlavnaFormaKontroler;
import kontroler.IzmeniClanaKontroler;
import kontroler.IzmeniKnjiguKontroler;
import kontroler.LoginKontroler;
import kontroler.PregledClanovaKontroler;
import kontroler.PregledKnjigaKontroler;
import kontroler.PregledZaduzenjaKontroler;

/**
 *
 * @author filipjevtovic
 */
public class Koordinator {
    private static Koordinator instanca;
    
    private Zaposleni ulogovani;
    private LoginKontroler loginKontroler;
    private GlavnaFormaKontroler glavnaFormaKontroler;
    
    private PregledClanovaKontroler pregledClanovaKontroler;
    private DodajClanaKontroler dodajClanaKontroler;
    private IzmeniClanaKontroler izmeniClanaKontroler;
    
    private PregledKnjigaKontroler pregledKnjigaKontroler;
    private DodajKnjiguKontroler dodajKnjiguKontroler;
    private IzmeniKnjiguKontroler izmeniKnjiguKontroler;
    
    private PregledZaduzenjaKontroler pregledPozajmicaKontroler;
    private DodajZaduzenjeKontroler dodajPozajmicuKontroler;
    
    private Koordinator() {
        
    }
    
    public static Koordinator getInstanca() {
        if(instanca == null) {
            instanca = new Koordinator();
        }
        return instanca;
    }

    public void otvoriLoginFormu() {        
        loginKontroler = new LoginKontroler(new LoginForma());
        loginKontroler.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaKontroler = new GlavnaFormaKontroler(new GlavnaForma());
        glavnaFormaKontroler.otvoriFormu();
    }

    public Zaposleni getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Zaposleni ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void otvoriPregledClanovaFormu() {
        pregledClanovaKontroler = new PregledClanovaKontroler(new PregledClanovaForma());
        pregledClanovaKontroler.otvoriFormu();
    }

    public void otvoriDodajClanaFormu() {
        dodajClanaKontroler = new DodajClanaKontroler(new DodajClanaForma());
        dodajClanaKontroler.otvoriFormu();
    }
    
    public void otvoriIzmeniClanaFormu(Clan clanZaIzmenu) {
        izmeniClanaKontroler = new IzmeniClanaKontroler(new IzmeniClanaForma(), clanZaIzmenu);
        izmeniClanaKontroler.otvoriFormu();
    }

    public void osveziPregledClanovaFormu() {
        pregledClanovaKontroler.pripremiFormu();
    }

    public void otvoriPregledKnjigaFormu() {
        pregledKnjigaKontroler = new PregledKnjigaKontroler(new PregledKnjigaForma());
        pregledKnjigaKontroler.otvoriFormu();
    }
    
     public void otvoriDodajKnjiguFormu() {
        dodajKnjiguKontroler = new DodajKnjiguKontroler(new DodajKnjiguForma());
        dodajKnjiguKontroler.otvoriFormu();
    }

    public void otvoriIzmeniKnjiguFormu(Knjiga knjigaZaIzmenu) {
        izmeniKnjiguKontroler = new IzmeniKnjiguKontroler(new IzmeniKnjiguForma(), knjigaZaIzmenu);
        izmeniKnjiguKontroler.otvoriFormu();
    }

    public void osveziPregledKnjigaFormu() {
        pregledKnjigaKontroler.pripremiFormu();
    }

    public void otvoriPregledPozajmicaFormu() {
        pregledPozajmicaKontroler = new PregledZaduzenjaKontroler(new PregledZaduzenjaForma());
        pregledPozajmicaKontroler.otvoriFormu();
    }

    public void otvoriDodajPozajmicuFormu() {
        dodajPozajmicuKontroler = new DodajZaduzenjeKontroler(new DodajZaduzenjeForma());
        dodajPozajmicuKontroler.otvoriFormu();
    }

    public void zatvoriAplikaciju() throws IOException {
        Komunikacija.getInstanca().posaljiKrajRada();
    }
}
