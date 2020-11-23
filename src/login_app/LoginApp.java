
package login_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       Parent root = (Parent) FXMLLoader.load(getClass().getResource("Login.fxml")); 
       
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.setTitle("Tution Fee Automation System");
       stage.setResizable(false);
       stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
