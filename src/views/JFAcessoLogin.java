
package views;

import java.awt.event.KeyEvent;
import model.bean.UsuarioLogin;
import model.dao.LoginDao;
import java.sql.Connection;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JFAcessoLogin extends javax.swing.JFrame {

    UsuarioLogin login = new UsuarioLogin();
    
    public JFAcessoLogin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelUsuario = new javax.swing.JLabel();
        jLabelSenha = new javax.swing.JLabel();
        jPasswordFieldSenha = new javax.swing.JPasswordField();
        jTextFieldNome = new javax.swing.JTextField();
        jButtonAcessar = new javax.swing.JButton();
        jLabelLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabelUsuario.setText("Usuário: *");
        getContentPane().add(jLabelUsuario);
        jLabelUsuario.setBounds(20, 60, 60, 14);

        jLabelSenha.setText("Senha: *");
        getContentPane().add(jLabelSenha);
        jLabelSenha.setBounds(20, 90, 60, 14);

        jPasswordFieldSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(jPasswordFieldSenha);
        jPasswordFieldSenha.setBounds(80, 90, 140, 20);

        jTextFieldNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeActionPerformed(evt);
            }
        });
        jTextFieldNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldNomeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNomeKeyTyped(evt);
            }
        });
        getContentPane().add(jTextFieldNome);
        jTextFieldNome.setBounds(80, 60, 140, 20);

        jButtonAcessar.setText("Acessar");
        jButtonAcessar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcessarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAcessar);
        jButtonAcessar.setBounds(100, 130, 90, 23);

        jLabelLogin.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelLogin.setText("Login");
        jLabelLogin.setName(""); // NOI18N
        getContentPane().add(jLabelLogin);
        jLabelLogin.setBounds(20, 10, 75, 29);

        setSize(new java.awt.Dimension(263, 198));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordFieldSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldSenhaActionPerformed

    private void jTextFieldNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeActionPerformed

    private void jButtonAcessarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcessarActionPerformed
        
        jTextFieldNome.requestFocus();
        
        if(jTextFieldNome.getText().isEmpty() && jPasswordFieldSenha.getText().isEmpty()){
            jTextFieldNome.requestFocus();
            JOptionPane.showMessageDialog(null, "Campo nome e senha são obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }      
        
        login.setNome_usr(jTextFieldNome.getText().toUpperCase());
        login.setSenha_usr(jPasswordFieldSenha.getText().toUpperCase());

        LoginDao logar = new LoginDao();
        boolean retorno = logar.verificarLogin(login);
        
        //busca tipo_usr em LoginDao
        String tipo_usr  = logar.toStringTipo();
        String nome_usr  = logar.toStringNome();
        String senha_usr = logar.toStringSenha();

        //seta tipo_usr
        UsuarioLogin sessao = UsuarioLogin.getInstance();
        sessao.setTipo_usr(tipo_usr);
        sessao.setNome_usr(nome_usr);
        sessao.setSenha_usr(senha_usr);
        
//        Para busca de tipo_usr em qualquer lugar no sistema
//        UsuarioLogin sessao = UsuarioLogin.getInstance();
//        sessao.getInstance().getTipo_usr();
//        System.out.println(sessao.toString());
        
        if(retorno){
            JFTelaPrincipal tela = new JFTelaPrincipal();
            tela.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jButtonAcessarActionPerformed

    private void jTextFieldNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeKeyPressed

        //Coloca esse codigo no evento KY PRESSED DO JTextField
        String numCampo = jTextFieldNome.getText();

        int quantCaracteres = numCampo.length();
        if (quantCaracteres > 10){
            numCampo = numCampo.substring (0, numCampo.length() - 1);
            jTextFieldNome.setText(numCampo);

        }        
    }//GEN-LAST:event_jTextFieldNomeKeyPressed

    private void jTextFieldNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeKeyTyped
        
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
          evt.setKeyChar(Character.toUpperCase(c));
        }
        
        if((Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
            evt.consume();
        }                
    }//GEN-LAST:event_jTextFieldNomeKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFAcessoLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAcessar;
    private javax.swing.JLabel jLabelLogin;
    private javax.swing.JLabel jLabelSenha;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
}
