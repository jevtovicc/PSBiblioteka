/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.clan;

import domen.ClanskaKarta;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author filipjevtovic
 */
public class KreirajClanskuKartuSO extends ApstraktnaGenerickaOperacija {
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }
    
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.kreiraj((ClanskaKarta) param);
    }
    
}
