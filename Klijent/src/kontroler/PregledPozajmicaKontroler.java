/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Knjiga;
import domen.Pozajmica;
import forme.model.ModelTabeleKnjiga;
import forme.model.ModelTabelePozajmica;
import forme.pozajmica.PregledPozajmicaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author filipjevtovic
 */
public class PregledPozajmicaKontroler {
    private PregledPozajmicaForma ppf;

    public PregledPozajmicaKontroler(PregledPozajmicaForma ppf) {
        this.ppf = ppf;
        addActionListeners();
    }
    
    private void addActionListeners() {
        
        ppf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imePrezimeClana = ppf.getjTextFieldImePrezimeClana().getText().trim();
                String nazivKnjige = ppf.getjTextFieldNazivKnjige().getText().trim();
                ModelTabelePozajmica mtp = (ModelTabelePozajmica) ppf.getjTablePozajmice().getModel();
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
        List<Pozajmica> pozajmice = komunikacija.Komunikacija.getInstanca().ucitajPozajmice();
        ModelTabelePozajmica mtp = new ModelTabelePozajmica(pozajmice);
        ppf.getjTablePozajmice().setModel(mtp);
    }
}
