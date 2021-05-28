package pkgMain;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * This file is visual representation of the program and manages the window that the user views.
 * 
 * References: Using http://tutorials.jenkov.com/javafx/tabpane.html
 * 
 * @author Allie
 */
public class View extends Application {

	static GardenController garden;
	public final static double WIDTH = 800;
	public final static double HEIGHT = 600;
	public static Stage window = new Stage();
	public BorderPane root = new BorderPane();
	public BorderPane root2 = new BorderPane();
    public TabPane tabs = new TabPane();
    public final int iconSize = 16;
    static Tab plantIndex = new Tab("Plant Index");
    static Tab recTab = new Tab("Recommendations");
    static Tab templateIndex = new Tab("Template Index");
    static ViewPlot vp;
    public View() throws Exception {
    	garden = new GardenController();
    	vp = new ViewPlot();
    }
	
	@Override
	 public void start(Stage window) throws Exception {
				
		window.setTitle("Land 4 Leps");
		window.getIcons().add(new Image("https://www.vhv.rs/dpng/d/44-440112_transparent-background-black-butterfly-png-png-download.png"));

		//scenes
		WelcomeScreen wsscene = new WelcomeScreen();
		GardenConditions gcscene = new GardenConditions();
		ViewPlot vpscene = new ViewPlot();
		CompPlantIndex indscene = new CompPlantIndex();
		Plot pscene = new Plot();
		Histogram histo = new Histogram();
		Basket basketScene = new Basket();
		Recommendation rec = new Recommendation();

        //User can not close tabs
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        createTabs("Home", wsscene.root);
  		createTabs("Conditions", gcscene.borderPane);
  		createTabs("Draw Plot", vpscene.borderPane);
  		
  		recTab.setContent(rec.root);
  		recTab.setOnSelectionChanged(e -> { 
  			try {
				rec.displayAll();
			} catch (Exception e1) {
			
				e1.printStackTrace();
			}
  		});
  		tabs.getTabs().add(recTab);

  		ImageView recView = new ImageView(new Image(getClass().getResourceAsStream("/img/Recommendations.png")));
  		recView.setFitHeight(iconSize);
        recView.setFitWidth(iconSize);
        recTab.setGraphic(recView);
  		
  		plantIndex.setContent(indscene.border);
  		ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/img/Plant Index.png")));
        imageView.setFitHeight(iconSize);
        imageView.setFitWidth(iconSize);
  
        plantIndex.setGraphic(imageView);
  		tabs.getTabs().add(plantIndex);
  			
  		
  		Tab basket = new Tab("My Basket");
  		basket.setContent(basketScene.root);
  		ImageView imageView1 = new ImageView(new Image(getClass().getResourceAsStream("/img/My Basket.png")));
        imageView1.setFitHeight(iconSize);
        imageView1.setFitWidth(iconSize);
        basket.setGraphic(imageView1);
        
        basket.setOnSelectionChanged(e -> {
        	try {
				basketScene.updateList();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        });
        
        tabs.getTabs().add(basket);
        
  		Tab plot = new Tab("Garden Plot");
  		plot.setContent(pscene.root);
  		ImageView imageView11 = new ImageView(new Image(getClass().getResourceAsStream("/img/Garden Plot.png")));
        imageView11.setFitHeight(iconSize);
        imageView11.setFitWidth(iconSize);
        plot.setGraphic(imageView11);
  		
        plot.setOnSelectionChanged(e -> {
        	try {
				pscene.update();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        });
  		
        tabs.getTabs().add(plot);
  		//createTabs("Bar Chart", histo.root);
  		
  		Tab tab = new Tab("Bar Chart");
  	  
    	//Setting images on the tabs
        ImageView imageview = new ImageView(new Image(getClass().getResourceAsStream("/img/"+"Bar Chart.png")));
        imageview.setFitHeight(iconSize);
        imageview.setFitWidth(iconSize);
  
        //histogram
        tab.setGraphic(imageview);
		tabs.getTabs().add(tab);	
		tab.setContent(histo.root);
		tab.setOnSelectionChanged(e -> {
			try {
				histo.totalLeps=0;
				histo.barChart.getData().clear();
		        System.out.println("update");
				Model.sortPlants();
				Model.findTopSpecies();
				histo.setTopThree(Model.getTopThree());			
				for (int i=0; i<histo.getTopThree().size(); i++) {
					if(histo.getTopThree().get(i)!=null) {
						histo.getLepData(i);
					}
			    }
			    
		   
				histo.calcTotalLeps();
				histo.showHistogram();
					
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		
  		VBox vbox = new VBox(tabs);
        Scene scene = new Scene(vbox, 300, 300);

        window.setScene(scene);
      
        window.setMinHeight(HEIGHT);
        window.setMinWidth(WIDTH);

        window.show();
    }
	

	public static String saveChoose() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = directoryChooser.showDialog(window);

		if(selectedDirectory == null){
		     //No Directory selected
			return "failed";
		}else{
		     return selectedDirectory.getAbsolutePath() + "\\";
		}
	}
	
	public static String openChoose() {
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Open Resource File");
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Garden Files", "*.ser"));
		 File selectedFile = fileChooser.showOpenDialog(window);
			if(selectedFile == null){
			     //No Directory selected
				return "failed";
			}else{
			     return selectedFile.getAbsolutePath() + "\\";
			}
	}
      
	/**
	 * Getter for the current garden
	 * @return garden 
	 */
	public static GardenController getGarden() {
		return garden;
	}
	
	/**
	 * Getter for the view plot
	 * @return vp 
	 */
	public static ViewPlot getViewPlot() {
		return vp;
	}
    
	/**
	 * Creates the tabs and sets the icon image
	 */
    private void createTabs(String tabName, Parent paneName) {
    	Tab tab = new Tab(tabName);
  
    	//Setting images on the tabs
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/img/"+tabName+".png")));
        imageView.setFitHeight(iconSize);
        imageView.setFitWidth(iconSize);
  
        tab.setGraphic(imageView);
		tabs.getTabs().add(tab);	
		tab.setContent(paneName);
		} 
  
	public static void main(String[] args) {
		javafx.application.Application.launch();
	}
	
}






