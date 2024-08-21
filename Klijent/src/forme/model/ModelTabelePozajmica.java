/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Pozajmica;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author filipjevtovic
 */
public class ModelTabelePozajmica extends AbstractTableModel {
    List<Pozajmica> lista;
    String[] kolone = {"ID", "Ime i Prezime", "Knjiga", "Datum izajmljivanja", "Datum vracanja"};
    
    public ModelTabelePozajmica(List<Pozajmica> lista) {
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
        Pozajmica p = lista.get(rowIndex);
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(outputPattern);
        String datumPozajmiceString = sdf.format(p.getDatumPozajmice());
        String datumVracanjaString = p.getDatumVracanja() == null ? null : sdf.format(p.getDatumVracanja());
        
        switch (columnIndex) {
            case 0: return p.getPozajmicaID();
            case 1: return p.getClan().getImePrezime();
            case 2: return p.getKnjiga().getNazivKnjige();
            case 3: return datumPozajmiceString;
            case 4: return datumVracanjaString;
            default: return "NA";
        }   
    }

    public List<Pozajmica> getLista() {
        return lista;
    }

    public void pretrazi(String imePrezimeClana, String nazivKnjige) {
        this.lista = lista.stream()
                .filter(p -> p.getClan().getImePrezime().toLowerCase().contains(imePrezimeClana.toLowerCase()))
                .filter(p -> p.getKnjiga().getNazivKnjige().toLowerCase().contains(nazivKnjige.toLowerCase()))
                .collect(Collectors.toList());
        
        this.fireTableDataChanged();
    }
}
