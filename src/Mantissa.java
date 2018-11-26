import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Mantissa extends Application{
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		Text historyLabel;
		
		BorderPane root = new BorderPane();
		
		/*=======================================================
		 * For the Input Container
		 *======================================================= */
		HBox inputContainer = new HBox();
		// add css
		inputContainer.getStyleClass().add("hbox");
		
		// input field
		TextField userInput = new TextField();
		userInput.setId("mainInput");
		userInput.setPromptText("Number");

		// add textFormatter so that only numbers are entered
		Button confirmButton = new Button("Compute");
		
		// nodes in the top container
		inputContainer.getChildren().addAll(userInput, confirmButton);
		
		
		/*=======================================================
		 * For left container (history)
		 *======================================================= */
		
		VBox historyContainer = new VBox();
		historyContainer.getStyleClass().add("vbox");
		historyContainer.setPadding(new Insets(10)); // Set all sides to 10
        historyContainer.setSpacing(8);              // Gap between nodes
		
		//label for history box
		historyLabel = new Text("History");
		historyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		
		// add history elements to history container
		historyContainer.getChildren().addAll(historyLabel);
		
		/*=======================================================
		 * For middle container (process)
		 *======================================================= */
		VBox middleContainer = new VBox();
		middleContainer.getStyleClass().add("vbox-middle");
		
		/*
		 *  Add layouts to root
		 * */
		root.setTop(inputContainer);
		root.setLeft(historyContainer);
		root.setCenter(middleContainer);
		
		// set up table view to display content
		TableView decimalTable = new TableView<>();
		
		/* Number  |  Quotient   | Remainder  |  Bit set
		 * ---------------------------------------------
		 *  90     |     45      |    0       |   {0}
		 *  45     |     22      |    1       |   {1, 0} 
		 *  ....
		 * */
		
		// add table columns
		TableColumn numCol = new TableColumn<>("Number");
		TableColumn quotientCol= new TableColumn<>("Quotient");
		TableColumn remainderCol = new TableColumn<>("Remainder");
		TableColumn bitSet = new TableColumn<>("Bit Set");
		
		decimalTable.getColumns().addAll(numCol, quotientCol, remainderCol, bitSet);
		
		// add table to the middle container
		middleContainer.getChildren().add(decimalTable);
		
		
		Scene scene = new Scene(root, 800, 500);
		// add css
		scene.getStylesheets().add("main.css");
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		/*=======================================================
		 * All components are added. Now add event handlers
		 *======================================================= */
		confirmButton.setOnAction( e -> {
			System.out.println();
			try {
				double num = Double.parseDouble(userInput.getText());
			} catch(Exception ex) {
				System.out.println("Invalid Number");
			}
		});
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Logic log = new Logic(273.15);
		log.generate();
		
		launch(args);
		
	}


}
