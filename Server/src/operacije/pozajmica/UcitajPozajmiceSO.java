/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.pozajmica;

import domen.Pozajmica;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author filipjevtovic
 */
public class UcitajPozajmiceSO extends ApstraktnaGenerickaOperacija {
    
    private List<Pozajmica> pozajmice;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " JOIN Clan C on Pozajmica.clanID = C.clanID" +
                       " JOIN Knjiga K on K.knjigaID = Pozajmica.knjigaID" + 
                       " JOIN Autor A on K.autorID = A.autorID";
        pozajmice = broker.vratiSve(new Pozajmica(), uslov);
    }

    public List<Pozajmica> getPozajmice() {
        return pozajmice;
    }
}
