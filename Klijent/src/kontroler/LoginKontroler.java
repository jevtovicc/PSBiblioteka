/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Zaposleni;
import forme.prijava.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
               
               Komunikacija.getInstanca().konekcija();
               Zaposleni z = Komunikacija.getInstanca().login(korIme, lozinka);
               if(z == null) {
                   JOptionPane.showMessageDialog(lf, "Prijava na sistem neuspesna", "Greska", JOptionPane.ERROR_MESSAGE);
               } else {
                   Koordinator.getInstanca().setUlogovani(z);
                   JOptionPane.showMessageDialog(lf, "Prijava na sistem uspesna", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                   Koordinator.getInstanca().otvoriGlavnuFormu();
                   lf.dispose();
               }
           }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
}
