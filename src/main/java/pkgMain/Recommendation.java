package pkgMain;

import java.util.ArrayList;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Displays the recommended plants based on previous user inputs.
 * User can add these plants to that basket to be used in the garden. Hovering over a plant shows information about each plant and lep combo.
 * 
 * @author Matthew
 *
 */
public class Recommendation {

	public BorderPane root = new BorderPane();
	public Scene rec;
	private VBox vbox2 = new VBox();
	private VBox vbox3 = new VBox();
	private VBox vbox4 = new VBox();
	private ArrayList<Plant> allPlants = View.getGarden().getPlantAL();
	private final int INSET_TOP = 15;
	private final int INSET_BOTTOM = 15;
	private final int INSET_RIGHT = 12;
	private final int INSET_LEFT = 12;
	private final int PLANT_WD_HT = 250;
	private final int BUTTON_HT = 20;
	private final int REG_BUTTON_WD = 200;
	private final int TITLE_SIZE = 25;
	private final double SCALE_VALUE = 0.4;
	private final int LEP_WD_HT = 75;
	private final int TOOLTIP_SIZE = 250;

	public Recommendation() throws Exception {
		rec = new Scene(root, View.WIDTH, View.HEIGHT);
		begin();
	}
	/**
	 * Sets up the UI for the recommendations page
	 * @throws Exception
	 */
    public void begin() throws Exception {
    	
    	Text title = new Text("Plant Recommendations");
    	Text info = new Text("Based on your previous inputs in the Conditions tab, here are some native plants we recommend for your garden!");
    	

      	
      	//Creating a navy colored title with a custom font
      	Color navy = Color.web("2A363B");
      	title.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Black.ttf"), TITLE_SIZE));
      	title.setFill(navy);
      	BorderPane bp = new BorderPane();
  
        //Vbox 2 is the vbox you see within the scrollpane
   
        vbox2.setPadding(new Insets(INSET_TOP, INSET_RIGHT, INSET_BOTTOM, INSET_LEFT));
      	vbox2.setSpacing(10);
        vbox2.setAlignment(Pos.CENTER);
        vbox3.setPadding(new Insets(INSET_TOP, INSET_RIGHT, INSET_BOTTOM, INSET_LEFT));
      	vbox3.setSpacing(10);
        vbox3.setAlignment(Pos.TOP_CENTER);
        vbox4.setPadding(new Insets(INSET_TOP, INSET_RIGHT, INSET_BOTTOM, INSET_LEFT));
      	vbox4.setSpacing(10);
        vbox4.setAlignment(Pos.TOP_CENTER);

    	
    	HBox titleBox = new HBox();
    	HBox infoBox = new HBox();
    	
    	titleBox.setStyle("-fx-background-color: #99B898;");
    	infoBox.setStyle("-fx-background-color: #2A363B;");
    	
      	title.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Black.ttf"), 25));
      	title.setFill(navy);
      	
      	info.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Italic.ttf"), 15));
      	info.setFill(Color.SNOW);
      	
      	titleBox.getChildren().addAll(title);
      	infoBox.getChildren().addAll(info);
      	
      	titleBox.setPadding(new Insets(INSET_TOP, INSET_RIGHT, INSET_BOTTOM, INSET_LEFT));
      	infoBox.setPadding(new Insets(INSET_TOP, INSET_RIGHT, INSET_BOTTOM, INSET_LEFT));
	
        titleBox.prefWidthProperty().bind(bp.widthProperty());
        titleBox.setAlignment(Pos.BASELINE_CENTER);
        infoBox.prefWidthProperty().bind(bp.widthProperty());
        infoBox.setAlignment(Pos.BASELINE_CENTER);
        
     
        
        ScrollPane sp = new ScrollPane();
        
        vbox3.prefWidthProperty().bind(bp.widthProperty().multiply(SCALE_VALUE));
        vbox2.prefWidthProperty().bind(bp.widthProperty().multiply(SCALE_VALUE));
        
        
        BorderPane bp2 = new BorderPane();
        bp2.setLeft(vbox2);
        bp2.setRight(vbox3);
        bp2.setCenter(vbox4);
        sp.setContent(bp2);
        

      	bp.setTop(infoBox);
      	bp.setCenter(sp);
      	root.setTop(titleBox);
      	root.setCenter(bp);
      	
    }
    

    /**
     * Displays every plant in plant_data
     * @throws Exception
     */
   void displayAll() throws Exception {
	   vbox2.getChildren().clear();
	   vbox3.getChildren().clear();
	   vbox4.getChildren().clear();
	   String soil = View.getGarden().getSoil();
	   String moisture = View.getGarden().getMoisture();
	   String sun = View.getGarden().getSun();
	   int col = 0;
       for(Plant plant : allPlants) {
    	   if((soil == null || soil.equals(plant.getSoilType())) && (moisture == null || moisture.equals(plant.getMoisture())) && (sun == null || sun.equals(plant.getSun()))) {
    		   	if(col == 0) {
        	   		vbox2.getChildren().addAll(createPlant(plant), createText(plant), createButton(plant));
        	   		col = 1;
        	   	} else if(col == 1) {
        	   		vbox3.getChildren().addAll(createPlant(plant), createText(plant), createButton(plant));
        	   		col = 2;
        	   	} else {
        	   	    vbox4.getChildren().addAll(createPlant(plant), createText(plant), createButton(plant));
        	   		col = 0;
        	   	}
    	   }
		}
    }
	
   /**
    * Creates a new image view for a plant.
    * @param plant Plant that we want to create an iv of
    * @return iv The respective ImageView
    * @throws Exception
    */
	private ImageView createPlant(Plant plant) throws Exception {
		ImageView iv = new ImageView();
		iv.setImage(plant.getPlantPic());
		iv.setPreserveRatio(true);
	    iv.setFitHeight(PLANT_WD_HT);
	    iv.setFitWidth(PLANT_WD_HT);
	    Tooltip tooltip = new Tooltip("Common Name: " + plant.getCommonName() + System.lineSeparator()
	    		+ "Spread: " + plant.getSpread() + " ft." + System.lineSeparator() + 
	    		"Soil type: " + plant.getSoilType() + System.lineSeparator() +
	    		"Sun level: " + plant.getSun() + System.lineSeparator() + 
	    		"Moisture: " + plant.getMoisture() + System.lineSeparator()  +"Lep Species Supported: " + plant.getLep().getNumLeps() + System.lineSeparator() +
	    		"Most common lep: " + plant.getLep().getSpecies());
	    ImageView ivLep = new ImageView(plant.getLepPic());
	    ivLep.setFitHeight(LEP_WD_HT);
	    ivLep.setFitWidth(LEP_WD_HT);
	    tooltip.setGraphic(ivLep);
	    tooltip.setPrefWidth(TOOLTIP_SIZE);
	   
	    tooltip.setWrapText(true);
	    Tooltip.install(iv, tooltip);
	    return iv;
	}
   private Text createText(Plant plant) throws Exception {
	   Text name = new Text(plant.getScientificName());
       name.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Italic.ttf"), 21));
       Color navy = Color.web("2A363B");
       name.setFill(navy);
       return name;
	}
	/**
	 * Creates a button that leads to the plant info popup
	 * @param plant Plant that the button is created for
	 * @return b 
	 * @throws Exception
	 */
	private Button createButton(Plant plant) throws Exception {
		Button b = new Button();
		b.setStyle("-fx-background-color: linear-gradient(#2b3d5b, #2A363B);fx-background-radius: 30;fx-background-insets: 0;-fx-text-fill: white;");
		b.setText("Add to Basket");
		b.setPrefSize(REG_BUTTON_WD, BUTTON_HT);
		b.setOnAction(e -> {
       	try {
				View.getGarden().addToBasket(plant);
				b.setText("Plant added to basket!");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
       });
       return b;
	}
}