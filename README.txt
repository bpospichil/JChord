###################### JChord v1.0 Leia-me ###################### 
# UFRGS - UNIVERSIDADE FEDERAL DO RIO GRANDE DO SUL 		#
# INSTITUTO DE INFORMÁTICA					#
# DEPARTAMENTO DE INFORMÁTICA APLICADA 				#
# INF01120 - Técnicas de Construção de Programas		#
# Turma C - Prof. Marcelo Pimenta				#
# Copyleft 2009 - Autores:					#
# Bruno Pospichil <bmpospichil@inf.ufrgs.br>			#
# Gabriel Stabel <gcstabel@inf.ufrgs.br >			#
# Luiza de Souza < luiza.souza@inf.ufrgs.br >			#
#################################################################

1. Objetivos/Recursos
		
	1.1. Objetivos
		JChord é um programa de fins didáticos para auxiliar no estudo de acordes.
	Dado um determinado acorde o JChord tem como funcionalidade emitir o som 
	caracteristico do mesmo e exibir quais notas que o compõem (modo Acorde) ou 
	exibir e emitir individualmente as notas que o compõe (modo Arpejo).
		A entrada de dados do JChord está descrita na seção 3.

	1.2. Executando
		Pode-se executar o JChord de duas formas:
		- Duplo clique, caso exista associação de arquivos jar para o java.exe;
		- java -jar jchord.jar
	
	
2. Requisitos de Sistema
	
	2.1. Hardware
			- Computador de 500MHz com 128MB de RAM ou superior.
	
	2.2. Software
		- Sistema operacional com Java Runtime Environment (JRE) 1.5 ou superior.

		
3. Interface de Dados
	
	3.1. Formato do acorde
		O acorde a ser inserido no JChord deve seguir um formato definido:
		Primeiramente deve ser inserida a nota fundamental [A-G] sucedida ou não de
sustenido ("#") ou bemol ("b"). Após, pode ser inserido ou não um modificador das
notas não fundamentais, como por exemplo o diminuto ("°").
		Exemplos:
		"C" - Dó maior
		"Dm" - Ré menor
		"G#4" - Sol sustenido com quarta
		"F°" - Fá diminuto
		Os símbolos aceitos são descritos na seção 5.	
	
	3.2. Métodos de inserção
	
		3.2.1. Entrada Drop Down
			O JChord permite que o usuário insira um acorde por meio da seleção das
informações que o compõem nos dois menus Drop Down, um para selecionar o acorde base
e outro para modificá-lo.
		
		3.2.2 Arquivo Texto
			Pode-se inserir uma lista de acordes por meio do botão "Abrir" aonde é
possível selecionar-se um arquivo texto (*.txt) com uma lista de acordes, um por linha,
seguindo o padrão descrito em 3.1.


4. Saída
	
	O JChord irá emitir os sons, nos modos descritos na seção um, bem como exibir no 
teclado quais as teclas necessárias para a formação do acorde desejado.


5. Limitações
	
	No JChord foi determinado um número finito de acordes reconhecidos, por isso foram
considerados somente os mais comuns, os símbolos aceitos pelo JChord são:
	- C, D, E, F, G, A e B como da nota fundamental;
	- # e b como modificador da nota fundamental (sustenido e bemol);
	- "m5-/7", "m4/7", "m7", "m9", "m", "7/9-", "7/9", "7+", "7", "4/9", "4/7", "4",
"9", "°" e "5-/7" como modificadores (ou adicionadores) das demais notas.


6. Bugs Possíveis
	
	Durante a definição do arpejo das cifras, foram encontradas informações contraditórias,
portanto, podem haver certas cifras que não estejam corretamente definidas, caso encontre
alguma cifra nessa condição, ou qualquer outro bug existente, favor comunicar aos autores
por email.


7. Suporte
	
	Questões de suporte podem ser tratadas com os autores.
	- Bruno Pospichil < bmpospichil@inf.ufrgs.br >
	- Gabriel Stabel <gcstabel@inf.ufrgs.br >
	- Luiza de Souza < luiza.souza@inf.ufrgs.br >
