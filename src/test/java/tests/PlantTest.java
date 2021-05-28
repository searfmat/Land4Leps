package tests;
import org.junit.jupiter.api.Test;
import javafx.scene.image.Image;
import junit.framework.TestCase;
import pkgMain.Lep;
import pkgMain.Plant;


class PlantTest extends TestCase {
	
	@Test
	void testSoilType() throws Exception{
		pkgMain.Plant plant = new Plant();
		assertEquals(null,plant.getSoilType());
		plant.setSoilType("peaty");
		assertEquals("peaty",plant.getSoilType());
		plant.setSoilType("sandy");
		assertEquals("sandy",plant.getSoilType());
		plant.setSoilType("");
		assertEquals("",plant.getSoilType());
	}
	
	@Test
	void testColor() throws Exception {
		pkgMain.Plant plant = new Plant();
		assertEquals(null,plant.getColor());
		plant.setColor("green");
		assertEquals("green",plant.getColor());
		plant.setColor("red red");
		assertEquals("red red",plant.getColor());
		plant.setColor("");
		assertEquals("",plant.getColor());
	}
	
	@Test
	void testSetSpread() throws Exception {
		pkgMain.Plant plant = new Plant();
		assertEquals(0f,plant.getSpread());
		plant.setSpread(3f);
		assertEquals(3f,plant.getSpread());
		plant.setSpread(9999999f);
		assertEquals(9999999f,plant.getSpread());
	}
	
	@Test
	void testPrice() throws Exception {
		pkgMain.Plant plant = new Plant();
		assertEquals(0f, plant.getPrice());
		plant.setPrice(135f);
		assertEquals(135f, plant.getPrice());
		plant.setPrice(14532985045f);
		assertEquals(14532985045f, plant.getPrice());
	}
	
	@Test
	void testNumInPlot() throws Exception {
		pkgMain.Plant plant = new Plant();
		assertEquals(0,plant.getNumInPlot());
		plant.setNumInPlot(243214);
		assertEquals(243214,plant.getNumInPlot());
		plant.setNumInPlot(0);
		assertEquals(0,plant.getNumInPlot());
	}
	
	@Test
	void testPlantPic() throws Exception {
		pkgMain.Plant plant = new Plant();
		assertEquals(plant.getPlantPic(), null);
		plant.setPlantPic(null);
		assertEquals(plant.getPlantPic(), null);
		Image testImg = new Image(getClass().getResourceAsStream("/testimg.png"));
		plant.setPlantPic(testImg);
		assertEquals(plant.getPlantPic(), testImg);
	}
	
	@Test
	void testX() throws Exception {
		pkgMain.Plant plant = new Plant();
		assertEquals(plant.getX(),0d);
		plant.setX(43289479124731d);
		assertEquals(plant.getX(),43289479124731d);
		plant.setX(000000000d);
		assertEquals(plant.getX(),0d);
	}
	
	@Test
	void testY() throws Exception {
		pkgMain.Plant plant = new Plant();
		assertEquals(plant.getY(),0d);
		plant.setY(43289479124731d);
		assertEquals(plant.getY(),43289479124731d);
		plant.setY(000000000d);
		assertEquals(plant.getY(),0d);
	}
	
	@Test
	void testCommonName() throws Exception {
		pkgMain.Plant plant = new Plant();
		plant.setCommonName("cname test");
		assertEquals("cname test",plant.getCommonName());
		plant.setCommonName("c");
		assertEquals("c",plant.getCommonName());
		plant.setCommonName("");
		assertEquals("",plant.getCommonName());
	}
	
	@Test
	void testScientificName() throws Exception {
		pkgMain.Plant plant = new Plant();
		plant.setScientificName("Testinous test test test test");
		assertEquals("Testinous test test test test",plant.getScientificName());
		plant.setScientificName("t");
		assertEquals("t",plant.getScientificName());
		plant.setScientificName("");
		assertEquals("",plant.getScientificName());
	}
	
	@Test
	void testType() throws Exception {
		pkgMain.Plant plant = new Plant();
		plant.setType("w");
		assertEquals("w",plant.getType());
		plant.setType("h");
		assertEquals("h",plant.getType());
	}
	
	@Test
	void testMoisture() throws Exception {
		pkgMain.Plant plant = new Plant();
		plant.setMoisture("wet");
		assertEquals("wet",plant.getMoisture());
		plant.setMoisture("dry");
		assertEquals("dry",plant.getMoisture());
		plant.setMoisture("moist");
		assertEquals("moist",plant.getMoisture());
	}
	
	@Test
	void testSun() throws Exception {
		pkgMain.Plant plant = new Plant();
		plant.setSun("full sun");
		assertEquals("full sun",plant.getSun());
		plant.setSun("part sun");
		assertEquals("part sun",plant.getSun());
		plant.setSun("");
		assertEquals("",plant.getSun());
	}
	
	@Test
	void testLep() throws Exception {
		pkgMain.Plant plant = new Plant();
		pkgMain.Lep lep = new Lep();
		assertEquals(null, plant.getLep());
		plant.setLep(lep);
		assertEquals(lep, plant.getLep());
		plant.setLep(null);
		assertEquals(null, plant.getLep());
	}
	
	@Test
	void testCount() throws Exception {
		pkgMain.Plant plant = new Plant();
		assertEquals(0, plant.getCount());
		plant.setCount(0);
		assertEquals(0, plant.getCount());
		plant.setCount(15);
		assertEquals(15, plant.getCount());
		plant.setCount(999999999);
		assertEquals(999999999, plant.getCount());
	}
	
	@Test
	void testLepPic() throws Exception {
		pkgMain.Plant plant = new Plant();
		assertEquals(plant.getLepPic(), null);
		plant.setLepPic(null);
		assertEquals(plant.getLepPic(), null);
		Image testImg = new Image(getClass().getResourceAsStream("/testimg.png"));
		plant.setLepPic(testImg);
		assertEquals(plant.getLepPic(), testImg);
	}

}