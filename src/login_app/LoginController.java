
package login_app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import accountant.AccountantController;
import students.StudentsController;
import org.controlsfx.control.textfield.TextFields;


public class LoginController implements Initializable {
    
    LoginModel loginModel = new LoginModel();
    
   
    @FXML
    private Label dbstatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<option> combobox;
    @FXML
    private Label loginstatus;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        String[] possibleWords = {"student@gmail.com","accountant@gmail.com"};
        TextFields.bindAutoCompletion(username, possibleWords);
    
        
        
        if(loginModel.isDatabaseConnected()){
            this.dbstatus.setText("Connected to Database");
        }else{
            this.dbstatus.setText("Not Connected to Database");
        }
        
        this.combobox.setItems(FXCollections.observableArrayList(option.values()));
    }
    
     

    
    @FXML
    public void Login(ActionEvent event){
        
        try{
            if (this.loginModel.isLogin(this.username.getText(), this.password.getText(),((option)this.combobox.getValue()).toString())){
               //Stage stage = (Stage)this.loginButton.getScene().getWindow();
               //stage.close();
               switch(((option)this.combobox.getValue()).toString()){
                   case "Accountant":
                       accountantLogin();
                       break;
                   
                   case "Student":
                       studentLogin();
                       break;
                   
               }
                
            }
            else{
                this.loginstatus.setText("\t\t\tError!\nCheck Username or Password and try again");
                this.loginstatus.setTextFill(Color.WHITE);
            }
                
                
           
        
        
    }catch(Exception localException){
        
    }
  }
    
    @FXML
    private void clearFields(ActionEvent event){
        this.username.setText("");
        this.password.setText("");
        this.loginstatus.setText("");
      
    }
    
    public void studentLogin(){
        
        try{
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/students/studentFXML.fxml").openStream());
            
            StudentsController studentsController = (StudentsController)loader.getController();
            
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Student Dashboard");
            userStage.setResizable(false);
            userStage.show();
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
    }
    
    public void accountantLogin(){
        try{
            Stage accountantStage = new Stage();
            FXMLLoader accountantloader = new FXMLLoader();
            Pane accountantroot = (Pane)accountantloader.load(getClass().getResource("/accountant/Accountant.fxml").openStream());
            
            AccountantController accountantController = (AccountantController)accountantloader.getController();
            
            Scene scene = new Scene(accountantroot);
            accountantStage.setScene(scene);
            accountantStage.setTitle("Accountant Dashboard");
            accountantStage.setResizable(false);
            accountantStage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    
   
    
}  
