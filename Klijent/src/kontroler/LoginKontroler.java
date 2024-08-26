/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Zaposleni;
import forme.prijava.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author filipjevtovic
 */
public class LoginKontroler {
    private final LoginForma lf;

    public LoginKontroler(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               prijava(e);
           }
           
           private void prijava(ActionEvent e) {
               String korIme = lf.getjTextFieldKorisnickoIme().getText().trim();
               String lozinka = String.valueOf(lf.getjPasswordFieldLozinka().getPassword()).trim();
               
               if (korIme.isEmpty()) {
                   JOptionPane.showMessageDialog(lf, "Polje za korisnicko ime ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               if (lozinka.isEmpty()) {
                   JOptionPane.showMessageDialog(lf, "Polje za lozinku ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               try {
                   Zaposleni z = Komunikacija.getInstanca().login(korIme, lozinka);
                   if(z == null) {
                       JOptionPane.showMessageDialog(lf, "Sistem ne moze da pronadje zaposlenog po zadatoj vrednosti", "Greska", JOptionPane.ERROR_MESSAGE);
                   } else {
                        Koordinator.getInstanca().setUlogovani(z);
                        JOptionPane.showMessageDialog(lf, "Prijava na sistem uspesna", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        Koordinator.getInstanca().otvoriGlavnuFormu();
                        lf.dispose();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(lf, "Server je pao. Nije moguce obaviti operaciju. Bicete izlogovani sa sistema.", "Greska", JOptionPane.ERROR_MESSAGE);
                    Komunikacija.getInstanca().zatvoriResurse();
                    System.exit(0);
                }
           }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
}
