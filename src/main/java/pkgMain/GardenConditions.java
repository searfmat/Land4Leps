package pkgMain;


import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Shows all of the conditions the user must set,
 * including, sun, soil, moisture and budget.
 * 
 * @author Jaden
 *
 */
public class GardenConditions {
	
	BorderPane borderPane = new BorderPane();
	public BorderPane root = new BorderPane();
	AnchorPane ap = new AnchorPane();
	public TilePane tile = new TilePane(Orientation.VERTICAL);
	private Text title = new Text("Garden Conditions");
	public Scene conditions;
	private HBox hbox = new HBox();
	private VBox vboxLeft = new VBox();
	private VBox vboxMid = new VBox();
	private VBox vboxRight = new VBox();
	Button enter = new Button("Enter");
	Button next = new Button("Moisture\n    and\n Budget");
	//DropDowns
	ComboBox<String> sunDropDown = new ComboBox<String>();
	ComboBox<String> moistureDropDown = new ComboBox<String>();
	ComboBox<String> soilDropDown = new ComboBox<String>();
	TextField budgetTextBox;
	private final int IMAGE_SIZE = 300;
	private final int IMAGE_SIZE2 = 300;
	private final int INSET1 = 12;
	private final int INSET2 = 10;
	private final int BUDGET_WIDTH = 150;
	private final int HBOX_SPACING = 10;
	private final int ENTER_WIDTH = 100;
	private final int ENTER_HEIGHT = 20;
	private final int DD_WIDTH = 150;
	private final int DD_HEIGHT = 65;
	public int budget;
	public int selectedIndex;
	public Object selectedItem;
	private final int VBOX_SPACING = 30;
	private final double FIT_VBOX = 0.45;
    ImageView iv = new ImageView();
	ImageView iv2 = new ImageView();	
	ImageView iv3 = new ImageView();	
	ImageView iv4 = new ImageView();
	
	public GardenConditions() throws Exception {
		 conditions = new Scene(borderPane, View.WIDTH, View.HEIGHT);
		 begin();
	}
	
	/**
	 * Sets up the scene and UI for the all the condition inputs.
	 * @throws Exception
	 */
	 public void begin() throws Exception {		
		 
		 //Condition Images
			iv.setImage(new Image(getClass().getResourceAsStream("/img/sun2.png")));
			iv.setPreserveRatio(true);
			iv.setFitHeight(IMAGE_SIZE2);
			iv.setFitWidth(IMAGE_SIZE2);
			
			iv2.setImage(new Image(getClass().getResourceAsStream("/img/soil2.png")));
			iv2.setPreserveRatio(true);
			iv2.setFitHeight(IMAGE_SIZE);
			iv2.setFitWidth(IMAGE_SIZE);
			
			iv3.setImage(new Image(getClass().getResourceAsStream("/img/waterdroplet.png")));
			iv3.setPreserveRatio(true);
			iv3.setFitHeight(IMAGE_SIZE);
			iv3.setFitWidth(IMAGE_SIZE);
			
			iv4.setImage(new Image(getClass().getResourceAsStream("/img/budget.png")));
			iv4.setPreserveRatio(true);
			iv4.setFitHeight(IMAGE_SIZE);
			iv4.setFitWidth(IMAGE_SIZE);
			
			//Set Styles
	    	ap.setStyle("-fx-background-color: #99B898;");
	    	borderPane.setStyle("-fx-background-color: #99B898;");
	    	next.setStyle("-fx-background-color: linear-gradient(#2b3d5b, #2A363B);fx-background-radius: 30;fx-background-insets: 0;-fx-text-fill: white;");
	    	enter.setStyle("-fx-background-color: linear-gradient(#2b3d5b, #2A363B);fx-background-radius: 30;fx-background-insets: 0;-fx-text-fill: white;");
    	
	      	title.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Black.ttf"), 25));
	      	title.setFill(Color.web("2A363B"));
	      	
	      	//Set top bar and button color
	      	hbox.setPadding(new Insets(INSET1, INSET2, INSET1, INSET2));
	      	hbox.setSpacing(HBOX_SPACING);   // Gap between nodes
	      	hbox.setAlignment(Pos.BASELINE_CENTER);
	      	
	      	//Sunlight Drop Down
	        sunDropDown.setValue("Sunlight");
	        sunDropDown.getItems().add("Full Sun");
	        sunDropDown.getItems().add("Partial Shade");
	        sunDropDown.getItems().add("Shade");
	        sunDropDown.getItems().add("Any");
	        
	    //Moisture Drop Down
	        moistureDropDown.setValue("Moisture");
	        moistureDropDown.getItems().add("Wet");
	        moistureDropDown.getItems().add("Moist");
	        moistureDropDown.getItems().add("Dry");
	        moistureDropDown.getItems().add("Any");
	        
	    //Soil Type Drop Down
	        soilDropDown.setValue("Soil Type");
	        soilDropDown.getItems().add("Clay");
	        soilDropDown.getItems().add("Sandy");
	        soilDropDown.getItems().add("Peaty");
	        soilDropDown.getItems().add("Loamy");
	        soilDropDown.getItems().add("Any");
	        
	    //Budget Text Box
	        budgetTextBox = new TextField();
	        budgetTextBox.setPrefWidth(BUDGET_WIDTH);
	        budgetTextBox.setMaxWidth(BUDGET_WIDTH);
	        
	    //Enter Button
	        enter.setPrefSize(ENTER_WIDTH,ENTER_HEIGHT);
	        next.setPrefSize(ENTER_WIDTH, ENTER_WIDTH);
	   
	    //DropDowns
	        soilDropDown.setPrefSize(DD_WIDTH, DD_HEIGHT);
	        sunDropDown.setPrefSize(DD_WIDTH, DD_HEIGHT);
	        moistureDropDown.setPrefSize(DD_WIDTH, DD_HEIGHT);
	        
	    //Mouse Changes
	        soilDropDown.setCursor(Cursor.HAND);
	        sunDropDown.setCursor(Cursor.HAND);
	        moistureDropDown.setCursor(Cursor.HAND);
	        enter.setCursor(Cursor.HAND);
	        next.setCursor(Cursor.HAND);
	        
	      
	    //Set VBox on Left of Screen
	      	vboxLeft.setPadding(new Insets(INSET1, INSET2, INSET1, INSET2));
	        vboxLeft.setStyle("-fx-background-color: #F7F7F7");
	       	vboxLeft.setSpacing(VBOX_SPACING);
	        vboxLeft.setAlignment(Pos.CENTER);
	        vboxLeft.getChildren().addAll(iv, sunDropDown);
	        
	    //Set VBox on Left of Screen
	      	vboxMid.setPadding(new Insets(INSET1, INSET2, INSET1, INSET2));
	        vboxMid.setStyle("-fx-background-color: #F7F7F7");
	       	vboxMid.setSpacing(VBOX_SPACING);
	        vboxMid.setAlignment(Pos.CENTER);
	        vboxMid.getChildren().addAll(next);
	       
	    //Set VBox on Right of Screen
	        vboxRight.setPadding(new Insets(INSET1, INSET2, INSET1, INSET2));
	        vboxRight.setStyle("-fx-background-color: #F7F7F7");
	       	vboxRight.setSpacing(VBOX_SPACING);
	        vboxRight.setAlignment(Pos.CENTER);
	        vboxRight.getChildren().addAll(iv2, soilDropDown);
	       
	    //Sets Boxes/Panes to different sides of borderPane and adds nodes
	        borderPane.setTop(ap);
	        borderPane.setLeft(vboxLeft);
	        borderPane.setCenter(vboxMid);
	        borderPane.setRight(vboxRight);
	        hbox.getChildren().addAll(title);
	        ap.getChildren().addAll(hbox);
	    
	    //Allows box's to fit the screen when expanding
	        hbox.prefWidthProperty().bind(borderPane.widthProperty());
	        vboxLeft.prefWidthProperty().bind(borderPane.widthProperty().multiply(FIT_VBOX));
	        vboxRight.prefWidthProperty().bind(borderPane.widthProperty().multiply(FIT_VBOX));
	        
	    //Only allows the user to type numerical values into the budget box
	        budgetTextBox.setTextFormatter(new TextFormatter<>(c -> {
	            if (!c.getControlNewText().matches("\\d*")) 
	                return null;
	            else
	                return c;
	            }
	        ));
	        
	    //DropDown Listeners
	        sunDropDown.setOnAction((event) -> {
	            View.getGarden().setSun(sunDropDown.getValue().toLowerCase());
	        });
	        
	        moistureDropDown.setOnAction((event) -> {
	            View.getGarden().setMoisture(moistureDropDown.getValue().toLowerCase());	          
	        });
	        
	        soilDropDown.setOnAction((event) -> {
	            View.getGarden().setSoil(soilDropDown.getValue().toLowerCase());
	        });
	        
	        //Resets Enter Button
	        budgetTextBox.setOnMouseClicked(event -> enter.setText("Enter"));
	        
	        //Listener for Enter Button that gets budget and changes text
	        enter.setOnAction(event -> {
	        	enter.setText("Budget Set!");
	        	budget = Integer.parseInt(budgetTextBox.getText());
	        	View.getGarden().setBudget(budget);
	        });
	        
	        //Switches the conditions in the VBox's next button
	        next.setOnAction(event -> {
	        	if(vboxLeft.getChildren().contains(sunDropDown)) {
	        		vboxLeft.getChildren().clear();
		        	vboxRight.getChildren().clear();
		        	vboxLeft.getChildren().addAll(iv3, moistureDropDown);
		        	vboxRight.getChildren().addAll(iv4, budgetTextBox, enter);
		        	next.setText("Sun and Soil");
	        	}
	        	else if(vboxLeft.getChildren().contains(moistureDropDown)){
	        		vboxLeft.getChildren().clear();
		        	vboxRight.getChildren().clear();
	        		vboxLeft.getChildren().addAll(iv, sunDropDown);
	        		vboxRight.getChildren().addAll(iv2, soilDropDown);
		        	next.setText("Moisture\n    and\n Budget");
	        	}
	        });

	 }
}
