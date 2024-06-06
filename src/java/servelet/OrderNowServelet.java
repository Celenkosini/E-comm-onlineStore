/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servelet;

import OnlineStorePractise.coonection.DBCon;
import OnlineStorePractise.model.Cart;
import OnlineStorePractise.model.Order;
import OnlineStorePractise.model.User;
import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Teacher
 */
@WebServlet(name = "OrderNowServelet", urlPatterns = {"/order-now"})
public class OrderNowServelet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()){
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            
            User auth = (User) request.getSession().getAttribute("auth");
            if(auth != null){
                String productId = request.getParameter("id");
                int productQuantity = Integer.parseInt((request.getParameter("quantity")));
                if(productQuantity <=0){
                    productQuantity =1;
                }
                Order orderModel = new Order();
                orderModel.setId(Integer.parseInt(productId));
                orderModel.setuId(auth.getId());
                orderModel.setQuantity(productQuantity);
                orderModel.setDate(formatter.format(date));
                
                OrderDao orderdoa = new OrderDao(DBCon.getConnection());
                boolean results = orderdoa.insertOrder(orderModel);
                if(results){
                    
                ArrayList<Cart> cart_list = (ArrayList<Cart>)request.getSession().getAttribute("cart-list");
                if(cart_list !=null){
                    for(Cart c:cart_list){
                        if(c.getId()== Integer.parseInt(productId)){
                            cart_list.remove(cart_list.indexOf(c));
                            break;
                        }
                    }
                }
                
                    
                    
                    
                    response.sendRedirect("orders.jsp");
                }else{
                    out.print("order failed11111"+orderModel.getOrderId());
                
                }
                
            }else{
                response.sendRedirect("login.jsp");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

        
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    

}
