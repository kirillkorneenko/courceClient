package by.bsuir.stock.window.WindowEmployee;

import by.bsuir.stock.bean.CargoEntity;
import by.bsuir.stock.bean.CargosininvoiceEntity;
import by.bsuir.stock.util.SortTable;
import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.bean.InvoiceEntity;
import by.bsuir.stock.conection.Connection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.ArrayList;

public class WorkOrder extends javax.swing.JFrame {

    public WorkOrder() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        scrollTable = new javax.swing.JScrollPane();
        ordersTable = new javax.swing.JTable();
        openOrder = new javax.swing.JButton();
        approveOrder = new javax.swing.JButton();
        refuseOrder = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        captionLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        ordersTable.setRowSorter(new SortTable().sorter(ordersTable, columns));

        ordersTable.getTableHeader().setReorderingAllowed(false);
        scrollTable.setViewportView(ordersTable);

        openOrder.setText("Открыть");
        openOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openOrderActionPerformed(evt);
            }
        });

        approveOrder.setText("Одобрить");
        approveOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveOrderActionPerformed(evt);
            }
        });

        refuseOrder.setText("Отказать");
        refuseOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refuseOrderActionPerformed(evt);
            }
        });

        backButton.setText("назад");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        captionLabel.setFont(new java.awt.Font("Times New Roman", 0, 24));
        captionLabel.setText("Заказы");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(captionLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(openOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(approveOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                                        .addComponent(refuseOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(captionLabel)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(openOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(approveOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(refuseOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }

    private void openOrderActionPerformed(ActionEvent evt) {
        int[] rows = this.ordersTable.getSelectedRows();

        if (rows.length == 1) {
            int i = rows[0];
            InvoiceEntity invoice = new InvoiceEntity();
            invoice.setId((Integer) this.ordersTable.getValueAt(i, 0));

            Batch batch = new Batch();
            batch.setCommand("GET_INVOICE");
            batch.setDate(invoice);

            Batch result = Connection.connection(batch);
            if (result != null && result.getResult()) {
                invoice = (InvoiceEntity) result.getDate();
                orderDetails = new OrderDetails(WorkOrder.this, invoice);
                orderDetails.setLocationRelativeTo(null);
                orderDetails.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(new JDialog(), "Неизвестная ошибка", "Ошибка", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(new JDialog(), "Выберите груз", "Ошибка выбора", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void approveOrderActionPerformed(java.awt.event.ActionEvent evt) {
        int[] rows = this.ordersTable.getSelectedRows();

        if (rows.length == 1) {
            int i = rows[0];
            InvoiceEntity invoice = new InvoiceEntity();
            invoice.setId((Integer) this.ordersTable.getValueAt(i, 0));

            Batch batch = new Batch();
            batch.setCommand("GET_INVOICE");
            batch.setDate(invoice);

            Batch result = Connection.connection(batch);
            if (result != null && result.getResult()) {
                invoice = (InvoiceEntity) result.getDate();
                invoice.setApproval("true");
                java.util.Date da = new java.util.Date();
                Date date = new Date(da.getTime());
                invoice.setDate(date);
                invoice.setUsersByIdEmployee(Connection.getUser());
                batch.setDate(invoice);
                batch.setCommand("UPDATE_INVOICE");
                result = Connection.connection(batch);

                if (result != null && result.getResult()) {

                    Batch updateCargo = new Batch();
                    ArrayList<CargoEntity> cargos = new ArrayList<CargoEntity>();

                    for (CargosininvoiceEntity cargosininvoiceEntity : invoice.getCargosininvoicesById()) {
                        CargoEntity cargoEntity = cargosininvoiceEntity.getCargoByIdCargo();
                        cargoEntity.setInvoiceByIdInvoise(invoice);
                        cargoEntity.setUsersByIdUser(invoice.getUsersByIdUser());
                        cargos.add(cargoEntity);
                    }
                    updateCargo.setList(cargos);
                    updateCargo.setCommand("UPDATE_CARGOS");
                    result = Connection.connection(updateCargo);

                    if (result != null && result.getResult()) {
                        JOptionPane.showMessageDialog(new JDialog(), "Заказ одобрен", "Заказ", JOptionPane.WARNING_MESSAGE);
                        initTable();
                    } else {
                        JOptionPane.showMessageDialog(new JDialog(), "Неизвестная ошибка", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(new JDialog(), "Неизвестная ошибка", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(new JDialog(), "Неизвестная ошибка", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(new JDialog(), "Выберите груз", "Ошибка выбора", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void refuseOrderActionPerformed(java.awt.event.ActionEvent evt) {
        int[] rows = this.ordersTable.getSelectedRows();

        if (rows.length == 1) {
            int i = rows[0];
            InvoiceEntity invoice = new InvoiceEntity();
            invoice.setId((Integer) this.ordersTable.getValueAt(i, 0));

            Batch batch = new Batch();
            batch.setDate(invoice);
            batch.setCommand("DELETE_INVOICE");

            Batch result = Connection.connection(batch);

            if (result != null && result.getResult()) {
                JOptionPane.showMessageDialog(new JDialog(), "Отказано", "Заказ", JOptionPane.WARNING_MESSAGE);
                initTable();
            } else {
                JOptionPane.showMessageDialog(new JDialog(), "Неизвестная ошибка", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }


        } else {
            JOptionPane.showMessageDialog(new JDialog(), "Выберите груз", "Ошибка выбора", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        StartEmployee window = new StartEmployee();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.setVisible(false);
    }

    private void initTable() {
        Batch batch = new Batch();
        batch.setCommand("GET_INVOICE_LIST");
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


    private OrderDetails orderDetails;
    private javax.swing.JButton openOrder;
    private javax.swing.JButton approveOrder;
    private javax.swing.JButton refuseOrder;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel captionLabel;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable ordersTable;
}
