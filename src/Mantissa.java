import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Mantissa extends Application{

	// Data for decimal converter table

	private ObservableList<DecimalData> decimalData = FXCollections.observableArrayList();;
	Logic dataHandler;
	TableView<DecimalData> decimalTable;

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



		// add table to the middle container
		createDecimalTable();
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
			try {
				double num = Double.parseDouble(userInput.getText());
				handleContent(num);
			} catch(Exception ex) {
				System.out.println("Invalid Number + " + ex.getMessage());
			}
		});

	}

	void handleContent(double num) {

		// delete the existing data from the table
		decimalData.clear();

		dataHandler =  new Logic(num);
		dataHandler.generate();

		// handle data for decimal conversion
		int dTableSize = dataHandler.dgetTableSize();
		HashMap<Integer, Integer> dColumn1 = dataHandler.dgetColumn1();
		HashMap<Integer, Integer> dColumn2 = dataHandler.dgetColumn2();
		HashMap<Integer, ArrayList<Integer>> dColumn4 = dataHandler.dgetColumn4();

		System.out.println("JUST VHRVKIN: " + dTableSize);

		for(Integer i=1; i<=dTableSize; i++) {
			decimalData.add(new DecimalData(dColumn1.get(i),
					dColumn2.get(i), 
					dColumn4.get(i).get(0), 
					dColumn4.get(i))
					);
		}

	}

	/*=============================================================
	 * These blocks are just for initializing components
	 *============================================================= */
	void createDecimalTable() {
		// set up table view to display content
		decimalTable = new TableView<DecimalData>(decimalData);		

		/* Number  |  Quotient   | Remainder  |  Bit set
		 * ---------------------------------------------
		 *  90     |     45      |    0       |   {0}
		 *  45     |     22      |    1       |   {1, 0} 
		 *  ....
		 * */

		// add table columns
		TableColumn<DecimalData, Integer> numCol = new TableColumn<DecimalData, Integer>("Number");
		TableColumn<DecimalData, Integer> quotientCol= new TableColumn<>("Quotient");
		TableColumn<DecimalData, Integer> remainderCol = new TableColumn<>("Remainder");
		TableColumn<DecimalData, String> bitSet = new TableColumn<>("Bit Set");		

		numCol.setSortable(false); 
		quotientCol.setSortable(false);
		remainderCol.setSortable(false);
		bitSet.setSortable(false);

		// bind data to columns
		numCol.setCellValueFactory(new Callback<CellDataFeatures<DecimalData,Integer>, ObservableValue<Integer>>() {
			@Override
			public ObservableValue<Integer> call(CellDataFeatures<DecimalData, Integer> param) {
				// TODO Auto-generated method stub
				return new ReadOnlyObjectWrapper<>(param.getValue().getDivident());
			}
		});

		quotientCol.setCellValueFactory(new Callback<CellDataFeatures<DecimalData,Integer>, ObservableValue<Integer>>() {
			@Override
			public ObservableValue<Integer> call(CellDataFeatures<DecimalData, Integer> param) {
				// TODO Auto-generated method stub
				return new ReadOnlyObjectWrapper<>(param.getValue().getQuotient());
			}
		});

		remainderCol.setCellValueFactory(new Callback<CellDataFeatures<DecimalData,Integer>, ObservableValue<Integer>>() {
			@Override
			public ObservableValue<Integer> call(CellDataFeatures<DecimalData, Integer> param) {
				// TODO Auto-generated method stub
				return new ReadOnlyObjectWrapper<>(param.getValue().getRemainder());
			}
		});

		bitSet.setCellValueFactory(new Callback<CellDataFeatures<DecimalData,String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<DecimalData, String> param) {
				// TODO Auto-generated method stub
				return new ReadOnlyObjectWrapper<>(param.getValue().getRemainderSet());
			}
		});


		decimalTable.getColumns().addAll(numCol, quotientCol, remainderCol, bitSet);
	}

	class DecimalData {
		//		int divident;
		//		int quotient;
		//		int remainder;
		//		ArrayList<Integer> remainderSet;

		// properties
		IntegerProperty divident;
		IntegerProperty quotient;
		IntegerProperty remainder;
		StringProperty remainderSet;

		DecimalData(int divident, int quotient, int remainder, ArrayList<Integer> remainderSet) {
			this.divident = new SimpleIntegerProperty(divident);
			this.quotient = new SimpleIntegerProperty(quotient);
			this.remainder = new SimpleIntegerProperty(remainder);
			this.remainderSet = new SimpleStringProperty(remainderSet.toString());
		}

		// FOR DIVIDENT
		public int getDivident() {
			return divident.get();
		}

		public void setDivident(int divident) {
			this.divident.set(divident);;
		}

		public IntegerProperty dividentProperty() {
			return divident;
		}

		// FOR quotient
		public int getQuotient() {
			return quotient.get();
		}

		public IntegerProperty quotientProperty() {
			return quotient;
		}

		public void setQuotient(int quotient) {
			this.quotient.set(quotient);
		}

		// for remainder
		public int getRemainder() {
			return remainder.get();
		}

		public void setRemainder(int remainder) {
			this.remainder.set(remainder);;
		}

		public IntegerProperty remainderProperty() {
			return remainder;
		}

		// for remainderset
		public StringProperty remainderSet() {
			return remainderSet;
		}

		public String getRemainderSet() {
			return remainderSet.get();
		}

		public void setRemainder(ArrayList<Integer> remainderSet) {
			this.remainderSet.set(remainderSet.toString());
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logic log = new Logic(273.15);
		log.generate();

		launch(args);
	}



}
