/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Clan;
import domen.Knjiga;
import domen.Pozajmica;
import forme.model.ModelTabeleClan;
import forme.model.ModelTabeleKnjiga;
import forme.pozajmica.DodajPozajmicuForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author filipjevtovic
 */
public class DodajPozajmicuKontroler {
    private final DodajPozajmicuForma dpf;
    
    public DodajPozajmicuKontroler(DodajPozajmicuForma dpf) {
        this.dpf = dpf;
        addActionListeners();
    }
    
    private void addActionListeners() {
        dpf.dodajAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               dodaj(e);
           }
           
           private void dodaj(ActionEvent e) {   
               Pozajmica p = new Pozajmica();
               
               String datumIznajmljivanjaString = dpf.getjTextFieldDatumPozajmice().getText().trim();
               String datumVracanjaString = dpf.getjTextFieldDatumVracanja().getText().trim();
               
               if (datumIznajmljivanjaString.isEmpty()) {
                   JOptionPane.showMessageDialog(dpf, "Polje za datum pozajmice ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               } 
               
               SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
               try {                   
                   Date datumIznajmljivanja = formatter.parse(datumIznajmljivanjaString);
                   p.setDatumPozajmice(datumIznajmljivanja);
               } catch (ParseException pex) {
                   JOptionPane.showMessageDialog(dpf, "Datum pozajmice mora biti u odgovarajucem formatu", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               if (datumVracanjaString == null || datumVracanjaString.isEmpty()) {
                   p.setDatumVracanja(null);
               } else {
                   try {                   
                       Date datumVracanja = formatter.parse(datumVracanjaString);
                       p.setDatumVracanja(datumVracanja);
                   } catch (ParseException pex) {
                       JOptionPane.showMessageDialog(dpf, "Datum vracanja mora biti u odgovarajucem formatu", "Greska", JOptionPane.ERROR_MESSAGE);
                       return;
                   }
               }

               int redClana = dpf.getjTableClanovi().getSelectedRow();
               if(redClana == -1) {
                    JOptionPane.showMessageDialog(dpf, "Morate izabrati clana", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
               } else {
                   ModelTabeleClan mtc = (ModelTabeleClan) dpf.getjTableClanovi().getModel();
                   Clan c = mtc.getLista().get(redClana);
                   p.setClan(c);
               }
               
               int redKnjige = dpf.getjTableKnjige().getSelectedRow();
               if(redKnjige == -1) {
                    JOptionPane.showMessageDialog(dpf, "Morate izabrati knjigu", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
               } else {
                   ModelTabeleKnjiga mtk = (ModelTabeleKnjiga) dpf.getjTableKnjige().getModel();
                   Knjiga k = mtk.getLista().get(redKnjige);
                   p.setKnjiga(k);
               }               
               
               
               try {
                    Komunikacija.getInstanca().dodajPozajmicu(p);
                    JOptionPane.showMessageDialog(dpf, "Sistem je kreirao pozajmicu", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    dpf.dispose();
                } catch(Exception ex) {
                    String porukaGreske = ex.getMessage();
                    JOptionPane.showMessageDialog(dpf, porukaGreske, "Greska", JOptionPane.ERROR_MESSAGE);
                } 
           }
        });
        
        dpf.addBtnPretraziClanaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = dpf.getjTextFieldImePrezime().getText().trim();
                ModelTabeleClan mtc = (ModelTabeleClan) dpf.getjTableClanovi().getModel();
                mtc.pretrazi(naziv);
            }
        });
        
        dpf.addBtnResetujClanoveActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dpf.getjTextFieldImePrezime().setText("");
                pripremiFormu();
            }
        });
        
        dpf.addBtnPretraziKnjiguActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = dpf.getjTextFieldNazivKnjige().getText().trim();
                ModelTabeleKnjiga mtk = (ModelTabeleKnjiga) dpf.getjTableKnjige().getModel();
                mtk.pretrazi(naziv);
            }
        });
        
        dpf.addBtnResetujKnjigeActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dpf.getjTextFieldNazivKnjige().setText("");
                pripremiFormu();
            }
        });
    }
    
    public void otvoriFormu() {
        pripremiFormu(); // ucitaj sve autore
        dpf.setVisible(true);
    }
    
    public void pripremiFormu() {
        List<Clan> clanovi = komunikacija.Komunikacija.getInstanca().ucitajClanove();
        List<Knjiga> knjige = komunikacija.Komunikacija.getInstanca().ucitajKnjige();
        ModelTabeleClan mtc = new ModelTabeleClan(clanovi);
        ModelTabeleKnjiga mtk = new ModelTabeleKnjiga(knjige);
        dpf.getjTableClanovi().setModel(mtc);
        dpf.getjTableKnjige().setModel(mtk);
    }
}
