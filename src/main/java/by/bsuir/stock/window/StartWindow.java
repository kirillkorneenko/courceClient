package by.bsuir.stock.window;


import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.bean.UsersEntity;
import by.bsuir.stock.conection.Connection;
import by.bsuir.stock.window.WindowEmployee.StartEmployee;
import by.bsuir.stock.window.windowAdmin.StartAdmin;
import by.bsuir.stock.window.windowUser.StartUser;

import javax.swing.*;

public class StartWindow extends javax.swing.JFrame {

    public StartWindow() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        imagePanel1 = new javax.swing.JPanel();
        captionLabel = new javax.swing.JLabel();
        loginText = new javax.swing.JTextField();
        passwordText = new javax.swing.JPasswordField();
        autorizationButton = new javax.swing.JButton();
        registrationButton = new javax.swing.JButton();
        passwordLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        image = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imagePanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        captionLabel.setFont(new java.awt.Font("Verdana", 1, 36));
        captionLabel.setForeground(new java.awt.Color(204, 204, 0));
        captionLabel.setText("АВТОРИЗАЦИЯ");
        imagePanel1.add(captionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 340, 60));

        loginText.setFont(new java.awt.Font("Verdana", 1, 18));
        imagePanel1.add(loginText, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, 290, 40));

        passwordText.setFont(new java.awt.Font("Verdana", 1, 18));
        imagePanel1.add(passwordText, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, 290, 40));

        autorizationButton.setFont(new java.awt.Font("Verdana", 1, 18));
        autorizationButton.setText("Вход");
        autorizationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autorizationButtonActionPerformed(evt);
            }
        });
        imagePanel1.add(autorizationButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, 290, 50));

        registrationButton.setFont(new java.awt.Font("Verdana", 1, 18));
        registrationButton.setText("Регистрация");
        registrationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationButtonActionPerformed(evt);
            }
        });
        imagePanel1.add(registrationButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 420, 290, 40));

        passwordLabel.setFont(new java.awt.Font("Segoe Script", 1, 36));
        passwordLabel.setForeground(java.awt.Color.yellow);
        passwordLabel.setText("Пароль:");
        imagePanel1.add(passwordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, -1));

        loginLabel.setFont(new java.awt.Font("Segoe Script", 1, 36));
        loginLabel.setForeground(java.awt.Color.yellow);
        loginLabel.setText("Логин:");
        imagePanel1.add(loginLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, -1, -1));

        //String root = System.getProperty("user.dir");
        image.setIcon(new javax.swing.ImageIcon("classes/by/bsuir/stock/resource/fon.jpg"));
        imagePanel1.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(imagePanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }

    private void autorizationButtonActionPerformed(java.awt.event.ActionEvent evt) {

        UsersEntity user = new UsersEntity();
        user.setLogin(this.loginText.getText());
        user.setPassword(String.valueOf(this.passwordText.getPassword()));
        Batch batch = new Batch();
        batch.setDate(user);
        batch.setCommand("AUTORIZATION_USER");

        Batch result= Connection.connection(batch);

        if(result.getResult()){
            UsersEntity autorizationUser = (UsersEntity) result.getDate();
            Connection.setUser(autorizationUser);
            switch ( autorizationUser.getStatus()){
                case "user":
                    StartUser windowUser = new StartUser();
                    windowUser.setLocationRelativeTo(null);
                    windowUser.setVisible(true);
                    this.setVisible(false);
                    break;
                case "admin":
                    StartAdmin windowAdmin = new StartAdmin();
                    windowAdmin.setLocationRelativeTo(null);
                    windowAdmin.setVisible(true);
                    this.setVisible(false);
                    break;
                case "employee":
                    StartEmployee windowEmployee = new StartEmployee();
                    windowEmployee.setLocationRelativeTo(null);
                    windowEmployee.setVisible(true);
                    this.setVisible(false);
                    break;
                default:
                    JOptionPane.showMessageDialog(new JDialog(),"Доступ закрыт. Обратитесь к адмиистратору","Ошибка аутентификации",JOptionPane.WARNING_MESSAGE);

                    break;
            }
        }

        else {
            JOptionPane.showMessageDialog(new JDialog(),"Проверьте правильность введенных данных","Ошибка аутентификации",JOptionPane.WARNING_MESSAGE);
            this.loginText.setText("");
            this.passwordText.setText("");
        }

    }

    private void registrationButtonActionPerformed(java.awt.event.ActionEvent evt) {
        RegistrationWindow window = new RegistrationWindow();
         window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.setVisible(false);
    }

    private javax.swing.JButton autorizationButton;
    private javax.swing.JButton registrationButton;
    private javax.swing.JLabel image;
    private javax.swing.JLabel captionLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPanel imagePanel1;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JTextField loginText;
}
