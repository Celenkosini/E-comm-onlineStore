/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OnlineStorePractise.model;

/**
 *
 * @author Teacher
 */
public class Order extends Product{
    private int orderId;
    private int uId;
    private int quantity;
    private String date;

    public Order() {
    }

    public Order(int orderId, int uId, int quantity, String date, int id, String name, String category, int price, String image) {
        super(id, name, category, price, image);
        this.orderId = orderId;
        this.uId = uId;
        this.quantity = quantity;
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getuId() {
        return uId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
 
    
}
