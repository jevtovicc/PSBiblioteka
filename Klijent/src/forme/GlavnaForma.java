/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forme;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import koordinator.Koordinator;

/**
 *
 * @author filipjevtovic
 */
public class GlavnaForma extends javax.swing.JFrame {

    /**
     * Creates new form GlavnaForma
     */
    public GlavnaForma() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onExit();
            }
        });
    }
    
    private void onExit() {
        try {
            Koordinator.getInstanca().zatvoriAplikaciju();
            JOptionPane.showMessageDialog(this, "Odjavljeni ste sa sistema.", "Odjava", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Greska prilikom slanja zahteva za kraj rada. Bicete nasilno diskonektovani", "Greska", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabelUlogovani = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemDodajClana = new javax.swing.JMenuItem();
        jMenuItemPregledClana = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemDodajKnjigu = new javax.swing.JMenuItem();
        jMenuItemPregledKnjiga = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemDodajZaduzenje = new javax.swing.JMenuItem();
        jMenuItemPregledZaduzenja = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Dobrodosli");

        jLabelUlogovani.setText("jLabel2");

        jMenu1.setText("Clan");

        jMenuItemDodajClana.setText("Dodaj");
        jMenuItemDodajClana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDodajClanaActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemDodajClana);

        jMenuItemPregledClana.setText("Pregled");
        jMenuItemPregledClana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPregledClanaActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemPregledClana);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Knjiga");

        jMenuItemDodajKnjigu.setText("Dodaj");
        jMenuItemDodajKnjigu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDodajKnjiguActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemDodajKnjigu);

        jMenuItemPregledKnjiga.setText("Pregled");
        jMenuItemPregledKnjiga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPregledKnjigaActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemPregledKnjiga);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Zaduzenje");

        jMenuItemDodajZaduzenje.setText("Dodaj");
        jMenuItemDodajZaduzenje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDodajZaduzenjeActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemDodajZaduzenje);

        jMenuItemPregledZaduzenja.setText("Pregled");
        jMenuItemPregledZaduzenja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPregledZaduzenjaActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemPregledZaduzenja);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelUlogovani, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelUlogovani))
                .addContainerGap(222, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemPregledClanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPregledClanaActionPerformed
        Koordinator.getInstanca().otvoriPregledClanovaFormu();
    }//GEN-LAST:event_jMenuItemPregledClanaActionPerformed

    private void jMenuItemDodajClanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDodajClanaActionPerformed
        Koordinator.getInstanca().otvoriDodajClanaFormu();
    }//GEN-LAST:event_jMenuItemDodajClanaActionPerformed

    private void jMenuItemDodajKnjiguActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDodajKnjiguActionPerformed
        Koordinator.getInstanca().otvoriDodajKnjiguFormu();
    }//GEN-LAST:event_jMenuItemDodajKnjiguActionPerformed

    private void jMenuItemPregledKnjigaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPregledKnjigaActionPerformed
        Koordinator.getInstanca().otvoriPregledKnjigaFormu();
    }//GEN-LAST:event_jMenuItemPregledKnjigaActionPerformed

    private void jMenuItemPregledZaduzenjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPregledZaduzenjaActionPerformed
        Koordinator.getInstanca().otvoriPregledPozajmicaFormu();
    }//GEN-LAST:event_jMenuItemPregledZaduzenjaActionPerformed

    private void jMenuItemDodajZaduzenjeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDodajZaduzenjeActionPerformed
        Koordinator.getInstanca().otvoriDodajPozajmicuFormu();
    }//GEN-LAST:event_jMenuItemDodajZaduzenjeActionPerformed

    public JLabel getjLabelUlogovani() {
        return jLabelUlogovani;
    }    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelUlogovani;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemDodajClana;
    private javax.swing.JMenuItem jMenuItemDodajKnjigu;
    private javax.swing.JMenuItem jMenuItemDodajZaduzenje;
    private javax.swing.JMenuItem jMenuItemPregledClana;
    private javax.swing.JMenuItem jMenuItemPregledKnjiga;
    private javax.swing.JMenuItem jMenuItemPregledZaduzenja;
    // End of variables declaration//GEN-END:variables
}
