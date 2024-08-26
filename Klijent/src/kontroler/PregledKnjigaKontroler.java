/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Clan;
import domen.Knjiga;
import forme.knjiga.PregledKnjigaForma;
import forme.model.ModelTabeleClan;
import forme.model.ModelTabeleKnjiga;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author filipjevtovic
 */
public class PregledKnjigaKontroler {
    
    private PregledKnjigaForma pkf;

    public PregledKnjigaKontroler(PregledKnjigaForma pkf) {
        this.pkf = pkf;
        addActionListeners();
    }
    
    private void addActionListeners() {
        pkf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKnjige().getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(pkf, "Morate izabrati knjigu za brisanje", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleKnjiga mtk = (ModelTabeleKnjiga) pkf.getjTableKnjige().getModel();
                    Knjiga k = mtk.getLista().get(red);
                    try {
                        Komunikacija.getInstanca().obrisiKnjigu(k);
                        JOptionPane.showMessageDialog(pkf, "Sistem je obrisao knjigu", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu(); // Videti drugi nacin, jer se ovako ponovo vrsi poziv do baze
                    } catch(IOException ioex) {
                        JOptionPane.showMessageDialog(pkf, "Server je pao. Nije moguce obaviti operaciju. Bicete izlogovani sa sistema.", "Greska", JOptionPane.ERROR_MESSAGE);
                        Komunikacija.getInstanca().zatvoriResurse();
                        System.exit(0);
                   } catch(Exception ex) {
                        String porukaGreske = ex.getMessage();
                        JOptionPane.showMessageDialog(pkf, porukaGreske, "Greska", JOptionPane.ERROR_MESSAGE);
                   } 
                }
            }
            
        });
        
        pkf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKnjige().getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(pkf, "Morate izabrati knjigu za izmenu", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleKnjiga mtk = (ModelTabeleKnjiga) pkf.getjTableKnjige().getModel();
                    Knjiga knjigaZaIzmemu = mtk.getLista().get(red);
                    
                    Koordinator.getInstanca().otvoriIzmeniKnjiguFormu(knjigaZaIzmemu);
                }
            }
            
        });
        
        pkf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = pkf.getjTextFieldNazivKnjige().getText().trim();
                ModelTabeleKnjiga mtk = (ModelTabeleKnjiga) pkf.getjTableKnjige().getModel();
                mtk.pretrazi(naziv);
            }
        });
        
        pkf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pkf.getjTextFieldNazivKnjige().setText("");
                pripremiFormu();
            }
        });
    }
    
    public void otvoriFormu() {
        pripremiFormu(); // ucitaj sve knjige
        pkf.setVisible(true);
    }

    public void pripremiFormu() {
        try {
            List<Knjiga> knjige = komunikacija.Komunikacija.getInstanca().ucitajKnjige();
            ModelTabeleKnjiga mtk = new ModelTabeleKnjiga(knjige);
            pkf.getjTableKnjige().setModel(mtk);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(pkf, "Server je pao. Nije moguce obaviti operaciju. Bicete izlogovani sa sistema.", "Greska", JOptionPane.ERROR_MESSAGE);
            Komunikacija.getInstanca().zatvoriResurse();
            System.exit(0);
        }
    }
}
