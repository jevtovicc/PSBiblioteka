/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forme.zaduzenje;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author filipjevtovic
 */
public class PregledZaduzenjaForma extends javax.swing.JFrame {

    /**
     * Creates new form PregledPozajmicaForma
     */
    public PregledZaduzenjaForma() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePozajmice = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldImePrezimeClana = new javax.swing.JTextField();
        jTextFieldNazivKnjige = new javax.swing.JTextField();
        jButtonPretrazi = new javax.swing.JButton();
        jButtonResetuj = new javax.swing.JButton();
        jButtonObrisi = new javax.swing.JButton();
        jButtonIzmeni = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pregled Zaduzenja");

        jTablePozajmice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTablePozajmice);

        jLabel1.setText("Ime i prezime clana:");

        jLabel2.setText("Naziv knjige:");

        jButtonPretrazi.setText("Pretrazi");

        jButtonResetuj.setText("Resetuj");

        jButtonObrisi.setText("Obrisi");

        jButtonIzmeni.setText("Izmeni");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonObrisi)
                            .addComponent(jButtonIzmeni)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldImePrezimeClana, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                            .addComponent(jTextFieldNazivKnjige))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonPretrazi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonResetuj)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldImePrezimeClana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNazivKnjige, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPretrazi)
                    .addComponent(jButtonResetuj))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jButtonObrisi)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonIzmeni)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getjButtonPretrazi() {
        return jButtonPretrazi;
    }

    public void setjButtonPretrazi(JButton jButtonPretrazi) {
        this.jButtonPretrazi = jButtonPretrazi;
    }

    public JButton getjButtonResetuj() {
        return jButtonResetuj;
    }

    public void setjButtonResetuj(JButton jButtonResetuj) {
        this.jButtonResetuj = jButtonResetuj;
    }

    public JTable getjTablePozajmice() {
        return jTablePozajmice;
    }

    public void setjTablePozajmice(JTable jTablePozajmice) {
        this.jTablePozajmice = jTablePozajmice;
    }

    public JTextField getjTextFieldImePrezimeClana() {
        return jTextFieldImePrezimeClana;
    }

    public void setjTextFieldImePrezimeClana(JTextField jTextFieldImePrezimeClana) {
        this.jTextFieldImePrezimeClana = jTextFieldImePrezimeClana;
    }

    public JTextField getjTextFieldNazivKnjige() {
        return jTextFieldNazivKnjige;
    }

    public void setjTextFieldNazivKnjige(JTextField jTextFieldNazivKnjige) {
        this.jTextFieldNazivKnjige = jTextFieldNazivKnjige;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonIzmeni;
    private javax.swing.JButton jButtonObrisi;
    private javax.swing.JButton jButtonPretrazi;
    private javax.swing.JButton jButtonResetuj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePozajmice;
    private javax.swing.JTextField jTextFieldImePrezimeClana;
    private javax.swing.JTextField jTextFieldNazivKnjige;
    // End of variables declaration//GEN-END:variables

    public void addBtnPretraziActionListener(ActionListener actionListener) {
        jButtonPretrazi.addActionListener(actionListener);
    }

    public void addBtnResetujActionListener(ActionListener actionListener) {
        jButtonResetuj.addActionListener(actionListener);
    }

    public void addBtnObrisiActionListener(ActionListener actionListener) {
        jButtonObrisi.addActionListener(actionListener);
    }
    
    public void addBtnIzmeniActionListener(ActionListener actionListener) {
        jButtonIzmeni.addActionListener(actionListener);
    }
}
