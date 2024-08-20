/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Zaposleni;
import forme.GlavnaForma;
import koordinator.Koordinator;

/**
 *
 * @author filipjevtovic
 */
public class GlavnaFormaKontroler {
    private final GlavnaForma gf;
    
    public GlavnaFormaKontroler(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    private void addActionListeners() {
        // TODO
    }

    public void otvoriFormu() {
        Zaposleni ulogovani = Koordinator.getInstanca().getUlogovani();
        String imePrezime = ulogovani.getImePrezime();
        gf.setVisible(true);
        gf.getjLabelUlogovani().setText(imePrezime);
    }
}
