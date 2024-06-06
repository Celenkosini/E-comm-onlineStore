/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OnlineStorePractise.model;

/**
 *
 * @author Teacher
 */
public class Product {
    private int id;
    private String name;
    private String category;
    private int price;
    private String image;

    public Product() {
    }
    

    public Product(int id, String name, String category, int price, String image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;
    }


    public int getId() {
        return id;
    }
    public String getName(){
    return name;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

   

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name){
    this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    

    public int setPrice(int price) {
        this.price = price;
        return price;
    }

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
     
     
    
    
}
