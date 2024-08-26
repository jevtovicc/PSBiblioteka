/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Autor;
import domen.Knjiga;
import forme.knjiga.IzmeniKnjiguForma;
import forme.model.ModelTabeleAutor;
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
public class IzmeniKnjiguKontroler {
    private final IzmeniKnjiguForma ikforma;
    private final Knjiga knjigaZaIzmenu;
    
    public IzmeniKnjiguKontroler(IzmeniKnjiguForma ikforma, Knjiga knjigaZaIzmenu) {
        this.ikforma = ikforma;
        this.knjigaZaIzmenu = knjigaZaIzmenu;
        addActionListeners();
    }

    private void addActionListeners() {
        ikforma.izmeniAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               izmeni(e);
           }
           
           private void izmeni(ActionEvent e) {
               String nazivKnjige = ikforma.getjTextFieldNazivKnjige().getText().trim();
               String ISBN = ikforma.getjTextFieldISBN().getText().trim();
               
               if (nazivKnjige.isEmpty()) {
                   JOptionPane.showMessageDialog(ikforma, "Polje za naziv knjige ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               if (ISBN.isEmpty()) {
                   JOptionPane.showMessageDialog(ikforma, "Polje za ISBN knjige ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               
               int kolicina = (Integer) ikforma.getjSpinnerKolicina().getValue();
               
               knjigaZaIzmenu.setNazivKnjige(nazivKnjige);
               knjigaZaIzmenu.setISBN(ISBN);
               knjigaZaIzmenu.setKolicina(kolicina);
               
               int red = ikforma.getjTableAutori().getSelectedRow();
               if(red == -1) {
                    JOptionPane.showMessageDialog(ikforma, "Morate izabrati autora", "Greska", JOptionPane.ERROR_MESSAGE);
               } else {
                   ModelTabeleAutor mta = (ModelTabeleAutor) ikforma.getjTableAutori().getModel();
                   Autor a = mta.getLista().get(red);
                   knjigaZaIzmenu.setAutor(a);
                   try {
                        Komunikacija.getInstanca().izmeniKnjigu(knjigaZaIzmenu);
                        JOptionPane.showMessageDialog(ikforma, "Sistem je izmenio podatke o knjizi", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        ikforma.dispose();
                    } catch(IOException ioex) {
                         JOptionPane.showMessageDialog(ikforma, "Server je pao. Nije moguce obaviti operaciju. Bicete izlogovani sa sistema.", "Greska", JOptionPane.ERROR_MESSAGE);
                         Komunikacija.getInstanca().zatvoriResurse();
                         System.exit(0);
                    } catch(Exception ex) {
                         String porukaGreske = ex.getMessage();
                         JOptionPane.showMessageDialog(ikforma, porukaGreske, "Greska", JOptionPane.ERROR_MESSAGE);
                    } 
               }               
           }
        });
        
        ikforma.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = ikforma.getjTextFieldNazivAutora().getText().trim();
                ModelTabeleAutor mta = (ModelTabeleAutor) ikforma.getjTableAutori().getModel();
                mta.pretrazi(naziv);
            }
        });
        
        ikforma.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ikforma.getjTextFieldNazivAutora().setText("");
                osveziFormu();
            }
        });
        
        ikforma.addBtnDodajAutoraActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nazivAutora = ikforma.getjTextFieldNazivAutora().getText().trim();
                Autor a = new Autor();
                a.setImePrezime(nazivAutora);
                try {
                    Komunikacija.getInstanca().dodajAutora(a);
                    JOptionPane.showMessageDialog(ikforma, "Sistem je kreirao autora", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    osveziFormu();
                    selektujAutora((ModelTabeleAutor) ikforma.getjTableAutori().getModel(), a);
                } catch(IOException ioex) {
                     JOptionPane.showMessageDialog(ikforma, "Server je pao. Nije moguce obaviti operaciju. Bicete izlogovani sa sistema.", "Greska", JOptionPane.ERROR_MESSAGE);
                     Komunikacija.getInstanca().zatvoriResurse();
                     System.exit(0);
                } catch(Exception ex) {
                     String porukaGreske = ex.getMessage();
                     JOptionPane.showMessageDialog(ikforma, porukaGreske, "Greska", JOptionPane.ERROR_MESSAGE);
                } 
            }
        });
    }
    
    public void otvoriFormu() {
        pripremiFormu(); // popuni polja
        ikforma.setVisible(true);
    }

    private void pripremiFormu() {
        String nazivKnjige = knjigaZaIzmenu.getNazivKnjige();
        String ISBN = knjigaZaIzmenu.getISBN();
        int kolicina = knjigaZaIzmenu.getKolicina();
        Autor autor = knjigaZaIzmenu.getAutor();
        
        ikforma.getjTextFieldNazivKnjige().setText(nazivKnjige);
        ikforma.getjTextFieldISBN().setText(ISBN);
        ikforma.getjSpinnerKolicina().setValue(kolicina);
        
        List<Autor> autori;
        try {
            autori = komunikacija.Komunikacija.getInstanca().ucitajAutore();
            ModelTabeleAutor mta = new ModelTabeleAutor(autori);
            ikforma.getjTableAutori().setModel(mta);    
            selektujAutora(mta, autor);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(ikforma, "Server je pao. Nije moguce obaviti operaciju. Bicete izlogovani sa sistema.", "Greska", JOptionPane.ERROR_MESSAGE);
            Komunikacija.getInstanca().zatvoriResurse();
            System.exit(0);
        }
    }
    
    private void osveziFormu() {
        List<Autor> autori;
        try {
            autori = komunikacija.Komunikacija.getInstanca().ucitajAutore();
            ModelTabeleAutor mta = new ModelTabeleAutor(autori);
            ikforma.getjTableAutori().setModel(mta);    
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(ikforma, "Server je pao. Nije moguce obaviti operaciju. Bicete izlogovani sa sistema.", "Greska", JOptionPane.ERROR_MESSAGE);
            Komunikacija.getInstanca().zatvoriResurse();
            System.exit(0);
        }
    }
    
    private void selektujAutora(ModelTabeleAutor mta, Autor autor) {
        ikforma.getjTextFieldNazivAutora().setText(autor.getImePrezime());
        mta.pretrazi(autor.getImePrezime());
        int red = 0;
        ikforma.getjTableAutori().setRowSelectionInterval(red, red);
    }
}
