/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Knjiga;
import domen.Zaduzenje;
import forme.model.ModelTabeleKnjiga;
import forme.model.ModelTabeleZaduzenje;
import forme.zaduzenje.PregledZaduzenjaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
    }
    
    public void otvoriFormu() {
        pripremiFormu(); // ucitaj sve pozajmice
        ppf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Zaduzenje> pozajmice = komunikacija.Komunikacija.getInstanca().ucitajPozajmice();
        ModelTabeleZaduzenje mtp = new ModelTabeleZaduzenje(pozajmice);
        ppf.getjTablePozajmice().setModel(mtp);
    }
}
