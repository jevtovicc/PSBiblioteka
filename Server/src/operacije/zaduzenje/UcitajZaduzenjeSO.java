/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.zaduzenje;

import domen.Zaduzenje;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author filipjevtovic
 */
public class UcitajZaduzenjeSO extends ApstraktnaGenerickaOperacija {
    
    private List<Zaduzenje> pozajmice;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " JOIN clan c on zaduzenje.clan_id = c.clan_id" +
                       " JOIN knjiga k on k.knjiga_id = zaduzenje.knjiga_id" + 
                       " JOIN autor a on k.autor_id = a.autor_id";
        pozajmice = broker.vratiSve(new Zaduzenje(), uslov);
    }

    public List<Zaduzenje> getPozajmice() {
        return pozajmice;
    }
}
