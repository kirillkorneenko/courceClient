package by.bsuir.stock.window.windowUser;


import by.bsuir.stock.util.SortTable;
import by.bsuir.stock.basket.Basket;
import by.bsuir.stock.bean.*;
import by.bsuir.stock.conection.Connection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class BasketOfOrder extends javax.swing.JFrame {

    public BasketOfOrder() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        summLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        captionLabel = new javax.swing.JLabel();
        scrollTable = new javax.swing.JScrollPane();
        cargoTable = new javax.swing.JTable();
        makeOrder = new javax.swing.JButton();
        deleteOrder = new javax.swing.JButton();
        resultLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(823, 553));

        summLabel.setFont(new java.awt.Font("Times New Roman", 0, 24));
        summLabel.setText("0");

        backButton.setText("Выход");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        captionLabel.setFont(new java.awt.Font("Times New Roman", 0, 24));
        captionLabel.setText("Заказ");

        cargoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Номер груза", "Наименование", "Цена", "Количество"
            }
        ) {
            Class[] types = new Class [] {
                Integer.class, String.class, Float.class, Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        initTable();

        String columns[] = {"Номер заказа", "Номер договора", "Операция", "Статус заказа"};
        cargoTable.setRowSorter(new SortTable().sorter(cargoTable,columns));

        cargoTable.getTableHeader().setReorderingAllowed(false);
        scrollTable.setViewportView(cargoTable);

        makeOrder.setText("Оформить заказ");
        makeOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeOrderActionPerformed(evt);
            }
        });

        deleteOrder.setText("Удалить");
        deleteOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteOrderActionPerformed(evt);
            }
        });

        resultLabel.setFont(new java.awt.Font("Times New Roman", 0, 24));
        resultLabel.setText("Итого:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(resultLabel)
                        .addGap(466, 466, 466)
                        .addComponent(summLabel))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(captionLabel))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(makeOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(deleteOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(captionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(makeOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(deleteOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(resultLabel)
                            .addComponent(summLabel))))
                .addGap(35, 35, 35))
        );

        pack();
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        StartUser window = new StartUser();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.setVisible(false);
    }

    private void makeOrderActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<CargoEntity> list = (ArrayList<CargoEntity>) Basket.getInstance().getBasket();

        InvoiceEntity invoice = new InvoiceEntity();
        invoice.setViewOperation("Покупка");
        invoice.setApproval("false");
        invoice.setUsersByIdUser(Connection.getUser());
        invoice.setNumber((int) (Math.random()*9999999));

        Batch batch = new Batch();
        batch.setDate(invoice);
        batch.setCommand("ADD_INVOICE");
        Batch result = Connection.connection(batch);
        if(result!=null && result.getResult()){
            batch= new Batch();
            ArrayList<CargosininvoiceEntity> basket = new ArrayList<>();
            for (CargoEntity cargo : list) {
                InvoiceEntity invResult= (InvoiceEntity) result.getDate();
                CargosininvoiceEntity cii= new CargosininvoiceEntity();
                cii.setCargoByIdCargo(cargo);
                cii.setInvoiceByNumberInvoice(invResult);
                basket.add(cii);
            }
            batch.setList(basket);
            batch.setCommand("ADD_CARGO_IN_INVOICE");
            result = Connection.connection(batch);
            if (result!= null&& result.getResult()){
                JOptionPane.showMessageDialog(new JDialog(), "Заказ оформлен", "Оформление", JOptionPane.WARNING_MESSAGE);
                Basket.getInstance().deleteAll();
                StartUser window = new StartUser();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                this.setVisible(false);
            }
            else {
                JOptionPane.showMessageDialog(new JDialog(), "Неизвестная ошибка", "Ошибка", JOptionPane.WARNING_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(new JDialog(), "Неизвестная ошибка", "Ошибка", JOptionPane.WARNING_MESSAGE);
        }



    }

    private void deleteOrderActionPerformed(java.awt.event.ActionEvent evt) {
        int[] rows = this.cargoTable.getSelectedRows();
        CargoEntity cargo = new CargoEntity();

        if (rows.length == 1) {
            int i = rows[0];
            cargo.setId((Integer) this.cargoTable.getValueAt(i, 0));
            cargo.setName((String) this.cargoTable.getValueAt(i, 1));
            cargo.setPrice((Double) this.cargoTable.getValueAt(i, 2));
            Basket.getInstance().delete(cargo);}
        else {
            JOptionPane.showMessageDialog(new JDialog(), "Выберите один склад", "Ошибка", JOptionPane.WARNING_MESSAGE);
        }
        initTable();
    }

    private void initTable(){
        ArrayList<CargoEntity> arrayList = (ArrayList<CargoEntity>) Basket.getInstance().getBasket();
        DefaultTableModel model = (DefaultTableModel) cargoTable.getModel();
        model.getDataVector().removeAllElements();
        for (CargoEntity cargo : arrayList) {
            model.addRow(new Object[]{cargo.getId(), cargo.getName(), cargo.getPrice(),0});
        }
    }

    private javax.swing.JButton makeOrder;
    private javax.swing.JButton deleteOrder;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel captionLabel;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JLabel summLabel;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable cargoTable;
}
