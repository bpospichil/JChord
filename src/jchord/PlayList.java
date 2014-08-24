package jchord;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import javax.swing.event.*;



public class PlayList extends JPanel implements ListSelectionListener {
	
	static final long serialVersionUID = 0;
	
	/*
	 * Fun��es e bot�es relacionados a ajuda fornecida ao 
	 * usu�rio
	 */
	private Ajuda ajudaUsuario = new Ajuda();
	
	/*
	 * Fun��es e bot�es relacionados a leitura de arquivo 
	 * originados na classe "CarregarArquivo"
	 */
	private CarregarArquivo arquivo = new CarregarArquivo();
	
	/*
	 * Fun��es e bot�es relacionados a sele��o de acordes 
	 * oriundas da classe "SelecaoAcordes" 
	 */
	private final SelecaoAcordes selecaoAcordes = new SelecaoAcordes();
	
	
	/*
	 * Cria��o dos bot�es de 
	 * Adicionar um Acorde 
	 * Excluir um Acorde
	 * Deletar todos Acordes
	 */
	private JButton botaoAdicionar = new JButton("Adicionar");
	private JButton botaoExcluir = new JButton("Excluir");
	private JButton botaoDeletar = new JButton("Deletar");
	
	
	/*
	 * Cria��o de um modelo de tabela que n�o permite a edi��o
	 *  de suas colulas
	 */
	public static DefaultTableModel conteudoPlayList = new DefaultTableModel(){
  
		private static final long serialVersionUID = 0;

		public boolean isCellEditable(int rowIndex, int mColIndex) {
            return false;
        }
    };
    
	/*
	 * Cria��o da Tabela "listaReproducao" segundo o modelo 
	 * "conteudoPlayList"
	 */
    private final JTable listaReproducao = new JTable(conteudoPlayList);
	
    /*
     * Defini��o do nome da coluna da tabela "listaReproducao"
     */
    private static String nomeColuna = "Nome";
    
	/*
	 * Dimens�es da Tabela "listaReprodu��o"
	 */
    private static int larguraTabela = 250;
	private static int alturaTabela = 350;
	
	
	/*
	 * Inclus�o da barra de rolagem na "listaReproducao"
	 */
	private final JScrollPane estiloPlayList = new JScrollPane(listaReproducao);
	
	
	/*
	 * Fun��o que deleta todo o conte�do da "listaReproducao"
	 * Essa fun��o � utilizada pelo bot�o de deletar 
	 * ("botaoDeletar")
	 */
	public void DeletaPlayList(DefaultTableModel modelo){
		if (modelo.getRowCount() == 0){
			new InformaErro("N�o h� nenhum acorde na PlayList", "Erro");
		}
		else{
			while (modelo.getRowCount()>0){
				modelo.removeRow(0);
				}
		}
	}
	
	
	/*
	 * Indica o indice da c�lula selecionada
	 */
	public int CelulaSelecionada(JTable Tabela){
		return Tabela.getSelectedRow();
	}	
	
	/*
	 * Indica se uma colula foi selecionada 
	 */
	public boolean VerificaSelecao(JTable Tabela){
		if(Tabela.getSelectedRow()!= -1){ 
			return true;	
		}
		else{
			return false;
		}
	}
	

	/*
	 * Dimensiona a Tabela "listaReproducao" segundo os 
	 * par�metros fornecido (largura e altura)
	 */
	public void dimensionaTabela(){
		 listaReproducao.setPreferredScrollableViewportSize(new 
			        Dimension(larguraTabela, alturaTabela));
	}
	
	/*
	 * Verifica se um acorde fornecido pelo usu�rio � v�lido
	 */
	public boolean acordeValido(String acorde){
		if (ChordParser.isValid(acorde)){
			return true;
		}
		else{
			return false;
		}
	}
	
    public void valueChanged(ListSelectionEvent e) {
    	JTable tb = this.listaReproducao;
    	int cell = CelulaSelecionada(tb);
    	if(cell != -1){
    		String cifra = (String) tb.getValueAt(cell, 0);
    		JChord.setCifra(cifra);
    	}
    }
    	
	public PlayList(){

		/*
		 * Adiciona a "listaReproducao" uma coluna, cujo nome
		 * foi definido por nomeColuna
		 */
	    conteudoPlayList.addColumn(nomeColuna);

	    //Adiciona a respectiva fun��o ao botao "botaoAdiciona"
	    botaoAdicionar.addActionListener(
	    	new ActionListener(){
	    		public void actionPerformed(ActionEvent e){ 
	    			String Nome = selecaoAcordes.NomeAcordeSelecionado();
	    			if(acordeValido(Nome)){
	    				conteudoPlayList.addRow(new Object[]{Nome});
	    			}
	    			else{
	    				new InformaErro("O acorde selecionado n�o � v�lido","ERRO:");
	    			}
	    		}
	    	}	
	    ); 
	    
	    //Adiciona a respectiva fun��o ao botao "botaoExcluir"
	  	botaoExcluir.addActionListener(
	  	      new ActionListener(){
	  	      	public void actionPerformed(ActionEvent e){  	  
	  	      		if(VerificaSelecao(listaReproducao)){ 
	  	      			conteudoPlayList.removeRow(CelulaSelecionada(listaReproducao));
	  	      		}
	  	      		else{
	  	      			new InformaErro("Nenhum acorde foi selecionado","ERRO");
	  	      		}
	  	      	}
	  	      }	
	  	    ); 
	    
	    //Adiciona a respectiva fun��o ao botao "botaoDeletar"
	    botaoDeletar.addActionListener(
	      new ActionListener(){
	      	public void actionPerformed(ActionEvent e){ 
	      		DeletaPlayList(conteudoPlayList);
	        }
	      }	
	    ); 
	    	    
	    listaReproducao.getSelectionModel().addListSelectionListener(this);	    
	     
	    dimensionaTabela();
	    this.setLayout( null );
	    
	    estiloPlayList.setBounds(10, 10, larguraTabela, alturaTabela);
	    this.add(estiloPlayList);
	       
	    selecaoAcordes.setBounds( (larguraTabela + 10), (alturaTabela - 105) , 200, 50 );
	    this.add(selecaoAcordes);
		
	    botaoAdicionar.setBounds( (larguraTabela + 220), (alturaTabela - 100) , 90, 25 );  
		this.add( botaoAdicionar );
		
		botaoExcluir.setBounds( (larguraTabela + 320), (alturaTabela - 100) , 90, 25 );
		this.add( botaoExcluir );
		
		botaoDeletar.setBounds( (larguraTabela + 32), (alturaTabela - 50) , 90, 25 );
		this.add(botaoDeletar);
		
		arquivo.setBounds( (larguraTabela + 130), (alturaTabela - 55) , 90, 50 );
	    this.add(arquivo);
	    
	    ajudaUsuario.setBounds( (larguraTabela + 220), (alturaTabela - 55) , 90, 50 );
	    this.add(ajudaUsuario);
	    
	  }
	
	}


