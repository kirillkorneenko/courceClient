package by.bsuir.stock.window.WindowEmployee;


import by.bsuir.stock.util.SortTable;
import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.bean.StockEntity;
import by.bsuir.stock.conection.Connection;
import by.bsuir.stock.window.StartWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class StartEmployee extends javax.swing.JFrame {

    public StartEmployee() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        scrollTable = new javax.swing.JScrollPane();
        stockTable = new javax.swing.JTable();
        openStockButton = new javax.swing.JButton();
        updateTable = new javax.swing.JButton();
        captionLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        workOrders = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        stockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Номер Склада", "Расположение", "Объем склада", "Загруженность"
            }
        ) {
            Class[] types = new Class [] {
                Integer.class, String.class, Float.class, Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stockTable.getTableHeader().setReorderingAllowed(false);
        scrollTable.setViewportView(stockTable);
        if (stockTable.getColumnModel().getColumnCount() > 0) {
            stockTable.getColumnModel().getColumn(0).setMinWidth(90);
            stockTable.getColumnModel().getColumn(0).setMaxWidth(90);
            stockTable.getColumnModel().getColumn(3).setMinWidth(100);
            stockTable.getColumnModel().getColumn(3).setMaxWidth(100);
        }


        initTable();
        String columns[] = {"Номер Склада", "Расположение", "Объем склада", "Загруженность"};
        stockTable.setRowSorter(new SortTable().sorter(stockTable,columns));

        openStockButton.setText("Открыть");
        openStockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openStockButtonActionPerformed(evt);
            }
        });

        updateTable.setText("Обновить");
        updateTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateTableActionPerformed(evt);
            }
        });

        captionLabel.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        captionLabel.setText("Список складов");

        backButton.setText("Выход");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        workOrders.setText("Работа с заказами");
        workOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workOrdersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(captionLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(openStockButton, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(updateTable, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(workOrders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(captionLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(openStockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(updateTable, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(workOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(237, 237, 237)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollTable))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }

    private void openStockButtonActionPerformed(java.awt.event.ActionEvent evt) {

        int[] rows = this.stockTable.getSelectedRows();


        if (rows.length == 1) {

            int i = rows[0];
            StockEntity stock = new StockEntity();
            stock.setId((Integer) this.stockTable.getValueAt(i, 0));

                StockWindow window = new StockWindow(stock.getId());
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                this.setVisible(false);

        } else {
            JOptionPane.showMessageDialog(new JDialog(), "Выберите склад", "Ошибка выбора", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        StartWindow window = new StartWindow();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.setVisible(false);
    }

    private void updateTableActionPerformed(java.awt.event.ActionEvent evt) {
        initTable();
    }

    private void workOrdersActionPerformed(java.awt.event.ActionEvent evt) {
       WorkOrder window = new WorkOrder();
       window.setLocationRelativeTo(null);
       window.setVisible(true);
       this.setVisible(false);
    }

    private void initTable(){
        Batch batch = new Batch();
        batch.setCommand("GET_STOCK_LIST");
        Batch result = Connection.connection(batch);
        ArrayList<StockEntity> arrayList = (ArrayList<StockEntity>) result.getList();
        DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
        model.getDataVector().removeAllElements();
        for(StockEntity stock:arrayList){
            model.addRow(new Object[]{stock.getId(),stock.getLocation(),stock.getMaxAmount(),stock.getCongestion()});
        }
    }

    private javax.swing.JButton workOrders;
    private javax.swing.JButton openStockButton;
    private javax.swing.JButton updateTable;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel captionLabel;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable stockTable;
}
