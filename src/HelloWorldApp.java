import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Controllers.LoginController;
import Models.Author;
import Models.Role;
import Models.User;
import Views.LoginView;
import Views.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelloWorldApp extends Application {
	 public static void main(String[] args) {
	      
		 //seedData();
	        launch(args);
	    }

	    private static void seedData() {
	        User admin = new User("Alissa","Çenga","admin", "Test2022","25-09-2002",
	        		"0698436261","acenga20","2333",Role.ADMIN);
	        User manager = new User("manager", "Test2022", Role.MANAGER);
	        User librarian = new User("librarian", "Test2022", Role.LIBRARIAN);
	        
	        try {
	            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("users.ser"));
	            outputStream.writeObject(admin);
	            outputStream.writeObject(manager);
	            outputStream.writeObject(librarian);
	            System.out.println("Wrote users to the file users.dat successfully");
	            outputStream.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("authors.ser"))) {
	            outputStream.writeObject(new Author("Test1", "Test1"));
	            outputStream.writeObject(new Author("Test2", "Test2"));
	            outputStream.writeObject(new Author("Test3", "Test3"));
	            System.out.println("Wrote authors to the file users.dat successfully");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void start(Stage stage) {
	    
	        LoginView loginView = new LoginView();
	        LoginController controller = new LoginController(loginView, new MainView(), stage);
	        Scene scene = new Scene(loginView.getView(), 320, 240);
	   
	        stage.setTitle("Bookstore");
	        stage.setScene(scene);
	        stage.show();
	    }
}
