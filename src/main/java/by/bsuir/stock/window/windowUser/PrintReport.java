package by.bsuir.stock.window.windowUser;

import by.bsuir.stock.bean.InvoiceEntity;
import org.apache.poi.ss.usermodel.Workbook;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class PrintReport extends javax.swing.JDialog {



    public PrintReport(Workbook workbook, InvoiceEntity invoice) {
        this.workbook =workbook;
        this.invoice = invoice;
        initComponents();
    }

    public PrintReport() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jFileChooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jFileChooser1ActionPerformed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {

        String st = jFileChooser1.getCurrentDirectory()+"\\Договор "+ invoice.getNumber()+".xlsx";

        FileOutputStream fileOut = new FileOutputStream(jFileChooser1.getSelectedFile()+"\\Договор "+ invoice.getNumber()+".xlsx");
        workbook.write(fileOut);
        fileOut.close();
        this.setVisible(false);
    }

    private InvoiceEntity invoice;
    private Workbook workbook;
    private javax.swing.JFileChooser jFileChooser1;
}
