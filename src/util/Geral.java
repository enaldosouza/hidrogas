
package util;

/**
 *
 * @author enaldo.souza
 */
public class Geral {
    
}

/*

<?php
/**
* Criado em 31/05/2010
*
* Essa classe helper auxilia de forma a validar a entrada de dados no sistema
* 
* @author unitri
* @version 1.0.0
*
	public function validaEmail(&$email)
	{
        $email = @ereg_replace("[áàâãª]", "a", $email);
        $email = @ereg_replace("[ÁÀÂÃ]", "A", $email);
        $email = @ereg_replace("[éèê]", "e", $email);
        $email = @ereg_replace("[ÉÈÊ]", "E", $email);
        $email = @ereg_replace("[óòôõº]", "o", $email);
        $email = @ereg_replace("[ÓÒÔÕ]", "O", $email);
        $email = @ereg_replace("[úùû]", "u", $email);
        $email = @ereg_replace("[ÚÙÛ]", "U", $email);
        $email = str_replace("ç", "c", $email);
        $email = str_replace("Ç", "C", $email);
        $email = @ereg_replace(" ", "", $email);
	}
?>
	
	/**
	 * Função que valida se um email é valido 
	 * @param string
	 * @return bool
	 * *//*
		function validarEmail($email){ 
		   $mail_correcto = 0; 
		   if ((strlen($email) >= 6) && (substr_count($email,"@") == 1) && (substr($email,0,1) != "@") && (substr($email,strlen($email)-1,1) != "@")){ 
		      if ((!strstr($email,"'")) && (!strstr($email,"\"")) && (!strstr($email,"\\")) && (!strstr($email,"\$")) && (!strstr($email," "))) { 
		         if (substr_count($email,".")>= 1){ 
		            $term_dom = substr(strrchr ($email, '.'),1); 
		         if (strlen($term_dom)>1 && strlen($term_dom)<5 && (!strstr($term_dom,"@")) ){ 
		            $antes_dom = substr($email,0,strlen($email) - strlen($term_dom) - 1); 
		            $caracter_ult = substr($antes_dom,strlen($antes_dom)-1,1); 
		            if ($caracter_ult != "@" && $caracter_ult != "."){ 
		               $mail_correcto = 1; 
		            } 
		         } 
		      } 
		   } 
		}
		return $mail_correcto;
		}
	*/
		
         /**
    	 * Função que valida se um cpf é valido 
    	 * @param string
    	 * @return bool
    	 * 
        function validarCPF($cpf)
		{	
		    // Verifiva se o número digitado contém todos os digitos
		    $cpf = str_pad(@ereg_replace('[^0-9]', '', $cpf), 11, '0', STR_PAD_LEFT);
			
			// Verifica se nenhuma das sequências abaixo foi digitada, caso seja, retorna falso
		    if (strlen($cpf) != 11 || $cpf == '00000000000' || $cpf == '11111111111' || $cpf == '22222222222' || $cpf == '33333333333' || $cpf == '44444444444' || $cpf == '55555555555' || $cpf == '66666666666' || $cpf == '77777777777' || $cpf == '88888888888' || $cpf == '99999999999')
			{
			return false;
		    }
			else
			{   // Calcula os números para verificar se o CPF é verdadeiro
		        for ($t = 9; $t < 11; $t++) {
		            for ($d = 0, $c = 0; $c < $t; $c++) {
		                $d += $cpf{$c} * (($t + 1) - $c);
		            }
		
		            $d = ((10 * $d) % 11) % 10;
		
		            if ($cpf{$c} != $d) {
		                return false;
		            }
		        }
		
		        return true;
		    }
		}
		
    	 * Função que valida se um cnpj é valido 
    	 * @param string
    	 * @return bool
    	 * 
		function validaCNPJ($cnpj) { 
		   	
			    if (strlen($cnpj) <> 18) return 0; 
			    $soma1 = ($cnpj[0] * 5) + 
			
			    ($cnpj[1] * 4) + 
			    ($cnpj[3] * 3) + 
			    ($cnpj[4] * 2) + 
			    ($cnpj[5] * 9) + 
			    ($cnpj[7] * 8) + 
			    ($cnpj[8] * 7) + 
			    ($cnpj[9] * 6) + 
			    ($cnpj[11] * 5) + 
			    ($cnpj[12] * 4) + 
			    ($cnpj[13] * 3) + 
			    ($cnpj[14] * 2); 
			    $resto = $soma1 % 11; 
			    $digito1 = $resto < 2 ? 0 : 11 - $resto; 
			    $soma2 = ($cnpj[0] * 6) + 
			
			    ($cnpj[1] * 5) + 
			    ($cnpj[3] * 4) + 
			    ($cnpj[4] * 3) + 
			    ($cnpj[5] * 2) + 
			    ($cnpj[7] * 9) + 
			    ($cnpj[8] * 8) + 
			    ($cnpj[9] * 7) + 
			    ($cnpj[11] * 6) + 
			    ($cnpj[12] * 5) + 
			    ($cnpj[13] * 4) + 
			    ($cnpj[14] * 3) + 
			    ($cnpj[16] * 2); 
			    $resto = $soma2 % 11; 
			    $digito2 = $resto < 2 ? 0 : 11 - $resto; 
			    return (($cnpj[16] == $digito1) && ($cnpj[17] == $digito2)); 
			}

	/**
    	 * Função que valida se existem valores vazios em um array  
    	 * @param array
    	 * @param array
    	 * @return bool
    	 * @author Jose Guilherme Honorato
    	 * 
        function validarCamposVazio($arrPost=array(), $arrKeysVazios=array())
		{
		    
            foreach($arrPost as $k => $campo)
            {
                if(in_array($k, $arrKeysVazios) AND empty($campo)) {
                    return false;
                }
            }
            return true;
            }
		
         /**
    	 * Função que valida se uma data é valida
    	 * @param string
    	 * @return bool
    	 * 
        function validarData($date) 
        {
		    
            $char = strpos($date, "/")!==false?"/":"-";
            $date_array = explode($char,$date);
            if(count($date_array)!=3) { 
                return false;
            }
            return checkdate($date_array[1],$date_array[0],$date_array[2])?($date_array[2] . "-" . $date_array[1] . "-" . $date_array[0]):false;
        
        }
        
        /**
    	 * Função que retorna o erro formatado
    	 * @param array
    	 * @return string
    	 * 
        public function retornarErro($arr = array()) 
        {
            foreach($arr as $k => $reg) 
            {
               if(empty($reg)) {
                   unset($arr[$k]);
               } 
            }
            return implode('<br>',$arr);
        }
	/**
	 * Método responsável em comparar duas datas e demonstrar se uma destas é maior
	 * do que a outra.
	 * 
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 14 de Abril, 2014
	 * 
	 * @param String $dtInicio
	 * @param String $dtFim
	 * @return Boolean $resultado || FALSE
	public function compararDatas($dtInicio, $dtFim)
	{
		if ($dtInicio AND $dtFim) {
			# Ajustando o TIMESTAMP da data de ínicio.
			$dtInicio = explode('/', $dtInicio);
			$dtInicio = $dtInicio['2'].'-'.$dtInicio[1].'-'.$dtInicio[0];
			$dtInicio = strtotime($dtInicio);
			
			# Ajustando o TIMESTAMP da data final.
			$dtFim = explode('/', $dtFim);
			$dtFim = $dtFim['2'].'-'.$dtFim[1].'-'.$dtFim[0];
			$dtFim = strtotime($dtFim);
			
			if ($dtInicio >= $dtFim) {
				return TRUE;
			}
			return FALSE;
		}
		return TRUE;
	}
	
	
	/**
	 * Método responsável em efetuar a validação de um determinado email.
	 * 
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 27 de Maio, 2015
	 * 
	 * @param  String $email
	 * @return Boolean || FALSE
	public function validaEmail($email)
	{
		if ($email) {
			$email = trim($email);
	    	return (filter_var($email, FILTER_VALIDATE_EMAIL)) ? TRUE : FALSE;
		}
		return FALSE;
	}
	
	/**
	 * Método responsável em efetuar a validação de uma determinada data.
	 *
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 09 de Janeiro, 2014
	 *
	 * @param String $data
	 * @return Boolean $isValida || FALSE
         * 
	public function validarData($data)
	{
		if ($data) {
			$arrData = explode('/', $data);
			
			$dia = $arrData[0];
			$mes = $arrData[1];
			$ano = $arrData[2];
			
			$isValida = checkdate($mes, $dia, $ano);
			return $isValida;
		}
		return FALSE;
	}
	
	
	/**
	 * Método responsável em validar a obrigatoriedade de todos os campos de um 
	 * determinado formulário.
	 * 
	 * @param Array $arrCampos
	 * @return String || Boolean
	 *
	public function validarCampos($arrCampos = array ())
	{
		if (! empty($arrCampos)) {
			foreach ($arrCampos as $campo => $valor) {
				if (empty($valor)) {
					return 'O campo '.$campo.' é de preenchimento obrigatório!';
				}
			}
			return true;
		}
		return false;
	}
	
	
	/**
	 * Método no qual efetua a conversão de um vetor de dados em uma string. Muito 
	 * utilizado para geração de logs.
	 * 
	 * @param Array $array
	 * @param String $string
	 *
	public function converterArrayToString(array $array)
	{
		$string = '';
		if (! empty($array)) {
			foreach ($array as $chave => $valor) {
				$string .= $chave.' => '.$valor." \n";
			}
		}
		return $string;
	}
	
	/**
	 * Método responsável em remover a incidência de algum caracter especial
	 * dentro de um vetor de dados.
	 * 
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 04 de Julho, 2013
	 * 
	 * @throws Exception
	 * @param String $caracter
	 * @param Array $arrIndices
	 * @param Array $arrDados
	 * @param String $caracterSubstituto
	 * @return Array $arrResultado
	 *
         * 
	
        public function removerEspecialCharInArray (
		$caracter, 
		array $arrDados, 
		array $arrIndices, 
		$caracterSubstituto = ''
	)
	{
		if (! empty($arrDados) AND ! empty($arrIndices)) {
			foreach ($arrDados as $chave => $valor) {
				if (in_array($chave, $arrIndices)) {
					$arrDados[$chave] = str_replace($caracter, $caracterSubstituto, $valor);
				}
			}
			return $arrDados;
		}
		return new Exception('Esperado vetor de índice e de dados!');
	}
	
	
	/**
	 * Método responsável em verificar se um determinado cpf válido.
	 * 
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 04 de Julho, 2013
	 * 
	 * @param String $cpf
	 * @return Boolean
	 *
	
        public function validarCpf($cpf)
	{
		if (! empty($cpf)) {
			$arrCaracteres = array ('.', '-');
			$cpf = str_replace($arrCaracteres, '', $cpf);
			$cpf = str_pad(preg_replace('[^0-9]', '', $cpf), 11, '0', STR_PAD_LEFT);
			
			$arrInvalidos = array (
				'00000000000', 
				'11111111111',
				'22222222222', 
				'33333333333', 
				'44444444444', 
				'55555555555', 
				'66666666666', 
				'77777777777', 
				'88888888888', 
				'99999999999',
			);
			
			if (strlen($cpf) != 11 || in_array($cpf, $arrInvalidos)) {
				return false;
			} else {
				for ($t = 9; $t < 11; $t++) {
					for ($d = 0, $c = 0; $c < $t; $c++) {
						$d += $cpf{$c} * (($t + 1) - $c);
					}
					$d = ((10 * $d) % 11) % 10;
					if ($cpf{$c} != $d) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Método responsável em escapar os caracteres especiais de uma determinada string.
	 *
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 26 de Agosto, 2013
	 *
	 * @param String $string
	 * @return String $string
	 *
	
        public function escapeString($string = '')
	{
		if (is_string($string)) {
			$string = (get_magic_quotes_gpc() ? stripslashes($string) : $string);
			$string = addcslashes(trim($string), "\\\'\"&");
			$string = addslashes($string);
			return $string;
		}
		return $string;
	}
	
	
	/**
	 * Método responsável em retornar o valor monetário de uma determinada string
	 * para exibição na boleta de pagamento.
	 *
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 26 de Agosto, 2013
	 *
	 * @param Array $arrDados
	 * @param String $indice
	 * @return String $valor || ''
	 *
         * 
	
        public function getValorMonetario(array $arrDados, $indice)
	{
		if (isset($arrDados[$indice])) {
			$valor = (string) $arrDados[$indice];
			if ($valor !== '0') {
				return number_format($valor, 2, ',', '.');
			}
			return '';
		}
		return '';
	}
	
	
	/**
	 * Método responsável em efetuar a troca de um determinado padrão '%valor%',
	 * para um outro valor estipulado.
	 *
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 20 de Agosto, 2013
	 *
	 * @throws Exception
	 * @param String $string
	 * @param String $valorSubstituto
	 * @param String $valor
	 * @return String $strResultado
	 *
	public function parserValor($string, $valorSubstituto = '', $valor = 's')
	{
		if (isset($string) AND isset($valor)) {
			if (is_string($string) AND is_string($valor)) {
				$valorSubstituto = trim($valorSubstituto);
				return preg_replace("/%".$valor."%/i", $valorSubstituto, $string);
			}
			return new Exception('Aceita-se apenas strings!');
		}
		return new Exception('Esperada string e valor a ser substituído!');
	}
	
	
	/**
	 * Método responsável em formatar uma determinada data para um dos seguintes formatos:
	 * DD/MM/AAAA -> AAAAMMDD e AAAAMMDD -> DD/MM/AAAA.
	 *
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 20 de Agosto, 2013
	 *
	 * @param String $data
	 * @return String $data
	 *
	public function formatarData($data)
	{
		if (substr_count($data, '/')) {
			$data = explode('/', $data);
			$data = $data[2].$data[1].$data[0];
		} else {
			$data = substr($data, 6, 2).'/'.substr($data, 4, 2).'/'.substr($data, 0, 4);
		}
		return $data;
	}
	
	
	/**
	 * Método responsável em remover os valores depreciados ou inexistentes de 
	 * algumas filiais do conglomerado.
	 * 
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 18 de Setembro, 2013
	 * 
	 * @param Array $arrFiliais
	 * @param String $strFiliais
	 * @param String $delimitador
	 * @return Array $arrFiliais || NULL
	 *
	public function delFips (
		$arrFiliais, 
		$strFiliais, 
		$delimitador = ','
	)
	{
		if ($arrFiliais) {
			$arrRemocao = explode($delimitador, $strFiliais);
			foreach ($arrFiliais as $chave => $valor) {
				foreach ($arrRemocao as $item) {
					if ($valor['COD_FIP'] == $item) {
						unset ($arrFiliais[$chave]);
					}
				}
			}
			return $arrFiliais;
		}
		return NULL;
	}
	
	
	/**
	 * Efetua a diminuição de caracteres de uma determinada String.
	 * Upgrade maligno do Erik Urbanski Santos.
	 *
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 14 de Abril, 2014
	 *
	 * @param String $string
	 * @param Integer $del
	 * @return String
	 *
	public function truncate($string, $limite)
	{
		$tamanho = strlen($string);
		return ($tamanho > $limite) ? substr($string, 0, $limite).'...' : $string;
	}
	
	
	/**
	 * Método responsável em verificar se um determinado campo ou atributo é nulo 
	 * ou vazio. Validação para passagem de parâmetro para o WS.
	 * 
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 06 de Maio, 2014
	 * 
	 * @param Unknown $atributo
	 * @return Unknown $atributo || NULL
	 *
	public function isEmpty($atributo)
	{
		return (empty($atributo) OR is_null($atributo)) ? NULL : $atributo;
	}
	
	
	/**
	 * Método responsável em remover todos os caracteres especiais de uma 
	 * determinada STRING passada.
	 * 
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 06 de Maio, 2014
	 * 
	 * @param String $string
	 * @return String $string
	 *
	function removerCaracteresEspeciais($string = '', $substituicao = '')
	{		
		$array = array('-', '"', '‘', '\'', '!', '@', '#', '$', '%', '¨', '&', '*', '(', ')', '=', '+', '´', '`', '[', ']', '{', '}', '~', '^', ',', '<', '.', '>', ';', ':', '/', '?', '\\', '|', '¹', '²', '³', '£', '¢', '¬', '§', 'ª', 'º', '°');
		return str_replace($array, $substituicao, $string);
	}
	
	
	/**
	 * Método responsável em remover a acentuação de uma determinada STRING.
	 * 
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 06 de Maio, 2014
	 * 
	 * @param String $string
	 * @param String $encode
	 * @return String $string || NULL
	 *
	public function removeAcentos($string, $encode = "UTF-8")
	{
		if (! empty($string)) {
			
			$arrAcentuacao = array(
				'A' => '/&Agrave;|&Aacute;|&Acirc;|&Atilde;|&Auml;|&Aring;/', 
				'a' => '/&agrave;|&aacute;|&acirc;|&atilde;|&auml;|&aring;/', 
				'C' => '/&Ccedil;/', 
				'c' => '/&ccedil;/', 
				'E' => '/&Egrave;|&Eacute;|&Ecirc;|&Euml;/', 
				'e' => '/&egrave;|&eacute;|&ecirc;|&euml;/', 
				'I' => '/&Igrave;|&Iacute;|&Icirc;|&Iuml;/', 
				'i' => '/&igrave;|&iacute;|&icirc;|&iuml;/', 
				'N' => '/&Ntilde;/', 
				'n' => '/&ntilde;/', 
				'O' => '/&Ograve;|&Oacute;|&Ocirc;|&Otilde;|&Ouml;/', 
				'o' => '/&ograve;|&oacute;|&ocirc;|&otilde;|&ouml;/', 
				'U' => '/&Ugrave;|&Uacute;|&Ucirc;|&Uuml;/', 
				'u' => '/&ugrave;|&uacute;|&ucirc;|&uuml;/', 
				'Y' => '/&Yacute;/', 
				'y' => '/&yacute;|&yuml;/', 
				'a.' => '/&ordf;/', 
				'o.' => '/&ordm;/'
			);
			
			$strAuxiliar = preg_replace($arrAcentuacao, array_keys($arrAcentuacao), htmlentities($string, ENT_NOQUOTES, $encode));
			return str_replace(" ", "_", $strAuxiliar);
			
		}
		
		return NULL;
	}
	
	
	/**
	 * Método responsável em acoplar um elemento <optgroup> em uma determinada  
	 * STRING contendo o elemento <select>.
	 * 
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 08 de Maio, 2013
	 * 
	 * @param String $strLabel
	 * @param String $htmlSelect
	 * @return String $optgroup
	 *
	public function getOptgroup($strLabel, $htmlSelect)
	{
		$optgroup = '';
		if ($strLabel AND $htmlSelect) {
			$strLabel  = strtoupper($strLabel);
			$optgroup .= <<<OPTGROUP
			<optgroup class="optgroup-master" label="{$strLabel}">
				{$htmlSelect}
			</optgroup>
OPTGROUP;
		}
		return $optgroup;
	}
	
	
	/**
	 * Método responsável em construir o HTML de um <select> para posteriormente
	 * o mesmo ser acoplado na view.
	 * 
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 08 de Julho, 2013
	 * 
	 * @throws Exception
	 * @param Array $arrDados
	 * @param Array $arrIndiceValores
	 * @param Array $arrIndiceDescricoes
	 * @param String $valorSelecionado
	 * @param String $campoAdicional
	 * @param String $caracterEspecialValores
	 * @param String $caracterEspecialDescricoes
	 * @return String $strCombo
	 *
	public function construirSelect (
		array $arrDados = NULL, 
		array $arrIndiceValores, 
		array $arrIndiceDescricoes, 
		$valorSelecionado = '', 
		$campoAdicional = '', 
		$caracterEspecialValores = '#', 
		$caracterEspecialDescricoes = ' - '
	)
	{
		if (empty($arrIndiceValores) OR empty($arrIndiceDescricoes)) {
			
			return new Exception('Esperado vetor de índice, descrições e dados!');
		
		} else {
			
			if (empty($arrDados) OR is_null($arrDados)) {
				
				return '<option value="">Nenhum registro encontrado!</option>';
				
			} else {
				
				$strCombo = '';
				foreach ($arrDados as $chave => $valor) {
					$arrValores = $arrDescricoes = array ();
					
					foreach ($arrIndiceValores as $cValor => $iValor) {
						$arrValores[] = $valor[$iValor];
					}
					
					foreach ($arrIndiceDescricoes as $cDescricao => $iDescricao) {
						$arrDescricoes[] = $valor[$iDescricao];
					}
					
					# Ajustando o atributo VALUE do <option>.
					$valor  = implode($caracterEspecialValores, $arrValores);
					$valor .= (empty($campoAdicional)) ? '' : $caracterEspecialValores.$campoAdicional;
					
					# Verficando a existência de um <option> selecionado.
					$selecionado = ($valor === $valorSelecionado) ? 'selected' : '';
					
					# Ajustando a descrição do <option>.
					$descricao = implode($caracterEspecialDescricoes, $arrDescricoes);
					
					# HTML correspondente do <option>.
					$strCombo .= <<<OPTION
					<option value="{$valor}" {$selecionado} title="{$descricao}">{$descricao}</option>
OPTION;
				}
				
				return $strCombo;
			}
		}
	}
	
	
	/**
	 * Método responsável em construir um seleção de radio buttons para acoplamento
	 * em formulários de cadastro, edição e seleção de dados.
	 * 
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 24 de Abril, 2014
	 * 
	 * @throws Exception
	 * @param Array $arrDados
	 * @param Array $arrIndiceValores
	 * @param Array $arrIndiceDescricoes
	 * @param String $attrNomeCampo
	 * @param String $caracterEspecialValores
	 * @param String $caracterEspecialDescricoes
	 * @return String $htmlRadio
	 * 
	public function construirRadio (
		array $arrDados = NULL, 
		array $arrIndiceValores, 
		array $arrIndiceDescricoes, 
		$attrNomeCampo = 'attr', 
		$caracterEspecialValores = '#', 
		$caracterEspecialDescricoes = ' - '
	)
	{
		if (empty($arrIndiceValores) OR empty($arrIndiceDescricoes)) {
			
			return new Exception('Esperado vetor de índice ou descrições dos dados!');
			
		} else {
			
			if (empty($arrDados) OR is_null($arrDados)) {
				
				return <<<MENSAGEM
				<label class="radio">
					<strong>Atenção:</strong> Caro usuário, ainda não existem registros cadastros em nossa base de dados, verifique a situação dos mesmos para continuar o procedimento desejadoss!
				</label>
MENSAGEM;
				
			} else {
				
				$count = 1;
				$htmlRadio = '';
				
				foreach ($arrDados as $chave => $valor) {
					$arrValores = $arrDescricoes = array ();
					
					foreach ($arrIndiceValores as $cValor => $iValor) {
						$arrValores[] = $valor[$iValor];
					}
					
					foreach ($arrIndiceDescricoes as $cDescricao => $iDescricao) {
						$arrDescricoes[] = $valor[$iDescricao];
					}
					
					# Ajustando o atributo VALUE do <checkbox>.
					$valor  = implode($caracterEspecialValores, $arrValores);
					
					# Ajustando a descrição do <checkbox>.
					$descricao = implode($caracterEspecialDescricoes, $arrDescricoes);
					
					# Ajustando o atributo NAME do <checkbox>.
					$attrName = strtolower(trim($attrNomeCampo));
					
					# Ajustando o atributo ID do <checkbox>.
					$attrId = $attrName.'-'.$count;
					
					# HTML correspondente do <checkbox>.
					$htmlRadio .= <<<LABEL
					<label class="radio">
						<input type="radio" class="required" id="{$attrId}" name="{$attrName}" title="{$descricao}" value="{$valor}" />
						&nbsp;{$descricao}
					</label>
LABEL;
						
					$count++;
				}
				
				return $htmlRadio;
				
			}
			
		}
	}
	
	
	/**
	 * Método responsável em construir uma GRID contendo uma lista de opções que
	 * sejam selecionáveis através de campos do tipo checkbox.
	 * 
	 * @author Erik Urbanski Santos <erikurbanski@gmail.com>
	 * @since 11 de Abril, 2014
	 * 
	 * @throws Exception
	 * @param Array   $arrDados
	 * @param Array   $arrIndiceValores
	 * @param Array   $arrIndiceDescricoes
	 * @param String  $attrNomeCampo
	 * @param Integer $widthCampo
	 * @param String  $campoAdicional
	 * @param String  $caracterEspecialValores
	 * @param String  $caracterEspecialDescricoes
	 * @return String $htmlCheck
	 *
	public function construirCheck (
		array $arrDados = NULL, 
		array $arrIndiceValores, 
		array $arrIndiceDescricoes, 
		$attrNomeCampo = 'attr',
		$widthCampo = NULL,  
		$campoAdicional = '', 
		$caracterEspecialValores = '#', 
		$caracterEspecialDescricoes = ' - '
	)
	{
		if (empty($arrIndiceValores) OR empty($arrIndiceDescricoes)) {
				
			return new Exception('Esperado vetor de índice ou descrições dos dados!');
		
		} else {
			
			if (empty($arrDados) OR is_null($arrDados)) {
				
				return <<<MENSAGEM
				<div class="controls-multiple controls-error input-xxlarge">
					<label class="checkbox alert alert-error">
						<strong>Atenção:</strong> Caro usuário, ainda não existem registros cadastros em nossa base de dados, verifique a situação dos mesmos para continuar o procedimento desejadoss!
					</label>
				</div>
MENSAGEM;
				
			} else {
				
				$count = 1;
				
				$styleWidth = (is_null($widthCampo) ? '' : 'style="width:'.$widthCampo.'px"');
				
				$htmlCheck  = '<div class="controls-multiple input-xxlarge no-bottom" '.$styleWidth.'>';
				
				foreach ($arrDados as $chave => $valor) {
					$arrValores = $arrDescricoes = array ();
					
					foreach ($arrIndiceValores as $cValor => $iValor) {
						$arrValores[] = $valor[$iValor];
					}
					
					foreach ($arrIndiceDescricoes as $cDescricao => $iDescricao) {
						$arrDescricoes[] = $valor[$iDescricao];
					}
					
					# Ajustando o atributo VALUE do <checkbox>.
					$valor  = implode($caracterEspecialValores, $arrValores);
					$valor .= (empty($campoAdicional)) ? '' : $caracterEspecialValores.$campoAdicional;
					
					# Ajustando a descrição do <checkbox>.
					$descricao = implode($caracterEspecialDescricoes, $arrDescricoes);
					
					# Ajustando o atributo NAME do <checkbox>.
					$attrName = strtolower(trim($attrNomeCampo));
					
					# Ajustando o atributo ID do <checkbox>.
					$attrId = $attrName.'-'.$count;
					
					# Ajustando o efeito zebrado para as linhas da GRID.
					$classeCSS = ((($count % 2) === 0) ? 'option' : '');
					
					# HTML correspondente do <checkbox>.
					$htmlCheck .= <<<LABEL
					<label class="checkbox {$classeCSS}">
						<input id="{$attrId}" name="{$attrName}[]" type="checkbox" title="{$descricao}" value="{$valor}" />
						&nbsp;{$descricao}
					</label>
LABEL;
					
					$count++;
				}
				
				$htmlCheck .= '</div>';
				return $htmlCheck;
				
			}
			
		}
		
	}
	
	
	/**
	 * Método responsável em comparar duas STRINGS contendo sequenciais de forma a 
	 * retornar o que existe de diferente entre as duas. Utilizado PLUGIN CHOSEN.
	 * 
	 * @author Erik Urbanski Santos <erik.urbanski@unitri.edu.br>
        * @since 17 de Novembro, 2014
	 * 
	 * @param String $selecoesVelhas
	 * @param String $selecoesNovas
	 * @return Array $arrRetorno
	 *
	function compararSequencial($itensVelhos = '', $itensNovos = '')
	{
	    $arrItensVelhos = empty($itensVelhos) ? array () : explode('#', $itensVelhos);
	    $arrItensNovos  = empty($itensNovos)  ? array () : explode('#', $itensNovos);
		
	    $arrDelete = $arrInsert = array ();
	    
	    if ($arrItensNovos AND $arrItensVelhos) {
	        foreach ($arrItensVelhos as $itemVelho) {
	            if (! in_array($itemVelho, $arrItensNovos)) {
	                if (! empty($itemVelho)) {
	                    array_push($arrDelete, $itemVelho);
	                }
	            }
	        }
	    }
		
	    if ($arrItensNovos) {
	        foreach ($arrItensNovos as $itemNovo) {
	            if (! in_array($itemNovo, $arrItensVelhos)) {
	                if (! empty($itemNovo)) {
	                    array_push($arrInsert, $itemNovo);
	                }
	            }
	        }
	    }
	    
	    if ($arrItensVelhos AND empty($arrItensNovos)) {
	        foreach ($arrItensVelhos as $itemVelho) {
	            if (! in_array($itemVelho, $arrItensNovos)) {
	                if (! empty($itemVelho)) {
	                    array_push($arrDelete, $itemVelho);
	                }
	            }
	        }
	    }
	    
	    $arrRetorno = array (
	        'ARR_DELETE' => $arrDelete,
	        'ARR_INSERT' => $arrInsert
	    );
	    
	    return $arrRetorno;
	}
	
	/**
	 * Método que converte uma data no formato 01/01/2014
	 * para o formato de INSERT do Oracle 01/Jan/14
	 *
	 * @author Daniel de Aranda Lima <daniel.aranda@unitri.edu.br>
	 * @since 04 de Junho, 2014
	 * @param string $data
	 * @return string
	 *
	public function formataDataOracle($data = NULL)
	{
		if ($data) {
			date_default_timezone_set('America/Sao_Paulo');
			$data = explode('/', $data);
			return date('d/M/y', mktime(0, 0, 0, $data[1], $data[0], $data[2]));
		}
	
		return NULL;
	}
	
	/**
	 * Método que converte horas no formato HH:MM para segundos
	 *
	 * @author Daniel de Aranda Lima <daniel.aranda@unitri.edu.br>
	 * @since 10 de Junho, 2014
	 * @param string $hora
	 * @return string
	 *
	public function converteHora($hora = NULL)
	{
		if ($hora) {
			$hora = explode(':', $hora);
			return ($hora[0] * 3600) + ($hora[1] * 60);
		}
	
		return NULL;
	}
}
*/