<%@page import="OnlineStorePractise.model.User"%>
<%@page import="OnlineStorePractise.coonection.DBCon"%>
<%@page import="dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="OnlineStorePractise.model.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%          User auth = (User)request.getSession().getAttribute("auth");
             if(auth != null){
                request.setAttribute("auth", auth);
            
            }

             ArrayList<Cart> cart_list =(ArrayList<Cart>) session.getAttribute("cart-list");
            List<Cart> cartProduct = null;
            if(cart_list!= null){
                ProductDao pDao = new ProductDao(DBCon.getConnection());
                cartProduct = pDao.getCartProduct(cart_list);
                request.setAttribute("cart_list", cart_list);
            }



%>
<!DOCTYPE html>
<html>
    <head>
        <title>online store login</title>
        <%@include file = "includes/head.jsp" %>
    </head>
    <body>
        <div class ="container">
            <div class=" card w-50 mx-auto my-5">
                <div class="card-hearder text-center"> USER LOGIN</div>
                <div class="card-body">
                    <form action="user-login" method="post">
                        <div class="form-group">
                            <label> Email address</label>
                            <input class ="form-control" name="login-email" placeholder = "enter email" required>
                        </div>
                        <div class="form-group">
                            <label> password</label>
                            <input class ="form-control" name="login-password" placeholder = "**********" required>
                        </div>
                        <div class="text-center">
                            <button type="submit" class ="btn btn-primary">login</>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        
        <%@include file = "includes/footer.jsp" %>
    </body>
</html>