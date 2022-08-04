package Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;


public class LoginView extends View {
	private final StackPane pane= new StackPane();
    private final BorderPane borderPane = new BorderPane();

    private final TextField usernameField = new TextField();

    private final PasswordField passwordField = new PasswordField();
    private final Button loginBtn = new Button("Login");
    private final Label errorLabel = new Label("");
    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getLoginBtn() {
        return loginBtn;
    }
    public Label getErrorLabel() {
        return errorLabel;
    }
    public LoginView() {
        setView();
    }

    private void setView() {
    
    	HBox usern= new HBox(20);
    	
        Label usernameLabel = new Label("Username:");
      
        usern.getChildren().add(usernameLabel);
        usern.getChildren().add(usernameField);
        usernameField.setStyle("-fx-background-color: azure;-fx-border-color: black;-fx-border-radius:3;-fx-border-width:2px");
       
        usernameLabel.setContentDisplay(ContentDisplay.LEFT);
        usernameLabel.setTextFill(Color.BLACK);
        usernameLabel.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.REGULAR, 16));
        HBox pass= new HBox(20);
        Label passwordLabel = new Label("Password: ");
        passwordLabel.setTextFill(Color.BLACK);
        passwordLabel.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.REGULAR, 16));
        passwordField.setStyle("-fx-background-color: azure;-fx-border-color: black;-fx-border-radius:3;-fx-border-width:2px");
        pass.getChildren().add(passwordLabel);
        pass.getChildren().add(passwordField);
        VBox vBox = new VBox();
        Label loginLabel = new Label("LOGIN");
        
        loginLabel.setFont(Font.font("Lucida Handwriting",FontWeight.BOLD,FontPosture.REGULAR,20));
        loginBtn.setStyle("-fx-background-color: azure");
        vBox.getChildren().addAll(loginLabel, usern,pass, loginBtn, errorLabel);
    	BackgroundImage myBI= new BackgroundImage(new Image("https://wattention.com/wp-content/uploads/2018/04/Books-and-coffee.jpg",300,300,false,true),
    	        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
    	          BackgroundSize.DEFAULT);
    	//Background bck= new Background(myBI);
   
    	borderPane.setBackground(new Background(myBI));
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));
        vBox.setSpacing(20);
        borderPane.setCenter(vBox);
        pane.getChildren().add(borderPane);
        
        
        
    }

    public Parent getView() {
        return pane;
    }
}
