
package login_app;

public enum option {
    
    Accountant , Student;
    
    private option(){}
    
    public String value(){
        
        return name();
    }
    
    public static option fromvalue(String v){
        
        return valueOf(v);
    }
}
