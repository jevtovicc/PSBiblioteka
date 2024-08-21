/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author filipjevtovic
 */
public enum Operacija implements Serializable {
    LOGIN,
    UCITAJ_CLANOVE, 
    OBRISI_CLANA, 
    DODAJ_CLANA, IZMENI_CLANA, 
    UCITAJ_KNJIGE, 
    OBRISI_KNJIGU, 
    UCITAJ_AUTORE, 
    DODAJ_KNJIGU, 
    IZMENI_KNJIGU, 
    UCITAJ_POZAJMICE, 
    DODAJ_POZAJMICU
}
