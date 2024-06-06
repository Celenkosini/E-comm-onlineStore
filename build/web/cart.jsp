<%@page import="java.text.DecimalFormat"%>
<%@page import="OnlineStorePractise.coonection.DBCon"%>
<%@page import="dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="OnlineStorePractise.model.Cart"%>
<%@page import="OnlineStorePractise.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

   <%
            DecimalFormat dcf = new DecimalFormat("#,##");
            request.setAttribute("dcf", dcf);
             User auth = (User)request.getSession().getAttribute("auth");
             if(auth != null){
                request.setAttribute("auth", auth);
            
            }
            ArrayList<Cart> cart_list =(ArrayList<Cart>) session.getAttribute("cart-list");
            List<Cart> cartProduct = null;
            if(cart_list!= null){
                ProductDao pDao = new ProductDao(DBCon.getConnection());
                cartProduct = pDao.getCartProduct(cart_list);
                double total = pDao.getTotalPrice(cart_list);
                request.setAttribute("cart_list", cart_list);
                request.setAttribute("total", total);
            }
            

            %>
<!DOCTYPE html>
<html>
    <head>
        <title>online store cart</title>
        <%@include file = "includes/head.jsp" %>
        <style type="text/css">
            .table tbody td{
                vertical-align: middle;
            }
            .btn-incre, .btn-decre{
                box-shadow: none;
                font-size: 25px;
            }
            
        </style>
       
    </head>
    <body>
        <%@include file = "includes/navbar.jsp" %>
        <div class="container">
            <div class="d-flex py-3"><h3>Total price : R ${ dcf.format(total) }</h3><a href="check-out" class="btn btn-primary mx-3">Checkout</a></div>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">category</th>
                        <th scope="col">Price</th>
                        <th scope="col">buy now</th>
                        <th scope="col">cancel</th>
                    </tr>
                </thead>
                <tbody>
                    <%if(cart_list!= null){
                        for(Cart c:cartProduct){%>
                          <tr>
                              <td><%=c.getName()%></td>
                              <td><%=c.getCategory()%></td>
                              <td>R <%=dcf.format(c.getPrice())%></td>
                        <td>
                            <form action="order-now" method="post" class="form-inline">
                                <input type="hidden" name="id" value="<%=c.getId()%>" class="form-input">
                                <div class="form-group d-flex justify-content-between w-50">
                                    <a class="btn btn-sm btn-decre " href="quantity-inc-Dec?action=dec&id=<%=c.getId()%>"><i class="fas fa-minus-square"></i></a>
                                    <input type="text" name="quantity" class="form-contol w-25 " value="<%=c.getQuantity()%>" readony>
                                    <a class="btn btn-sm btn-incre " href="quantity-inc-Dec?action=inc&id=<%=c.getId()%>"><i class="fas fa-plus-square"></i></a>
                                    <button type="submit" class="btn btn-primary btn-sm">buy</button>
                                </div>
                                
                            </form>
                        </td>
                        <td>
                            <a href="remove-from-cart?id=<%=c.getId()%>" class ="btn btn-sm btn-danger">Remove</a>
                        </td>
                    </tr>
                        
                      <%  }
                        }
                    
                    %>
                  
                </tbody>
            </table>
            
        </div>
        
        <%@include file = "includes/footer.jsp" %>
    </body>
</html>