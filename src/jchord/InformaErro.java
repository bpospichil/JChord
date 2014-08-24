package jchord;

import javax.swing.*;

/*
 * Classe desenvolvida com objetivo de informar o usuário
 *  de seus possíveis erros
 */
public class InformaErro extends JPanel {
	
	static final long serialVersionUID = 0;
	
	// Nome da Janela Criada
	private String NomeJanela;
	
	//Mensagem contida na Janela
	private String MensagemErro;
	
	/*
	 * Setter e Getter de NomeJanela
	 */
	public void setNomeJanela(String nome) {
	    this.NomeJanela = nome;
	}
	public String getNomeJanela() {
		return NomeJanela;
	}
	
	/*
	 * Setter e Getter de MensagemErro
	 */
	public void setMensagemErro(String DescricaoErro) {
	    this.MensagemErro = DescricaoErro;
	}
	public String getMensagemErro() {
		return MensagemErro;
	}
	
	/* 
	 * Cria uma Janela com o nome fonecido pelo campo Nome
	 * e com a mensagem repassada em Mensagem  
	 */
	InformaErro(String Mensagem, String Nome){
	  setNomeJanela(Nome);
	  setMensagemErro(Mensagem);
	  JOptionPane.showMessageDialog(null,getMensagemErro(),getNomeJanela(),
		      JOptionPane.PLAIN_MESSAGE);
      
	}

}
