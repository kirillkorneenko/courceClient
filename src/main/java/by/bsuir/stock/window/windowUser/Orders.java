package by.bsuir.stock.window.windowUser;


import by.bsuir.stock.util.CreateWorkbook;
import by.bsuir.stock.util.SortTable;
import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.bean.InvoiceEntity;
import by.bsuir.stock.conection.Connection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Orders extends javax.swing.JFrame {

    public Orders() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        captionLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ordersTable = new javax.swing.JTable();
        updateTable = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        printOrder = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        captionLabel.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        captionLabel.setText("Заказы");

        ordersTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "Номер заказа", "Номер договора", "Операция", "Статус заказа"
                }
        ) {
            Class[] types = new Class[]{
                    Integer.class, Integer.class, String.class, String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        initTable();

        String columns[] = {"Номер заказа", "Номер договора", "Операция", "Статус заказа"};
        ordersTable.setRowSorter(new SortTable().sorter(ordersTable,columns));

        ordersTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(ordersTable);

        updateTable.setText("Отменить");
        updateTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateTableActionPerformed(evt);
            }
        });

        backButton.setText("Выход");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        printOrder.setText("Распечатать");
        printOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(captionLabel)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(updateTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(printOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))))
                                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(captionLabel)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(printOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(updateTable, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }

    private void updateTableActionPerformed(java.awt.event.ActionEvent evt) {
        int[] rows = this.ordersTable.getSelectedRows();
        InvoiceEntity invoice = new InvoiceEntity();
        if (rows.length == 1) {
            int i = rows[0];
            invoice.setId((Integer) this.ordersTable.getValueAt(i, 0));

            Batch batch = new Batch();
            batch.setCommand("DELETE_INVOICE");
            batch.setDate(invoice);
            Batch result = Connection.connection(batch);
            if (result != null && result.getResult()) {
                JOptionPane.showMessageDialog(new JDialog(), "Заказ отменён", "Ошибка", JOptionPane.ERROR_MESSAGE);
                initTable();
            } else {
                JOptionPane.showMessageDialog(new JDialog(), "Неизвестная ошибка", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(new JDialog(), "Выберите один заказ", "Ошибка", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        StartUser window = new StartUser();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.setVisible(false);
    }

    private void printOrderActionPerformed(java.awt.event.ActionEvent evt) {
        int[] rows = this.ordersTable.getSelectedRows();
        InvoiceEntity invoice = new InvoiceEntity();

        if (rows.length == 1) {
            int i = rows[0];
            invoice.setId((Integer) this.ordersTable.getValueAt(i, 0));
            Batch batch = new Batch();
            batch.setCommand("GET_INVOICE");
            batch.setDate(invoice);
            Batch result = Connection.connection(batch);
            InvoiceEntity invoiceEntity = (InvoiceEntity) result.getDate();
            CreateWorkbook createWorkbook = new CreateWorkbook();
            createWorkbook.setInvoiceEntity(invoiceEntity);
            createWorkbook.createWorkbook();


        } else {
            JOptionPane.showMessageDialog(new JDialog(), "Выберите один заказ", "Ошибка", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void initTable() {
        Batch batch = new Batch();
        batch.setCommand("GET_INVOICE_BY_USER_ID");
        batch.setDate(Connection.getUser());
        Batch result = Connection.connection(batch);
        ArrayList<InvoiceEntity> arrayList = (ArrayList<InvoiceEntity>) result.getList();
        DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();
        model.getDataVector().removeAllElements();
        for (InvoiceEntity invoice : arrayList) {
            model.addRow(new Object[]{invoice.getId(), invoice.getNumber(), invoice.getViewOperation(), approvalStatus(invoice.getApproval())});
        }
    }

    private String approvalStatus(String st) {
        if (st.equals("true")) {
            return "Договор оформлен";
        } else {
            return "На рассмотрении";
        }
    }

    private javax.swing.JButton printOrder;
    private javax.swing.JButton updateTable;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel captionLabel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable ordersTable;
}
