<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <% request.getRequestDispatcher("/product?method=findByNew").forward(request,response);%>
  </body>
</html>
