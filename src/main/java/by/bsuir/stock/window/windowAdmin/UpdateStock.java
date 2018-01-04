package by.bsuir.stock.window.windowAdmin;

import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.bean.StockEntity;
import by.bsuir.stock.conection.Connection;

import javax.swing.*;

public class UpdateStock extends javax.swing.JDialog {

    public UpdateStock(JFrame owner, StockEntity stock) {
        super(owner, "Добавление", true);
        this.stock = stock;
        initComponents();
    }

    public UpdateStock() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        captionLabel = new javax.swing.JLabel();
        locationText = new javax.swing.JTextField();
        maxAmountText = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        locationLabel = new javax.swing.JLabel();
        amountLabel = new javax.swing.JLabel();
        orAndLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        captionLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        captionLabel.setText("Изменение параметров склада");

        updateButton.setText("Изменить");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        backButton.setText("Закрыть");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        locationLabel.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        locationLabel.setText("Введите распоожение склада:");

        amountLabel.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        amountLabel.setText("Введите максимальный объем:");

        orAndLabel.setText("(и/или)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(149, 149, 149))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(107, 107, 107)
                                                .addComponent(captionLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(locationLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(amountLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(41, 41, 41)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(orAndLabel)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(locationText)
                                                                .addComponent(maxAmountText, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(captionLabel)
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(locationText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(locationLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(orAndLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(maxAmountText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(amountLabel))
                                .addGap(30, 30, 30)
                                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        initText();

        pack();
    }

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.maxAmountText.getText().equals(stock.getMaxAmount()) && this.locationLabel.getText().equals(stock.getLocation())) {
            JOptionPane.showMessageDialog(new JDialog(), "Вы не внесли изменения", "Внимание!", JOptionPane.WARNING_MESSAGE);
        } else {
            Batch batch = new Batch();
            StockEntity stock = new StockEntity();
            stock.setId(this.stock.getId());
            stock.setMaxAmount(Double.parseDouble(this.maxAmountText.getText()));
            stock.setLocation(this.locationText.getText());
            stock.setCongestion(this.stock.getCongestion());
            batch.setDate(stock);
            batch.setCommand("UPDATE_STOCK");

            Batch result = Connection.connection(batch);
            if (result.getResult()) {
                JOptionPane.showMessageDialog(new JDialog(), "Склад успешно изменён", "Изменение склад", JOptionPane.DEFAULT_OPTION);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(new JDialog(), "Неизвестная ошибка", "Ошибка изменения", JOptionPane.ERROR_MESSAGE);
                this.locationText.setText("");
                this.maxAmountText.setText("");

            }
            this.setVisible(false);
        }
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
    }

    private void initText() {
        this.locationText.setText(stock.getLocation());
        this.maxAmountText.setText(String.valueOf(stock.getMaxAmount()));
    }

    private StockEntity stock;
    private javax.swing.JButton updateButton;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel captionLabel;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JLabel orAndLabel;
    private javax.swing.JTextField locationText;
    private javax.swing.JTextField maxAmountText;
}
