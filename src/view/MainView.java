/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JPanel;
import model.Usuario;

/**
 *
 * @author dener
 */
public class MainView extends javax.swing.JFrame {
    
    private final Stack<JPanel> screen;
    private Usuario loggedUser;
    
    /**
     * Creates new form MainView
     */
    public MainView() {
        initComponents();
        this.screen = new Stack<>();
        
        abrirLoginView();
    }
    
    public void abrirCadastrarUsuarioView() {
        JPanel view = new CadastrarUsuarioView(this);
        this.setContentPane(view);
        this.revalidate();
    }
    
    public void abrirAdicionarAulaView() {
        JPanel view = new AdicionarAulaView(this);
        // screen.push(view);
        this.setContentPane(view);
        this.revalidate();
    }
    
    private void abrirLoginView() {
        this.menu.setVisible(false);
        JPanel view = new LoginView(this);
        this.setContentPane(view);
        this.revalidate();
    }
    
    public void voltar() {
        // screen.pop();
        // JPanel view = screen.peek();
        JPanel view = new PaginaInicialView(this);
        this.setContentPane(view);
        this.revalidate();
    }
    
    public void sair() {
        screen.clear();
        abrirLoginView();
        this.menu.setVisible(false);
    }
    
    public void abrirPaginaInicialView(Usuario user) {
        this.menu.setVisible(true);
        JPanel view = new PaginaInicialView(this);
        this.loggedUser = user;
        // screen.add(view);
        this.setContentPane(view);
        this.revalidate();
        
        if(user.getTipoUsuario() == 0) {
            this.adicionarAula.setEnabled(false);
        } else {
            this.adicionarAula.setEnabled(true);
        }
    }
    
    public void abrirPerfilUsuarioView() {
        JPanel view = new PerfilUsuarioView(this, loggedUser);
        // screen.add(view);;
        this.setContentPane(view);
        this.revalidate();
    }
    
    void updateComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 450));
        setResizable(false);

        contentPanel.setMaximumSize(new java.awt.Dimension(700, 400));
        contentPanel.setMinimumSize(new java.awt.Dimension(700, 400));
        contentPanel.setPreferredSize(new java.awt.Dimension(700, 400));
        contentPanel.setLayout(new javax.swing.OverlayLayout(contentPanel));

        jMenu1.setText("Opções");

        adicionarAula.setText("Adicionar Aula");
        adicionarAula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarAulaActionPerformed(evt);
            }
        });
        jMenu1.add(adicionarAula);

        menu.add(jMenu1);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        menu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        adicionarAula = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        perfil = new javax.swing.JMenuItem();
        sair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 450));
        setMinimumSize(new java.awt.Dimension(700, 450));
        setPreferredSize(new java.awt.Dimension(700, 450));
        setResizable(false);

        contentPanel.setMaximumSize(new java.awt.Dimension(700, 400));
        contentPanel.setMinimumSize(new java.awt.Dimension(700, 400));
        contentPanel.setPreferredSize(new java.awt.Dimension(700, 400));
        contentPanel.setLayout(new javax.swing.OverlayLayout(contentPanel));

        jMenu1.setText("Ações");

        adicionarAula.setText("Adicionar Aula");
        adicionarAula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarAulaActionPerformed(evt);
            }
        });
        jMenu1.add(adicionarAula);

        menu.add(jMenu1);

        jMenu2.setText("Conta");

        perfil.setText("Ver Perfil");
        perfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perfilActionPerformed(evt);
            }
        });
        jMenu2.add(perfil);

        sair.setText("Sair");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });
        jMenu2.add(sair);

        menu.add(jMenu2);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarAulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarAulaActionPerformed
        abrirAdicionarAulaView();
    }//GEN-LAST:event_adicionarAulaActionPerformed

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        sair();
    }//GEN-LAST:event_sairActionPerformed

    private void perfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfilActionPerformed
        abrirPerfilUsuarioView();
    }//GEN-LAST:event_perfilActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem adicionarAula;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem perfil;
    private javax.swing.JMenuItem sair;
    // End of variables declaration//GEN-END:variables
}
