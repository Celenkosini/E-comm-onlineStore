/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servelet;

import OnlineStorePractise.coonection.DBCon;
import OnlineStorePractise.model.User;
import dao.Userdao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Loginservelet", urlPatterns = {"/user-login"})
public class Loginservelet extends HttpServlet {

   
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.sendRedirect("login.jsp");
    }

   
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
       try(PrintWriter out = response.getWriter()){
           String email = request.getParameter("login-email");
           String password = request.getParameter("login-password");
          try{
             Userdao udoa = new Userdao(DBCon.getConnection());
             User user = udoa.userLogin(email, password);
            
             if(user != null){
                 out.print("user login");
                 request.getSession().setAttribute("auth", user);
                 response.sendRedirect("index.jsp");
             }else{
                 out.print("user login unsuccessful ");
                 
             }
          }catch(ClassNotFoundException |SQLException  e){
                e.printStackTrace();
          }
       
       }
    }

   
}
