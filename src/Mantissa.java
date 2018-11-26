import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Mantissa extends Application{
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
//		BorderPane borderPane = new BorderPane();
//		
//		ToolBar toolbar = new ToolBar();
//		HBox statusbar = new HBox();
//		Node appContent = new AppContentNode();
//		borderPane.setTop(toolbar);
//		borderPane.setCenter(appContent);
//		borderPane.setBottom(statusbar);
		
		BorderPane root = new BorderPane();
		
		/*=======================================================
		 * For the Input Container
		 *======================================================= */
		HBox inputContianer = new HBox();
		// add css
		inputContianer.getStyleClass().add("hbox");
		
		// input field
		TextField userInput = new TextField();
		userInput.setId("mainInput");
		userInput.setPromptText("Number");
		
		//button
		Button confirmButton = new Button("Compute");
		
		// nodes in the top container
		inputContianer.getChildren().addAll(userInput, confirmButton);
		
		
		/*=======================================================
		 * For left container (history)
		 *======================================================= */
		
		VBox historyContainer = new VBox();
		historyContainer.getStyleClass().add("vbox");
		
		//label for history box
		Label historyLabel = new Label("History");
		
		// add historyelements to history contianer
		historyContainer.getChildren().addAll(historyLabel);
		
		/*=======================================================
		 * For Center container (main Content)
		 *======================================================= */
		
		
		/*
		 *  Add layouts to root
		 * */
		root.setTop(inputContianer);
		root.setLeft(historyContainer);
		
		Scene scene = new Scene(root, 600, 300);
		// add css
		scene.getStylesheets().add("main.css");
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Logic log = new Logic(273.15);
		log.generate();
		
		launch(args);
		
	}


}
