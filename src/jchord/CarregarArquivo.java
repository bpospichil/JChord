package jchord;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class CarregarArquivo extends JPanel implements ActionListener {
	
	static final long serialVersionUID = 0;
	
	/*
	 * Cria��o do Bot�o de Abrir o Arquivo
	 */
	private JButton botaoAbrir = new JButton("Abrir");
	
	/*
	 * Cria��o do Painel de Sele��o de Arquivo
	 */
	private JFileChooser fileChooser = new JFileChooser();

	/*
	 * Adi��o do bot�o "botaoAbrir" com sua respectiva fun��o
	 * associada no painel
	 */
	public CarregarArquivo() {      
        botaoAbrir.addActionListener(this);
        this.add(botaoAbrir);
    }
	
	/*
	 * Fun��o que verifica se a extens�o do arquivo � ".txt"
	 */
	public boolean ExtensaoCorreta(String arquivo){
		if(arquivo.endsWith(".txt")){
			return true;
		}
		else{
			return false;
		}
	}
	
	/*
	 * Fun��o que deleta o conte�do pr�vio da PlayList
	 */
	public void DeletaConteudo(){
		while (PlayList.conteudoPlayList.getRowCount()>0){
			PlayList.conteudoPlayList.removeRow(0);
			}
	}
	
	/*
	 * Fun��o que verifica se os acordes presentes em um 
	 * arquivo s�o todos v�lidos
	 * Se n�o forem, um aviso de erro com as linhas erradas
	 * ser� repassado ao usu�rio 
	 * Caso contr�rio o PlayLista � atualizado com os acordes
	 * presentes no arquivo
	 */
	public void AcordesCorretos(File arquivo) 
		throws IOException{
		
		BufferedReader buffer = new BufferedReader(new FileReader(arquivo));
		String linha;
		int indiceLinha = 0;
		int numeroErros = 0;
		String linhasErradas = "";
		String mensagemErro = "";  
		while( (linha = buffer.readLine()) != null ){
			indiceLinha = indiceLinha + 1;
			//Verifica se cifra correta
			if(!ChordParser.isValid(linha)){
				numeroErros = numeroErros + 1;
				linhasErradas = linhasErradas + ", " + indiceLinha;
			
			}
		}
		
		buffer.close();
		
		if (numeroErros == 0){
			DeletaConteudo();
			BufferedReader buffer2 = new BufferedReader(new FileReader(arquivo));
			while( (linha = buffer2.readLine()) != null ){
				PlayList.conteudoPlayList.addRow(new Object[]{linha});
			}
			buffer2.close();
		}
		else if(numeroErros != 0){
			mensagemErro = "Erro nas linhas" + linhasErradas; 
			new InformaErro(mensagemErro,"ERRO:");
		}

	}
		

	/*
	 * Fun��o de abrir o arquivo que � realizada quando o 
	 * bot�o de abrir arquivo ("botaoAbrir")� acionado
	 */
	 public void actionPerformed(ActionEvent e) {

	        
	        if (e.getSource() == botaoAbrir) {
	            int returnVal = fileChooser.showOpenDialog(CarregarArquivo.this);

	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File arquivo = fileChooser.getSelectedFile();
	                String nomeArquivo = arquivo.getName();
	                if(arquivo == null || nomeArquivo.equals("")){
	                	new InformaErro("Nenhum Arquivo foi selecionado","ERRO:");
	                }
	                else if(!ExtensaoCorreta(nomeArquivo)) {
	                	new InformaErro("Extensao do Arquivo Inv�lida", "ERRO:");
	                }
	                else if(!arquivo.canRead()) {
	                	new InformaErro("O Arquivo n�o pode ser lido", "ERRO:");
	                }
	                else {
	                   	try {
	                   		AcordesCorretos(arquivo);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                	
	                }
	                
	            } 
	        
	        }
	 }
	 
	
}
