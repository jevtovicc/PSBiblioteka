/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author filipjevtovic
 */
public class Main {
    public static void main(String[] args) {
        // konekcija sa serverom
        Komunikacija.getInstanca();
        Koordinator.getInstanca().otvoriLoginFormu();
    }
}
