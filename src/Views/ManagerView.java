package Views;

import java.awt.TextArea;
import java.io.File;
import java.util.ArrayList;

import Controllers.ManagerController;
import Models.Author;
import Models.Book;
import UI.CreateButton;
import UI.DeleteButton;
import UI.EditButton;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import Models.Book;
public class ManagerView extends View{

	private static  ArrayList<Book> books = Book.getBooks();
	public static final String FILE_PATH = "books.ser";
	public static final File DATA_FILE = new File(FILE_PATH);
    private final BorderPane borderPane = new BorderPane();
    private final TableView<Book> tableView = new TableView<>();
    private final HBox formPane = new HBox();
    private final TextField isbnField = new TextField();
    private final TextField titleField = new TextField();
    private final TextField purchasedPriceField = new TextField();
    private final TextField sellingPriceField = new TextField();
    private final ComboBox<Author> authorsComboBox = new ComboBox<>();
    private final TextField quantityField = new TextField();
    private final TextField categoryField = new TextField();
    private final Button saveBtn = new CreateButton();
    private final Button deleteBtn = new DeleteButton();
    private final Button editBtn = new EditButton();
    private final TableColumn<Book, String> isbnCol = new TableColumn<>("ISBN");
    private final TableColumn<Book, String> titleCol = new TableColumn<>("Title");
    private final TableColumn<Book, Float> purchasedPriceCol = new TableColumn<>("Purchased Price");
    private final TableColumn<Book, Float> sellingPriceCol = new TableColumn<>("Selling Price");
    private final TableColumn<Book, String> authorCol = new TableColumn<>("Author");
    private final TableColumn<Book, Integer> quantityCol = new TableColumn<>("Quantity");
    private final TableColumn<Book, String> categoryCol = new TableColumn<>("Category");
    private final Label resultLabel = new Label("");
    private final SearchView searchView = new SearchView("Search for a book");
	private TextField problematic;

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

    public ComboBox<Author> getAuthorsComboBox() {
        return authorsComboBox;
    }
    
    public TextField getQuantityField() {
    	return quantityField;
    }
    
    public TextField getCategoryField() {
    	return categoryField;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public Button getEditBtn() {
        return editBtn;
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
   public TableColumn<Book,Integer> getQuantityCol(){
	   return quantityCol;
   }
   public TableColumn<Book,String> getCategoryCol(){
	   return categoryCol;
   }

    public Label getResultLabel() {
        return resultLabel;
    }

    public SearchView getSearchView() {
        return searchView;
    }

  public ManagerView() {
        setTableView();
        setForm();
        // inject the controller
        new ManagerController(this);
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
        Label authorLabel = new Label("Author", authorsComboBox);
        authorsComboBox.getItems().setAll(Author.getAuthors());
        Label quantityLabel= new Label("Quantity",quantityField);
        quantityLabel.setContentDisplay(ContentDisplay.TOP);
        Label categoryLabel= new Label("Category",categoryField);
        categoryLabel.setContentDisplay(ContentDisplay.TOP);
        // set default selected the first author
        if (!Author.getAuthors().isEmpty())
            authorsComboBox.setValue(Author.getAuthors().get(0));
        authorLabel.setContentDisplay(ContentDisplay.TOP);
        formPane.getChildren().addAll(isbnLabel, titleLabel, purchasedPriceLabel, sellingPriceLabel,
                                        authorLabel,quantityLabel,categoryLabel, saveBtn, deleteBtn, editBtn);
    }

    private void setTableView() {
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setEditable(true);
        tableView.setItems(FXCollections.observableArrayList(Book.getBooks()));

        isbnCol.setCellValueFactory(
                new PropertyValueFactory<>("isbn")
        );
        // to edit the value inside the table view
        isbnCol.setCellFactory(TextFieldTableCell.forTableColumn());

        titleCol.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());

        purchasedPriceCol.setCellValueFactory(
                new PropertyValueFactory<>("purchasedPrice")
        );
        purchasedPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        sellingPriceCol.setCellValueFactory(
                new PropertyValueFactory<>("sellingPrice")
        );
        sellingPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        authorCol.setCellValueFactory(
                new PropertyValueFactory<>("author")
        );
  

        
        //authorCol.setCellFactory(ComboBoxTableCell.forTableColumn(showNames));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.getColumns().addAll(isbnCol, titleCol, purchasedPriceCol, sellingPriceCol,quantityCol, authorCol,categoryCol);
    }


    @Override
    public Parent getView() {
        borderPane.setCenter(tableView);
        VBox vBox = new VBox();
        HBox hBox= new HBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(5);
        vBox.getChildren().addAll(formPane, resultLabel);
        borderPane.setBottom(vBox);
        //borderPane.setTop(searchView.getSearchPane());
        Button stock= new Button("Stock Situation");
        stock.setStyle("-fx-text-weight:BOLD");
        hBox.getChildren().add(stock);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(searchView.getSearchPane());
        borderPane.setTop(hBox);

        VBox insertPane= new VBox(20);
        for(int i=0;i<books.size();i++) {
			if(books.get(i).getQuantity()<5)
			{
        		Label problematic= new Label((i+1)+"."+"The book "+books.get(i).getTitle()+" is low on stock!");
        		insertPane.getChildren().add(problematic);
        }}
        
        stock.setOnAction(e->{
        	 Stage random= new Stage();
             Scene scene= new Scene(insertPane,400,400);
             random.setTitle("Alert");
             random.setScene(scene);
             random.show();
        });
       
        BackgroundImage myBI= new BackgroundImage(new Image("https://wattention.com/wp-content/uploads/2018/04/Books-and-coffee.jpg",300,300,false,true),
    	        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
    	          BackgroundSize.DEFAULT);
        borderPane.setBackground(new Background(myBI));
        
        return borderPane;
    }

}
