/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Knjiga;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author filipjevtovic
 */
public class ModelTabeleKnjiga extends AbstractTableModel {
    List<Knjiga> lista;
    String[] kolone = {"ID", "Naziv", "ISBN", "Autor", "Kolicina"};
    
    public ModelTabeleKnjiga(List<Knjiga> lista) {
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
        Knjiga k = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return k.getKnjigaID();
            case 1: return k.getNazivKnjige();
            case 2: return k.getISBN();
            case 3: return k.getAutor().getImePrezime();
            case 4: return k.getKolicina();
            default: return "NA";
        }   
    }

    public List<Knjiga> getLista() {
        return lista;
    }

    public void pretrazi(String nazivKnjige) {
        this.lista = lista.stream()
                .filter(k -> k.getNazivKnjige().toLowerCase().contains(nazivKnjige.toLowerCase()))
                .collect(Collectors.toList());
        
        this.fireTableDataChanged();
    }
}
