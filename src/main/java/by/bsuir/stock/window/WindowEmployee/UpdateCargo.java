package by.bsuir.stock.window.WindowEmployee;

import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.bean.CargoEntity;
import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.sql.Date;

public class UpdateCargo extends javax.swing.JDialog {

    public UpdateCargo(JFrame owner, CargoEntity cargo) {
        super(owner,"Изменение",true);
        this.cargo = cargo;
        initComponents();
    }

    public UpdateCargo() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        captionLabel = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        amountLabel = new javax.swing.JLabel();
        amountText = new javax.swing.JTextField();
        priceText = new javax.swing.JTextField();
        updateCargo = new javax.swing.JButton();
        backCargo = new javax.swing.JButton();
        dateLabel = new javax.swing.JLabel();
        dateText = new JCalendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nameLabel.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        nameLabel.setText("Введите наименование груза:");

        priceLabel.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        priceLabel.setText("Введите цену груза:");

        captionLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        captionLabel.setText("Изменение");

        amountLabel.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        amountLabel.setText("Введите объем груза:");

        updateCargo.setText("Изменить");
        updateCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCargoActionPerformed(evt);
            }
        });

        backCargo.setText("Закрыть");
        backCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backCargoActionPerformed(evt);
            }
        });

        dateLabel.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        dateLabel.setText("Введите срок годности:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(208, 208, 208)
                                                .addComponent(captionLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
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
                                                                        .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(57, 57, 57)
                                                .addComponent(dateLabel)
                                                .addGap(41, 41, 41)
                                                .addComponent(dateText)))
                                .addContainerGap(43, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(backCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(updateCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(132, 132, 132))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(captionLabel)
                                .addGap(27, 27, 27)
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dateLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addComponent(updateCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(backCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        initText();

        pack();
    }

    private void updateCargoActionPerformed(java.awt.event.ActionEvent evt) {
        CargoEntity cargo = new CargoEntity();
        cargo.setId(this.cargo.getId());
        Batch batch = new Batch();
        batch.setDate(cargo);
        batch.setCommand("GET_CARGO");
        Batch result= by.bsuir.stock.conection.Connection.connection(batch);
        if (result!=null&&result.getResult()){
            this.cargo = (CargoEntity) result.getDate();
            cargo.setId(this.cargo.getId());
            cargo.setName(this.nameText.getText());
            cargo.setPrice(Double.parseDouble(this.priceText.getText()));
            cargo.setAmount(Double.parseDouble(this.amountText.getText()));
            cargo.setStockByIdStock(this.cargo.getStockByIdStock());
            Date lifeDate = new Date(dateText.getDate().getTime());
            cargo.setShelfLife(lifeDate);
            cargo.setReceiptDate(this.cargo.getReceiptDate());

            batch = new Batch();
            batch.setDate(cargo);
            batch.setCommand("UPDATE_CARGO");

            result = by.bsuir.stock.conection.Connection.connection(batch);
            if(result!=null&&result.getResult()){
                JOptionPane.showMessageDialog(new JDialog(),"Груз изменён","Изменение",JOptionPane.WARNING_MESSAGE);
                this.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(new JDialog(),"Ошибка изменения","Ошибка",JOptionPane.ERROR_MESSAGE);
            }

        }else {
            JOptionPane.showMessageDialog(new JDialog(),"Груз не найден","Ошибка измененя",JOptionPane.ERROR_MESSAGE);
        }





    }

    private void backCargoActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
    }

    private void initText(){
        this.nameText.setText(cargo.getName());
        this.priceText.setText(String.valueOf(cargo.getPrice()));
        this.amountText.setText(String.valueOf(cargo.getAmount()));
        this.dateText.setDate(cargo.getShelfLife());
    }

    private CargoEntity cargo;
    private javax.swing.JButton updateCargo;
    private javax.swing.JButton backCargo;
    private javax.swing.JLabel captionLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField nameText;
    private javax.swing.JTextField priceText;
    private javax.swing.JTextField amountText;
    private JCalendar dateText;
}

