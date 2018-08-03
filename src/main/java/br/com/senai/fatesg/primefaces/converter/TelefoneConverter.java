package br.com.senai.fatesg.primefaces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.senai.fatesg.primefaces.formatter.CPFFormatter;
import br.com.senai.fatesg.primefaces.formatter.TelefoneFormatter;

@FacesConverter("telefoneConverter")
public class TelefoneConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
		return arg2;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
		if (arg2 == null) {
			return "";
		}
		TelefoneFormatter formatter = new TelefoneFormatter();
		return formatter.format(arg2.toString());
	}

	public static String formatarTelefone(String telefone) {
		if (telefone == null || "".equals(telefone)) {
			return "";
		}
		return new CPFFormatter().format(telefone);
	}
}