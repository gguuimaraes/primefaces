package br.com.senai.fatesg.primefaces.formatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelefoneFormatter implements Formatter {
	
	public String format(String telefone) {
		//return cpf.replace("/(\d{3})(\d{3})(\d{3})(\d{2})/g","\$1.\$2.\$3\-\$4");
		Pattern pattern = Pattern.compile("(\\d{2})" + (telefone.length() == 11 ? "(\\d{1})" : "") + "(\\d{4})(\\d{4})");
		Matcher matcher = pattern.matcher(telefone);
		if(matcher.find()){
			return matcher.replaceAll("($1) " + (telefone.length() == 11 ? "$2$3-$4" : "$2-$3"));
		}
		return telefone;
	}
}
