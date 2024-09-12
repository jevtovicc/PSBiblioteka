/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer;

import domen.Zaposleni;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author filipjevtovic
 */
public class PrijavljeniZaposleniMenadzer {
    private static List<Zaposleni> prijavljeniZaposleni = new ArrayList<>();
    private static List<PrijavljeniZaposleniListener> listeners = new ArrayList<>();
    
    // Sinhronizovane metode za upravljanje zaposlenima
    public static synchronized void dodajZaposlenog(Zaposleni zaposleni) {
        prijavljeniZaposleni.add(zaposleni);
        notifyPrijavljeniZaposleniPromena();
    }
    
    public static synchronized void ukloniZaposlenog(Zaposleni zaposleni) {
        prijavljeniZaposleni.remove(zaposleni);
        notifyPrijavljeniZaposleniPromena();
    }
    
    public static synchronized List<Zaposleni> getPrijavljeniZaposleni() {
        return new ArrayList<>(prijavljeniZaposleni);  // Vraća kopiju liste
    }
    
    // Metode za rad sa listenerima
    public static synchronized void dodajListener(PrijavljeniZaposleniListener listener) {
        listeners.add(listener);
    }
    
    public static synchronized void ukloniListener(PrijavljeniZaposleniListener listener) {
        listeners.remove(listener);
    }

    // Obaveštavanje svih listenera o promeni
    private static synchronized void notifyPrijavljeniZaposleniPromena() {
        for (PrijavljeniZaposleniListener listener : listeners) {
            listener.onPrijavljeniZaposleniPromena();
        }
    }
    
    
}