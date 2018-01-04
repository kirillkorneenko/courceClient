package by.bsuir.stock.window;

import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.bean.UsersEntity;
import by.bsuir.stock.conection.Connection;

import javax.swing.*;

public class RegistrationWindow extends javax.swing.JFrame {

    public RegistrationWindow() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        imagePanel1 = new javax.swing.JPanel();
        captionLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        registrationButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        repeatPasswordField = new javax.swing.JPasswordField();
        nameText = new javax.swing.JTextField();
        surnameText = new javax.swing.JTextField();
        loginText = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        surnameLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        repeatPasswordLabel = new javax.swing.JLabel();
        image = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        imagePanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        captionLabel.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        captionLabel.setForeground(new java.awt.Color(204, 204, 0));
        captionLabel.setText("РЕГИСТРАЦИЯ");
        imagePanel1.add(captionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 340, 60));

        backButton.setFont(new java.awt.Font("Segoe Script", 1, 18)); // NOI18N
        backButton.setText("Назад");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        imagePanel1.add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 420, 210, 50));

        registrationButton.setFont(new java.awt.Font("Segoe Script", 1, 18)); // NOI18N
        registrationButton.setText("Зарегистрироваться");
        registrationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                    registrationButtonActionPerformed(evt);

            }
        });
        imagePanel1.add(registrationButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, 280, 50));
        imagePanel1.add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 210, 30));

        imagePanel1.add(repeatPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 350, 210, 30));
        imagePanel1.add(nameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 210, 30));
        imagePanel1.add(surnameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, 210, 30));
        imagePanel1.add(loginText, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, 210, 30));

        nameLabel.setFont(new java.awt.Font("Segoe Script", 1, 20)); // NOI18N
        nameLabel.setForeground(java.awt.Color.yellow);
        nameLabel.setText("Введите имя:");
        imagePanel1.add(nameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, -1));

        surnameLabel.setFont(new java.awt.Font("Segoe Script", 1, 20)); // NOI18N
        surnameLabel.setForeground(java.awt.Color.yellow);
        surnameLabel.setText("Введите фамилию:");
        imagePanel1.add(surnameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, -1, -1));

        loginLabel.setFont(new java.awt.Font("Segoe Script", 1, 20)); // NOI18N
        loginLabel.setForeground(java.awt.Color.yellow);
        loginLabel.setText("Введите логин:");
        imagePanel1.add(loginLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, -1, -1));

        passwordLabel.setFont(new java.awt.Font("Segoe Script", 1, 20)); // NOI18N
        passwordLabel.setForeground(java.awt.Color.yellow);
        passwordLabel.setText("Введите пароль:");
        imagePanel1.add(passwordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, -1, -1));

        repeatPasswordLabel.setFont(new java.awt.Font("Segoe Script", 1, 20)); // NOI18N
        repeatPasswordLabel.setForeground(java.awt.Color.yellow);
        repeatPasswordLabel.setText("Повторите пароль:");
        imagePanel1.add(repeatPasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, -1, 20));

        image.setIcon(new javax.swing.ImageIcon("classes/by/bsuir/stock/resource/fon.jpg")); // NOI18N
        imagePanel1.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(imagePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(imagePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        StartWindow window = new StartWindow();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.setVisible(false);
    }

    private void registrationButtonActionPerformed(java.awt.event.ActionEvent evt) {

            if (String.valueOf(this.passwordField.getPassword()).equals(String.valueOf(this.repeatPasswordField.getPassword())) &&
                    this.loginText.getText().length() >= 3) {
                UsersEntity user = new UsersEntity();
                user.setName(this.nameText.getText());
                user.setSurName(this.surnameText.getText());
                user.setLogin(this.loginText.getText());
                user.setStatus("user");
                user.setPassword(String.valueOf(this.passwordField.getPassword()));

                Batch batch = new Batch();
                batch.setDate(user);
                batch.setCommand("ADD_USER");
                Batch result = Connection.connection(batch);
                if (result.getResult()) {
                    JOptionPane.showMessageDialog(new JDialog(), "Регистрация прошла успешно", "Регистрация", JOptionPane.DEFAULT_OPTION);
                    StartWindow window = new StartWindow();
                    window.setLocationRelativeTo(null);
                    window.setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(new JDialog(), "Данный логин уже занят, попробуйте другой", "Ошибка Регистрации", JOptionPane.ERROR_MESSAGE);
                    this.nameText.setText("");
                    this.surnameText.setText("");
                    this.loginText.setText("");
                    this.passwordField.setText("");
                    this.repeatPasswordField.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(new JDialog(), "Проверьте правильность введенных данных", "Ошибка Регистрации", JOptionPane.ERROR_MESSAGE);
                this.nameText.setText("");
                this.passwordField.setText("");
            }
    }

    private javax.swing.JButton backButton;
    private javax.swing.JButton registrationButton;
    private javax.swing.JLabel image;
    private javax.swing.JLabel captionLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel surnameLabel;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel repeatPasswordLabel;
    private javax.swing.JPanel imagePanel1;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPasswordField repeatPasswordField;
    private javax.swing.JTextField nameText;
    private javax.swing.JTextField surnameText;
    private javax.swing.JTextField loginText;
}
