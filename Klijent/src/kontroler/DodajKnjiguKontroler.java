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
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
               
               if (nazivKnjige.isEmpty()) {
                   JOptionPane.showMessageDialog(dkforma, "Polje za naziv knjige ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               if (ISBN.isEmpty()) {
                   JOptionPane.showMessageDialog(dkforma, "Polje za ISBN knjige ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
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
                   } catch(IOException ioex) {
                        JOptionPane.showMessageDialog(dkforma, "Server je pao. Nije moguce obaviti operaciju. Bicete izlogovani sa sistema.", "Greska", JOptionPane.ERROR_MESSAGE);
                        Komunikacija.getInstanca().zatvoriResurse();
                        System.exit(0);
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
        
        dkforma.addBtnDodajAutoraActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nazivAutora = dkforma.getjTextFieldNazivAutora().getText().trim();
                Autor a = new Autor();
                a.setImePrezime(nazivAutora);
                try {
                    Komunikacija.getInstanca().dodajAutora(a);
                    JOptionPane.showMessageDialog(dkforma, "Sistem je kreirao autora", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    osveziFormu();
                    selektujAutora((ModelTabeleAutor) dkforma.getjTableAutori().getModel(), a);
                } catch(IOException ioex) {
                     JOptionPane.showMessageDialog(dkforma, "Server je pao. Nije moguce obaviti operaciju. Bicete izlogovani sa sistema.", "Greska", JOptionPane.ERROR_MESSAGE);
                     Komunikacija.getInstanca().zatvoriResurse();
                     System.exit(0);
                } catch(Exception ex) {
                     String porukaGreske = ex.getMessage();
                     JOptionPane.showMessageDialog(dkforma, porukaGreske, "Greska", JOptionPane.ERROR_MESSAGE);
                } 
            }
        });
    }

    public void otvoriFormu() {
        pripremiFormu(); // ucitaj sve autore
        dkforma.setVisible(true);
    }
    
    public void pripremiFormu() {
        List<Autor> autori;
        try {
            autori = komunikacija.Komunikacija.getInstanca().ucitajAutore();
            ModelTabeleAutor mta = new ModelTabeleAutor(autori);
            dkforma.getjTableAutori().setModel(mta);            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(dkforma, "Server je pao. Nije moguce obaviti operaciju. Bicete izlogovani sa sistema.", "Greska", JOptionPane.ERROR_MESSAGE);
            Komunikacija.getInstanca().zatvoriResurse();
            System.exit(0);
        }
        
    }
    
    private void osveziFormu() {
        List<Autor> autori;
        try {
            autori = komunikacija.Komunikacija.getInstanca().ucitajAutore();
            ModelTabeleAutor mta = new ModelTabeleAutor(autori);
            dkforma.getjTableAutori().setModel(mta);    
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(dkforma, "Server je pao. Nije moguce obaviti operaciju. Bicete izlogovani sa sistema.", "Greska", JOptionPane.ERROR_MESSAGE);
            Komunikacija.getInstanca().zatvoriResurse();
            System.exit(0);
        }
    }
    
    private void selektujAutora(ModelTabeleAutor mta, Autor autor) {
        dkforma.getjTextFieldNazivAutora().setText(autor.getImePrezime());
        mta.pretrazi(autor.getImePrezime());
        int red = 0;
        dkforma.getjTableAutori().setRowSelectionInterval(red, red);
    }
}
