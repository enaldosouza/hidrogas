package util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;

/**
 * @author enaldo.souza <enaldo@unitri.edu.br>
 */
public class Util {

    public Util() {

    }

    public String transformaData(String minhaData) {

        // Recebe formato: 29/10/2016 - Devolve: 20161029
        String dataFinal = "";
        try{
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(minhaData, formato);
            String[] dataSplit = (formato.format(data)).split("/");
            String dia = "";
            String mes = "";
            String ano = "";
            for (int i = 0; i <= 3; i++) {
                dia = dataSplit[0];
                mes = dataSplit[1];
                ano = dataSplit[2];
            }
            dataFinal = ano + mes + dia;
            return dataFinal;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Data inválida. Redigite!",
            "Aviso", JOptionPane.WARNING_MESSAGE); 
            return "invalida";
        }
    }

    public boolean validaEmail(String email) {

        Matcher matcher = Pattern.compile("[\\w]+@[\\w]+\\.[\\w]+[\\.]*[\\w]*", Pattern.CASE_INSENSITIVE).matcher(email);
        while (matcher.find()) {
            return true;
            //System.out.println(matcher.start() + " - " + matcher.group());
        }
        return false;
    }
    
    public Integer contaStringDeDigitos(String numero){
        
        String nroSemMascara = this.removeMascara(numero);
        int numFinal = 0;
        for (int i = 0; i < nroSemMascara.length(); i++) {
            numFinal += 1;
        }
        return numFinal;
    }

    public static boolean isCPF(String CPF) {
        //retira . e - da string cpf
        CPF = Pattern.compile("\\.+").matcher(CPF).replaceAll("");
        CPF = Pattern.compile("\\-+").matcher(CPF).replaceAll("");

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0         
                // (48 eh a posicao de '0' na tabela ASCII)         
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }// converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }

        } catch (InputMismatchException erro) {
            return (false);
        }
        //    como retirar caracteres especiais  
        //    String line = new String("banana*batata.pepino#alface_tomate@cenoura cebola/abacate|morango\\laranja");
        //    for (String retval: line.split(" |#|@|_|\\\\|\\/|\\.|\\*") ){
        //    System.out.println(retval);
    }

    public static String imprimeCPF(String CPF) {

        return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "."
                + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

    public static boolean isCNPJ(String CNPJ) {

        //retira . e - da string cnpj
        CNPJ = Pattern.compile("\\.+").matcher(CNPJ).replaceAll("");
        CNPJ = Pattern.compile("\\-+").matcher(CNPJ).replaceAll("");
        CNPJ = Pattern.compile("\\/+").matcher(CNPJ).replaceAll("");

        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
                || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
                || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
                || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
                || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
                || (CNPJ.length() != 14)) {
            return (false);
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        // "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                // converte o i-ésimo caractere do CNPJ em um número:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posição de '0' na tabela ASCII)
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static String imprimeCNPJ(String CNPJ) {
        // máscara do CNPJ: 99.999.999.9999-99
        return (CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "."
                + CNPJ.substring(5, 8) + "." + CNPJ.substring(8, 12) + "-"
                + CNPJ.substring(12, 14));
    }

    public String getDataAtual() throws ParseException {
        //retorno anomêsdia
        DateFormat df = new SimpleDateFormat("YYYYMMdd");
        Calendar c = Calendar.getInstance();
        return df.format(c.getTime()); 
    }

    
    //Créditos: ThiagoWorldCoder em http://www.guj.com.br/t/resolvido-algoritmo-inscricao-estadual/139155/4
    private static String removeMascara(String ie) {
        String strIE = "";
        for (int i = 0; i < ie.length(); i++) {
            if (Character.isDigit(ie.charAt(i))) {
                strIE += ie.charAt(i);
            }
        }
        return strIE;
    }
    
    public String removeCaracEspeciais(String carac) {
        String caracteres = "";
        for (int i = 0; i < carac.length(); i++) {
            if (Character.isDigit(carac.charAt(i))) {
                caracteres += carac.charAt(i);
            }
        }
        return caracteres;
    }

    public static void validaIE(String inscricaoEstadual, String siglaUf) throws Exception {
        String strIE = removeMascara(inscricaoEstadual);
        siglaUf = siglaUf.toUpperCase();
        
        switch (siglaUf) {
            case "AC":
                validaIEAcre(strIE);
                break;
            case "AL":
                validaIEAlagoas(strIE);
                break;
            case "AP":
                validaIEAmapa(strIE);
                break;
            case "AM":
                validaIEAmazonas(strIE);
                break;
            case "BA":
                validaIEBahia(strIE);
                break;
            case "CE":
                validaIECeara(strIE);
                break;
            case "ES":
                validaIEEspiritoSanto(strIE);
                break;
            case "GO":
                validaIEGoias(strIE);
                break;
            case "MA":
                validaIEMaranhao(strIE);
                break;
            case "MT":
                validaIEMatoGrosso(strIE);
                break;
            case "MS":
                validaIEMatoGrossoSul(strIE);
                break;
            case "MG":
                validaIEMinasGerais(strIE);
                break;
            case "PA":
                validaIEPara(strIE);
                break;
            case "PB":
                validaIEParaiba(strIE);
                break;
            case "PR":
                validaIEParana(strIE);
                break;
            case "PE":
                validaIEPernambuco(strIE);
                break;
            case "PI":
                validaIEPiaui(strIE);
                break;
            case "RJ":
                validaIERioJaneiro(strIE);
                break;
            case "RN":
                validaIERioGrandeNorte(strIE);
                break;
            case "RS":
                validaIERioGrandeSul(strIE);
                break;
            case "RO":
                validaIERondonia(strIE);
                break;
            case "RR":
                validaIERoraima(strIE);
                break;
            case "SC":
                validaIESantaCatarina(strIE);
                break;
            case "SP":
                if (inscricaoEstadual.charAt(0) == 'P') {
                    strIE = "P" + strIE;
                }   validaIESaoPaulo(strIE);
                break;
            case "SE":
                validaIESergipe(strIE);
                break;
            case "TO":
                validaIETocantins(strIE);
                break;
            case "DF":
                validaIEDistritoFederal(strIE);
                break;
            default:
                throw new Exception("Estado não encontrado : " + siglaUf);
        }
    }

    /**
     * Valida inscrição estadual do estado do Acre
     *
     * @param ie (Inscricao estadual)
     * @throws Exception
     */
    private static void validaIEAcre(String ie) throws Exception { //inscricao do validaIEAcre()
        //valida a quantidade de digitos
        if (ie.length() != 13) {
            throw new Exception("Quantidade de digitos inválida.");
        }

        //valida os dois primeiros digitos - devem ser iguais a 01
        for (int i = 0; i < 2; i++) {
            if (Integer.parseInt(String.valueOf(ie.charAt(i))) != i) {
                throw new Exception("Inscrição Estadual inválida");
            }
        }

        int soma = 0;
        int pesoInicial = 4;
        int pesoFinal = 9;
        int d1 = 0; //primeiro digito verificador
        int d2 = 0; //segundo digito verificador

        //calcula o primeiro digito
        for (int i = 0; i < ie.length() - 2; i++) {
            if (i < 3) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicial;
                pesoInicial--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFinal;
                pesoFinal--;
            }
        }
        d1 = 11 - (soma % 11);
        if (d1 == 10 || d1 == 11) {
            d1 = 0;
        }

        //calcula o segundo digito
        soma = d1 * 2;
        pesoInicial = 5;
        pesoFinal = 9;
        for (int i = 0; i < ie.length() - 2; i++) {
            if (i < 4) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicial;
                pesoInicial--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFinal;
                pesoFinal--;
            }
        }

        d2 = 11 - (soma % 11);
        if (d2 == 10 || d2 == 11) {
            d2 = 0;
        }

        //valida os digitos verificadores
        String dv = d1 + "" + d2;
        if (!dv.equals(ie.substring(ie.length() - 2, ie.length()))) {
            throw new Exception("Digito verificador inválido.");
        }
    } //fim do validaIEAcre()

    /**
     * Valida inscricao estadual do estado do Alagoas
     *
     * @param ie (Inscricao estadual)
     * @throws Exception
     */
    private static void validaIEAlagoas(String ie) throws Exception {
        //valida quantidade de digitos
        if (ie.length() != 9) {
            throw new Exception("Quantidade de dígitos inválidos!");
        }

        //valida os dois primeiros d&#digitos;gitos - deve ser iguais a 24
        if (!ie.substring(0, 2).equals("24")) {
            throw new Exception("Inscrição estadual inválida.");
        }

        //valida o terceiro digito - deve ser 0,3,5,7,8
        int[] digits = {0, 3, 5, 7, 8};
        boolean check = false;
        for (int i = 0; i < digits.length; i++) {
            if (Integer.parseInt(String.valueOf(ie.charAt(2))) == digits[i]) {
                check = true;
                break;
            }
        }
        if (!check) {
            throw new Exception("Inscrição estadual inválida.");
        }

        //calcula o digito verificador
        int soma = 0;
        int peso = 9;
        int d = 0; //digito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }
        d = ((soma * 10) % 11);
        if (d == 10) {
            d = 0;
        }

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscricao estadual do estado do Amap&#65533;
     *
     * @param ie (Inscricao estadual)
     * @throws Exception
     */
    private static void validaIEAmapa(String ie) throws Exception {
        //valida quantida de digitos
        if (ie.length() != 9) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //verifica os dois primeiros digitos - deve ser igual 03
        if (!ie.substring(0, 2).equals("03")) {
            throw new Exception("Inscrição estadual inválida.");
        }

        //calcula o digito verificador
        int d1 = -1;
        int soma = -1;
        int peso = 9;

        //configura o valor do digito verificador e da soma de acordo com faixa das inscricoes
        long x = Long.parseLong(ie.substring(0, ie.length() - 1)); //x = inscricao estadual sem o digito verificador
        if (x >= 3017001L && x <= 3019022L) {
            d1 = 1;
            soma = 9;
        } else if (x >= 3000001L && x <= 3017000L) {
            d1 = 0;
            soma = 5;
        } else if (x >= 3019023L) {
            d1 = 0;
            soma = 0;
        }

        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        int d = 11 - ((soma % 11)); //d = armazena o digito verificador apos calculo
        if (d == 10) {
            d = 0;
        } else if (d == 11) {
            d = d1;
        }

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscricao;o estadual do estado do Amazonas
     *
     * @param ie (Inscricao estadual)
     * @throws Exception
     */
    private static void validaIEAmazonas(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 9) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        int soma = 0;
        int peso = 9;
        int d = -1; //d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        if (soma < 11) {
            d = 11 - soma;
        } else if ((soma % 11) <= 1) {
            d = 0;
        } else {
            d = 11 - (soma % 11);
        }

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Bahia
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIEBahia(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 8 && ie.length() != 9) {
            throw new Exception("Quantidade de digitos inválidas." + ie);
        }

        //C&#65533;lculo do m&#65533;dulo de acordo com o primeiro d&#65533;gito da inscri&#65533;&#65533;o Estadual
        int modulo = 10;
        int firstDigit = Integer.parseInt(String.valueOf(ie.charAt(ie.length() == 8 ? 0 : 1)));
        if (firstDigit == 6 || firstDigit == 7 || firstDigit == 9) {
            modulo = 11;
        }

        //C&#65533;lculo do segundo d&#65533;gito
        int d2 = -1; //segundo d&#65533;gito verificador
        int soma = 0;
        int peso = ie.length() == 8 ? 7 : 8;
        for (int i = 0; i < ie.length() - 2; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        int resto = soma % modulo;

        if (resto == 0 || (modulo == 11 && resto == 1)) {
            d2 = 0;
        } else {
            d2 = modulo - resto;
        }

        //C&#65533;lculo do primeiro d&#65533;gito
        int d1 = -1; //primeiro d&#65533;gito verificador
        soma = d2 * 2;
        peso = ie.length() == 8 ? 8 : 9;
        for (int i = 0; i < ie.length() - 2; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        resto = soma % modulo;

        if (resto == 0 || (modulo == 11 && resto == 1)) {
            d1 = 0;
        } else {
            d1 = modulo - resto;
        }

        //valida os digitos verificadores
        String dv = d1 + "" + d2;
        if (!dv.equals(ie.substring(ie.length() - 2, ie.length()))) {
            throw new Exception("Digito verificador inválido." + ie);
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Cear&#65533;
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIECeara(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 9) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //C&#65533;lculo do d&#65533;gito verificador
        int soma = 0;
        int peso = 9;
        int d = -1; //d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        d = 11 - (soma % 11);
        if (d == 10 || d == 11) {
            d = 0;
        }
        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Esp&#65533;rito
     * Santo
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIEEspiritoSanto(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 9) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //C&#65533;lculo do d&#65533;gito verificador
        int soma = 0;
        int peso = 9;
        int d = -1; //d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        int resto = soma % 11;
        if (resto < 2) {
            d = 0;
        } else if (resto > 1) {
            d = 11 - resto;
        }

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Goi&#65533;s
     *
     * @param ie (Inscrição estadual)
     * @throws Exception
     */
    private static void validaIEGoias(String ie) throws Exception {
        //valida quantida de dígitos
        if (ie.length() != 9) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //válida os dois primeiros dígitos
        if (!"10".equals(ie.substring(0, 2))) {
            if (!"11".equals(ie.substring(0, 2))) {
                if (!"15".equals(ie.substring(0, 2))) {
                    throw new Exception("Inscrição estadual inválida");
                }
            }
        }

        if (ie.substring(0, ie.length() - 1).equals("11094402")) {
            if (!ie.substring(ie.length() - 1, ie.length()).equals("0")) {
                if (!ie.substring(ie.length() - 1, ie.length()).equals("1")) {
                    throw new Exception("Inscrição estadual inválida.");
                }
            }
        } else {

            //C&#65533;lculo do d&#65533;gito verificador
            int soma = 0;
            int peso = 9;
            int d = -1; //d&#65533;gito verificador
            for (int i = 0; i < ie.length() - 1; i++) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
                peso--;
            }

            int resto = soma % 11;
            long faixaInicio = 10103105;
            long faixaFim = 10119997;
            long insc = Long.parseLong(ie.substring(0, ie.length() - 1));
            if (resto == 0) {
                d = 0;
            } else if (resto == 1) {
                if (insc >= faixaInicio && insc <= faixaFim) {
                    d = 1;
                } else {
                    d = 0;
                }
            } else if (resto != 0 && resto != 1) {
                d = 11 - resto;
            }

            //valida o digito verificador
            String dv = d + "";
            if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
                throw new Exception("Digito verificador inválido.");
            }
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Maranh&#65533;o
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIEMaranhao(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 9) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //valida os dois primeiros d&#65533;gitos
        if (!ie.substring(0, 2).equals("12")) {
            throw new Exception("Inscrição estadual inválida.");
        }

        //Calcula o d&#65533;gito verificador
        int soma = 0;
        int peso = 9;
        int d = -1; //d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        d = 11 - (soma % 11);
        if ((soma % 11) == 0 || (soma % 11) == 1) {
            d = 0;
        }

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Mato Grosso
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIEMatoGrosso(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 11) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //Calcula o d&#65533;gito verificador
        int soma = 0;
        int pesoInicial = 3;
        int pesoFinal = 9;
        int d = -1;

        for (int i = 0; i < ie.length() - 1; i++) {
            if (i < 2) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicial;
                pesoInicial--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFinal;
                pesoFinal--;
            }
        }

        d = 11 - (soma % 11);
        if ((soma % 11) == 0 || (soma % 11) == 1) {
            d = 0;
        }

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Mato Grosso do Sul
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIEMatoGrossoSul(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 9) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //valida os dois primeiros d&#65533;gitos
        if (!ie.substring(0, 2).equals("28")) {
            throw new Exception("Inscrição estadual inválida.");
        }

        //Calcula o d&#65533;gito verificador
        int soma = 0;
        int peso = 9;
        int d = -1; //d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        int resto = soma % 11;
        int result = 11 - resto;
        if (resto == 0) {
            d = 0;
        } else if (resto > 0) {
            if (result > 9) {
                d = 0;
            } else if (result < 10) {
                d = result;
            }
        }

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Minas Gerais
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIEMinasGerais(String ie) throws Exception {
        /*
	 * FORMATO GERAL: A1A2A3B1B2B3B4B5B6C1C2D1D2
	 * Onde: A= C&#65533;digo do Munic&#65533;pio
	 * B= N&#65533;mero da inscri&#65533;&#65533;o
	 * C= N&#65533;mero de ordem do estabelecimento
	 * D= D&#65533;gitos de controle
         */

        // valida quantida de d&#65533;gitos
        if (ie.length() != 13) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //iguala a casas para o c&#65533;lculo
        //em inserir o algarismo zero "0" imediatamente ap&#65533;s o n&#65533;mero de c&#65533;digo do munic&#65533;pio, 
        //desprezando-se os d&#65533;gitos de controle.
        String str = "";
        for (int i = 0; i < ie.length() - 2; i++) {
            if (Character.isDigit(ie.charAt(i))) {
                if (i == 3) {
                    str += "0";
                    str += ie.charAt(i);
                } else {
                    str += ie.charAt(i);
                }
            }
        }

        //C&#65533;lculo do primeiro d&#65533;gito verificador
        int soma = 0;
        int pesoInicio = 1;
        int pesoFim = 2;
        int d1 = -1; //primeiro d&#65533;gito verificador
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0) {
                int x = Integer.parseInt(String.valueOf(str.charAt(i))) * pesoInicio;
                String strX = Integer.toString(x);
                for (int j = 0; j < strX.length(); j++) {
                    soma += Integer.parseInt(String.valueOf(strX.charAt(j)));
                }
            } else {
                int y = Integer.parseInt(String.valueOf(str.charAt(i))) * pesoFim;
                String strY = Integer.toString(y);
                for (int j = 0; j < strY.length(); j++) {
                    soma += Integer.parseInt(String.valueOf(strY.charAt(j)));
                }
            }
        }

        int dezenaExata = soma;
        while (dezenaExata % 10 != 0) {
            dezenaExata++;
        }
        d1 = dezenaExata - soma; //resultado - primeiro d&#65533;gito verificador

        //C&#65533;lculo do segundo d&#65533;gito verificador
        soma = d1 * 2;
        pesoInicio = 3;
        pesoFim = 11;
        int d2 = -1;
        for (int i = 0; i < ie.length() - 2; i++) {
            if (i < 2) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicio;
                pesoInicio--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFim;
                pesoFim--;
            }
        }

        d2 = 11 - (soma % 11); //resultado - segundo d&#65533;gito verificador
        if ((soma % 11 == 0) || (soma % 11 == 1)) {
            d2 = 0;
        }

        //valida os digitos verificadores
        String dv = d1 + "" + d2;
        if (!dv.equals(ie.substring(ie.length() - 2, ie.length()))) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Par&#65533;
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIEPara(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 9) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //valida os dois primeiros d&#65533;gitos
        if (!ie.substring(0, 2).equals("15")) {
            throw new Exception("Inscrição estadual inválida.");
        }

        //Calcula o d&#65533;gito verificador
        int soma = 0;
        int peso = 9;
        int d = -1; //d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        d = 11 - (soma % 11);
        if ((soma % 11) == 0 || (soma % 11) == 1) {
            d = 0;
        }

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Para&#65533;ba
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIEParaiba(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 9) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //Calcula o d&#65533;gito verificador
        int soma = 0;
        int peso = 9;
        int d = -1; //d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        d = 11 - (soma % 11);
        if (d == 10 || d == 11) {
            d = 0;
        }

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Paran&#65533;
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIEParana(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 10) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //Cálculo do primeiro dígito
        int soma = 0;
        int pesoInicio = 3;
        int pesoFim = 7;
        int d1 = -1; //d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 2; i++) {
            if (i < 2) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicio;
                pesoInicio--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFim;
                pesoFim--;
            }
        }

        d1 = 11 - (soma % 11);
        if ((soma % 11) == 0 || (soma % 11) == 1) {
            d1 = 0;
        }

        //c&#65533;lculo do segundo d&#65533;gito
        soma = d1 * 2;
        pesoInicio = 4;
        pesoFim = 7;
        int d2 = -1; //segundo d&#65533;gito
        for (int i = 0; i < ie.length() - 2; i++) {
            if (i < 3) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicio;
                pesoInicio--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFim;
                pesoFim--;
            }
        }

        d2 = 11 - (soma % 11);
        if ((soma % 11) == 0 || (soma % 11) == 1) {
            d2 = 0;
        }

        //valida os digitos verificadores
        String dv = d1 + "" + d2;
        if (!dv.equals(ie.substring(ie.length() - 2, ie.length()))) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Pernambuco
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIEPernambuco(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 14) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //C&#65533;lculo do d&#65533;gito verificador
        int soma = 0;
        int pesoInicio = 5;
        int pesoFim = 9;
        int d = -1; //d&#65533;gito verificador

        for (int i = 0; i < ie.length() - 1; i++) {
            if (i < 5) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicio;
                pesoInicio--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFim;
                pesoFim--;
            }
        }

        d = 11 - (soma % 11);
        if (d > 9) {
            d -= 10;
        }

        System.out.println(soma);
        System.out.println(11 - (soma % 11));
        System.out.println(d);

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Piau&#65533;
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIEPiaui(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 9) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //C&#65533;lculo do d&#65533;gito verficador
        int soma = 0;
        int peso = 9;
        int d = -1; //d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        d = 11 - (soma % 11);
        if (d == 11 || d == 10) {
            d = 0;
        }

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Rio de Janeiro
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIERioJaneiro(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 8) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //C&#65533;lculo do d&#65533;gito verficador
        int soma = 0;
        int peso = 7;
        int d = -1; //d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            if (i == 0) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * 2;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
                peso--;
            }
        }

        d = 11 - (soma % 11);
        if ((soma % 11) <= 1) {
            d = 0;
        }

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Rio Grande do Norte
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIERioGrandeNorte(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 10 && ie.length() != 9) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //valida os dois primeiros d&#65533;gitos
        if (!ie.substring(0, 2).equals("20")) {
            throw new Exception("Inscrição estadual inválida.");
        }

        //calcula o d&#65533;gito para inscri&#65533;&#65533;o de 9 d&#65533;gitos
        if (ie.length() == 9) {
            int soma = 0;
            int peso = 9;
            int d = -1; //d&#65533;gito verificador
            for (int i = 0; i < ie.length() - 1; i++) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
                peso--;
            }

            d = ((soma * 10) % 11);
            if (d == 10) {
                d = 0;
            }

            //valida o digito verificador
            String dv = d + "";
            if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
                throw new Exception("Digito verificador inválido.");
            }
        } else {
            int soma = 0;
            int peso = 10;
            int d = -1; //d&#65533;gito verificador
            for (int i = 0; i < ie.length() - 1; i++) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
                peso--;
            }
            d = ((soma * 10) % 11);
            if (d == 10) {
                d = 0;
            }

            //valida o digito verificador
            String dv = d + "";
            if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
                throw new Exception("Digito verificador inválido.");
            }
        }

    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Rio Grande do Sul
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIERioGrandeSul(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 10) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //C&#65533;lculo do d&#65533;fito verificador
        int soma = Integer.parseInt(String.valueOf(ie.charAt(0))) * 2;
        int peso = 9;
        int d = -1; //d&#65533;gito verificador
        for (int i = 1; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        d = 11 - (soma % 11);
        if (d == 10 || d == 11) {
            d = 0;
        }

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Rond&#65533;nia
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIERondonia(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 14) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //C&#65533;lculo do d&#65533;gito verificador
        int soma = 0;
        int pesoInicio = 6;
        int pesoFim = 9;
        int d = -1; //d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            if (i < 5) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicio;
                pesoInicio--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFim;
                pesoFim--;
            }
        }

        d = 11 - (soma % 11);
        if (d == 11 || d == 10) {
            d -= 10;
        }

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Rora&#65533;ma
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIERoraima(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 9) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //valida os dois primeiros d&#65533;gitos
        if (!ie.substring(0, 2).equals("24")) {
            throw new Exception("Inscrição estadual inválida.");
        }

        int soma = 0;
        int peso = 1;
        int d = -1; //d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso++;
        }

        d = soma % 9;

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Santa Catarina
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIESantaCatarina(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 9) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //C&#65533;lculo do d&#65533;fito verificador
        int soma = 0;
        int peso = 9;
        int d = -1; //d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        d = 11 - (soma % 11);
        if ((soma % 11) == 0 || (soma % 11) == 1) {
            d = 0;
        }

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do S&#65533;o Paulo
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIESaoPaulo(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 12 && ie.length() != 13) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        if (ie.length() == 12) {
            int soma = 0;
            int peso = 1;
            int d1 = -1; //primeiro d&#65533;gito verificador
            //c&#65533;lculo do primeiro d&#65533;gito verificador (nona posi&#65533;&#65533;o)
            for (int i = 0; i < ie.length() - 4; i++) {
                if (i == 1 || i == 7) {
                    soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * ++peso;
                    peso++;
                } else {
                    soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
                    peso++;
                }
            }

            d1 = soma % 11;
            String strD1 = Integer.toString(d1); //O d&#65533;gito &#65533; igual ao algarismo mais a direita do resultado de (soma % 11)
            d1 = Integer.parseInt(String.valueOf(strD1.charAt(strD1.length() - 1)));

            //c&#65533;lculo do segunfo d&#65533;gito
            soma = 0;
            int pesoInicio = 3;
            int pesoFim = 10;
            int d2 = -1; //segundo d&#65533;gito verificador
            for (int i = 0; i < ie.length() - 1; i++) {
                if (i < 2) {
                    soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicio;
                    pesoInicio--;
                } else {
                    soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFim;
                    pesoFim--;
                }
            }

            d2 = soma % 11;
            String strD2 = Integer.toString(d2); //O d&#65533;gito &#65533; igual ao algarismo mais a direita do resultado de (soma % 11)
            d2 = Integer.parseInt(String.valueOf(strD2.charAt(strD2.length() - 1)));

            //valida os d&#65533;gitos verificadores
            if (!ie.substring(8, 9).equals(d1 + "")) {
                throw new Exception("Inscrição estadual inválida.");
            }
            if (!ie.substring(11, 12).equals(d2 + "")) {
                throw new Exception("Inscrição estadual inválida.");
            }

        } else {
            //valida o primeiro caracter
            if (ie.charAt(0) != 'P') {
                throw new Exception("Inscrição estadual inválida.");
            }

            String strIE = ie.substring(1, 10); //Obt&#65533;m somente os d&#65533;gitos utilizados no c&#65533;lculo do d&#65533;gito verificador
            int soma = 0;
            int peso = 1;
            int d1 = -1; //primeiro d&#65533;gito verificador
            //c&#65533;lculo do primeiro d&#65533;gito verificador (nona posi&#65533;&#65533;o)
            for (int i = 0; i < strIE.length() - 1; i++) {
                if (i == 1 || i == 7) {
                    soma += Integer.parseInt(String.valueOf(strIE.charAt(i))) * ++peso;
                    peso++;
                } else {
                    soma += Integer.parseInt(String.valueOf(strIE.charAt(i))) * peso;
                    peso++;
                }
            }

            d1 = soma % 11;
            String strD1 = Integer.toString(d1); //O d&#65533;gito &#65533; igual ao algarismo mais a direita do resultado de (soma % 11)
            d1 = Integer.parseInt(String.valueOf(strD1.charAt(strD1.length() - 1)));

            //valida o d&#65533;gito verificador
            if (!ie.substring(9, 10).equals(d1 + "")) {
                throw new Exception("Inscrição estadual inválida.");
            }
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Sergipe
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIESergipe(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 9) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //c&#65533;lculo do d&#65533;gito verificador
        int soma = 0;
        int peso = 9;
        int d = -1; //d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
            peso--;
        }

        d = 11 - (soma % 11);
        if (d == 11 || d == 11 || d == 10) {
            d = 0;
        }

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Tocantins
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIETocantins(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 9 && ie.length() != 11) {
            throw new Exception("Quantidade de d&#65533;gitos inv&#65533;lida.");
        } else if (ie.length() == 9) {
            ie = ie.substring(0, 2) + "02" + ie.substring(2);
        }

        int soma = 0;
        int peso = 9;
        int d = -1; //d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 1; i++) {
            if (i != 2 && i != 3) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * peso;
                peso--;
            }
        }
        d = 11 - (soma % 11);
        if ((soma % 11) < 2) {
            d = 0;
        }

        //valida o digito verificador
        String dv = d + "";
        if (!ie.substring(ie.length() - 1, ie.length()).equals(dv)) {
            throw new Exception("Digito verificador inválido.");
        }
    }

    /**
     * Valida inscri&#65533;&#65533;o estadual do estado do Distrito Federal
     *
     * @param ie (Inscri&#65533;&#65533;o estadual)
     * @throws Exception
     */
    private static void validaIEDistritoFederal(String ie) throws Exception {
        //valida quantida de d&#65533;gitos
        if (ie.length() != 13) {
            throw new Exception("Quantidade de digitos inválidas.");
        }

        //c&#65533;lculo do primeiro d&#65533;gito verificador
        int soma = 0;
        int pesoInicio = 4;
        int pesoFim = 9;
        int d1 = -1; //primeiro d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 2; i++) {
            if (i < 3) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicio;
                pesoInicio--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFim;
                pesoFim--;
            }
        }

        d1 = 11 - (soma % 11);
        if (d1 == 11 || d1 == 10) {
            d1 = 0;
        }

        //c&#65533;lculo do segundo d&#65533;gito verificador
        soma = d1 * 2;
        pesoInicio = 5;
        pesoFim = 9;
        int d2 = -1; //segundo d&#65533;gito verificador
        for (int i = 0; i < ie.length() - 2; i++) {
            if (i < 4) {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoInicio;
                pesoInicio--;
            } else {
                soma += Integer.parseInt(String.valueOf(ie.charAt(i))) * pesoFim;
                pesoFim--;
            }
        }

        d2 = 11 - (soma % 11);
        if (d2 == 11 || d2 == 10) {
            d2 = 0;
        }

        //valida os digitos verificadores
        String dv = d1 + "" + d2;
        if (!dv.equals(ie.substring(ie.length() - 2, ie.length()))) {
            throw new Exception("Digito verificador inválido.");
        }
    }

}

//para transformar um vetor de char em uma String faça assim
//String r = new String(vetDeChar);

