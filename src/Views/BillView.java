package Views;

import java.io.File;
import java.util.ArrayList;

import Controllers.LibController;
import Models.Author;
import Models.Book;
import UI.BillButton;
import UI.ChoiceButton;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class BillView { private static final ArrayList<Author> authors = new ArrayList<>();
private static  ArrayList<Author> prove = new ArrayList<>();
private static  ArrayList<Book> orderedBooks= new ArrayList<>();
public static final String FILE_PATH = "authors.ser";
public static final File DATA_FILE = new File(FILE_PATH);
private final BorderPane borderPane = new BorderPane();
private final TableView<Book> tableView = new TableView<>();
private final HBox formPane = new HBox();
private final TextField isbnField = new TextField();
private final TextField titleField = new TextField();
private final TextField purchasedPriceField = new TextField();
private final TextField sellingPriceField = new TextField();
private final TextField categoryField= new TextField();
private final TextField quantityField= new TextField();
private final Button BillButton = new BillButton();
private final Button choiceButton= new ChoiceButton();
private final TableColumn<Book, String> isbnCol = new TableColumn<>("ISBN");
private final TableColumn<Book, String> titleCol = new TableColumn<>("Title");
private final TableColumn<Book, Float> purchasedPriceCol = new TableColumn<>("Purchased Price");
private final TableColumn<Book, Float> sellingPriceCol = new TableColumn<>("Selling Price");
private final TableColumn<Book, String> authorCol = new TableColumn<>("Author");
private final TableColumn<Book, String> categoryCol = new TableColumn<>("Category");
private final TableColumn<Book, Integer> quantityCol = new TableColumn<>("Quantity");
TableColumn<Book, String> colBtn = new TableColumn("AddToBill");
public TextField getCategoryField() {
	return categoryField;
}

public TextField getQuantityField() {
	return quantityField;
}

public TableColumn<Book, String> getCategoryCol() {
	return categoryCol;
}

public TableColumn<Book, Integer> getQuantityCol() {
	return quantityCol;
}
private final Label resultLabel = new Label("");
private final SearchView searchView = new SearchView("Search for a book");

public TableView<Book> getTableView() {
    return tableView;
}

public TextField getIsbnField() {
    return isbnField;
}

public TextField getTitleField() {
    return titleField;
}

public TextField getPurchasedPriceField() {
    return purchasedPriceField;
}

public TextField getSellingPriceField() {
    return sellingPriceField;
}

public TableColumn<Book, String> getIsbnCol() {
    return isbnCol;
}

public TableColumn<Book, String> getTitleCol() {
    return titleCol;
}

public TableColumn<Book, Float> getPurchasedPriceCol() {
    return purchasedPriceCol;
}

public TableColumn<Book, Float> getSellingPriceCol() {
    return sellingPriceCol;
}

public Label getResultLabel() {
    return resultLabel;
}

public SearchView getSearchView() {
    return searchView;
}
public Button getBillBtn() {
    return BillButton;
}

public BillView() {
    setTableView();
    setForm();
    //new LibController(this);
}

private void setForm() {
    formPane.setPadding(new Insets(20));
    formPane.setSpacing(20);
    formPane.setAlignment(Pos.CENTER);
    Label isbnLabel = new Label("ISBN: ", isbnField);
    isbnLabel.setContentDisplay(ContentDisplay.TOP);
    Label titleLabel = new Label("Title: ", titleField);
    titleLabel.setContentDisplay(ContentDisplay.TOP);
    Label purchasedPriceLabel = new Label("Purchased price", purchasedPriceField);
    purchasedPriceLabel.setContentDisplay(ContentDisplay.TOP);
    Label sellingPriceLabel = new Label("Selling price", sellingPriceField);
    sellingPriceLabel.setContentDisplay(ContentDisplay.TOP);
    Label categoryLabel= new Label("Category: ",categoryField);
    categoryLabel.setContentDisplay(ContentDisplay.TOP);
    formPane.getChildren().addAll(BillButton);
    

    }

private void setTableView() {
    tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    tableView.setEditable(true);
    
    //tableView.setItems((ObservableList<Book>) new ChoiceButton());

    isbnCol.setCellValueFactory(
            new PropertyValueFactory<>("isbn")
    );
    // to edit the value inside the table view
    isbnCol.setCellFactory(TextFieldTableCell.forTableColumn());

    titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
    titleCol.setCellFactory(TextFieldTableCell.forTableColumn());

    purchasedPriceCol.setCellValueFactory(new PropertyValueFactory<>("purchasedPrice"));
    purchasedPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

    sellingPriceCol.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
    sellingPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

    authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
    quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    quantityCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
    categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
    prove= Author.getAuthors();
    String provNames;
    String[] showNames= new String[prove.size()];
    for(int i=0;i<prove.size();i++) {
    	provNames= prove.get(i).getFullName();
    	showNames[i]= provNames;
    }
    
    authorCol.setCellFactory(ComboBoxTableCell.forTableColumn(showNames));        

Callback<TableColumn<Book, String>, TableCell<Book, String>> cellFactory = new Callback<TableColumn<Book, String>, TableCell<Book, String>>() {
    	
    	@Override
        public TableCell<Book,String> call(final TableColumn<Book, String> param) {
            final TableCell<Book, String> cell = new TableCell<Book, String>() {
            	final Button choiceButton= new ChoiceButton();
               

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //System.out.println(empty);
                    
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(choiceButton);
                        choiceButton.setOnAction(e->{
                            Book data = getTableView().getItems().get(getIndex());
                            //System.out.println("selectedData: " + data.toString());
                            getOrderedBooks().add(data);
                            
                            
                        });
                        
                    }
                }
            };
            return cell;
        } 
    	
    };
    colBtn.setCellFactory(cellFactory);
   
    tableView.setItems(FXCollections.observableArrayList(Book.getBooks()));
   
    
    tableView.getColumns().addAll(isbnCol, titleCol, purchasedPriceCol, sellingPriceCol, authorCol,quantityCol,categoryCol);
    tableView.getColumns().add(colBtn);
}
public Parent getView() {
    borderPane.setCenter(tableView);
    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    vBox.setSpacing(5);
    vBox.getChildren().addAll(formPane, resultLabel);
    borderPane.setBottom(vBox);
    borderPane.setTop(searchView.getSearchPane());
    return borderPane;
}

public static ArrayList<Book> getOrderedBooks() {
	return orderedBooks;
}

public static void setOrderedBooks(ArrayList<Book> orderedBooks) {
	//LibView.orderedBooks = orderedBooks;
}


    
}