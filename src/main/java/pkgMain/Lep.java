package pkgMain;

import java.util.ArrayList;


/** This file contains the information for the Leps.
 * 
 * @author Allie
 *
 */
public class Lep {
	public String species;
	public int numLeps;
    public int totalInBasket;
    public ArrayList<Lep> lepsInGarden;
    public int numSpecies=1;
    
    
    public Lep(String species, int numLeps) {
    	this.numLeps=numLeps;
    	this.species=species;
    }
   
    public Lep() {}

	/**
	 * Getter for the lep number
	 * @return numLeps 
	 */
	public int getNumLeps() {
		return numLeps;
	}
	
	/**
	 * Setter for the lep number
	 * 
	 */
	public void setNumLeps(int numLeps) {
		this.numLeps = numLeps;
	}
	
	/**
	 * Getter for the lep species
	 * @return species 
	 */
	public String getSpecies() {
		return species;
	}
	
	/**
	 * Setter for the lep species
	 *
	 */
	public void setSpecies(String species) {
		this.species = species;
	}
	
	
	/**
	 * Calculates the total number of leps of that species using the number of each plant with that lep type 
	 * and the number of leps each plant supports.
	 * @return total leps of that species 
	 */
	public int calcTotalOfSpecies() {
		return (numLeps*numSpecies);
	}
	
	
}


