package pkgMain;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.image.Image;

/** Mostly responsible to accessing and sorting our data
 * 
 * @author Matthew and Allie
 *
 */

public class Model {
	
	public ArrayList<Plant> plantList = new ArrayList<Plant>();
	ArrayList<Plant> nnPlantList = new ArrayList<Plant>();
	ArrayList<Plant> basket = new ArrayList<Plant>();
	private Scanner scanner = null;
	private BufferedReader reader = null;
	private String line = null;
	private int index = 0;
	private int budget;
	private String soil;
	private String sun;
	private String moisture;
	private float gardenCost;
	private int numLepsSupported;
	
	private static ArrayList<Plant> plantsInGarden = new ArrayList<Plant>();
	private static ArrayList<Plant> uniquePlants= new ArrayList<Plant>();
	private static ArrayList<Plant> topThree= new ArrayList<Plant>();
	
	public Model() throws Exception{}

	/**
	 * Returns the basket. (ArrayList of plants added)
	 * @return beasket
	 */
	public ArrayList<Plant> getBasket() {
		return basket;
	}
	/**
	 * Adds specific plants to the basket.
	 * @param p Plant to be added
	 */
	public void addToBasket(Plant p) {
		basket.add(p);
	}
	/**
	 * Removes a specific plant from the basket
	 * @param p Plant to be removed from the basket
	 */
	public void removeFromBasket(Plant p) {
		basket.remove(p);
	}
	/**
	 * Parses through plant_data an assigns everything.
	 * @throws Exception
	 */
	public void parse() throws Exception {
			
			try {
				reader = new BufferedReader(new FileReader(
							"src/main/resources/data/plant_data.csv"));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
 	
        	try {
				while ((line = reader.readLine()) != null) {

					Lep l = new Lep();
					Plant p = new Plant();
					scanner = new Scanner(line);
					scanner.useDelimiter(",");
					while (scanner.hasNext()) {
						String data = scanner.next();
						
						switch(index) {
							case 0:
								p.setScientificName(data);
								break;
							case 1:
								p.setCommonName(data);
								break;
							case 2:
								p.setType(data);
								break;
							case 3:
								p.setSoilType(data);
								break;
							case 4:
								p.setSun(data);
								break;
							case 5:
								p.setColor(data);
								break;
							case 6:
								p.setMoisture(data);
								break;
							case 7:
								p.setSpread(Float.parseFloat(data));
								break;
							case 8:
								p.setPrice(Float.parseFloat(data));
								break;
							case 9:
								l.setNumLeps(Integer.parseInt(data));
								break;
							case 10:
								l.setSpecies(data);
								break;
							default:
								System.out.print("Out of bounds");
								break;		
						}
						index++;
					}
					System.out.println(p.getScientificName());
					p.setLep(l);
					p.setPlantPic(new Image(getClass().getResourceAsStream("/img/plants/" + p.getCommonName() + ".png")));
					p.setLepPic(new Image(getClass().getResourceAsStream("/img/leps/" + p.getLep().getSpecies() + ".png")));
					index = 0;
					plantList.add(p);
					
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	
        	scanner.close();
        	
		}
	

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public String getSoil() {
		return soil;
	}

	public void setSoil(String soil) {
		this.soil = soil;
	}

	public String getSun() {
		return sun;
	}

	public void setSun(String sun) {
		this.sun = sun;
	}

	public String getMoisture() {
		return moisture;
	}

	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}
	
	
	public int getNumLepsSupported() {
		return this.numLepsSupported;
	}
	
	public void setNumLepsSupported(int numLepsSupported) throws Exception {
		this.numLepsSupported = numLepsSupported;
	}
	
	public float getGardenCost() throws Exception {
		return this.gardenCost;
	}
	
	public void setGardenCost(float gardenCost) throws Exception {
		this.gardenCost = gardenCost;
	}


	public static ArrayList<Plant> getPlantsInGarden() {
		return plantsInGarden;
	}
	public static ArrayList<Plant> getUniquePlants() {
		return uniquePlants;
	}
	public static ArrayList<Plant> getTopThree() {
		return topThree;
	}
	public void setPlantsInGarden(ArrayList<Plant> plantsInGarden) {
		this.plantsInGarden=plantsInGarden;
	}
	public void setUniquePlants(ArrayList<Plant> uniquePlants) {
		this.uniquePlants=uniquePlants;
	}
	public void setTopThree(ArrayList<Plant> topThree) {
		this.topThree=topThree;
	}
	
	/**
	 * Uses the data for all the plants in the garden to make a new arraylist without any repeat species 
	 * and tracking how many of each plant are present.
	 * @throws Exception 
	 */
	public static void sortPlants() throws Exception {
		plantsInGarden = GardenController.getPlants();

		uniquePlants.clear();
    	int i=0;
        for (Plant p : plantsInGarden) {
        	i=0;
	        for (Plant m : uniquePlants) {
	        	try {
					if (p.getCommonName()==m.getCommonName()) {
						i++;
						m.numInPlot++;
						//System.out.println("inc i:" + i); 
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        	if (i==0) {
	        		uniquePlants.add(p);
	        		//System.out.println("added " + p.getCommonName()); 
	        	}
        	}
        
      }
	
	/**
	 * Iterates through the list of plants organized by species and finds which species have the most leps 
	 * and sets this in the array list topthree.
	 */
    public static void findTopSpecies() {
    	topThree.clear();
    	if (uniquePlants.size() > 2) {
    		Plant one = uniquePlants.get(0);
    		Plant two = uniquePlants.get(1);
    		Plant three = uniquePlants.get(2);
    		Plant m;
    		if(one.getLep().getNumLeps() < two.getLep().getNumLeps()) {
				m=two;
    			two=one;
    			one=m;
			}
    		if(one.getLep().getNumLeps() < three.getLep().getNumLeps()) {
				m=three;
    			three=one;
    			one=m;
			}
    		if(two.getLep().getNumLeps() < three.getLep().getNumLeps()) {
				m=three;
    			three=two;
    			two=m;
			}
    		for (Plant l : uniquePlants) {
    			if (l!=one & l!=two & l!=three) {
	    			if(l.getLep().getNumLeps() > one.getLep().getNumLeps()) {
	    				m=one;
	    				one=l;
	    				three=two;
	    				two=m;
	    			}
	    			else if(l.getLep().getNumLeps() > two.getLep().getNumLeps()) {
	    				m=two;
	    				two=l;
	    				three=m;
	    			}
	    			else if(l.getLep().getNumLeps() > three.getLep().getNumLeps()) {
	    				three=l;
	    			}
    			}
    		}
    		topThree.add(one);
        	topThree.add(two);
        	topThree.add(three);
        	//System.out.println("one: " + one.getCommonName() + " two: "+ two.getCommonName()+ " three: "+ three.getCommonName());
    	}
    	else{
    		for (Plant l : uniquePlants) {
	    		topThree.add(l);
    		}
    	}
    	
    	
    }
	
	
}


