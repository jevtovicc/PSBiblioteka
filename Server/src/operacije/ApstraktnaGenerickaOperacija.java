/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import repozitorijum.Repozitorijum;
import repozitorijum.db.DBRepozitorijum;
import repozitorijum.db.impl.DBRepozitorijumGenericki;

/**
 *
 * @author filipjevtovic
 */
public abstract class ApstraktnaGenerickaOperacija {
    protected final Repozitorijum broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DBRepozitorijumGenericki(); 
    }
    
    public final void izvrsi(Object obj, String kljuc) throws Exception {
        try {
            preduslovi(obj);
            zapocniTransakciju();
            izvrsiOperaciju(obj, kljuc);
            potvrdiTransakciju();
        } catch(Exception e) {
            ponistiTransakciju();
            throw e;
        } finally {
            // ugasiKonekciju();
        }
    }
    
    protected abstract void preduslovi(Object param) throws Exception;
    protected abstract void izvrsiOperaciju(Object param, String kljuc) throws Exception;
    
    private void zapocniTransakciju() throws Exception {
        ((DBRepozitorijum) broker).connect();
    }
    
    private void potvrdiTransakciju() throws Exception {
        ((DBRepozitorijum) broker).commit();
    }
    
    private void ponistiTransakciju() throws Exception {
        ((DBRepozitorijum) broker).rollback();
    }
    
    private void ugasiKonekciju() throws Exception {
        ((DBRepozitorijum) broker).disconnect();
    }
}
