package Controllers;
import java.io.IOException;
import java.util.ArrayList;

import Auxiliaries.FileHandler;
import Models.Author;
import Models.Book;
import Views.BookView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

public class BookController {
    private final BookView bookView;
    public BookController(BookView bookView) {
        this.bookView = bookView;
        setSaveListener();
        setDeleteListener();
        setSearchListener();
        setEditListener();
    }

    private void setSaveListener() {
        bookView.getSaveBtn().setOnAction(e -> {
            String isbn = bookView.getIsbnField().getText();
            String title = bookView.getTitleField().getText();
            float purchasedPrice = Float.parseFloat(bookView.getPurchasedPriceField().getText());
            float sellingPrice = Float.parseFloat(bookView.getSellingPriceField().getText());
            Author author = bookView.getAuthorsComboBox().getValue();
            int quantity=Integer.parseInt(bookView.getQuantityField().getText());
            String category=bookView.getCategoryField().getText();
            
            Book book = new Book(isbn, title, purchasedPrice, sellingPrice, author,quantity,category);
            if(!book.exists()) {
            if (book.saveInFile()) {
                bookView.getTableView().getItems().add(book);
                bookView.getResultLabel().setText("Book created successfully");
                bookView.getResultLabel().setTextFill(Color.DARKGREEN);
                resetFields();
               
            } else {
            	Alert alert = new Alert(Alert.AlertType.WARNING);
            	alert.setTitle("Error");
            	alert.setHeaderText("Book creation failed!");
            	alert.setContentText("Try again");
            	alert.showAndWait();
                /*bookView.getResultLabel().setText("Book creation failed");
                bookView.getResultLabel().setTextFill(Color.DARKRED);*/
            }}
            else {
            	Alert alert = new Alert(Alert.AlertType.WARNING);
            	alert.setTitle("Error");
            	alert.setHeaderText("Book creation failed!");
            	alert.setContentText("ISBN already exists");
            	alert.showAndWait();
            	resetFields();
            }
        });
    }
    private void setSearchListener() {
        bookView.getSearchView().getClearBtn().setOnAction(e -> {
            bookView.getSearchView().getSearchField().setText("");
            bookView.getTableView().setItems(FXCollections.observableArrayList(Book.getBooks()));
        });
        bookView.getSearchView().getSearchBtn().setOnAction(e -> {
            String searchText = bookView.getSearchView().getSearchField().getText();
            ArrayList<Book> searchResults = Book.getSearchBookResults(searchText);
            bookView.getTableView().setItems(FXCollections.observableArrayList(searchResults));
        });
    }
    private void setEditListener() {
    	 bookView.getTitleCol().setOnEditCommit(event -> {
             Book bookToEdit = event.getRowValue();
             int index = Book.getBooks().indexOf(bookToEdit);
           
             Book.getBooks().get(index).setTitle(event.getNewValue());
     
         });
    	 bookView.getIsbnCol().setOnEditCommit(event -> {
             Book bookToEdit = event.getRowValue();
             int index = Book.getBooks().indexOf(bookToEdit);
             if(event.getNewValue().matches("\\d{3}-\\d-\\d{2}-\\d{6}-\\d")){
             Book.getBooks().get(index).setIsbn(event.getNewValue());}
             else {
         		Alert alert = new Alert(Alert.AlertType.WARNING);
             	alert.setTitle("Error");
             	alert.setHeaderText("Book edit failed!");
             	alert.setContentText("Invalid input");
             	alert.showAndWait();
         	 
          }
          
         });
    	 bookView.getPurchasedPriceCol().setOnEditCommit(event -> {
             Book bookToEdit = event.getRowValue();
             int index = Book.getBooks().indexOf(bookToEdit);
             if(event.getNewValue()>0) {
             Book.getBooks().get(index).setPurchasedPrice(event.getNewValue());}
             else {
            	 Alert alert = new Alert(Alert.AlertType.WARNING);
              	alert.setTitle("Error");
              	alert.setHeaderText("Book edit failed!");
              	alert.setContentText("Invalid input");
              	alert.showAndWait();
             }
         });
    	 bookView.getSellingPriceCol().setOnEditCommit(event -> {
             Book bookToEdit = event.getRowValue();
             int index = Book.getBooks().indexOf(bookToEdit);
             if(event.getNewValue()>0)
             Book.getBooks().get(index).setSellingPrice(event.getNewValue());
             else {
            	 Alert alert = new Alert(Alert.AlertType.WARNING);
              	alert.setTitle("Error");
              	alert.setHeaderText("Book edit failed!");
              	alert.setContentText("Invalid input");
              	alert.showAndWait();
             }
         });
    	 bookView.getQuantityCol().setOnEditCommit(event -> {
             Book bookToEdit = event.getRowValue();
             int index = Book.getBooks().indexOf(bookToEdit);
             if(event.getNewValue()>0){
             Book.getBooks().get(index).setQuantity(event.getNewValue());}
             else {
            	 Alert alert = new Alert(Alert.AlertType.WARNING);
              	alert.setTitle("Error");
              	alert.setHeaderText("Book edit failed!");
              	alert.setContentText("Invalid input");
              	alert.showAndWait();
             }
         });
    	 bookView.getCategoryCol().setOnEditCommit(event -> {
             Book bookToEdit = event.getRowValue();
             int index = Book.getBooks().indexOf(bookToEdit);
             if(event.getNewValue().matches("^[a-zA-Z'-]+$"))
             Book.getBooks().get(index).setCategory(event.getNewValue());
             else {
            	 Alert alert = new Alert(Alert.AlertType.WARNING);
               	alert.setTitle("Error");
               	alert.setHeaderText("Book edit failed!");
               	alert.setContentText("Invalid input");
               	alert.showAndWait();
             }
         });
    	 //bookView.getAuthorsComboBox().setEditable(true);
    	 bookView.getAuthorsComboBox().setOnAction(event -> {
    		// Book bookToEdit = event.getRowValue();
         });
    	
    	bookView.getEditBtn().setOnAction(e->{try {
            // todo change it
    	
            FileHandler.overwriteCurrentListToFile(Book.DATA_FILE, Book.getBooks());
            bookView.getResultLabel().setText("Books were updated successfully");
        } catch (IOException ex) {
            bookView.getResultLabel().setText("Writing books to the file failed!");
            ex.printStackTrace();
        }
    		
    		
    	});
    }
    private void setDeleteListener() {
       bookView.getDeleteBtn().setOnAction(e -> {
            ObservableList<Book> booksInTable = bookView.getTableView().getItems();
            ObservableList<Integer> indices = bookView.getTableView().getSelectionModel().getSelectedIndices();
            for (int index: indices)
                booksInTable.get(index).deleteFromFile();
            bookView.getTableView().setItems(FXCollections.observableArrayList(Book.getBooks()));
            bookView.getResultLabel().setTextFill(Color.DARKGREEN);
            bookView.getResultLabel().setText("Books deleted successfully!");
        });
    }


    private void resetFields() {
        bookView.getIsbnField().setText("");
        bookView.getTitleField().setText("");
        bookView.getPurchasedPriceField().setText("");
        bookView.getSellingPriceField().setText("");
        bookView.getQuantityField().setText("");
    }
}