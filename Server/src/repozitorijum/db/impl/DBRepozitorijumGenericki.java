/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum.db.impl;

import domen.ApstraktniDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import repozitorijum.db.DBRepozitorijum;
import repozitorijum.db.KonekcijaBPFactory;

/**
 *
 * @author filipjevtovic
 */
public class DBRepozitorijumGenericki implements DBRepozitorijum<ApstraktniDomenskiObjekat>{

    @Override
    // TODO
    public List<ApstraktniDomenskiObjekat> vratiSve() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiSve(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        String sqlUpit = "SELECT * FROM " + param.vratiNazivTabele();
        if(uslov != null) {
            sqlUpit += uslov;
        }
        System.out.println(sqlUpit);
        
        Statement st = KonekcijaBPFactory.getInstanca().getKonekcija().createStatement();
        ResultSet rs = st.executeQuery(sqlUpit);
        
        List<ApstraktniDomenskiObjekat> lista = param.vratiListu(rs);
        
        rs.close();
        st.close();
        return lista;
    }

    @Override
    public void kreiraj(ApstraktniDomenskiObjekat param) throws Exception {
        String sqlUpit = "INSERT INTO " + param.vratiNazivTabele() + 
                " (" + param.vratiKoloneZaUbacivanje() + ") VALUES (" + param.vratiVrednostiZaUbacivanje() + ")";
        System.out.println(sqlUpit);
        
        ResultSet generatedKeys = null;
        Statement st = KonekcijaBPFactory.getInstanca().getKonekcija().createStatement();
        int affectedRows = st.executeUpdate(sqlUpit, Statement.RETURN_GENERATED_KEYS);
        
        if (affectedRows > 0) {
                generatedKeys = st.getGeneratedKeys();
                if (generatedKeys.next()) {
                    long generisaniID = generatedKeys.getLong(1);
                    param.postaviGenerisaniID(generisaniID);
                    System.out.println("Generated ID: " + generisaniID);
                }
            }
        
        
        
        st.close();
    }

    @Override
    public void izmeni(ApstraktniDomenskiObjekat param) throws Exception {
        String vrednostiZaIzmenu = param.vratiVrednostZaIzmenu();
        String sqlUpit = "UPDATE " + param.vratiNazivTabele() + " SET " + vrednostiZaIzmenu + " WHERE " + param.vratiPrimarniKljuc();
        System.out.println(sqlUpit);
        
        Statement st = KonekcijaBPFactory.getInstanca().getKonekcija().createStatement();
        st.executeUpdate(sqlUpit);
        st.close();
    }

    @Override
    public void obrisi(ApstraktniDomenskiObjekat param) throws Exception {
        String sqlUpit = "DELETE FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiPrimarniKljuc();
        System.out.println(sqlUpit);
        
        Statement st = KonekcijaBPFactory.getInstanca().getKonekcija().createStatement();
        st.executeUpdate(sqlUpit);
        st.close();
    }
    
}
