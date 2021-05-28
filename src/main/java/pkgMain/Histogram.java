package pkgMain;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.util.*;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.image.ImageView;


/** This file organizes the data for the leps in the garden and creates a histogram with this data.
 * 
 * References: http://java-buddy.blogspot.com/2015/07/javafx-barchart-example-plot-data.html
 * 
 * @author Allie
 *
 */

public class Histogram{
    Image butterflyIcon;
    Image mothIcon;
    int yaxis;
    int xaxis;
    Image key;
    int totalLeps=0;
    int picSize=100;
    ArrayList<Plant> plotList = new ArrayList<>(); // use toString instead of ArrayList<String> otherSpecies;
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis);
    //public StackPane root = new StackPane();
    //private Text title;
    public Label labelInfo = new Label();
    public Scene histo;
    public VBox vBox = new VBox();
    GridPane gridpane = new GridPane();
    public StackPane root = new StackPane();
	private ArrayList<Plant> topThree = new ArrayList<Plant>();
	private ListView<AnchorPane> list = new ListView<AnchorPane>();
	private HBox titleBox = new HBox();
	private HBox infoBox = new HBox();
	private Text info = new Text("Thank you for supporting a community of lepidoptera!");
	private Text newTitle = new Text("Your garden supports leps!");
	private final int INSET_TOP = 15;
	private final int INSET_BOTTOM = 15;
	private int INSET_RIGHT = 455;
	private int INSET_LEFT = 455;
	private AnchorPane ap = new AnchorPane();
	private BorderPane infoPane = new BorderPane();
	private final int PLANT_WD_HT = 250;
    
    public Histogram() {
    	histo = new Scene(root, View.WIDTH, View.HEIGHT);
    	format();
    	showHistogram();
    }
    
    /**
 	 * This sets up places an anchor pane and border pane on the Histogram screen. It formats it to match the rest of the screens.
 	 */
    public void format() {
		ap.getChildren().addAll(titleBox);
		list.prefWidthProperty().bind(root.widthProperty());
        titleBox.prefWidthProperty().bind(root.widthProperty());
        infoBox.prefWidthProperty().bind(root.widthProperty());
        
        titleBox.setPadding(new Insets(INSET_TOP, INSET_RIGHT, INSET_BOTTOM, INSET_LEFT)); 
      	titleBox.setAlignment(Pos.BASELINE_CENTER);
      	titleBox.setStyle("-fx-background-color: #2A363B;");
      	
      	Color green = Color.web("99B898");
      	newTitle.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Black.ttf"), 25));
      	newTitle.setFill(green);
      	
      	infoBox.setPadding(new Insets(INSET_TOP, INSET_RIGHT, INSET_BOTTOM, INSET_LEFT)); 
      	infoBox.setAlignment(Pos.BOTTOM_CENTER);
      	infoBox.setStyle("-fx-background-color: #99B898;");
      	
      	Color navy = Color.web("2A363B");
      	info.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Italic.ttf"), 15));
      	info.setFill(navy);
      	
      	titleBox.getChildren().add(newTitle);
      	infoBox.getChildren().add(info);
      		
      	StackPane.setAlignment(ap, Pos.TOP_CENTER);
      	StackPane.setAlignment(infoPane, Pos.TOP_CENTER);
      	infoPane.setTop(infoBox);
      	infoPane.setCenter(list);	
    }
    
    /**
 	 * This sets up the histogram by setting the labels, axis, and title.
 	 * This also updates the histogram's title based on the current plot data.
 	 */
     public void showHistogram() {
         xAxis.setLabel("Plant Species");       
         yAxis.setLabel("Population"); 
         
         if (totalLeps==0) {newTitle.setText("Add Some Plants In Your Garden To See The Butterflies and Moth Species Supported!");}
         else{newTitle.setText("Your Garden Supports " + calcTotalLeps() + " Butterflies and Moth Species!");}
         
         vBox.getChildren().addAll(ap, barChart, infoPane);
         vBox.setStyle("-fx-background-color: #99B898;");
         vBox.prefHeightProperty().bind(root.heightProperty());
         
         Color green = Color.web("99B898");
         
         root = new StackPane();
         root.setBackground(new Background(new BackgroundFill(green, null, null)));
         root.getChildren().add(vBox);
         
         histo = new Scene(root, View.WIDTH, View.HEIGHT);
         }
    
    /**
	 * Gets the data for the three species of leps that are mostly prevalent in the garden.
	 * @param i is the index in the list of leps
	 */
    public void getLepData(int i) {
    	if(getTopThree().get(i)!=null) {
	        XYChart.Series<String, Number> series = new XYChart.Series<>();
	        Plant l=getTopThree().get(i);
	        series.setName(l.getCommonName());
	        XYChart.Data<String, Number> data = new XYChart.Data<>(l.getCommonName(), l.getLep().getNumLeps());
	        series.getData().add(data);
	      //displayImages(data);
	        barChart.getData().add(series);
    	}
    }
    
    
    /**
	 * This method puts an image over the bar chart to show the species of lep being represented.
	 */
    public void displayImages(XYChart.Data<String, Number> data) {
    	
    	//XYChart.Data<String,Number> data = (XYChart.Data<String, Number>) data;	
        StackPane node = (StackPane) data.getNode();

        //StackPane node = (StackPane) series.getNode();
        //series.getNode().getXValue();
        String name = (String) data.getXValue();
        System.out.println(name);
        Plant m =topThree.get(0);
        for (Plant p: topThree) {
        	if (name==p.getCommonName()) {
        		m=p;
        	}
        }
        ImageView imageView = getImage(m);
        StackPane s= new StackPane(data.getNode());
        s.getChildren().add(imageView);
        node.getChildren().add(imageView);
         
    }
    
    /**
	 * This find the overall amount of leps supported in the garden.
	 */
    public int calcTotalLeps(){
    	totalLeps=0;
    	if(Model.getUniquePlants()==null) {
    		return 0;
    	}
        for(Plant l : Model.getUniquePlants()) {
            totalLeps+= l.getLep().getNumLeps();
        }
        return totalLeps;
    }
    
    /**
	 * Finds the image of the lep based on its species.
	 */
    public ImageView getImage(Plant p) {
    	ImageView iv2 = new ImageView();
    	iv2.setImage(p.getLepPic());
    	iv2.setPreserveRatio(true);
    	iv2.setFitHeight(PLANT_WD_HT);
    	iv2.setFitWidth(PLANT_WD_HT);
    	return iv2;
    }

    /**
   	 * Getter for the ArrayList the stores the three plant species with the most leps.
   	 *  @return topThree
   	 */
	public ArrayList<Plant> getTopThree() {
		return topThree;
	}

	/**
   	 * Setter for the ArrayList the stores the three plant species with the most leps.
   	
   	 */
	public void setTopThree(ArrayList<Plant> topThree) {
		this.topThree = topThree;
	}
    

}





