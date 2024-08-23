/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Zaduzenje;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author filipjevtovic
 */
public class ModelTabeleZaduzenje extends AbstractTableModel {
    List<Zaduzenje> lista;
    String[] kolone = {"ID", "Ime i Prezime", "Knjiga", "Datum zaduzenja", "Datum razduzenja"};
    
    public ModelTabeleZaduzenje(List<Zaduzenje> lista) {
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
        Zaduzenje p = lista.get(rowIndex);
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(outputPattern);
        String datumZaduzenjaString = sdf.format(p.getDatumZaduzenja());
        String datumRazduzenjaString = p.getDatumRazduzenja() == null ? null : sdf.format(p.getDatumRazduzenja());
        
        switch (columnIndex) {
            case 0: return p.getZaduzenjeID();
            case 1: return p.getClan().getImePrezime();
            case 2: return p.getKnjiga().getNazivKnjige();
            case 3: return datumZaduzenjaString;
            case 4: return datumRazduzenjaString;
            default: return "NA";
        }   
    }

    public List<Zaduzenje> getLista() {
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
