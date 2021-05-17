/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.UsuarioController;
import exceptions.UsernameNaoUnico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import persistence.local.AvaliacaoDAO;
import persistence.local.UsuarioDAO;

/**
 *
 * @author dener
 */
public class CadastrarUsuarioView extends javax.swing.JPanel {
    
    private final JFrame context;
    private final UsuarioController usuarioController;
    private int daysOnMonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    private DefaultComboBoxModel<String> modelDay;
    private DefaultComboBoxModel<String> modelMonth;
    private DefaultComboBoxModel<String> modelYear;
    
    private final String DATA_INVALIDA = "Data Inválida";
    private final String DIGITE_UM_NOME = "Digite um nome";
    private final String DIGITE_UM_EMAIL = "Digite um email";
    private final String EMAIL_JA_UTILIZADO = "Email já utilizado";
    
    /**
     * Creates new form CadastrarUsuarioView
     * @param context
     */
    public CadastrarUsuarioView(JFrame context) {
        initComponents();
        this.context = context;
        this.usuarioController = new UsuarioController(new UsuarioDAO(), new AvaliacaoDAO());
        
        this.emailInvalid.setText("");
        this.dataInvalida.setText("");
        this.nomeInvalido.setText("");
        
        modelDay = new DefaultComboBoxModel<>();
        modelDay.addElement("Dia");
        for(int i=1; i<=31; ++i) modelDay.addElement(Integer.toString(i));
        
        modelMonth = new DefaultComboBoxModel<>();
        modelMonth.addElement("Mês");
        for(int i=1; i<=12; ++i) modelMonth.addElement(Integer.toString(i));
        
        modelYear = new DefaultComboBoxModel<>();
        modelYear.addElement("Ano");
        for(int i=2021; i>=2021-120; --i) modelYear.addElement(Integer.toString(i));
        
        
        this.selectDay.setModel(modelDay);
        this.selectMonth.setModel(modelMonth);
        this.selectYear.setModel(modelYear);
        
        this.btnGroup.add(btnAluno);
        this.btnGroup.add(btnProfessor);
        this.btnAluno.setSelected(true);
        
        academic.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EM incompleto", "EM completo", "Graduação", "Mestrado", "Doutorado" }));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup = new javax.swing.ButtonGroup();
        userName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        userEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        academic = new javax.swing.JComboBox<>();
        save = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dataInvalida = new javax.swing.JLabel();
        emailInvalid = new javax.swing.JLabel();
        nomeInvalido = new javax.swing.JLabel();
        selectDay = new javax.swing.JComboBox<>();
        selectMonth = new javax.swing.JComboBox<>();
        selectYear = new javax.swing.JComboBox<>();
        btnAluno = new javax.swing.JRadioButton();
        btnProfessor = new javax.swing.JRadioButton();

        setMaximumSize(new java.awt.Dimension(700, 400));
        setMinimumSize(new java.awt.Dimension(700, 400));

        userName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Nome:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Email:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Formação:");

        userEmail.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Descrição:");

        description.setColumns(20);
        description.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        description.setRows(5);
        jScrollPane1.setViewportView(description);

        academic.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        academic.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        academic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                academicActionPerformed(evt);
            }
        });

        save.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        save.setText("Salvar");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        cancel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cancel.setText("Cancelar");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Data de nascimento:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Cadastro de Usuário");

        dataInvalida.setForeground(new java.awt.Color(255, 0, 0));
        dataInvalida.setText("Data inválida");

        emailInvalid.setForeground(new java.awt.Color(255, 0, 0));
        emailInvalid.setText("Email já utilizado");

        nomeInvalido.setForeground(new java.awt.Color(255, 0, 0));
        nomeInvalido.setText("Digite um nome");

        selectDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        selectDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDayActionPerformed(evt);
            }
        });

        selectMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        selectMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMonthActionPerformed(evt);
            }
        });

        selectYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAluno.setText("Aluno");
        btnAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlunoActionPerformed(evt);
            }
        });

        btnProfessor.setText("Professor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cancel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(save))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nomeInvalido))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(emailInvalid))
                            .addComponent(userEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dataInvalida))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(selectDay, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(selectMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(selectYear, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(academic, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnAluno)
                                        .addComponent(btnProfessor)))))
                        .addGap(10, 10, 10)))
                .addComponent(jLabel2)
                .addContainerGap(80, Short.MAX_VALUE))
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(nomeInvalido)
                            .addComponent(dataInvalida))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(emailInvalid)
                            .addComponent(btnAluno))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(academic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnProfessor))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(save)
                            .addComponent(cancel))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        ((MainView) context).sair();
    }//GEN-LAST:event_cancelActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        this.emailInvalid.setText("");
        this.dataInvalida.setText("");
        this.nomeInvalido.setText("");
        
        String nome = this.userName.getText();
        String email = this.userEmail.getText();
        boolean nomeOk = nome.length() > 0;
        boolean dataOk = checkData();
        boolean emailOk = email.length() > 0;
        
        if(nomeOk && dataOk && emailOk) {
            
            Date date = new Date();
            try {
                usuarioController.addUser(email, nome, (String)this.academic.getSelectedItem(), date, this.description.getText(), (btnAluno.isSelected() ? 0 : 1));
                
                // go to login
                ((MainView) context).sair();
                
            } catch(UsernameNaoUnico e) {
                this.emailInvalid.setText(EMAIL_JA_UTILIZADO);
            }
            
        } else {
            if(!nomeOk) this.nomeInvalido.setText(DIGITE_UM_NOME);
            if(!dataOk) this.dataInvalida.setText(DATA_INVALIDA);
            if(!emailOk) this.emailInvalid.setText(DIGITE_UM_EMAIL);
        }
        
        
    }//GEN-LAST:event_saveActionPerformed

    private void selectDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDayActionPerformed

    private void selectMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMonthActionPerformed

    private void btnAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlunoActionPerformed

    private void academicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_academicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_academicActionPerformed
    
    private boolean checkData() {
        if(selectDay.getSelectedIndex() == 0 || 
                selectMonth.getSelectedIndex() == 0 ||
                selectYear.getSelectedIndex() == 0) return false;
        
        int d = strToInt((String) selectDay.getSelectedItem());
        int m = strToInt((String) selectMonth.getSelectedItem());
        int y = strToInt((String) selectYear.getSelectedItem());
        
        if(isLeapYear(y) && m == 2 && d <= daysOnMonth[m] + 1) return true;
        return d <= daysOnMonth[m];
    }
    
    private boolean isLeapYear(int year) {
        if(year%400 == 0) return true;
        if(year%100 == 0) return false;
        if(year%4 == 0) return true;
        return false;
    }
    
    private int strToInt(String s) {
        int val = 0;
        try {
            val = Integer.parseInt(s);
        } catch(Exception e) {
            System.out.println("this exception should never appear. CadastrarUsuario.strToInt()");
        }
        return val;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> academic;
    private javax.swing.JRadioButton btnAluno;
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JRadioButton btnProfessor;
    private javax.swing.JButton cancel;
    private javax.swing.JLabel dataInvalida;
    private javax.swing.JTextArea description;
    private javax.swing.JLabel emailInvalid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nomeInvalido;
    private javax.swing.JButton save;
    private javax.swing.JComboBox<String> selectDay;
    private javax.swing.JComboBox<String> selectMonth;
    private javax.swing.JComboBox<String> selectYear;
    private javax.swing.JTextField userEmail;
    private javax.swing.JTextField userName;
    // End of variables declaration//GEN-END:variables
}