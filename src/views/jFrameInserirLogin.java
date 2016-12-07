package views;

import model.dao.LoginDao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.UsuarioLogin;

/**
 * @author enaldo.souza <enaldo@unitri.edu.br>
 */
public class jFrameInserirLogin extends javax.swing.JFrame {
    
    String tipo_usr  = "";
    String nome_usr  = "";
    String senha_usr = "";
    
    public jFrameInserirLogin() {
        initComponents();

        //ordenando jtable
        DefaultTableModel modelo = (DefaultTableModel) jTableUsuarioLogin.getModel();
        jTableUsuarioLogin.setRowSorter(new TableRowSorter(modelo));
                
        //busca tipo_usr logado - pattern Singleton 
        UsuarioLogin sessao = UsuarioLogin.getInstance();
        tipo_usr  = sessao.getInstance().getTipo_usr();
        nome_usr  = sessao.getInstance().getNome_usr();
        senha_usr = sessao.getInstance().getSenha_usr();

        if(tipo_usr.equals("ADM")){
            jComboBoxTipo.removeAllItems();
            jComboBoxTipo.addItem("ESCOLHA");
            jComboBoxTipo.addItem("ADM");
            jComboBoxTipo.addItem("USR");
            jTextFieldNome.setEditable(true);
            populaJtableUsuarioLoginAdm();
        }else{
            jBInserir.setEnabled(false);
            jButtonRemover.setEnabled(false);
            jComboBoxTipo.setEnabled(false);
            jTextFieldNome.setEditable(false);
            jPasswordFieldSenha.setEditable(false);
            jComboBoxTipo.removeAllItems();
            jComboBoxTipo.addItem("ESCOLHA");
            jComboBoxTipo.addItem("USR");
            populaJtableUsuarioLoginUsr();
        }
    }

    public void populaJtableUsuarioLoginAdm(){
        DefaultTableModel modelo = (DefaultTableModel) jTableUsuarioLogin.getModel();
        modelo.setNumRows(0);
        LoginDao loginDao = new LoginDao();
            for(UsuarioLogin l: loginDao.listaUsuariosLogin()){
                modelo.addRow(new Object[]{
                    l.getNome_usr(),
                    l.getSenha_usr(),
                    l.getTipo_usr()
                });
            }
    }

    public void populaJtableUsuarioLoginUsr(){

        DefaultTableModel modelo = (DefaultTableModel) jTableUsuarioLogin.getModel();
        modelo.setNumRows(0);
        modelo.addRow(new Object[]{nome_usr, senha_usr, tipo_usr});    
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelInserirLogin = new javax.swing.JPanel();
        jBInserir = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jPanelListaUsuarios = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsuarioLogin = new javax.swing.JTable();
        jLCadastroLogin = new javax.swing.JLabel();
        jPanelDados = new javax.swing.JPanel();
        jLabelNome = new javax.swing.JLabel();
        jLabelSenha = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        jLabelTipo = new javax.swing.JLabel();
        jPasswordFieldSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelInserirLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBInserir.setText("Inserir");
        jBInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBInserirActionPerformed(evt);
            }
        });

        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonRemover.setText("Remover");
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonLimpar.setText("Limpar");
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelInserirLoginLayout = new javax.swing.GroupLayout(jPanelInserirLogin);
        jPanelInserirLogin.setLayout(jPanelInserirLoginLayout);
        jPanelInserirLoginLayout.setHorizontalGroup(
            jPanelInserirLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInserirLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBInserir)
                .addGap(18, 18, 18)
                .addComponent(jButtonAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonRemover)
                .addGap(18, 18, 18)
                .addComponent(jButtonLimpar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCancelar)
                .addContainerGap())
        );
        jPanelInserirLoginLayout.setVerticalGroup(
            jPanelInserirLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInserirLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInserirLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBInserir)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonRemover)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonLimpar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelListaUsuarios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTableUsuarioLogin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOME", "SENHA", "TIPO"
            }
        ));
        jTableUsuarioLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableUsuarioLoginMouseClicked(evt);
            }
        });
        jTableUsuarioLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableUsuarioLoginKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableUsuarioLoginKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableUsuarioLogin);

        javax.swing.GroupLayout jPanelListaUsuariosLayout = new javax.swing.GroupLayout(jPanelListaUsuarios);
        jPanelListaUsuarios.setLayout(jPanelListaUsuariosLayout);
        jPanelListaUsuariosLayout.setHorizontalGroup(
            jPanelListaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanelListaUsuariosLayout.setVerticalGroup(
            jPanelListaUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListaUsuariosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLCadastroLogin.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLCadastroLogin.setText("Cadastro de Login");

        jPanelDados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelNome.setText("Nome: *");

        jLabelSenha.setText("Senha:*");

        jTextFieldNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldNomeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNomeKeyTyped(evt);
            }
        });

        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ESCOLHA", "ADM\t", "USR", " " }));
        jComboBoxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoActionPerformed(evt);
            }
        });

        jLabelTipo.setText("Tipo: *");

        javax.swing.GroupLayout jPanelDadosLayout = new javax.swing.GroupLayout(jPanelDados);
        jPanelDados.setLayout(jPanelDadosLayout);
        jPanelDadosLayout.setHorizontalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelSenha)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosLayout.createSequentialGroup()
                                .addComponent(jLabelNome)
                                .addGap(0, 298, Short.MAX_VALUE))
                            .addComponent(jTextFieldNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelDadosLayout.setVerticalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLCadastroLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanelListaUsuarios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelInserirLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelDados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLCadastroLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelInserirLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelListaUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(553, 564));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBInserirActionPerformed
        
        jTextFieldNome.setEnabled(true);
        jPasswordFieldSenha.setEnabled(true);
        
        jTextFieldNome.requestFocus();
        String tipo = String.valueOf(jComboBoxTipo.getSelectedItem());
        String nome = jTextFieldNome.getText().toUpperCase().trim();
        String senha = jPasswordFieldSenha.getText();

        //retira espaços da string nome
        String nomeSemEspaço = nome.replaceAll(" ", "");

        if (String.valueOf(jComboBoxTipo.getSelectedItem()) != "ESCOLHA"
                && nomeSemEspaço.length() > 0 && senha.length() > 0) {
            try {
                UsuarioLogin login = new UsuarioLogin();
                login.setNome_usr(nomeSemEspaço);
                login.setSenha_usr(senha);
                login.setTipo_usr(tipo);

                LoginDao cadastra = new LoginDao();
                cadastra.cadastrarLogin(login);
                
                jTextFieldNome.setText("");
                jPasswordFieldSenha.setText("");
                jComboBoxTipo.setSelectedItem("ESCOLHA");
                
                populaJtableUsuarioLoginAdm();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jBInserirActionPerformed
    
    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTableUsuarioLoginKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableUsuarioLoginKeyReleased
    }//GEN-LAST:event_jTableUsuarioLoginKeyReleased

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        if(jTableUsuarioLogin.getSelectedRow() != -1 && jTextFieldNome.getText() != "" 
            && jPasswordFieldSenha.getText() != ""
            && jComboBoxTipo.getSelectedItem() != "ESCOLHA"){
            jTextFieldNome.setEditable(false);
            if (String.valueOf(jComboBoxTipo.getSelectedItem()) != "ESCOLHA"
                    && jPasswordFieldSenha.getText().length() > 0) {
                try {
                    UsuarioLogin login = new UsuarioLogin();
                    login.setNome_usr(jTableUsuarioLogin.getValueAt(jTableUsuarioLogin.getSelectedRow(), 0).toString());
                    login.setSenha_usr(jPasswordFieldSenha.getText());
                    login.setTipo_usr(String.valueOf(jComboBoxTipo.getSelectedItem()));

                    LoginDao atualiza = new LoginDao();
                    atualiza.atualizarLogin(login);
                    
                    jTextFieldNome.setText("");
                    jPasswordFieldSenha.setText("");
                    jComboBoxTipo.setSelectedItem("ESCOLHA");
                    
                    if(tipo_usr.equals("ADM")){
                        populaJtableUsuarioLoginAdm();
                        jTextFieldNome.setEditable(true);
                    }else{
                        //seta nova senha
                        UsuarioLogin sessao = UsuarioLogin.getInstance();
                        sessao.setSenha_usr(login.getSenha_usr());
                        senha_usr = sessao.getInstance().getSenha_usr();
                        populaJtableUsuarioLoginUsr();
//                        dispose();
                        jTextFieldNome.setEditable(false);
                        jPasswordFieldSenha.setEditable(false);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    jTextFieldNome.setEditable(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!", "Aviso", JOptionPane.WARNING_MESSAGE);
                jTextFieldNome.setEditable(true);
            }        
        }else{
            JOptionPane.showMessageDialog(null, "Escolha usuário para alteração!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jTableUsuarioLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUsuarioLoginMouseClicked
        
        jTextFieldNome.setEditable(false);
        if(jTableUsuarioLogin.getSelectedRow() != -1){
            jTextFieldNome.setEnabled(true);
            jPasswordFieldSenha.setEnabled(true);
            jPasswordFieldSenha.setEditable(true);
            jTextFieldNome.setText(jTableUsuarioLogin.getValueAt(jTableUsuarioLogin.getSelectedRow(), 0).toString());
            jPasswordFieldSenha.setText(jTableUsuarioLogin.getValueAt(jTableUsuarioLogin.getSelectedRow(), 1).toString());
            jComboBoxTipo.setSelectedItem(jTableUsuarioLogin.getValueAt(jTableUsuarioLogin.getSelectedRow(), 2).toString());
        }
    }//GEN-LAST:event_jTableUsuarioLoginMouseClicked

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        if(jTableUsuarioLogin.getSelectedRow() != -1 && jTextFieldNome.getText() != "" 
            && jPasswordFieldSenha.getText() != ""
            && jComboBoxTipo.getSelectedItem() != "ESCOLHA"){
            Object[] options = { "Sim", "Não" }; 
            int opcao = JOptionPane.showOptionDialog(null, "Confirma exclusão?","Excluir", 
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE , null, options, options[0]);

            if(opcao == 0){    
                jTextFieldNome.setEditable(false);
                jPasswordFieldSenha.setEditable(false);
                jComboBoxTipo.setEditable(false);
                if(jTableUsuarioLogin.getSelectedRow() != -1){
                    UsuarioLogin login = new UsuarioLogin();
                    login.setNome_usr(jTableUsuarioLogin.getValueAt(jTableUsuarioLogin.getSelectedRow(), 0).toString());
                    login.setSenha_usr(jPasswordFieldSenha.getText());
                    login.setTipo_usr(String.valueOf(jComboBoxTipo.getSelectedItem()));

                    LoginDao remover = new LoginDao();
                    remover.apagarLogin(login);  
                    jTextFieldNome.setEditable(true);
                    jPasswordFieldSenha.setEditable(true);
                    jComboBoxTipo.setEditable(true);

                    jTextFieldNome.setText("");
                    jPasswordFieldSenha.setText("");
                    jComboBoxTipo.setSelectedItem("ESCOLHA");
                    populaJtableUsuarioLoginAdm();
                }
            }else{
                jTextFieldNome.setEditable(true);
                jPasswordFieldSenha.setEditable(true);
                jComboBoxTipo.setEditable(true);
                jTextFieldNome.setText("");
                jPasswordFieldSenha.setText("");
                jComboBoxTipo.setSelectedItem("ESCOLHA");

            }
        }else{
            JOptionPane.showMessageDialog(null, "Escolha usuário para remoção!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        
        if(tipo_usr.equals("ADM")){
            jTextFieldNome.setText("");
            jPasswordFieldSenha.setText("");
            jComboBoxTipo.setSelectedItem("ESCOLHA");
            jTextFieldNome.setEnabled(true);
            jPasswordFieldSenha.setEnabled(true);
            jTextFieldNome.setEditable(true);
        }else{
            jTextFieldNome.setText("");
            jPasswordFieldSenha.setText("");
            jComboBoxTipo.setSelectedItem("ESCOLHA");
            jTextFieldNome.setEnabled(false);
            jPasswordFieldSenha.setEnabled(false);
            jPasswordFieldSenha.setEditable(false);
        }
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jTableUsuarioLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableUsuarioLoginKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableUsuarioLoginKeyPressed

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
        char c=evt.getKeyChar();
        if((c<'a' || c>'z') && (c<'A' || c>'Z')) evt.consume();
    }//GEN-LAST:event_jTextFieldNomeKeyTyped

    private void jComboBoxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoActionPerformed

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
            java.util.logging.Logger.getLogger(jFrameInserirLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jFrameInserirLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jFrameInserirLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jFrameInserirLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jFrameInserirLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBInserir;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JLabel jLCadastroLogin;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelSenha;
    private javax.swing.JLabel jLabelTipo;
    private javax.swing.JPanel jPanelDados;
    private javax.swing.JPanel jPanelInserirLogin;
    private javax.swing.JPanel jPanelListaUsuarios;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableUsuarioLogin;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables

}
