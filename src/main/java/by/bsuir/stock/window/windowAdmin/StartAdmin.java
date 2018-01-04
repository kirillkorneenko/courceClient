/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.stock.window.windowAdmin;

import by.bsuir.stock.util.SortTable;
import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.bean.StockEntity;
import by.bsuir.stock.conection.Connection;
import by.bsuir.stock.window.StartWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class StartAdmin extends javax.swing.JFrame {

    public StartAdmin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        caption = new javax.swing.JLabel();
        scrollTable = new javax.swing.JScrollPane();
        stockTable = new javax.swing.JTable();
        buttonStatistics = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        caption.setFont(new java.awt.Font("Times New Roman", 0, 24));
        caption.setText("Список складов");

        stockTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "Номер Склада", "Расположение", "Объем склада", "Загруженность"
                }
        ) {
            Class[] types = new Class[]{
                    Integer.class, String.class, Float.class, Float.class
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

        String columns[] = {"Номер Склада", "Расположение", "Объем склада", "Загруженность"};
        stockTable.setRowSorter(new SortTable().sorter(stockTable, columns));

        stockTable.getTableHeader().setReorderingAllowed(false);
        scrollTable.setViewportView(stockTable);
        if (stockTable.getColumnModel().getColumnCount() > 0) {
            stockTable.getColumnModel().getColumn(0).setMinWidth(90);
            stockTable.getColumnModel().getColumn(0).setPreferredWidth(90);
            stockTable.getColumnModel().getColumn(0).setMaxWidth(90);
            stockTable.getColumnModel().getColumn(1).setResizable(false);
            stockTable.getColumnModel().getColumn(2).setMinWidth(100);
            stockTable.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        buttonStatistics.setText("Показать статистику");
        buttonStatistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStatisticsActionPerformed(evt);
            }
        });

        addButton.setText("Добавить");
        addButton.addActionListener(
                new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        addButtonActionPerformed(evt);
                        initTable();
                    }
                }
        );

        deleteButton.setText("Удалить");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        updateButton.setText("Изменить");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
                initTable();
            }
        });

        exitButton.setText("Выход");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(buttonStatistics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(scrollTable, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                                        .addComponent(caption))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                        .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                        .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                        .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(caption)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                        .addComponent(buttonStatistics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }

    private void buttonStatisticsActionPerformed(java.awt.event.ActionEvent evt) {
        int[] rows = this.stockTable.getSelectedRows();

        if (rows.length == 1) {
            int i = rows[0];
            StockEntity stock = new StockEntity();
            stock.setId((Integer) this.stockTable.getValueAt(i, 0));
            Batch batch = new Batch();
            batch.setCommand("GET_STOCK");
            batch.setDate(stock);
            Batch result = Connection.connection(batch);
            stock = (StockEntity) result.getDate();
            StatisticsWindow demo = new StatisticsWindow(stock);
            demo.setLocationRelativeTo(null);
            demo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(new JDialog(), "Выберите один склад", "Ошибка", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        StartWindow window = new StartWindow();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.setVisible(false);
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dialogAdd = new AddStock(StartAdmin.this);
        dialogAdd.setLocationRelativeTo(null);
        dialogAdd.setVisible(true);
    }

    private void deleteButtonActionPerformed(ActionEvent evt) {
        int[] rows = this.stockTable.getSelectedRows();

        if (rows.length == 1) {
            int i = rows[0];
            StockEntity stock = new StockEntity();
            stock.setId((Integer) this.stockTable.getValueAt(i, 0));
            stock.setLocation((String) this.stockTable.getValueAt(i, 1));
            stock.setMaxAmount((Double) this.stockTable.getValueAt(i, 2));
            stock.setCongestion((Double) this.stockTable.getValueAt(i, 3));

            Batch batch = new Batch();
            batch.setDate(stock);
            batch.setCommand("DELETE_STOCK");
            Batch result = Connection.connection(batch);
            if (result.getResult()) {
                initTable();
                JOptionPane.showMessageDialog(new JDialog(), "Склад удалён", "Удаление", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(new JDialog(), "Неизвестная ошибка", "Ошибка", JOptionPane.WARNING_MESSAGE);
            }
        } else {

            ArrayList<StockEntity> list = new ArrayList<StockEntity>();

            for (int i : rows) {
                StockEntity stock = new StockEntity();
                stock.setId((Integer) this.stockTable.getValueAt(i, 0));
                stock.setLocation((String) this.stockTable.getValueAt(i, 1));
                stock.setMaxAmount((Double) this.stockTable.getValueAt(i, 2));
                stock.setCongestion((Double) this.stockTable.getValueAt(i, 3));

                list.add(stock);
            }
            Batch batch = new Batch();
            batch.setList(list);
            batch.setCommand("DELETE_STOCKS");
            Batch result = Connection.connection(batch);
            if (result.getResult()) {
                initTable();
                JOptionPane.showMessageDialog(new JDialog(), "Склады успешно удалены", "Удаление", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(new JDialog(), "Неизвестная ошибка", "Ошибка", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int[] rows = this.stockTable.getSelectedRows();
        StockEntity stock = new StockEntity();

        if (rows.length == 1) {
            int i = rows[0];
            stock.setId((Integer) this.stockTable.getValueAt(i, 0));
            stock.setLocation((String) this.stockTable.getValueAt(i, 1));
            stock.setMaxAmount((Double) this.stockTable.getValueAt(i, 2));
            stock.setCongestion((Double) this.stockTable.getValueAt(i, 3));
            dialogUpdate = new UpdateStock(StartAdmin.this, stock);
            dialogUpdate.setLocationRelativeTo(null);
            dialogUpdate.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(new JDialog(), "Выберите один склад", "Ошибка", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void initTable() {
        Batch batch = new Batch();
        batch.setCommand("GET_STOCK_LIST");
        Batch result = Connection.connection(batch);
        ArrayList<StockEntity> arrayList = (ArrayList<StockEntity>) result.getList();
        DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
        model.getDataVector().removeAllElements();
        for (StockEntity stock : arrayList) {
            model.addRow(new Object[]{stock.getId(), stock.getLocation(), stock.getMaxAmount(), stock.getCongestion()});
        }
    }

    private AddStock dialogAdd;
    private UpdateStock dialogUpdate;
    private javax.swing.JButton buttonStatistics;
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton updateButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel caption;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable stockTable;
}
