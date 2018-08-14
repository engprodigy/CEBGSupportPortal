<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : productmappingtwo
    Created on : 24-Nov-2017, 09:55:13
    Author     : BELLO
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

    <title>Map Product to Role</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">
    
    <div class="row"> <div class="col-sm">
      <h2>Select Products to Map to ${roleName} Specification </h2>
    </div>
    <div class="col-sm">
      (Checked Columns of Product are already mapped to role)
    </div> 
                          
                      </div>
      
    
    
     <form:form method="POST" modelAttribute="productCheckBox" class="form-signin">
        <h2 class="form-signin-heading"> </h2>
        <spring:bind path="productListMapToRoleCheckBox">
            <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:checkboxes items="${productListStringValue}" path="productListMapToRoleCheckBox" class="form-control"
                            />
               <errors path="productListMapToRoleCheckBox"></errors>
            </div>
        </spring:bind>
        <spring:bind path="roleID">
            <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:hidden path="roleID" value="${roleID}" class="form-control"
                            /> 
               <errors path="roleID"></errors>
            </div>
        </spring:bind>
         <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

   
   
    <h3><a href="${contextPath}/mapMenuToRole-${roleID}" >Map Selected Products Menu To ${roleName} Role</a></h3>

</div>
<!--/container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>


