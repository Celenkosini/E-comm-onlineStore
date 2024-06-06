
package dao;

import OnlineStorePractise.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Userdao {
    private Connection Conn;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;
     

    public Userdao(Connection Conn) {
        this.Conn = Conn;
    }
    public User userLogin(String email,String password){
       User user = null;
        try{
        query = "select * from users where email='"+email+"' and password='"+password+"'";
        pst = this.Conn.prepareStatement(query);
       // pst.setString(1,email);
       // pst.setString(2,password);
        pst.executeQuery();
        rs = pst.executeQuery(query);
        if(rs.next()){
        user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        }
        
        }catch(Exception e){
            e.printStackTrace();
            System.out.print(e.getMessage());
            
        }
        return user;
        
    }
    
    
}
