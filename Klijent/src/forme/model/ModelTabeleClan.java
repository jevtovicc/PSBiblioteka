/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Clan;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author filipjevtovic
 */
public class ModelTabeleClan extends AbstractTableModel {

    List<Clan> lista;
    String[] kolone = {"ID", "Ime i Prezime", "Adresa Stanovanja", "Datum uclanjenja", "Datum isteka clanarine"};
    
    public ModelTabeleClan(List<Clan> lista) {
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
        Clan c = lista.get(rowIndex);
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(outputPattern);
        String datumUclanjenjaString = sdf.format(c.getClanskaKarta().getDatumUclanjenja());
        String datumIstekaString = sdf.format(c.getClanskaKarta().getDatumIsteka());
        
        switch (columnIndex) {
            case 0: return c.getClanID();
            case 1: return c.getImePrezime();
            case 2: return c.getAdresaStanovanja();
            case 3: return datumUclanjenjaString;
            case 4: return datumIstekaString;
            default: return "NA";
        }   
    }

    public List<Clan> getLista() {
        return lista;
    }

    public void pretrazi(String imePrezime) {
        this.lista = lista.stream()
                .filter(c -> c.getImePrezime().toLowerCase().contains(imePrezime.toLowerCase()))
                .collect(Collectors.toList());
        
        this.fireTableDataChanged();
    }
}
