/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servelet;

import OnlineStorePractise.model.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "AddToCartServelet", urlPatterns = {"/add-to-cart"})
public class AddToCartServelet extends HttpServlet {

   

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
       try(PrintWriter out = response.getWriter()){
           ArrayList<Cart> cartList = new ArrayList<>();
           
           int id = Integer.parseInt(request.getParameter("id"));
           Cart cm = new Cart();
           cm.setId(id);
           cm.setQuantity(1);
           
           HttpSession session = request.getSession();
           ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
           
           if(cart_list == null){
           cartList.add(cm);
           session.setAttribute("cart-list", cartList);
            response.sendRedirect("index.jsp");
           }else{
           
                cartList = cart_list;
                boolean exist = false;
                for(Cart c:cartList){
                    if(c.getId()==id){
                        exist = true;
                        out.println("<h3 style='color:crimson;text-align:center'>Item already exist in cart <a href='cart.jsp'>Go to Cart Page</a></h3>");
                    }
                   
                }
                 if(!exist){
                        cartList.add(cm);
                        response.sendRedirect("index.jsp");
                    }
           }
       }
    }

    

}
