/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.clan;

import domen.Clan;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author filipjevtovic
 */
public class UcitajClanoveSO extends ApstraktnaGenerickaOperacija {
    
    private List<Clan> clanovi;

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " JOIN clanska_karta ck on clan.clan_id = ck.clan_id";
        clanovi = broker.vratiSve(new Clan(), uslov);
    }

    public List<Clan> getClanovi() {
        return clanovi;
    }
    
    
}
