/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.prijava;

import domen.Zaposleni;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author filipjevtovic
 */
public class PrijavaSO extends ApstraktnaGenerickaOperacija {
    
    private Zaposleni zaposleni;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Zaposleni)) {
            throw new Exception("Sistem ne moze da pronadje zaposlenog");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Zaposleni paramZaposleni = (Zaposleni) param;
        List<Zaposleni> sviZaposleni = broker.vratiSve(paramZaposleni, null);
        
        for(Zaposleni z : sviZaposleni) {
            if(z.getKorisnickoIme().equals(paramZaposleni.getKorisnickoIme()) &&
                    z.getLozinka().equals(paramZaposleni.getLozinka())) {
                zaposleni = z;
                return;
            }
        }
        
        zaposleni = null;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }
    
}
