/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;

/**
 *
 * @author filipjevtovic
 */
public class KonekcijaBPFactory {
    private static KonekcijaBPFactory instanca;
    private Connection konekcija;
    
    private KonekcijaBPFactory() {
        
        try {
            if(konekcija == null || konekcija.isClosed()) {
                String url = Konfiguracija.getInstanca().getProperty("url");
                String username = Konfiguracija.getInstanca().getProperty("username");
                String password = Konfiguracija.getInstanca().getProperty("password");
                konekcija = DriverManager.getConnection(url, username, password);
                konekcija.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KonekcijaBPFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static KonekcijaBPFactory getInstanca() {
        if(instanca == null) {
           instanca = new KonekcijaBPFactory(); 
        }
        return instanca;
    }

    public Connection getKonekcija() {
        return konekcija;
    }
}
