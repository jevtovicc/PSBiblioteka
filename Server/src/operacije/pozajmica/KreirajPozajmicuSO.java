/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.pozajmica;

import domen.Knjiga;
import domen.Pozajmica;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author filipjevtovic
 */
public class KreirajPozajmicuSO extends ApstraktnaGenerickaOperacija {
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Pozajmica)) {
            throw new Exception("Sistem ne moze da kreira pozajmicu");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.kreiraj((Pozajmica) param);
    }
}
