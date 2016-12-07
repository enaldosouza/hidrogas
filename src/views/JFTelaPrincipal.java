
package views;

import model.bean.UsuarioLogin;
import model.dao.CadastroPessoaDao;
import model.dao.LoginDao;

public class JFTelaPrincipal extends javax.swing.JFrame {
    
    LoginDao login = new LoginDao();

    public JFTelaPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCadastro = new javax.swing.JMenu();
        jMenuPessoa = new javax.swing.JMenu();
        jMenuItemPessoaFisica = new javax.swing.JMenuItem();
        jMenuItemPessoaJuridica = new javax.swing.JMenuItem();
        jMenuItemMaterial = new javax.swing.JMenuItem();
        jMenuItemContasPagar = new javax.swing.JMenuItem();
        jMenuItemContasReceber = new javax.swing.JMenuItem();
        jMenuItemUsuario = new javax.swing.JMenuItem();
        jMenuICidade = new javax.swing.JMenuItem();
        jMenuOrcamento = new javax.swing.JMenu();
        jMenuItemCadOrcamento = new javax.swing.JMenuItem();
        jMenuMovimento = new javax.swing.JMenu();
        jMenuItemMovContasPagar = new javax.swing.JMenuItem();
        jMenuItemMovContasReceber = new javax.swing.JMenuItem();
        jMenuRelatorio = new javax.swing.JMenu();
        jMenuItemRelOrcamento = new javax.swing.JMenuItem();
        jMenuSair = new javax.swing.JMenu();
        jMenuItemSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("frameTelaPrincipal"); // NOI18N

        jMenuCadastro.setText("Cadastro");

        jMenuPessoa.setText("Pessoa");

        jMenuItemPessoaFisica.setText("Física");
        jMenuItemPessoaFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPessoaFisicaActionPerformed(evt);
            }
        });
        jMenuPessoa.add(jMenuItemPessoaFisica);

        jMenuItemPessoaJuridica.setText("Jurídica");
        jMenuItemPessoaJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPessoaJuridicaActionPerformed(evt);
            }
        });
        jMenuPessoa.add(jMenuItemPessoaJuridica);

        jMenuCadastro.add(jMenuPessoa);

        jMenuItemMaterial.setText("Material");
        jMenuItemMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMaterialActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemMaterial);

        jMenuItemContasPagar.setText("Contas a Pagar");
        jMenuCadastro.add(jMenuItemContasPagar);

        jMenuItemContasReceber.setText("Contas a Receber");
        jMenuCadastro.add(jMenuItemContasReceber);

        jMenuItemUsuario.setText("Usuário");
        jMenuItemUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUsuarioActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemUsuario);

        jMenuICidade.setText("Cidade");
        jMenuICidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuICidadeActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuICidade);

        jMenuBar1.add(jMenuCadastro);

        jMenuOrcamento.setText("Orçamento");

        jMenuItemCadOrcamento.setText("Cadastrar ");
        jMenuOrcamento.add(jMenuItemCadOrcamento);

        jMenuBar1.add(jMenuOrcamento);

        jMenuMovimento.setText("Movimento");

        jMenuItemMovContasPagar.setText("Contas a Pagar");
        jMenuMovimento.add(jMenuItemMovContasPagar);

        jMenuItemMovContasReceber.setText("Contas a Receber");
        jMenuMovimento.add(jMenuItemMovContasReceber);

        jMenuBar1.add(jMenuMovimento);

        jMenuRelatorio.setText("Relatório");

        jMenuItemRelOrcamento.setText("Orçamento");
        jMenuRelatorio.add(jMenuItemRelOrcamento);

        jMenuBar1.add(jMenuRelatorio);

        jMenuSair.setText("Sair");

        jMenuItemSair.setText("Sair do Sistema");
        jMenuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSairActionPerformed(evt);
            }
        });
        jMenuSair.add(jMenuItemSair);

        jMenuBar1.add(jMenuSair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 596, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(612, 308));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMaterialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemMaterialActionPerformed

    private void jMenuItemUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUsuarioActionPerformed

        jFrameInserirLogin tela = new jFrameInserirLogin();
        tela.setVisible(true);
        //dispose();
     
    }//GEN-LAST:event_jMenuItemUsuarioActionPerformed

    private void jMenuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSairActionPerformed
        dispose();
//        System.exit(0);
    }//GEN-LAST:event_jMenuItemSairActionPerformed

    private void jMenuICidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuICidadeActionPerformed
        JFCadastroCidade telaCidade = new JFCadastroCidade();
        telaCidade.setVisible(true);
    }//GEN-LAST:event_jMenuICidadeActionPerformed

    private void jMenuItemPessoaFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPessoaFisicaActionPerformed
        
        JFCadastroPessoa cadPessoa = new JFCadastroPessoa();
        cadPessoa.setVisible(true);
        cadPessoa.visualizaPessoaFisica();
//        dispose();
    }//GEN-LAST:event_jMenuItemPessoaFisicaActionPerformed

    private void jMenuItemPessoaJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPessoaJuridicaActionPerformed
                
        JFCadastroPessoa cadPessoa = new JFCadastroPessoa();
        cadPessoa.setVisible(true);
        cadPessoa.visualizaPessoaJuridica();
    }//GEN-LAST:event_jMenuItemPessoaJuridicaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFTelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCadastro;
    private javax.swing.JMenuItem jMenuICidade;
    private javax.swing.JMenuItem jMenuItemCadOrcamento;
    private javax.swing.JMenuItem jMenuItemContasPagar;
    private javax.swing.JMenuItem jMenuItemContasReceber;
    private javax.swing.JMenuItem jMenuItemMaterial;
    private javax.swing.JMenuItem jMenuItemMovContasPagar;
    private javax.swing.JMenuItem jMenuItemMovContasReceber;
    private javax.swing.JMenuItem jMenuItemPessoaFisica;
    private javax.swing.JMenuItem jMenuItemPessoaJuridica;
    private javax.swing.JMenuItem jMenuItemRelOrcamento;
    private javax.swing.JMenuItem jMenuItemSair;
    private javax.swing.JMenuItem jMenuItemUsuario;
    private javax.swing.JMenu jMenuMovimento;
    private javax.swing.JMenu jMenuOrcamento;
    private javax.swing.JMenu jMenuPessoa;
    private javax.swing.JMenu jMenuRelatorio;
    private javax.swing.JMenu jMenuSair;
    // End of variables declaration//GEN-END:variables
}
