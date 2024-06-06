/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import OnlineStorePractise.model.Cart;
import OnlineStorePractise.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDao {
    private final Connection Conn;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProductDao(Connection Conn) {
        this.Conn = Conn;
    }
    public List <Product> getsAllProducts(){
        List<Product> product = new ArrayList<>();
        try{
        query = "select * from products";
        pst = this.Conn.prepareStatement(query);
        pst.executeQuery();
        rs = pst.executeQuery(query);
        while (rs.next()){
        Product row = new Product();
        row.setId(rs.getInt("id"));
        row.setName(rs.getString("name"));
        row.setCategory(rs.getString("category"));
        row.setPrice(rs.getInt("price"));
        row.setImage(rs.getString("image"));
            
            product.add(row);
        }
        }catch(SQLException e){
                }
        return product;
    }
    public List<Cart>getCartProduct(ArrayList<Cart>cartList){
        List<Cart> products = new ArrayList<>();
        try{
            if(cartList.size()>0)
                for(Cart item:cartList){
                    query = "select * from products where id = ?";
                    pst = this.Conn.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while(rs.next()){
                        Cart row = new Cart();
                        row.setId(rs.getInt("id"));
                        row.setName(rs.getString("name"));
                        row.setCategory(rs.getString("category"));
                        row.setPrice(rs.getInt("price")*item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        products.add(row);
                         
                         
                        
                    }
                }
        }catch(Exception e){
         e.printStackTrace();
         }
         return products;
    }
    public double getTotalPrice(ArrayList<Cart>cartList){
        double sum = 0;
        try{
            if (!cartList.isEmpty()){
                 for(Cart item: cartList){
                     query = "select price from products where id=?";
                     pst = this.Conn.prepareStatement(query);
                     pst.setInt(1,item.getId());
                     rs = pst.executeQuery();
                     
                     while(rs.next()){
                         sum += rs.getDouble("price")*item.getQuantity();
                    }
        
                 }
            
            
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return sum;
        
    }
    public Product getSingleProduct(int id){
        Product row = null;
        
        try{
            query = "select * from products wher id=?";
            pst = this.Conn.prepareStatement(query);
            pst.setInt(1, id);
            rs =pst.executeQuery();
            
            while(rs.next()){
                row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("catagory"));
                row.setPrice((int) rs.getDouble("prise"));
                row.setImage(rs.getString("image"));
            
            }
        }catch(Exception e){
                e.printStackTrace();
        }
        return row;
    
    
    
    }
}
