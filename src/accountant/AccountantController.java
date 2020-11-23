package accountant;

import dbUtil.dbConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

public class AccountantController implements Initializable {

    @FXML
    private Button logoutbutton;

    @FXML
    private TextField id;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField email;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField fees;

    @FXML
    private TextField month;

    @FXML
    private Label lblsuccessfull;

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

        String[] possibleWords = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        TextFields.bindAutoCompletion(month, possibleWords);

        this.dc = new dbConnection();
        insertdata();
    }

    @FXML
    private void loadStudentData(ActionEvent event) {
        try {

            Connection conn = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                this.data.add(new StudentData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (SQLException e) {

            System.err.println("Error " + e);
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
    private void addStudent(ActionEvent event) throws SQLException {

        String sqlInsert = "INSERT INTO students(id,firstname,lastname,email,DOB,fee,month,due) VALUES(?,?,?,?,?,?,?,?)";
        double cal = Double.parseDouble(fees.getText());
        double calc = 5000 - cal;

        switch (month.getText()) {

            case "January":
                calc += 0;
                break;
            case "February":
                calc += 5100;
                break;
            case "March":
                calc += 10250;
                break;
            case "April":
                calc += 15300;
                break;
            case "May":
                calc += 20400;
                break;
            case "June":
                calc += 25500;
                break;
            case "July":
                calc += 30600;
                break;
            case "August":
                calc += 35700;
                break;
            case "September":
                calc += 40800;
                break;
            case "October":
                calc += 45900;
                break;
            case "November":
                calc += 51000;
                break;
            case "December":
                calc += 56100;
                break;
        }

        String s = Double.toString(calc);

        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);

            stmt.setString(1, this.id.getText());
            stmt.setString(2, this.firstname.getText());
            stmt.setString(3, this.lastname.getText());
            stmt.setString(4, this.email.getText());
            stmt.setString(5, this.dob.getEditor().getText());
            stmt.setString(6, this.fees.getText());
            stmt.setString(7, this.month.getText());
            stmt.setString(8, s);

            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.lblsuccessfull.setText("Successfull!");
    }

    @FXML
    private void deleteStudent(ActionEvent event) throws SQLException {

        String sqlDelete = "DELETE FROM students where id = ? and firstname = ? and lastname = ? and email = ? and DOB = ? and fee = ? and month = ? and due = ?";
        double cal = Double.parseDouble(fees.getText());
        double calc = 5000 - cal;

        switch (month.getText()) {

            case "January":
                calc += 0;
                break;
            case "February":
                calc += 5100;
                break;
            case "March":
                calc += 10200;
                break;
            case "April":
                calc += 15300;
                break;
            case "May":
                calc += 20400;
                break;
            case "June":
                calc += 25500;
                break;
            case "July":
                calc += 30600;
                break;
            case "August":
                calc += 35700;
                break;
            case "September":
                calc += 40800;
                break;
            case "October":
                calc += 45900;
                break;
            case "November":
                calc += 51000;
                break;
            case "December":
                calc += 56100;
                break;
        }

        String s = Double.toString(calc);
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlDelete);

            stmt.setString(1, this.id.getText());
            stmt.setString(2, this.firstname.getText());
            stmt.setString(3, this.lastname.getText());
            stmt.setString(4, this.email.getText());
            stmt.setString(5, this.dob.getEditor().getText());
            stmt.setString(6, this.fees.getText());
            stmt.setString(7, this.month.getText());
            stmt.setString(8, s);

            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.lblsuccessfull.setText("Successfull!");
    }
    
    @FXML
    private void updateStudent(ActionEvent event) throws SQLException {
        
        String sqlUpdate = "UPDATE students SET firstname = ?, lastname = ? , email = ? , DOB = ? , fee = ? , month = ? , due = ? WHERE id = ?";
        
        double cal = Double.parseDouble(fees.getText());
        double calc = 5000 - cal;

        switch (month.getText()) {

            case "January":
                calc += 0;
                break;
            case "February":
                calc += 5100;
                break;
            case "March":
                calc += 10200;
                break;
            case "April":
                calc += 15300;
                break;
            case "May":
                calc += 20400;
                break;
            case "June":
                calc += 25500;
                break;
            case "July":
                calc += 30600;
                break;
            case "August":
                calc += 35700;
                break;
            case "September":
                calc += 40800;
                break;
            case "October":
                calc += 45900;
                break;
            case "November":
                calc += 51000;
                break;
            case "December":
                calc += 56100;
                break;
        }

        String s = Double.toString(calc);
        
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlUpdate);

            
            stmt.setString(1, this.firstname.getText());
            stmt.setString(2, this.lastname.getText());
            stmt.setString(3, this.email.getText());
            stmt.setString(4, this.dob.getEditor().getText());
            stmt.setString(5, this.fees.getText());
            stmt.setString(6, this.month.getText());
            stmt.setString(7, s);
            stmt.setString(8, this.id.getText());

            stmt.execute();
            conn.close();
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.lblsuccessfull.setText("Successfull!");
    }

    @FXML
    private void clearFields(ActionEvent event) {
        this.id.setText("");
        this.firstname.setText("");
        this.lastname.setText("");
        this.email.setText("");
        this.dob.setValue(null);
        this.fees.setText("");
        this.month.setText("");
        this.lblsuccessfull.setText("");
    }

    @FXML
    public void Logut(ActionEvent event) {

        Stage accountantStage = (Stage) this.logoutbutton.getScene().getWindow();
        accountantStage.close();

    }

    @FXML
    private void insertdata() {

        studenttable.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                StudentData sd = studenttable.getItems().get(studenttable.getSelectionModel().getSelectedIndex());
                
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");
                String date = sd.getDOB();
                LocalDate localDate = LocalDate.parse(date, dtf);

                id.setText(sd.getID());
                firstname.setText(sd.getFirstName());
                lastname.setText(sd.getLastName());
                email.setText(sd.getEmail());
                dob.setValue(localDate);
                fees.setText(sd.getfees());
                month.setText(sd.getmonth());
            }

        });

    }

}
