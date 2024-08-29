/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Clan;
import forme.clan.DodajClanaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author filipjevtovic
 */
public class DodajClanaKontroler {
    private final DodajClanaForma dcforma;
    
    public DodajClanaKontroler(DodajClanaForma dcforma) {
        this.dcforma = dcforma;
        addActionListeners();
    }

    private void addActionListeners() {
        dcforma.dodajAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               dodaj(e);
           }
           
           private void dodaj(ActionEvent e) {
               String imePrezime = dcforma.getjTextFieldImePrezime().getText().trim();
               String adresaStanovanja = dcforma.getjTextFieldAdresa().getText().trim();
               
               if (imePrezime.isEmpty()) {
                   JOptionPane.showMessageDialog(dcforma, "Polje za ime i prezime ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               } 
               
               if (adresaStanovanja.isEmpty()) {
                   JOptionPane.showMessageDialog(dcforma, "Polje za adresu ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               } 
               
               String datumUclanjenjaString = dcforma.getjTextFieldDatumUclanjenja().getText().trim();
               String datumIstekaString = dcforma.getjTextFieldDatumIsteka().getText().trim();
               
               if (datumUclanjenjaString.isEmpty()) {
                   JOptionPane.showMessageDialog(dcforma, "Polje za datum uclanjenja ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               } 
               
               if (datumIstekaString.isEmpty()) {
                   JOptionPane.showMessageDialog(dcforma, "Polje za datum isteka clanarine ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               } 
               
               Clan c = new Clan();
               c.setImePrezime(imePrezime);
               c.setAdresaStanovanja(adresaStanovanja);
               
               SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
               try {                   
                   Date datumUclanjenja = formatter.parse(datumUclanjenjaString);
                   c.setDatumUclanjenja(datumUclanjenja);
               } catch (ParseException pex) {
                   JOptionPane.showMessageDialog(dcforma, "Datum uclanjenja mora biti u odgovarajucem formatu", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               try {                   
                   Date datumIsteka = formatter.parse(datumIstekaString);
                   c.setDatumIsteka(datumIsteka);
               } catch (ParseException pex) {
                   JOptionPane.showMessageDialog(dcforma, "Datum isteka mora biti u odgovarajucem formatu", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               try {
                   Komunikacija.getInstanca().dodajClana(c);
                   JOptionPane.showMessageDialog(dcforma, "Sistem je zapamtio clana", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                   dcforma.dispose();
               } catch (IOException ioex) {
                    JOptionPane.showMessageDialog(dcforma, "Server je pao. Nije moguce obaviti operaciju. Bicete izlogovani sa sistema.", "Greska", JOptionPane.ERROR_MESSAGE);
                    Komunikacija.getInstanca().zatvoriResurse();
                    System.exit(0);
               } catch(Exception ex) {
                   String porukaGreske = ex.getMessage();
                   JOptionPane.showMessageDialog(dcforma, "Sistem ne moze da zapamti clana", "Greska", JOptionPane.ERROR_MESSAGE);
               }
           }
        });
    }

    public void otvoriFormu() {
        dcforma.setVisible(true);
    }
}
