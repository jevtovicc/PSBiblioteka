/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Clan;
import forme.clan.DodajClanaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
               String datumUclanjenjaString = dcforma.getjTextFieldDatumUclanjenja().getText().trim();
               String datumIstekaString = dcforma.getjTextFieldDatumIsteka().getText().trim();
               
               Clan c = new Clan();
               c.setImePrezime(imePrezime);
               c.setAdresaStanovanja(adresaStanovanja);
               
               SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
               try {                   
                   Date datumUclanjenja = formatter.parse(datumUclanjenjaString);
                   c.setDatumUclanjenja(datumUclanjenja);
               } catch (ParseException pex) {
                   JOptionPane.showMessageDialog(dcforma, "Datum uclanjenja mora biti u odgovarajucem formatu", "Greska", JOptionPane.ERROR_MESSAGE);
               }
               
               try {                   
                   Date datumIsteka = formatter.parse(datumIstekaString);
                   c.setDatumIsteka(datumIsteka);
               } catch (ParseException pex) {
                   JOptionPane.showMessageDialog(dcforma, "Datum isteka mora biti u odgovarajucem formatu", "Greska", JOptionPane.ERROR_MESSAGE);
               }
               
               System.out.println("Kreiranje clanae");
               
               try {
                   Komunikacija.getInstanca().dodajClana(c);
                   JOptionPane.showMessageDialog(dcforma, "Sistem je kreirao clana", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                   dcforma.dispose();
               } catch(Exception ex) {
                   String porukaGreske = ex.getMessage();
                   JOptionPane.showMessageDialog(dcforma, porukaGreske, "Greska", JOptionPane.ERROR_MESSAGE);
               }
           }
        });
    }

    public void otvoriFormu() {
        dcforma.setVisible(true);
    }
}
