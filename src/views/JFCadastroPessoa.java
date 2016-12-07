
package views;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Cidade;
import model.bean.Pessoa;
import model.bean.PessoaFisica;
import model.bean.PessoaJuridica;
import model.dao.CadastroPessoaDao;
import model.dao.CidadeDao;
import util.Util;

/**
 * @author enaldo.souza
 */
public class JFCadastroPessoa extends javax.swing.JFrame {

    Util formData             = new Util();
    Util formEmail            = new Util();
    Util formCpf              = new Util();
    Util formCnpj             = new Util();
    Util getDataAtual         = new Util();
    Util verificaIE           = new Util();
    Util verificaQtdeDigitos  = new Util();
    Util removeCaracEspeciais = new Util();
    Pessoa pessoa             = new Pessoa();   
    CadastroPessoaDao pessoas = new CadastroPessoaDao();    
    PessoaFisica pFisica      = new PessoaFisica();  
    PessoaJuridica pJur       = new PessoaJuridica();
    CidadeDao cidadeDao       = new CidadeDao();
    Cidade cidade             = new Cidade();
    int habilitaInsercao = 0;
    
    ArrayList<String> arrCidEst = new ArrayList<>();
    
    public JFCadastroPessoa() {
        initComponents();
        
        for(Cidade c: cidadeDao.listaCidades()){
            jComboBoxCidadeEndereco.addItem(c.getDesc_cidade().trim());
            jComboBoxEstadoEnd.addItem(c.getEstado_cidade().trim());
            jCBcodCidade.addItem(c.getCod_cidade().trim());
        }
        
        //agrupa cidade+estado de dois combobox em um array
        int aux = jComboBoxCidadeEndereco.getItemCount();
        for (int i = 0; i < aux; i++) {
            arrCidEst.add(jCBcodCidade.getItemAt(i).trim() + "-" + jComboBoxCidadeEndereco.getItemAt(i).trim()
                          + "-" +jComboBoxEstadoEnd.getItemAt(i).trim());
        }
        
        jComboBoxEstadoEnd.setEnabled(false);
        jCBcodCidade.setEnabled(false);
        
        //estados
        jCBestadoIden.addItem("ESCOLHA");
        jCBestadoIden.addItem("AC"); //13
        jCBestadoIden.addItem("AL"); //9
        jCBestadoIden.addItem("AP"); //9
        jCBestadoIden.addItem("AM"); //9
        jCBestadoIden.addItem("BA"); //9
        jCBestadoIden.addItem("CE"); //9
        jCBestadoIden.addItem("DF"); //13
        jCBestadoIden.addItem("ES"); //9
        jCBestadoIden.addItem("GO"); //9
        jCBestadoIden.addItem("MA"); //9
        jCBestadoIden.addItem("MT"); //11
        jCBestadoIden.addItem("MS"); //9
        jCBestadoIden.addItem("MG"); //13
        jCBestadoIden.addItem("PA"); //9
        jCBestadoIden.addItem("PB"); //9
        jCBestadoIden.addItem("PR"); //10
        jCBestadoIden.addItem("PE"); //14
        jCBestadoIden.addItem("PI"); //9
        jCBestadoIden.addItem("RJ"); //8
        jCBestadoIden.addItem("RN"); //ie.length() != 10 && ie.length() != 9
        jCBestadoIden.addItem("RS"); //10
        jCBestadoIden.addItem("RO"); //14
        jCBestadoIden.addItem("RR"); //9
        jCBestadoIden.addItem("SC"); //9
        jCBestadoIden.addItem("SP"); //ie.length() != 12 && ie.length() != 13
        jCBestadoIden.addItem("SE"); //9
        jCBestadoIden.addItem("TO"); //ie.length() != 9 && ie.length() != 11
        
        //orgãos expeditores
        jComboBoxOrgExpeditor.addItem("ESCOLHA");
        jComboBoxOrgExpeditor.addItem("SSP  - Secretaria de Segurança Pública");
        jComboBoxOrgExpeditor.addItem("PM   - Polícia Militar");
        jComboBoxOrgExpeditor.addItem("PC   - Policia Civil");
        jComboBoxOrgExpeditor.addItem("CNT  - Carteira Nacional de Habilitação");
        jComboBoxOrgExpeditor.addItem("IC   - Diretoria de Identificação Civil");
        jComboBoxOrgExpeditor.addItem("CTPS - Carteira de Trabaho e Previdência Social");
        jComboBoxOrgExpeditor.addItem("FGTS - Fundo de Garantia do Tempo de Serviço");
        jComboBoxOrgExpeditor.addItem("IFP  - Instituto Félix Pacheco");
        jComboBoxOrgExpeditor.addItem("IPF  - Instituto Pereira Faustino");
        jComboBoxOrgExpeditor.addItem("IML  - Instituto Médico-Legal");
        jComboBoxOrgExpeditor.addItem("MTE  - Ministério do Trabalho e Emprego");
        jComboBoxOrgExpeditor.addItem("MMA  - Ministério da Marinha");
        jComboBoxOrgExpeditor.addItem("MAE  - Ministério da Aeronáutica");
        jComboBoxOrgExpeditor.addItem("MEX  - Ministério do Exército");
        jComboBoxOrgExpeditor.addItem("POF  - Polícia Federal");
        jComboBoxOrgExpeditor.addItem("POM  - Polícia Militar");
        jComboBoxOrgExpeditor.addItem("SES  - Carteira de Estrangeiro");
        jComboBoxOrgExpeditor.addItem("SJS  - Secretaria da Justiça e Segurança");
        jComboBoxOrgExpeditor.addItem("SJTS - Secretaria da Justiça do Trabalho e Segurança");
        jComboBoxOrgExpeditor.addItem("ZZZ  - Outros (inclusive exterior) ");
        
        populajTcadastroPessoa();
        
        //ordenando jtable
        DefaultTableModel modelo = (DefaultTableModel) jTcadastroPessoa.getModel();
        jTcadastroPessoa.setRowSorter(new TableRowSorter(modelo));
        
    }
    
    public void populajTcadastroPessoa(){
        DefaultTableModel modelo = (DefaultTableModel)jTcadastroPessoa.getModel();
        modelo.setNumRows(0);
        for(Pessoa p: pessoas.listaPessoas()){
            modelo.addRow(new Object[]{
                p.getCod_pessoa(),
                p.getCidadeCodCidade(),
                p.getNome(),
                p.getTipo_pessoa(),
                p.getTelefone_res(),
                p.getTelefone_com(),
                p.getCelular(),
                p.getLogradouro(),
                p.getTipo_logradouro(),
                p.getNumLogradouro(),
                p.getBairro(),
                p.getCep(),
                p.getUf(),
                p.getDt_cadastro()
            });
        }
    }
    public void visualizaPessoaFisica(){

        jTFnomeFantasia.setEditable(false);
        jTFInscricaoEstadual.setEditable(false);
        jFormattedTFinscricaoMunicipal.setEditable(false);
        jFormattedTextFcnpj.setEditable(false);
        habilitaInsercao = 1;
    }
    
    public void visualizaPessoaJuridica(){

        jTFnomePessoa.setEditable(false);
        jFormattedTextFdataNascPessoa.setEditable(false);
        jRadioButtonFeminino.setEnabled(false);
        jRadioButtonMasculino.setEnabled(false);
        jTFidentidade.setEditable(false);
        jCBestadoIden.setEnabled(false);
        jComboBoxOrgExpeditor.setEnabled(false);
        jTextFieldNomeMae.setEditable(false);
        jTextFieldNomePai.setEditable(false);
        jFormattedTextCpf.setEditable(false);
        habilitaInsercao = 2;
    }

    public void limparCamposFormPessoa(){
    
        jCBcodCidade.addItem("ESCOLHA");
        jComboBoxCidadeEndereco.addItem("ESCOLHA'");
        jTFnomePessoa.setText("");
        jFormattedTextFdataNascPessoa.setText("");
        jTFidentidade.setText("");  
        jCBestadoIden.setSelectedItem("ESCOLHA");
        jComboBoxOrgExpeditor.setSelectedItem("ESCOLHA");
        jTextfEmailPessoa.setText("");
        jFormattedTextCpf.setText("");
        jFormattedTextFcnpj.setText("");
        jTextFieldNomeMae.setText("");
        jTextFieldNomePai.setText("");  
        jTextFieldEnderecoPessoa.setText("");
        jTextFieldNumero.setText("");
        jTextFieldComplemento.setText("");
        jTextFieldBairro.setText("");
        jComboBoxCidadeEndereco.setSelectedItem("ESCOLHA");
        jFormattedTextFieldCep.setText("");        
        jFormattedTextFieldTelefonePessoaRes.setText("");
        jFormattedTextFieldCelularPessoa.setText("");
        jTFnomeFantasia.setText("");
        jFormattedTextFieldTelefonePessoaComl.setText("");
        jTFInscricaoEstadual.setText("");
        jFormattedTFinscricaoMunicipal.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupSexo = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPCadastroPessoa = new javax.swing.JPanel();
        jBinserirPessoa = new javax.swing.JButton();
        jBalterarPessoa = new javax.swing.JButton();
        jBremoverPessoa = new javax.swing.JButton();
        jBlimparPessoa = new javax.swing.JButton();
        jBcancelarPessoa = new javax.swing.JButton();
        jLabelCadastroPessoa = new javax.swing.JLabel();
        jPanelInformacoesGerais = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTcadastroPessoa = new javax.swing.JTable();
        jTabbedPaneCadastroDadosPessoais = new javax.swing.JTabbedPane();
        jPanelDadosPessoais = new javax.swing.JPanel();
        jLabelNomePessoa = new javax.swing.JLabel();
        jTFnomePessoa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextCpf = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextFcnpj = new javax.swing.JFormattedTextField();
        jLBemailPessoa = new javax.swing.JLabel();
        jTextfEmailPessoa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextFdataNascPessoa = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLidentidade = new javax.swing.JLabel();
        jTFidentidade = new javax.swing.JTextField();
        jLestadoIdentidade = new javax.swing.JLabel();
        jCBestadoIden = new javax.swing.JComboBox<>();
        jLorgaoExpeditor = new javax.swing.JLabel();
        jComboBoxOrgExpeditor = new javax.swing.JComboBox<>();
        jLabelSexo = new javax.swing.JLabel();
        jRadioButtonFeminino = new javax.swing.JRadioButton();
        jRadioButtonMasculino = new javax.swing.JRadioButton();
        jLabelNomeMae = new javax.swing.JLabel();
        jTextFieldNomeMae = new javax.swing.JTextField();
        jLabelNomePai = new javax.swing.JLabel();
        jTextFieldNomePai = new javax.swing.JTextField();
        jTFnomeFantasia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLInscricaoEstadual = new javax.swing.JLabel();
        jLInscricaoMunicipal = new javax.swing.JLabel();
        jFormattedTFinscricaoMunicipal = new javax.swing.JFormattedTextField();
        jTFInscricaoEstadual = new javax.swing.JTextField();
        jPanelDadosComplementares = new javax.swing.JPanel();
        jLabelEnderecoPessoa = new javax.swing.JLabel();
        jTextFieldEnderecoPessoa = new javax.swing.JTextField();
        jLabelNumero = new javax.swing.JLabel();
        jTextFieldNumero = new javax.swing.JTextField();
        jLabelComplemento = new javax.swing.JLabel();
        jTextFieldComplemento = new javax.swing.JTextField();
        jLabelBairro = new javax.swing.JLabel();
        jTextFieldBairro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabelCidade = new javax.swing.JLabel();
        jLabelCep = new javax.swing.JLabel();
        jFormattedTextFieldCep = new javax.swing.JFormattedTextField();
        jLabelTelefoneRes = new javax.swing.JLabel();
        jFormattedTextFieldTelefonePessoaRes = new javax.swing.JFormattedTextField();
        jLabelCelularPessoa = new javax.swing.JLabel();
        jFormattedTextFieldCelularPessoa = new javax.swing.JFormattedTextField();
        jComboBoxEstadoEnd = new javax.swing.JComboBox<>();
        jLabelFoneComlPessoa = new javax.swing.JLabel();
        jFormattedTextFieldTelefonePessoaComl = new javax.swing.JFormattedTextField();
        jComboBoxCidadeEndereco = new javax.swing.JComboBox<>();
        jLcodCidade = new javax.swing.JLabel();
        jCBcodCidade = new javax.swing.JComboBox<>();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPCadastroPessoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBinserirPessoa.setText("Inserir");
        jBinserirPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBinserirPessoaActionPerformed(evt);
            }
        });

        jBalterarPessoa.setText("Alterar");
        jBalterarPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBalterarPessoaActionPerformed(evt);
            }
        });

        jBremoverPessoa.setText("Remover");

        jBlimparPessoa.setText("Limpar");
        jBlimparPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlimparPessoaActionPerformed(evt);
            }
        });

        jBcancelarPessoa.setText("Cancelar");
        jBcancelarPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcancelarPessoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPCadastroPessoaLayout = new javax.swing.GroupLayout(jPCadastroPessoa);
        jPCadastroPessoa.setLayout(jPCadastroPessoaLayout);
        jPCadastroPessoaLayout.setHorizontalGroup(
            jPCadastroPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCadastroPessoaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBinserirPessoa)
                .addGap(17, 17, 17)
                .addComponent(jBalterarPessoa)
                .addGap(18, 18, 18)
                .addComponent(jBremoverPessoa)
                .addGap(18, 18, 18)
                .addComponent(jBlimparPessoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBcancelarPessoa)
                .addContainerGap())
        );
        jPCadastroPessoaLayout.setVerticalGroup(
            jPCadastroPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPCadastroPessoaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPCadastroPessoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBinserirPessoa)
                    .addComponent(jBalterarPessoa)
                    .addComponent(jBremoverPessoa)
                    .addComponent(jBlimparPessoa)
                    .addComponent(jBcancelarPessoa))
                .addContainerGap())
        );

        jLabelCadastroPessoa.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabelCadastroPessoa.setText("Cadastro de Pessoa");

        jPanelInformacoesGerais.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTcadastroPessoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COD_PESSOA", "NOME PESSOA", "TIPO_PESSOA", "CPF", "RG", "DATA_NASC", "TELEFONE_RESIDENCIAL", "TELEFONE_COMERCIAL", "TELEFONE_CELULAR", "CNPJ", "IE", "IM", "NOME_FANTASIA", "COD_CIDADE", "NOME_CIDADE", "LOGRADOURO", "TIPO", "NÚMERO", "BAIRRO", "CEP", "ESTADO", "DATA_CADASTRO", "DATA_DESATIVACAO"
            }
        ));
        jTcadastroPessoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTcadastroPessoaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTcadastroPessoa);

        javax.swing.GroupLayout jPanelInformacoesGeraisLayout = new javax.swing.GroupLayout(jPanelInformacoesGerais);
        jPanelInformacoesGerais.setLayout(jPanelInformacoesGeraisLayout);
        jPanelInformacoesGeraisLayout.setHorizontalGroup(
            jPanelInformacoesGeraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInformacoesGeraisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanelInformacoesGeraisLayout.setVerticalGroup(
            jPanelInformacoesGeraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInformacoesGeraisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabelNomePessoa.setText("Nome: *");

        jTFnomePessoa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFnomePessoaKeyTyped(evt);
            }
        });

        jLabel2.setText("Cpf: *");

        try {
            jFormattedTextCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextCpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextCpfKeyTyped(evt);
            }
        });

        jLabel3.setText("Cnpj: *");

        try {
            jFormattedTextFcnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFcnpj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFcnpjActionPerformed(evt);
            }
        });
        jFormattedTextFcnpj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextFcnpjKeyTyped(evt);
            }
        });

        jLBemailPessoa.setText("E-mail: *  (exemplo: email@teste.com.br)");

        jTextfEmailPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextfEmailPessoaActionPerformed(evt);
            }
        });
        jTextfEmailPessoa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextfEmailPessoaKeyTyped(evt);
            }
        });

        jLabel4.setText("Data Nasc: *");

        try {
            jFormattedTextFdataNascPessoa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFdataNascPessoa.setToolTipText("");
        jFormattedTextFdataNascPessoa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextFdataNascPessoaKeyTyped(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLidentidade.setText("Identidae: *");

        jTFidentidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFidentidadeActionPerformed(evt);
            }
        });
        jTFidentidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFidentidadeKeyTyped(evt);
            }
        });

        jLestadoIdentidade.setText("Estado: *");

        jCBestadoIden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ESCOLHA", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        jLorgaoExpeditor.setText("Orgão Expeditor: *");

        jComboBoxOrgExpeditor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ESCOLHA", "SSP - Secretaria de Segurança Pública", "PM - Polícia Militar", "PC - Policia Civil", "CNT - Carteira Nacional de Habilitação", "IC - Diretoria de Identificação Civil", "CTPS - Carteira de Trabaho e Previdência Social", "FGTS - Fundo de Garantia do Tempo de Serviço", "IFP - Instituto Félix Pacheco", "IPF - Instituto Pereira Faustino", "IML - Instituto Médico-Legal", "MTE - Ministério do Trabalho e Emprego", "MMA - Ministério da Marinha", "MAE - Ministério da Aeronáutica", "MEX - Ministério do Exército", "POF - Polícia Federal", "POM - Polícia Militar", "SES - Carteira de Estrangeiro", "SJS - Secretaria da Justiça e Segurança", "SJTS - Secretaria da Justiça do Trabalho e Segurança", "ZZZ - Outros (inclusive exterior)" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLidentidade)
                    .addComponent(jTFidentidade, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLestadoIdentidade)
                    .addComponent(jCBestadoIden, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxOrgExpeditor, 0, 1, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLorgaoExpeditor)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLestadoIdentidade)
                    .addComponent(jLorgaoExpeditor)
                    .addComponent(jLidentidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBestadoIden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFidentidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxOrgExpeditor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabelSexo.setText("Sexo: *");

        buttonGroupSexo.add(jRadioButtonFeminino);
        jRadioButtonFeminino.setText("FEMININO");
        jRadioButtonFeminino.setActionCommand("feminino");

        buttonGroupSexo.add(jRadioButtonMasculino);
        jRadioButtonMasculino.setSelected(true);
        jRadioButtonMasculino.setText("MASCULINO");
        jRadioButtonMasculino.setActionCommand("masculino");

        jLabelNomeMae.setText("Nome da Mãe: *");

        jTextFieldNomeMae.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNomeMaeKeyTyped(evt);
            }
        });

        jLabelNomePai.setText("Nome do Pai: *");

        jTextFieldNomePai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNomePaiKeyTyped(evt);
            }
        });

        jTFnomeFantasia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFnomeFantasiaKeyTyped(evt);
            }
        });

        jLabel6.setText("Nome Fantasia: *");

        jLInscricaoEstadual.setText("Inscrição Estadual: *");

        jLInscricaoMunicipal.setText("Inscrição Municipal: *");

        try {
            jFormattedTFinscricaoMunicipal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#############")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanelDadosPessoaisLayout = new javax.swing.GroupLayout(jPanelDadosPessoais);
        jPanelDadosPessoais.setLayout(jPanelDadosPessoaisLayout);
        jPanelDadosPessoaisLayout.setHorizontalGroup(
            jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                        .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNomeMae)
                            .addComponent(jTextFieldNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNomePai)
                            .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                                .addComponent(jLabelNomePai)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosPessoaisLayout.createSequentialGroup()
                        .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTFnomePessoa, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosPessoaisLayout.createSequentialGroup()
                                .addComponent(jLabelNomePessoa)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosPessoaisLayout.createSequentialGroup()
                                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextFdataNascPessoa))
                                .addGap(32, 32, 32)
                                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                                        .addComponent(jRadioButtonFeminino)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jRadioButtonMasculino))
                                    .addComponent(jLabelSexo)))
                            .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextfEmailPessoa, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                                        .addComponent(jLBemailPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                        .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jFormattedTextCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jFormattedTextFcnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(352, 352, 352))
                            .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jTFnomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLInscricaoEstadual)
                                    .addComponent(jTFInscricaoEstadual, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLInscricaoMunicipal)
                            .addComponent(jFormattedTFinscricaoMunicipal, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanelDadosPessoaisLayout.setVerticalGroup(
            jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                        .addComponent(jLabelNomePessoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFnomePessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFdataNascPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addComponent(jLabel4))
                        .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                            .addComponent(jLabelSexo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioButtonFeminino)
                                .addComponent(jRadioButtonMasculino)))))
                .addGap(18, 18, 18)
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosPessoaisLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLBemailPessoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextfEmailPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNomePai)
                    .addComponent(jLabelNomeMae))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNomePai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLInscricaoEstadual)
                    .addComponent(jLInscricaoMunicipal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFnomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFInscricaoEstadual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTFinscricaoMunicipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(12, 12, 12)
                .addGroup(jPanelDadosPessoaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFcnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPaneCadastroDadosPessoais.addTab("Dados Pessoais", jPanelDadosPessoais);

        jLabelEnderecoPessoa.setText("Endereço: *");

        jTextFieldEnderecoPessoa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldEnderecoPessoaKeyTyped(evt);
            }
        });

        jLabelNumero.setText("Número: *");

        jTextFieldNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNumeroKeyTyped(evt);
            }
        });

        jLabelComplemento.setText("Complemento: *");

        jTextFieldComplemento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldComplementoKeyTyped(evt);
            }
        });

        jLabelBairro.setText("Bairro: *");

        jTextFieldBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBairroKeyTyped(evt);
            }
        });

        jLabel5.setText("Estado:");

        jLabelCidade.setText("Cidade: *");

        jLabelCep.setText("Cep: *");

        try {
            jFormattedTextFieldCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldCep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldCepKeyTyped(evt);
            }
        });

        jLabelTelefoneRes.setText("Telefone residencial: *");

        try {
            jFormattedTextFieldTelefonePessoaRes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldTelefonePessoaRes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldTelefonePessoaResKeyTyped(evt);
            }
        });

        jLabelCelularPessoa.setText("Celular: *");

        try {
            jFormattedTextFieldCelularPessoa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldCelularPessoa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldCelularPessoaKeyTyped(evt);
            }
        });

        jLabelFoneComlPessoa.setText("Telefone Comercial: *");

        try {
            jFormattedTextFieldTelefonePessoaComl.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldTelefonePessoaComl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldTelefonePessoaComlKeyTyped(evt);
            }
        });

        jComboBoxCidadeEndereco.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxCidadeEnderecoItemStateChanged(evt);
            }
        });
        jComboBoxCidadeEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCidadeEnderecoActionPerformed(evt);
            }
        });
        jComboBoxCidadeEndereco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxCidadeEnderecoKeyPressed(evt);
            }
        });

        jLcodCidade.setText("Cod Cidade:");

        javax.swing.GroupLayout jPanelDadosComplementaresLayout = new javax.swing.GroupLayout(jPanelDadosComplementares);
        jPanelDadosComplementares.setLayout(jPanelDadosComplementaresLayout);
        jPanelDadosComplementaresLayout.setHorizontalGroup(
            jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                                .addComponent(jLabelEnderecoPessoa)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextFieldEnderecoPessoa))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNumero)
                            .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelComplemento)
                            .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelCep))
                                .addGap(28, 28, 28)
                                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTelefoneRes)
                                    .addComponent(jFormattedTextFieldTelefonePessoaRes, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelFoneComlPessoa)
                                    .addComponent(jFormattedTextFieldTelefonePessoaComl, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextFieldCelularPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelCelularPessoa))
                                .addGap(167, 167, 167))
                            .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelBairro))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLcodCidade)
                                    .addComponent(jCBcodCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCidade)
                                    .addComponent(jComboBoxCidadeEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)))
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jComboBoxEstadoEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanelDadosComplementaresLayout.setVerticalGroup(
            jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEnderecoPessoa)
                    .addComponent(jLabelNumero)
                    .addComponent(jLabelComplemento))
                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEnderecoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCidade)
                    .addComponent(jLabel5)
                    .addComponent(jLabelBairro)
                    .addComponent(jLcodCidade))
                .addGap(7, 7, 7)
                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxEstadoEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCidadeEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBcodCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCelularPessoa)
                            .addComponent(jLabelFoneComlPessoa)
                            .addComponent(jLabelTelefoneRes))
                        .addGap(6, 6, 6)
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldCelularPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldTelefonePessoaComl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelDadosComplementaresLayout.createSequentialGroup()
                        .addComponent(jLabelCep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDadosComplementaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldTelefonePessoaRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jTabbedPaneCadastroDadosPessoais.addTab("Dados Complementares", jPanelDadosComplementares);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelInformacoesGerais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPCadastroPessoa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabelCadastroPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPaneCadastroDadosPessoais, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelCadastroPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPCadastroPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPaneCadastroDadosPessoais)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelInformacoesGerais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        setSize(new java.awt.Dimension(915, 770));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextFcnpjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFcnpjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFcnpjActionPerformed

    private void jTextfEmailPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextfEmailPessoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextfEmailPessoaActionPerformed

    private void jTFidentidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFidentidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFidentidadeActionPerformed

    private void jTFnomePessoaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFnomePessoaKeyTyped

        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
          evt.setKeyChar(Character.toUpperCase(c));
        }
        
        if((Character.isDigit(c))    || c==KeyEvent.VK_BACK_SPACE    || c==KeyEvent.VK_DELETE           || c==KeyEvent.VK_COMMA 
            || c==KeyEvent.VK_AT     || c==KeyEvent.VK_CIRCUMFLEX    || c==KeyEvent.VK_BACK_SLASH       || c==KeyEvent.VK_UNDERSCORE 
            || c==KeyEvent.VK_AT     || c==KeyEvent.VK_CLOSE_BRACKET || c==KeyEvent.VK_COLON            || c==KeyEvent.VK_SLASH 
            || c==KeyEvent.VK_DOLLAR || c==KeyEvent.VK_EQUALS        || c==KeyEvent.VK_EXCLAMATION_MARK || c==KeyEvent.VK_LEFT_PARENTHESIS
            || c==KeyEvent.VK_MINUS  || c==KeyEvent.VK_NUMBER_SIGN   || c==KeyEvent.VK_OPEN_BRACKET     || c==KeyEvent.VK_RIGHT_PARENTHESIS
            || c==KeyEvent.VK_PERIOD || c==KeyEvent.VK_PLUS          || c==KeyEvent.VK_SEMICOLON){
            
            evt.consume();
        }        
    }//GEN-LAST:event_jTFnomePessoaKeyTyped

    public boolean verificaQuantDigitos() throws Exception{
    
        int qtdDigitos = 0;
        String estadoEnd     = jComboBoxEstadoEnd.getSelectedItem().toString();  
        String inscEstadual  = jTFInscricaoEstadual.getText();     
        qtdDigitos           = verificaQtdeDigitos.contaStringDeDigitos(inscEstadual);
        
        if(qtdDigitos == 9 && estadoEnd.equals("GO")){
            verificaIE.validaIE(inscEstadual, estadoEnd);
            return true;
        } else if(qtdDigitos == 9 && estadoEnd.equals("AM")){  
            verificaIE.validaIE(inscEstadual, estadoEnd);
            return true;
        } else if(qtdDigitos == 9 && estadoEnd.equals("AL")){
            verificaIE.validaIE(inscEstadual, estadoEnd);
            return true;
        } else if(qtdDigitos == 9 && estadoEnd.equals("BA")){
            verificaIE.validaIE(inscEstadual, estadoEnd);
            return true;
        } else if(qtdDigitos == 9 && estadoEnd.equals("CE")){
            verificaIE.validaIE(inscEstadual, estadoEnd);
            return true;
        } else if(qtdDigitos == 9 && estadoEnd.equals("ES")){
            verificaIE.validaIE(inscEstadual, estadoEnd);
            return true;
        } else if(qtdDigitos == 9 && estadoEnd.equals("MA")){
            verificaIE.validaIE(inscEstadual, estadoEnd);
            return true;
        } else if(qtdDigitos == 9 && estadoEnd.equals("MS")){
            verificaIE.validaIE(inscEstadual, estadoEnd);
            return true;
        } else if(qtdDigitos == 9 && estadoEnd.equals("PA")){
            verificaIE.validaIE(inscEstadual, estadoEnd);
            return true;
        } else if(qtdDigitos == 9 && estadoEnd.equals("PB")){
            verificaIE.validaIE(inscEstadual, estadoEnd);
            return true;
        } else if(qtdDigitos == 9 && estadoEnd.equals("PI")){
            verificaIE.validaIE(inscEstadual, estadoEnd);
            return true;
        } else if(qtdDigitos == 9 && estadoEnd.equals("RR")){
            verificaIE.validaIE(inscEstadual, estadoEnd);
            return true;
        } else if(qtdDigitos == 9 && estadoEnd.equals("SC")){
            verificaIE.validaIE(inscEstadual, estadoEnd);
            return true;
        } else if(qtdDigitos == 9 && estadoEnd.equals("SE")){
            verificaIE.validaIE(inscEstadual, estadoEnd);            
            return true;
        } else if(qtdDigitos == 9 && estadoEnd.equals("AP")){
            verificaIE.validaIE(inscEstadual, estadoEnd);            
            return true;
        } else if(qtdDigitos == 13 && estadoEnd.equals("AC")){
            verificaIE.validaIE(inscEstadual, estadoEnd);            
            return true;
        } else if(qtdDigitos == 13 && estadoEnd.equals("DF")){
            verificaIE.validaIE(inscEstadual, estadoEnd);            
            return true;
        } else if(qtdDigitos == 13 && estadoEnd.equals("MG")){
            verificaIE.validaIE(inscEstadual, estadoEnd);            
            return true;
        } else if(qtdDigitos == 11 && estadoEnd.equals("MT")){
            verificaIE.validaIE(inscEstadual, estadoEnd);            
            return true;
        } else if(qtdDigitos == 10 && estadoEnd.equals("RS")){
            verificaIE.validaIE(inscEstadual, estadoEnd);            
            return true;
        } else if(qtdDigitos == 10 && estadoEnd.equals("PR")){
            verificaIE.validaIE(inscEstadual, estadoEnd);            
            return true;
        } else if(qtdDigitos == 14 && estadoEnd.equals("PE")){
            verificaIE.validaIE(inscEstadual, estadoEnd);            
            return true;
        } else if(qtdDigitos == 14 && estadoEnd.equals("RO")){
            verificaIE.validaIE(inscEstadual, estadoEnd);            
            return true;
        } else if(qtdDigitos == 8 && estadoEnd.equals("RJ")){
            verificaIE.validaIE(inscEstadual, estadoEnd);            
            return true;
        }  else if(qtdDigitos == 10 && estadoEnd.equals("RN")){
            verificaIE.validaIE(inscEstadual, estadoEnd);            
            return true;
        }  else if(qtdDigitos == 12 && estadoEnd.equals("SP")){
            verificaIE.validaIE(inscEstadual, estadoEnd);            
            return true;
        } else if(qtdDigitos == 13 && estadoEnd.equals("SP")){
            verificaIE.validaIE(inscEstadual, estadoEnd);            
            return true;
        } else if(qtdDigitos == 9 && estadoEnd.equals("RN")){
            verificaIE.validaIE(inscEstadual, estadoEnd);            
            return true;
        } else if(qtdDigitos == 10 && estadoEnd.equals("RN")){
            verificaIE.validaIE(inscEstadual, estadoEnd);            
            return true;
        } else if(qtdDigitos == 9 && estadoEnd.equals("TO")){
            verificaIE.validaIE(inscEstadual, estadoEnd);
            return true;
        } else if(qtdDigitos == 11 && estadoEnd.equals("TO")){
            verificaIE.validaIE(inscEstadual, estadoEnd);
            return true;
        } else{
                JOptionPane.showMessageDialog(this, "Número de caracteres inválidos para a IE do estado " +
                estadoEnd + ",  redigite!", "Aviso", JOptionPane.WARNING_MESSAGE);            
        }    
        return false;
    }
    
 
    private void jBinserirPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBinserirPessoaActionPerformed
        
        Integer codCidade  = Integer.valueOf(jCBcodCidade.getSelectedItem().toString());
        String estadoEnd   = jComboBoxEstadoEnd.getSelectedItem().toString();        
        String cidade      = jComboBoxCidadeEndereco.getSelectedItem().toString();
        
        String inscEstadual  = jTFInscricaoEstadual.getText();        
        String inscMunicipal = jFormattedTFinscricaoMunicipal.getText();
        String nomeFantasia  = jTFnomeFantasia.getText().toUpperCase();
        
        //formata data
        String dataFormatada = "";
        String dtNasc = jFormattedTextFdataNascPessoa.getText();
        dtNasc = dtNasc.trim();
        
        //captura botão
        String sexo = buttonGroupSexo.getSelection().getActionCommand();
        sexo = sexo.equals("feminino") ? "F" : "M";
        
        String identidade = jTFidentidade.getText();
        String estadoId   = jCBestadoIden.getSelectedItem().toString();
        String orgExped   = jComboBoxOrgExpeditor.getSelectedItem().toString();
        String email      = jTextfEmailPessoa.getText().toUpperCase();

        //prepara cpf
        String cpf = jFormattedTextCpf.getText();
        cpf        = removeCaracEspeciais.removeCaracEspeciais(cpf);

        //prepara cnpj
        String cnpj = jFormattedTextFcnpj.getText();
        cnpj = cnpj.trim();

        String nomePessoa  = jTFnomePessoa.getText().toUpperCase();        
        String nomeMae     = jTextFieldNomeMae.getText().toUpperCase();
        String nomePai     = jTextFieldNomePai.getText().toUpperCase();
        String endereco    = jTextFieldEnderecoPessoa.getText().toUpperCase();
        String numero      = jTextFieldNumero.getText();
        String complemento = jTextFieldComplemento.getText().toUpperCase();
        String bairro      = jTextFieldBairro.getText().toUpperCase();
        String cep         = jFormattedTextFieldCep.getText();
        String foneRes     = jFormattedTextFieldTelefonePessoaRes.getText();
        String foneComl    = jFormattedTextFieldTelefonePessoaComl.getText();
        String celular     = jFormattedTextFieldCelularPessoa.getText();
        
        cep      = removeCaracEspeciais.removeCaracEspeciais(cep);
        foneRes  = removeCaracEspeciais.removeCaracEspeciais(foneRes);
        foneComl = removeCaracEspeciais.removeCaracEspeciais(foneComl);
        celular  = removeCaracEspeciais.removeCaracEspeciais(celular);
//        foneRes = Pattern.compile("\\(+").matcher(foneRes).replaceAll("");

        boolean retornoEmail = false;
        String dtCadastro = "";
        
        //se true cadastra no banco após todas as validações
        boolean dadosConfirmados = false; 
        
        if(habilitaInsercao == 1){//verifica campos para cadastro pessoa física
            if( nomePessoa.isEmpty()           || sexo.isEmpty()             || identidade.isEmpty()
                || estadoId.equals("ESCOLHA")  || orgExped.equals("ESCOLHA") || email.isEmpty()
                || cpf.length()< 11            || dtNasc.length()< 10        || celular.isEmpty()
                || nomeMae.isEmpty()           || nomePai.isEmpty()          || endereco.isEmpty()
                || numero.isEmpty()            || complemento.isEmpty()      || bairro.isEmpty()
                || estadoEnd.equals("ESCOLHA") || cidade.isEmpty()           || foneComl.isEmpty()
                || foneRes.isEmpty()           || cep.isEmpty()){

                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!",
                "Aviso", JOptionPane.WARNING_MESSAGE);

            }else if(!retornoEmail){
                dataFormatada = formData.transformaData(dtNasc);
                if(!dataFormatada.equals("invalida")){
                    retornoEmail  = formEmail.validaEmail(email);
                    if(retornoEmail){
                        //formata data atual do sistema
                        try {
                            dtCadastro = getDataAtual.getDataAtual();
                        } catch (ParseException ex) {
                            Logger.getLogger(JFCadastroPessoa.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //verifica cpf
                        boolean ehCpf = formCpf.isCPF(cpf);
                        if(ehCpf){
                            dadosConfirmados = true;
                        }else{
                            JOptionPane.showMessageDialog(this, "Cpf inválido. Redigite!",
                            "Aviso", JOptionPane.WARNING_MESSAGE);                    
                        }
                    }else{  
                        JOptionPane.showMessageDialog(this, "Email inválido, redigite!",
                        "Aviso", JOptionPane.WARNING_MESSAGE);   
                        dadosConfirmados = false;  
                    }
                }
            }
            if(dadosConfirmados){
                pessoa.setDt_cadastro(dtCadastro);
                pessoa.setCidadeCodCidade(codCidade);
                pessoa.setNome(nomePessoa);
                pessoa.setTipo_logradouro(complemento);
                pessoa.setLogradouro(endereco);
                pessoa.setNumLogradouro(numero);
                pessoa.setBairro(bairro);
                pessoa.setCep(cep);
                pessoa.setUf(estadoId);
                pessoa.setTelefone_res(foneRes);
                pessoa.setTelefone_com(foneComl);
                pessoa.setCelular(celular);
                pessoa.setTipo_pessoa(sexo);

                pFisica.setCpf(cpf);    
                pFisica.setRg(identidade);
                pFisica.setDt_nascimento(dataFormatada);
                pFisica.setSexo(sexo);
                
                try {
                    boolean ret = pessoas.createPessoaFisica(pessoa, pFisica);
                    if(ret){
                        this.limparCamposFormPessoa();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(JFCadastroPessoa.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        }else if(habilitaInsercao == 2){ //verifica campos para cadastro pessoa jurídica
            if( email.isEmpty()                || cnpj.length()< 18     || endereco.isEmpty()
                || numero.isEmpty()            || complemento.isEmpty() || bairro.isEmpty()
                || estadoEnd.equals("ESCOLHA") || cidade.isEmpty()      || cep.isEmpty()
                || foneRes.isEmpty()           || celular.isEmpty()     || inscEstadual.isEmpty()
                || inscMunicipal.isEmpty()     || nomeFantasia.isEmpty()|| foneComl.isEmpty()){

                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!",
                "Aviso", JOptionPane.WARNING_MESSAGE);
            }else{
                retornoEmail  = formEmail.validaEmail(email);
                if(retornoEmail){
                    //formata data atual do sistema
                    try{
                        dtCadastro = getDataAtual.getDataAtual();
                    }catch (ParseException ex) {
                        Logger.getLogger(JFCadastroPessoa.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                    //verifica Cnpj
                    boolean ehCnpj = formCnpj.isCNPJ(cnpj);
                    if(ehCnpj){
                       dadosConfirmados = true;
                    }else{
                        JOptionPane.showMessageDialog(this, "Cnpj inválido. Redigite!",
                        "Aviso", JOptionPane.WARNING_MESSAGE);  
                        dadosConfirmados = false;                          
                    }
                    //verifica Ie
                    try {
                        boolean retVerQtdeDigitos = this.verificaQuantDigitos();
                        if(retVerQtdeDigitos){
                            dadosConfirmados = true;
                        }else{
                            dadosConfirmados = false;                        
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Inscrição Estadual inválida, redigite!",
                        "Aviso", JOptionPane.WARNING_MESSAGE);  
                        dadosConfirmados = false;                        
                    }
                }else{  
                    JOptionPane.showMessageDialog(this, "Email inválido, redigite!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);   
                    dadosConfirmados = false;  
                }
                if(dadosConfirmados){
                    pessoa.setCidadeCodCidade(codCidade);
                    pessoa.setTipo_logradouro(complemento);
                    pessoa.setLogradouro(endereco);
                    pessoa.setNumLogradouro(numero);
                    pessoa.setBairro(bairro);
                    pessoa.setCep(cep);
                    pessoa.setUf(estadoEnd);
                    pessoa.setTelefone_com(foneComl);
                    pessoa.setCelular(celular);
                    pessoa.setDt_cadastro(dtCadastro);

                    pJur.setCnpj(cnpj);
                    pJur.setIe(inscEstadual);
                    pJur.setIm(inscMunicipal);
                    pJur.setNome_fantasia(nomeFantasia);
                    
                    try {
                        boolean ret = pessoas.createPessoaJuridica(pessoa, pJur);
                        if(ret){
                            this.limparCamposFormPessoa();
                        }    
                    }catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Erro Exception: JFCadastroPessoa.java - linha: 1286",
                        "Aviso", JOptionPane.WARNING_MESSAGE);  
                    }                    
                } 
            }    
        }
    }//GEN-LAST:event_jBinserirPessoaActionPerformed

    private void jTextfEmailPessoaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextfEmailPessoaKeyTyped
        
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
          evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_jTextfEmailPessoaKeyTyped

    private void jBlimparPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimparPessoaActionPerformed

        jTFnomePessoa.setText("");
        jFormattedTextFdataNascPessoa.setText("");
        jTFidentidade.setText("");  
        jCBestadoIden.setSelectedItem("ESCOLHA");
        jComboBoxOrgExpeditor.setSelectedItem("ESCOLHA");
        jTextfEmailPessoa.setText("");
        jFormattedTextCpf.setText("");
        jFormattedTextFcnpj.setText("");
        jTextFieldNomeMae.setText("");
        jTextFieldNomePai.setText("");  
        jTextFieldEnderecoPessoa.setText("");
        jTextFieldNumero.setText("");
        jTextFieldComplemento.setText("");
        jTextFieldBairro.setText("");
        jComboBoxCidadeEndereco.setSelectedItem("ESCOLHA");
        jComboBoxEstadoEnd.setSelectedItem("ESCOLHA");
        jFormattedTextFieldCep.setText("");        
        jFormattedTextFieldTelefonePessoaRes.setText("");
        jFormattedTextFieldCelularPessoa.setText("");
        jFormattedTextFieldTelefonePessoaComl.setText("");
        jTFnomeFantasia.setText("");
        jTFInscricaoEstadual.setText("");
        jFormattedTFinscricaoMunicipal.setText("");        
    }//GEN-LAST:event_jBlimparPessoaActionPerformed

    private void jBcancelarPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelarPessoaActionPerformed
        dispose();
    }//GEN-LAST:event_jBcancelarPessoaActionPerformed

    private void jTFidentidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFidentidadeKeyTyped
        
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
          evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_jTFidentidadeKeyTyped

    private void jTextFieldNomeMaeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeMaeKeyTyped
        
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
          evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_jTextFieldNomeMaeKeyTyped

    private void jTextFieldNomePaiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomePaiKeyTyped
        
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
          evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_jTextFieldNomePaiKeyTyped

    private void jTextFieldEnderecoPessoaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEnderecoPessoaKeyTyped
        
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
          evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_jTextFieldEnderecoPessoaKeyTyped

    private void jTextFieldBairroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBairroKeyTyped
        
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
          evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_jTextFieldBairroKeyTyped

    private void jTextFieldComplementoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldComplementoKeyTyped
        
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
          evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_jTextFieldComplementoKeyTyped

    private void jTextFieldNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNumeroKeyTyped

        char c = evt.getKeyChar();
        if(!Character.isDigit(c)) evt.consume(); 
    }//GEN-LAST:event_jTextFieldNumeroKeyTyped

    private void jFormattedTextFieldTelefonePessoaResKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTelefonePessoaResKeyTyped
        
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
          evt.setKeyChar(Character.toUpperCase(c));
        }
        if(!Character.isDigit(c)) evt.consume();         
    }//GEN-LAST:event_jFormattedTextFieldTelefonePessoaResKeyTyped

    private void jFormattedTextFieldCelularPessoaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCelularPessoaKeyTyped
        
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
          evt.setKeyChar(Character.toUpperCase(c));
        }
        if(!Character.isDigit(c)) evt.consume(); 
    }//GEN-LAST:event_jFormattedTextFieldCelularPessoaKeyTyped

    private void jFormattedTextFieldCepKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCepKeyTyped

        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
          evt.setKeyChar(Character.toUpperCase(c));
        }
        if(!Character.isDigit(c)) evt.consume();         
    }//GEN-LAST:event_jFormattedTextFieldCepKeyTyped

    private void jFormattedTextFdataNascPessoaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFdataNascPessoaKeyTyped
        
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
          evt.setKeyChar(Character.toUpperCase(c));
        }
        if(!Character.isDigit(c)) evt.consume(); 
    }//GEN-LAST:event_jFormattedTextFdataNascPessoaKeyTyped

    private void jFormattedTextFcnpjKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFcnpjKeyTyped
        
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
          evt.setKeyChar(Character.toUpperCase(c));
        }
        if(!Character.isDigit(c)) evt.consume(); 
    }//GEN-LAST:event_jFormattedTextFcnpjKeyTyped

    private void jFormattedTextCpfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextCpfKeyTyped
        
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
          evt.setKeyChar(Character.toUpperCase(c));
        }
        if(!Character.isDigit(c)) evt.consume(); 
    }//GEN-LAST:event_jFormattedTextCpfKeyTyped

    private void jBalterarPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBalterarPessoaActionPerformed
        
        //pode ser usada para marcar alteração ou deleção
        String atual = "";
        try {
            atual = getDataAtual.getDataAtual();
        } catch (ParseException ex) {
            Logger.getLogger(JFCadastroPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBalterarPessoaActionPerformed

    private void jFormattedTextFieldTelefonePessoaComlKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTelefonePessoaComlKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldTelefonePessoaComlKeyTyped

    private void jComboBoxCidadeEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCidadeEnderecoActionPerformed
//
//        for(Cidade c: cidadeDao.listaCidades()){
//          jComboBoxCidadeEndereco.addItem(c.getDesc_cidade());
//          jComboBoxEstadoEnd.addItem(c.getEstado_cidade());
//        }
//          System.out.println("aa");
//        
//        jComboBoxCidadeEndereco.addActionListener(new ActionListener() {    
//            public void actionPerformed(ActionEvent e) {  
//                CidadeDao cidadeDao = new CidadeDao();
//                for(Cidade c: cidadeDao.listaCidades()){
//                  jComboBoxCidadeEndereco.addItem(c.getDesc_cidade());
//                  jComboBoxEstadoEnd.addItem(c.getEstado_cidade());
//                } 
//            System.out.println("teste");
//            }
//        });
//        
//        private void cbUfActionPerformed(java.awt.event.ActionEvent evt) {                                       
//          cbUf.addActionListener(new ActionListener() {    
//                public void actionPerformed(ActionEvent e) {  
//                        consultaTodosCidadesUfBancoBox();  
//                   }  
//          } );
//        }      

    }//GEN-LAST:event_jComboBoxCidadeEnderecoActionPerformed

    private void jComboBoxCidadeEnderecoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxCidadeEnderecoItemStateChanged
        
        for(int pos = jComboBoxCidadeEndereco.getSelectedIndex(); pos < arrCidEst.size(); ++pos){
            String endEst = arrCidEst.get(pos);
            String a[] = endEst.split("-");
            jCBcodCidade.removeAllItems();
            jComboBoxEstadoEnd.removeAllItems();
            jCBcodCidade.addItem(a[0]);
            jComboBoxEstadoEnd.addItem(a[2]);
            break;
        }
    }//GEN-LAST:event_jComboBoxCidadeEnderecoItemStateChanged

    private void jComboBoxCidadeEnderecoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxCidadeEnderecoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCidadeEnderecoKeyPressed

    private void jTFnomeFantasiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFnomeFantasiaKeyTyped
        
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
          evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_jTFnomeFantasiaKeyTyped

    private void jTcadastroPessoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTcadastroPessoaMouseClicked

        //captura botão
//        String sexo = buttonGroupSexo.getSelection().getActionCommand();
//        sexo = sexo.equals("feminino") ? "F" : "M";
        if(jTcadastroPessoa.getSelectedRow() != -1)
            //código de pessoa
            jTFnomePessoa.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),2).toString());
            buttonGroupSexo.getSelection().setActionCommand(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),3).toString());
            //tipo pessoa juridica ou Fisica
//            jFormattedTextCpf.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),5).toString()); //(F)
//            jTFidentidade.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),6).toString()); //(F)
//            jFormattedTextFdataNascPessoa.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),7).toString()); //(F)
            jFormattedTextFieldTelefonePessoaRes.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),8).toString());
            jFormattedTextFieldTelefonePessoaComl.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),9).toString());
            jFormattedTextFieldCelularPessoa.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),10).toString());
//            jFormattedTextFcnpj.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),11).toString()); //(J)
//            jTFInscricaoEstadual.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),12).toString()); //(j)
//            jFormattedTFinscricaoMunicipal.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),13).toString()); //(j)
//            jTFnomeFantasia.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),14).toString()); //(J)
            jCBcodCidade.setSelectedItem(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),15).toString());
            jComboBoxCidadeEndereco.setSelectedItem(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),16).toString());
            jTextFieldEnderecoPessoa.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),17).toString());
            jTextFieldComplemento.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),18).toString());
            jTextFieldNumero.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),19).toString());
            jTextFieldBairro.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),20).toString());
            jFormattedTextFieldCep.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),21).toString());
            jComboBoxEstadoEnd.setSelectedItem(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),22).toString());
            
//            jTextfEmailPessoa.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),7).toString()); 
//            jTextFieldNomeMae.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),8).toString());
//            jTextFieldNomePai.setText(jTcadastroPessoa.getValueAt(jTcadastroPessoa.getSelectedRow(),9).toString());
            

//            p.getCod_pessoa(),
//            p.getCidadeCodCidade(),
//            p.getNome(),
//            p.getTipo_pessoa(),
//            p.getTelefone_res(),
//            p.getTelefone_com(),
//            p.getCelular(),
//            p.getLogradouro(),
//            p.getTipo_logradouro(),
//            p.getNumLogradouro(),
//            p.getBairro(),
//            p.getCep(),
//            p.getUf(),
//            p.getDt_cadastro()

            
    }//GEN-LAST:event_jTcadastroPessoaMouseClicked

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
            java.util.logging.Logger.getLogger(JFCadastroPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFCadastroPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFCadastroPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFCadastroPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFCadastroPessoa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupSexo;
    private javax.swing.JButton jBalterarPessoa;
    private javax.swing.JButton jBcancelarPessoa;
    private javax.swing.JButton jBinserirPessoa;
    private javax.swing.JButton jBlimparPessoa;
    private javax.swing.JButton jBremoverPessoa;
    private javax.swing.JComboBox<String> jCBcodCidade;
    private javax.swing.JComboBox<String> jCBestadoIden;
    private javax.swing.JComboBox<String> jComboBoxCidadeEndereco;
    private javax.swing.JComboBox<String> jComboBoxEstadoEnd;
    private javax.swing.JComboBox<String> jComboBoxOrgExpeditor;
    private javax.swing.JFormattedTextField jFormattedTFinscricaoMunicipal;
    private javax.swing.JFormattedTextField jFormattedTextCpf;
    private javax.swing.JFormattedTextField jFormattedTextFcnpj;
    private javax.swing.JFormattedTextField jFormattedTextFdataNascPessoa;
    private javax.swing.JFormattedTextField jFormattedTextFieldCelularPessoa;
    private javax.swing.JFormattedTextField jFormattedTextFieldCep;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefonePessoaComl;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefonePessoaRes;
    private javax.swing.JLabel jLBemailPessoa;
    private javax.swing.JLabel jLInscricaoEstadual;
    private javax.swing.JLabel jLInscricaoMunicipal;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelBairro;
    private javax.swing.JLabel jLabelCadastroPessoa;
    private javax.swing.JLabel jLabelCelularPessoa;
    private javax.swing.JLabel jLabelCep;
    private javax.swing.JLabel jLabelCidade;
    private javax.swing.JLabel jLabelComplemento;
    private javax.swing.JLabel jLabelEnderecoPessoa;
    private javax.swing.JLabel jLabelFoneComlPessoa;
    private javax.swing.JLabel jLabelNomeMae;
    private javax.swing.JLabel jLabelNomePai;
    private javax.swing.JLabel jLabelNomePessoa;
    private javax.swing.JLabel jLabelNumero;
    private javax.swing.JLabel jLabelSexo;
    private javax.swing.JLabel jLabelTelefoneRes;
    private javax.swing.JLabel jLcodCidade;
    private javax.swing.JLabel jLestadoIdentidade;
    private javax.swing.JLabel jLidentidade;
    private javax.swing.JLabel jLorgaoExpeditor;
    private javax.swing.JPanel jPCadastroPessoa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelDadosComplementares;
    private javax.swing.JPanel jPanelDadosPessoais;
    private javax.swing.JPanel jPanelInformacoesGerais;
    private javax.swing.JRadioButton jRadioButtonFeminino;
    private javax.swing.JRadioButton jRadioButtonMasculino;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFInscricaoEstadual;
    private javax.swing.JTextField jTFidentidade;
    private javax.swing.JTextField jTFnomeFantasia;
    private javax.swing.JTextField jTFnomePessoa;
    private javax.swing.JTabbedPane jTabbedPaneCadastroDadosPessoais;
    private javax.swing.JTable jTcadastroPessoa;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldComplemento;
    private javax.swing.JTextField jTextFieldEnderecoPessoa;
    private javax.swing.JTextField jTextFieldNomeMae;
    private javax.swing.JTextField jTextFieldNomePai;
    private javax.swing.JTextField jTextFieldNumero;
    private javax.swing.JTextField jTextfEmailPessoa;
    // End of variables declaration//GEN-END:variables
}
