

<%@page import="java.util.ArrayList"%>
<%@page import="OnlineStorePractise.model.Cart"%>
<%@page import="java.util.List"%>
<%@page import="OnlineStorePractise.model.Product"%>
<%@page import="dao.ProductDao"%>
<%@page import="OnlineStorePractise.model.User"%>
<%@page import="OnlineStorePractise.coonection.DBCon"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

  <%
             User auth = (User)request.getSession().getAttribute("auth");
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
        <title>index</title>
        <%@include file = "includes/head.jsp" %>
      
    </head>
    <body>
        
        <%@include file = "includes/navbar.jsp" %>
        
        <div class="container">
           
            <div class="card">
                    <div class="card-header"> All Products </div>
                    <div class="row">
                        <%
                        ProductDao pd = new ProductDao(DBCon.getConnection());
                        List<Product> products = pd.getsAllProducts();
                        if(!products.isEmpty()){
                            for(Product p:products){%>
                            
                            
                    <div class="col-md-3 my-3 mx-3">
                    <div class="card" style="width: 18rem;">
                        <img src="images/<%=p.getImage()%>" class="card-img-top" alt="Card image cap">
                                <div class="card-body">
                                <h5 class="card-title"><%=p.getName()%></h5>
                                <h6 class="prece">price: R <%=p.getPrice()%></h6>
                                <h6 class="category">Category: <%=p.getCategory()%></h6>
                                <div class="mt-3 d-flex justify-content-between">
                                <a href="add-to-cart?id=<%=p.getId()%>" class="btn btn-dark">Add to cart</a>
                                <a href="order-now?quantity=1&id=<%=p.getId()%>" class="btn btn-primary">Buy now</a>
                                    
                     </div>         
                    </div>
                  </div>
                    </div>
          
                          <%  }
                            }
                        %>
                          
            
 
                    
                        </div>
            </div>
        
        
        <%@include file = "includes/footer.jsp" %>
    </body>
</html>
