package jchord;

import javax.swing.JComboBox;
import javax.swing.JPanel;

/*
 * Classe responsável por indicar as opções de acordes 
 * disponíveis para serem selecionadas pelo usuário
 */
public class SelecaoAcordes extends JPanel{

	static final long serialVersionUID = 0;
	
	/*
	 * Listagem de todas as possíveis seleções no primeiro 
	 * ComboBox ("menuAcordes1")   
	 */
	private static String[] listaAcordes1 = {"C","C#","D","D#","E","F","F#",
			"G","G#","A","A#","B"};
	
	/*
	 * Listagem de todas as possíveis seleções no segundo 
	 * ComboBox ("menuAcordes2")  
	 */
	private static String[] listaAcordes2 = {"","°","4","7","7+","9","4/7",
			"5-/7","4/9","7/9","7/9-","m","m7","m4/7","m9",
			"m5-/7"};
	
	/*
	 * Criação de ComboBox, os quais são inicializados com a
	 * respectiva lista de possíveis seleções
	 */
	private final JComboBox menuAcordes1 = new JComboBox(listaAcordes1);
	private final JComboBox menuAcordes2 = new JComboBox(listaAcordes2);
	
	/*
	 * Getters dos respectivos ComboBox
	 */
	public JComboBox getAcordeCasa1() {
		return menuAcordes1;
	}
	public JComboBox getAcordeCasa2() {
		return menuAcordes2;
	}

	
	/*
	 * Função que retorna o nome do acorde selecionado pelo 
	 * usuário através da utilização dos ComboBox
	 */
	public String NomeAcordeSelecionado()
	{
		//Busca a cifra do objeto selecionado em cada DropBox
		int indiceMenu1 = menuAcordes1.getSelectedIndex();
		String cifra1 = listaAcordes1[indiceMenu1];
		
		int indiceMenu2 = menuAcordes2.getSelectedIndex();
		String cifra2 = listaAcordes2[indiceMenu2];
		
		// Retorna um string com as duas cifras concatenadas
		return cifra1 + cifra2;
	}
	
	/*
	 * Função que possibilita a visualização de cada ComboBox
	 * pelo usuário
	 */
	public SelecaoAcordes(){
		
		 // Cada ComboBox iniciará selecionando o primeiro
		 // elemento da respectiva lista de seleção 
		menuAcordes1.setSelectedIndex(0);
		menuAcordes2.setSelectedIndex(0);
		
		
		this.add(menuAcordes1);
		this.add(menuAcordes2);
		
	}

}
