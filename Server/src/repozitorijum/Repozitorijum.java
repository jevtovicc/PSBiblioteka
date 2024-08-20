  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum;

import java.util.List;

/**
 *
 * @author filipjevtovic
 */
public interface Repozitorijum<T> {
    List<T> vratiSve();
    List<T> vratiSve(T param, String uslov) throws Exception;
    void kreiraj(T param) throws Exception;
    void izmeni(T param) throws Exception;
    void obrisi(T param) throws Exception;
}
