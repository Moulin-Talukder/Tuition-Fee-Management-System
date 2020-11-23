
package accountant;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentData {
    
    private final StringProperty ID;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final StringProperty DOB;
    private final StringProperty fees;
    private final StringProperty month;
    private final StringProperty due;
    
    public StudentData(String id, String firstname, String lastname, String email, String dob, String fees, String month, String due){
        
        this.ID = new SimpleStringProperty(id);
        this.firstName = new SimpleStringProperty(firstname);
        this.lastName = new SimpleStringProperty(lastname);
        this.email = new SimpleStringProperty(email);
        this.DOB = new SimpleStringProperty(dob);
        this.fees = new SimpleStringProperty(fees);
        this.month = new SimpleStringProperty(month);
        this.due = new SimpleStringProperty(due);
        
        
        
    }

    public String getID() {
        return ID.get();
    }
    
    
     
    public void setID(String ID){
         this.ID.set(ID);
    }

    public String getFirstName() {
        return firstName.get();
    }
    
    
    
    public void setFirstName(String firstName){
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }
    
   
    
    public void setLastName(String lastName){
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }
    
   
    
    public void setEmail(String email){
        this.email.set(email);
    }

    public String getDOB() {
        return DOB.get();
    }
    
    
    public void setDOB(String DOB){
        this.DOB.set(DOB);
    }
    
    public String getfees() {
        return fees.get();
    }
    
    public StringProperty feesProperty(){
        return fees;
    }
    
    public void setfees(String fees){
        this.fees.set(fees);
    }
    
     public String getmonth() {
        return month.get();
    }
    
    public StringProperty monthProperty(){
        return month;
    }
    
    public void setmonth(String month){
        this.month.set(month);
    }
    
    public String getdue() {
        return due.get();
    }
    
    public StringProperty dueProperty(){
        return due;
    }
    
    public void setdue(String due){
        this.due.set(due);
    }
    
   

   
    
    
    
}
