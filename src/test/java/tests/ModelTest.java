package tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import junit.framework.TestCase;
import pkgMain.Model;
import pkgMain.Plant;
import pkgMain.Template;

class ModelTest extends TestCase{
	private List<Plant> plantList;
	private List<Plant> expectedList;
	private List<Template> templateList;
	private List<Template> expectedTempList;
	
	@Test
	void testParse() throws Exception {
		//IMPORTANT: To test parse(), images must be commented out. JUnit doesn't access the main files
		pkgMain.Model m = new Model();
		m.parse();
		plantList = m.plantList;
		expectedList = new ArrayList<Plant>();
		m.parse();
		expectedList = m.plantList;
		
		assertEquals(expectedList.size(),plantList.size());
		
		Iterator<Plant> it = plantList.iterator();
		Iterator<Plant> it1 = expectedList.iterator();
		
		while(it.hasNext() && it1.hasNext()) {
			Plant plant = it.next();
			Plant expect = it1.next();
			assertEquals(expect.getScientificName(),plant.getScientificName());
			assertEquals(expect.getCommonName(),plant.getCommonName());
			assertEquals(expect.getType(),plant.getType());
			assertEquals(expect.getSoilType(),plant.getSoilType());
			assertEquals(expect.getSun(),plant.getSun());
			assertEquals(expect.getColor(),plant.getColor());
			assertEquals(expect.getMoisture(),plant.getMoisture());
			assertEquals(expect.getSpread(),plant.getSpread());
			assertEquals(expect.getPrice(),plant.getPrice());
			assertEquals(expect.getNumInPlot(),plant.getNumInPlot());
		}
	}
	
	public void testBasket() throws Exception {
		pkgMain.Model m = new Model();
		pkgMain.Model m2 = new Model();
		
		assertEquals(m2.getBasket(), m.getBasket());
		Plant p = new Plant();
		m.addToBasket(p);
		m2.addToBasket(p);
		assertEquals(m2.getBasket(), m.getBasket());
	}

	
	@Test
	void testSortPlants() throws Exception{
		pkgMain.Model m = new Model();
		ArrayList<Plant> list = new ArrayList<Plant>();
		Plant a = new Plant();
		a.setCommonName("a");
		Plant b = new Plant();
		b.setCommonName("a");
		list.add(a);
		list.add(b);
		m.setPlantsInGarden(list);
		Model.sortPlants();
		list = Model.getUniquePlants();
		
		assertTrue(list.contains(a));
		assertFalse(list.contains(b)); //Plants with same name should not be added more than once
		
		Plant c = new Plant();
		c.setCommonName("b");
		list.add(c);
		m.setPlantsInGarden(list);
		Model.sortPlants();
		list = Model.getUniquePlants();
		assertTrue(list.contains(c)); //Plant with different name should be added.
		
	}
	
	@Test
	void testTopSpecies() throws Exception{
		pkgMain.Model m = new Model();
		ArrayList<Plant> list = new ArrayList<Plant>();
		Plant a = new Plant();
		a.getLep().setNumLeps(2);
		Plant b = new Plant();
		b.getLep().setNumLeps(3);
		Plant c = new Plant();
		c.getLep().setNumLeps(4);
		list.add(a);
		list.add(b);
		list.add(c);
		m.setUniquePlants(list);
		m.findTopSpecies();
		list = m.getTopThree();
		
		assertTrue(list.get(0).getLep().getNumLeps() >= list.get(1).getLep().getNumLeps());
		assertTrue(list.get(1).getLep().getNumLeps() >= list.get(2).getLep().getNumLeps());
		assertEquals(list.get(0).getLep().getNumLeps(), c.getLep().getNumLeps());
		assertEquals(list.get(1).getLep().getNumLeps(), b.getLep().getNumLeps());
		assertEquals(list.get(2).getLep().getNumLeps(), a.getLep().getNumLeps());
	}


	void testParseTemplates() throws Exception {
		pkgMain.Model m = new Model();
		templateList = m.templateList;
		expectedTempList = new ArrayList<Template>();
		m.parseTemplates();
		expectedTempList = m.templateList;
		
		assertEquals(expectedTempList.size(),templateList.size());
		
		Iterator<Template> it = templateList.iterator();
		Iterator<Template> it2 = expectedTempList.iterator();
		while (it.hasNext() && it2.hasNext()) {
			Template template = it.next();
			Template expect = it2.next();
			assertEquals(expect.getTemplateName(),template.getTemplateName());
			assertEquals(expect.getGardenCost(),template.getGardenCost());
			assertEquals(expect.getNumLepsSupported(),template.getNumLepsSupported());
		}
	}
	
	@Test
	void testBudget() throws Exception {
		pkgMain.Model m = new Model();
		m.setBudget(156);
		assertEquals(156, m.getBudget());
	}
	
	@Test
	void testSoil() throws Exception {
		pkgMain.Model m = new Model();
		m.setSoil("Peaty");
		assertEquals("Peaty",m.getSoil());
	}

	@Test
	void testSun() throws Exception {
		pkgMain.Model m = new Model();
		m.setSun("Full Sun");
		assertEquals("Full Sun",m.getSun());
	}


	@Test
	void testMoisture() throws Exception {
		pkgMain.Model m = new Model();
		m.setMoisture("Wet");
		assertEquals("Wet",m.getMoisture());
	}

	@Test
	void testTemplateName() throws Exception {
		pkgMain.Model m = new Model();
		m.setTemplateName("Garden1");
		assertEquals("Garden1",m.getTemplateName());
	}

	@Test
	void testNumLepsSupported() throws Exception {
		pkgMain.Model m = new Model();
		m.setNumLepsSupported(124324);
		assertEquals(124324,m.getNumLepsSupported());
	}

	@Test
	void testGardenCost() throws Exception {
		pkgMain.Model m = new Model();
		m.setGardenCost(123f);
		assertEquals(123f,m.getGardenCost());
	}

	

}
