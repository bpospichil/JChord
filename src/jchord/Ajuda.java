package jchord;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ajuda extends JPanel implements ActionListener{

	private static final long serialVersionUID = 0;
	
	/*
	 * Criação do Botão de Ajuda
	 */
	private JButton botaoAjuda = new JButton("Ajuda");
	
	/*
	 * Texto de Ajuda Fornecido ao usuário
	 */
	private static String textoAjuda = "Entrada de dados (Seção 3 Readme)\n" +
	"Você pode inserir acordes no JChord de duas formas:\n" +
	"     Inserção por drop down - Selecione nos menus a nota fundamentale o modificador desejado,\n" +
	"de acordo com os templates que existentes.\n" +
	"     Inserção por arquivo texto - Insira os acordes desejados, em um arquivo texto (*.txt) seguindo\n" +
	"os mesmos moldes dos templates existentes, contendo um acorde por linha. Após, clicando em\n" +
	"\"Abrir\" abra o arquivo desejado.\n\n" +
	"Exemplos de acordes válidos: \"C\", \"D#4\", \"F°\", \"G5-/7\", \"Em7\", \"Cm\".\n" +
	"Exemplos de acordes inválidos: \"C##\", \"D#b\", \"F5°\", \"Gm4-/7\", \"Em7-\", \"aE-\".\n" +
	"Para ver a lista completa de acordes aceitos, consulte a seção 5 do Readme.\n\n" +
	"Saída de dados (Seção 4 Readme)\n" +
	"O JChord irá emitir os sons, nos modos descritos na seção um, bem como exibir no teclado\n" +
	"quais as teclas necessárias para a formação do acorde desejado.";
	
	/*
	 * Nome da Janela fornecida ao usuário
	 */
	private static String nomeJanela = "AJUDA:";
	
	/*
	 * Implementação da função que mostra o conteúdo de ajuda
	 * ao usuário quando ele clicar no botão de ajuda
	 * "botaoAjuda"
	 */
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoAjuda) {
        	JOptionPane.showMessageDialog(null,textoAjuda,nomeJanela,
  			      JOptionPane.PLAIN_MESSAGE);
        }
	}
	
	/*
	 * Inclusão do botão de ajuda no painel com sua respectiva
	 * função associada
	 */
	public Ajuda(){
		botaoAjuda.addActionListener(this);
        this.add(botaoAjuda);
	}

}
