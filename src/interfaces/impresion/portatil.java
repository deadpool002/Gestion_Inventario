/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.impresion;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;

/**
 *
 * @author pablo
 */
public class portatil extends javax.swing.JPanel implements Printable {

    /**
     * Creates new form impresora
     */
    public portatil() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel10 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(766, 437));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Dialog", 2, 8)); // NOI18N
        jLabel10.setText("Tel:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 20, 10));

        jLabel47.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel47.setText("TECNICO");
        add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        jLabel11.setFont(new java.awt.Font("Dialog", 2, 8)); // NOI18N
        jLabel11.setText("64-66285");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 90, 10));

        jLabel48.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel48.setText("Recibí Conforme");
        add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, -1, -1));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setText("N° Orden");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, -1, -1));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setText("0001");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, -1, -1));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        jLabel14.setText("Fecha Recepción");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, -1));

        jLabel49.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel49.setText("Costo");
        add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, -1, -1));

        jLabel50.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel50.setText("BS.");
        add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, -1, -1));

        jLabel51.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel51.setText("Costo");
        add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, -1, -1));

        jLabel52.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel52.setText("Servicio");
        add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, -1, -1));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        jLabel15.setText("Fecha Entrega Apróximada");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, -1));

        jLabel53.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel53.setText("CI:_______________");
        add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, -1, -1));

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel16.setText("día/mes/año");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        jLabel17.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel17.setText("día/mes/año");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, -1, -1));

        jLabel18.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel18.setText("Hora");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, -1, -1));

        jLabel19.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel19.setText("Hora");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, -1, -1));

        jLabel20.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel20.setText("05/06/2019");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

        jLabel22.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel22.setText("05/06/2019");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, -1, -1));

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel24.setText("DATOS  DEL  CLIENTE");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, -1, -1));

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        jLabel25.setText("Nombre:");
        add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

        jLabel26.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        jLabel26.setText("CI:");
        add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 40, -1));

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        jLabel27.setText("Dirección:");
        add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        jLabel28.setText("Celular:");
        add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, -1, -1));

        jLabel29.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel29.setText("Pablo Segovia Vargas");
        add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));

        jLabel30.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel30.setText("Av Japon 93");
        add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        jLabel31.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel31.setText("12345678");
        add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, -1, -1));

        jLabel32.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel32.setText("78670128");
        add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, -1, -1));

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel33.setText("DATOS  PC PORTATIL");
        add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, -1));

        jLabel34.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel34.setText("kx-mc6020");
        add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, -1));

        jLabel35.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        jLabel35.setText("Marca:");
        add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel36.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        jLabel36.setText("Modelo:");
        add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, -1, -1));

        jLabel37.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel37.setText("HP");
        add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 50, -1));

        jLabel38.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel38.setText("123456789123");
        add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, -1, -1));

        jLabel39.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        jLabel39.setText("N° Serie:");
        add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, -1, -1));

        jLabel40.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        jLabel40.setText("Cargador:");
        add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("SOPORTE TÉCNICO");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, 10));

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jRadioButton1.setText("SI");
        add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("PC PORTATIL");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, 20));

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("NO");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, -1, -1));

        jLabel5.setFont(new java.awt.Font("aakar", 1, 30)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 153));
        jLabel5.setText("A.M.");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 38));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setText("Comerzializadores");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        jLabel41.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jLabel41.setText("12313");
        add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 100, 10));

        jLabel42.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        jLabel42.setText("N° Serie");
        add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, -1, -1));

        jLabel43.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 0, 0));
        jLabel43.setText("\"Señor cliente si en el plazo de 30 dias no recoge su equipo la empresa no se hace responsable\"");
        add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        jLabel44.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel44.setText("TRABAJO A REALIZAR");
        add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 2, 8)); // NOI18N
        jLabel7.setText("Raul Otero #101");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 90, 10));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("\n\n\nOBSERVACIONES: ");
        jScrollPane1.setViewportView(jTextArea1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 470, 70));

        jLabel8.setFont(new java.awt.Font("Dialog", 2, 8)); // NOI18N
        jLabel8.setText("Cel:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 30, 10));

        jLabel9.setFont(new java.awt.Font("Dialog", 2, 8)); // NOI18N
        jLabel9.setText("71164598 - 73417476");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 110, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("TODA  ENTREGA  CON  ESTA  BOLETA");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("CON  CARNET  DE  IDENTIDAD");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        jTextField1.setText("1000");
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 50, 20));

        jTextField2.setText("1000");
        add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 300, 50, -1));

        jLabel103.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel103.setText("Repuesto");
        add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, -1, -1));

        jLabel104.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel104.setText("BS.");
        add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 320, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    @Override
    public int print(Graphics graf, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if(pageIndex>0){
          return NO_SUCH_PAGE;
      }
        Graphics2D a= (Graphics2D) graf;
        a.translate(pageFormat.getImageableX()+30, pageFormat.getImageableY()+30);
        a.scale(1.0, 1.1);
        this.printAll(graf);
        return PAGE_EXISTS;
    }
}