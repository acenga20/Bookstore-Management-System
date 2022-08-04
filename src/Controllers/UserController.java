package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import Auxiliaries.FileHandler;
import Models.Order;
import Models.Role;
import Models.User;
import Views.UserView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

public class UserController {
    private final UserView userView;
    public UserController(UserView userView) {
        this.userView = userView;
        setSaveListener();
        setDeleteListener();
        setSearchListener();
        setEditListener();
    }

    private void setSaveListener() {
        userView.getSaveBtn().setOnAction(e -> {
            String name = userView.getNameField().getText();
            String surname = userView.getSurnameField().getText();
            String username = userView.getUsernameField().getText();
            String password = userView.getPasswordField().getText();
            String birthday = userView.getBirthdayField().getText();
            String phoneNr = userView.getPhoneNrField().getText();
            String email = userView.getEmailField().getText();
            String salary= userView.getSalaryField().getText();
            Role role = userView.getRoleComboB().getValue();
           
            User user = new User(name,surname,username,password,birthday,phoneNr,email,salary,role);
            if(!user.exists()) {
            if (user.saveInFile()) {
                userView.getTableView1().getItems().add(user);
                userView.getResultLabel().setText("User added successfully");
                userView.getResultLabel().setTextFill(Color.DARKGREEN);
                resetFields();
                //Order.DATA_FILE.delete();
            } else {
            	Alert alert = new Alert(Alert.AlertType.WARNING);
            	alert.setTitle("Error");
            	alert.setHeaderText("User creation failed!");
            	alert.setContentText("Try again");
            	alert.showAndWait();
                /*bookView.getResultLabel().setText("Book creation failed");
                bookView.getResultLabel().setTextFill(Color.DARKRED);*/
            }}
        else {
        	Alert alert = new Alert(Alert.AlertType.WARNING);
        	alert.setTitle("Error");
        	alert.setHeaderText("User already exists!");
        	alert.setContentText("Try again");
        	alert.showAndWait();}
        	
        });
    }
   private void setSearchListener() {
        userView.getSearchView().getClearBtn().setOnAction(e -> {
            userView.getSearchView().getSearchField().setText("");
            userView.getTableView1().setItems(FXCollections.observableArrayList(User.getUsers()));
        });
        userView.getSearchView().getSearchBtn().setOnAction(e -> {
            String searchText = userView.getSearchView().getSearchField().getText();
            ArrayList<User> searchResults = User.getSearchUserResults(searchText);
            userView.getTableView1().setItems(FXCollections.observableArrayList(searchResults));
        });
    }
    private void setEditListener() {
    	 userView.getNameCol().setOnEditCommit(event -> {
             User userToEdit = event.getRowValue();
             int index = User.getUsers().indexOf(userToEdit);
             if(event.getNewValue().matches("[a-zA-Z'-]+$"))
             User.getUsers().get(index).setName(event.getNewValue());
             else {
            	 Alert alert = new Alert(Alert.AlertType.WARNING);
             	alert.setTitle("Error");
             	alert.setHeaderText("Invalid Input!");
             	alert.setContentText("Try again");
             	alert.showAndWait();
             }
         });
      	 userView.getSurnameCol().setOnEditCommit(event -> {
             User userToEdit = event.getRowValue();
             
             int index = User.getUsers().indexOf(userToEdit);
             if(event.getNewValue().matches("[a-zA-Z'-]+$"))
             User.getUsers().get(index).setSurname(event.getNewValue());
             else {
            	 Alert alert = new Alert(Alert.AlertType.WARNING);
             	alert.setTitle("Error");
             	alert.setHeaderText("Invalid Input!");
             	alert.setContentText("Try again");
             	alert.showAndWait();
             }
         });
      	 userView.getUsernameCol().setOnEditCommit(event -> {
             User userToEdit = event.getRowValue();
             int index = User.getUsers().indexOf(userToEdit);
   
             User.getUsers().get(index).setUsername(event.getNewValue());
            
         });
      	 userView.getPassCol().setOnEditCommit(event -> {
             User userToEdit = event.getRowValue();
             int index = User.getUsers().indexOf(userToEdit);
             
             User.getUsers().get(index).setPassword(event.getNewValue());
         });
      	 userView.getBirthdayCol().setOnEditCommit(event -> {
             User userToEdit = event.getRowValue();
             int index = User.getUsers().indexOf(userToEdit);
             if(event.getNewValue().matches("\\\\d{4}-(0[1-9]|1[012])-([012][0-9]|3[01])"))
             User.getUsers().get(index).setBirthday(event.getNewValue());
             else {
            	 Alert alert = new Alert(Alert.AlertType.WARNING);
              	alert.setTitle("Error");
              	alert.setHeaderText("Invalid Input!");
              	alert.setContentText("Try again");
              	alert.showAndWait();
             }
         });
      	 userView.getPhoneCol().setOnEditCommit(event -> {
             User userToEdit = event.getRowValue();
             int index = User.getUsers().indexOf(userToEdit);
             if(event.getNewValue().matches("06[789]\\\\d{7}"))
             User.getUsers().get(index).setPhoneNr(event.getNewValue());
             else {
            	 Alert alert = new Alert(Alert.AlertType.WARNING);
               	alert.setTitle("Error");
               	alert.setHeaderText("Invalid Input!");
               	alert.setContentText("Try again");
               	alert.showAndWait();
             }
         });
      	 userView.getEmailCol().setOnEditCommit(event -> {
             User userToEdit = event.getRowValue();
             int index = User.getUsers().indexOf(userToEdit);
             if(event.getNewValue().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\\\"(?:[\\\\x01-\\\\x08\\\\x0b\\\\x0c\\\\x0e-\\\\x1f\\\\x21\\\\x23-\\\\x5b\\\\x5d-\\\\x7f]|\\\\\\\\[\\\\x01-\\\\x09\\\\x0b\\\\x0c\\\\x0e-\\\\x7f])*\\\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\\\x01-\\\\x08\\\\x0b\\\\x0c\\\\x0e-\\\\x1f\\\\x21-\\\\x5a\\\\x53-\\\\x7f]|\\\\\\\\[\\\\x01-\\\\x09\\\\x0b\\\\x0c\\\\x0e-\\\\x7f])+)\\\\])"))
             User.getUsers().get(index).setEmail(event.getNewValue());
             else {
            	 Alert alert = new Alert(Alert.AlertType.WARNING);
               	alert.setTitle("Error");
               	alert.setHeaderText("Invalid Input!");
               	alert.setContentText("Try again");
               	alert.showAndWait();
             }
         });
      	 userView.getSalaryCol().setOnEditCommit(event -> {
             User userToEdit = event.getRowValue();
             int index = User.getUsers().indexOf(userToEdit);
             if(event.getNewValue().matches("\\d{3}"))
             User.getUsers().get(index).setSalary(event.getNewValue());
             else {
            	 Alert alert = new Alert(Alert.AlertType.WARNING);
                	alert.setTitle("Error");
                	alert.setHeaderText("Invalid Input!");
                	alert.setContentText("Try again");
                	alert.showAndWait();
             }
         });
      	 userView.getRoleCol().setOnEditCommit(event -> {
             User userToEdit = event.getRowValue();
             int index = User.getUsers().indexOf(userToEdit);
             
           //  User.getUsers().get(index).setRole(event.getNewValue());
         });
    
    	
    	userView.getEditBtn().setOnAction(e->{try {
            // todo change it
    	
            FileHandler.overwriteCurrentListToFile(User.DATA_FILE1, User.getUsers());
            userView.getResultLabel().setText("Users were updated successfully");
        } catch (IOException ex) {
            userView.getResultLabel().setText("Writing books to the file failed!");
            ex.printStackTrace();
        }
    		
    		
    	});
    }
    private void setDeleteListener() {
       userView.getDeleteBtn().setOnAction(e -> {
            ObservableList<User> usersInTable = userView.getTableView1().getItems();
            ObservableList<Integer> indices =userView.getTableView1().getSelectionModel().getSelectedIndices();
            for (int index: indices)
                usersInTable.get(index).deleteFromFile();
            userView.getTableView1().setItems(FXCollections.observableArrayList(User.getUsers()));
            userView.getResultLabel().setTextFill(Color.DARKGREEN);
            userView.getResultLabel().setText("Users deleted successfully!");
        });
    }
    private void resetFields() {
    	 userView.getNameField().setText("");
    	 userView.getSurnameField().setText("");
    	 userView.getUsernameField().setText("");
    	 userView.getPasswordField().setText("");
    	 userView.getBirthdayField().setText("");
    	 userView.getPhoneNrField().setText("");
    	 userView.getEmailField().setText("");
    	 userView.getSalaryField().setText("");
    }


}
