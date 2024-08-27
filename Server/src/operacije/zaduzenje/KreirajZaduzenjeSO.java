/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.zaduzenje;

import domen.Zaduzenje;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author filipjevtovic
 */
public class KreirajZaduzenjeSO extends ApstraktnaGenerickaOperacija {
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Zaduzenje)) {
            throw new Exception("Sistem ne moze da zapamti zaduzenje");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.kreiraj((Zaduzenje) param);
    }
}
