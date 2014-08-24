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
import javax.swing.*;

public class JChord {	
    
    //Cifra compartilhada entre a Lista de Reprodu��o e o Player.   
    static private String cifra;   
    static public String getCifra() {
    	return JChord.cifra;
    }    
    static public void setCifra(String cifra) {
    	JChord.cifra = cifra;
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("JChord - Arpejador");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel painel = new JPanel();
        painel.setLayout( null );
        AcordePlayer player = new AcordePlayer();
        player.setBounds( 270, 10, 700, 200 );
	    painel.add(player);
	    
        PlayList playList = new PlayList();
        playList.setBounds( 0, 0 , 700, 400);
	    painel.add(playList);
	    
        frame.setContentPane(painel);
        frame.setSize(950, 420);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
