package Controllers;

import Views.BookView;
import java.io.IOException;
import java.util.ArrayList;

import Auxiliaries.FileHandler;
import Models.Author;
import Models.Book;
import Views.ManagerView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;


public class ManagerController {
    private final ManagerView manView;
    public ManagerController(ManagerView manView) {
        this.manView = manView;
        setSaveListener();
        setDeleteListener();
        setSearchListener();
        setEditListener();
    }
    
    private void setSaveListener() {
    		manView.getSaveBtn().setOnAction(e -> {
            String isbn = manView.getIsbnField().getText();
            String title = manView.getTitleField().getText();
            float purchasedPrice = Float.parseFloat(manView.getPurchasedPriceField().getText());
            float sellingPrice = Float.parseFloat(manView.getSellingPriceField().getText());
            Author author = manView.getAuthorsComboBox().getValue();
            int quantity=Integer.parseInt(manView.getQuantityField().getText());
            String category=manView.getCategoryField().getText();
            
            Book book = new Book(isbn, title, purchasedPrice, sellingPrice, author,quantity,category);
            if(!book.exists()) {
            if (book.saveInFile()) {
                manView.getTableView().getItems().add(book);
                manView.getResultLabel().setText("Book created successfully");
                manView.getResultLabel().setTextFill(Color.DARKGREEN);
                resetFields();
            } else {
                manView.getResultLabel().setText("Book creation failed");
                manView.getResultLabel().setTextFill(Color.DARKRED);
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
        manView.getSearchView().getClearBtn().setOnAction(e -> {
            manView.getSearchView().getSearchField().setText("");
            manView.getTableView().setItems(FXCollections.observableArrayList(Book.getBooks()));
        });
        manView.getSearchView().getSearchBtn().setOnAction(e -> {
            String searchText = manView.getSearchView().getSearchField().getText();
            ArrayList<Book> searchResults = Book.getSearchBookResults(searchText);
            manView.getTableView().setItems(FXCollections.observableArrayList(searchResults));
        });
    }
    private void setEditListener() {
    	 manView.getTitleCol().setOnEditCommit(event -> {
             Book bookToEdit = event.getRowValue();
             int index = Book.getBooks().indexOf(bookToEdit);
             Book.getBooks().get(index).setTitle(event.getNewValue());
         });
    	 manView.getIsbnCol().setOnEditCommit(event -> {
             Book bookToEdit = event.getRowValue();
             int index = Book.getBooks().indexOf(bookToEdit);
             if(event.getNewValue().matches("\\d{3}-\\d-\\d{2}-\\d{6}-\\d"))
             Book.getBooks().get(index).setIsbn(event.getNewValue());
             else {
          		Alert alert = new Alert(Alert.AlertType.WARNING);
              	alert.setTitle("Error");
              	alert.setHeaderText("Book edit failed!");
              	alert.setContentText("Invalid input");
              	alert.showAndWait();
          	 
           }
         });
    	 manView.getPurchasedPriceCol().setOnEditCommit(event -> {
             Book bookToEdit = event.getRowValue();
             int index = Book.getBooks().indexOf(bookToEdit);
             if(event.getNewValue()>0) 
             Book.getBooks().get(index).setPurchasedPrice(event.getNewValue());
             else {
            	 Alert alert = new Alert(Alert.AlertType.WARNING);
              	alert.setTitle("Error");
              	alert.setHeaderText("Book edit failed!");
              	alert.setContentText("Invalid input");
              	alert.showAndWait();
             }
         });
    	 manView.getSellingPriceCol().setOnEditCommit(event -> {
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
    	 manView.getQuantityCol().setOnEditCommit(event -> {
             Book bookToEdit = event.getRowValue();
             int index = Book.getBooks().indexOf(bookToEdit);
             if(event.getNewValue()>0)
             Book.getBooks().get(index).setQuantity(event.getNewValue());
             else {
            	 Alert alert = new Alert(Alert.AlertType.WARNING);
              	alert.setTitle("Error");
              	alert.setHeaderText("Book edit failed!");
              	alert.setContentText("Invalid input");
              	alert.showAndWait();
             }
         });
    	 manView.getCategoryCol().setOnEditCommit(event -> {
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
    	 manView.getAuthorsComboBox().setOnAction(event -> {
    		// Book bookToEdit = event.getRowValue();
         });
    	
    	 manView.getEditBtn().setOnAction(e->{try {
            // todo change it
            FileHandler.overwriteCurrentListToFile(Book.DATA_FILE, Book.getBooks());
            manView.getResultLabel().setText("Books were updated successfully");
        } catch (IOException ex) {
            manView.getResultLabel().setText("Writing books to the file failed!");
            ex.printStackTrace();
        }
    		
    		
    	});
    }
    private void setDeleteListener() {
       manView.getDeleteBtn().setOnAction(e -> {
            ObservableList<Book> booksInTable = manView.getTableView().getItems();
            ObservableList<Integer> indices = manView.getTableView().getSelectionModel().getSelectedIndices();
            for (int index: indices)
                booksInTable.get(index).deleteFromFile();
            manView.getTableView().setItems(FXCollections.observableArrayList(Book.getBooks()));
            manView.getResultLabel().setTextFill(Color.DARKGREEN);
            manView.getResultLabel().setText("Books deleted successfully!");
        });
    }


    private void resetFields() {
        manView.getIsbnField().setText("");
        manView.getTitleField().setText("");
        manView.getPurchasedPriceField().setText("");
        manView.getSellingPriceField().setText("");
        manView.getQuantityField().setText("");
    }
}
