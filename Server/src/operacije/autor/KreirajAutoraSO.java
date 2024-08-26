/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.autor;

import domen.Autor;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author filipjevtovic
 */
public class KreirajAutoraSO extends ApstraktnaGenerickaOperacija {
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Autor)) {
            throw new Exception("Sistem ne moze da kreira autora");
        }
        Autor a = (Autor) param;
        if (a.getImePrezime().isEmpty()) {
            throw new Exception("Sistem ne moze da kreira autora");
        }
    }
    
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.kreiraj((Autor) param);
    }

}
