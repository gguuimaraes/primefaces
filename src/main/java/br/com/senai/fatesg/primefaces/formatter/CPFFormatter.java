package br.com.senai.fatesg.primefaces.formatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPFFormatter implements Formatter {
	
	public String format(String cpf) {
		//return cpf.replace("/(\d{3})(\d{3})(\d{3})(\d{2})/g","\$1.\$2.\$3\-\$4");
		Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");
		Matcher matcher = pattern.matcher(cpf);
		if(matcher.find()){
			return matcher.replaceAll("$1.$2.$3-$4");
		}
		return cpf;
	}
}
