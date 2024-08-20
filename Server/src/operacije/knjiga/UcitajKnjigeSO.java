/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.knjiga;

import domen.Knjiga;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author filipjevtovic
 */
public class UcitajKnjigeSO extends ApstraktnaGenerickaOperacija {
    
    private List<Knjiga> knjige;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " JOIN Autor A on Knjiga.autorID = A.autorID";
        knjige = broker.vratiSve(new Knjiga(), uslov);
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }
}
