package pkgMain;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Lets the user use their mouse to draw their plot,
 * clear and restart, and enter in the longest length for scaling.
 * 
 * @author Jaden
 *
 */
public class ViewPlot {
	BorderPane wrapperPane = new BorderPane();
	BorderPane borderPane = new BorderPane();
	AnchorPane ap = new AnchorPane();
	AnchorPane ap2 = new AnchorPane();
	AnchorPane ap3 = new AnchorPane();
	static Canvas canvas = new Canvas();
	private Text title = new Text("Draw Your Garden");
	//private Text longestLength = new Text("Enter Longest Length");
	TextField longestLengthInput = new TextField();
	Button enter = new Button("Enter");
	public static int lengthOfGarden;
	private Text feet = new Text("Ft.");
	private VBox vbox = new VBox();
	private VBox vbox2 = new VBox();
	private HBox hbox = new HBox();
	private final int HBOX_INSET1 = 12;
	private final int HBOX_INSET2 = 10;
	private final int HBOX_SPACING = 10;
	private final int HBOX2_INSET = 15;
	private final int VBOX_WIDTH = 150;
//	private final double ANCHOR_SUN_TEXT = 300.0;
//	private final double ANCHOR_SOIL_TEXT = 320.0;
//	private final double ANCHOR_MOIST_TEXT = 340.0;
//	private final double ANCHOR_BUDGET_TEXT = 360.0;
	private final double ANCHOR_TOP_INPUT = 200.0;
	//private final double ANCHOR_TOP_LENGTH = 5.0;
	private final double ANCHOR_FEET_TEXT = 225.0;
	private final double L_ANCHOR_FEET_TEXT = 130.0;
	private final double ANCHOR_ENTER_BUTTON = 240.0;
	private final double L_ANCHOR_ENTER_BUTTON = 10.0;
	private final double T_ANCHOR_CLEAR_BUTTON = 220.0;
	private final double L_ANCHOR_CLEAR_BUTTON = 45.0;
	private final double L_ANCHOR_LENGTH = 10;
	private final static int STROKE_WIDTH = 2;
	private final int CLEAR_RECT = 0;
	private final int CLEAR_BUTTON_SIZE = 75;
	public Button clear = new Button("Clear");
	public Scene plot;
	private static EventHandler<MouseEvent> start;
	private static EventHandler<MouseEvent> draw;
	private static ArrayList<Double> xlist = new ArrayList<Double>();
	private static ArrayList<Double> ylist = new ArrayList<Double>();
	private static double startx;
	private static double starty;
    final static GraphicsContext gc = canvas.getGraphicsContext2D();
   
    public ViewPlot() {
    	plot = new Scene(borderPane, View.WIDTH, View.HEIGHT);
    	drawPlot(canvas);
    }
    
    /**
	 * Sets up the scene and UI for the garden drawing screen
	 * @param canvas Canvas
	 */
	public void drawPlot(Canvas canvas) {    
		Text drawText = new Text("Use your mouse to draw in the space below. Once you are done, enter the longest length of your garden on the right!");
		HBox drawHereBox = new HBox();
		
		//Set Styles
		ap.setStyle("-fx-background-color: #99B898;"); //Top AnchorPane
		ap2.setStyle("-fx-background-color: #2A363B;");
		ap3.setStyle("-fx-background-color: #2A363B;");
		drawHereBox.setStyle("-fx-background-color: #2A363B;");
		
		//Set Fonts
      	title.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Black.ttf"), 25)); //Title
      	clear.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Black.ttf"), 15)); //Clear Button
      	enter.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Black.ttf"), 15)); //Enter Button
      	drawText.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Italic.ttf"), 15));
      	
      	//Button Sizes
      	clear.setPrefSize(CLEAR_BUTTON_SIZE, CLEAR_BUTTON_SIZE);
      	
      	//Set Fill
      	title.setFill(Color.web("2A363B")); //Title
      	feet.setFill(Color.SNOW);
    	drawText.setFill(Color.SNOW);
      	
		//Sets Top Bar
      	hbox.setPadding(new Insets(HBOX_INSET1, HBOX_INSET2, HBOX_INSET1, HBOX_INSET2));
      	hbox.setSpacing(HBOX_SPACING); 
      	hbox.setAlignment(Pos.BASELINE_CENTER);
      	
      	//Set side bar VBox
      	vbox.setPrefWidth(VBOX_WIDTH);
      	vbox2.setPrefWidth(VBOX_WIDTH);
      	longestLengthInput.setPrefWidth(120);
		
      	//Setting parts of BorderPane
    	borderPane.setTop(ap);
    	borderPane.setLeft(ap2);
    	borderPane.setRight(ap3);
		borderPane.setCenter(wrapperPane);
		wrapperPane.setTop(drawHereBox);
		
		//Adding Children
		borderPane.getChildren().add(canvas);
		wrapperPane.getChildren().add(canvas);
		hbox.getChildren().add(title);
		drawHereBox.getChildren().addAll(drawText);     
		ap.getChildren().add(hbox);
		ap2.getChildren().addAll(vbox2,clear);
		ap3.getChildren().addAll(vbox,longestLengthInput,feet,enter);
      	
      	drawHereBox.setPadding(new Insets(HBOX2_INSET, HBOX_INSET1, HBOX2_INSET, HBOX_INSET1));
        drawHereBox.setAlignment(Pos.BASELINE_CENTER);
		
		//Sets width property
		hbox.prefWidthProperty().bind(borderPane.widthProperty());
		canvas.widthProperty().bind(wrapperPane.widthProperty());
		canvas.heightProperty().bind(wrapperPane.heightProperty());
		drawHereBox.prefWidthProperty().bind(wrapperPane.widthProperty());
		
		//Puts Text/Button/TextField in place
        AnchorPane.setTopAnchor(longestLengthInput, ANCHOR_TOP_INPUT);
        AnchorPane.setTopAnchor(feet, ANCHOR_FEET_TEXT);
        AnchorPane.setLeftAnchor(feet, L_ANCHOR_FEET_TEXT);
        AnchorPane.setTopAnchor(clear,T_ANCHOR_CLEAR_BUTTON);
        AnchorPane.setLeftAnchor(clear, L_ANCHOR_CLEAR_BUTTON);
        AnchorPane.setTopAnchor(enter, ANCHOR_ENTER_BUTTON);
        AnchorPane.setLeftAnchor(enter, L_ANCHOR_ENTER_BUTTON);
        AnchorPane.setLeftAnchor(longestLengthInput, L_ANCHOR_LENGTH);
        
        //Drawing Handlers  
        //Mouse Press Handler
        /**
         * Handler for starting the drawing with clicking the mouse.
         * @param event MouseEvent
         * 
         */
        start = (new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	//Enables the TextField again
            	longestLengthInput.setEditable(true);
            	gc.setStroke(Color.BLACK);
     	        gc.setLineWidth(STROKE_WIDTH);
                gc.beginPath();
                startx = event.getX();
                starty = event.getY();
                gc.moveTo(event.getX(), event.getY());
                gc.stroke();
            }
         });
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, start);
        
        //Mouse Drag Handler
        /**
         * Handler for drawing on the plot while the mouse is pressed and dragged.
         * @param event MouseEvent
         * 
         */
        draw = (new EventHandler<MouseEvent>(){
        	@Override
        	public void handle(MouseEvent event) {
        		xlist.add(event.getX());
                ylist.add(event.getY());
        		gc.lineTo(event.getX(), event.getY());
        		gc.stroke();
        	}
        	
        });
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, draw);
        
        //Only allows the user to type in numerical value in text box
        longestLengthInput.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*")) 
                return null;
            else
                return c;
            }
        ));
        
        //Clears canvas with Clear button
        clear.setOnAction(e ->  gc.clearRect(CLEAR_RECT, CLEAR_RECT, gc.getCanvas().getWidth(), gc.getCanvas().getHeight()));
        
        //Cursor Changes
        clear.setCursor(Cursor.HAND);
        enter.setCursor(Cursor.HAND);
        //ViewPlot.getCanvas().setCursor(Cursor.CROSSHAIR);
        
        //Changes text back to Enter when you click in the TextBox
        longestLengthInput.setOnMouseClicked(e -> enter.setText("Enter"));
        
        //Switches text of enter button and stores input value
        enter.setOnAction(e -> {
        	enter.setText("Set");
        	System.out.println(xlist);
        	System.out.println(ylist);
        	//longestLengthInput.setEditable(false);
        	lengthOfGarden = Integer.parseInt(longestLengthInput.getText());
        });   
	}
	
	/**
     * Redraws the exact plot that the user draws.
     * 
     * 
     */
	public static void redraw() {
        gc.setStroke(Color.BLUE);
	    gc.setLineWidth(STROKE_WIDTH);
        gc.beginPath();
        gc.moveTo(startx, starty);
        gc.stroke();
    	
        Iterator<Double> it1 = xlist.iterator();
        Iterator<Double> it2 = ylist.iterator();
        while(it1.hasNext() && it2.hasNext()) {
        	gc.lineTo(it1.next(), it2.next());
   			gc.stroke();
        }
        
	}
	
	//Getter to return the canvas
	 public static Canvas getCanvas() {
	    return canvas;	
	 }
	 
	 public static ArrayList<Double> getXlist() {
		return xlist;
	 }
	 
	 public static void setCanvas(Canvas c) {
		 canvas = c;
	 }
	 
	 public static ArrayList<Double> getYlist() {
		return ylist;
	 }
	 
	 public static double getStartX() {
		return startx;
	 }
	 
	 public static double getStartY() {
		return starty;
	 }
	 
	 public static void setXlist(ArrayList<Double> x) {
		xlist = x;
	 }
	 
	 
	 public static void setYlist(ArrayList<Double> y) {
		ylist = y;
	 }
	 
	 public static void setStartX(double x) {
		startx = x;
	 }
	 
	 public static void setStartY(double y) {
		starty = y;
	 }
	 
	 
	 public static int getLengthOfGarden() {
		 return lengthOfGarden;
	 }
	 
	 public static void setLengthOfGarden(int len) {
		 lengthOfGarden = len;
	 }


}