package Controllers;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import Auxiliaries.FileHandler;
import Models.Author;
import Models.Book;
import Models.Order;
import UI.CreateButton;
import UI.DeleteButton;
import UI.EditButton;
import Views.BillView;
import Views.LibView;
import javafx.collections.FXCollections;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;


public class LibController {

    private final LibView libView;
    
    public LibController(LibView libView) {
        this.libView = libView;
        setBillListener();
        setSearchListener();
    }
    private void setSearchListener() {
        libView.getSearchView().getClearBtn().setOnAction(e -> {
            libView.getSearchView().getSearchField().setText("");
            libView.getTableView().setItems(FXCollections.observableArrayList(Book.getBooks()));
        });
        libView.getSearchView().getSearchBtn().setOnAction(e -> {
            String searchText = libView.getSearchView().getSearchField().getText();
            ArrayList<Book> searchResults = Book.getSearchBookResults(searchText);
            libView.getTableView().setItems(FXCollections.observableArrayList(searchResults));
        });
    }
    private void setBillListener() {
   
        libView.getBillBtn().setOnAction(e -> 
        {
          	BorderPane pane= new BorderPane();
          	HBox bottomPane= new HBox(10);
          	 Stage billStage= new Stage();
          	 TableColumn<Book,String> titleColm= new TableColumn<>("Title");
          	 TableColumn<Book, String> isbnColm = new TableColumn<>("ISBN");
          	 TableColumn<Book, Float> purchasedPriceColm = new TableColumn<>("Purchased Price");
          	 TableColumn<Book, Float> sellingPriceColm = new TableColumn<>("Selling Price");
          	 TableColumn<Book, String> authorColm = new TableColumn<>("Author");
          	 TableColumn<Book, String> categoryColm = new TableColumn<>("Category");
          
               TableView tableView1= new TableView();
               tableView1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
               tableView1.setEditable(true);
               tableView1.setItems(FXCollections.observableArrayList(LibView.getOrderedBooks()));

              
               VBox labels= new VBox(20);
               titleColm.setCellValueFactory(
                       new PropertyValueFactory<>("title")
               );
               titleColm.setCellFactory(TextFieldTableCell.forTableColumn());
               isbnColm.setCellValueFactory(
                       new PropertyValueFactory<>("isbn")
               );
               // to edit the value inside the table view
               isbnColm.setCellFactory(TextFieldTableCell.forTableColumn());
               purchasedPriceColm.setCellValueFactory(
                       new PropertyValueFactory<>("purchasedPrice")
               );
               purchasedPriceColm.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

               sellingPriceColm.setCellValueFactory(
                       new PropertyValueFactory<>("sellingPrice")
               );
               sellingPriceColm.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
               categoryColm.setCellValueFactory(new PropertyValueFactory<>("category"));
               categoryColm.setCellFactory(TextFieldTableCell.forTableColumn());

               
               billStage.setTitle("Bill Situation");
               for(int i=0;i<libView.getOrderedBooks().size();i++) {
           		String isbn= libView.getOrderedBooks().get(i).getIsbn();
           		String title = libView.getOrderedBooks().get(i).getTitle();
                   float purchasedPrice = libView.getOrderedBooks().get(i).getPurchasedPrice();
                   float sellingPrice = libView.getOrderedBooks().get(i).getSellingPrice();
                   Author author = libView.getOrderedBooks().get(i).getAuthor();
      
                   int quantity=libView.getOrderedBooks().get(i).getQuantity();
                   String category=libView.getOrderedBooks().get(i).getCategory();
                   Book book= new Book(isbn,title,purchasedPrice,sellingPrice,author,quantity,category);
                   
                   
               }
               tableView1.getColumns().addAll(isbnColm,titleColm,sellingPriceColm,categoryColm);
               pane.setCenter(tableView1);
       
               Scene scene= new Scene(pane,300,400);
               billStage.setScene(scene);
               TextField billNumbername= new TextField();
               billNumbername.setPromptText("Insert name and number");
              // pane.setBottom(billNumbername);
               
               //System.out.println(filename);
               
             billStage.show();
             
             Button checkOut= new Button("Finish");
             checkOut.setOnAction(event->{
            	 ArrayList<Order> orders= new ArrayList<>();
            	 PrintWriter writer = null;
				try {
					String filename=billNumbername.getText()+".txt";
					writer = new PrintWriter(filename, "UTF-8");
				} catch (FileNotFoundException | UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	 //writer.println("The first line");
            	 float amountToPay=0;
            	 
            	 for(int i=0;i<libView.getOrderedBooks().size();i++) {
            			String isbn= libView.getOrderedBooks().get(i).getIsbn();
                   		String title = libView.getOrderedBooks().get(i).getTitle();
                        float sellingPrice = libView.getOrderedBooks().get(i).getSellingPrice();
                        amountToPay=amountToPay+sellingPrice;
                        writer.println(isbn+" "+title+" "+" "+sellingPrice);
                        
                        String isbn1=libView.getOrderedBooks().get(i).getIsbn();
                        String title1=libView.getOrderedBooks().get(i).getTitle();
                        float purchasedPrice=libView.getOrderedBooks().get(i).getPurchasedPrice();
                        float sellingPrice1=libView.getOrderedBooks().get(i).getSellingPrice();
                        Author author=libView.getOrderedBooks().get(i).getAuthor();
                        int quantity=libView.getOrderedBooks().get(i).getQuantity();
                        String category=libView.getOrderedBooks().get(i).getCategory();
                        Order order= new Order(isbn1,title1,purchasedPrice,sellingPrice1,author,quantity,category);
                        orders.add(order);
                        
                    }
            	 for(int i=0;i<orders.size();i++)
            	 {
            		 orders.get(i).saveInFile();
                     //if( orders.get(i).saveInFile())
                     	//System.out.println("saved");
            	 }
            	 	
            	 writer.println("Total amount "+ amountToPay);
            	 writer.close();
            	 billStage.close();
            	 try {
            		 FileHandler.overwriteCurrentListToFile(Book.DATA_FILE, Book.getBooks());
            		 System.out.println("Updated");
            	 }
            	 catch(IOException ex) {
            		 System.out.println("Failed");
            		 ex.printStackTrace();
            		 }
            	 libView.getOrderedBooks().clear();
            	 
             });
             bottomPane.getChildren().addAll(billNumbername,checkOut);
             
             //checkOut.setContentDisplay(ContentDisplay.CENTER);
             pane.setBottom(bottomPane);
   
        });
      
    
}
        }