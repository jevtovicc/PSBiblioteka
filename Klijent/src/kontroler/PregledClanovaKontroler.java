/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Clan;
import forme.clan.PregledClanovaForma;
import forme.model.ModelTabeleClan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author filipjevtovic
 */
public class PregledClanovaKontroler {
    
    private final PregledClanovaForma pcforma;
    
    public PregledClanovaKontroler(PregledClanovaForma pcforma) {
        this.pcforma = pcforma;
        addActionListeners();
    }

    private void addActionListeners() {
        pcforma.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pcforma.getjTableClanovi().getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(pcforma, "Sistem ne moze da obrise clana", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleClan mtc = (ModelTabeleClan) pcforma.getjTableClanovi().getModel();
                    Clan c = mtc.getLista().get(red);
                    try {
                        Komunikacija.getInstanca().obrisiClana(c);
                        JOptionPane.showMessageDialog(pcforma, "Sistem je obrisao clana", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu(); // Videti drugi nacin, jer se ovako ponovo vrsi poziv do baze
                    } catch(Exception ex) {
                        String porukaGreske = ex.getMessage();
                        JOptionPane.showMessageDialog(pcforma, porukaGreske, "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
        });
        
        pcforma.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pcforma.getjTableClanovi().getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(pcforma, "Morate izabrati clana za izmenu", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleClan mtc = (ModelTabeleClan) pcforma.getjTableClanovi().getModel();
                    Clan clanZaIzmenu = mtc.getLista().get(red);
                    
                    Koordinator.getInstanca().otvoriIzmeniClanaFormu(clanZaIzmenu);
                }
            }
            
        });
        
        pcforma.addBtnPretraziActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String imePrezime = pcforma.getjTextFieldIme().getText().trim();
                ModelTabeleClan mtc = (ModelTabeleClan) pcforma.getjTableClanovi().getModel();
                mtc.pretrazi(imePrezime);
            }
        });
        
        pcforma.addBtnResetujActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                pcforma.getjTextFieldIme().setText("");
                pripremiFormu();
            }
        });
    }

    public void otvoriFormu() {
        pripremiFormu(); // ucitaj sve clanove
        pcforma.setVisible(true);
    }

    public void pripremiFormu() {
        List<Clan> clanovi = komunikacija.Komunikacija.getInstanca().ucitajClanove();
        ModelTabeleClan mtc = new ModelTabeleClan(clanovi);
        pcforma.getjTableClanovi().setModel(mtc);
    }
    
}
