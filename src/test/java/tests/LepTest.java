package tests;
import org.junit.jupiter.api.Test;
import javafx.scene.image.Image;
import junit.framework.TestCase;
import pkgMain.Lep;



class LepTest extends TestCase {
	
	
	@Test
	void testConstruct() {
		Lep test = new Lep("test",3);
		Lep test1 = new Lep();
		
		assertEquals(test.getSpecies(), "test");
		assertEquals(test.getNumLeps(), 3);
		assertEquals(test1.getSpecies(), null);
		assertEquals(test1.getNumLeps(), 0);
		
		
	}
    
	
	@Test
	void testNumLeps() {
		Lep test = new Lep();
		test.setNumLeps(132);
		assertEquals(test.getNumLeps(), 132);
		test.setNumLeps(0);
		assertEquals(test.getNumLeps(), 0);
		test.setNumLeps(999999999);
		assertEquals(test.getNumLeps(), 999999999);
	}
	
	@Test
	void testSpecies() {
		Lep test = new Lep();
		test.setSpecies("test test");
		assertEquals(test.getSpecies(), "test test");
		test.setSpecies("");
		assertEquals(test.getSpecies(), "");
	}
	
	@Test
	void testCalcTotalOfSpecies() {
		Lep test = new Lep();
		test.setNumLeps(132);
		assertEquals(test.calcTotalOfSpecies(), 132);
	}
	
	

}