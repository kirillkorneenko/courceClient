package by.bsuir.stock.window.windowAdmin;

import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.bean.StockEntity;
import by.bsuir.stock.conection.Connection;

import javax.swing.*;

public class AddStock extends javax.swing.JDialog {

    public AddStock(JFrame owner) {
        super(owner,"Добавление",true);
        initComponents();
    }

    public AddStock() {
        initComponents();
    }

    private void initComponents() {

        captionLabel = new javax.swing.JLabel();
        locationText = new javax.swing.JTextField();
        maxAmountText = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        locationLabel = new javax.swing.JLabel();
        amountLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        captionLabel.setFont(new java.awt.Font("Times New Roman", 1, 24));
        captionLabel.setText("Добавление");

        addButton.setText("Добавить");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        backButton.setText("Закрыть");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        locationLabel.setFont(new java.awt.Font("Times New Roman", 0, 15));
        locationLabel.setText("Введите распоожение склада:");

        amountLabel.setFont(new java.awt.Font("Times New Roman", 0, 15));
        amountLabel.setText("Введите максимальный объем:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(248, Short.MAX_VALUE)
                .addComponent(captionLabel)
                .addGap(267, 267, 267))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(locationLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(amountLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(locationText)
                            .addComponent(maxAmountText, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captionLabel)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locationText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxAmountText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amountLabel))
                .addGap(42, 42, 42)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
      this.setVisible(false);
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Batch batch = new Batch();
        StockEntity stock = new StockEntity();
        stock.setMaxAmount(Double.parseDouble(this.maxAmountText.getText()));
        stock.setLocation(this.locationText.getText());
        stock.setCongestion((double) 0);
        batch.setDate(stock);
        batch.setCommand("ADD_STOCK");

        Batch result = Connection.connection(batch);
        if(result.getResult()){
            JOptionPane.showMessageDialog(new JDialog(),"Склад успешно добавлен","Добавление склад",JOptionPane.DEFAULT_OPTION);
            this.setVisible(false);
        }else {
            JOptionPane.showMessageDialog(new JDialog(),"Неизвестная ошибка","Ошибка добавления",JOptionPane.ERROR_MESSAGE);
            this.locationText.setText("");
            this.maxAmountText.setText("");

        }
    }

    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel captionLabel;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JTextField locationText;
    private javax.swing.JTextField maxAmountText;
}
