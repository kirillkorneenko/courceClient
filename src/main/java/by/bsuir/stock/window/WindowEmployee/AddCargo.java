package by.bsuir.stock.window.WindowEmployee;

import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.bean.CargoEntity;
import by.bsuir.stock.bean.StockEntity;
import by.bsuir.stock.conection.Connection;
import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;

public class AddCargo extends javax.swing.JDialog {

    public AddCargo(JFrame owner,Integer idStock) {
        super(owner,"Добавление",true);
        this.idStock = idStock;
        initComponents();
    }
    public AddCargo() {
        initComponents();
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        priceText = new javax.swing.JTextField();
        addCargo = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        captionLabel = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        amountLabel = new javax.swing.JLabel();
        amountText = new javax.swing.JTextField();
        quantityLabel = new javax.swing.JLabel();
        quantityText = new javax.swing.JTextField();
        shelfLifeLabel = new javax.swing.JLabel();
        shelfLifeText = new JCalendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        addCargo.setText("Добавить");
        addCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCargoActionPerformed(evt);
            }
        });

        backButton.setText("Закрыть");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        nameLabel.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        nameLabel.setText("Введите наименование товара:");

        priceLabel.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        priceLabel.setText("Введите цену груза:");

        captionLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        captionLabel.setText("Добавление");

        amountLabel.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        amountLabel.setText("Введите объем груза:");

        quantityLabel.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        quantityLabel.setText("Введите количество:");

        shelfLifeLabel.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        shelfLifeLabel.setText("Введите срок годности:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(amountLabel)
                        .addGap(41, 41, 41)
                        .addComponent(amountText, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(priceLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameText)
                            .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(quantityLabel)
                            .addComponent(shelfLifeLabel))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(quantityText, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                            .addComponent(shelfLifeText))))
                .addContainerGap(92, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(captionLabel)
                        .addGap(267, 267, 267))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(180, 180, 180))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captionLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amountText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amountLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quantityLabel)
                    .addComponent(quantityText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shelfLifeText, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shelfLifeLabel))
                .addGap(28, 28, 28)
                .addComponent(addCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void addCargoActionPerformed(java.awt.event.ActionEvent evt) {
        Batch batch = new Batch();
        java.util.Date da = new java.util.Date();
        Date date = new Date(da.getTime());
        ArrayList<CargoEntity> list = new ArrayList<>();
        StockEntity stock = new StockEntity();
        stock.setId(idStock);
        for (int i = 0; i <Integer.parseInt(quantityText.getText()); i++) {
            CargoEntity cargo = new CargoEntity();
            cargo.setName(this.nameText.getText());
            cargo.setPrice(Double.parseDouble(this.priceText.getText()));
            cargo.setAmount(Double.parseDouble(this.amountText.getText()));
            cargo.setStockByIdStock(stock);
            Date lifeDate = new Date(shelfLifeText.getDate().getTime());
            cargo.setShelfLife(lifeDate);
            cargo.setReceiptDate(Date.valueOf(date.toString()));
           list.add(cargo);
        }
         batch.setList(list);
        batch.setCommand("ADD_CARGOS");

        Batch result = Connection.connection(batch);
        if(result.getResult()){
            JOptionPane.showMessageDialog(new JDialog(),"Грузы успешно добавлены","Добавление груов",JOptionPane.DEFAULT_OPTION);
            this.setVisible(false);
        }else {
            JOptionPane.showMessageDialog(new JDialog(),"Неизвестная ошибка","Ошибка добавления",JOptionPane.ERROR_MESSAGE);
        }

       this.setVisible(false);
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
    }



    private Integer idStock;
    private javax.swing.JButton addCargo;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel captionLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JLabel shelfLifeLabel;
    private javax.swing.JTextField nameText;
    private javax.swing.JTextField priceText;
    private javax.swing.JTextField amountText;
    private javax.swing.JTextField quantityText;
    private JCalendar shelfLifeText;
}
