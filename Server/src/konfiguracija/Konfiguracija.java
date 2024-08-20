/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author filipjevtovic
 */
public class Konfiguracija {
    private static Konfiguracija instanca;
    private Properties konfiguracija;
    
    private Konfiguracija() {
        konfiguracija = new Properties();
        try {
            konfiguracija.load(new FileInputStream("/Users/filipjevtovic/NetBeansProjects/PSBiblioteka/Server/config/config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Konfiguracija getInstanca() {
        if(instanca == null) {
            instanca = new Konfiguracija();
        }
        return instanca;
    }
    
    public String getProperty(String key) {
        return konfiguracija.getProperty(key, "N/A");
    }
    
    public void setProperty(String key, String value) {
        konfiguracija.setProperty(key, value);
    }
    
    public void sacuvajIzmene() {
        try {
            konfiguracija.store(new FileOutputStream("/Users/filipjevtovic/NetBeansProjects/PSBiblioteka/Server/config/config.properties"), null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
