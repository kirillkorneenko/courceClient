package by.bsuir.stock.window.windowUser;


import by.bsuir.stock.util.SortTable;
import by.bsuir.stock.basket.Basket;
import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.bean.CargoEntity;
import by.bsuir.stock.conection.Connection;
import by.bsuir.stock.window.StartWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.ArrayList;

public class StartUser extends javax.swing.JFrame {

    public StartUser() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        captionLabel = new javax.swing.JLabel();
        scrollTable = new javax.swing.JScrollPane();
        cargoTable = new javax.swing.JTable();
        showBasket = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        addBasket = new javax.swing.JButton();
        myOrders = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        captionLabel.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        captionLabel.setText("Список грузов");

        cargoTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "Номер груза", "Наименование", "Цена", "Срок хранения"
                }
        ) {
            Class[] types = new Class[]{
                    Integer.class, String.class, Double.class, Object.class
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
        cargoTable.getTableHeader().setReorderingAllowed(false);
        scrollTable.setViewportView(cargoTable);
        if (cargoTable.getColumnModel().getColumnCount() > 0) {
            cargoTable.getColumnModel().getColumn(0).setMinWidth(80);
            cargoTable.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        initTable();

        String columns[] = {"Номер заказа", "Номер договора", "Операция", "Статус заказа"};
        cargoTable.setRowSorter(new SortTable().sorter(cargoTable,columns));

        showBasket.setText("Корзина");
        showBasket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showBasketActionPerformed(evt);
            }
        });

        backButton.setText("Выход");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        addBasket.setText("Добавить в корзину");
        addBasket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBasketActionPerformed(evt);
            }
        });

        myOrders.setText("Мои заказы");
        myOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myOrdersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(42, 42, 42)
                                                .addComponent(captionLabel)
                                                .addGap(0, 622, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(showBasket, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                                        .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                                        .addComponent(addBasket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(myOrders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(captionLabel)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(addBasket, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(showBasket, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(myOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }

    private void showBasketActionPerformed(java.awt.event.ActionEvent evt) {
        BasketOfOrder window = new BasketOfOrder();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.setVisible(false);
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        StartWindow window = new StartWindow();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.setVisible(false);
    }

    private void addBasketActionPerformed(java.awt.event.ActionEvent evt) {
        int[] rows = this.cargoTable.getSelectedRows();

        if (rows.length == 1) {
            int i = rows[0];
            CargoEntity cargo = new CargoEntity();
            cargo.setId((Integer) this.cargoTable.getValueAt(i, 0));
            cargo.setName((String) this.cargoTable.getValueAt(i, 1));
            cargo.setPrice((Double) this.cargoTable.getValueAt(i, 2));
            cargo.setShelfLife((Date) this.cargoTable.getValueAt(i, 3));

            Basket.getInstance().add(cargo);
        } else {
            JOptionPane.showMessageDialog(new JDialog(), "Выберите товар", "Ошибка выбора", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void myOrdersActionPerformed(java.awt.event.ActionEvent evt) {
        Orders window = new Orders();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.setVisible(false);
    }

    private void initTable() {
        Batch batch = new Batch();
        batch.setCommand("GET_CARGO_LIST");
        Batch result = Connection.connection(batch);
        ArrayList<CargoEntity> arrayList = (ArrayList<CargoEntity>) result.getList();
        DefaultTableModel model = (DefaultTableModel) cargoTable.getModel();
        model.getDataVector().removeAllElements();
        for (CargoEntity cargo : arrayList) {
            if(cargo.getUsersByIdUser()==null) model.addRow(new Object[]{cargo.getId(), cargo.getName(), cargo.getPrice(), cargo.getShelfLife()});
        }
    }

    private javax.swing.JButton addBasket;
    private javax.swing.JButton showBasket;
    private javax.swing.JButton myOrders;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel captionLabel;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable cargoTable;
}
