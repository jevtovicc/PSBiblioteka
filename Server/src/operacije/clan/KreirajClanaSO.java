/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.clan;

import domen.Clan;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author filipjevtovic
 */
public class KreirajClanaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Clan)) {
            throw new Exception("Sistem ne moze da kreira clana");
        }
        Clan c = (Clan) param;
        if (c.getImePrezime().isEmpty() || c.getAdresaStanovanja().isEmpty()) {
            throw new Exception("Sistem ne moze da kreira clana");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.kreiraj((Clan) param);
    }
    
}
