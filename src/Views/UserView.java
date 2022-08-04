package Views;

import java.io.File;
import java.util.ArrayList;

import Controllers.BookController;
import Controllers.UserController;
import Models.Author;
import Models.Book;
import Models.Role;
import Models.User;
import UI.CreateButton;
import UI.DeleteButton;
import UI.EditButton;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
import javafx.scene.layout.VBox;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class UserView extends View{
	private static  ArrayList<Author> prove = new ArrayList<>();
	public static final String FILE_PATH = "authors.ser";
	public static final File DATA_FILE = new File(FILE_PATH);
    private final BorderPane borderPane = new BorderPane();
    private final TableView<User> tableView1 = new TableView<>();
    private final HBox formPane = new HBox();
    private final TextField nameField = new TextField();
    private final TextField surnameField = new TextField();
    private final TextField usernameField = new TextField();
    private final TextField passwordField = new TextField();
    private final TextField birthdayField = new TextField();
    private final TextField phoneNrField = new TextField();
    private final TextField emailField = new TextField();
    private final TextField accessLevelField= new TextField();
    //private final ComboBox<Role> accessLevelComboBox = new ComboBox<>();
    private final TextField salaryField = new TextField();
    private final Button saveBtn = new CreateButton();
    private final Button deleteBtn = new DeleteButton();
    private final Button editBtn = new EditButton();
    private final TableColumn<User, String> nameCol = new TableColumn<>("Name");
    private final TableColumn<User, String> surnameCol = new TableColumn<>("Surname");
    private final TableColumn<User, String> usernameCol = new TableColumn<>("Username");
    private final TableColumn<User, String> passCol = new TableColumn<>("Password");
    private final TableColumn<User, String> birthdayCol = new TableColumn<>("Birthday");
    private final TableColumn<User, String> phoneCol = new TableColumn<>("Phone Number");
    private final TableColumn<User, String> emailCol = new TableColumn<>("E-mail");
    private final TableColumn<User, String> salaryCol = new TableColumn<>("Salary");
    private final TableColumn<User, String> accessLevelCol= new TableColumn<>("Access Level");
    private final TableColumn<User,Role> roleCol= new TableColumn<>("Role");
    private final ComboBox<Role> roleComboB= new ComboBox<>();
    private final Label resultLabel = new Label("");
    private final SearchView searchView = new SearchView("Search for a book");

    public TableView<User> getTableView1() {
        return tableView1;
    }

    

    public TextField getNameField() {
		return nameField;
	}



	public TextField getSurnameField() {
		return surnameField;
	}



	public TextField getUsernameField() {
		return usernameField;
	}

	public TextField getPasswordField() {
		return passwordField;
	}

	public TextField getBirthdayField() {
		return birthdayField;
	}


	public TextField getPhoneNrField() {
		return phoneNrField;
	}


	public TextField getEmailField() {
		return emailField;
	}


	public TextField getSalaryField() {
		return salaryField;
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
    
    

    
    public TextField getAccessLevelField() {
		return accessLevelField;
	}



	public TableColumn<User, Role> getRoleCol() {
		return roleCol;
	}



	public ComboBox<Role> getRoleComboB() {
		return roleComboB;
	}



	public TableColumn<User, String> getNameCol() {
		return nameCol;
	}


	public TableColumn<User, String> getSurnameCol() {
		return surnameCol;
	}


	public TableColumn<User, String> getUsernameCol() {
		return usernameCol;
	}
	public TableColumn<User, String> getPassCol() {
		return passCol;
	}


	public TableColumn<User, String> getBirthdayCol() {
		return birthdayCol;
	}


	public TableColumn<User, String> getPhoneCol() {
		return phoneCol;
	}


	public TableColumn<User, String> getEmailCol() {
		return emailCol;
	}


	public TableColumn<User, String> getSalaryCol() {
		return salaryCol;
	}
	

	public TableColumn<User, String> getAccessLevelCol() {
		return accessLevelCol;
	}



	public Label getResultLabel() {
        return resultLabel;
    }

    public SearchView getSearchView() {
        return searchView;
    }

    public UserView() {
        setTableView1();
        setForm();
        new UserController(this);
        // inject the controller
        //new BookController(this);
    }

    private void setForm() {
        formPane.setPadding(new Insets(20));
        formPane.setSpacing(20);
        formPane.setAlignment(Pos.CENTER);
        Label nameLabel = new Label("Name: ", nameField);
        nameLabel.setContentDisplay(ContentDisplay.TOP);
        Label surnameLabel = new Label("Surname: ", surnameField);
        surnameLabel.setContentDisplay(ContentDisplay.TOP);
        Label usernameLabel = new Label("username: ", usernameField);
        usernameLabel.setContentDisplay(ContentDisplay.TOP);
        Label passwordLabel = new Label("password: ", passwordField);
        passwordLabel.setContentDisplay(ContentDisplay.TOP);
        Label birthdayLabel = new Label("Birthday: ", birthdayField);
        birthdayLabel.setContentDisplay(ContentDisplay.TOP);
        Label phoneLabel= new Label("PhoneNr: ",phoneNrField);
        phoneLabel.setContentDisplay(ContentDisplay.TOP);
        Label emailLabel= new Label("Email: ",emailField);
        emailLabel.setContentDisplay(ContentDisplay.TOP);
        Label salaryLabel= new Label("Salary: ",salaryField);
        salaryLabel.setContentDisplay(ContentDisplay.TOP);
        Label roleLabel = new Label("Role: ", roleComboB);
        roleComboB.getItems().setAll(Role.ADMIN,Role.MANAGER,Role.LIBRARIAN);
        roleComboB.setValue(Role.ADMIN);
            
        //accessLabel.setContentDisplay(ContentDisplay.TOP);
        formPane.getChildren().addAll(nameLabel, surnameLabel, usernameLabel, passwordLabel,
                                        birthdayLabel,phoneLabel,emailLabel, salaryLabel,
                                        roleLabel,saveBtn, deleteBtn, editBtn);
    }

    private void setTableView1() {
        tableView1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView1.setEditable(true);
        tableView1.setItems(FXCollections.observableArrayList(User.getUsers()));

        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        // to edit the value inside the table view
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        surnameCol.setCellValueFactory(
                new PropertyValueFactory<>("surname")
        );
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        usernameCol.setCellValueFactory(
                new PropertyValueFactory<>("username")
        );
        usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passCol.setCellValueFactory(
                new PropertyValueFactory<>("password")
        );
        passCol.setCellFactory(TextFieldTableCell.forTableColumn());
        birthdayCol.setCellValueFactory(
                new PropertyValueFactory<>("birthday")
        );
        birthdayCol.setCellFactory(TextFieldTableCell.forTableColumn());

        phoneCol.setCellValueFactory(
                new PropertyValueFactory<>("phoneNr"));
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        emailCol.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        

        salaryCol.setCellValueFactory(
                new PropertyValueFactory<>("salary")
        );
        salaryCol.setCellFactory(TextFieldTableCell.forTableColumn());

        accessLevelCol.setCellValueFactory(
                new PropertyValueFactory<>("access")
        );

        accessLevelCol.setCellFactory(ComboBoxTableCell.forTableColumn());
        
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleCol.setCellFactory(ComboBoxTableCell.forTableColumn(Role.values()));
        
 

        tableView1.getColumns().addAll(nameCol, surnameCol, usernameCol,passCol,
        		birthdayCol,phoneCol,emailCol,salaryCol,roleCol);
    }


	@Override
	public Parent getView() {
		 borderPane.setCenter(tableView1);
	        VBox vBox = new VBox();
	        vBox.setAlignment(Pos.CENTER);
	        vBox.setSpacing(5);
	        vBox.getChildren().addAll(formPane, resultLabel);
	        borderPane.setBottom(vBox);
	        borderPane.setTop(searchView.getSearchPane());
	        BackgroundImage myBI= new BackgroundImage(new Image("https://wattention.com/wp-content/uploads/2018/04/Books-and-coffee.jpg",300,300,false,true),
	    	        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
	    	          BackgroundSize.DEFAULT);
	        borderPane.setBackground(new Background(myBI));
	        return borderPane;
	}

}
