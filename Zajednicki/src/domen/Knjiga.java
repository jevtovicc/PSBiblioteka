/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.ResultSet;

/**
 *
 * @author filipjevtovic
 */
public class Knjiga implements ApstraktniDomenskiObjekat {
    private long knjigaID;
    private String nazivKnjige;
    private String ISBN;
    private int kolicina;
    private Autor autor;

    public Knjiga() {
        
    }

    public Knjiga(long knjigaID, String nazivKnjige, String ISBN, int kolicina, Autor autor) {
        this.knjigaID = knjigaID;
        this.nazivKnjige = nazivKnjige;
        this.ISBN = ISBN;
        this.kolicina = kolicina;
        this.autor = autor;
    }

    public long getKnjigaID() {
        return knjigaID;
    }

    public void setKnjigaID(long knjigaID) {
        this.knjigaID = knjigaID;
    }

    public String getNazivKnjige() {
        return nazivKnjige;
    }

    public void setNazivKnjige(String nazivKnjige) {
        this.nazivKnjige = nazivKnjige;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    } 

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        return nazivKnjige;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (int) (this.knjigaID ^ (this.knjigaID >>> 32));
        hash = 61 * hash + Objects.hashCode(this.nazivKnjige);
        hash = 61 * hash + Objects.hashCode(this.ISBN);
        hash = 61 * hash + Objects.hashCode(this.autor);
        hash = 61 * hash + Objects.hashCode(this.kolicina);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Knjiga other = (Knjiga) obj;
        if (this.knjigaID != other.knjigaID) {
            return false;
        }
        if (!Objects.equals(this.nazivKnjige, other.nazivKnjige)) {
            return false;
        }
        if (!Objects.equals(this.ISBN, other.ISBN)) {
            return false;
        }
        if (this.kolicina != other.kolicina) {
            return false;
        }
        return Objects.equals(this.autor, other.autor);
    }

    @Override
    public String vratiNazivTabele() {
        return "Knjiga";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while(rs.next()) {
            long knjigaID = rs.getLong("knjigaID");
            String nazivKnjige = rs.getString("nazivKnjige");
            String ISBN = rs.getString("ISBN");
            long autorID = rs.getLong("autorID");
            int kolicina = rs.getInt("kolicina");
            
            // Todo: Kreirati autora
            String imePrezimeAutora = rs.getString("imePrezime");
            Autor a = new Autor(autorID, imePrezimeAutora);
            
            Knjiga k = new Knjiga(knjigaID, nazivKnjige, ISBN, kolicina, a);
            lista.add(k);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivKnjige, ISBN, kolicina, autorID";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + this.nazivKnjige + "','" + this.ISBN + "', " + this.kolicina + ", " + this.autor.getAutorID();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "knjigaID=" + knjigaID;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "nazivKnjige='" + this.nazivKnjige + "', ISBN='" + this.ISBN + "', autorID=" + this.autor.getAutorID() + ", kolicina=" + this.kolicina;
    }

    @Override
    public void postaviGenerisaniID(long generisaniID) {
        this.knjigaID = generisaniID;
    }

}
