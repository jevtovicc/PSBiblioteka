/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import domen.Zaposleni;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author filipjevtovic
 */
public class ModelTabeleZaposleni extends AbstractTableModel {

   private List<Zaposleni> lista;
   private String[] kolone = {"ID", "Ime i Prezime", "Korisnicko ime"};
    
    public ModelTabeleZaposleni(List<Zaposleni> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zaposleni z = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return z.getZaposleniID();
            case 1: return z.getImePrezime();
            case 2: return z.getKorisnickoIme();
            default: return "NA";
        }   
    }

    public List<Zaposleni> getLista() {
        return lista;
    }    
}
