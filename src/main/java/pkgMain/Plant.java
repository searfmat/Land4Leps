package pkgMain;

import javafx.scene.image.Image;

/**
 * Model for each individual plant. Handles its specific conditions as well as assigns an image for each one.
 * @author Matthew
 *
 */
public class Plant {
	/**
	 * Picture of plant
	 */
	private Image plantPic;
	/**
	 * X coordinate in the plot
	 */
	private double xcoord;
	/**
	 * Y coordinate in the plot
	 */
	private double ycoord;
	/**
	 * Common name of the plant
	 */
	private String commonName;
	/**
	 * Scientific name of the plant
	 */
	private String scientificName;
	/**
	 * Woody or herbaceous
	 */
	private String type;
	/**
	 * The type of soil a plant can thrive in
	 */
	private String soilType;
	/**
	 * Amount of sunlight required
	 */
	private String sun;
	/**
	 * Color of the plant. It it has flowers, color of the bloom
	 */
	private String color;
	/**
	 * Moisture level of the soil
	 */
	private String moisture;
	/**
	 * How far the roots spread out from the plant
	 */
	private float spread;
	/**
	 * Estimated cost of the plant
	 */
	private float price;
	/**
	 * How many are used
	 */
	private int count;
	/**
	 * Picture of the major lep species
	 */
	private Image lepPic;
	/**
	 * Lep species object
	 */
	private Lep lep;
	/**
	 * Number of plants in the plot
	 */
	public int numInPlot=0;
	
	
	public Plant() {
		this.xcoord = 0;
		this.ycoord = 0;
	}

	
	public void setX(double xcoord) throws Exception{
		this.xcoord = xcoord;
	}
	
	public void setY(double ycoord) throws Exception {
		this.ycoord = ycoord;
	}
	
	public void setCommonName(String commonName) throws Exception{
		this.commonName = commonName;
	}
	
	public void setScientificName(String scientificName) throws Exception{
		this.scientificName = scientificName;
	}
	
	public void setType(String type) throws Exception{
		this.type = type;
	}
	
	public void setSoilType(String soilType) throws Exception{
		this.soilType = soilType;
	}
	
	public void setSun(String sun) throws Exception{
		this.sun = sun;
	}
	
	public void setColor(String color) throws Exception{
		this.color = color;
	}
	
	public void setMoisture(String moisture) throws Exception {
		this.moisture = moisture;
	}
	
	public void setSpread(float spread) throws Exception{
		this.spread = spread;
	}
	
	public void setPrice(float price) throws Exception{
		this.price = price;
	}
	
	public void setPlantPic(Image plantPic) throws Exception{
		this.plantPic = plantPic;
	}

	public double getX() throws Exception{
		return this.xcoord;
	}
	
	public double getY() throws Exception{
		return this.ycoord;
	}

	public String getCommonName(){
		return this.commonName;
	}
	
	public int getNumInPlot() throws Exception{
		return numInPlot;
	}
	
	public void setNumInPlot(int numInPlot){
		this.numInPlot= numInPlot;
	}
	
	public String getScientificName() throws Exception{
		return this.scientificName;
	}
	
	public String getType() throws Exception{
		return this.type;
	}
	
	public String getSoilType() throws Exception{
		return this.soilType;
	}
	
	public String getSun() throws Exception{
		return this.sun;
	}
	
	public String getColor() throws Exception{
		return this.color;
	}
	
	public String getMoisture() throws Exception{
		return this.moisture;
	}
	
	public float getSpread() throws Exception {
		return this.spread;
	}
	
	public float getPrice() throws Exception{
		return this.price;
	}

	public Lep getLep() {
		return this.lep;
	}
	
	public void setLep(Lep lep) {
		this.lep=lep;
	}
	
	public Image getPlantPic() throws Exception{
		return plantPic;
	}
	
	public Image getLepPic() {
		return lepPic;
	}
	
	public void setLepPic(Image lepPic) {
		this.lepPic = lepPic;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
}



