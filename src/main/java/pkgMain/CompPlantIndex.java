package pkgMain;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/*
 * Colors used:
 * White: #F7F7F7 or the SNOW in the color enum
 * Blue: #2A363B
 * Green: #99B898
 * 
 * Source used to create the horizontal separator: http://tutorials.jenkov.com/javafx/separator.html
 */

/**
 * Shows the full list of plants that the user can add to the garden. Filter options 
 * sort everything by conditions.
 * 
 * @author Matthew
 *
 */

public class CompPlantIndex {

	public BorderPane border = new BorderPane();
	private Text title = new Text("Complete Native Plant Index");
	private TextField textField = new TextField();
	private ArrayList<Plant> allPlants = View.getGarden().getPlantAL();
	public Scene plantInd;
	private ComboBox<String> comboBox = new ComboBox<String>();
	private ComboBox<String> comboBox1 = new ComboBox<String>();
	private ComboBox<String> comboBox2 = new ComboBox<String>();
	private ComboBox<String> comboBox3 = new ComboBox<String>();
	private ComboBox<String> comboBox4 = new ComboBox<String>();
	private VBox vbox3 = new VBox();
	private VBox vbox4 = new VBox();
	private Button paletteButton = new Button();
	private Button examplesButton = new Button();
	private VBox vbox2 = new VBox();
	private final int INSET_TOP = 15;
	private final int INSET_BOTTOM = 15;
	private final int INSET_RIGHT = 12;
	private final int INSET_LEFT = 12;
	private final int SPACING = 10;
	private final int COMBO_WD = 150;
	private final int PLANT_WD_HT = 240;
	private final int VBOX_SPACING = 10;
	private final int BUTTON_HT = 20;
	private final int REG_BUTTON_WD = 200;
	private final int BODY_SIZE = 13;
	private final int TITLE_SIZE = 25;
	private final double SCALE_VALUE = 0.4;
	private final int LEP_WD_HT = 75;
	private final int TOOLTIP_SIZE = 250;

	public CompPlantIndex() throws Exception {
		plantInd = new Scene(border, View.WIDTH, View.HEIGHT);
		begin();
	}
	/**
	 * Sets up the scene and UI for the index.
	 * @throws Exception
	 */
    public void begin() throws Exception {
    	
    	
        BorderPane bp2 = new BorderPane();
    	handleEnter(textField);
    	examplesButton.setStyle("-fx-background-color: blanchedalmond; -fx-text-fill: black; -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
    	paletteButton.setStyle("-fx-background-color: linear-gradient(to top,-color-amber, transparent); -fx-text-fill: black; -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");

    
    	//Our hbox is the green bar at the top, and the vbox is the navy bar on the side.
    	//Sets the spacine between nodes and their relative positioning
    	HBox hbox = new HBox();
    	VBox vbox = new VBox();
		hbox.setPadding(new Insets(INSET_TOP, INSET_RIGHT, INSET_BOTTOM, INSET_LEFT));
      	hbox.setSpacing(SPACING);   
      	hbox.setAlignment(Pos.BASELINE_CENTER);
      	hbox.setStyle("-fx-background-color: #99B898;");
      	vbox.setPadding(new Insets(INSET_TOP, INSET_RIGHT, INSET_BOTTOM, INSET_LEFT));
      	vbox.setSpacing(SPACING);   
      	vbox.setStyle("-fx-background-color: #2A363B;");
      	
      	//Creating a navy colored title with a custom font
      	Color navy = Color.web("2A363B");
      	title.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Black.ttf"), TITLE_SIZE));
      	title.setFill(navy);
      
  
        //Small "filter by text" using the same idea as the title text
        Text filter = new Text("Filter by: ");
        filter.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf"), BODY_SIZE));
        filter.setFill(Color.SNOW);

        //ComboBox is the dropdown menu (May be useful for selecting conditions)

        comboBox.setValue("Plant Type");
        comboBox.setPrefWidth(COMBO_WD);
        comboBox.getItems().add("Herbaceous");
        comboBox.getItems().add("Woody");
        comboBox.getItems().add("All");
        comboBox.setOnAction(e -> showPlantsOfType());
        
        comboBox1 = new ComboBox<String>();
        comboBox1.setValue("Sunlight");
        comboBox1.setPrefWidth(COMBO_WD);
        comboBox1.getItems().add("Full Sun");
        comboBox1.getItems().add("Partial Shade");
        comboBox1.getItems().add("Full Shade");
        comboBox1.getItems().add("Any");
        comboBox1.setOnAction(e -> sunChoice());

        comboBox2 = new ComboBox<String>();
        comboBox2.setValue("Soil Type");
        comboBox2.setPrefWidth(COMBO_WD);
        comboBox2.getItems().add("Sandy");
        comboBox2.getItems().add("Loamy");
        comboBox2.getItems().add("Clay");
        comboBox2.getItems().add("Peaty");
        comboBox2.getItems().add("Any");
        comboBox2.setOnAction(e -> soilChoice());
        
        comboBox3 = new ComboBox<String>();
        comboBox3.setValue("Moisture");
        comboBox3.setPrefWidth(COMBO_WD);
        comboBox3.getItems().add("Dry");
        comboBox3.getItems().add("Moist");
        comboBox3.getItems().add("Wet");
        comboBox3.getItems().add("Any");
        comboBox3.setOnAction(e -> moistureChoice());
        
        comboBox4 = new ComboBox<String>();
        comboBox4.setValue("Color");
        comboBox4.setPrefWidth(COMBO_WD);
        comboBox4.getItems().add("Blue");
        comboBox4.getItems().add("Brown");
        comboBox4.getItems().add("Green");
        comboBox4.getItems().add("Purple");
        comboBox4.getItems().add("Orange");
        comboBox4.getItems().add("Pink");
        comboBox4.getItems().add("Red");
        comboBox4.getItems().add("White");
        comboBox4.getItems().add("Yellow");
        comboBox4.getItems().add("All");
        comboBox4.setOnAction(e -> colorChoice());
        
        //Search bar
        textField.setPromptText("Search ... ");
        
        //Vbox 2 is the vbox you see within the scrollpane    
        vbox2.setPadding(new Insets(INSET_TOP, INSET_RIGHT, INSET_BOTTOM, INSET_LEFT));
      	vbox2.setSpacing(VBOX_SPACING);
        vbox2.setAlignment(Pos.CENTER);
        vbox3.setPadding(new Insets(INSET_TOP, INSET_RIGHT, INSET_BOTTOM, INSET_LEFT));
      	vbox3.setSpacing(VBOX_SPACING);
        vbox3.setAlignment(Pos.TOP_CENTER);
        vbox4.setPadding(new Insets(INSET_TOP, INSET_RIGHT, INSET_BOTTOM, INSET_LEFT));
      	vbox4.setSpacing(VBOX_SPACING);
        vbox4.setAlignment(Pos.TOP_CENTER);
      
        //ScrollPane is how you get the scrollbar on the side
        ScrollPane scrollPane = new ScrollPane();


        //This is how I was able to center the text and vbox2 within their respective panes
        vbox3.prefWidthProperty().bind(scrollPane.widthProperty().multiply(SCALE_VALUE));
        vbox2.prefWidthProperty().bind(scrollPane.widthProperty().multiply(SCALE_VALUE));
        hbox.prefWidthProperty().bind(border.widthProperty());
      
        displayAll();
        bp2.setLeft(vbox2);
        bp2.setRight(vbox3);
        bp2.setCenter(vbox4);
        scrollPane.setContent(bp2);
        //Adding the nodes to the vbox and hbox, then adding that to the borderpane
        border.setCenter(scrollPane);
        border.setTop(hbox);
        border.setLeft(vbox);
        hbox.getChildren().addAll(title);
        vbox.getChildren().addAll(textField, filter, comboBox, comboBox1, comboBox2, comboBox3, comboBox4);
    }
    

    /**
     * Displays every plant in plant_data
     * @throws Exception
     */
   private void displayAll() throws Exception {
	   int col = 0;
       for(Plant plant : allPlants) {
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
	/**
	 * Creates the text to display the scientific name of the plant.
	 * @param plant Plant that name comes from
	 * @return name The text of the name.
	 * @throws Exception
	 */
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
	
	/**
	 * Displays the results of the search.
	 * @param enter String taken from the text field 
	 * @throws Exception
	 */
	private void displaySearchResults(String enter) throws Exception {
		vbox2.getChildren().clear();
		vbox3.getChildren().clear();
		vbox4.getChildren().clear();
		if( enter.equals("")) {
			displayAll();
		} else {
			for(Plant plant : allPlants) {
				if(plant.getCommonName().equals(enter) || plant.getScientificName().equals(enter)) {
					vbox4.getChildren().addAll(createPlant(plant), createText(plant),createButton(plant));
				}
			}
		}
	} 
	
	/**
	 * Handler for the text field search bar.
	 * @param tf
	 */
	private void handleEnter(TextField tf) {
        tf.setOnAction(e -> {
        	try {
				displaySearchResults(tf.getText());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        });
	}
	/**
	 * Handler for the color dropdown.
	 */
	private void colorChoice() {
		vbox2.getChildren().clear();
		vbox3.getChildren().clear();
		vbox4.getChildren().clear();
		for (Plant plant : allPlants) {
				try {
					if (comboBox4.getValue().equals("Blue")) {
						if (plant.getColor().equals("blue")) {
							vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
						}
					}
					else if (comboBox4.getValue().equals("Brown")) {
						if (plant.getColor().equals("brown")) {
							vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
						}
					}
					else if (comboBox4.getValue().equals("Green")) {
						if (plant.getColor().equals("green")) {
							vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
						}
					}
					else if (comboBox4.getValue().equals("Purple")) {
						if (plant.getColor().equals("purple")) {
							vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
						}
					}
					else if (comboBox4.getValue().equals("Orange")) {
						if (plant.getColor().equals("orange")) {
							vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
						}
					}
					else if (comboBox4.getValue().equals("Pink")) {
						if (plant.getColor().equals("pink")) {
							vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
						}
					}
					else if (comboBox4.getValue().equals("Red")) {
						if (plant.getColor().equals("red")) {
							vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
						}
					}else if (comboBox4.getValue().equals("White")) {
						if (plant.getColor().equals("white")) {
							vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
						}
					}
					else if (comboBox4.getValue().equals("Yellow")) {
						if (plant.getColor().equals("yellow")) {
							vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
						}
					}
					else if (comboBox4.getValue().equals("Any")) {
						if (plant.getColor().equals("any")) {
							vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
						}
					}
					else {
						displayAll();
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
	/**
	 * Handler for the type dropdown.
	 */
	private void showPlantsOfType() {
		vbox2.getChildren().clear();
		vbox3.getChildren().clear();
		vbox4.getChildren().clear();
		for (Plant plant : allPlants) {
			try {
				if (comboBox.getValue().equals("Herbaceous")) {
					if (plant.getType().equals("h")) {
						vbox4.getChildren().addAll(createPlant(plant),createText(plant), createButton(plant));
					}
				}
				else if (comboBox.getValue().equals("Woody")) {
					if (plant.getType().equals("w")) {
						vbox4.getChildren().addAll(createPlant(plant), createText(plant),createButton(plant));
					}
				}
				else {
					displayAll();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Handler for the sun dropdown.
	 */
	private void sunChoice() {
		vbox2.getChildren().clear();
		vbox3.getChildren().clear();
		vbox4.getChildren().clear();
		for (Plant plant : allPlants) {
			try {
				if (comboBox1.getValue().equals("Full Sun")) {
					if (plant.getSun().equals("full sun")) {
						vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
					}
				}
				else if (comboBox1.getValue().equals("Partial Shade")) {
					if (plant.getSun().equals("partial shade")) {
						vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));	
					}	
				}
				else if (comboBox1.getValue().equals("Full Shade")) {
					if (plant.getSun().equals("full shade")) {
						vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));	
					}	
				}
				else {
					displayAll();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Handler for the soil dropdown.
	 */
	private void soilChoice() {
		vbox2.getChildren().clear();
		vbox3.getChildren().clear();
		vbox4.getChildren().clear();
		for (Plant plant : allPlants) {
			try {
				if (comboBox2.getValue().equals("Sandy")) {
					if (plant.getSoilType().equals("sandy")) {
						vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
					}
				}
				else if (comboBox2.getValue().equals("Loamy")) {
					if (plant.getSoilType().equals("loamy")) {
						vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
					}
				}
				else if (comboBox2.getValue().equals("Clay")) {
					if (plant.getSoilType().equals("clay")) {
						vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
					}
				}
				else if (comboBox2.getValue().equals("Peaty")) {
					if (plant.getSoilType().equals("peaty")) {
						vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
					}
				}
				else if (comboBox2.getValue().equals("Any")) {
					if (plant.getSoilType().equals("any")) {
						vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
					}
				}
				else {
					displayAll();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Handler for the moisture dropdown.
	 */
	private void moistureChoice() {
		vbox2.getChildren().clear();
		vbox3.getChildren().clear();
		vbox4.getChildren().clear();
		for (Plant plant : allPlants) {
			try {
				if (comboBox3.getValue().equals("Dry")) {
					if (plant.getMoisture().equals("dry")) {
						vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
					}
				}
				else if (comboBox3.getValue().equals("Moist")) {
					if (plant.getMoisture().equals("moist")) {
						vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
					}
				} else if (comboBox3.getValue().equals("Wet")) {
					if (plant.getMoisture().equals("wet")) {
						vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
					}
				}
				else if (comboBox3.getValue().equals("Any")) {
					if (plant.getMoisture().equals("any")) {
						vbox4.getChildren().addAll(createPlant(plant),createText(plant),createButton(plant));
					}
				}
				else {
					displayAll();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	


}