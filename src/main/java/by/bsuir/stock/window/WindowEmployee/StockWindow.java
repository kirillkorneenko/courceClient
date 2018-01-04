package by.bsuir.stock.window.WindowEmployee;

import by.bsuir.stock.util.SortTable;
import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.bean.CargoEntity;
import by.bsuir.stock.bean.StockEntity;
import by.bsuir.stock.conection.Connection;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

public class StockWindow extends javax.swing.JFrame implements TableCellRenderer {

    public StockWindow(Integer integer) {
        this.idStock = integer;
        initComponents();
    }

    public StockWindow() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        captionLabel = new javax.swing.JLabel();
        scrollTable = new javax.swing.JScrollPane();
        cargoTable = new javax.swing.JTable();
        addCargo = new javax.swing.JButton();
        deleteCargo = new javax.swing.JButton();
        updateCargo = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        captionLabel.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        captionLabel.setText("Список грузов");

        cargoTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Номер груза", "Наименование", "Цена", "Объем", "Срок годности"}
        ) {
            Class[] types = new Class[]{
                    java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        initTable();
        String columns[] = {"Номер груза", "Наименование", "Цена", "Объем", "Срок годности"};
        cargoTable.setRowSorter(new SortTable().sorter(cargoTable,columns));


        cargoTable.getTableHeader().setReorderingAllowed(false);
        scrollTable.setViewportView(cargoTable);

        addCargo.setText("Добавить");
        addCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCargoActionPerformed(evt);
                initTable();
            }
        });

        deleteCargo.setText("Удалить");
        deleteCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCargoActionPerformed(evt);
                initTable();
            }
        });

        updateCargo.setText("Изменить");
        updateCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCargoActionPerformed(evt);
                initTable();
            }
        });

        backButton.setText("Выход");
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
                                                .addGap(42, 42, 42)
                                                .addComponent(captionLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(addCargo, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                                        .addComponent(deleteCargo, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                                        .addComponent(updateCargo, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                                        .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))))
//                                                        .addComponent(printReport, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))))
                                .addGap(22, 22, 22))
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
                                                .addComponent(addCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(deleteCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(updateCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
//                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }

    private void addCargoActionPerformed(java.awt.event.ActionEvent evt) {
        addCargoWindow = new AddCargo(StockWindow.this, idStock);
        addCargoWindow.setLocationRelativeTo(null);
        addCargoWindow.setVisible(true);

    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        StartEmployee window = new StartEmployee();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.setVisible(false);
    }

    private void deleteCargoActionPerformed(java.awt.event.ActionEvent evt) {

        int[] rows = this.cargoTable.getSelectedRows();

        if (rows.length == 1) {
            int i = rows[0];
            CargoEntity cargo = new CargoEntity();
            cargo.setId((Integer) this.cargoTable.getValueAt(i, 0));

            Batch batch = new Batch();
            batch.setDate(cargo);
            batch.setCommand("DELETE_CARGO");
            Batch result = Connection.connection(batch);
            if (result.getResult()) {
                initTable();
            } else {
                JOptionPane.showMessageDialog(new JDialog(), "Неизвестная ошибка", "Ошибка", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(new JDialog(), "Выберите груз", "Ошибка выбора", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateCargoActionPerformed(java.awt.event.ActionEvent evt) {

        int[] rows = this.cargoTable.getSelectedRows();

        if (rows.length == 1) {
            int i = rows[0];
            CargoEntity cargo = new CargoEntity();
            cargo.setId((Integer) this.cargoTable.getValueAt(i, 0));
            cargo.setName((String) this.cargoTable.getValueAt(i, 1));
            cargo.setPrice((Double) this.cargoTable.getValueAt(i, 2));
            cargo.setAmount((Double) this.cargoTable.getValueAt(i, 3));
            cargo.setShelfLife((Date) this.cargoTable.getValueAt(i, 4));

            updateCargoWindow = new UpdateCargo(StockWindow.this, cargo);
            updateCargoWindow.setLocationRelativeTo(null);
            updateCargoWindow.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(new JDialog(), "Выберите груз", "Ошибка выбора", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void initTable() {
        StockEntity stock = new StockEntity();
        stock.setId(idStock);

        Batch batch = new Batch();
        batch.setDate(stock);
        batch.setCommand("GET_CARGO_LIST_BY_STOCK");
        Batch result = Connection.connection(batch);
        ArrayList<CargoEntity> arrayList = (ArrayList<CargoEntity>) result.getList();
        DefaultTableModel model = (DefaultTableModel) cargoTable.getModel();
        model.getDataVector().removeAllElements();
        int i = 0;
        for (CargoEntity cargo : arrayList) {
            model.addRow(new Object[]{cargo.getId(), cargo.getName(), cargo.getPrice(), cargo.getAmount(), cargo.getShelfLife()});
            if (cargo.getUsersByIdUser() != null) {

            }
            i++;
        }


    }

    private UpdateCargo updateCargoWindow;
    private AddCargo addCargoWindow;
    private Integer idStock;
    private javax.swing.JButton addCargo;
    private javax.swing.JButton deleteCargo;
    private javax.swing.JButton updateCargo;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel captionLabel;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable cargoTable;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return null;
    }
}
