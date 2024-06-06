/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import OnlineStorePractise.model.Order;
import OnlineStorePractise.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teacher
 */
public class OrderDao {
    private Connection Conn;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public OrderDao(Connection Conn) {
        this.Conn = Conn;
    }
    
    public boolean insertOrder(Order model){
        boolean result= false;
        try{
        query = "insert into orders(p_id,u_id,o_quantity,o_date)values(?,?,?,?)";
        pst = this.Conn.prepareStatement(query);
        pst.setInt(1, model.getId());
        pst.setInt(2, model.getuId());
        pst.setInt(3, model.getQuantity());
        pst.setString(4, model.getDate());
        pst.executeUpdate();
        result = true;
        
                
        }catch(Exception e){
        e.printStackTrace();
        }
    
    
    return result;
    }
    public List<Order> userOrders(int id){
       List<Order> list = new ArrayList<>();
       
       try{
           query = "select * from orders where u_id=?";
           pst = this.Conn.prepareStatement(query);
           pst.setInt(1, id);
           rs = pst.executeQuery();
           
           while(rs.next()){
               Order order = new Order();
               ProductDao productDao = new ProductDao(this.Conn);
               int pId = rs.getInt("p_id");
               
               Product product = productDao.getSingleProduct(pId);
               order.setOrderId(rs.getInt("o_id"));
               order.setId(pId);
               order.setCategory(product.getCategory());
               order.setPrice(product.getPrice()*rs.getInt("o_quanyity"));
               order.setQuantity(rs.getInt("o_quantity"));
               order.setDate(rs.getString("o_date"));
               list.add(order);
               
               
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       return list;
        
    }
}
