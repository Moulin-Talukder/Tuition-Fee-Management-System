
package login_app;

import dbUtil.dbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    
    Connection connection;
    
    public LoginModel(){
        
        try{
            
            this.connection = dbConnection.getConnection();  
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        if(this.connection == null){
            System.exit(1);
        }
    
    }
    
    public boolean isDatabaseConnected(){
        return this.connection != null;  
    }
    
    public boolean isLogin(String email, String pass, String opt) throws Exception{
        
        PreparedStatement pr = null;
        ResultSet rs = null;
        
        String sql ="SELECT * FROM user where email = ? and password = ? and division = ?";
        
        try{
            
            pr = this.connection.prepareStatement(sql);
            pr.setString(1,email);
            pr.setString(2,pass);
            pr.setString(3,opt);
            
            rs = pr.executeQuery();
            
            boolean boll1;
            if(rs.next()){
                return true;
            }
            return false;
            
        }catch(SQLException ex){
            return false;
        }
        
        finally{
            {
                pr.close();
                rs.close();
            }
        }
        
    }
}
