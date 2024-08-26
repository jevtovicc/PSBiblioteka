/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Clan;
import forme.clan.IzmeniClanaForma;
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
public class IzmeniClanaKontroler {
    private final IzmeniClanaForma icforma;
    private final Clan clanZaIzmenu;
    
    public IzmeniClanaKontroler(IzmeniClanaForma icforma, Clan clanZaIzmenu) {
        this.icforma = icforma;
        this.clanZaIzmenu = clanZaIzmenu;
        addActionListeners();
    }

    private void addActionListeners() {
        icforma.izmeniAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               izmeni(e);
           }
           
           private void izmeni(ActionEvent e) {
               String imePrezime = icforma.getjTextFieldImePrezime().getText().trim();
               String adresaStanovanja = icforma.getjTextFieldAdresa().getText().trim();
               
               if (imePrezime.isEmpty()) {
                   JOptionPane.showMessageDialog(icforma, "Polje za ime i prezime ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               } 
               
               if (adresaStanovanja.isEmpty()) {
                   JOptionPane.showMessageDialog(icforma, "Polje za adresu ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
               String datumUclanjenjaString = icforma.getjTextFieldDatumUclanjenja().getText().trim();
               String datumIstekaString = icforma.getjTextFieldDatumIsteka().getText().trim();
               
               if (datumUclanjenjaString.isEmpty()) {
                   JOptionPane.showMessageDialog(icforma, "Polje za datum uclanjenja ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               } 
               
               if (datumIstekaString.isEmpty()) {
                   JOptionPane.showMessageDialog(icforma, "Polje za datum isteka clanarine ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               } 
               
               clanZaIzmenu.setImePrezime(imePrezime);
               clanZaIzmenu.setAdresaStanovanja(adresaStanovanja);
               
               try {                   
                   Date datumUclanjenja = formatter.parse(datumUclanjenjaString);
                   clanZaIzmenu.setDatumUclanjenja(datumUclanjenja);
               } catch (ParseException pex) {
                   JOptionPane.showMessageDialog(icforma, "Datum uclanjenja mora biti u odgovarajucem formatu", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               try {                   
                   Date datumIsteka = formatter.parse(datumIstekaString);
                   clanZaIzmenu.setDatumIsteka(datumIsteka);
               } catch (ParseException pex) {
                   JOptionPane.showMessageDialog(icforma, "Datum isteka mora biti u odgovarajucem formatu", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
              
               try {
                   Komunikacija.getInstanca().izmeniClana(clanZaIzmenu);
                   JOptionPane.showMessageDialog(icforma, "Sistem je izmenio podatke o clanu", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                   icforma.dispose();
               } catch(IOException ioex) {
                    JOptionPane.showMessageDialog(icforma, "Server je pao. Nije moguce obaviti operaciju. Bicete izlogovani sa sistema.", "Greska", JOptionPane.ERROR_MESSAGE);
                    Komunikacija.getInstanca().zatvoriResurse();
                    System.exit(0);
                } catch(Exception ex) {
                    String porukaGreske = ex.getMessage();
                    JOptionPane.showMessageDialog(icforma, porukaGreske, "Greska", JOptionPane.ERROR_MESSAGE);
                } 
           }
        });
    }
    
    public void otvoriFormu() {
        pripremiFormu(); // popuni polja
        icforma.setVisible(true);
    }

    private void pripremiFormu() {
        String imePrezime = clanZaIzmenu.getImePrezime();
        String adresa = clanZaIzmenu.getAdresaStanovanja();
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(outputPattern);
        String datumUclanjenjaString = sdf.format(clanZaIzmenu.getDatumUclanjenja());
        String datumIstekaString = sdf.format(clanZaIzmenu.getDatumIsteka());
        
        icforma.getjTextFieldImePrezime().setText(imePrezime);
        icforma.getjTextFieldAdresa().setText(adresa);
        icforma.getjTextFieldDatumUclanjenja().setText(datumUclanjenjaString);
        icforma.getjTextFieldDatumIsteka().setText(datumIstekaString);
    }
}
