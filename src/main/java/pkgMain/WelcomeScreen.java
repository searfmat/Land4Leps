package pkgMain;


import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;


public class WelcomeScreen {
    Image softwareLogo; 
    Image infoButtonImg;
    Button startButton = new Button("Import Garden");
    Scene welcomeScene;
    StackPane root;
    Button infoButton= new Button("Info"); 
    
    public final int X_ORIGIN=10;
    public final int Y_ORIGIN=10;
    public final int WIDTH=1000;
    public final int iconSize = 16;
    /**
     * Creates welcome screen with Software logo 
     */
    public WelcomeScreen() throws Exception {
    	startButton.setStyle("-fx-background-color: lightsalmon; -fx-text-fill: white;");
    	infoButton.setStyle("-fx-background-color: lightsalmon; -fx-text-fill: white;");
    	
    	//Image infoButtonImg= new Image(getClass().getResourceAsStream("/img/infobutton.png"));;
        Image softwareLogo = new Image(getClass().getResourceAsStream("/img/welcome_screen.png"));;

        ImageView imageView= new ImageView();
        imageView.setImage(softwareLogo);

        ImageView infoIcon=new ImageView(new Image(getClass().getResourceAsStream("/img/infobutton.png")));
        infoIcon.setFitHeight(iconSize);
        infoIcon.setFitWidth(iconSize);
        infoButton.setGraphic(infoIcon);
        
        ImageView importIcon=new ImageView(new Image(getClass().getResourceAsStream("/img/import2.png")));
        importIcon.setFitHeight(iconSize);
        importIcon.setFitWidth(iconSize);
        startButton.setGraphic(importIcon);
        //infoButton.setAlignment()
        
        
        imageView.setX(X_ORIGIN);
        imageView.setY(Y_ORIGIN);
        imageView.setFitWidth(WIDTH);
        imageView.setPreserveRatio(true);
        root= new StackPane(imageView);
    	welcomeScene = new Scene(root, View.WIDTH, View.HEIGHT);
    	begin();
    }
    /**
     * Creates button to import previous gardens from computer
     * 
     * @throws Exception
     */
   
    
    public void begin() throws Exception {
      
         
        AnchorPane ap= new AnchorPane();
        ap.getChildren().addAll(startButton); //button left to text
        AnchorPane.setTopAnchor(startButton, 20d);
        AnchorPane.setRightAnchor(startButton, View.WIDTH / 1.25);
        AnchorPane.setLeftAnchor(startButton, View.WIDTH / 1.25);
        ap.prefWidthProperty().bind(root.widthProperty());
        startButton.setAlignment(Pos.BASELINE_CENTER);
        //startButton.setStyle("-fx-background-color: #E84A5F; -fx-text-fill: white;");
        
        AnchorPane ap2= new AnchorPane();
        ap.getChildren().addAll(infoButton);
        AnchorPane.setTopAnchor(infoButton, 60d);
        AnchorPane.setRightAnchor(infoButton, View.WIDTH / 1.25);
        AnchorPane.setLeftAnchor(infoButton, View.WIDTH / 1.25);
        ap2.prefWidthProperty().bind(root.widthProperty());
       // AnchorPane.setAlignment(ap2, Pos.BOTTOM_CENTER);
        infoButton.setAlignment(Pos.BOTTOM_CENTER);
        
        Plot p = new Plot();
    	
        startButton.setOnAction(e -> {
			try {
				p.importFromDisk(View.openChoose());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        
        root.setStyle("-fx-background-color: #FFFFFF;");
        root.getChildren().addAll(ap);
        
        infoButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			
    			Image logo=  new Image(getClass().getResourceAsStream("/img/butterfly-on-a-flower-clipart-md.png"));
    			ImageView iv= new ImageView();
    			iv.setImage(logo);
    			iv.setFitHeight(200);
    			iv.setFitWidth(200);
    			iv.setPreserveRatio(true);
    			iv.setTranslateY(-300);
    			
    			Label label1= new Label("Land 4 Leps is an Application which will allow you to create a garden including a wide range of Delaware native plants with a goal to  support as many Lepidoptera (Butterfly and Moth Species) as possible.");
    			label1.setFont(Font.font("Verdana", FontPosture.ITALIC, 32));
    			label1.setWrapText(true);
    			label1.setTranslateX(10);
    			label1.setTranslateY(10);
    			label1.setAlignment(Pos.CENTER);
    			
    			Hyperlink link = new Hyperlink("Visit Mt.Cuba");
    			link.setTranslateY(300);
    		
    			/*final WebView browser= new WebView();
    			final WebEngine webEngine= browser.getEngine();
    			final String url= "https://mtcubacenter.org/";
    			
    			link.setOnAction(new EventHandler<ActionEvent>(){
    				@Override
    				public void handle(ActionEvent e) {
    					webEngine.load(url);
    				}
    			});*/
    				
    			
    			//Label label2= new Label("Visit https://mtcubacenter.org/" );
    			//label2.setFont(Font.font("Verdana", FontPosture.ITALIC, 26));
    			//label2.setTranslateY(300);
    			
    			StackPane secondaryLayout= new StackPane();
    			secondaryLayout.getChildren().addAll(label1, link, iv);
    			
    			Scene secondScene= new Scene(secondaryLayout, 500, 400);
    			
    			//NewWindow(Stage)
    			Stage newWindow = new Stage();
    			newWindow.setTitle("Second Stage");
    			newWindow.setScene(secondScene);
    			//newWindow.sets
    			
    			//Set position of second window, related to primary window
    			newWindow.setX(400);
    			newWindow.setY(200);
    			
    			newWindow.show();
    			
    		}
    	});
    }
        
       
        
    
        
        
    }

    
