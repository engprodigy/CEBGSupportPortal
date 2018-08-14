<%-- 
    Document   : productrolemenumapping
    Created on : 30-Nov-2017, 12:45:20
    Author     : BELLO
--%>

<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
      <h2>Select Menu to Map to ${roleName} Specification </h2>
    </div>
    <div class="col-sm">
      (Checked Columns of Menu are already mapped to role)
    </div> 
                          
                      </div>
      
    
   
     
    
     <c:forEach items="${ProductRoleMenuCheckBoxList}" var="item" varStatus="status"  > 
        
 <form:form method="POST" modelAttribute="productRoleMenuForm" class="form-signin">
        <!--<h2 class="form-signin-heading"> Product Name</h2>-->
       
        
        <h2> <b> <c:out value="${item.productName}"/> </b>  </h2>
        <p> <c:out value="${productNameArray[status.getIndex()]}"/>   </p>
           
           <h2 class="form-signin-heading"> Menu list already assigned To Role</h2>
        <spring:bind path="menuListCheckBox">
            <div class="form-group ${status.error ? 'has-error' : ''}">   
                
                
                         
                 <c:choose>
                   <c:when test="${fn:length(item.menuListCheckBox) > 0}">
                  <form:checkboxes items="${item.menuListCheckBox}" path="menuListCheckBox" class="form-control"
                     checked= "checked" disabled= "disabled"      />
                             <br />
                         </c:when>

                      <c:otherwise> <div style="color:red">No menu has been assigned to ${item.productName} for ${roleName} role</div>
                <br />
                </c:otherwise>
             </c:choose>
                
            
               <errors path="menuListCheckBox"></errors>
            </div>
        </spring:bind>
           
           
             <h3 class="form-signin-heading">Menu list for <c:out value="${item.productName}"/> 
</h3> 
             <p>* assign/reassign menu for <c:out value="${item.productName}"/> here </p>
        <spring:bind path="allMenuListCheckBox">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                
                 <c:choose>
                   <c:when test="${fn:length(item.allMenuListCheckBox) > 0}">
                  <form:checkboxes items="${item.allMenuListCheckBox}" path="allMenuListCheckBox" class="form-control"
                          />
                             <br />
                         </c:when>

                     <c:otherwise><div style="color:red"> No menu list available </div>
                <br />
                </c:otherwise>
             </c:choose>
            
               <errors path="allMenuListCheckBox"></errors>
            </div>
        </spring:bind>
        
        <spring:bind path="roleID">
            <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:hidden path="roleID" value="${roleID}" class="form-control"
                            /> 
               <errors path="roleID"></errors>
            </div>
        </spring:bind>
        <c:set var = "productname" scope = "session" value = "${productNameArray[status.getIndex()]}"/>
        <spring:bind path="productName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:hidden path="productName" value="${productname}" class="form-control"
                            /> 
               <errors path="productName"></errors>
            </div>
                </spring:bind>
         <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
         
         
            </form:form>

      
   </c:forEach> 
                 
                 
     

</div>
<!--/container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>



