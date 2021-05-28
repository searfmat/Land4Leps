package tests;
import org.junit.jupiter.api.Test;
import javafx.scene.image.Image;
import junit.framework.TestCase;
import pkgMain.Template;
import pkgMain.Plant;

public class TemplateTest extends TestCase {

	private Plant p1,p2,p3,p4,p5;
	@Test
	void testTemplate() throws Exception {
		
		pkgMain.Template template2 = new Template("hi", 1f, 2, p1, p2, p3, p4, p5);
		pkgMain.Template template3 = new Template("hi", 0f, 2222, p1, p2, p3, p4, p5);
		assertEquals(template2.getTemplateName(), template3.getTemplateName());
		
	}
	
	@Test
	void testTemplateName() throws Exception {
		pkgMain.Template template = new Template();
		template.setTemplateName("Red Robins");
		assertEquals("Red Robins", template.getTemplateName());
		template.setTemplateName("I am very tired, I have been up VERY late these past few nights.");
		assertEquals("I am very tired, I have been up VERY late these past few nights.", template.getTemplateName());
		template.setTemplateName("");
		assertEquals("", template.getTemplateName());
	}
	
	@Test
	void testGardenCost() throws Exception {
		pkgMain.Template template = new Template();
		template.setGardenCost(199.33F);
		assertEquals(199.33F,template.getGardenCost());
		template.setGardenCost(30F);
		assertEquals(30F,template.getGardenCost());
		template.setGardenCost(0F);
		assertEquals(0F,template.getGardenCost());
	}
	
	@Test
	void testNumLepsSupported() throws Exception {
		pkgMain.Template template = new Template();
		template.setNumLepsSupported(99);
		assertEquals(99,template.getNumLepsSupported());
		template.setNumLepsSupported(100);
		assertEquals(100,template.getNumLepsSupported());
		template.setNumLepsSupported(7);
		assertEquals(7,template.getNumLepsSupported());
		template.setNumLepsSupported(0);
		assertEquals(0,template.getNumLepsSupported());
	}
	
	@Test
	void testTemplateImg() throws Exception {
		pkgMain.Template template = new Template();
		assertEquals(template.getTemplateImg(), null);
		template.setTemplateImg(null);
		assertEquals(template.getTemplateImg(), null);
		Image testImg = new Image(getClass().getResourceAsStream("/testimg.png"));
		template.setTemplateImg(testImg);
		assertEquals(template.getTemplateImg(), testImg);
	}
	
	@Test
	void testTemplateColorPal() throws Exception {
		pkgMain.Template template = new Template();
		assertEquals(template.getTemplateColorPal(), null);
		template.setTemplateColorPal(null);
		assertEquals(template.getTemplateColorPal(), null);
		Image testImg = new Image(getClass().getResourceAsStream("/testimg.png"));
		template.setTemplateColorPal(testImg);
		assertEquals(template.getTemplateColorPal(), testImg);
	}
	
	@Test
	void testP1() throws Exception {
		pkgMain.Plant plant1 = new Plant();
		pkgMain.Template template = new Template();
		assertEquals(null, template.getP1());
		template.setP1(plant1);
		assertEquals(plant1,template.getP1());
		template.setP1(null);
		assertEquals(null,template.getP1());
	}
	
	@Test
	void testP2() throws Exception {
		pkgMain.Plant plant2 = new Plant();
		pkgMain.Template template = new Template();
		assertEquals(null, template.getP2());
		template.setP2(plant2);
		assertEquals(plant2,template.getP2());
		template.setP2(null);
		assertEquals(null,template.getP2());
	}
	
	@Test
	void testP3() throws Exception {
		pkgMain.Plant plant3 = new Plant();
		pkgMain.Template template = new Template();
		assertEquals(null, template.getP3());
		template.setP3(plant3);
		assertEquals(plant3,template.getP3());
		template.setP3(null);
		assertEquals(null,template.getP3());
	}
	
	@Test
	void testP4() throws Exception {
		pkgMain.Plant plant4 = new Plant();
		pkgMain.Template template = new Template();
		assertEquals(null, template.getP4());
		template.setP4(plant4);
		assertEquals(plant4,template.getP4());
		template.setP4(null);
		assertEquals(null,template.getP4());
	}
	
	@Test
	void testP5() throws Exception {
		pkgMain.Plant plant5 = new Plant();
		pkgMain.Template template = new Template();
		assertEquals(null, template.getP5());
		template.setP5(plant5);
		assertEquals(plant5,template.getP5());
		template.setP5(null);
		assertEquals(null,template.getP5());
	}
	
}
