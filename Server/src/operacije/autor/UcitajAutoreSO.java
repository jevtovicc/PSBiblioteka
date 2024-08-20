/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.autor;

import domen.Autor;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author filipjevtovic
 */
public class UcitajAutoreSO extends ApstraktnaGenerickaOperacija {
    
    private List<Autor> autori;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        autori = broker.vratiSve(new Autor(), kljuc);
    }

    public List<Autor> getAutori() {
        return autori;
    }
    
}
