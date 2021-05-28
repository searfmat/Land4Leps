package pkgMain;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * A simple file that displays the list of plants that the user added to the basket.
 * 
 * References for ListView : https://docs.oracle.com/javafx/2/ui_controls/list-view.htm http://tutorials.jenkov.com/javafx/listview.html
 * 
 * @author Matthew
 *
 */
public class Basket {
	public Scene basketScene;
	public BorderPane root = new BorderPane();
	private BorderPane infoPane = new BorderPane();
	private ArrayList<Plant> plantsInBasket = View.getGarden().getBasket();
	private ListView<AnchorPane> list = new ListView<AnchorPane>();
	private HBox titleBox = new HBox();
	private HBox infoBox = new HBox();
	private Text info = new Text("Plants in here will be added to the plot! Press the compost button to remove a plant.");
	private Text title = new Text("My Basket");
	private final int INSET_TOP = 15;
	private final int INSET_BOTTOM = 15;
	private final int INSET_RIGHT = 12;
	private final int INSET_LEFT = 12;
	private final int PLANT_WD_HT = 90;
	private final int LEP_WD_HT = 75;
	private final int TOOLTIP_SIZE = 250;
	private final int COMPOST_SIZE = 25;
	private final double COMPOST_TOP = 25d;
	private final double COMPOST_RIGHT = 15d;
	public Basket() throws Exception {
		basketScene = new Scene(root, View.WIDTH, View.HEIGHT);
		begin();
	}
	
	/**
	 * Initializes the UI for the basket scene.
	 * @throws Exception
	 */
	public void begin() throws Exception {
		
		AnchorPane ap = new AnchorPane();
		ap.getChildren().addAll(titleBox);
		list.prefWidthProperty().bind(root.widthProperty());
        titleBox.prefWidthProperty().bind(root.widthProperty());
        infoBox.prefWidthProperty().bind(root.widthProperty());
        
        titleBox.setPadding(new Insets(INSET_TOP, INSET_RIGHT, INSET_BOTTOM, INSET_LEFT)); 
      	titleBox.setAlignment(Pos.BASELINE_CENTER);
      	titleBox.setStyle("-fx-background-color: #2A363B;");
      	
      	Color green = Color.web("99B898");
      	title.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Black.ttf"), 25));
      	title.setFill(green);
      	
      	infoBox.setPadding(new Insets(INSET_TOP, INSET_RIGHT, INSET_BOTTOM, INSET_LEFT)); 
      	infoBox.setAlignment(Pos.BASELINE_CENTER);
      	infoBox.setStyle("-fx-background-color: #99B898;");
      	
      	Color navy = Color.web("2A363B");
      	info.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Italic.ttf"), 15));
      	info.setFill(navy);
      	
      	titleBox.getChildren().add(title);
      	infoBox.getChildren().add(info);
      		
      	root.setTop(ap);
      	root.setCenter(infoPane);
      	infoPane.setTop(infoBox);
      	infoPane.setCenter(list);	
	}
	
	/**
	 * Updates the list of plants that are in the basket.
	 * Assigns tooltip for plant and lep information.
	 * @throws Exception
	 */
	void updateList() throws Exception {
		list.getItems().clear();
		for(Plant p : plantsInBasket) {
			AnchorPane ap = new AnchorPane();
			Label sname = new Label(p.getScientificName() + "   |");
			Label cname = new Label(p.getCommonName());
			sname.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Italic.ttf"), 21));
			cname.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Italic.ttf"), 21));
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_LEFT);
            
    		ImageView iv = new ImageView();
    		iv.setImage(p.getPlantPic());
    		iv.setPreserveRatio(true);
    	    iv.setFitHeight(PLANT_WD_HT);
    	    iv.setFitWidth(PLANT_WD_HT);
   
    	    Tooltip tooltip = new Tooltip("Common Name: " + p.getCommonName() + System.lineSeparator()
    		+ "Spread: " + p.getSpread() + " ft." + System.lineSeparator() + 
    		"Soil type: " + p.getSoilType() + System.lineSeparator() +
    		"Sun level: " + p.getSun() + System.lineSeparator() + 
    		"Moisture: " + p.getMoisture() + System.lineSeparator()  +"Lep Species Supported: " + p.getLep().getNumLeps() + System.lineSeparator() +
    		"Most common lep: " + p.getLep().getSpecies());
    	    ImageView ivLep = new ImageView(p.getLepPic());
    	    ivLep.setFitHeight(LEP_WD_HT);
    	    ivLep.setFitWidth(LEP_WD_HT);
    	    tooltip.setGraphic(ivLep);
    	    tooltip.setPrefWidth(TOOLTIP_SIZE);
   
    	    tooltip.setWrapText(true);
    	    Tooltip.install(iv, tooltip);
            // Add the values from our piece to the HBox
            hBox.getChildren().addAll(iv, sname, cname);
            
        	ImageView compost = new ImageView(new Image(getClass().getResourceAsStream("/img/compost.png")));
          	compost.setFitHeight(COMPOST_SIZE);
            compost.setFitWidth(COMPOST_SIZE);
            
            Button b = new Button();
            b.setGraphic(compost);
            b.setStyle("-fx-background-color: #99B898;");
            
            ap.getChildren().addAll(hBox, b);
            AnchorPane.setLeftAnchor(hBox, root.getWidth() / 3);
            AnchorPane.setRightAnchor(b, COMPOST_RIGHT);
            AnchorPane.setTopAnchor(b, COMPOST_TOP);
            
            b.setOnAction(e -> {
            	try {
					View.getGarden().removeFromBasket(p);
					updateList();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            	
            	
            });
            list.getItems().add(ap);
		}
	}
	
	
}