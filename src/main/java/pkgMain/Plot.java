package pkgMain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The main plot where the user is able to drag plants from the basket and add it to the garden as they please.
 * 
 * Draws from: https://docs.oracle.com/javafx/2/drag_drop/HelloDragAndDrop.java.html and https://docs.oracle.com/javase/8/javafx/events-tutorial/paper-doll.htm
 * 
 * @author Matthew
 *
 */
public class Plot {
    public ArrayList<String> plotPlants = new ArrayList<String>();
	public ArrayList<String> plist = new ArrayList<String>();
	public Scene pscene;
	public int lepCount = 0;
	public BorderPane root = new BorderPane();
	private FlowPane flow = new FlowPane();
	private ScrollPane sp = new ScrollPane();
	private AnchorPane ap = new AnchorPane();
	private HBox titleBox = new HBox();
	private VBox plantBox = new VBox();
	private int budget = 0;
	private Text title = new Text("Garden Plot");
	private Text budgetLabel = new Text("Cost: " + budget);
	private Text lepLabel = new Text("Leps supported:	" + lepCount);
	private Button export = new Button("Export");
	private ProgressBar pb = new ProgressBar();
	private ArrayList<Plant> allPlants = View.getGarden().getBasket();
	public static ArrayList<Plant> plantsInPlot = new ArrayList<>();
	private ArrayList<ImageView> imagesInPlot = new ArrayList<>();
    static ArrayList<Double> xplants = new ArrayList<Double>();
    static ArrayList<Double> yplants = new ArrayList<Double>();
	private String draggedPlantName;
	private Group g = new Group();
	private final int TITLE_SIZE = 25;
	private final double WIDTH = 800;
	private final double HEIGHT = 600;
	private final int EXPORT_WIDTH = 100;
	private final int EXPORT_HEIGHT = 50;
	private final double ANCHOR_DEFAULT = 10d;
	private final double ANCHOR_BUDGET = 125d;
	private final double ANCHOR_LEP = 40d;
	private final int INSET_TOP = 15;
	private final int INSET_BOTTOM = 15;
	private final int INSET_RIGHT = 12;
	private final int INSET_LEFT = 12;
	private final int SPACE = 10;
	private final int PIC_HEIGHT = 75;
	private final double FULL = 100.0F;
	private ContextMenu cm = new ContextMenu();
	private MenuItem mi = new MenuItem("Compost");
	private static ArrayList<Double> xlist = new ArrayList<Double>();
	private static ArrayList<Double> ylist = new ArrayList<Double>();
	private static double startx;
	private static double starty;
	static boolean imp;
	
	public Plot() throws Exception{
		pscene = new Scene(root, WIDTH, HEIGHT);
		begin();
	}
	
	/**
	 * Sets up the UI for the plot and assigns the appropriate handlers.
	 * @throws Exception
	 */
    public void begin() throws Exception {
    	
    	lepLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf"), 15));
    	budgetLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf"), 15));
    	
    	cm.getItems().add(mi);
    	AnchorPane tap = new AnchorPane();
    	
    	export.setPrefSize(EXPORT_WIDTH, EXPORT_HEIGHT);
    	export.setStyle("-fx-background-color: linear-gradient(#2b3d5b, #2A363B);fx-background-radius: 30;fx-background-insets: 0;-fx-text-fill: white;");
    	export.setOnAction(e -> {
			try {
				export();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
    	ap.getChildren().addAll(export,lepLabel,budgetLabel,pb);
    	
    	
    	//AnchorPane formatting 
        AnchorPane.setRightAnchor(pb, ANCHOR_DEFAULT);
        AnchorPane.setTopAnchor(pb, ANCHOR_DEFAULT);
        AnchorPane.setRightAnchor(budgetLabel, ANCHOR_BUDGET);
        AnchorPane.setTopAnchor(budgetLabel, ANCHOR_DEFAULT);
        AnchorPane.setRightAnchor(lepLabel, ANCHOR_DEFAULT);
        AnchorPane.setTopAnchor(lepLabel, ANCHOR_LEP);
        AnchorPane.setRightAnchor(export, ANCHOR_DEFAULT);
        AnchorPane.setBottomAnchor(export, ANCHOR_DEFAULT);
        
     	//Creating a navy colored title with a custom font
      	Color navy = Color.web("2A363B");
      	title.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Black.ttf"), TITLE_SIZE));
      	title.setFill(navy);
      	
    	//Set styling and spacing for the vbox on left side
    	plantBox.setStyle("-fx-background-color: #2A363B;");
    	plantBox.setPadding(new Insets(INSET_TOP, INSET_RIGHT, INSET_BOTTOM, INSET_LEFT));
      	plantBox.setSpacing(SPACE);
      	plantBox.setAlignment(Pos.BASELINE_CENTER);
      	
      	//Sets styling and spacing for the title
      	titleBox.setPadding(new Insets(INSET_TOP, INSET_RIGHT, INSET_BOTTOM, INSET_LEFT)); 
      	titleBox.setAlignment(Pos.BASELINE_CENTER);
      	titleBox.setStyle("-fx-background-color: #99B898;");
      	titleBox.prefWidthProperty().bind(root.widthProperty());
      	
    	titleBox.getChildren().addAll(title);
 
    	
    	//Assign handlers for the flow pane 
        flow.setOnDragOver(e -> paneSwitch(e));
        flow.setOnDragDropped(e -> {
			try {
				dropInPlot(e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
        

        //Setting scrollpane to bind with the size of the vbox
        sp.setContent(plantBox);
        plantBox.prefHeightProperty().bind(root.widthProperty());
        
        tap.getChildren().addAll(titleBox);
        
        root.setTop(tap);
        root.setRight(ap);
        root.setLeft(sp);
        root.setCenter(flow);
    }
    /**
     * Updates the plot when prompted.
     * @throws Exception
     */
    public void update() throws Exception {
    	flow.getChildren().clear();
    	g.getChildren().clear();
    	Canvas canvas = ViewPlot.getCanvas();
		g.getChildren().add(canvas);
		canvas.toFront();
        int count = 0;
        for(Plant p : plantsInPlot) {
       	

    		ImageView iv2 = newCircle(p);
	
    		if(imp) {
    			System.out.println("imp");
    			iv2.setTranslateX(xplants.get(count));
                iv2.setTranslateY(yplants.get(count));
                p.setX(iv2.getTranslateX());
                p.setY(iv2.getTranslateY());
    		} else {
    			try {
        			iv2.setTranslateX(xplants.get(count));
                    iv2.setTranslateY(yplants.get(count));
                    p.setX(iv2.getTranslateX());
                    p.setY(iv2.getTranslateY());
    			} catch(Exception e) {
    				iv2.setTranslateX(p.getX());
    				iv2.setTranslateY(p.getY());
    			}
    		}
    		imagesInPlot.add(iv2);
            g.getChildren().add(iv2);
            
            ++count;
        }
        imp = false;
		updateLepCount();
		updateBudget();
		flow.getChildren().add(g);
		addPlantsFromBasket();
    }
    
   
    /**
     * Handler for dragging an ImageView when it is already in the plot.
     * @param event MouseEvent
     * @throws Exception 
     */
	public void dragWithinPlot(MouseEvent event) throws Exception {	
		Node n = (Node)event.getSource();
		n.setTranslateX(n.getTranslateX() + event.getX());
		n.setTranslateY(n.getTranslateY() + event.getY());
		
		for(Plant p : plantsInPlot) {
			if(n.getId().equals(p.getCommonName())) {
				p.setX(n.getTranslateX());
				p.setY(n.getTranslateY());
			}
		}
	}
	
	/**
	 * Begins the dragging process by using a dragboard. Also tracks the plants that are in the plot.
	 * @param e MouseEvent
	 */
	public void dragDetect(MouseEvent e) {
		Node n = (Node)e.getSource();
		draggedPlantName = n.getId();
	    Dragboard db = n.startDragAndDrop(TransferMode.COPY);
        db.setDragView(n.snapshot(null, null), e.getX(), e.getY());
        ClipboardContent cc = new ClipboardContent();
        cc.put(DataFormat.IMAGE, n);
        db.setContent(cc);
        e.consume();
	}
	
	/**
	 * Transfers the node from the Box to the FlowPane
	 * @param event DragEvent
	 */
	public void paneSwitch(DragEvent event) {
		event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	}
	
	/**
	 * Drops the image view in the plot. Updates budget and number of leps. 
	 * @param event DragEvent
	 * @throws Exception
	 */
	public void dropInPlot(DragEvent event) throws Exception {
		Plant draggedPlant = null;
		for(Plant p : allPlants) {
			if(p.getCommonName().equals(draggedPlantName)) {
				draggedPlant = p;
				break;
			}
		}
		int i=0;
		for (Plant p : plantsInPlot) {
        	if(p==draggedPlant) {
        		i++;
        	}
		}
		if (i==0) {
			//updateLepCount(draggedPlant.getLep().getNumLeps());
			//draggedPlant.getLep().getNumLeps();
			updateLepCount();
		}
		plantsInPlot.add(draggedPlant);

		//lepsInGarden.add(draggedPlant.getLep());


		ImageView iv2 = newCircle(draggedPlant);
		imagesInPlot.add(iv2);
        g.getChildren().addAll(iv2);
        flow.getChildren().clear();
        flow.getChildren().add(g);
        iv2.setTranslateX(event.getX());
        iv2.setTranslateY(event.getY());
        
        draggedPlant.setX(iv2.getTranslateX());
        draggedPlant.setY(iv2.getTranslateY());
        
        xplants.add(iv2.getTranslateX());
        yplants.add(iv2.getTranslateY());
        
        event.setDropCompleted(true);
        updateBudget();
        updateLepCount();
	}

	
	
	/**
	 * Adds all of the plants from the basket into the side bar VBox
	 * @throws Exception
	 */
	public void addPlantsFromBasket() throws Exception {
		
		plantBox.getChildren().clear();
		allPlants = View.getGarden().getBasket();
		
        for(Plant p : allPlants) {      	
        	Text label = new Text(p.getCommonName());
        	label.setFill(Color.SNOW);
        	plantBox.getChildren().addAll(newNode(p),label);
        }
	}
	
	/**
	 * Tracks number of leps.
	 * @param count Number of leps to be added.
	 * @throws Exception 
	 */
	public void updateLepCount() throws Exception {
		lepCount = 0;
		Model.sortPlants();
    	if(Model.getUniquePlants()==null) {
    		lepLabel.setText("Lep species:	" + lepCount);
    	}
        for(Plant l : Model.getUniquePlants()) {
            lepCount += l.getLep().getNumLeps();
            
        }
		lepLabel.setText("Lep species:	" + lepCount);
	}
	
	/**
	 * Tracks the budget and updates the budget bar.
	 * @param money Cost of individual plant.
	 * @throws Exception 
	 */
	public void updateBudget() throws Exception {
		budget = 0;
		for(Plant p : plantsInPlot) {
			budget += p.getPrice();
		}
		budgetLabel.setText("Cost: " + budget);
		if(budget < View.getGarden().getBudget()) {
			pb.setProgress((double)(budget / (float)View.getGarden().getBudget()));
		} else {
			pb.setProgress(FULL);
		}
		
	}
	
	/**
	 * Getter for the plants in the plot
	 * @return plantsInPlot
	 */
	public static ArrayList<Plant> getPlantsInPlot() {
		return plantsInPlot;
	}
	
	/**
	 * Creates an image view node for the individual plants
	 * @param p Plant to be added
	 * @return iv New ImageView
	 * @throws Exception
	 */
	public ImageView newNode(Plant p) throws Exception {
		ImageView iv = new ImageView();
		iv.setId(p.getCommonName());
		iv.setPreserveRatio(true);
    	iv.setFitHeight(PIC_HEIGHT);
    	iv.setImage(p.getPlantPic());
    	iv.setOnDragDetected(e -> dragDetect(e));
    	return iv;
	}
	
	/**
	 * Creates a new image view that is a circle of the plant pic within the plot.
	 * @param p Plant
	 * @return iv ImageView of movable plant.
	 * @throws Exception
	 */
	public ImageView newCircle(Plant p) throws Exception {
		ImageView iv = new ImageView();
		iv.setId(p.getCommonName());
		iv.setPreserveRatio(true);
    	iv.setFitHeight((double)((p.getSpread() * 500) / ViewPlot.getLengthOfGarden()));
    	iv.setImage(p.getPlantPic());
		iv.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
			public void handle(ContextMenuEvent event) {
				cm.show(iv, event.getScreenX(), event.getScreenY());		
			}
		});
		
		mi.setOnAction(e-> {
			imagesInPlot.remove(cm.getOwnerNode());
			for(Plant plant : plantsInPlot) {
				try {
					if(cm.getOwnerNode().getId().equals(p.getCommonName())) {
						plantsInPlot.remove(plant);
						updateBudget();
						updateLepCount();
						break;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}		
			g.getChildren().remove(cm.getOwnerNode());
		});
    	iv.setOnMouseDragged(event -> {
			try {
				dragWithinPlot(event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
    	return iv;
	}
	/**
	 * Exports the current garden
	 * @throws Exception
	 */
	public void export() throws Exception {
		ArrayList<Plant> outputList = new ArrayList<Plant>();
		String path = View.saveChoose();
		FileWriter myWriter = new FileWriter(path + "plantlist.txt");
	    myWriter.write("Plant Shopping List\n-----------------------------\n");
	    for(Plant plant : plantsInPlot) {
	    	if(outputList.contains(plant)) {
	    		plant.setCount(plant.getCount() + 1);
	    	} else {
	    		plant.setCount(1);
	    		outputList.add(plant);
	    	}
	    }
	    
	    for(Plant plant : outputList) {
	    	myWriter.write("x" + plant.getCount() + " " + plant.getScientificName() + " also known as: " + 
	    plant.getCommonName() + "\n");
	    	
	    }
	    myWriter.write("-----------------------------\nLand 4 Leps 2021");
	    myWriter.close();

        FileOutputStream fos = new FileOutputStream(path + "mygarden.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        ArrayList<String> plist = new ArrayList<String>();
        ArrayList<String> plotPlants = new ArrayList<String>();
        ArrayList<Double> xplants = new ArrayList<Double>();
        ArrayList<Double> yplants = new ArrayList<Double>();
        Integer len = ViewPlot.getLengthOfGarden();
        for (Plant p : allPlants) {
        	plist.add(p.getCommonName());
        }
        for (Plant p : plantsInPlot) {
        	plotPlants.add(p.getCommonName());
        }  
        for (ImageView iv : imagesInPlot) {
        	xplants.add(iv.getTranslateX());
        	yplants.add(iv.getTranslateY());
        }    
        oos.writeObject(plotPlants);
        oos.writeObject(xplants);
        oos.writeObject(yplants);
        oos.writeObject(plist);
        oos.writeObject(len);
        oos.writeObject(ViewPlot.getStartX());
        oos.writeObject(ViewPlot.getStartY());
        oos.writeObject(ViewPlot.getXlist());
        oos.writeObject(ViewPlot.getYlist());
        oos.close();
        fos.close();      
	}
	
	/**
	 * Imports a previous garden
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void importFromDisk(String path) throws Exception {
		imp = true;
		FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        plotPlants = (ArrayList<String>) ois.readObject();
        xplants = (ArrayList<Double>) ois.readObject();
        yplants = (ArrayList<Double>) ois.readObject();
        plist = (ArrayList<String>) ois.readObject();
        Integer len = (int) ois.readObject();
        startx = (double) ois.readObject();
        starty = (double) ois.readObject();
        xlist = (ArrayList<Double>) ois.readObject();
        ylist = (ArrayList<Double>) ois.readObject();
        ois.close();
        
        ViewPlot.setLengthOfGarden(len);
        for (String s : plist) {
        	for(Plant p : View.getGarden().getPlantAL()) {
                if(p.getCommonName().equals(s)) {
                	View.getGarden().addToBasket(p);
                	break;
                }
            }      	
        }
        
        ViewPlot.setStartX(startx);
        ViewPlot.setStartY(starty);
        ViewPlot.setXlist(xlist);
        ViewPlot.setYlist(ylist);
        ViewPlot.redraw();
           
        for (String s : plotPlants) {
        	for(Plant p : View.getGarden().getPlantAL()) {
                if(p.getCommonName().equals(s)) {     	
                	plantsInPlot.add(p);
                	break;
                }
            }      	
        }
	}
	
    
}