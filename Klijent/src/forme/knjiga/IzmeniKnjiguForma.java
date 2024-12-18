/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forme.knjiga;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author filipjevtovic
 */
public class IzmeniKnjiguForma extends javax.swing.JFrame {

    /**
     * Creates new form IzmeniKnjiguForma
     */
    public IzmeniKnjiguForma() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNazivKnjige = new javax.swing.JTextField();
        jTextFieldISBN = new javax.swing.JTextField();
        jSpinnerKolicina = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNazivAutora = new javax.swing.JTextField();
        jButtonPronadji = new javax.swing.JButton();
        jButtonResetuj = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAutori = new javax.swing.JTable();
        jButtonIzmeni = new javax.swing.JButton();
        jButtonDodajAutora = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Izmeni Knjigu");

        jLabel1.setText("Naziv:");

        jLabel2.setText("ISBN:");

        jLabel3.setText("Kolicina:");

        jSpinnerKolicina.setModel(new javax.swing.SpinnerNumberModel(1, 0, 10, 1));

        jLabel4.setText("Autor:");

        jButtonPronadji.setText("Pronadji");

        jButtonResetuj.setText("Resetuj");

        jTableAutori.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableAutori);

        jButtonIzmeni.setText("Izmeni");

        jButtonDodajAutora.setText("Dodaj novog autora");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldNazivKnjige)
                                .addComponent(jTextFieldISBN)
                                .addComponent(jSpinnerKolicina, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldNazivAutora, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButtonPronadji)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButtonResetuj))
                                .addComponent(jButtonDodajAutora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jButtonIzmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNazivKnjige, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jSpinnerKolicina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldNazivAutora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPronadji)
                    .addComponent(jButtonResetuj))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonDodajAutora)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonIzmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getjButtonIzmeni() {
        return jButtonIzmeni;
    }

    public JButton getjButtonResetuj() {
        return jButtonResetuj;
    }

    public JSpinner getjSpinnerKolicina() {
        return jSpinnerKolicina;
    }

    public JTable getjTableAutori() {
        return jTableAutori;
    }

    public JTextField getjTextFieldISBN() {
        return jTextFieldISBN;
    }

    public JTextField getjTextFieldNazivAutora() {
        return jTextFieldNazivAutora;
    }

    public JTextField getjTextFieldNazivKnjige() {
        return jTextFieldNazivKnjige;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDodajAutora;
    private javax.swing.JButton jButtonIzmeni;
    private javax.swing.JButton jButtonPronadji;
    private javax.swing.JButton jButtonResetuj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerKolicina;
    private javax.swing.JTable jTableAutori;
    private javax.swing.JTextField jTextFieldISBN;
    private javax.swing.JTextField jTextFieldNazivAutora;
    private javax.swing.JTextField jTextFieldNazivKnjige;
    // End of variables declaration//GEN-END:variables

    public void izmeniAddActionListener(ActionListener actionListener) {
        jButtonIzmeni.addActionListener(actionListener);
    }

    public void addBtnPretraziActionListener(ActionListener actionListener) {
        jButtonPronadji.addActionListener(actionListener);
    }

    public void addBtnResetujActionListener(ActionListener actionListener) {
        jButtonResetuj.addActionListener(actionListener);
    }

    public void addBtnDodajAutoraActionListener(ActionListener actionListener) {
        jButtonDodajAutora.addActionListener(actionListener);
    }
}
