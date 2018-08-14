<%-- 
    Document   : navigation
    Created on : Jun 21, 2017, 11:32:55 PM
    Author     : Olakunle Awotunbo
--%>


<nav class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <!--<a class="navbar-brand" href="<%=request.getContextPath()%>">WINGAMEPLAY</a>-->
                     <a class="navbar-brand" href="/">WINGAMEPLAY</a>
                     <a class="navbar-brand" href="/">Home</a>
                     <a class="navbar-brand" href="<%=request.getContextPath()%>/prediction">Match Prediction</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <!--<li><a href="<%=request.getContextPath()%>">Home</a></li>-->
                        <%--                   
                        <li><a href="/">Home</a></li>
                        <li><a href="<%=request.getContextPath()%>/prediction">Match Prediction</a></li>
                        --%>
                         <li><a href="<%=request.getContextPath()%>/addWinnerNumber">Winners List</a></li>
                        <li><a href="<%=request.getContextPath()%>/about">About</a></li>
                        <li><a href="<%=request.getContextPath()%>/contact">Contact</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="<%=request.getContextPath()%>/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    