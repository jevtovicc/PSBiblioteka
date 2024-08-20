/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Autor;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author filipjevtovic
 */
public class ModelTabeleAutor extends AbstractTableModel {
    List<Autor> lista;
    String[] kolone = {"ID", "Ime i Prezime"};
    
    public ModelTabeleAutor(List<Autor> lista) {
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
        Autor a = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return a.getAutorID();
            case 1: return a.getImePrezime();
            default: return "NA";
        }   
    }

    public List<Autor> getLista() {
        return lista;
    }

    public void pretrazi(String nazivAutora) {
        this.lista = lista.stream()
                .filter(a -> a.getImePrezime().toLowerCase().contains(nazivAutora.toLowerCase()))
                .collect(Collectors.toList());
        
        this.fireTableDataChanged();
    }
}
