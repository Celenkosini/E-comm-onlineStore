<%@page import="OnlineStorePractise.model.Order"%>
<%@page import="dao.OrderDao"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="OnlineStorePractise.coonection.DBCon"%>
<%@page import="dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="OnlineStorePractise.model.Cart"%>
<%@page import="OnlineStorePractise.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>online store orders</title>
        <%@include file = "includes/head.jsp" %>
          <%
               DecimalFormat dcf = new DecimalFormat("#,##");
            request.setAttribute("dcf", dcf);
             User auth = (User)request.getSession().getAttribute("auth");
             List<Order> orders = null;
             if(auth != null){
                request.setAttribute("auth", auth);
                orders = new OrderDao(DBCon.getConnection()).userOrders(auth.getId());
            
            }else{
              //response.sendRedirect("login.jsp");
              }
              ArrayList<Cart> cart_list =(ArrayList<Cart>) session.getAttribute("cart-list");
            List<Cart> cartProduct = null;
            if(cart_list!= null){
                ProductDao pDao = new ProductDao(DBCon.getConnection());
                cartProduct = pDao.getCartProduct(cart_list);
                request.setAttribute("cart_list", cart_list);
              }
            %>
    </head>
    <body>
        <%@include file = "includes/navbar.jsp" %>
        <div class="container">
            
            <div class="card-header my-3">All orders</div>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Date</th>
                        <th scope="col">Name</th>
                        <th scope="col">Category</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">price</th>
                        <th scope="col">Cancel</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if(orders != null){
                                for(Order o:orders){%>
                                    <td><%=o.getDate()%></td>
                                    <td><%=o.getName()%></td>
                                    <td><%=o.getCategory()%></td>
                                    <td><%=o.getQuantity()%></td>
                                    <td><%=o.getPrice()%></td>
                                    <td><a class="btn btn-sm btn-danger" href="cancel-order?id<%=o.getOrderId()%>">cancel</a></td>
                               <% }
                                     
                        
                        
                         }
                    
                    %>
                </tbody>
            </table>
            
        </div>
            
            
            
        
        
        <%@include file = "includes/footer.jsp" %>
    </body>
</html>