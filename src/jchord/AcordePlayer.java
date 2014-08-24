/*
 * @(#)MidiSynth.java	1.15	99/12/03
 *
 * Copyright (c) 1999 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

package jchord;
import java.awt.*;
import java.awt.event.*;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.util.*;

public class AcordePlayer extends JPanel {
	
	static final long serialVersionUID = 0;

	private final int BASIC_PADDING = 0;
	private final int TICK_SPACING = 10;   
	
	private static final int PLAYING = 0, STOPPED = 1;    
	private static final int ON = 0, OFF = 1;
	private static final Color COR_TECLA_PRESSIONADA = new Color(222, 122, 55);
    
    private Thread soundPlayer;    
    private Acorde acorde = new Acorde(null);
    private Controle controle = new Controle();    
    public Piano piano = new Piano();
    
    public AcordePlayer() {       
    	//Inicializa interface
    	setLayout(new BorderLayout());

    	JPanel mainPanel = new JPanel();
    	
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(BASIC_PADDING, BASIC_PADDING, BASIC_PADDING, BASIC_PADDING));
        
        mainPanel.add(piano);
        mainPanel.add(controle);        
        this.add(mainPanel);                
    }

    class Piano extends JPanel {
		private static final long serialVersionUID = 0;
		private static final int TECLA_BRANCA = 0;
		private static final int TECLA_PRETA = 1;
		private static final int TECLA_LARGURA = 12;
        private static final int TECLA_ALTURA = 70;
        private static final int OITAVA_NUM_BRANCAS = 7;
        private static final int OITAVA_NUM_PRETAS = 5;
        private static final int OITAVA_NUM_TECLAS = OITAVA_NUM_BRANCAS + OITAVA_NUM_PRETAS;
        private static final int PIANO_NUM_OITAVAS = 7;
		private static final int PIANO_NUM_TECLAS = PIANO_NUM_OITAVAS * OITAVA_NUM_TECLAS;
        public static final int PRIMEIRA_NOTA = 24; //Dó na 3a oitava 
        public static final int ULTIMA_NOTA = 108; //Dó na 3a oitava
		private static final int TERCEIRA_TECLA_PRETA = 1;
		private static final int TECLAS_PRETAS_OFFSET = 10;

        private HashMap<Integer,Tecla> Teclas = new HashMap<Integer,Tecla>(PIANO_NUM_TECLAS);

        public Piano() {
        	
        	Vector<Integer> vectorNotas = new Vector<Integer>();
        	
        	int n = 123;
        	vectorNotas.add(n);
        	vectorNotas.add(321);
        	
        	int[] arrayNotas = new int[vectorNotas.size()];
        	for (int i = 0; i < vectorNotas.size(); i++){
        		arrayNotas[i] = vectorNotas.get(i);
        	}
        	
        	//Container do Piano
            setLayout(new BorderLayout());
            int largura = PIANO_NUM_OITAVAS * OITAVA_NUM_BRANCAS * TECLA_LARGURA + 1;
            int altura = TECLA_ALTURA + 1;
            setPreferredSize(new Dimension(largura, altura));

            //Teclas
            int brancasIDs[] = { 0, 2, 4, 5, 7, 9, 11 };
            int pretasIDs[] = { 1, 3, 6, 8, 10 };          
            
			int codigoOitava, numNota, brancaX, pretaX, oitavaX = 0;
			Tecla tecla;
			for (int oitava = 0; oitava < PIANO_NUM_OITAVAS; oitava++) {
				codigoOitava = PRIMEIRA_NOTA + (oitava * OITAVA_NUM_TECLAS);				
				brancaX = oitavaX;
				pretaX = oitavaX + TECLAS_PRETAS_OFFSET;

				//Teclas Brancas
				for (int i = 0; i < OITAVA_NUM_BRANCAS; i++) {
					numNota = codigoOitava + brancasIDs[i];
					tecla = new Tecla(brancaX, 0, TECLA_LARGURA, TECLA_ALTURA, numNota, TECLA_BRANCA);
					Teclas.put(numNota, tecla);        		
					brancaX += TECLA_LARGURA;				
				}
				oitavaX = brancaX;
				
				//Teclas Pretas				
				for (int i = 0; i < OITAVA_NUM_PRETAS; i++) {
					numNota = codigoOitava + pretasIDs[i];
					tecla = new Tecla(pretaX, 0, TECLA_LARGURA/2, TECLA_ALTURA/2, numNota, TECLA_PRETA);
					Teclas.put(numNota, tecla);       		
					pretaX += TECLA_LARGURA;
					if(i == TERCEIRA_TECLA_PRETA){
						pretaX += TECLA_LARGURA;
					}
				}			
			}
        }

        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Dimension d = getSize();

            //1o: Fundo Branco
            g2.setBackground(getBackground());
            g2.clearRect(0, 0, d.width, d.height);
            g2.setColor(Color.white);
            g2.fillRect(0, 0, PIANO_NUM_OITAVAS * OITAVA_NUM_BRANCAS * TECLA_LARGURA, TECLA_ALTURA);

            //2o: Teclas Brancas
            Tecla tecla;
            Set<Integer> keys = Teclas.keySet();
            for (int key : keys) {
                tecla = Teclas.get(key);
                if(tecla.tipo == TECLA_BRANCA){
                    if (tecla.isTeclaOn()) {
                        g2.setColor(COR_TECLA_PRESSIONADA);
                        g2.fill(tecla);
                    }
                    g2.setColor(Color.black);
                    g2.draw(tecla);                    
                }
            }
            
            //3o: Teclas Pretas
            for (int key : keys) {
                tecla = Teclas.get(key);
                if(tecla.tipo == TECLA_PRETA){
                    if (tecla.isTeclaOn()) {
                        g2.setColor(COR_TECLA_PRESSIONADA);
                        g2.fill(tecla);
                        g2.setColor(Color.black);
                        g2.draw(tecla);
                    } else {
                        g2.setColor(Color.black);
                        g2.fill(tecla);
                    }
                }
            }
        }
    
        class Tecla extends Rectangle {
        	static final long serialVersionUID = 0;
        	
            public int estado = OFF;
            public int numNota;
            public int tipo;
            
            public Tecla(int x, int y, int largura, int altura, int numNota, int tipo) {
                super(x, y, largura, altura);
                this.numNota = numNota;
                this.tipo = tipo;
            }
            public boolean isTeclaOn() {
                return estado == ON;
            }
            public void setEstado(int estado) {
                this.estado = estado;
                repaint();
            }
        }
        
        public synchronized void teclaOn(int numNota){
        	Tecla tecla = Teclas.get(numNota);
        	tecla.setEstado(ON);
        }
        public synchronized void teclaOff(int numNota){
        	Tecla tecla = Teclas.get(numNota);
        	tecla.setEstado(OFF);        
        }
        public synchronized void allTeclasOff(){
            Tecla tecla;
            Set<Integer> keys = Teclas.keySet();
            for (int key : keys) {
                tecla = Teclas.get(key);
        		tecla.estado = OFF;        		
        	}
        	repaint();
        	controle.setStatus(STOPPED);
        }
    }

    public void playSound(){
    	boolean valida;

    	String cifra = JChord.getCifra();
    	
    	valida = acorde.setCifra(cifra);
    	if(valida){    		
        	int[] notas = acorde.getNumNotas();
        	for(int i = 0; i < notas.length; i++){
        		if(notas[i] > Piano.ULTIMA_NOTA | notas[i] < Piano.PRIMEIRA_NOTA){
        			new InformaErro("Acorde fora do alcance do Piano!","ERRO:");
        			valida = false;
        			break;
        		}
        		
        	}    	
	    	if(valida){
		    	acorde.setCifra(cifra);
		    	
		    	controle.setStatus(PLAYING);
		    	
		    	boolean loop = controle.getPlayInLoop();
		    	soundPlayer = new Thread(new SoundPlayer(acorde, loop));
		    	soundPlayer.start();
	    	}
    	}
    }
        
    public void stopSound(){
    	if(soundPlayer != null){    		
    		soundPlayer.interrupt();
    	}
    	controle.setStatus(STOPPED);
    }

    private class Controle extends JPanel implements ActionListener, ChangeListener {

		private final Color PLAY_BORDER_COLOR = new Color(150,150,150);

		static final long serialVersionUID = 0;
    	
		private int status = STOPPED;
    	private JButton play_stop;    	
    	private JComboBox modoExecucao, oitava;
    	private JSlider bpm;
    	private TitledBorder bpmBorder;
    	private JCheckBox playInLoop;
    	protected ImageIcon playIcon, stopIcon;
    	
        public Controle() {
        	this.setPreferredSize(new Dimension(100,100));
        	
        	//Play / Stop        	
            playIcon = createImageIcon("images/play.png");
            stopIcon = createImageIcon("images/stop.png");
        	play_stop = new JButton(null, playIcon);
        	play_stop.setBorder(new LineBorder(PLAY_BORDER_COLOR,1,true));
        	play_stop.setBackground(null);
        	play_stop.setPreferredSize(new Dimension(80, 35));
        	play_stop.addActionListener(this);
        	this.add(play_stop);

            //ComboBox do Modo de Execução: Acorde ou Arpejo.            
            JLabel labelModo = new JLabel("Modo:");
            this.add(labelModo);
            
        	modoExecucao = new JComboBox(Acorde.MODOS_EXECUCAO);
            modoExecucao.setSelectedIndex(0);
            modoExecucao.addActionListener(this);
            this.add(modoExecucao);
            
            //Oitava base
            JLabel labelOitava = new JLabel("Oitava:");
            this.add(labelOitava);
            
        	oitava = new JComboBox(Acorde.OITAVAS);
        	oitava.setSelectedIndex(Acorde.OITAVA_INIT - Acorde.OITAVA_MENOR);
        	oitava.addActionListener(this);
            this.add(oitava);
            
            //Slider do BPMs (frequência em Batidas Por Minuto).
            bpm = new JSlider(JSlider.HORIZONTAL, Acorde.BPM_MIN, Acorde.BPM_MAX, Acorde.BPM_INIT);
            bpm.setSnapToTicks(true);            
            bpm.setMajorTickSpacing(TICK_SPACING);
            bpm.setMinorTickSpacing(TICK_SPACING);
            bpm.setPaintTicks(true);
            bpmBorder = new TitledBorder(new EtchedBorder());
            setBPMBorderTitle();
            bpm.setBorder(bpmBorder);            
            bpm.addChangeListener(this);
            this.add(bpm);        
            
            //CheckBox do Loop            
            playInLoop = new JCheckBox("Loop");
            playInLoop.setMnemonic(KeyEvent.VK_G); 
            playInLoop.setSelected(true);
            this.add(playInLoop);            
        }

        protected ImageIcon createImageIcon(String path) {
            java.net.URL imgURL = AcordePlayer.class.getResource(path);
            if (imgURL != null) {
                return new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find file: " + path);
                System.exit(1);
                return null;
            }
        }

        public void stateChanged(ChangeEvent event) {
        	acorde.setBPM(bpm.getValue());
       		setBPMBorderTitle();
        }

		private void setBPMBorderTitle() {
			bpmBorder.setTitle("BPMs = " + bpm.getValue());
		}
        
		public void actionPerformed(ActionEvent event) {
			Object source = event.getSource();
			
			if (source == play_stop){
				switch(status){
					case PLAYING:
						stopSound();
						break;
					case STOPPED:
						playSound();
						break;					
				}
			
			} else if (source == modoExecucao){
				acorde.setModoExecucao(modoExecucao.getSelectedIndex());
			
			} else if (source == oitava){
				int o = oitava.getSelectedIndex() + Acorde.OITAVA_MENOR;
				acorde.setOitava(o);
			
			} else {
				System.err.println("Object "+ source + " inválido!");
			}
        }

		public boolean getPlayInLoop() {
			return playInLoop.isSelected();
		}
		
		public void setStatus(int status){
			this.status = status;
			switch(status){
				case PLAYING:
					play_stop.setIcon(stopIcon);
			    	modoExecucao.setEnabled(false);
			    	oitava.setEnabled(false);
			    	bpm.setEnabled(false);
			    	playInLoop.setEnabled(false);				
					break;
					
				case STOPPED:
					play_stop.setIcon(playIcon);
			    	modoExecucao.setEnabled(true);
			    	oitava.setEnabled(true);
			    	bpm.setEnabled(true);
			    	playInLoop.setEnabled(true);				
					break;
			}
		}
    }
    
    private class SoundPlayer implements Runnable {
    	
    	private static final int VELOCIDADE = 60;
    	private static final String MIDI_UNAVAIABLE = "Midi Synthesizer não encontrado!";
    	
        private Synthesizer synthesizer;
        private Acorde acorde;
        private boolean playInLoop;
        
        public SoundPlayer(Acorde acorde, boolean playInLoop){
        	this.acorde = acorde;
        	this.playInLoop = playInLoop;        	
        }

        public void run() {        	
        	try {
    	    	synthesizer = MidiSystem.getSynthesizer();
    			synthesizer.open();			
    	        MidiChannel[] channels = synthesizer.getChannels();
    	        
    	        int periodo = acorde.getPeriodo();
    	        
    	        int numNota;	        
    	        int[][] Teclas = acorde.getNotasNoTempo();
    	        boolean first = true;
    	        while(first | playInLoop){
    	        	first = false;
    		        for(int instante = 0; instante < Teclas.length; instante++){
    	
    		        	//Liga/Ativa todas as notas em um dado instante.
    		        	for(int indice = 0; indice < Teclas[instante].length; indice++){
    		        		numNota = Teclas[instante][indice];
    		        		piano.teclaOn(numNota);
    		        		channels[indice].noteOn(numNota, VELOCIDADE);
    		        	}
    		        	    		        	
    		        	Thread.sleep(periodo);
    		        	
    		        	//Desliga todas as notas em um dado instante.
    		        	for(int indice = 0; indice < Teclas[instante].length; indice++){
    		        		numNota = Teclas[instante][indice];
    		        		piano.teclaOff(numNota);
    		        		channels[indice].noteOff(numNota);
    		        	}
    		        }
    		        Thread.sleep(periodo);
    	        }
    			synthesizer.close();	
    		
    	    } catch (InterruptedException e) {
    	    	//Stop
    	    	piano.allTeclasOff();
    	    	synthesizer.close();
    	    	
    	    } catch (MidiUnavailableException e) {
    	    	System.err.println(MIDI_UNAVAIABLE);
    	    }	     
    	    piano.allTeclasOff();    	    
        }
    }
} 
