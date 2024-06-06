/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servelet;

import OnlineStorePractise.coonection.DBCon;
import OnlineStorePractise.model.Cart;
import OnlineStorePractise.model.Order;
import OnlineStorePractise.model.User;
import dao.OrderDao;
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
@WebServlet(name = "CheckOutServelet", urlPatterns = {"/check-out"})
public class CheckOutServelet extends HttpServlet {

  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try(PrintWriter out = response.getWriter()){
       
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            
             ArrayList<Cart> cart_list = (ArrayList<Cart>)request.getSession().getAttribute("cart-list");
             User auth = (User) request.getSession().getAttribute("auth");
             if(cart_list != null && auth !=null){
             
                 for(Cart c:cart_list){
                     Order order = new Order();
                     order.setId(c.getId());
                     order.setuId(auth.getId());
                     order.setQuantity(c.getQuantity());
                     order.setDate(formatter.format(date));
                     OrderDao odao = new OrderDao(DBCon.getConnection());
                     boolean result = odao.insertOrder(order);
                     if(!result)break;   
                 }
                 cart_list.clear();
                 response.sendRedirect("orders.jsp");
             }else{
                 if(auth == null){
                    response.sendRedirect("login.jsp");
                 }else{
                     response.sendRedirect("cart.jsp");
                 }
             }
       }catch(Exception e){
            e.printStackTrace();}
    }

    
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

   

}
