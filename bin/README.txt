###################### JChord v1.0 Leia-me ###################### 
# Copyleft 2009 - 						#
# Bruno Pospichil <bmpospichil@inf.ufrgs.br>			#
# Gabriel Stabel < >						#
# Luiza Souza < >						#
#################################################################

1. Objetivos/Recursos
	JChord � um programa de fins did�ticos para auxiliar no estudo de acordes.
Dado um determinado acorde o JChord tem como funcionalidade emitir o som 
caracteristico do mesmo e exibir quais notas que o comp�em (modo Acorde) ou 
exibir e emitir individualmente as notas que o comp�e (modo Arpejo).
	A entrada de dados do JChord est� descrita na se��o 3.

	
2. Requisitos de Sistema
	
	2.1. Hardware
			- Computador de 500MHz com 128MB de RAM ou superior.
	
	2.2. Software
		- Sistema operacional com Java Runtime Environment (JRE) 1.5 ou superior.

		
3. Interface de Dados
	
	3.1. Formato do acorde
		O acorde a ser inserido no JChord deve seguir um formato definido:
		Primeiramente deve ser inserida a nota fundamental [A-G] sucedida ou n�o de
sustenido ("#") ou bemol ("b"). Ap�s, pode ser inserido ou n�o um modificador das
notas n�o fundamentais, como por exemplo o diminuto ("�").
		Exemplos:
		"C" - D� maior
		"Dm" - R� menor
		"G#4" - Sol sustenido com quarta
		"F�" - F� diminuto
		Os s�mbolos aceitos s�o descritos na se��o 5.	
	
	3.2. M�todos de inser��o
	
		3.2.1. Entrada Drop Down
			O JChord permite que o usu�rio insira um acorde por meio da sele��o das
informa��es que o comp�em nos dois menus Drop Down, um para selecionar o acorde base
e outro para modific�-lo.
		
		3.2.2 Arquivo Texto
			Pode-se inserir uma lista de acordes por meio do bot�o "Abrir" aonde �
poss�vel selecionar-se um arquivo texto (*.txt) com uma lista de acordes, um por linha,
seguindo o padr�o descrito em 3.1.


4. Sa�da
	
	O JChord ir� emitir os sons, nos modos descritos na se��o um, bem como exibir no 
teclado quais as teclas necess�rias para a forma��o do acorde desejado.


5. Limita��es
	
	No JChord foi determinado um n�mero finito de acordes reconhecidos, por isso foram
considerados somente os mais comuns, os s�mbolos aceitos pelo JChord s�o:
	- C, D, E, F, G, A e B como da nota fundamental;
	- # e b como modificador da nota fundamental (sustenido e bemol);
	- "m5-/7", "m4/7", "m7", "m9", "m", "7/9-", "7/9", "7+", "7", "4/9", "4/7", "4",
"9", "�" e "5-/7" como modificadores (ou adicionadores) das demais notas.


6. Suporte
	
	Quest�es de suporte podem ser tratadas com os autores.