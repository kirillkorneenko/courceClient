package by.bsuir.stock.window.WindowEmployee;

import by.bsuir.stock.bean.CargosininvoiceEntity;
import by.bsuir.stock.bean.InvoiceEntity;
import by.bsuir.stock.window.StartWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collection;

public class OrderDetails extends javax.swing.JDialog {

    public OrderDetails(JFrame owner, InvoiceEntity invoiceEntity) {
        super(owner,"Заказ",true);
        this.invoiceEntity = invoiceEntity;
        initComponents();
    }

    public OrderDetails() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        captionLabel = new javax.swing.JLabel();
        scrollTable = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        captionLabel.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        captionLabel.setText("Заказ");

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                    "Номер заказа","Номер товара", "Наименование"
            }
        ) {
            Class[] types = new Class [] {
                Integer.class, Integer.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        initTable();

        orderTable.getTableHeader().setReorderingAllowed(false);
        scrollTable.setViewportView(orderTable);

        backButton.setText("Назад");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(captionLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(captionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
    }

    private void initTable(){
        Collection<CargosininvoiceEntity> list= invoiceEntity.getCargosininvoicesById();
        ArrayList<CargosininvoiceEntity> arrayList = new ArrayList<>();
        for (CargosininvoiceEntity cargosininvoiceEntity: list) {
            arrayList.add(cargosininvoiceEntity);
        }
        DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
        model.getDataVector().removeAllElements();
        for(CargosininvoiceEntity cargosininvoiceEntity:arrayList){
            model.addRow(new Object[]{cargosininvoiceEntity.getInvoiceByNumberInvoice().getId(),cargosininvoiceEntity.getCargoByIdCargo().getId(),cargosininvoiceEntity.getCargoByIdCargo().getName()});
        }

    }


    private InvoiceEntity invoiceEntity;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel captionLabel;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable orderTable;
}
