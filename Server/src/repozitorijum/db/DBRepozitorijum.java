/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum.db;

import repozitorijum.Repozitorijum;

/**
 *
 * @author filipjevtovic
 */
public interface DBRepozitorijum<T> extends Repozitorijum<T> {
    
    default public void connect() throws Exception {
        KonekcijaBPFactory.getInstanca().getKonekcija();
    }
    
    default public void disconnect() throws Exception {
        KonekcijaBPFactory.getInstanca().getKonekcija().close();
    }
    
    default public void commit() throws Exception {
        KonekcijaBPFactory.getInstanca().getKonekcija().commit();
    }
    
    default public void rollback() throws Exception {
        KonekcijaBPFactory.getInstanca().getKonekcija().rollback();
    }
}
