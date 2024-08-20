/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.knjiga;

import domen.Knjiga;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author filipjevtovic
 */
public class IzmeniKnjiguSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Knjiga)) {
            throw new Exception("Sistem ne moze da kreira knjigu");
        }
        Knjiga k = (Knjiga) param;
        if (k.getNazivKnjige().isEmpty() || k.getISBN().isEmpty() || k.getAutor() == null) {
            throw new Exception("Sistem ne moze da kreira knjigu");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.izmeni((Knjiga) param);
    }
    
}
