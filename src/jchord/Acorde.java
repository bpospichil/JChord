/* UFRGS - Universidade Federal do Rio Grande do Sul (www.ufrgs.br)
 * Instituto de Informática (www.inf.ufrgs.br)
 * INF01120 - Técnicas de Constução de Programas - 2009/2
 * Professor: Marcelo Soares Pimenta
 * Trabalho Prático - Arpejador
 * 
 * Autores:
 * 		Bruno M. Pospichil (173133)
 * 		Gabriel C. Stabel (96463)
 * 		Luiza Souza (162022)
 */
package jchord;
//"In music a chord is a set of three or more different notes from a specific key that sound simultaneously." http://en.wikipedia.org/wiki/Chord_(music)
public class Acorde {

	static final int OITAVA_NUM_TECLAS = 12;
	static public final int OITAVA_MAIOR = 9;
	static public final int OITAVA_MENOR = 3;
	static public final int OITAVA_INIT = 4;
	static public final String[] OITAVAS = {"3ª","4ª","5ª","6ª","7ª","8ª","9ª"};
	private static final int SESSENTA_MIL_MILISEGUNDOS = 60000;
	static public final int MODO_ARPEJO = 0, MODO_ACORDE = 1;
	static public final String[] MODOS_EXECUCAO = {"Arpejo", "Acorde"};
	static public final int BPM_INIT = 80;
	static public final int BPM_MIN = 20;
	static public final int BPM_MAX = 220;
	
	private String cifra = null;
	private int modoExecucao;
	private int bpm;
	private int periodo;
	private int oitava;
	
    public Acorde(String cifra) {
    	if (cifra != null) {
    		this.setCifra(cifra);
    	}
    	this.setBPM(BPM_INIT);
    	this.setModoExecucao(MODO_ARPEJO);
    	this.setOitava(OITAVA_INIT);
    }
   
    public int[] getNumNotas() {

    	int base = OITAVA_NUM_TECLAS * (oitava - 1);
    	
    	ChordParser parser = new ChordParser(cifra);
    	int[] numNotas = parser.getNotes();

    	for(int i = 0; i < numNotas.length; i++){
			numNotas[i] = numNotas[i] + base; 
		}
    	
    	return numNotas;
    }
    
    public int[][] getNotasNoTempo() {
    	int[][] tempoNotas = {{}};    	
    	int[] numNotas = getNumNotas();
    	switch(modoExecucao){
    		case MODO_ARPEJO:
    			tempoNotas = new int[numNotas.length][1];
				for(int i=0; i < numNotas.length; i++){
					tempoNotas[i][0] = numNotas[i];
			    }
				break;
				
    		case MODO_ACORDE:
    			tempoNotas = new int[1][numNotas.length];
				for(int i=0; i < numNotas.length; i++){
					tempoNotas[0][i] = numNotas[i];
			    }
				break;	
    	}
    	return tempoNotas;
    }
    
	public boolean setCifra(String cifra) {
    	if(ChordParser.isValid(cifra)) {
    		this.cifra = cifra;
    		return true;
    	} else {
    		return false;
    	}
	}
	public String getCifra() {
		return cifra;
	}

	public void setBPM(int bpm) {
		if(BPM_MIN <= bpm && bpm <= BPM_MAX){
			this.bpm = bpm;
			setPeriodo();
		} else {
			System.err.println("BPM " + bpm + " inválido!");
			System.exit(1);
		}
	}	
	public int getBPM() {
		return bpm;
	}

	
	private void setPeriodo() {
		//Converte a frequência de BPM (Batidas Por Segundo) para o período em milisegundos.
		periodo = SESSENTA_MIL_MILISEGUNDOS/bpm;
	}
	
	public int getPeriodo() {
		return periodo;
	}

	public void setModoExecucao(int modoExecucao) {
		if(modoExecucao == MODO_ACORDE | modoExecucao == MODO_ARPEJO){
			this.modoExecucao = modoExecucao;
        } else {
			System.err.println("Modo de execução " + modoExecucao + " inválido!");
			System.exit(1);        	
        }
	}
	
	public int getModoExecucao() {
		return modoExecucao;
	}

	public void setOitava(int oitava) {
		if(OITAVA_MENOR <= oitava && oitava<= OITAVA_MAIOR){
			this.oitava = oitava;
        } else {
			System.err.println("Oitava " + oitava + " inválida!");
			System.exit(1);    
        }		
	}

	public int getOitava() {
		return oitava;
	}
    
}
