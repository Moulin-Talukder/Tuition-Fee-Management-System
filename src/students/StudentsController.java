
package students;


import accountant.StudentData;
import dbUtil.dbConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StudentsController implements Initializable {
    
     @FXML
    private Button logoutButton;
    
    @FXML
    private TableView<StudentData> studenttable;
    
    @FXML
    private TableColumn<StudentData, String> idcolumn;
    
    @FXML
    private TableColumn<StudentData, String> firstnamecolumn;
    
    @FXML
    private TableColumn<StudentData, String> lastnamecolumn;
    
    @FXML
    private TableColumn<StudentData, String> emailcolumn;
    
    @FXML
    private TableColumn<StudentData, String> dobcolumn;
    
    @FXML
    private TableColumn<StudentData, String> feecolumn;
    
    @FXML
    private TableColumn<StudentData, String> duecolumn;
    
    @FXML
    private TableColumn<StudentData, String> monthcolumn;
    
    private dbConnection dc;
    private ObservableList<StudentData> data;
    private String sql = "SELECT * FROM students";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new dbConnection();
    }
    
    @FXML
    private void loadStudentData(ActionEvent event){
        try{
            
            Connection conn = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            
            while(rs.next()){
                this.data.add(new StudentData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            }
           
        }catch(SQLException e){
        
            System.err.println("Error " +e);
        }
        
        this.idcolumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("ID"));
        this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("firstName"));
        this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("lastName"));
        this.emailcolumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("email"));
        this.dobcolumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("DOB"));
        this.feecolumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("fees"));
        this.monthcolumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("month"));
        this.duecolumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("due"));
        
        this.studenttable.setItems(null);
        this.studenttable.setItems(this.data);
      
    }
    
    @FXML
    public void Logut(ActionEvent event){
       
        
        Stage userStage = (Stage)this.logoutButton.getScene().getWindow();
        userStage.close();
        //islogout();
        
    }
    
//    public void islogout(){
//        try{
//            Stage stage = new Stage();
//            FXMLLoader loginloader = new FXMLLoader();
//            Parent loginroot = (Parent)loginloader.load(getClass().getResource("/login_app/Login.fxml").openStream());
//            
//            LoginController loginController = (LoginController)loginloader.getController();
//            
//            Scene scene = new Scene(loginroot);
//            stage.setScene(scene);
//            stage.setTitle("Tution Fee Automation System");
//            stage.setResizable(false);
//            stage.show();
//            
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//    
//    public static void main(String[] args) {
//        launch(args);
//    }
    }
    



