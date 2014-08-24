package jchord;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;




public class ChordParserTest {

	@Before
	public void setUp() throws Exception {
		System.out.print("Starting test: ");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test ended.");
	}
	
	
	@Test
	public void testSetChord() {
		System.out.println("testSetChord ...");
		ChordParser cp = new ChordParser();
		assertEquals(cp.setChord("aaasadasd"), false);
		assertEquals(cp.setChord("   C    "), true);
		assertEquals(cp.setChord("   C#    "), true);
		assertEquals(cp.setChord("D4/7"), true);
		assertEquals(cp.setChord(""), false);
		assertEquals(cp.setChord(null), false);
		assertEquals(cp.setChord("C4#7"), false);
	}

	@Test
	public void testGetChord() {
		System.out.println("testGetChord ...");
		ChordParser cp = new ChordParser();
		
		cp.setChord("C#");
		assertEquals(cp.getChord() == "C#", true);
		
		cp.setChord("D#m4/7");
		assertEquals(cp.getChord() == "D#m4/7", true);
		
		cp.setChord("G#m5-/7");
		assertEquals(cp.getChord() == "G#m5-/7", true);
		
		cp.setChord("F#m5-/7");
		assertEquals(cp.getChord() == "C#m5-/7", false);
	}

	@Test
	public void testIsValid() {
		System.out.println("testIsValid ...");
		ChordParser cp = new ChordParser();
		
		cp.setChord("C4");
		assertEquals(cp.isValid(), true);
	}

	@Test
	public void testGetNotes() {
		System.out.println("testGetNotes ...");
		ChordParser cp = new ChordParser("G");
		assertEquals(Arrays.equals(cp.getNotes(), new int[]{7,11,14}), true);
		
		cp = new ChordParser("Gm");
		assertEquals(Arrays.equals(cp.getNotes(), new int[]{7,10,14}), true);
		
		cp = new ChordParser("G#m");
		assertEquals(Arrays.equals(cp.getNotes(), new int[]{8,11,15}), true);
		
		cp = new ChordParser("D4");
		assertEquals(Arrays.equals(cp.getNotes(), new int[]{2,7,9}), true);
		
		cp = new ChordParser("C#5-/7");
		assertEquals(Arrays.equals(cp.getNotes(), new int[]{1,5,7,11}), true);
		
		cp = new ChordParser("F7/9");
		assertEquals(Arrays.equals(cp.getNotes(), new int[]{5,9,12,15,19}), true);
		
		cp = new ChordParser("Bm");
		assertEquals(Arrays.equals(cp.getNotes(), new int[]{11,14,18}), true);
		
		cp = new ChordParser("A°");
		assertEquals(Arrays.equals(cp.getNotes(), new int[]{9,12,15}), true);
		
	}
}
