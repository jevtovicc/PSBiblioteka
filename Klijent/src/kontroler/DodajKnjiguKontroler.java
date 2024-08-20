/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Autor;
import domen.Knjiga;
import forme.knjiga.DodajKnjiguForma;
import forme.model.ModelTabeleAutor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author filipjevtovic
 */
public class DodajKnjiguKontroler {
    private final DodajKnjiguForma dkforma;
    
    public DodajKnjiguKontroler(DodajKnjiguForma dkforma) {
        this.dkforma = dkforma;
        addActionListeners();
    }

    private void addActionListeners() {
        
        dkforma.dodajAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               dodaj(e);
           }
           
           private void dodaj(ActionEvent e) {
               String nazivKnjige = dkforma.getjTextFieldNazivKnjige().getText().trim();
               String ISBN = dkforma.getjTextFieldISBN().getText().trim();
               
               int kolicina = (Integer) dkforma.getjSpinnerKolicina().getValue();
               
               int red = dkforma.getjTableAutori().getSelectedRow();
               if(red == -1) {
                    JOptionPane.showMessageDialog(dkforma, "Morate izabrati autora", "Greska", JOptionPane.ERROR_MESSAGE);
               } else {
                   ModelTabeleAutor mta = (ModelTabeleAutor) dkforma.getjTableAutori().getModel();
                   Autor a = mta.getLista().get(red);
                   
                   Knjiga k = new Knjiga();
                   k.setNazivKnjige(nazivKnjige);
                   k.setISBN(ISBN);
                   k.setKolicina(kolicina);
                   k.setAutor(a);
                   
                   try {
                        Komunikacija.getInstanca().dodajKnjigu(k);
                        JOptionPane.showMessageDialog(dkforma, "Sistem je kreirao knjigu", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        dkforma.dispose();
                    } catch(Exception ex) {
                        String porukaGreske = ex.getMessage();
                        JOptionPane.showMessageDialog(dkforma, porukaGreske, "Greska", JOptionPane.ERROR_MESSAGE);
                    } 
               }
           }
        });
        
        dkforma.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = dkforma.getjTextFieldNazivAutora().getText().trim();
                ModelTabeleAutor mta = (ModelTabeleAutor) dkforma.getjTableAutori().getModel();
                mta.pretrazi(naziv);
            }
        });
        
        dkforma.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dkforma.getjTextFieldNazivAutora().setText("");
                pripremiFormu();
            }
        });
    }

    public void otvoriFormu() {
        pripremiFormu(); // ucitaj sve autore
        dkforma.setVisible(true);
    }
    
    public void pripremiFormu() {
        List<Autor> autori = komunikacija.Komunikacija.getInstanca().ucitajAutore();
        ModelTabeleAutor mta = new ModelTabeleAutor(autori);
        dkforma.getjTableAutori().setModel(mta);
    }
}
