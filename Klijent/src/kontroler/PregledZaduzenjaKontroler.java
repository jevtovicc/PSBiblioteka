/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Zaduzenje;
import forme.model.ModelTabeleKnjiga;
import forme.model.ModelTabeleZaduzenje;
import forme.zaduzenje.PregledZaduzenjaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author filipjevtovic
 */
public class PregledZaduzenjaKontroler {
    private PregledZaduzenjaForma ppf;

    public PregledZaduzenjaKontroler(PregledZaduzenjaForma ppf) {
        this.ppf = ppf;
        addActionListeners();
    }
    
    private void addActionListeners() {
        
        ppf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imePrezimeClana = ppf.getjTextFieldImePrezimeClana().getText().trim();
                String nazivKnjige = ppf.getjTextFieldNazivKnjige().getText().trim();
                ModelTabeleZaduzenje mtp = (ModelTabeleZaduzenje) ppf.getjTablePozajmice().getModel();
                mtp.pretrazi(imePrezimeClana, nazivKnjige);
                if (mtp.getLista().isEmpty()) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne moze da nadje zaduzenja po zadatoj vrednosti", "", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ppf, "Sistem je nasao zaduzenja po zadatoj vrednosti", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        ppf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ppf.getjTextFieldNazivKnjige().setText("");
                ppf.getjTextFieldImePrezimeClana().setText("");
                pripremiFormu();
            }
        });
        
        ppf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppf.getjTablePozajmice().getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(ppf, "Morate izabrati zaduzenje za brisanje", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleZaduzenje mtz = (ModelTabeleZaduzenje) ppf.getjTablePozajmice().getModel();
                    Zaduzenje z = mtz.getLista().get(red);
                    try {
                        Komunikacija.getInstanca().obrisiZaduzenje(z);
                        JOptionPane.showMessageDialog(ppf, "Sistem je obrisao zaduzenje", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu(); // Videti drugi nacin, jer se ovako ponovo vrsi poziv do baze
                    } catch(IOException ioex) {
                        JOptionPane.showMessageDialog(ppf, "Server je pao. Nije moguce obaviti operaciju. Bicete izlogovani sa sistema.", "Greska", JOptionPane.ERROR_MESSAGE);
                        Komunikacija.getInstanca().zatvoriResurse();
                        System.exit(0);
                   } catch(Exception ex) {
                        String porukaGreske = ex.getMessage();
                        JOptionPane.showMessageDialog(ppf, "Sistem ne moze da obrise zaduzenje", "Greska", JOptionPane.ERROR_MESSAGE);
                   } 
                }
            }
            
        });
        
        
         ppf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppf.getjTablePozajmice().getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(ppf, "Morate izabrati zaduzenje za izmenu", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleZaduzenje mtz = (ModelTabeleZaduzenje) ppf.getjTablePozajmice().getModel();
                    Zaduzenje zaduzenjeZaIzmenu = mtz.getLista().get(red);
                    try {
                        Komunikacija.getInstanca().izmeniZaduzenje(zaduzenjeZaIzmenu);
                        JOptionPane.showMessageDialog(ppf, "Sistem je zapamtio zaduzenje", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    } catch(IOException ioex) {
                         JOptionPane.showMessageDialog(ppf, "Server je pao. Nije moguce obaviti operaciju. Bicete izlogovani sa sistema.", "Greska", JOptionPane.ERROR_MESSAGE);
                         Komunikacija.getInstanca().zatvoriResurse();
                         System.exit(0);
                     } catch(Exception ex) {
                         String porukaGreske = ex.getMessage();
                         JOptionPane.showMessageDialog(ppf, "Sistem ne moze da zapamti zaduzenje", "Greska", JOptionPane.ERROR_MESSAGE);
                     } 
                }
                
                
            }
            
        });
    }
    
    public void otvoriFormu() {
        pripremiFormu(); // ucitaj sve pozajmice
        ppf.setVisible(true);
    }

    public void pripremiFormu() {
        try {
            List<Zaduzenje> pozajmice = komunikacija.Komunikacija.getInstanca().ucitajPozajmice();
            ModelTabeleZaduzenje mtp = new ModelTabeleZaduzenje(pozajmice);
            ppf.getjTablePozajmice().setModel(mtp);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(ppf, "Server je pao. Nije moguce obaviti operaciju. Bicete izlogovani sa sistema.", "Greska", JOptionPane.ERROR_MESSAGE);
            Komunikacija.getInstanca().zatvoriResurse();
            System.exit(0);
        }
    }
}
