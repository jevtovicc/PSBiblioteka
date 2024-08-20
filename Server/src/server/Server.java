/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author filipjevtovic
 */
public class Server extends Thread {
    private boolean kraj = false;
    private List<ObradaKlijentskihZahteva> klijenti;
    private ServerSocket serverSoket;
    
    public Server() {
        klijenti = new ArrayList<>();
    }
    
    // Metoda za pokretanje servera.
    @Override
    public void run() {
        kraj = false;
        try {
            serverSoket = new ServerSocket(9000);
            while(!kraj) {
                Socket soket = serverSoket.accept();
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(soket);
                klijenti.add(okz);
                okz.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void zaustaviServer() {
        kraj = true;
        try {
            for (ObradaKlijentskihZahteva k : klijenti) {
                k.prekiniNit();
            }
            serverSoket.close();
            interrupt();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
