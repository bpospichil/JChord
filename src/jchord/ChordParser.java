package jchord;
public class ChordParser {

	private String chord;
	
	public ChordParser() {
		doNothing();
	}
	
	public ChordParser(String chord){
		setChord(chord);
	}
	
	public boolean setChord(String chord) {
		if(chord != null && isValid(chord.trim())) {
			this.chord = chord.trim();
			return true;
		}
		else {
			return false;
		}
	}

	public String getChord() {
		return chord;
	}

	public boolean isValid(){
		int i = 0;
		if (chord == null || chord.length() <= i) {
			return false;  //empty or null is invalid!
		}
		if (chord.length() > i)
		{
			if ((chord.charAt(i) != 'C') && (chord.charAt(i) != 'D') &&
					(chord.charAt(i) != 'E') && (chord.charAt(i) != 'F') &&
					(chord.charAt(i) != 'G') && (chord.charAt(i) != 'A') &&
					(chord.charAt(i) != 'B')){
				return false;
			}
			else {
				i++;
			}
		}
		if (chord.length() > i)
		{
			if ((chord.charAt(i) == '#') || (chord.charAt(i) == '#')) {
				i++; // valid, go ahead
			}
			String modifier = chord.substring(i);
			if (validateModifier(modifier)) {
				return true;
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	// method used by setChord
	public static boolean isValid(String inputChord){
		int i = 0;
		if (inputChord == null || inputChord.length() <= i) {
			return false;  //empty or null is invalid!
		}
		if (inputChord.length() > i)
		{
			if ((inputChord.charAt(i) != 'C') && (inputChord.charAt(i) != 'D') &&
					(inputChord.charAt(i) != 'E') && (inputChord.charAt(i) != 'F') &&
					(inputChord.charAt(i) != 'G') && (inputChord.charAt(i) != 'A') &&
					(inputChord.charAt(i) != 'B')){
				return false;
			}
			else {
				i++;
			}
		}
		if (inputChord.length() > i)
		{
			if ((inputChord.charAt(i) == '#') || (inputChord.charAt(i) == '#')) {
				i++; // valid, go ahead
			}
			String modifier = inputChord.substring(i);
			if (validateModifier(modifier)) {
				return true;
			}
			else {
				return false;
			}
		}
		return true; 
	}
	
	private static boolean validateModifier(String modifier) {
		if (modifier.length()== 0 || modifier.contentEquals("m5-/7") ||
				modifier.contentEquals("m4/7") || modifier.contentEquals("m7") ||
				modifier.contentEquals("m9") || modifier.contentEquals("m") ||
				modifier.contentEquals("7/9-") || modifier.contentEquals("7/9") ||
				modifier.contentEquals("7+") || modifier.contentEquals("7") ||
				modifier.contentEquals("4/9") || modifier.contentEquals("4/7") ||
				modifier.contentEquals("4") || modifier.contentEquals("9") ||
				modifier.contentEquals("∞") || modifier.contentEquals("5-/7")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static void doNothing(){
		return;
	}

	public int[] getNotes(){
		int[] midiNotes = {-1,-1,-1,-1,-1};
		int i = 0;
		if (chord.length() > i) {
			//checking for notes at the first char
			if (chord.charAt(i) == 'C'){
				midiNotes[0] = 0;
				i++;	
			}
			else if (chord.charAt(i) == 'D'){
				midiNotes[0] = 2;
				i++;
			}
			else if (chord.charAt(i) == 'E'){
				midiNotes[0] = 4;
				i++;
			}
			else if (chord.charAt(i) == 'F'){
				midiNotes[0] = 5;
				i++;
			}
			else if (chord.charAt(i) == 'G'){
				midiNotes[0] = 7;
				i++;
			}
			else if (chord.charAt(i) == 'A'){
				midiNotes[0] = 9;
				i++;
			}
			else if (chord.charAt(i) == 'B'){
				midiNotes[0] = 11;
				i++;
			}
			else {; 
				return null;
			}
			//fill the three notes
			midiNotes[1] = midiNotes[0] + 4;
			midiNotes[2] = midiNotes[0] + 7;
		}
		
		if (chord.length() > i){
			
			//checking for notes at the second char
			if (chord.charAt(i)== '#'){
				midiNotes[0]++; midiNotes[1]++; midiNotes[2]++;
				i++;
			}
			else if (chord.charAt(i) == 'b'){
				midiNotes[0]--; midiNotes[1]--; midiNotes[2]--;
				i++;
			}
			
			String chordModifier = chord.substring(i);
			
			//checking the chord modifiers
			if (chordModifier.contentEquals("m5-/7")){
				midiNotes[1]--; midiNotes[2]--; midiNotes[3] = midiNotes[0] + 10;
			}
			else if (chordModifier.contentEquals("m4/7")){
				//  menor com quarta com s√©tima = primeira, ter√ßa menor, quarta, quinta e s√©tima?
				midiNotes[1]--; midiNotes[2] = midiNotes[0] + 5; 
				midiNotes[3] = midiNotes[0] + 7; midiNotes[4] = midiNotes[0] + 10;  
			} 
			else if (chordModifier.contentEquals("m7")){
				midiNotes[1]--; midiNotes[3] = midiNotes[0] + 10;
			}
			else if (chordModifier.contentEquals("m9")){
				midiNotes[1]--; midiNotes[3] = midiNotes[0] + 14;
			}
			else if (chordModifier.contentEquals("m")){
				midiNotes[1]--;
			}
			else if (chordModifier.contentEquals("7/9-")){
				midiNotes[3] = midiNotes[0] + 10; midiNotes[4] = midiNotes[0] + 13;
			}
			else if (chordModifier.contentEquals("7/9")){
				midiNotes[3] = midiNotes[0] + 10; midiNotes[4] = midiNotes[0] + 14;
			}
			else if (chordModifier.contentEquals("7+")){
				midiNotes[3] = midiNotes[0] + 11;
			}
			else if (chordModifier.contentEquals("7")){
				midiNotes[3] = midiNotes[0] + 10;
			}
			else if (chordModifier.contentEquals("4/9")){
				midiNotes[1]++; midiNotes[3] = midiNotes[0] + 14;
			}
			else if (chordModifier.contentEquals("4/7")){
				midiNotes[1]++; midiNotes[3] = midiNotes[0] + 10; 
			}
			else if (chordModifier.contentEquals("4")){
				midiNotes[1]++;	
			}
			else if (chordModifier.contentEquals("5-/7")){
				midiNotes[2]--; midiNotes[3] = midiNotes[0] + 10;
			}
			else if (chordModifier.contentEquals("9")){
				midiNotes[3] = midiNotes[0] + 14;
			}
			else if (chordModifier.contentEquals("∞")){
				midiNotes[1]--; midiNotes[2]--;
			}
			else if (chordModifier.length()==0){
				doNothing();
			}		
			else {
				new InformaErro("Cifra null!","ERRO:");
				return null;
			}
		}
		
		//Ajusta o tamanho da saida
		int size = 0;
		for(int j = 0; j < midiNotes.length; j++){
			if(midiNotes[j] != -1){
				size++;
			} else {
				break;
			}
		}
		int[] result = new int[size];
		for(int j = 0; j < midiNotes.length; j++){
			if(midiNotes[j] != -1){
				result[j] = midiNotes[j];
			}
		}
		return result;
	}
}
