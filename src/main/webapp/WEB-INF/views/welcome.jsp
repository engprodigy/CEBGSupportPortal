<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container-fluid">
                                
    
               <h1>CEBG Support Portal</h1>
                    <p> &nbsp; </p>
                     
                      

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <p>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></p>

    </c:if>
             <p> &nbsp; </p>
                      <p> &nbsp; </p>
           
                <div class="row">   
                    <c:forEach var="item" items="${productList}" >
                    <div class="col-sm-3 col-md-6 col-lg-4">
                                  
                        <h3><a href="${contextPath}/setupRole" >${item.productname} </a></h3>   
                            
                           </div>
                        </c:forEach>
                    
                    </div>
                      
                      <div class="row">
                          <p>&nbsp;</p>
                          <p>&nbsp;</p>
                          <p>&nbsp;</p>
                          <p>&nbsp;</p>
                      </div>
                      
                      
                     <div class="row">
                    <c:if test="${Role == 'ROLE_ADMIN'}"> 
                   
                    <div class="col-sm-3 col-md-6 col-lg-4">
                                  
                        <h3><a href="${contextPath}/registration2" >Setup Users </a></h3> 
                        
                         <h3><a href="${contextPath}/mapProductToRole" >Map Role to Products and Menu </a></h3><!--*menu link
                         map role to products -->
                         <h3><a href="${contextPath}/addNewRole" >Create New Role</a></h3>     
                           </div>
                        
                        </c:if>
                        
                     </div>    
                            
               

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
