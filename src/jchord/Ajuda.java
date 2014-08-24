package jchord;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ajuda extends JPanel implements ActionListener{

	private static final long serialVersionUID = 0;
	
	/*
	 * Cria��o do Bot�o de Ajuda
	 */
	private JButton botaoAjuda = new JButton("Ajuda");
	
	/*
	 * Texto de Ajuda Fornecido ao usu�rio
	 */
	private static String textoAjuda = "Entrada de dados (Se��o 3 Readme)\n" +
	"Voc� pode inserir acordes no JChord de duas formas:\n" +
	"     Inser��o por drop down - Selecione nos menus a nota fundamentale o modificador desejado,\n" +
	"de acordo com os templates que existentes.\n" +
	"     Inser��o por arquivo texto - Insira os acordes desejados, em um arquivo texto (*.txt) seguindo\n" +
	"os mesmos moldes dos templates existentes, contendo um acorde por linha. Ap�s, clicando em\n" +
	"\"Abrir\" abra o arquivo desejado.\n\n" +
	"Exemplos de acordes v�lidos: \"C\", \"D#4\", \"F�\", \"G5-/7\", \"Em7\", \"Cm\".\n" +
	"Exemplos de acordes inv�lidos: \"C##\", \"D#b\", \"F5�\", \"Gm4-/7\", \"Em7-\", \"aE-\".\n" +
	"Para ver a lista completa de acordes aceitos, consulte a se��o 5 do Readme.\n\n" +
	"Sa�da de dados (Se��o 4 Readme)\n" +
	"O JChord ir� emitir os sons, nos modos descritos na se��o um, bem como exibir no teclado\n" +
	"quais as teclas necess�rias para a forma��o do acorde desejado.";
	
	/*
	 * Nome da Janela fornecida ao usu�rio
	 */
	private static String nomeJanela = "AJUDA:";
	
	/*
	 * Implementa��o da fun��o que mostra o conte�do de ajuda
	 * ao usu�rio quando ele clicar no bot�o de ajuda
	 * "botaoAjuda"
	 */
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoAjuda) {
        	JOptionPane.showMessageDialog(null,textoAjuda,nomeJanela,
  			      JOptionPane.PLAIN_MESSAGE);
        }
	}
	
	/*
	 * Inclus�o do bot�o de ajuda no painel com sua respectiva
	 * fun��o associada
	 */
	public Ajuda(){
		botaoAjuda.addActionListener(this);
        this.add(botaoAjuda);
	}

}
