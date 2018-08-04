package br.com.senai.fatesg.primefaces.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import br.com.ambientinformatica.ambientjsf.util.UtilFaces;
import br.com.senai.fatesg.primefaces.entidade.Contato;
import br.com.senai.fatesg.primefaces.persistencia.ContatoDao;

@Named("ContatoControl")
@Scope("conversation")
public class ContatoControl {

	private Contato contato = new Contato();

	private Contato contatoSelecionado;

	@Autowired
	private ContatoDao contatoDao;

	private List<Contato> contatos = new ArrayList<Contato>();

	@PostConstruct
	public void init() {
		listar(null);
	}

	public void confirmar(ActionEvent evt) {
		try {
			contatoDao.alterar(contato);
			listar(evt);
			contato = new Contato();
			UtilFaces.addMensagemFaces("Cadastro realizado com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void excluir(Contato contato) {
		try {
			contatoDao.excluirPorId(contato.getId());
			contatos.remove(contato);
			UtilFaces.addMensagemFaces("Cadastro excluído com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void cancelar(ActionEvent evt) {
		try {
			contato = new Contato();
			UtilFaces.addMensagemFaces("Operação cancelada");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void onRowEdit(RowEditEvent event) {
		try {
			contatoDao.alterar((Contato) event.getObject());
			UtilFaces.addMensagemFaces("Cadastro alterado com sucesso!");
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void onRowCancel(RowEditEvent event) {
		UtilFaces.addMensagemFaces("Operação cancelada");
	}

	public void listar(ActionEvent evt) {
		try {
			contatos = contatoDao.listar();
		} catch (Exception e) {
			UtilFaces.addMensagemFaces(e);
		}
	}

	public void onRowDblClckSelect(SelectEvent event) {
		contato = (Contato) event.getObject();
		System.out.println(contato.getCpf());
	}

	public void preencherFormulario() {
		if (contatoSelecionado == null) {
			System.out.println("nenhum contato selecionado");
			return;
		}
		contato.setId(contatoSelecionado.getId());
		contato.setCpf(contatoSelecionado.getCpf());
		contato.setNome(contatoSelecionado.getNome());
		contato.setDataNascimento(contatoSelecionado.getDataNascimento());
		contato.setTelefone(contatoSelecionado.getTelefone());
		System.out.println(contato);
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public Contato getContatoSelecionado() {
		return contatoSelecionado;
	}

	public void setContatoSelecionado(Contato contatoSelecionado) {
		this.contatoSelecionado = contatoSelecionado;
		System.out.println("set contatoSelecionado: " + contatoSelecionado);
	}

}
